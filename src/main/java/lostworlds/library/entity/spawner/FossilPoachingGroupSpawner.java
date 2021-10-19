package lostworlds.library.entity.spawner;

import java.util.Random;

import lostworlds.content.server.init.EntityInit;
import net.minecraft.block.BlockState;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.monster.PatrollerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.spawner.WorldEntitySpawner;
import net.minecraftforge.common.ForgeHooks;

public class FossilPoachingGroupSpawner 
{
	private int nextTick;

	public int tick(World world) 
	{
		if(world.getGameRules().getBoolean(GameRules.RULE_DO_PATROL_SPAWNING)) 
		{
			Random random = world.random;
			--this.nextTick;
			if(this.nextTick > 0) 
			{
				return 0;
			}
			else 
			{
				this.nextTick += 12000 + random.nextInt(1200);
				long i = world.getDayTime() / 24000L;
				if(i >= 5L && world.isDay()) 
				{
					if(random.nextInt(5) != 0) 
					{
						return 0;
					}
					else 
					{
						int j = world.players().size();
						if(j < 1) 
						{
							return 0;
						} 
						else 
						{
							PlayerEntity playerentity = world.players().get(random.nextInt(j));
							if(playerentity.isSpectator()) 
							{
								return 0;
							} 
							else 
							{
								int k = (24 + random.nextInt(24)) * (random.nextBoolean() ? -1 : 1);
								int l = (24 + random.nextInt(24)) * (random.nextBoolean() ? -1 : 1);
								BlockPos.Mutable blockpos$mutable = playerentity.blockPosition().mutable().move(k, 0, l);
								if(!world.hasChunksAt(blockpos$mutable.getX() - 10, blockpos$mutable.getY() - 10, blockpos$mutable.getZ() - 10, blockpos$mutable.getX() + 10, blockpos$mutable.getY() + 10, blockpos$mutable.getZ() + 10)) 
								{
									return 0;
								} 
								else 
								{
									Biome biome = world.getBiome(blockpos$mutable);
									Biome.Category biome$category = biome.getBiomeCategory();
									if(biome$category == Biome.Category.MUSHROOM) 
									{
										return 0;
									} 
									else 
									{
										int i1 = 0;
										int j1 = (int) Math.ceil((double) world.getCurrentDifficultyAt(blockpos$mutable).getEffectiveDifficulty()) + 1;

										for(int k1 = 0; k1 < j1; ++k1) 
										{
											++i1;
											blockpos$mutable.setY(world.getHeightmapPos(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, blockpos$mutable).getY());
											if(k1 == 0) 
											{
												if(!this.spawnMember((ServerWorld) world, blockpos$mutable, random, true)) 
												{
													break;
												}
											} 
											else 
											{
												this.spawnMember((ServerWorld) world, blockpos$mutable, random, false);
											}

											blockpos$mutable.setX(blockpos$mutable.getX() + random.nextInt(5) - random.nextInt(5));
											blockpos$mutable.setZ(blockpos$mutable.getZ() + random.nextInt(5) - random.nextInt(5));
										}
										return i1;
									}
								}
							}
						}
					}
				}
			}
		}
		return 0;
	}

	private boolean spawnMember(ServerWorld world, BlockPos pos, Random rand, boolean leader) 
	{
		BlockState blockstate = world.getBlockState(pos);
		if(!WorldEntitySpawner.isValidEmptySpawnBlock(world, pos, blockstate, blockstate.getFluidState(), EntityInit.FOSSIL_POACHER)) 
		{
			return false;
		} 
		else if(!PatrollerEntity.checkPatrollingMonsterSpawnRules(EntityInit.FOSSIL_POACHER, world, SpawnReason.PATROL, pos, rand)) 
		{
			return false;
		} 
		else 
		{
			PatrollerEntity patrollerentity = EntityInit.FOSSIL_POACHER.create(world);
			if(patrollerentity != null) 
			{
				if(leader) 
				{
					patrollerentity.setPatrolLeader(true);
					patrollerentity.findPatrolTarget();
				}

				patrollerentity.setPos((double) pos.getX(), (double) pos.getY(), (double) pos.getZ());
				if(ForgeHooks.canEntitySpawn(patrollerentity, world, pos.getX(), pos.getY(), pos.getZ(), null, SpawnReason.PATROL) == -1)
				{
					return false;
				}
				patrollerentity.finalizeSpawn(world, world.getCurrentDifficultyAt(pos), SpawnReason.PATROL, (ILivingEntityData) null, (CompoundNBT) null);
				world.addFreshEntityWithPassengers(patrollerentity);
				return true;
			} 
			else 
			{
				return false;
			}
		}
	}
}
