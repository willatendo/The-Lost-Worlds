package lostworlds.server.item;

import java.util.List;
import java.util.function.Predicate;

import lostworlds.server.entity.ModBoat;
import lostworlds.server.entity.utils.enums.ModBoatType;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class ModBoatItem extends Item {
	private static final Predicate<Entity> RIDERS = EntitySelector.NO_SPECTATORS.and(Entity::isPickable);
	private final ModBoatType type;

	public ModBoatItem(ModBoatType type, Properties properties) {
		super(properties);
		this.type = type;
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
		ItemStack itemstack = player.getItemInHand(hand);
		HitResult raytraceresult = getPlayerPOVHitResult(level, player, ClipContext.Fluid.ANY);
		if (raytraceresult.getType() == HitResult.Type.MISS) {
			return InteractionResultHolder.pass(itemstack);
		} else {
			Vec3 Vector3d = player.getViewVector(1.0F);
			List<Entity> list = level.getEntities(player, player.getBoundingBox().expandTowards(Vector3d.scale(5.0D)).inflate(1.0D), RIDERS);
			if (!list.isEmpty()) {
				Vec3 Vector3d1 = player.getEyePosition(1.0F);

				for (Entity entity : list) {
					AABB axisalignedbb = entity.getBoundingBox().inflate(entity.getPickRadius());
					if (axisalignedbb.contains(Vector3d1)) {
						return InteractionResultHolder.pass(itemstack);
					}
				}
			}

			if (raytraceresult.getType() == HitResult.Type.BLOCK) {
				ModBoat boat = new ModBoat(level, raytraceresult.getLocation().x, raytraceresult.getLocation().y, raytraceresult.getLocation().z);
				boat.setBYGBoatType(this.type);
				boat.yRot = player.yRot;
				if (!level.noCollision(boat, boat.getBoundingBox().inflate(-0.1D))) {
					return InteractionResultHolder.fail(itemstack);
				} else {
					if (!level.isClientSide) {
						level.addFreshEntity(boat);
						if (!player.isCreative()) {
							itemstack.shrink(1);
						}
					}

					player.awardStat(Stats.ITEM_USED.get(this));
					return InteractionResultHolder.success(itemstack);
				}
			} else {
				return InteractionResultHolder.pass(itemstack);
			}
		}
	}
}
