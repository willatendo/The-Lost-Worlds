package lostworlds.server.entity.terrestrial;

import java.util.Random;

import net.minecraft.world.entity.EntityType;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

public abstract class EggLayingMob extends TaggedMob {
	public final Random random = new Random();
	protected static final EntityDataAccessor<BlockPos> HOME_POS = SynchedEntityData.defineId(EggLayingMob.class, EntityDataSerializers.BLOCK_POS);
	protected static final EntityDataAccessor<Boolean> HAS_EGG = SynchedEntityData.defineId(EggLayingMob.class, EntityDataSerializers.BOOLEAN);
	protected static final EntityDataAccessor<Boolean> GOING_HOME = SynchedEntityData.defineId(EggLayingMob.class, EntityDataSerializers.BOOLEAN);
	private boolean hasTerritory;
	public int layEggCounter;

	public EggLayingMob(EntityType<? extends EggLayingMob> entity, Level world) {
		super(entity, world);
	}

	public boolean hasTerritory() {
		return this.hasTerritory;
	}

	public boolean setHasTerritory(boolean hasTerritory) {
		return this.hasTerritory = hasTerritory;
	}

	public boolean canMakeTerritory() {
		return hasTerritory() == false;
	}

	public void setHomePos(BlockPos pos) {
		this.entityData.set(HOME_POS, pos);
	}

	public BlockPos getHomePos() {
		return this.entityData.get(HOME_POS);
	}

	public void setHasEgg(boolean hasEgg) {
		this.entityData.set(HAS_EGG, hasEgg);
	}

	public boolean hasEgg() {
		return this.entityData.get(HAS_EGG);
	}

	public boolean isGoingHome() {
		return this.entityData.get(GOING_HOME);
	}

	public void setGoingHome(boolean goingHome) {
		this.entityData.set(GOING_HOME, goingHome);
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(HOME_POS, BlockPos.ZERO);
		this.entityData.define(HAS_EGG, false);
		this.entityData.define(GOING_HOME, false);
	}

	@Override
	public void addAdditionalSaveData(CompoundTag nbt) {
		super.addAdditionalSaveData(nbt);
		nbt.putBoolean("HasTerritory", this.hasTerritory());
		nbt.putInt("HomePosX", this.getHomePos().getX());
		nbt.putInt("HomePosY", this.getHomePos().getY());
		nbt.putInt("HomePosZ", this.getHomePos().getZ());
		nbt.putBoolean("HasEgg", this.hasEgg());
		nbt.putBoolean("GoingHome", this.isGoingHome());
	}

	@Override
	public void readAdditionalSaveData(CompoundTag nbt) {
		int x = nbt.getInt("HomePosX");
		int y = nbt.getInt("HomePosY");
		int z = nbt.getInt("HomePosZ");
		this.setHomePos(new BlockPos(x, y, z));
		super.readAdditionalSaveData(nbt);
		this.setHasEgg(nbt.getBoolean("HasEgg"));
		this.setGoingHome(nbt.getBoolean("GoingHome"));
		this.setHasTerritory(nbt.getBoolean("HasTerritory"));
	}

	@Override
	public boolean canFallInLove() {
		return super.canFallInLove() && !this.hasEgg();
	}
}
