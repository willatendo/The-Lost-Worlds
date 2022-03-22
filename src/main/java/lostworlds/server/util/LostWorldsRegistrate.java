package lostworlds.server.util;

import com.tterrag.registrate.AbstractRegistrate;
import com.tterrag.registrate.util.nullness.NonNullSupplier;

import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class LostWorldsRegistrate extends AbstractRegistrate<LostWorldsRegistrate> {
	protected LostWorldsRegistrate(String modid) {
		super(modid);
	}

	public static NonNullSupplier<LostWorldsRegistrate> lazy(String modid) {
		return NonNullSupplier.of(() -> new LostWorldsRegistrate(modid).registerEventListeners(FMLJavaModLoadingContext.get().getModEventBus()));
	}
}
