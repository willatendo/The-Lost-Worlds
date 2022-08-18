package lostworlds.server.entity;

import lostworlds.server.block.LostWorldsBlocks;
import lostworlds.server.entity.utils.enums.ModBoatType;
import lostworlds.server.util.registrate.WoodTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.IndirectEntityDamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;

public class ModBoat extends Boat {
	private static final EntityDataAccessor<Integer> BOAT_TYPE = SynchedEntityData.defineId(ModBoat.class, EntityDataSerializers.INT);

	public ModBoat(Level level, double x, double y, double z) {
		this(LostWorldsEntities.MOD_BOAT.get(), level);
		this.setPos(x, y, z);
		this.setDeltaMovement(Vec3.ZERO);
		this.xo = x;
		this.yo = y;
		this.zo = z;
	}

	public ModBoat(EntityType<? extends ModBoat> entity, Level level) {
		super(entity, level);
	}

	public ModBoat(Level level) {
		super(LostWorldsEntities.MOD_BOAT.get(), level);
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
			return LostWorldsBlocks.ARAUCARIA.getBlock(WoodTypes.PLANKS).get().get();
		case CALAMITES:
			return LostWorldsBlocks.CALAMITES.getBlock(WoodTypes.PLANKS).get().get();
		case CONIFER:
			return LostWorldsBlocks.CONIFER.getBlock(WoodTypes.PLANKS).get().get();
		case CYPRESS:
			return LostWorldsBlocks.CYPRESS.getBlock(WoodTypes.PLANKS).get().get();
		case GINKGO:
			return LostWorldsBlocks.GINKGO.getBlock(WoodTypes.PLANKS).get().get();
		case SEQUOIA:
			return LostWorldsBlocks.SEQUOIA.getBlock(WoodTypes.PLANKS).get().get();
		default:
		case SCORCHED:
			return LostWorldsBlocks.SCORCHED.getBlock(WoodTypes.PLANKS).get().get();
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
	protected void addAdditionalSaveData(CompoundTag compound) {
		compound.putString("BoatType", this.getModBoatType().getName());
	}

	@Override
	protected void readAdditionalSaveData(CompoundTag compound) {
		if (compound.contains("BoatType", 8)) {
			this.setBYGBoatType(ModBoatType.getTypeFromString(compound.getString("BoatType")));
		}
	}

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
					if (this.status != Boat.Status.ON_LAND) {
						this.fallDistance = 0.0F;
						return;
					}

					this.causeFallDamage(this.fallDistance, 1.0F, DamageSource.FALL);
					if (!this.level.isClientSide && !this.isRemoved()) {
						this.kill();
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
		} else if (!this.level.isClientSide && !this.isRemoved()) {
			if (source instanceof IndirectEntityDamageSource && source.getEntity() != null && this.hasPassenger(source.getEntity())) {
				return false;
			} else {
				this.setHurtDir(-this.getHurtDir());
				this.setHurtTime(10);
				this.setDamage(this.getDamage() + amount * 10.0F);
				this.markHurt();
				boolean flag = source.getEntity() instanceof Player && ((Player) source.getEntity()).isCreative();
				if (flag || this.getDamage() > 40.0F) {
					if (!flag && this.level.getGameRules().getBoolean(GameRules.RULE_DOENTITYDROPS)) {
						this.spawnAtLocation(this.getDropItem());
					}

					this.kill();
				}

				return true;
			}
		} else {
			return true;
		}
	}

	@Override
	public Packet<?> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}
}
