package lostworlds.library.entity.aquatic.modern;

import lostworlds.content.config.LostWorldsConfig;
import lostworlds.library.entity.aquatic.BasicFishLikeEntity;
import lostworlds.library.entity.utils.enums.DinoTypes;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;
import tyrannotitanlib.library.tyrannomation.core.ITyrannomatable;
import tyrannotitanlib.library.tyrannomation.core.PlayState;
import tyrannotitanlib.library.tyrannomation.core.builder.TyrannomationBuilder;
import tyrannotitanlib.library.tyrannomation.core.controller.TyrannomationController;
import tyrannotitanlib.library.tyrannomation.core.event.predicate.TyrannomationEvent;
import tyrannotitanlib.library.tyrannomation.core.manager.TyrannomationData;
import tyrannotitanlib.library.tyrannomation.core.manager.TyrannomationFactory;

public class NautilusEntity extends BasicFishLikeEntity implements ITyrannomatable
{
	private TyrannomationFactory factory = new TyrannomationFactory(this);
	
	private <E extends ITyrannomatable> PlayState predicate(TyrannomationEvent<E> event) 
	{
		event.getController().setAnimation(new TyrannomationBuilder().addAnimation("animation.nautilus", true));
		return PlayState.CONTINUE;
	}
	
	public NautilusEntity(EntityType<? extends CreatureEntity> entity, World world) 
	{
		super(entity, world);
	}
	
	public static AttributeModifierMap createAttributes() 
	{
		return MonsterEntity.createMonsterAttributes().add(Attributes.MAX_HEALTH, LostWorldsConfig.COMMON_CONFIG.nautilusHeath.get()).add(Attributes.ARMOR, LostWorldsConfig.COMMON_CONFIG.nautilusArmour.get()).build();
	}

	@Override
	protected ItemStack getBucketItemStack() 
	{
		return DinoTypes.NAUTILUS.getFishBucket().getDefaultInstance();
	}

	@Override
	protected SoundEvent getFlopSound() 
	{
		return SoundEvents.BONE_BLOCK_BREAK;
	}
	
	@Override
	protected SoundEvent getHurtSound(DamageSource source) 
	{
		return SoundEvents.ITEM_BREAK;
	}

	@Override
	public void registerControllers(TyrannomationData data) 
	{
		data.addAnimationController(new TyrannomationController<ITyrannomatable>(this, "controller", 0, this::predicate));
	}

	@Override
	public TyrannomationFactory getFactory() 
	{
		return this.factory;
	}
}