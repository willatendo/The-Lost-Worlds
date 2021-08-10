package lostworlds.library.item;

import java.util.Set;
import java.util.function.Supplier;

import com.google.common.collect.Sets;

import lostworlds.library.ModMaterials;
import lostworlds.library.ModToolTypes;
import lostworlds.library.tab.ModItemGroup;
import lostworlds.library.util.ModRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.ToolItem;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;

/*
 * Author: Willatendo
 * Date: July 2, 2021
 */

public class BrushItem extends ToolItem
{
	private static final Set<Material> EFFECTIVE_ON_MATERIALS = Sets.newHashSet(ModMaterials.MADE_FOR_BRUSH);
	private static final Set<Block> EFFECTIVE_ON_BLOCKS = Sets.newHashSet(/*BlockInit.STONE_FOSSIL.get(), BlockInit.TERRACOTTA_FOSSIL.get(), BlockInit.RED_TERRACOTTA_FOSSIL.get(), BlockInit.ORANGE_TERRACOTTA_FOSSIL.get(), BlockInit.YELLOW_TERRACOTTA_FOSSIL.get(), BlockInit.LIME_TERRACOTTA_FOSSIL.get(), BlockInit.GREEN_TERRACOTTA_FOSSIL.get(), BlockInit.CYAN_TERRACOTTA_FOSSIL.get(), BlockInit.LIGHT_BLUE_TERRACOTTA_FOSSIL.get(), BlockInit.BLUE_TERRACOTTA_FOSSIL.get(), BlockInit.MAGENTA_TERRACOTTA_FOSSIL.get(), BlockInit.PURPLE_TERRACOTTA_FOSSIL.get(), BlockInit.PINK_TERRACOTTA_FOSSIL.get(), BlockInit.BROWN_TERRACOTTA_FOSSIL.get(), BlockInit.BLACK_TERRACOTTA_FOSSIL.get(), BlockInit.GREY_TERRACOTTA_FOSSIL.get(), BlockInit.LIGHT_GREY_TERRACOTTA_FOSSIL.get(), BlockInit.WHITE_TERRACOTTA_FOSSIL.get()*/);

	protected BrushItem(IItemTier tier) 
	{
		super(1.0F, -2.4F, tier, EFFECTIVE_ON_BLOCKS, new Properties().addToolType(ModToolTypes.BRUSH, tier.getLevel()).tab(ModItemGroup.ITEMS));
	}

	@Override
	public float getDestroySpeed(ItemStack stack, BlockState state) 
	{
		Material material = state.getMaterial();
		return EFFECTIVE_ON_MATERIALS.contains(material) ? this.speed : super.getDestroySpeed(stack, state);
	}
	
	public static Item create(Teirs teir)
	{
		Item item = new BrushItem(teir);
		ModRegistry.register(teir.toString().toLowerCase() + "_brush", item);
		return item;
	}
	
	public enum Teirs implements IItemTier
	{
		LEATHER(0, 59, 2.0F, 0.0F, 15, () -> 
		{
			return Ingredient.of(Items.STRING);
		}),
		IRON(0, 250, 2.0F, 2.0F, 14, () -> 
		{
			return Ingredient.of(Items.STRING);
		}),
		DIAMOND(0, 1561, 2.0F, 3.0F, 10, () -> 
		{
			return Ingredient.of(Items.STRING);
		}),
		GOLD(0, 32, 2.0F, 0.0F, 22, () -> 
		{
			return Ingredient.of(Items.STRING);
		}),
		NETHERITE(0, 2031, 2.0F, 4.0F, 15, () -> 
		{
			return Ingredient.of(Items.STRING);
		}),	
		CRYSTAL_SCARAB(0, 13616, 2.0F, 80.0F, 40, () -> 
		{
			return Ingredient.of(Items.STRING); 
		}); 
		
		private final int level;
		private final int uses;
		private final float speed;
		private final float damage;
		private final int enchantmentValue;
		private final LazyValue<Ingredient> repairIngredient;
		
		private Teirs(int level, int uses, float speed, float damage, int enchantmentValue, Supplier<Ingredient> repairIngredient) 
		{
			this.level = level;
			this.uses = uses;
			this.speed = speed;
			this.damage = damage;
			this.enchantmentValue = enchantmentValue;
			this.repairIngredient = new LazyValue<>(repairIngredient);
		}
		
		@Override
		public int getUses() 
		{
			return this.uses;
		}
		
		@Override
		public float getSpeed() 
		{
			return this.speed;
		}
		
		@Override
		public float getAttackDamageBonus() 
		{
			return this.damage;
		}
		
		@Override
		public int getLevel() 
		{
			return this.level;
		}
		
		@Override
		public int getEnchantmentValue() 
		{
			return this.enchantmentValue;
		}
		
		@Override
		public Ingredient getRepairIngredient() 
		{
			return this.repairIngredient.get();
		}
	}
}
