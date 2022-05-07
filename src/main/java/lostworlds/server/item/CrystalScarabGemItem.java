package lostworlds.server.item;

import lostworlds.server.entity.item.ChargedCrystalScarabGemItemEntity;
import lostworlds.server.entity.item.CrystalScarabGemItemEntity;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class CrystalScarabGemItem extends Item {
	public CrystalScarabGemItem(Properties properties) {
		super(properties);
	}

	public static class CECrystalScarabGemItem extends CrystalScarabGemItem {
		public CECrystalScarabGemItem(Properties properties) {
			super(properties);
		}

		@Override
		public int getEntityLifespan(ItemStack itemStack, World world) {
			return Integer.MAX_VALUE;
		}

		@Override
		public boolean hasCustomEntity(ItemStack stack) {
			return true;
		}

		@Override
		public Entity createEntity(World world, Entity entity, ItemStack stack) {
			final CrystalScarabGemItemEntity customentity = new CrystalScarabGemItemEntity(world, entity.getX(), entity.getY(), entity.getZ(), stack);
			customentity.setDeltaMovement(entity.getDeltaMovement());
			customentity.setPickUpDelay(40);

			return customentity;
		}
	}

	public static class CEChargedCrystalScarabGemItem extends CrystalScarabGemItem {
		public CEChargedCrystalScarabGemItem(Properties properties) {
			super(properties);
		}

		@Override
		public boolean isFoil(ItemStack stack) {
			return true;
		}

		@Override
		public int getEntityLifespan(ItemStack itemStack, World world) {
			return Integer.MAX_VALUE;
		}

		@Override
		public boolean hasCustomEntity(ItemStack stack) {
			return true;
		}

		@Override
		public Entity createEntity(World world, Entity entity, ItemStack stack) {
			final ChargedCrystalScarabGemItemEntity customentity = new ChargedCrystalScarabGemItemEntity(world, entity.getX(), entity.getY(), entity.getZ(), stack);
			customentity.setDeltaMovement(entity.getDeltaMovement());
			customentity.setPickUpDelay(40);

			return customentity;
		}
	}
}
