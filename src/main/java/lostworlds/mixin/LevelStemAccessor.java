package lostworlds.mixin;

import java.util.Set;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.dimension.LevelStem;

@Mixin(LevelStem.class)
public interface LevelStemAccessor {
	@Accessor("BUILTIN_ORDER")
	static Set<ResourceKey<LevelStem>> getBuiltinOrder() {
		throw new UnsupportedOperationException();
	}

	@Mutable
	@Accessor("BUILTIN_ORDER")
	static void setBuiltinOrder(Set<ResourceKey<LevelStem>> order) {
		throw new UnsupportedOperationException();
	}
}