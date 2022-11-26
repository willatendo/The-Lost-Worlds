package lostworlds.server.entity.avian.jurassic;

import lostworlds.server.LostWorldsTags;
import lostworlds.server.entity.SpeciesTagModelAndTextureable;
import lostworlds.server.entity.avian.Pterosaur;
import lostworlds.server.entity.utils.enums.ActivityType;
import lostworlds.server.entity.utils.enums.CreatureDiet;
import lostworlds.server.species.SpeciesType;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class Anurognathus extends Pterosaur implements SpeciesTagModelAndTextureable {
	private AnimationFactory factory = new AnimationFactory(this);

	public Anurognathus(EntityType<? extends Pterosaur> entity, Level world) {
		super(entity, world);
	}

	@Override
	public void registerControllers(AnimationData data) {
		data.addAnimationController(new AnimationController<IAnimatable>(this, "controller", 0, this::predicate));
	}

	@Override
	public AnimationFactory getFactory() {
		return this.factory;
	}

	@Override
	public CreatureDiet diet() {
		return CreatureDiet.PISCIVORE;
	}

	@Override
	public ActivityType activity() {
		return ActivityType.CREPUSCULAR;
	}

	@Override
	public TagKey<SpeciesType> getTagToUse() {
		return LostWorldsTags.ModSpeciesTypeTags.ANUROGNATHUS;
	}

	@Override
	public byte getVarientData() {
		return this.getVarient();
	}

	@Override
	public int maxHunger() {
		return 8000;
	}

	@Override
	public AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
		return null;
	}

	@Override
	public int maxFlyHeight() {
		return 10;
	}
}
