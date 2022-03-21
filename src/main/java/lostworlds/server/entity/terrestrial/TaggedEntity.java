package lostworlds.server.entity.terrestrial;

import java.util.Optional;
import java.util.UUID;

import javax.annotation.Nullable;

import lostworlds.server.item.LostWorldsItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.scoreboard.Team;
import net.minecraft.server.management.PreYggdrasilConverter;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.Util;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;

public abstract class TaggedEntity extends PrehistoricEntity {
	protected static final DataParameter<Byte> DATA_FLAGS_ID = EntityDataManager.defineId(TaggedEntity.class, DataSerializers.BYTE);
	protected static final DataParameter<Optional<UUID>> DATA_OWNERUUID_ID = EntityDataManager.defineId(TaggedEntity.class, DataSerializers.OPTIONAL_UUID);

	public TaggedEntity(EntityType<? extends TaggedEntity> entity, World world) {
		super(entity, world);
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(DATA_FLAGS_ID, (byte) 0);
		this.entityData.define(DATA_OWNERUUID_ID, Optional.empty());
	}

	@Override
	public void addAdditionalSaveData(CompoundNBT nbt) {
		super.addAdditionalSaveData(nbt);
		if (this.getTaggedToUUID() != null) {
			nbt.putUUID("TaggedTo", this.getTaggedToUUID());
		}
	}

	@Override
	public void readAdditionalSaveData(CompoundNBT nbt) {
		super.readAdditionalSaveData(nbt);
		UUID uuid;
		if (nbt.hasUUID("TaggedTo")) {
			uuid = nbt.getUUID("TaggedTo");
		} else {
			String s = nbt.getString("TaggedTo");
			uuid = PreYggdrasilConverter.convertMobOwnerIfNecessary(this.getServer(), s);
		}

		if (uuid != null) {
			try {
				this.setTaggedToUUID(uuid);
				this.setTagged(true);
			} catch (Throwable throwable) {
				this.setTagged(false);
			}
		}
	}

	public boolean isTagged() {
		return (this.entityData.get(DATA_FLAGS_ID) & 4) != 0;
	}

	public void setTagged(boolean tagged) {
		byte b0 = this.entityData.get(DATA_FLAGS_ID);
		if (tagged) {
			this.entityData.set(DATA_FLAGS_ID, (byte) (b0 | 4));
		} else {
			this.entityData.set(DATA_FLAGS_ID, (byte) (b0 & -5));
		}
	}

	@Nullable
	public UUID getTaggedToUUID() {
		return this.entityData.get(DATA_OWNERUUID_ID).orElse((UUID) null);
	}

	public void setTaggedToUUID(@Nullable UUID uuid) {
		this.entityData.set(DATA_OWNERUUID_ID, Optional.ofNullable(uuid));
	}

	public void tag(PlayerEntity entity) {
		this.setTagged(true);
		this.setTaggedToUUID(entity.getUUID());
	}

	@Nullable
	public LivingEntity getOwner() {
		try {
			UUID uuid = this.getTaggedToUUID();
			return uuid == null ? null : this.level.getPlayerByUUID(uuid);
		} catch (IllegalArgumentException illegalargumentexception) {
			return null;
		}
	}

	public boolean isTaggedBy(LivingEntity entity) {
		return entity == this.getOwner();
	}

	public String getTaggedToName() {
		return this.getOwner().getName().getContents();
	}

	@Override
	public Team getTeam() {
		if (this.isTagged()) {
			LivingEntity livingentity = this.getOwner();
			if (livingentity != null) {
				return livingentity.getTeam();
			}
		}

		return super.getTeam();
	}

	@Override
	public boolean isAlliedTo(Entity entity) {
		if (this.isTagged()) {
			LivingEntity livingentity = this.getOwner();
			if (entity == livingentity) {
				return true;
			}

			if (livingentity != null) {
				return livingentity.isAlliedTo(entity);
			}
		}

		return super.isAlliedTo(entity);
	}

	@Override
	public void die(DamageSource source) {
		if (!this.level.isClientSide && this.level.getGameRules().getBoolean(GameRules.RULE_SHOWDEATHMESSAGES) && this.getOwner() instanceof ServerPlayerEntity) {
			this.getOwner().sendMessage(this.getCombatTracker().getDeathMessage(), Util.NIL_UUID);
		}

		super.die(source);
	}

	@Override
	public ActionResultType mobInteract(PlayerEntity entity, Hand hand) {
		ItemStack itemstack = entity.getItemInHand(hand);
		if (this.isTag(itemstack)) {
			if (itemstack.hasCustomHoverName()) {
				if (!this.level.isClientSide && this.isAlive()) {
					this.setCustomName(itemstack.getHoverName());
				}
			}
		}

		if (this.level.isClientSide) {
			if (this.isTagged() && this.isTaggedBy(entity)) {
				return ActionResultType.SUCCESS;
			} else {
				return !this.isFood(itemstack) || !(this.getHealth() < this.getMaxHealth()) && this.isTagged() ? ActionResultType.PASS : ActionResultType.SUCCESS;
			}
		} else if (this.isTag(itemstack)) {
			this.usePlayerItem(entity, itemstack);
			this.tag(entity);
			this.level.broadcastEntityEvent(this, (byte) 6);
			this.setPersistenceRequired();
			return ActionResultType.CONSUME;
		}

		ActionResultType actionresulttype1 = super.mobInteract(entity, hand);
		if (actionresulttype1.consumesAction()) {
			this.setPersistenceRequired();
		}

		return actionresulttype1;
	}

	public boolean isTag(ItemStack stack) {
		Ingredient tag = Ingredient.of(LostWorldsItems.TAG);
		return tag.test(stack);
	}
}
