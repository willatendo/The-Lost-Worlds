package lostworlds.server.entity.terrestrial;

import java.util.Optional;
import java.util.UUID;

import javax.annotation.Nullable;

import lostworlds.server.item.LostWorldsItems;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.scores.Team;
import net.minecraft.server.players.OldUsersConverter;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.Util;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;

public abstract class TaggedEntity extends PrehistoricEntity {
	protected static final EntityDataAccessor<Byte> DATA_FLAGS_ID = SynchedEntityData.defineId(TaggedEntity.class, EntityDataSerializers.BYTE);
	protected static final EntityDataAccessor<Optional<UUID>> DATA_OWNERUUID_ID = SynchedEntityData.defineId(TaggedEntity.class, EntityDataSerializers.OPTIONAL_UUID);

	public TaggedEntity(EntityType<? extends TaggedEntity> entity, Level world) {
		super(entity, world);
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(DATA_FLAGS_ID, (byte) 0);
		this.entityData.define(DATA_OWNERUUID_ID, Optional.empty());
	}

	@Override
	public void addAdditionalSaveData(CompoundTag nbt) {
		super.addAdditionalSaveData(nbt);
		if (this.getTaggedToUUID() != null) {
			nbt.putUUID("TaggedTo", this.getTaggedToUUID());
		}
	}

	@Override
	public void readAdditionalSaveData(CompoundTag nbt) {
		super.readAdditionalSaveData(nbt);
		UUID uuid;
		if (nbt.hasUUID("TaggedTo")) {
			uuid = nbt.getUUID("TaggedTo");
		} else {
			String s = nbt.getString("TaggedTo");
			uuid = OldUsersConverter.convertMobOwnerIfNecessary(this.getServer(), s);
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

	public void tag(Player entity) {
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
		if (!this.level.isClientSide && this.level.getGameRules().getBoolean(GameRules.RULE_SHOWDEATHMESSAGES) && this.getOwner() instanceof ServerPlayer) {
			this.getOwner().sendMessage(this.getCombatTracker().getDeathMessage(), Util.NIL_UUID);
		}

		super.die(source);
	}

	@Override
	public InteractionResult mobInteract(Player entity, InteractionHand hand) {
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
				return InteractionResult.SUCCESS;
			} else {
				return !this.isFood(itemstack) || !(this.getHealth() < this.getMaxHealth()) && this.isTagged() ? InteractionResult.PASS : InteractionResult.SUCCESS;
			}
		} else if (this.isTag(itemstack)) {
			this.usePlayerItem(entity, itemstack);
			this.tag(entity);
			this.level.broadcastEntityEvent(this, (byte) 6);
			this.setPersistenceRequired();
			return InteractionResult.CONSUME;
		}

		InteractionResult actionresulttype1 = super.mobInteract(entity, hand);
		if (actionresulttype1.consumesAction()) {
			this.setPersistenceRequired();
		}

		return actionresulttype1;
	}

	public boolean isTag(ItemStack stack) {
		Ingredient tag = Ingredient.of(LostWorldsItems.TAG.get());
		return tag.test(stack);
	}
}
