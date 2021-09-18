package lostworlds.content.client.entity.model;

import java.util.List;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;

import lostworlds.library.entity.prehistoric.PrehistoricEntity;
import lostworlds.library.util.ModUtils;
import net.minecraft.util.ResourceLocation;

public class ExampleModel extends PatternModel<PrehistoricEntity>
{
	private static final ResourceLocation MALE_TEXTURE_NORMAL = ModUtils.rL("male_texture_normal");
	private static final List<ResourceLocation> maleTextures = ImmutableList.of(MALE_TEXTURE_NORMAL);
	private static final List<ResourceLocation> femaleTextures = ImmutableList.of(MALE_TEXTURE_NORMAL);

	public ExampleModel() 
	{
		super(Pair.of(maleTextures, femaleTextures));
	}

	@Override
	public ResourceLocation getAnimationFileLocation(PrehistoricEntity animatable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResourceLocation getModelLocation(PrehistoricEntity object) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
