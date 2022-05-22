package lostworlds.server.util;

import java.util.function.Function;

import net.minecraft.world.entity.Entity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.server.level.ServerLevel;
import net.minecraftforge.common.util.ITeleporter;

public class ModTeleporter implements ITeleporter {
	@Override
	public Entity placeEntity(Entity entity, ServerLevel currentWorld, ServerLevel destWorld, float yaw, Function<Boolean, Entity> repositionEntity) {
		BlockPos pos = entity.blockPosition();

		Entity repositionedEntity = repositionEntity.apply(false);

		repositionedEntity.setPortalCooldown();

		int x = pos.getX();
		int z = pos.getZ();
		int surfaceY = destWorld.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, x, z);
		int y = surfaceY < 1 ? 70 : surfaceY;

		repositionedEntity.moveTo(x, y, z);

		return repositionedEntity;
	}
}
