package lostworlds.content.client.entity.model;

import java.util.List;

import com.mojang.datafixers.util.Pair;

import lostworlds.library.entity.prehistoric.PrehistoricEntity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public abstract class PatternModel<T extends PrehistoricEntity> extends AnimatedGeoModel<T>
{
	//The left side is the male textures and the right side is the female textures.
	public static Pair<List<ResourceLocation>, List<ResourceLocation>> textures;
	private ResourceLocation texture;
	public static int entries = textures.getFirst().size();
	
	public PatternModel(Pair<List<ResourceLocation>, List<ResourceLocation>> textures) 
	{
		this.textures = textures;
	}
	
	@Override
	public ResourceLocation getTextureLocation(T object) 
	{
		return texture;
	}
	
	
	
	@Override
	public void setLivingAnimations(T entity, Integer uniqueID, AnimationEvent customPredicate) 
	{
		super.setLivingAnimations(entity, uniqueID, customPredicate);
		
		List<ResourceLocation> maleTexture = textures.getFirst(); 
		List<ResourceLocation> femaleTexture = textures.getSecond();
		ResourceLocation maletexture = maleTexture.get(entity.getPattern());
		ResourceLocation femaletexture = femaleTexture.get(entity.getPattern());
		
		if(entity.getSex() == 0)
		{
			this.texture = maletexture;
		}
		if(entity.getSex() == 1)
		{
			this.texture = femaletexture;
		}
	}
}
