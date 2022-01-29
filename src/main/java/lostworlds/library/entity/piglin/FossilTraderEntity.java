package lostworlds.library.entity.piglin;

public class FossilTraderEntity // extends AbstractPiglinEntity
{
//	private static final DataParameter<Boolean> DATA_BABY_ID = EntityDataManager.defineId(FossilTraderEntity.class, DataSerializers.BOOLEAN);
//	private static final DataParameter<Boolean> DATA_IS_DANCING = EntityDataManager.defineId(FossilTraderEntity.class, DataSerializers.BOOLEAN);
//	private static final UUID SPEED_MODIFIER_BABY_UUID = UUID.fromString("766bfa64-11f3-11ea-8d71-362b9e155667");
//	private static final AttributeModifier SPEED_MODIFIER_BABY = new AttributeModifier(SPEED_MODIFIER_BABY_UUID, "Baby speedModifer boost", (double)0.2F, AttributeModifier.Operation.MULTIPLY_BASE);
//	private final Inventory inventory = new Inventory(16);
//	private boolean cannotHunt = false;
//	protected static final ImmutableList<SensorType<? extends Sensor<? super FossilTraderEntity>>> SENSOR_TYPES = ImmutableList.of(SensorType.NEAREST_LIVING_ENTITIES, SensorType.NEAREST_PLAYERS, SensorType.NEAREST_ITEMS, SensorType.HURT_BY, SensorType.PIGLIN_SPECIFIC_SENSOR);
//	protected static final ImmutableList<MemoryModuleType<?>> MEMORY_TYPES = ImmutableList.of(MemoryModuleType.LOOK_TARGET, MemoryModuleType.DOORS_TO_CLOSE, MemoryModuleType.LIVING_ENTITIES, MemoryModuleType.VISIBLE_LIVING_ENTITIES, MemoryModuleType.NEAREST_VISIBLE_PLAYER, MemoryModuleType.NEAREST_VISIBLE_TARGETABLE_PLAYER, MemoryModuleType.NEAREST_VISIBLE_ADULT_PIGLINS, MemoryModuleType.NEARBY_ADULT_PIGLINS, MemoryModuleType.NEAREST_VISIBLE_WANTED_ITEM, MemoryModuleType.HURT_BY, MemoryModuleType.HURT_BY_ENTITY, MemoryModuleType.WALK_TARGET, MemoryModuleType.CANT_REACH_WALK_TARGET_SINCE, MemoryModuleType.ATTACK_TARGET, MemoryModuleType.ATTACK_COOLING_DOWN, MemoryModuleType.INTERACTION_TARGET, MemoryModuleType.PATH, MemoryModuleType.ANGRY_AT, MemoryModuleType.UNIVERSAL_ANGER, MemoryModuleType.AVOID_TARGET, MemoryModuleType.ADMIRING_ITEM, MemoryModuleType.TIME_TRYING_TO_REACH_ADMIRE_ITEM, MemoryModuleType.ADMIRING_DISABLED, MemoryModuleType.DISABLE_WALK_TO_ADMIRE_ITEM, MemoryModuleType.CELEBRATE_LOCATION, MemoryModuleType.DANCING, MemoryModuleType.HUNTED_RECENTLY, MemoryModuleType.NEAREST_VISIBLE_BABY_HOGLIN, MemoryModuleType.NEAREST_VISIBLE_NEMESIS, MemoryModuleType.NEAREST_VISIBLE_ZOMBIFIED, MemoryModuleType.RIDE_TARGET, MemoryModuleType.VISIBLE_ADULT_PIGLIN_COUNT, MemoryModuleType.VISIBLE_ADULT_HOGLIN_COUNT, MemoryModuleType.NEAREST_VISIBLE_HUNTABLE_HOGLIN, MemoryModuleType.NEAREST_TARGETABLE_PLAYER_NOT_WEARING_GOLD, MemoryModuleType.NEAREST_PLAYER_HOLDING_WANTED_ITEM, MemoryModuleType.ATE_RECENTLY, MemoryModuleType.NEAREST_REPELLENT);
//
//	public FossilTraderEntity(EntityType<? extends FossilTraderEntity> entity, World world) 
//	{
//		super(entity, world);
//	}
//	
//	@Override
//	public void addAdditionalSaveData(CompoundNBT nbt) 
//	{
//		super.addAdditionalSaveData(nbt);
//		if(this.isBaby())
//		{
//			nbt.putBoolean("IsBaby", true);
//		}
//		
//		if(this.cannotHunt)
//		{
//			nbt.putBoolean("CannotHunt", true);
//		}
//	}
//	
//	@Override
//	public void readAdditionalSaveData(CompoundNBT nbt) 
//	{
//		super.readAdditionalSaveData(nbt);
//		this.setBaby(nbt.getBoolean("IsBaby"));
//		this.setCannotHunt(nbt.getBoolean("CannotHunt"));
//		this.inventory.fromTag(nbt.getList("Inventory", 18));
//	}
//
//	@Override
//	protected void dropCustomDeathLoot(DamageSource p_213333_1_, int p_213333_2_, boolean p_213333_3_) 
//	{
//		super.dropCustomDeathLoot(p_213333_1_, p_213333_2_, p_213333_3_);
//		this.inventory.removeAllItems().forEach(this::spawnAtLocation);
//	}
//	
//	protected ItemStack addToInventory(ItemStack stack) 
//	{
//		return this.inventory.addItem(stack);
//	}
//	
//	protected boolean canAddToInventory(ItemStack stack) 
//	{
//		return this.inventory.canAddItem(stack);
//	}
//	
//	@Override
//	protected void defineSynchedData() 
//	{
//		super.defineSynchedData();
//		this.entityData.define(DATA_BABY_ID, false);
//		this.entityData.define(DATA_IS_DANCING, false);
//	}
//	
//	@Override
//	public void onSyncedDataUpdated(DataParameter<?> data) 
//	{
//		super.onSyncedDataUpdated(data);
//		if(DATA_BABY_ID.equals(data)) 
//		{
//			this.refreshDimensions();
//		}
//	}
//
//	public static boolean checkPiglinSpawnRules(EntityType<FossilTraderEntity> entity, IWorld world, SpawnReason reason, BlockPos pos, Random rand) 
//	{
//		return !world.getBlockState(pos.below()).is(Blocks.NETHER_WART_BLOCK);
//	}
//	
//	@Override
//	@Nullable
//	public ILivingEntityData finalizeSpawn(IServerWorld world, DifficultyInstance instance, SpawnReason reason, @Nullable ILivingEntityData data, @Nullable CompoundNBT nbt) 
//	{
//		if(reason != SpawnReason.STRUCTURE) 
//		{
//			if(world.getRandom().nextFloat() < 0.2F) 
//			{
//				this.setBaby(true);
//			}
//		}
//		
//		ModPiglinTasks.initMemories(this);
//		this.populateDefaultEquipmentSlots(instance);
//		this.populateDefaultEquipmentEnchantments(instance);
//		return super.finalizeSpawn(world, instance, reason, data, nbt);
//	}
//	
//	@Override
//	protected boolean shouldDespawnInPeaceful() 
//	{
//		return false;
//	}
//	
//	@Override
//	public boolean removeWhenFarAway(double distance) 
//	{
//		return !this.isPersistenceRequired();
//	}
//	
//	@Override
//	protected void populateDefaultEquipmentSlots(DifficultyInstance instance) 
//	{
//		if(this.isAdult()) 
//		{
//			this.maybeWearArmor(EquipmentSlotType.HEAD, new ItemStack(Items.GOLDEN_HELMET));
//			this.maybeWearArmor(EquipmentSlotType.CHEST, new ItemStack(Items.GOLDEN_CHESTPLATE));
//			this.maybeWearArmor(EquipmentSlotType.LEGS, new ItemStack(Items.GOLDEN_LEGGINGS));
//			this.maybeWearArmor(EquipmentSlotType.FEET, new ItemStack(Items.GOLDEN_BOOTS));
//		}
//	}
//	
//	private void maybeWearArmor(EquipmentSlotType slot, ItemStack stack) 
//	{
//		if(this.level.random.nextFloat() < 0.1F) 
//		{
//			this.setItemSlot(slot, stack);
//		}
//	}
//	
//	@Override
//	protected Brain.BrainCodec<FossilTraderEntity> brainProvider() 
//	{
//		return Brain.provider(MEMORY_TYPES, SENSOR_TYPES);
//	}
//	
//	@Override
//	protected Brain<?> makeBrain(Dynamic<?> dynamic) 
//	{
//		return PiglinTasks.makeBrain(this, this.brainProvider().makeBrain(dynamic));
//	}
//	
//	@Override
//	public Brain<FossilTraderEntity> getBrain() 
//	{
//		return (Brain<FossilTraderEntity>)super.getBrain();
//	}
//	
//	@Override
//	public ActionResultType mobInteract(PlayerEntity entity, Hand hand) 
//	{
//		ActionResultType actionresulttype = super.mobInteract(entity, hand);
//		if(actionresulttype.consumesAction()) 
//		{
//			return actionresulttype;
//		} 
//		else if(!this.level.isClientSide) 
//		{
//			return PiglinTasks.mobInteract(this, entity, hand);
//		} 
//		else 
//		{
//			boolean flag = PiglinTasks.canAdmire(this, entity.getItemInHand(hand)) && this.getArmPose() != PiglinAction.ADMIRING_ITEM;
//			return flag ? ActionResultType.SUCCESS : ActionResultType.PASS;
//		}
//	}
//	
//	@Override
//	protected float getStandingEyeHeight(Pose pose, EntitySize size) 
//	{
//		return this.isBaby() ? 0.93F : 1.74F;
//	}
//	
//	@Override
//	public double getPassengersRidingOffset() 
//	{
//		return (double)this.getBbHeight() * 0.92D;
//	}
//	
//	@Override
//	public void setBaby(boolean baby) 
//	{
//		this.getEntityData().set(DATA_BABY_ID, baby);
//		if(!this.level.isClientSide) 
//		{
//			ModifiableAttributeInstance modifiableattributeinstance = this.getAttribute(Attributes.MOVEMENT_SPEED);
//			modifiableattributeinstance.removeModifier(SPEED_MODIFIER_BABY);
//			if(baby) 
//			{
//				modifiableattributeinstance.addTransientModifier(SPEED_MODIFIER_BABY);
//			}
//		}
//	}
//	
//	@Override
//	public boolean isBaby() 
//	{
//		return this.getEntityData().get(DATA_BABY_ID);
//	}
//	
//	private void setCannotHunt(boolean canHunt) 
//	{
//		this.cannotHunt = canHunt;
//	}
//	
//	protected boolean canHunt() 
//	{
//		return !this.cannotHunt;
//	}
//	
//	@Override
//	protected void customServerAiStep() 
//	{
//		this.level.getProfiler().push("piglinBrain");
//		this.getBrain().tick((ServerWorld)this.level, this);
//		this.level.getProfiler().pop();
//		PiglinTasks.updateActivity(this);
//		super.customServerAiStep();
//	}
//
//	@Override
//	protected int getExperienceReward(PlayerEntity entity) 
//	{
//		return this.xpReward;
//	}
//	
//	@Override
//	protected void finishConversion(ServerWorld p_234416_1_) 
//	{
//		PiglinTasks.cancelAdmiring(this);
//		this.inventory.removeAllItems().forEach(this::spawnAtLocation);
//		super.finishConversion(p_234416_1_);
//	}
//	
//	@Override
//	public PiglinAction getArmPose() 
//	{
//		if(this.isDancing()) 
//		{
//			return PiglinAction.DANCING;
//		} 
//		else if(PiglinTasks.isLovedItem(this.getOffhandItem().getItem())) 
//		{
//			return PiglinAction.ADMIRING_ITEM;
//		}
//		else 
//		{
//			return PiglinAction.DEFAULT;
//		}
//	}
//	
//	public boolean isDancing() 
//	{
//		return this.entityData.get(DATA_IS_DANCING);
//	}   
//	
//	public void setDancing(boolean dancing) 
//	{
//		this.entityData.set(DATA_IS_DANCING, dancing);
//	}
//	
//	@Override
//	public boolean hurt(DamageSource source, float damage) 
//	{
//		boolean flag = super.hurt(source, damage);
//		if(this.level.isClientSide) 
//		{
//			return false;
//		} 
//		else 
//		{
//			if(flag && source.getEntity() instanceof LivingEntity) 
//			{
//				PiglinTasks.wasHurtBy(this, (LivingEntity)source.getEntity());
//			}
//			
//			return flag;
//		}
//	}
//
//	protected void holdInMainHand(ItemStack stack) 
//	{
//		this.setItemSlotAndDropWhenKilled(EquipmentSlotType.MAINHAND, stack);
//	}
//	
//	protected void holdInOffHand(ItemStack stack) 
//	{
//		if(stack.isPiglinCurrency()) 
//		{
//			this.setItemSlot(EquipmentSlotType.OFFHAND, stack);
//			this.setGuaranteedDrop(EquipmentSlotType.OFFHAND);
//		} 
//		else 
//		{
//			this.setItemSlotAndDropWhenKilled(EquipmentSlotType.OFFHAND, stack);
//		}
//	}
//	
//	@Override
//	public boolean wantsToPickUp(ItemStack stack) 
//	{
//		return ForgeEventFactory.getMobGriefingEvent(this.level, this) && this.canPickUpLoot() && PiglinTasks.wantsToPickup(this, stack);
//	}
//	
//	protected boolean canReplaceCurrentItem(ItemStack stack) 
//	{
//		EquipmentSlotType equipmentslottype = MobEntity.getEquipmentSlotForItem(stack);
//		ItemStack itemstack = this.getItemBySlot(equipmentslottype);
//		return this.canReplaceCurrentItem(stack, itemstack);
//	}
//	
//	@Override
//	protected boolean canReplaceCurrentItem(ItemStack stack, ItemStack replacementStack) 
//	{
//		if(EnchantmentHelper.hasBindingCurse(replacementStack)) 
//		{
//			return false;
//		} 
//		else 
//		{
//			boolean flag = PiglinTasks.isLovedItem(stack.getItem());
//			boolean flag1 = PiglinTasks.isLovedItem(replacementStack.getItem());
//			if(flag && !flag1) 
//			{
//				return true;
//			} 
//			else if(!flag && flag1) 
//			{
//				return false;
//			} 
//			else 
//			{
//				return super.canReplaceCurrentItem(stack, replacementStack);
//			}
//		}
//	}
//	
//	@Override
//	protected void pickUpItem(ItemEntity entity) 
//	{
//		this.onItemPickup(entity);
//		PiglinTasks.pickUpItem(this, entity);
//	}
//	
//	@Override
//	public boolean startRiding(Entity entity, boolean riding) 
//	{
//		if(this.isBaby() && entity.getType() == EntityType.HOGLIN) 
//		{
//			entity = this.getTopPassenger(entity, 3);
//		}
//		
//		return super.startRiding(entity, riding);
//	}
//	
//	private Entity getTopPassenger(Entity passenger, int place) 
//	{
//		List<Entity> list = passenger.getPassengers();
//		return place != 1 && !list.isEmpty() ? this.getTopPassenger(list.get(0), place - 1) : passenger;
//	}
//	
//	@Override
//	protected SoundEvent getAmbientSound() 
//	{
//		return this.level.isClientSide ? null : PiglinTasks.getSoundForCurrentActivity(this).orElse((SoundEvent)null);
//	}
//	
//	@Override
//	protected SoundEvent getHurtSound(DamageSource source) 
//	{
//		return SoundEvents.PIGLIN_HURT;
//	}
//	
//	@Override
//	protected SoundEvent getDeathSound() 
//	{
//		return SoundEvents.PIGLIN_DEATH;
//	}
//	
//	@Override
//	protected void playStepSound(BlockPos pos, BlockState state) 
//	{
//		this.playSound(SoundEvents.PIGLIN_STEP, 0.15F, 1.0F);
//	}
//	
//	protected void playSound(SoundEvent sound) 
//	{
//		this.playSound(sound, this.getSoundVolume(), this.getVoicePitch());
//	}
//	
//	@Override
//	protected void playConvertedSound() 
//	{
//		this.playSound(SoundEvents.PIGLIN_CONVERTED_TO_ZOMBIFIED);
//	}
}
