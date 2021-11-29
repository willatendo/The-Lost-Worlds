package lostworlds.library.item;

import lostworlds.library.entity.aquatic.BasicFishLikeEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.BucketItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.util.NonNullSupplier;

public class ModFishBucketItem extends BucketItem {
	private final NonNullSupplier<? extends EntityType<?>> fishTypeSupplier;

	public ModFishBucketItem(NonNullSupplier<? extends EntityType<?>> entityTypeSupplier, Fluid fluid, Properties properties) 
	{
		super(fluid, properties);
		this.fishTypeSupplier = entityTypeSupplier;
	}

	@Override
	public void checkExtraContent(World world, ItemStack stack, BlockPos pos) 
	{
		if(world instanceof ServerWorld) 
		{
			this.spawn((ServerWorld) world, stack, pos);
		}
	}

	@Override
	protected void playEmptySound(PlayerEntity entity, IWorld world, BlockPos pos) 
	{
		world.playSound(entity, pos, SoundEvents.BUCKET_EMPTY_FISH, SoundCategory.NEUTRAL, 1.0F, 1.0F);
	}

	private void spawn(ServerWorld world, ItemStack stack, BlockPos pos) 
	{
		Entity entity = this.fishTypeSupplier.get().spawn(world, stack, (PlayerEntity) null, pos, SpawnReason.BUCKET, true, false);
		if(entity != null && entity instanceof BasicFishLikeEntity) 
		{
			((BasicFishLikeEntity) entity).setFromBucket(true);
		}
	}

	protected EntityType<?> getFishType() 
	{
		return fishTypeSupplier.get();
	}
}
