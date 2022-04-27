package lostworlds.client.events;

import lostworlds.client.entity.render.ChilesaurusRenderer;
import lostworlds.client.entity.render.ChilesaurusSkeletonRenderer;
import lostworlds.client.entity.render.CryolophosaurusRenderer;
import lostworlds.client.entity.render.DiictodonRenderer;
import lostworlds.client.entity.render.DilophosaurusRenderer;
import lostworlds.client.entity.render.DimetrodonRenderer;
import lostworlds.client.entity.render.EdaphosaurusRenderer;
import lostworlds.client.entity.render.EoraptorRenderer;
import lostworlds.client.entity.render.FukuivenatorRenderer;
import lostworlds.client.entity.render.GiganotosaurusRenderer;
import lostworlds.client.entity.render.GorgonopsRenderer;
import lostworlds.client.entity.render.GreatAukRenderer;
import lostworlds.client.entity.render.KentrosaurusRenderer;
import lostworlds.client.entity.render.KentrosaurusSkeletonRenderer;
import lostworlds.client.entity.render.LiaoningosaurusRenderer;
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
import lostworlds.client.entity.render.bone.CustomisableRenderer;
import lostworlds.server.entity.LostWorldsEntities;
import lostworlds.server.entity.utils.enums.DinoTypes;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class ClientSetup {
	public static void entityRenderSetup() {
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
}
