package lostworlds.server.item;

import java.util.function.Predicate;

import lostworlds.server.LostWorldsTags;
import lostworlds.server.LostWorldsUtils;
import lostworlds.server.entity.utils.enums.TimeEras;
import lostworlds.server.util.ModTeleporter;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.ShootableItem;
import net.minecraft.item.UseAction;
import net.minecraft.server.MinecraftServer;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class TimeBookItem extends ShootableItem {
	public static final Predicate<ItemStack> FUEL = (stack) -> {
		return stack.getItem().is(LostWorldsTags.ModItemTags.TIME_BOOK_FUEL);
	};
	private final TimeEras era;
	private final RegistryKey<World> worldToTransportTo;

	public TimeBookItem(Properties properites, TimeEras eras, RegistryKey<World> world) {
		super(properites);
		this.era = eras;
		this.worldToTransportTo = world;
	}

	public void releaseUsing(ItemStack stack, World world, LivingEntity entity, int time) {
		if (entity instanceof PlayerEntity) {
			PlayerEntity playerentity = (PlayerEntity) entity;
			boolean flag = playerentity.abilities.instabuild;
			ItemStack itemstack = playerentity.getProjectile(stack);

			int i = this.getUseDuration(stack) - time;
			if (i < 0)
				return;

			if (!itemstack.isEmpty() || flag) {
				if (itemstack.isEmpty()) {
					itemstack = new ItemStack(Items.REDSTONE);
				}

				float f = getPowerForTime(i);
				if (!((double) f < 0.1D)) {
					if (!world.isClientSide) {
						if (!entity.isPassenger() && !entity.isVehicle() && entity.canChangeDimensions()) {
							if (entity.level instanceof ServerWorld) {
								if (entity.level.dimension() == World.NETHER || entity.level.dimension() == World.END) {
									entity.sendMessage(LostWorldsUtils.tTC("timeBook", "doesnt_work"), entity.getUUID());
									return;
								}
								ServerWorld serverworld = (ServerWorld) entity.level;
								MinecraftServer minecraftserver = serverworld.getServer();
								RegistryKey<World> registrykey = entity.level.dimension() == this.worldToTransportTo ? World.OVERWORLD : this.worldToTransportTo;
								ServerWorld serverworld1 = minecraftserver.getLevel(registrykey);
								if (serverworld1 != null && !entity.isPassenger()) {
									playerentity.changeDimension(serverworld1, new ModTeleporter());
									if (!registrykey.equals(World.OVERWORLD)) {
										entity.sendMessage(LostWorldsUtils.tTCA("timeBook", "transport_to_" + era.toString().toLowerCase(), playerentity.getName().getString()), entity.getUUID());
									} else {
										entity.sendMessage(LostWorldsUtils.tTCA("timeBook", "transport_to_overworld", playerentity.getName().getString()), entity.getUUID());
									}
								}

								if (!flag) {
									itemstack.shrink(1);
									if (itemstack.isEmpty()) {
										playerentity.inventory.removeItem(itemstack);
									}
								}

								world.playSound((PlayerEntity) null, playerentity.getX(), playerentity.getY(), playerentity.getZ(), SoundEvents.PORTAL_TRAVEL, SoundCategory.PLAYERS, 1.0F, 1.0F / (random.nextFloat() * 0.4F + 1.2F) + f * 0.5F);
							}
						}
					}

					playerentity.awardStat(Stats.ITEM_USED.get(this));
				}
			}
		}
	}

	@Override
	public ActionResult<ItemStack> use(World world, PlayerEntity entity, Hand hand) {
		ItemStack itemstack = entity.getItemInHand(hand);
		boolean flag = !entity.getProjectile(itemstack).isEmpty();

		if (!entity.abilities.instabuild && !flag) {
			return ActionResult.fail(itemstack);
		} else {
			entity.startUsingItem(hand);
			return ActionResult.consume(itemstack);
		}
	}

	public static float getPowerForTime(int time) {
		float f = (float) time / 20.0F;
		f = (f * f + f * 2.0F) / 3.0F;
		if (f > 1.0F) {
			f = 1.0F;
		}

		return f;
	}

	@Override
	public int getUseDuration(ItemStack stack) {
		return 72000;
	}

	public UseAction getUseAnimation(ItemStack p_77661_1_) {
		return UseAction.BOW;
	}

	@Override
	public boolean isFoil(ItemStack stack) {
		return true;
	}

	@Override
	public Predicate<ItemStack> getAllSupportedProjectiles() {
		return FUEL;
	}

	@Override
	public int getDefaultProjectileRange() {
		return 15;
	}
}
