package lostworlds.server.entity.semiaquatic;

import java.util.Random;

import lostworlds.server.entity.controller.SemiAquaticMoveController;
import lostworlds.server.entity.terrestrial.Carnivore;
import lostworlds.server.entity.utils.SemiAquaticMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.Level;

public abstract class CarnivoreSemiAquaticMob extends Carnivore implements SemiAquaticMob {
	public static final EntityDataAccessor<BlockPos> TRAVEL_POS = SynchedEntityData.defineId(CarnivoreSemiAquaticMob.class, EntityDataSerializers.BLOCK_POS);
	public final Random random = new Random();
	private boolean isLandNavigator;

	public CarnivoreSemiAquaticMob(EntityType<? extends CarnivoreSemiAquaticMob> entity, Level world) {
		super(entity, world);
		this.setPathfindingMalus(BlockPathTypes.WATER, 0.0F);
		this.setPathfindingMalus(BlockPathTypes.WATER_BORDER, 0.0F);
		switchNavigator(false);
	}

	public void setTravelPos(BlockPos pos) {
		this.entityData.set(TRAVEL_POS, pos);
	}

	public BlockPos getTravelPos() {
		return this.entityData.get(TRAVEL_POS);
	}

	private void switchNavigator(boolean onLand) {
		if (onLand) {
			this.moveControl = new MoveControl(this);
			this.navigation = new GroundPathNavigation(this, this.level);
			this.isLandNavigator = true;
		} else {
			this.moveControl = new SemiAquaticMoveController(this);
			this.navigation = new WaterBoundPathNavigation(this, this.level);
			this.isLandNavigator = false;
		}
	}

	public abstract double getInWaterSpeed();

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(TRAVEL_POS, BlockPos.ZERO);
	}

	@Override
	public void addAdditionalSaveData(CompoundTag nbt) {
		super.addAdditionalSaveData(nbt);
		nbt.putInt("TravelPosX", this.getTravelPos().getX());
		nbt.putInt("TravelPosY", this.getTravelPos().getY());
		nbt.putInt("TravelPosZ", this.getTravelPos().getZ());
	}

	@Override
	public void readAdditionalSaveData(CompoundTag nbt) {
		super.readAdditionalSaveData(nbt);
		int l = nbt.getInt("TravelPosX");
		int i1 = nbt.getInt("TravelPosY");
		int j1 = nbt.getInt("TravelPosZ");
		this.setTravelPos(new BlockPos(l, i1, j1));
	}

	@Override
	public boolean shouldEnterWater() {
		return true;
	}

	@Override
	public boolean shouldLeaveWater() {
		return false;
	}

	@Override
	public boolean shouldStopMoving() {
		return false;
	}

	@Override
	public int getWaterSearchRange() {
		return 10;
	}

	@Override
	public boolean canBreatheUnderwater() {
		return false;
	}

	@Override
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor world, DifficultyInstance difficuilty, MobSpawnType reason, SpawnGroupData data, CompoundTag nbt) {
		this.setAirSupply(this.getMaxAirSupply());
		this.setTravelPos(BlockPos.ZERO);
		return super.finalizeSpawn(world, difficuilty, reason, data, nbt);
	}

	@Override
	public int getMaxAirSupply() {
		return 4800;
	}

	@Override
	protected int increaseAirSupply(int air) {
		return this.getMaxAirSupply();
	}

	@Override
	public void tick() {
		if (this.isNoAi()) {
			this.setAirSupply(this.getMaxAirSupply());
		}

		boolean ground = !this.isInWaterOrBubble();

		if (!ground && this.isLandNavigator) {
			switchNavigator(false);
		}
		if (ground && !this.isLandNavigator) {
			switchNavigator(true);
		}

		super.tick();
	}

	@Override
	public void travel(Vec3 vec3d) {
		if (!this.level.isClientSide() && this.isInWater()) {
			this.moveRelative(this.getSpeed(), vec3d);
			this.move(MoverType.SELF, this.getDeltaMovement());
			this.setDeltaMovement(this.getDeltaMovement().scale(getInWaterSpeed()));
			if (this.getTarget() == null) {
				this.setDeltaMovement(this.getDeltaMovement().add(0.0D, -0.005D, 0.0D));
			}
		} else {
			super.travel(vec3d);
		}
	}
}
