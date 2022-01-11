package lostworlds.content.client.setup;

import lostworlds.content.client.entity.render.AllosaurusRenderer;
import lostworlds.content.client.entity.render.AnomalocarisRenderer;
import lostworlds.content.client.entity.render.CarnotaurusRenderer;
import lostworlds.content.client.entity.render.ChilesaurusRenderer;
import lostworlds.content.client.entity.render.ChilesaurusSkeletonRenderer;
import lostworlds.content.client.entity.render.CryolophosaurusRenderer;
import lostworlds.content.client.entity.render.DiictodonRenderer;
import lostworlds.content.client.entity.render.DilophosaurusRenderer;
import lostworlds.content.client.entity.render.DimetrodonRenderer;
import lostworlds.content.client.entity.render.EdaphosaurusRenderer;
import lostworlds.content.client.entity.render.EoraptorRenderer;
import lostworlds.content.client.entity.render.FossilPoacherRenderer;
import lostworlds.content.client.entity.render.FukuivenatorRenderer;
import lostworlds.content.client.entity.render.GiganotosaurusRenderer;
import lostworlds.content.client.entity.render.GorgonopsRenderer;
import lostworlds.content.client.entity.render.GreatAukRenderer;
import lostworlds.content.client.entity.render.KentrosaurusRenderer;
import lostworlds.content.client.entity.render.KentrosaurusSkeletonRenderer;
import lostworlds.content.client.entity.render.LiaoningosaurusRenderer;
import lostworlds.content.client.entity.render.ModBoatRenderer;
import lostworlds.content.client.entity.render.NautilusRenderer;
import lostworlds.content.client.entity.render.OphthalmosaurusRenderer;
import lostworlds.content.client.entity.render.OstromiaRenderer;
import lostworlds.content.client.entity.render.OuranosaurusRenderer;
import lostworlds.content.client.entity.render.PalaeoniscumRenderer;
import lostworlds.content.client.entity.render.ProcompsognathusRenderer;
import lostworlds.content.client.entity.render.ProtosuchusRenderer;
import lostworlds.content.client.entity.render.PsittacosaurusRenderer;
import lostworlds.content.client.entity.render.RhinesuchusRenderer;
import lostworlds.content.client.entity.render.SuchomimusRenderer;
import lostworlds.content.client.entity.render.TetraceratopsRenderer;
import lostworlds.content.client.entity.render.TyrannosaurusRenderer;
import lostworlds.content.client.entity.render.UtahraptorRenderer;
import lostworlds.content.client.entity.render.ZephyrosaurusRenderer;
import lostworlds.content.client.entity.render.block.DisplayCaseRenderer;
import lostworlds.content.client.entity.render.bone.CustomisableRenderer;
import lostworlds.content.client.screen.AnalyzerScreen;
import lostworlds.content.client.screen.ArchaeologyTableScreen;
import lostworlds.content.client.screen.CultivatorScreen;
import lostworlds.content.client.screen.DNAExtractorScreen;
import lostworlds.content.client.screen.DNAInjectorScreen;
import lostworlds.content.client.screen.DisplayCaseScreen;
import lostworlds.content.client.screen.FeedingTroughScreen;
import lostworlds.content.client.screen.FossilCleanerScreen;
import lostworlds.content.client.screen.FossilGrinderScreen;
import lostworlds.content.client.screen.PaleontologyTableScreen;
import lostworlds.content.client.screen.TimeMachineScreen;
import lostworlds.content.config.LostWorldsConfig;
import lostworlds.content.server.init.BlockInit;
import lostworlds.content.server.init.ContainerInit;
import lostworlds.content.server.init.EntityInit;
import lostworlds.content.server.init.TileEntityInit;
import lostworlds.library.block.ColouredGlassBlock;
import lostworlds.library.block.ColouredGlassPaneBlock;
import lostworlds.library.entity.utils.enums.DinoTypes;
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
		}, BlockInit.ARAUCARIA_LEAVES, BlockInit.CALAMITES_LEAVES, BlockInit.CONIFER_LEAVES, BlockInit.CYPRESS_LEAVES, BlockInit.GINKGO_LEAVES, BlockInit.SEQUOIA_LEAVES, BlockInit.ALETHOPTERIS, BlockInit.BRAZILEA, BlockInit.CEPHALOTAXUS, BlockInit.OSMUNDA, BlockInit.WILLIAMSONIA, BlockInit.ZAMITES, BlockInit.CYCAD, BlockInit.DICKSONIA);
		itemcolours.register((stack, colour) -> {
			BlockState blockstate = ((BlockItem) stack.getItem()).getBlock().defaultBlockState();
			return blockcolours.getColor(blockstate, (IBlockDisplayReader) null, (BlockPos) null, colour);
		}, BlockInit.ARAUCARIA_LEAVES, BlockInit.CALAMITES_LEAVES, BlockInit.CONIFER_LEAVES, BlockInit.CYPRESS_LEAVES, BlockInit.GINKGO_LEAVES, BlockInit.SEQUOIA_LEAVES, BlockInit.ALETHOPTERIS, BlockInit.BRAZILEA, BlockInit.CEPHALOTAXUS, BlockInit.OSMUNDA, BlockInit.CYCAD, BlockInit.DICKSONIA);

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

		for (DinoTypes types : DinoTypes.hasSpawn()) {
			itemcolours.register((stack, colour) -> {
				return types.getColour(colour, 0x000000, types.getSetEggColour());
			}, types.getSpawn());
		}
	}

	public static void renderSetup() {
		RenderTypeLookup.setRenderLayer(BlockInit.ARCHAEFRUTUS, RenderType.cutout());
		RenderTypeLookup.setRenderLayer(BlockInit.POTTED_ARCHAEFRUTUS, RenderType.cutout());
		RenderTypeLookup.setRenderLayer(BlockInit.ALETHOPTERIS, RenderType.cutout());
		RenderTypeLookup.setRenderLayer(BlockInit.CALAMITES_SUCKOWII, RenderType.cutout());
		RenderTypeLookup.setRenderLayer(BlockInit.CALAMITES_SUCKOWII_SAPLING, RenderType.cutout());
		RenderTypeLookup.setRenderLayer(BlockInit.CEPHALOTAXUS, RenderType.cutout());
		RenderTypeLookup.setRenderLayer(BlockInit.CYCAD, RenderType.cutout());
		RenderTypeLookup.setRenderLayer(BlockInit.DILLHOFFIA, RenderType.cutout());
		RenderTypeLookup.setRenderLayer(BlockInit.POTTED_DILLHOFFIA, RenderType.cutout());
		RenderTypeLookup.setRenderLayer(BlockInit.DICKSONIA, RenderType.cutout());
		RenderTypeLookup.setRenderLayer(BlockInit.DUISBERGIA, RenderType.cutout());
		RenderTypeLookup.setRenderLayer(BlockInit.EUDICOTS, RenderType.cutout());
		RenderTypeLookup.setRenderLayer(BlockInit.POTTED_EUDICOTS, RenderType.cutout());
		RenderTypeLookup.setRenderLayer(BlockInit.GROUND_FERNS, RenderType.cutout());
		RenderTypeLookup.setRenderLayer(BlockInit.MAGNOLIA, RenderType.cutout());
		RenderTypeLookup.setRenderLayer(BlockInit.POTTED_MAGNOLIA, RenderType.cutout());
		RenderTypeLookup.setRenderLayer(BlockInit.OSMUNDA, RenderType.cutout());
		RenderTypeLookup.setRenderLayer(BlockInit.PERMIAN_DESERT_FERNS, RenderType.cutout());
		RenderTypeLookup.setRenderLayer(BlockInit.PERMIAN_DESERT_SHRUB, RenderType.cutout());
		RenderTypeLookup.setRenderLayer(BlockInit.BRAZILEA, RenderType.cutout());
		RenderTypeLookup.setRenderLayer(BlockInit.WILLIAMSONIA, RenderType.cutout());
		RenderTypeLookup.setRenderLayer(BlockInit.ZAMITES, RenderType.cutout());

		RenderTypeLookup.setRenderLayer(BlockInit.FOG, RenderType.translucent());

		RenderTypeLookup.setRenderLayer(BlockInit.DISPLAY_CASE, RenderType.cutout());

		RenderTypeLookup.setRenderLayer(BlockInit.FOSSIL_CLEANER, RenderType.translucent());
		RenderTypeLookup.setRenderLayer(BlockInit.FOSSIL_GRINDER, RenderType.translucent());
		RenderTypeLookup.setRenderLayer(BlockInit.DNA_EXTRACTOR, RenderType.translucent());
		RenderTypeLookup.setRenderLayer(BlockInit.ANALYZER, RenderType.translucent());
		RenderTypeLookup.setRenderLayer(BlockInit.DNA_INJECTOR, RenderType.translucent());
		RenderTypeLookup.setRenderLayer(BlockInit.CULTIVATOR, RenderType.translucent());

		RenderTypeLookup.setRenderLayer(BlockInit.PALEOBOTANY_TABLE, RenderType.cutout());

		RenderTypeLookup.setRenderLayer(BlockInit.ARAUCARIA_LEAVES, RenderType.translucent());
		RenderTypeLookup.setRenderLayer(BlockInit.ARAUCARIA_DOOR, RenderType.translucent());
		RenderTypeLookup.setRenderLayer(BlockInit.ARAUCARIA_TRAPDOOR, RenderType.translucent());
		RenderTypeLookup.setRenderLayer(BlockInit.ARAUCARIA_SAPLING, RenderType.cutout());
		RenderTypeLookup.setRenderLayer(BlockInit.CALAMITES_LEAVES, RenderType.translucent());
		RenderTypeLookup.setRenderLayer(BlockInit.CALAMITES_DOOR, RenderType.translucent());
		RenderTypeLookup.setRenderLayer(BlockInit.CALAMITES_TRAPDOOR, RenderType.translucent());
		RenderTypeLookup.setRenderLayer(BlockInit.CALAMITES_SAPLING, RenderType.cutout());
		RenderTypeLookup.setRenderLayer(BlockInit.CONIFER_LEAVES, RenderType.translucent());
		RenderTypeLookup.setRenderLayer(BlockInit.CONIFER_DOOR, RenderType.translucent());
		RenderTypeLookup.setRenderLayer(BlockInit.CONIFER_TRAPDOOR, RenderType.translucent());
		RenderTypeLookup.setRenderLayer(BlockInit.CONIFER_SAPLING, RenderType.cutout());
		RenderTypeLookup.setRenderLayer(BlockInit.CYPRESS_LEAVES, RenderType.translucent());
		RenderTypeLookup.setRenderLayer(BlockInit.CYPRESS_DOOR, RenderType.translucent());
		RenderTypeLookup.setRenderLayer(BlockInit.CYPRESS_TRAPDOOR, RenderType.translucent());
		RenderTypeLookup.setRenderLayer(BlockInit.CYPRESS_SAPLING, RenderType.cutout());
		RenderTypeLookup.setRenderLayer(BlockInit.GINKGO_LEAVES, RenderType.translucent());
		RenderTypeLookup.setRenderLayer(BlockInit.GINKGO_DOOR, RenderType.translucent());
		RenderTypeLookup.setRenderLayer(BlockInit.GINKGO_TRAPDOOR, RenderType.translucent());
		RenderTypeLookup.setRenderLayer(BlockInit.GINKGO_SAPLING, RenderType.cutout());
		RenderTypeLookup.setRenderLayer(BlockInit.SCORCHED_DOOR, RenderType.translucent());
		RenderTypeLookup.setRenderLayer(BlockInit.SCORCHED_TRAPDOOR, RenderType.translucent());
		RenderTypeLookup.setRenderLayer(BlockInit.SEQUOIA_LEAVES, RenderType.translucent());
		RenderTypeLookup.setRenderLayer(BlockInit.SEQUOIA_DOOR, RenderType.translucent());
		RenderTypeLookup.setRenderLayer(BlockInit.SEQUOIA_TRAPDOOR, RenderType.translucent());
		RenderTypeLookup.setRenderLayer(BlockInit.SEQUOIA_SAPLING, RenderType.cutout());

		for (Block block : ColouredGlassPaneBlock.entries) {
			RenderTypeLookup.setRenderLayer(block, RenderType.translucent());
		}

		for (Block block : ColouredGlassBlock.entries) {
			RenderTypeLookup.setRenderLayer(block, RenderType.translucent());
		}

		RenderTypeLookup.setRenderLayer(BlockInit.OUTDOOR_TOILET_DOOR, RenderType.translucent());
		RenderTypeLookup.setRenderLayer(BlockInit.GLASS_SHOP_DOOR, RenderType.translucent());
		RenderTypeLookup.setRenderLayer(BlockInit.VISITOR_CENTER_DOOR, RenderType.translucent());
		RenderTypeLookup.setRenderLayer(BlockInit.INNOVATION_CENTER_DOOR, RenderType.translucent());
		RenderTypeLookup.setRenderLayer(BlockInit.SECURITY_DOOR, RenderType.translucent());
		RenderTypeLookup.setRenderLayer(BlockInit.BACK_DOOR, RenderType.translucent());

	}

	public static void screenSetup() {
		ScreenManager.register(ContainerInit.FOSSIL_CLEANER_CONTAINER, FossilCleanerScreen::new);
		ScreenManager.register(ContainerInit.FOSSIL_GRINDER_CONTAINER, FossilGrinderScreen::new);
		ScreenManager.register(ContainerInit.DNA_EXTRACTOR_CONTAINER, DNAExtractorScreen::new);
		ScreenManager.register(ContainerInit.ANALYZER_CONTAINER, AnalyzerScreen::new);
		ScreenManager.register(ContainerInit.DNA_INJECTOR_CONTAINER, DNAInjectorScreen::new);
		ScreenManager.register(ContainerInit.CULTIVATOR_CONTAINER, CultivatorScreen::new);

		ScreenManager.register(ContainerInit.TIME_MACHINE_CONTAINER, TimeMachineScreen::new);
		ScreenManager.register(ContainerInit.FEEDING_TROUGH_CONTAINER, FeedingTroughScreen::new);
		ScreenManager.register(ContainerInit.DISPLAY_CASE_CONTAINER, DisplayCaseScreen::new);
		ScreenManager.register(ContainerInit.ARCHAEOLOGY_CONTAINER, ArchaeologyTableScreen::new);
		ScreenManager.register(ContainerInit.PALEONTOLOGY_CONTAINER, PaleontologyTableScreen::new);
	}

	public static void entityRenderSetup() {
		RenderingRegistry.registerEntityRenderingHandler(EntityInit.FOSSIL_POACHER, manager -> new FossilPoacherRenderer(manager));
		RenderingRegistry.registerEntityRenderingHandler(EntityInit.MOD_BOAT, manager -> new ModBoatRenderer(manager));
		RenderingRegistry.registerEntityRenderingHandler(EntityInit.CHARGED_CRYSTAL_SCARAB_GEM_ITEM, manager -> new ItemRenderer(manager, Minecraft.getInstance().getItemRenderer()));
		RenderingRegistry.registerEntityRenderingHandler(EntityInit.CRYSTAL_SCARAB_GEM_ITEM, manager -> new ItemRenderer(manager, Minecraft.getInstance().getItemRenderer()));

		RenderingRegistry.registerEntityRenderingHandler(EntityInit.ALLOSAURUS, manager -> new AllosaurusRenderer(manager));
		RenderingRegistry.registerEntityRenderingHandler(EntityInit.ANOMALOCARIS, manager -> new AnomalocarisRenderer(manager));
		RenderingRegistry.registerEntityRenderingHandler(EntityInit.CARNOTAURUS, manager -> new CarnotaurusRenderer(manager));
		RenderingRegistry.registerEntityRenderingHandler(EntityInit.CHILESAURUS, manager -> new ChilesaurusRenderer(manager));
		RenderingRegistry.registerEntityRenderingHandler(EntityInit.CHILESAURUS_SKELETON, manager -> new ChilesaurusSkeletonRenderer(manager));
		RenderingRegistry.registerEntityRenderingHandler(EntityInit.CRYOLOPHOSAURUS, manager -> new CryolophosaurusRenderer(manager));
		RenderingRegistry.registerEntityRenderingHandler(EntityInit.DIICTODON, manager -> new DiictodonRenderer(manager));
		RenderingRegistry.registerEntityRenderingHandler(EntityInit.DILOPHOSAURUS, manager -> new DilophosaurusRenderer(manager));
		RenderingRegistry.registerEntityRenderingHandler(EntityInit.DIMETRODON, manager -> new DimetrodonRenderer(manager));
		RenderingRegistry.registerEntityRenderingHandler(EntityInit.EORAPTOR, manager -> new EoraptorRenderer(manager));
		RenderingRegistry.registerEntityRenderingHandler(EntityInit.EDAPHOSAURUS, manager -> new EdaphosaurusRenderer(manager));
		RenderingRegistry.registerEntityRenderingHandler(EntityInit.FUKUIVENATOR, manager -> new FukuivenatorRenderer(manager));
		RenderingRegistry.registerEntityRenderingHandler(EntityInit.GIGANOTOSAURUS, manager -> new GiganotosaurusRenderer(manager));
		RenderingRegistry.registerEntityRenderingHandler(EntityInit.GORGONOPS, manager -> new GorgonopsRenderer(manager));
		RenderingRegistry.registerEntityRenderingHandler(EntityInit.GREAT_AUK, manager -> new GreatAukRenderer(manager));
		RenderingRegistry.registerEntityRenderingHandler(EntityInit.KENTROSAURUS, manager -> new KentrosaurusRenderer(manager));
		RenderingRegistry.registerEntityRenderingHandler(EntityInit.KENTROSAURUS_SKELETON, manager -> new KentrosaurusSkeletonRenderer(manager));
		RenderingRegistry.registerEntityRenderingHandler(EntityInit.LIAONINGOSAURUS, manager -> new LiaoningosaurusRenderer(manager));
		RenderingRegistry.registerEntityRenderingHandler(EntityInit.NAUTILUS, manager -> new NautilusRenderer(manager));
		RenderingRegistry.registerEntityRenderingHandler(EntityInit.OPHTHALMOSAURUS, manager -> new OphthalmosaurusRenderer(manager));
		RenderingRegistry.registerEntityRenderingHandler(EntityInit.OSTROMIA, manager -> new OstromiaRenderer(manager));
		RenderingRegistry.registerEntityRenderingHandler(EntityInit.OURANOSAURUS, manager -> new OuranosaurusRenderer(manager));
		RenderingRegistry.registerEntityRenderingHandler(EntityInit.PALAEONISCUM, manager -> new PalaeoniscumRenderer(manager));
		RenderingRegistry.registerEntityRenderingHandler(EntityInit.PROCOMPSOGNATHUS, manager -> new ProcompsognathusRenderer(manager));
		RenderingRegistry.registerEntityRenderingHandler(EntityInit.PROTOSUCHUS, manager -> new ProtosuchusRenderer(manager));
		RenderingRegistry.registerEntityRenderingHandler(EntityInit.PSITTACOSAURUS, manager -> new PsittacosaurusRenderer(manager));
		RenderingRegistry.registerEntityRenderingHandler(EntityInit.RHINESUCHUS, manager -> new RhinesuchusRenderer(manager));
		RenderingRegistry.registerEntityRenderingHandler(EntityInit.SUCHOMIMUS, manager -> new SuchomimusRenderer(manager));
		RenderingRegistry.registerEntityRenderingHandler(EntityInit.UTAHRAPTOR, manager -> new UtahraptorRenderer(manager));
		RenderingRegistry.registerEntityRenderingHandler(EntityInit.TETRACERATOPS, manager -> new TetraceratopsRenderer(manager));
		RenderingRegistry.registerEntityRenderingHandler(EntityInit.TYRANNOSAURUS, manager -> new TyrannosaurusRenderer(manager));
		RenderingRegistry.registerEntityRenderingHandler(EntityInit.ZEPHYROSAURUS, manager -> new ZephyrosaurusRenderer(manager));

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
		ClientRegistry.bindTileEntityRenderer(TileEntityInit.DISPLAY_CASE_TILE_ENTITY, DisplayCaseRenderer::new);
	}
}
