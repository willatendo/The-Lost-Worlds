package lostworlds.client;

import lostworlds.client.entity.render.AllosaurusRenderer;
import lostworlds.client.entity.render.AnomalocarisRenderer;
import lostworlds.client.entity.render.CarnotaurusRenderer;
import lostworlds.client.entity.render.ChilesaurusRenderer;
import lostworlds.client.entity.render.CryolophosaurusRenderer;
import lostworlds.client.entity.render.CustomisableRenderer;
import lostworlds.client.entity.render.DiictodonRenderer;
import lostworlds.client.entity.render.DilophosaurusRenderer;
import lostworlds.client.entity.render.DimetrodonRenderer;
import lostworlds.client.entity.render.EdaphosaurusRenderer;
import lostworlds.client.entity.render.EoraptorRenderer;
import lostworlds.client.entity.render.EustreptospondylusRenderer;
import lostworlds.client.entity.render.FossilPoacherRenderer;
import lostworlds.client.entity.render.FukuivenatorRenderer;
import lostworlds.client.entity.render.GiganotosaurusRenderer;
import lostworlds.client.entity.render.GorgonopsRenderer;
import lostworlds.client.entity.render.GreatAukRenderer;
import lostworlds.client.entity.render.KentrosaurusRenderer;
import lostworlds.client.entity.render.LiaoningosaurusRenderer;
import lostworlds.client.entity.render.ModBoatRenderer;
import lostworlds.client.entity.render.NautilusRenderer;
import lostworlds.client.entity.render.OphthalmosaurusRenderer;
import lostworlds.client.entity.render.OstromiaRenderer;
import lostworlds.client.entity.render.OuranosaurusRenderer;
import lostworlds.client.entity.render.PalaeoniscumRenderer;
import lostworlds.client.entity.render.ParksosaurusRenderer;
import lostworlds.client.entity.render.ProcompsognathusRenderer;
import lostworlds.client.entity.render.ProtosuchusRenderer;
import lostworlds.client.entity.render.PsittacosaurusRenderer;
import lostworlds.client.entity.render.RhinesuchusRenderer;
import lostworlds.client.entity.render.SuchomimusRenderer;
import lostworlds.client.entity.render.TetraceratopsRenderer;
import lostworlds.client.entity.render.ThanosRenderer;
import lostworlds.client.entity.render.TyrannosaurusRenderer;
import lostworlds.client.entity.render.UtahraptorRenderer;
import lostworlds.client.entity.render.ZephyrosaurusRenderer;
import lostworlds.server.LostWorldsUtils;
import lostworlds.server.entity.LostWorldsEntities;
import lostworlds.server.entity.utils.enums.DinoTypes;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@EventBusSubscriber(bus = Bus.MOD, modid = LostWorldsUtils.ID, value = Dist.CLIENT)
public class LostWorldsEntityRenders {
	@SubscribeEvent
	public static void registerEntityRenderers(FMLClientSetupEvent event) {
		RenderingRegistry.registerEntityRenderingHandler(LostWorldsEntities.FOSSIL_POACHER.get(), FossilPoacherRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(LostWorldsEntities.MOD_BOAT.get(), ModBoatRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(LostWorldsEntities.CRYSTAL_SCARAB_GEM_ITEM.get(), manager -> new ItemRenderer(manager, Minecraft.getInstance().getItemRenderer()));
		RenderingRegistry.registerEntityRenderingHandler(LostWorldsEntities.CHARGED_CRYSTAL_SCARAB_GEM_ITEM.get(), manager -> new ItemRenderer(manager, Minecraft.getInstance().getItemRenderer()));
		RenderingRegistry.registerEntityRenderingHandler(LostWorldsEntities.ALLOSAURUS.get(), AllosaurusRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(LostWorldsEntities.ANOMALOCARIS.get(), AnomalocarisRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(LostWorldsEntities.CARNOTAURUS.get(), CarnotaurusRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(LostWorldsEntities.CHILESAURUS.get(), ChilesaurusRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(LostWorldsEntities.CRYOLOPHOSAURUS.get(), CryolophosaurusRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(LostWorldsEntities.DIICTODON.get(), DiictodonRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(LostWorldsEntities.DILOPHOSAURUS.get(), DilophosaurusRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(LostWorldsEntities.DIMETRODON.get(), DimetrodonRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(LostWorldsEntities.EDAPHOSAURUS.get(), EdaphosaurusRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(LostWorldsEntities.EORAPTOR.get(), EoraptorRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(LostWorldsEntities.EUSTREPTOSPONDYLUS.get(), EustreptospondylusRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(LostWorldsEntities.FUKUIVENATOR.get(), FukuivenatorRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(LostWorldsEntities.GIGANOTOSAURUS.get(), GiganotosaurusRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(LostWorldsEntities.GORGONOPS.get(), GorgonopsRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(LostWorldsEntities.GREAT_AUK.get(), GreatAukRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(LostWorldsEntities.KENTROSAURUS.get(), KentrosaurusRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(LostWorldsEntities.LIAONINGOSAURUS.get(), LiaoningosaurusRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(LostWorldsEntities.NAUTILUS.get(), NautilusRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(LostWorldsEntities.OPHTHALMOSAURUS.get(), OphthalmosaurusRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(LostWorldsEntities.OSTROMIA.get(), OstromiaRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(LostWorldsEntities.OURANOSAURUS.get(), OuranosaurusRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(LostWorldsEntities.PARKSOSAURUS.get(), ParksosaurusRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(LostWorldsEntities.PALAEONISCUM.get(), PalaeoniscumRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(LostWorldsEntities.PROCOMPSOGNATHUS.get(), ProcompsognathusRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(LostWorldsEntities.PROTOSUCHUS.get(), ProtosuchusRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(LostWorldsEntities.PSITTACOSAURUS.get(), PsittacosaurusRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(LostWorldsEntities.RHINESUCHUS.get(), RhinesuchusRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(LostWorldsEntities.SUCHOMIMUS.get(), SuchomimusRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(LostWorldsEntities.UTAHRAPTOR.get(), UtahraptorRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(LostWorldsEntities.THANOS.get(), ThanosRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(LostWorldsEntities.TETRACERATOPS.get(), TetraceratopsRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(LostWorldsEntities.TYRANNOSAURUS.get(), TyrannosaurusRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(LostWorldsEntities.ZEPHYROSAURUS.get(), ZephyrosaurusRenderer::new);

		for (DinoTypes dinos : DinoTypes.values()) {
			if (dinos != DinoTypes.NAUTILUS && dinos != DinoTypes.PALAEONISCUM && dinos != DinoTypes.ANOMALOCARIS) {
				RenderingRegistry.registerEntityRenderingHandler(dinos.getDirtySkull().get(), manager -> new CustomisableRenderer(manager, dinos.getId() + "_skull", dinos.getId(), 0.5F));
				RenderingRegistry.registerEntityRenderingHandler(dinos.getDirtyArmBones().get(), manager -> new CustomisableRenderer(manager, dinos.getId() + "_arm_bones", dinos.getId(), 0.5F));
				RenderingRegistry.registerEntityRenderingHandler(dinos.getDirtyLegBones().get(), manager -> new CustomisableRenderer(manager, dinos.getId() + "_leg_bones", dinos.getId(), 0.5F));
				RenderingRegistry.registerEntityRenderingHandler(dinos.getDirtyRibCage().get(), manager -> new CustomisableRenderer(manager, dinos.getId() + "_rib_cage", dinos.getId(), 0.5F));
				RenderingRegistry.registerEntityRenderingHandler(dinos.getDirtyTail().get(), manager -> new CustomisableRenderer(manager, dinos.getId() + "_tail", dinos.getId(), 0.5F));
				RenderingRegistry.registerEntityRenderingHandler(dinos.getSkull().get(), manager -> new CustomisableRenderer(manager, dinos.getId() + "_skull", dinos.getId(), 0.5F));
				RenderingRegistry.registerEntityRenderingHandler(dinos.getArmBones().get(), manager -> new CustomisableRenderer(manager, dinos.getId() + "_arm_bones", dinos.getId(), 0.5F));
				RenderingRegistry.registerEntityRenderingHandler(dinos.getLegBones().get(), manager -> new CustomisableRenderer(manager, dinos.getId() + "_leg_bones", dinos.getId(), 0.5F));
				RenderingRegistry.registerEntityRenderingHandler(dinos.getRibCage().get(), manager -> new CustomisableRenderer(manager, dinos.getId() + "_rib_cage", dinos.getId(), 0.5F));
				RenderingRegistry.registerEntityRenderingHandler(dinos.getTail().get(), manager -> new CustomisableRenderer(manager, dinos.getId() + "_tail", dinos.getId(), 0.5F));
				RenderingRegistry.registerEntityRenderingHandler(dinos.getSkeleton().get(), manager -> new CustomisableRenderer(manager, dinos.getId(), dinos.getId(), 0.5F));
			}
		}
	}
}
