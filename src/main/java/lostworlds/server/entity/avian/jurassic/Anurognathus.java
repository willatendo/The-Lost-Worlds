package lostworlds.server.entity.avian.jurassic;

import lostworlds.server.entity.SpeciesTagModelAndTextureable;
import lostworlds.server.entity.terrestrial.EggLayingMob;
import lostworlds.server.entity.utils.enums.ActivityType;
import lostworlds.server.entity.utils.enums.CreatureDiet;
import lostworlds.server.species.SpeciesType;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class Anurognathus extends EggLayingMob implements SpeciesTagModelAndTextureable {
	public Anurognathus(EntityType<? extends EggLayingMob> entity, Level world) {
		super(entity, world);
	}

	@Override
	public void registerControllers(AnimationData data) {
	}

	@Override
	public AnimationFactory getFactory() {
		return null;
	}

	@Override
	public CreatureDiet diet() {
		return null;
	}

	@Override
	public ActivityType activity() {
		return null;
	}

	@Override
	public TagKey<SpeciesType> getTagToUse() {
		return null;
	}

	@Override
	public byte getVarientData() {
		return 0;
	}

	@Override
	public int maxHunger() {
		return 0;
	}

	@Override
	public AgeableMob getBreedOffspring(ServerLevel p_146743_, AgeableMob p_146744_) {
		return null;
	}
}
