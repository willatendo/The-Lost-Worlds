package lostworlds.server.item;

import lostworlds.server.LostWorldsRegistry;
import lostworlds.server.LostWorldsUtils;
import lostworlds.server.entity.item.ChargedCrystalScarabGemItemEntity;
import lostworlds.server.entity.item.CrystalScarabGemItemEntity;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.world.World;

public class CrystalScarabGemItem extends Item {
	private Variant variant;

	public CrystalScarabGemItem(Properties properties, Variant variant) {
		super(properties);
		this.variant = variant;
	}

	public CrystalScarabGemItem(Variant variant) {
		this(new Properties().tab(LostWorldsUtils.ITEMS).rarity(Rarity.RARE), variant);
	}

	@Override
	public boolean isFoil(ItemStack stack) {
		if (this.variant != Variant.CHARGED) {
			return false;
		} else {
			return true;
		}
	}

	public static void createAll() {
		for (Gems items : Gems.values()) {
			LostWorldsRegistry.register(items.toString().toLowerCase(), items.getItem());
		}
	}

	public enum Variant {
		BROKEN,
		UNCHARGED,
		CHARGED;
	}

	public enum Gems {
		CHARGED_CRYSTAL_SCARAB_GEM(new CEChargedCrystalScarabGemItem(Variant.CHARGED)),
		CRYSTAL_SCARAB_GEM(new CECrystalScarabGemItem(Variant.UNCHARGED)),
		CRYSTAL_SCARAB_ABDOMEN(new CrystalScarabGemItem(Variant.BROKEN)),
		CRYSTAL_SCARAB_BOTTOM_LEFT_LEG(new CrystalScarabGemItem(Variant.BROKEN)),
		CRYSTAL_SCARAB_BOTTOM_RIGHT_LEG(new CrystalScarabGemItem(Variant.BROKEN)),
		CRYSTAL_SCARAB_THORAX(new CrystalScarabGemItem(Variant.BROKEN)),
		CRYSTAL_SCARAB_TOP_LEFT_LEG(new CrystalScarabGemItem(Variant.BROKEN)),
		CRYSTAL_SCARAB_TOP_RIGHT_LEG(new CrystalScarabGemItem(Variant.BROKEN));

		private final Item item;

		private Gems(Item item) {
			this.item = item;
		}

		public Item getItem() {
			return this.item;
		}
	}

	static class CECrystalScarabGemItem extends CrystalScarabGemItem {
		public CECrystalScarabGemItem(Variant variant) {
			super(new Properties().tab(LostWorldsUtils.ITEMS).fireResistant().rarity(Rarity.RARE), variant);
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

	static class CEChargedCrystalScarabGemItem extends CrystalScarabGemItem {
		public CEChargedCrystalScarabGemItem(Variant variant) {
			super(new Properties().tab(LostWorldsUtils.ITEMS).fireResistant().rarity(Rarity.RARE), variant);
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
