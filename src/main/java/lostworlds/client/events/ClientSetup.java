package lostworlds.client.events;

import lostworlds.client.LostWorldsConfig;
import lostworlds.client.entity.render.AllosaurusRenderer;
import lostworlds.client.entity.render.AnomalocarisRenderer;
import lostworlds.client.entity.render.CarnotaurusRenderer;
import lostworlds.client.entity.render.ChilesaurusRenderer;
import lostworlds.client.entity.render.ChilesaurusSkeletonRenderer;
import lostworlds.client.entity.render.CryolophosaurusRenderer;
import lostworlds.client.entity.render.DiictodonRenderer;
import lostworlds.client.entity.render.DilophosaurusRenderer;
import lostworlds.client.entity.render.DimetrodonRenderer;
import lostworlds.client.entity.render.EdaphosaurusRenderer;
import lostworlds.client.entity.render.EoraptorRenderer;
import lostworlds.client.entity.render.FossilPoacherRenderer;
import lostworlds.client.entity.render.FukuivenatorRenderer;
import lostworlds.client.entity.render.GiganotosaurusRenderer;
import lostworlds.client.entity.render.GorgonopsRenderer;
import lostworlds.client.entity.render.GreatAukRenderer;
import lostworlds.client.entity.render.KentrosaurusRenderer;
import lostworlds.client.entity.render.KentrosaurusSkeletonRenderer;
import lostworlds.client.entity.render.LiaoningosaurusRenderer;
import lostworlds.client.entity.render.ModBoatRenderer;
import lostworlds.client.entity.render.NautilusRenderer;
import lostworlds.client.entity.render.OphthalmosaurusRenderer;
import lostworlds.client.entity.render.OstromiaRenderer;
import lostworlds.client.entity.render.OuranosaurusRenderer;
import lostworlds.client.entity.render.PalaeoniscumRenderer;
import lostworlds.client.entity.render.ProcompsognathusRenderer;
import lostworlds.client.entity.render.ProtosuchusRenderer;
import lostworlds.client.entity.render.PsittacosaurusRenderer;
import lostworlds.client.entity.render.RhinesuchusRenderer;
import lostworlds.client.entity.render.SuchomimusRenderer;
import lostworlds.client.entity.render.TetraceratopsRenderer;
import lostworlds.client.entity.render.TyrannosaurusRenderer;
import lostworlds.client.entity.render.UtahraptorRenderer;
import lostworlds.client.entity.render.ZephyrosaurusRenderer;
import lostworlds.client.entity.render.block.DisplayCaseRenderer;
import lostworlds.client.entity.render.bone.CustomisableRenderer;
import lostworlds.client.screen.AnalyzerScreen;
import lostworlds.client.screen.ArchaeologyTableScreen;
import lostworlds.client.screen.CultivatorScreen;
import lostworlds.client.screen.DNAExtractorScreen;
import lostworlds.client.screen.DNAInjectorScreen;
import lostworlds.client.screen.DisplayCaseScreen;
import lostworlds.client.screen.FeedingTroughScreen;
import lostworlds.client.screen.FossilCleanerScreen;
import lostworlds.client.screen.FossilGrinderScreen;
import lostworlds.client.screen.PaleontologyTableScreen;
import lostworlds.client.screen.TimeMachineScreen;
import lostworlds.server.block.ColouredGlassBlock;
import lostworlds.server.block.ColouredGlassPaneBlock;
import lostworlds.server.block.LostWorldsBlocks;
import lostworlds.server.block.entity.LostWorldsBlockEntities;
import lostworlds.server.container.LostWorldsContainers;
import lostworlds.server.entity.LostWorldsEntities;
import lostworlds.server.entity.utils.enums.DinoTypes;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.item.BlockItem;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.FoliageColors;
import net.minecraft.world.IBlockDisplayReader;
import net.minecraft.world.biome.BiomeColors;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class ClientSetup {
	public static void blockColourSetup() {
		BlockColors blockcolours = Minecraft.getInstance().getBlockColors();
		ItemColors itemcolours = Minecraft.getInstance().getItemColors();

		blockcolours.register((state, reader, pos, color) -> {
			return reader != null && pos != null ? BiomeColors.getAverageFoliageColor(reader, pos) : FoliageColors.getDefaultColor();
		}, LostWorldsBlocks.ARAUCARIA_LEAVES, LostWorldsBlocks.CALAMITES_LEAVES, LostWorldsBlocks.CONIFER_LEAVES, LostWorldsBlocks.CYPRESS_LEAVES, LostWorldsBlocks.GINKGO_LEAVES, LostWorldsBlocks.SEQUOIA_LEAVES, LostWorldsBlocks.ALETHOPTERIS, LostWorldsBlocks.BRAZILEA, LostWorldsBlocks.CEPHALOTAXUS, LostWorldsBlocks.OSMUNDA, LostWorldsBlocks.WILLIAMSONIA, LostWorldsBlocks.ZAMITES, LostWorldsBlocks.CYCAD, LostWorldsBlocks.DICKSONIA);
		itemcolours.register((stack, colour) -> {
			BlockState blockstate = ((BlockItem) stack.getItem()).getBlock().defaultBlockState();
			return blockcolours.getColor(blockstate, (IBlockDisplayReader) null, (BlockPos) null, colour);
		}, LostWorldsBlocks.ARAUCARIA_LEAVES, LostWorldsBlocks.CALAMITES_LEAVES, LostWorldsBlocks.CONIFER_LEAVES, LostWorldsBlocks.CYPRESS_LEAVES, LostWorldsBlocks.GINKGO_LEAVES, LostWorldsBlocks.SEQUOIA_LEAVES, LostWorldsBlocks.ALETHOPTERIS, LostWorldsBlocks.BRAZILEA, LostWorldsBlocks.CEPHALOTAXUS, LostWorldsBlocks.OSMUNDA, LostWorldsBlocks.CYCAD, LostWorldsBlocks.DICKSONIA);

		if (LostWorldsConfig.CLIENT_CONFIG.eggsSetColour.get()) {
			for (DinoTypes types : DinoTypes.eggLaying()) {
				blockcolours.register((state, reader, pos, intager) -> {
					return types.getSetEggColour();
				}, types.getEgg());
				itemcolours.register((stack, colour) -> {
					BlockState blockstate = ((BlockItem) stack.getItem()).getBlock().defaultBlockState();
					return blockcolours.getColor(blockstate, (IBlockDisplayReader) null, (BlockPos) null, colour);
				}, types.getEgg());
			}
		} else {
			for (DinoTypes types : DinoTypes.eggLaying()) {
				blockcolours.register((state, reader, pos, color) -> {
					return reader != null && pos != null ? BiomeColors.getAverageFoliageColor(reader, pos) : FoliageColors.getDefaultColor();
				}, types.getEgg());
				itemcolours.register((stack, colour) -> {
					BlockState blockstate = ((BlockItem) stack.getItem()).getBlock().defaultBlockState();
					return blockcolours.getColor(blockstate, (IBlockDisplayReader) null, (BlockPos) null, colour);
				}, types.getEgg());
			}
		}

		blockcolours.register((state, reader, pos, color) -> {
			return reader != null && pos != null ? BiomeColors.getAverageWaterColor(reader, pos) : -1;
		}, LostWorldsBlocks.POTTED_ARCHAEFRUTUS);

		for (DinoTypes types : DinoTypes.hasSpawn()) {
			itemcolours.register((stack, colour) -> {
				return types.getColour(colour, 0x000000, types.getSetEggColour());
			}, types.getSpawn());
		}
	}

	public static void renderSetup() {
		RenderTypeLookup.setRenderLayer(LostWorldsBlocks.ARCHAEFRUTUS, RenderType.cutout());
		RenderTypeLookup.setRenderLayer(LostWorldsBlocks.POTTED_ARCHAEFRUTUS, RenderType.cutout());
		RenderTypeLookup.setRenderLayer(LostWorldsBlocks.POTTED_ARCHAEFRUTUS, RenderType.translucent());
		RenderTypeLookup.setRenderLayer(LostWorldsBlocks.ALETHOPTERIS, RenderType.cutout());
		RenderTypeLookup.setRenderLayer(LostWorldsBlocks.CALAMITES_SUCKOWII, RenderType.cutout());
		RenderTypeLookup.setRenderLayer(LostWorldsBlocks.CALAMITES_SUCKOWII_SAPLING, RenderType.cutout());
		RenderTypeLookup.setRenderLayer(LostWorldsBlocks.CEPHALOTAXUS, RenderType.cutout());
		RenderTypeLookup.setRenderLayer(LostWorldsBlocks.CYCAD, RenderType.cutout());
		RenderTypeLookup.setRenderLayer(LostWorldsBlocks.DILLHOFFIA, RenderType.cutout());
		RenderTypeLookup.setRenderLayer(LostWorldsBlocks.POTTED_DILLHOFFIA, RenderType.cutout());
		RenderTypeLookup.setRenderLayer(LostWorldsBlocks.DICKSONIA, RenderType.cutout());
		RenderTypeLookup.setRenderLayer(LostWorldsBlocks.DUISBERGIA, RenderType.cutout());
		RenderTypeLookup.setRenderLayer(LostWorldsBlocks.EUDICOTS, RenderType.cutout());
		RenderTypeLookup.setRenderLayer(LostWorldsBlocks.POTTED_EUDICOTS, RenderType.cutout());
		RenderTypeLookup.setRenderLayer(LostWorldsBlocks.GROUND_FERNS, RenderType.cutout());
		RenderTypeLookup.setRenderLayer(LostWorldsBlocks.MAGNOLIA, RenderType.cutout());
		RenderTypeLookup.setRenderLayer(LostWorldsBlocks.POTTED_MAGNOLIA, RenderType.cutout());
		RenderTypeLookup.setRenderLayer(LostWorldsBlocks.OSMUNDA, RenderType.cutout());
		RenderTypeLookup.setRenderLayer(LostWorldsBlocks.PERMIAN_DESERT_FERNS, RenderType.cutout());
		RenderTypeLookup.setRenderLayer(LostWorldsBlocks.PERMIAN_DESERT_SHRUB, RenderType.cutout());
		RenderTypeLookup.setRenderLayer(LostWorldsBlocks.BRAZILEA, RenderType.cutout());
		RenderTypeLookup.setRenderLayer(LostWorldsBlocks.WILLIAMSONIA, RenderType.cutout());
		RenderTypeLookup.setRenderLayer(LostWorldsBlocks.ZAMITES, RenderType.cutout());

		RenderTypeLookup.setRenderLayer(LostWorldsBlocks.DISPLAY_CASE, RenderType.cutout());

		RenderTypeLookup.setRenderLayer(LostWorldsBlocks.FOSSIL_CLEANER, RenderType.translucent());
		RenderTypeLookup.setRenderLayer(LostWorldsBlocks.FOSSIL_GRINDER, RenderType.translucent());
		RenderTypeLookup.setRenderLayer(LostWorldsBlocks.DNA_EXTRACTOR, RenderType.translucent());
		RenderTypeLookup.setRenderLayer(LostWorldsBlocks.ANALYZER, RenderType.translucent());
		RenderTypeLookup.setRenderLayer(LostWorldsBlocks.DNA_INJECTOR, RenderType.translucent());
		RenderTypeLookup.setRenderLayer(LostWorldsBlocks.CULTIVATOR, RenderType.translucent());

		RenderTypeLookup.setRenderLayer(LostWorldsBlocks.PALEOBOTANY_TABLE, RenderType.cutout());

		RenderTypeLookup.setRenderLayer(LostWorldsBlocks.ARAUCARIA_LEAVES, RenderType.translucent());
		RenderTypeLookup.setRenderLayer(LostWorldsBlocks.ARAUCARIA_DOOR, RenderType.translucent());
		RenderTypeLookup.setRenderLayer(LostWorldsBlocks.ARAUCARIA_TRAPDOOR, RenderType.translucent());
		RenderTypeLookup.setRenderLayer(LostWorldsBlocks.ARAUCARIA_SAPLING, RenderType.cutout());
		RenderTypeLookup.setRenderLayer(LostWorldsBlocks.CALAMITES_LEAVES, RenderType.translucent());
		RenderTypeLookup.setRenderLayer(LostWorldsBlocks.CALAMITES_DOOR, RenderType.translucent());
		RenderTypeLookup.setRenderLayer(LostWorldsBlocks.CALAMITES_TRAPDOOR, RenderType.translucent());
		RenderTypeLookup.setRenderLayer(LostWorldsBlocks.CALAMITES_SAPLING, RenderType.cutout());
		RenderTypeLookup.setRenderLayer(LostWorldsBlocks.CONIFER_LEAVES, RenderType.translucent());
		RenderTypeLookup.setRenderLayer(LostWorldsBlocks.CONIFER_DOOR, RenderType.translucent());
		RenderTypeLookup.setRenderLayer(LostWorldsBlocks.CONIFER_TRAPDOOR, RenderType.translucent());
		RenderTypeLookup.setRenderLayer(LostWorldsBlocks.CONIFER_SAPLING, RenderType.cutout());
		RenderTypeLookup.setRenderLayer(LostWorldsBlocks.CYPRESS_LEAVES, RenderType.translucent());
		RenderTypeLookup.setRenderLayer(LostWorldsBlocks.CYPRESS_DOOR, RenderType.translucent());
		RenderTypeLookup.setRenderLayer(LostWorldsBlocks.CYPRESS_TRAPDOOR, RenderType.translucent());
		RenderTypeLookup.setRenderLayer(LostWorldsBlocks.CYPRESS_SAPLING, RenderType.cutout());
		RenderTypeLookup.setRenderLayer(LostWorldsBlocks.GINKGO_LEAVES, RenderType.translucent());
		RenderTypeLookup.setRenderLayer(LostWorldsBlocks.GINKGO_DOOR, RenderType.translucent());
		RenderTypeLookup.setRenderLayer(LostWorldsBlocks.GINKGO_TRAPDOOR, RenderType.translucent());
		RenderTypeLookup.setRenderLayer(LostWorldsBlocks.GINKGO_SAPLING, RenderType.cutout());
		RenderTypeLookup.setRenderLayer(LostWorldsBlocks.SCORCHED_DOOR, RenderType.translucent());
		RenderTypeLookup.setRenderLayer(LostWorldsBlocks.SCORCHED_TRAPDOOR, RenderType.translucent());
		RenderTypeLookup.setRenderLayer(LostWorldsBlocks.SEQUOIA_LEAVES, RenderType.translucent());
		RenderTypeLookup.setRenderLayer(LostWorldsBlocks.SEQUOIA_DOOR, RenderType.translucent());
		RenderTypeLookup.setRenderLayer(LostWorldsBlocks.SEQUOIA_TRAPDOOR, RenderType.translucent());
		RenderTypeLookup.setRenderLayer(LostWorldsBlocks.SEQUOIA_SAPLING, RenderType.cutout());

		for (Block block : ColouredGlassPaneBlock.entries) {
			RenderTypeLookup.setRenderLayer(block, RenderType.translucent());
		}

		for (Block block : ColouredGlassBlock.entries) {
			RenderTypeLookup.setRenderLayer(block, RenderType.translucent());
		}

		RenderTypeLookup.setRenderLayer(LostWorldsBlocks.OUTDOOR_TOILET_DOOR, RenderType.translucent());
		RenderTypeLookup.setRenderLayer(LostWorldsBlocks.GLASS_SHOP_DOOR, RenderType.translucent());
		RenderTypeLookup.setRenderLayer(LostWorldsBlocks.VISITOR_CENTER_DOOR, RenderType.translucent());
		RenderTypeLookup.setRenderLayer(LostWorldsBlocks.INNOVATION_CENTER_DOOR, RenderType.translucent());
		RenderTypeLookup.setRenderLayer(LostWorldsBlocks.SECURITY_DOOR, RenderType.translucent());
		RenderTypeLookup.setRenderLayer(LostWorldsBlocks.BACK_DOOR, RenderType.translucent());

	}

	public static void screenSetup() {
		ScreenManager.register(LostWorldsContainers.FOSSIL_CLEANER_CONTAINER, FossilCleanerScreen::new);
		ScreenManager.register(LostWorldsContainers.FOSSIL_GRINDER_CONTAINER, FossilGrinderScreen::new);
		ScreenManager.register(LostWorldsContainers.DNA_EXTRACTOR_CONTAINER, DNAExtractorScreen::new);
		ScreenManager.register(LostWorldsContainers.ANALYZER_CONTAINER, AnalyzerScreen::new);
		ScreenManager.register(LostWorldsContainers.DNA_INJECTOR_CONTAINER, DNAInjectorScreen::new);
		ScreenManager.register(LostWorldsContainers.CULTIVATOR_CONTAINER, CultivatorScreen::new);

		ScreenManager.register(LostWorldsContainers.TIME_MACHINE_CONTAINER, TimeMachineScreen::new);
		ScreenManager.register(LostWorldsContainers.FEEDING_TROUGH_CONTAINER, FeedingTroughScreen::new);
		ScreenManager.register(LostWorldsContainers.DISPLAY_CASE_CONTAINER, DisplayCaseScreen::new);
		ScreenManager.register(LostWorldsContainers.ARCHAEOLOGY_CONTAINER, ArchaeologyTableScreen::new);
		ScreenManager.register(LostWorldsContainers.PALEONTOLOGY_CONTAINER, PaleontologyTableScreen::new);
	}

	public static void entityRenderSetup() {
		RenderingRegistry.registerEntityRenderingHandler(LostWorldsEntities.FOSSIL_POACHER, manager -> new FossilPoacherRenderer(manager));
		RenderingRegistry.registerEntityRenderingHandler(LostWorldsEntities.MOD_BOAT, manager -> new ModBoatRenderer(manager));
		RenderingRegistry.registerEntityRenderingHandler(LostWorldsEntities.CHARGED_CRYSTAL_SCARAB_GEM_ITEM, manager -> new ItemRenderer(manager, Minecraft.getInstance().getItemRenderer()));
		RenderingRegistry.registerEntityRenderingHandler(LostWorldsEntities.CRYSTAL_SCARAB_GEM_ITEM, manager -> new ItemRenderer(manager, Minecraft.getInstance().getItemRenderer()));

		RenderingRegistry.registerEntityRenderingHandler(LostWorldsEntities.ALLOSAURUS, manager -> new AllosaurusRenderer(manager));
		RenderingRegistry.registerEntityRenderingHandler(LostWorldsEntities.ANOMALOCARIS, manager -> new AnomalocarisRenderer(manager));
		RenderingRegistry.registerEntityRenderingHandler(LostWorldsEntities.CARNOTAURUS, manager -> new CarnotaurusRenderer(manager));
		RenderingRegistry.registerEntityRenderingHandler(LostWorldsEntities.CHILESAURUS, manager -> new ChilesaurusRenderer(manager));
		RenderingRegistry.registerEntityRenderingHandler(LostWorldsEntities.CHILESAURUS_SKELETON, manager -> new ChilesaurusSkeletonRenderer(manager));
		RenderingRegistry.registerEntityRenderingHandler(LostWorldsEntities.CRYOLOPHOSAURUS, manager -> new CryolophosaurusRenderer(manager));
		RenderingRegistry.registerEntityRenderingHandler(LostWorldsEntities.DIICTODON, manager -> new DiictodonRenderer(manager));
		RenderingRegistry.registerEntityRenderingHandler(LostWorldsEntities.DILOPHOSAURUS, manager -> new DilophosaurusRenderer(manager));
		RenderingRegistry.registerEntityRenderingHandler(LostWorldsEntities.DIMETRODON, manager -> new DimetrodonRenderer(manager));
		RenderingRegistry.registerEntityRenderingHandler(LostWorldsEntities.EORAPTOR, manager -> new EoraptorRenderer(manager));
		RenderingRegistry.registerEntityRenderingHandler(LostWorldsEntities.EDAPHOSAURUS, manager -> new EdaphosaurusRenderer(manager));
		RenderingRegistry.registerEntityRenderingHandler(LostWorldsEntities.FUKUIVENATOR, manager -> new FukuivenatorRenderer(manager));
		RenderingRegistry.registerEntityRenderingHandler(LostWorldsEntities.GIGANOTOSAURUS, manager -> new GiganotosaurusRenderer(manager));
		RenderingRegistry.registerEntityRenderingHandler(LostWorldsEntities.GORGONOPS, manager -> new GorgonopsRenderer(manager));
		RenderingRegistry.registerEntityRenderingHandler(LostWorldsEntities.GREAT_AUK, manager -> new GreatAukRenderer(manager));
		RenderingRegistry.registerEntityRenderingHandler(LostWorldsEntities.KENTROSAURUS, manager -> new KentrosaurusRenderer(manager));
		RenderingRegistry.registerEntityRenderingHandler(LostWorldsEntities.KENTROSAURUS_SKELETON, manager -> new KentrosaurusSkeletonRenderer(manager));
		RenderingRegistry.registerEntityRenderingHandler(LostWorldsEntities.LIAONINGOSAURUS, manager -> new LiaoningosaurusRenderer(manager));
		RenderingRegistry.registerEntityRenderingHandler(LostWorldsEntities.NAUTILUS, manager -> new NautilusRenderer(manager));
		RenderingRegistry.registerEntityRenderingHandler(LostWorldsEntities.OPHTHALMOSAURUS, manager -> new OphthalmosaurusRenderer(manager));
		RenderingRegistry.registerEntityRenderingHandler(LostWorldsEntities.OSTROMIA, manager -> new OstromiaRenderer(manager));
		RenderingRegistry.registerEntityRenderingHandler(LostWorldsEntities.OURANOSAURUS, manager -> new OuranosaurusRenderer(manager));
		RenderingRegistry.registerEntityRenderingHandler(LostWorldsEntities.PALAEONISCUM, manager -> new PalaeoniscumRenderer(manager));
		RenderingRegistry.registerEntityRenderingHandler(LostWorldsEntities.PROCOMPSOGNATHUS, manager -> new ProcompsognathusRenderer(manager));
		RenderingRegistry.registerEntityRenderingHandler(LostWorldsEntities.PROTOSUCHUS, manager -> new ProtosuchusRenderer(manager));
		RenderingRegistry.registerEntityRenderingHandler(LostWorldsEntities.PSITTACOSAURUS, manager -> new PsittacosaurusRenderer(manager));
		RenderingRegistry.registerEntityRenderingHandler(LostWorldsEntities.RHINESUCHUS, manager -> new RhinesuchusRenderer(manager));
		RenderingRegistry.registerEntityRenderingHandler(LostWorldsEntities.SUCHOMIMUS, manager -> new SuchomimusRenderer(manager));
		RenderingRegistry.registerEntityRenderingHandler(LostWorldsEntities.UTAHRAPTOR, manager -> new UtahraptorRenderer(manager));
		RenderingRegistry.registerEntityRenderingHandler(LostWorldsEntities.TETRACERATOPS, manager -> new TetraceratopsRenderer(manager));
		RenderingRegistry.registerEntityRenderingHandler(LostWorldsEntities.TYRANNOSAURUS, manager -> new TyrannosaurusRenderer(manager));
		RenderingRegistry.registerEntityRenderingHandler(LostWorldsEntities.ZEPHYROSAURUS, manager -> new ZephyrosaurusRenderer(manager));

		for (DinoTypes dinos : DinoTypes.values()) {
			if (dinos != DinoTypes.NAUTILUS && dinos != DinoTypes.PALAEONISCUM && dinos != DinoTypes.ANOMALOCARIS) {
				RenderingRegistry.registerEntityRenderingHandler(dinos.getDirtyArmBones(), manager -> new CustomisableRenderer(manager, dinos.getId() + "_arm_bones", dinos.getId(), 0.25F));
				RenderingRegistry.registerEntityRenderingHandler(dinos.getDirtyLegBones(), manager -> new CustomisableRenderer(manager, dinos.getId() + "_leg_bones", dinos.getId(), 0.25F));
				RenderingRegistry.registerEntityRenderingHandler(dinos.getDirtyRibCage(), manager -> new CustomisableRenderer(manager, dinos.getId() + "_rib_cage", dinos.getId(), 0.25F));
				RenderingRegistry.registerEntityRenderingHandler(dinos.getDirtySkull(), manager -> new CustomisableRenderer(manager, dinos.getId() + "_skull", dinos.getId(), 0.25F));
				RenderingRegistry.registerEntityRenderingHandler(dinos.getDirtyTail(), manager -> new CustomisableRenderer(manager, dinos.getId() + "_tail", dinos.getId(), 0.25F));
				RenderingRegistry.registerEntityRenderingHandler(dinos.getArmBones(), manager -> new CustomisableRenderer(manager, dinos.getId() + "_arm_bones", dinos.getId(), 0.25F));
				RenderingRegistry.registerEntityRenderingHandler(dinos.getLegBones(), manager -> new CustomisableRenderer(manager, dinos.getId() + "_leg_bones", dinos.getId(), 0.25F));
				RenderingRegistry.registerEntityRenderingHandler(dinos.getRibCage(), manager -> new CustomisableRenderer(manager, dinos.getId() + "_rib_cage", dinos.getId(), 0.25F));
				RenderingRegistry.registerEntityRenderingHandler(dinos.getSkull(), manager -> new CustomisableRenderer(manager, dinos.getId() + "_skull", dinos.getId(), 0.25F));
				RenderingRegistry.registerEntityRenderingHandler(dinos.getTail(), manager -> new CustomisableRenderer(manager, dinos.getId() + "_tail", dinos.getId(), 0.25F));
			}

			if (dinos == DinoTypes.ANOMALOCARIS) {
				RenderingRegistry.registerEntityRenderingHandler(dinos.getDirtyExoskeleton(), manager -> new CustomisableRenderer(manager, dinos.getId() + "_exoskeleton", dinos.getId(), 0.25F));
				RenderingRegistry.registerEntityRenderingHandler(dinos.getExoskeleton(), manager -> new CustomisableRenderer(manager, dinos.getId() + "_exoskeleton", dinos.getId(), 0.25F));
			}

			if (dinos == DinoTypes.PALAEONISCUM) {
				RenderingRegistry.registerEntityRenderingHandler(dinos.getDirtyBody(), manager -> new CustomisableRenderer(manager, dinos.getId() + "_body", dinos.getId(), 0.25F));
				RenderingRegistry.registerEntityRenderingHandler(dinos.getBody(), manager -> new CustomisableRenderer(manager, dinos.getId() + "_body", dinos.getId(), 0.25F));
			}
		}
	}

	public static void setupOther() {
		ClientRegistry.bindTileEntityRenderer(LostWorldsBlockEntities.DISPLAY_CASE_TILE_ENTITY, DisplayCaseRenderer::new);
	}
}
