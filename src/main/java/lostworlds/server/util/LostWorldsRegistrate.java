package lostworlds.server.util;

import com.tterrag.registrate.AbstractRegistrate;
import com.tterrag.registrate.builders.BlockBuilder;
import com.tterrag.registrate.util.nullness.NonNullFunction;
import com.tterrag.registrate.util.nullness.NonNullSupplier;

import lostworlds.server.block.LostWorldsBlockModels;
import net.minecraft.block.AbstractBlock.Properties;
import net.minecraft.block.AbstractButtonBlock;
import net.minecraft.block.AbstractSignBlock;
import net.minecraft.block.Block;
import net.minecraft.block.DoorBlock;
import net.minecraft.block.FenceBlock;
import net.minecraft.block.FenceGateBlock;
import net.minecraft.block.PressurePlateBlock;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.TrapDoorBlock;
import net.minecraft.block.WallBlock;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class LostWorldsRegistrate extends AbstractRegistrate<LostWorldsRegistrate> {
	protected LostWorldsRegistrate(String modid) {
		super(modid);

//		this.addDataGenerator(ProviderType.LANG, provider -> provider.add("itemGroup.lostworlds.items", "Lost Worlds Items"));
//		this.addDataGenerator(ProviderType.LANG, provider -> provider.add("itemGroup.lostworlds.blocks", "Lost Worlds Blocks"));
//		this.addDataGenerator(ProviderType.LANG, provider -> provider.add("item.lostworlds.lost_worlds_lexicon.desc", "An Everything-You-Need-to-Know Book!"));
//		this.addDataGenerator(ProviderType.LANG, provider -> provider.add("item.lostworlds.field_guide.desc", "Willatendo - Volume 3"));
//		this.addDataGenerator(ProviderType.LANG, provider -> provider.add("item.lostworlds.music_disc_ascented.desc", "Willatendo - Ascented"));
//		this.addDataGenerator(ProviderType.LANG, provider -> provider.add("item.lostworlds.collectible.desc", "It's a collectible!"));
//		for (DyeColor color : DyeColor.values()) {
//			this.addDataGenerator(ProviderType.LANG, provider -> provider.add("block.minecraft.banner.scarab." + color.getName(), Arrays.stream(color.getName().toLowerCase(Locale.ROOT).split("_")).map(StringUtils::capitalize).collect(Collectors.joining(" ")) + " Scarab"));
//		}
	}

	public static NonNullSupplier<LostWorldsRegistrate> lazy(String modid) {
		return NonNullSupplier.of(() -> new LostWorldsRegistrate(modid).registerEventListeners(FMLJavaModLoadingContext.get().getModEventBus()));
	}

	public <T extends Block> BlockBuilder<T, LostWorldsRegistrate> blockAndItem(String name, NonNullFunction<Properties, T> factory) {
		return super.block(name, factory).simpleItem();
	}

	public <T extends StairsBlock> BlockBuilder<T, LostWorldsRegistrate> stairBlock(String name, String texture, NonNullFunction<Properties, T> factory) {
		return super.block(name, factory).blockstate((block, provider) -> provider.stairsBlock((StairsBlock) block.get(), provider.modLoc("block/" + texture)));
	}

	public <T extends SlabBlock> BlockBuilder<T, LostWorldsRegistrate> slabBlock(String name, String texture, NonNullFunction<Properties, T> factory) {
		return super.block(name, factory).blockstate((block, provider) -> provider.slabBlock((SlabBlock) block.get(), provider.modLoc("block/" + texture), provider.modLoc("block/" + texture)));
	}

	public <T extends WallBlock> BlockBuilder<T, LostWorldsRegistrate> wallBlock(String name, String texture, NonNullFunction<Properties, T> factory) {
		return super.block(name, factory).blockstate((block, provider) -> provider.wallBlock((WallBlock) block.get(), provider.modLoc("block/" + texture))).item().model((item, provider) -> provider.withExistingParent(name, provider.modLoc("block/" + texture))).build();
	}

	public <T extends FenceBlock> BlockBuilder<T, LostWorldsRegistrate> fenceBlock(String name, String texture, NonNullFunction<Properties, T> factory) {
		return super.block(name, factory).blockstate((block, provider) -> provider.fenceBlock((FenceBlock) block.get(), provider.modLoc("block/" + texture)));
	}

	public <T extends FenceBlock> BlockBuilder<T, LostWorldsRegistrate> fenceBlock(String name, NonNullFunction<Properties, T> factory) {
		return super.block(name, factory).blockstate((block, provider) -> provider.fenceBlock((FenceBlock) block.get(), provider.modLoc("block/" + name)));
	}

	public <T extends FenceGateBlock> BlockBuilder<T, LostWorldsRegistrate> fenceGateBlock(String name, String texture, NonNullFunction<Properties, T> factory) {
		return super.block(name, factory).blockstate((block, provider) -> provider.fenceGateBlock((FenceGateBlock) block.get(), provider.modLoc("block/" + texture)));
	}

	public <T extends AbstractButtonBlock> BlockBuilder<T, LostWorldsRegistrate> buttonBlock(String name, String texture, NonNullFunction<Properties, T> factory) {
		return super.block(name, factory).blockstate((block, provider) -> LostWorldsBlockModels.button(block.get(), texture, provider)).item().model((item, provider) -> LostWorldsBlockModels.buttonInv(item.get(), provider)).build();
	}

	public <T extends PressurePlateBlock> BlockBuilder<T, LostWorldsRegistrate> pressurePlateBlock(String name, String texture, NonNullFunction<Properties, T> factory) {
		return super.block(name, factory).blockstate((block, provider) -> LostWorldsBlockModels.pressurePlate(block.get(), texture, provider)).simpleItem();
	}

	public <T extends TrapDoorBlock> BlockBuilder<T, LostWorldsRegistrate> trapdoorBlock(String name, NonNullFunction<Properties, T> factory) {
		return super.block(name, factory).blockstate((block, provider) -> provider.trapdoorBlock((TrapDoorBlock) block.get(), provider.modLoc(name), true)).item().model((item, provider) -> provider.withExistingParent(name, provider.modLoc("block/" + name + "_bottom"))).build();
	}

	public <T extends DoorBlock> BlockBuilder<T, LostWorldsRegistrate> doorBlock(String name, NonNullFunction<Properties, T> factory) {
		return super.block(name, factory).blockstate((block, provider) -> provider.doorBlock((DoorBlock) block.get(), provider.modLoc(name + "_bottom"), provider.modLoc(name + "_top"))).item().model((item, provider) -> provider.itemTexture(() -> item.get())).build();
	}

	public <T extends AbstractSignBlock> BlockBuilder<T, LostWorldsRegistrate> signBlock(String name, String texture, NonNullFunction<Properties, T> factory) {
		return super.block(name, factory).blockstate((block, provider) -> provider.getVariantBuilder(block.get()).partialState().addModels(new ConfiguredModel(provider.models().getBuilder(name).texture("particle", provider.modLoc(texture)))));
	}
}
