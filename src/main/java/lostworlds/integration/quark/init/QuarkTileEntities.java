package lostworlds.integration.quark.init;

//@EventBusSubscriber(bus = Bus.MOD, modid = ModUtils.ID)
public class QuarkTileEntities 
{
//	public static final TileEntityType<QuarkChestTileEntity> MOD_CHEST = new TileEntityType<>(QuarkChestTileEntity::new, Sets.newHashSet(collectBlocks(QuarkChestBlock.class)), null);
//	public static final TileEntityType<QuarkChestTileEntity> MOD_TRAPPED_CHEST = new TileEntityType<>(QuarkTrappedChestTileEntity::new, Sets.newHashSet(collectBlocks(QuarkTrappedChestBlock.class)), null);
//	
//	@SubscribeEvent
//	public static void register(final RegistryEvent.Register<TileEntityType<?>> event)
//	{
//		if(!FMLEnvironment.production || ModList.get().isLoaded(QuarkUtils.QUARK_ID))
//		{
//			event.getRegistry().register(MOD_CHEST.setRegistryName(ModUtils.rL("mod_chest")));
//			event.getRegistry().register(MOD_TRAPPED_CHEST.setRegistryName(ModUtils.rL("mod_trapped_chest")));
//		}
//	}
//	
//	public static Block[] collectBlocks(Class<?> blockClass) 
//	{
//		return ForgeRegistries.BLOCKS.getValues().stream().filter(blockClass::isInstance).toArray(Block[]::new);
//	}
}
