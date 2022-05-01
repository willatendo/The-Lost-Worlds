package lostworlds.server.entity;

import lostworlds.server.block.LostWorldsBlocks;
import lostworlds.server.entity.utils.enums.ModBoatType;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.item.BoatEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IndirectEntityDamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.fml.network.NetworkHooks;

public class ModBoatEntity extends BoatEntity {
	private static final DataParameter<Integer> BOAT_TYPE = EntityDataManager.defineId(ModBoatEntity.class, DataSerializers.INT);

	public ModBoatEntity(World world, double x, double y, double z) {
		this(LostWorldsEntities.MOD_BOAT.get(), world);
		this.setPos(x, y, z);
		this.setDeltaMovement(Vector3d.ZERO);
		this.xo = x;
		this.yo = y;
		this.zo = z;
	}

	public ModBoatEntity(EntityType<? extends ModBoatEntity> entity, World world) {
		super(entity, world);
	}

	public ModBoatEntity(FMLPlayMessages.SpawnEntity packet, World world) {
		super(LostWorldsEntities.MOD_BOAT.get(), world);
	}

	@Override
	public Item getDropItem() {
		switch (this.getModBoatType()) {
		case ARAUCARIA:
			return LostWorldsBlocks.ARAUCARIA.getItem(0).get().get();
		case CALAMITES:
			return LostWorldsBlocks.CALAMITES.getItem(0).get().get();
		case CONIFER:
			return LostWorldsBlocks.CONIFER.getItem(0).get().get();
		case CYPRESS:
			return LostWorldsBlocks.CYPRESS.getItem(0).get().get();
		case GINKGO:
			return LostWorldsBlocks.GINKGO.getItem(0).get().get();
		case SEQUOIA:
			return LostWorldsBlocks.SEQUOIA.getItem(0).get().get();
		default:
		case SCORCHED:
			return LostWorldsBlocks.SCORCHED.getItem(0).get().get();
		}
	}

	public Block getPlanks() {
		switch (this.getModBoatType()) {
		case ARAUCARIA:
			return LostWorldsBlocks.ARAUCARIA.getBlock(6).get().get();
		case CALAMITES:
			return LostWorldsBlocks.CALAMITES.getBlock(6).get().get();
		case CONIFER:
			return LostWorldsBlocks.CONIFER.getBlock(6).get().get();
		case CYPRESS:
			return LostWorldsBlocks.CYPRESS.getBlock(6).get().get();
		case GINKGO:
			return LostWorldsBlocks.GINKGO.getBlock(6).get().get();
		case SEQUOIA:
			return LostWorldsBlocks.SEQUOIA.getBlock(6).get().get();
		default:
		case SCORCHED:
			return LostWorldsBlocks.SCORCHED.getBlock(6).get().get();
		}
	}

	public ModBoatType getModBoatType() {
		return ModBoatType.byId(this.entityData.get(BOAT_TYPE));
	}

	public void setBYGBoatType(ModBoatType boatType) {
		this.entityData.set(BOAT_TYPE, boatType.ordinal());
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(BOAT_TYPE, ModBoatType.ARAUCARIA.ordinal());
	}

	@Override
	protected void addAdditionalSaveData(CompoundNBT compound) {
		compound.putString("BoatType", this.getModBoatType().getName());
	}

	@Override
	protected void readAdditionalSaveData(CompoundNBT compound) {
		if (compound.contains("BoatType", 8)) {
			this.setBYGBoatType(ModBoatType.getTypeFromString(compound.getString("BoatType")));
		}
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public void animateHurt() {
		this.setHurtDir(-this.getHurtDir());
		this.setHurtTime(10);
		this.setDamage(this.getDamage() * 11.0F);
	}

	@Override
	protected void checkFallDamage(double y, boolean onGroundIn, BlockState state, BlockPos pos) {
		this.lastYd = this.getDeltaMovement().y;
		if (!this.isPassenger()) {
			if (onGroundIn) {
				if (this.fallDistance > 3.0F) {
					if (this.status != BoatEntity.Status.ON_LAND) {
						this.fallDistance = 0.0F;
						return;
					}

					this.causeFallDamage(this.fallDistance, 1.0F);
					if (!this.level.isClientSide && !this.removed) {
						this.remove();
						if (this.level.getGameRules().getBoolean(GameRules.RULE_DOENTITYDROPS)) {
							for (int i = 0; i < 3; ++i) {
								this.spawnAtLocation(this.getPlanks());
							}

							for (int j = 0; j < 2; ++j) {
								this.spawnAtLocation(Items.STICK);
							}

							this.spawnAtLocation(Blocks.AIR);
						}
					}
				}

				this.fallDistance = 0.0F;
			} else if (!this.level.getFluidState((new BlockPos(this.getX(), this.getY(), this.getZ()).below())).is(FluidTags.WATER) && y < 0.0D) {
				this.fallDistance = (float) ((double) this.fallDistance - y);
			}

		}
	}

	@Override
	public boolean hurt(DamageSource source, float amount) {
		if (this.isInvulnerableTo(source)) {
			return false;
		} else if (!this.level.isClientSide && !this.removed) {
			if (source instanceof IndirectEntityDamageSource && source.getEntity() != null && this.hasPassenger(source.getEntity())) {
				return false;
			} else {
				this.setHurtDir(-this.getHurtDir());
				this.setHurtTime(10);
				this.setDamage(this.getDamage() + amount * 10.0F);
				this.markHurt();
				boolean flag = source.getEntity() instanceof PlayerEntity && ((PlayerEntity) source.getEntity()).abilities.instabuild;
				if (flag || this.getDamage() > 40.0F) {
					if (!flag && this.level.getGameRules().getBoolean(GameRules.RULE_DOENTITYDROPS)) {
						this.spawnAtLocation(this.getDropItem());
					}

					this.remove();
				}

				return true;
			}
		} else {
			return true;
		}
	}

	@Override
	public IPacket<?> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}
}
