package lostworlds.client.setup;

import lostworlds.client.LostWorldsBooks;
import lostworlds.server.LostWorldsUtils;
import lostworlds.server.block.LostWorldsWoodTypes;
import lostworlds.server.dimension.LostWorldsDimensionRenderInfo;
import lostworlds.server.item.LostWorldsBanners;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.resources.model.Material;
import net.minecraft.world.level.block.entity.BannerPattern;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@EventBusSubscriber(bus = Bus.MOD, modid = LostWorldsUtils.ID, value = Dist.CLIENT)
public class ClientSetup {
	@SubscribeEvent
	public static void clientSetup(FMLClientSetupEvent event) {
		LostWorldsDimensionRenderInfo.initClient();
		LostWorldsBooks.initBooks();

		event.enqueueWork(() -> {
			LostWorldsWoodTypes.WOOD_TYPES.forEach(woodType -> Sheets.addWoodType(woodType));
			LostWorldsBanners.BANNER_PATTERNS.forEach(bannerPattern -> createBannerPattern(bannerPattern));
		});
	}

	private static Material createBannerPatternMaterial(BannerPattern bannerPattern) {
		return new Material(Sheets.BANNER_SHEET, LostWorldsUtils.rL("entity/banner/" + bannerPattern.getFilename()));
	}

	private static Material createShieldPatternMaterial(BannerPattern bannerPattern) {
		return new Material(Sheets.SHIELD_SHEET, LostWorldsUtils.rL("entity/shield/" + bannerPattern.getFilename()));
	}

	private static void createBannerPattern(BannerPattern bannerPattern) {
		Sheets.BANNER_MATERIALS.put(bannerPattern, createBannerPatternMaterial(bannerPattern));
		Sheets.SHIELD_MATERIALS.put(bannerPattern, createShieldPatternMaterial(bannerPattern));
	}
}
