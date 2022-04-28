package lostworlds.client.events;

import lostworlds.client.entity.render.bone.CustomisableRenderer;
import lostworlds.server.entity.utils.enums.DinoTypes;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class ClientSetup {
	public static void entityRenderSetup() {
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
