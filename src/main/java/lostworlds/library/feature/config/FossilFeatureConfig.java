package lostworlds.library.feature.config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import lostworlds.library.entity.DinoTypes;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.template.RuleTest;

public class FossilFeatureConfig implements IFeatureConfig
{
	public static final Codec<FossilFeatureConfig> CODEC = RecordCodecBuilder.create((instance) -> 
	{
		return instance.group(RuleTest.CODEC.fieldOf("filler").forGetter((feature) -> 
		{
			return feature.filler;
		}), DinoTypes.CODEC.fieldOf("type").forGetter((feature) ->
		{
			return feature.type;
		}), Codec.intRange(0, 64).fieldOf("size").forGetter((feature) ->
		{
			return feature.size;
		})).apply(instance, FossilFeatureConfig::new);
	});
	
	public final RuleTest filler;
	public final DinoTypes type;
	public final int size;
	
	public FossilFeatureConfig(RuleTest filler, DinoTypes type, int size) 
	{
		this.filler = filler;
		this.type = type;
		this.size = size;
	}
}
