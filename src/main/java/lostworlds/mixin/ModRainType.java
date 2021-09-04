package lostworlds.mixin;

import org.spongepowered.asm.mixin.Mixin;

import net.minecraft.util.IStringSerializable;
import net.minecraft.world.biome.Biome.RainType;

@Mixin(RainType.class)
public enum ModRainType implements IStringSerializable 
{
	ASH("ash");

	public String name;

	private ModRainType(String id) 
	{
       this.name = id;
    }

	public String getName() 
	{
		return this.name;
	}

	public String getSerializedName() 
	{
		return this.name;
	}
}
