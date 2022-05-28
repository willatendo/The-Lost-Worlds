package lostworlds.server.item;

import lostworlds.server.LostWorldsUtils;
import lostworlds.server.effect.AshyLungEffect;
import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class LostWorldsMobEffects {
	public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(ForgeRegistries.Keys.MOB_EFFECTS, LostWorldsUtils.ID);

	public static final RegistryObject<AshyLungEffect> ASHY_LUNG_EFFECT = MOB_EFFECTS.register("ashy_lung_effect", () -> new AshyLungEffect());
}
