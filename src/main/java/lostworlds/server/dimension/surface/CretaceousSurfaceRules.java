package lostworlds.server.dimension.surface;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableList.Builder;

import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.VerticalAnchor;

public class CretaceousSurfaceRules extends SurfaceRuleParts {
	public static SurfaceRules.RuleSource cretaceous() {
		Builder<SurfaceRules.RuleSource> builder = ImmutableList.builder();
		builder.add(SurfaceRules.ifTrue(SurfaceRules.verticalGradient("bedrock_floor", VerticalAnchor.bottom(), VerticalAnchor.aboveBottom(5)), BEDROCK));

		builder.add(SurfaceRules.ifTrue(SurfaceRules.verticalGradient("deepslate", VerticalAnchor.absolute(0), VerticalAnchor.absolute(8)), DEEPSLATE));
		return SurfaceRules.sequence(builder.build().toArray((rules) -> {
			return new SurfaceRules.RuleSource[rules];
		}));
	}
}
