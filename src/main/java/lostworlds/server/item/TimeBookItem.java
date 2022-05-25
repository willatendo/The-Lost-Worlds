package lostworlds.server.item;

import java.util.Random;
import java.util.function.Predicate;

import lostworlds.server.LostWorldsTags;
import lostworlds.server.LostWorldsUtils;
import lostworlds.server.entity.utils.enums.TimeEras;
import lostworlds.server.util.ModTeleporter;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

public class TimeBookItem extends ProjectileWeaponItem {
	public static final Predicate<ItemStack> FUEL = (stack) -> {
		return stack.is(LostWorldsTags.ModItemTags.TIME_BOOK_FUEL.tag);
	};
	private final TimeEras era;
	private final ResourceKey<Level> levelToTransportTo;

	public TimeBookItem(Properties properites, TimeEras eras/* , ResourceKey<Level> level */) {
		super(properites);
		this.era = eras;
		this.levelToTransportTo = Level.OVERWORLD;
	}

	public void releaseUsing(ItemStack stack, Level level, LivingEntity entity, int time) {
		if (entity instanceof Player) {
			Player playerentity = (Player) entity;
			boolean flag = playerentity.isCreative();
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
					if (!level.isClientSide) {
						if (!entity.isPassenger() && !entity.isVehicle() && entity.canChangeDimensions()) {
							if (entity.level instanceof ServerLevel) {
								if (entity.level.dimension() == Level.NETHER || entity.level.dimension() == Level.END) {
									entity.sendMessage(LostWorldsUtils.tTC("timeBook", "doesnt_work"), entity.getUUID());
									return;
								}
								ServerLevel serverworld = (ServerLevel) entity.level;
								MinecraftServer minecraftserver = serverworld.getServer();
								ResourceKey<Level> registrykey = entity.level.dimension() == this.levelToTransportTo ? Level.OVERWORLD : this.levelToTransportTo;
								ServerLevel serverworld1 = minecraftserver.getLevel(registrykey);
								if (serverworld1 != null && !entity.isPassenger()) {
									playerentity.changeDimension(serverworld1, new ModTeleporter());
									if (!registrykey.equals(Level.OVERWORLD)) {
										entity.sendMessage(LostWorldsUtils.tTCA("timeBook", "transport_to_" + era.toString().toLowerCase(), playerentity.getName().getString()), entity.getUUID());
									} else {
										entity.sendMessage(LostWorldsUtils.tTCA("timeBook", "transport_to_overworld", playerentity.getName().getString()), entity.getUUID());
									}
								}

								if (!flag) {
									itemstack.shrink(1);
									if (itemstack.isEmpty()) {
										playerentity.getInventory().removeItem(itemstack);
									}
								}

								level.playSound((Player) null, playerentity.getX(), playerentity.getY(), playerentity.getZ(), SoundEvents.PORTAL_TRAVEL, SoundSource.PLAYERS, 1.0F, 1.0F / (new Random().nextFloat() * 0.4F + 1.2F) + f * 0.5F);
							}
						}
					}

					playerentity.awardStat(Stats.ITEM_USED.get(this));
				}
			}
		}
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
		ItemStack itemstack = player.getItemInHand(hand);
		boolean flag = !player.getProjectile(itemstack).isEmpty();

		if (!player.isCreative() && !flag) {
			return InteractionResultHolder.fail(itemstack);
		} else {
			player.startUsingItem(hand);
			return InteractionResultHolder.consume(itemstack);
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

	@Override
	public UseAnim getUseAnimation(ItemStack stack) {
		return UseAnim.BOW;
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
