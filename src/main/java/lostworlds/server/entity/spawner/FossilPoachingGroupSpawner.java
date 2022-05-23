package lostworlds.server.entity.spawner;

import java.util.Random;

import lostworlds.server.entity.LostWorldsEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.monster.PatrollingMonster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.NaturalSpawner;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.common.ForgeHooks;

public class FossilPoachingGroupSpawner {
	private int nextTick;

	public int tick(Level world) {
		if (world.getGameRules().getBoolean(GameRules.RULE_DO_PATROL_SPAWNING)) {
			Random random = world.random;
			--this.nextTick;
			if (this.nextTick > 0) {
				return 0;
			} else {
				this.nextTick += 12000 + random.nextInt(1200);
				long i = world.getDayTime() / 24000L;
				if (i >= 5L && world.isDay()) {
					if (random.nextInt(5) != 0) {
						return 0;
					} else {
						int j = world.players().size();
						if (j < 1) {
							return 0;
						} else {
							Player playerentity = world.players().get(random.nextInt(j));
							if (playerentity.isSpectator()) {
								return 0;
							} else {
								int k = (24 + random.nextInt(24)) * (random.nextBoolean() ? -1 : 1);
								int l = (24 + random.nextInt(24)) * (random.nextBoolean() ? -1 : 1);
								BlockPos.MutableBlockPos blockpos$mutable = playerentity.blockPosition().mutable().move(k, 0, l);
								if (!world.hasChunksAt(blockpos$mutable.getX() - 10, blockpos$mutable.getY() - 10, blockpos$mutable.getZ() - 10, blockpos$mutable.getX() + 10, blockpos$mutable.getY() + 10, blockpos$mutable.getZ() + 10)) {
									return 0;
								} else {
									Holder<Biome> biome = world.getBiome(blockpos$mutable);
									Biome.BiomeCategory biome$category = Biome.getBiomeCategory(biome);
									if (biome$category == Biome.BiomeCategory.MUSHROOM) {
										return 0;
									} else {
										int i1 = 0;
										int j1 = (int) Math.ceil((double) world.getCurrentDifficultyAt(blockpos$mutable).getEffectiveDifficulty()) + 1;

										for (int k1 = 0; k1 < j1; ++k1) {
											++i1;
											blockpos$mutable.setY(world.getHeightmapPos(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, blockpos$mutable).getY());
											if (k1 == 0) {
												if (!this.spawnMember((ServerLevel) world, blockpos$mutable, random, true)) {
													break;
												}
											} else {
												this.spawnMember((ServerLevel) world, blockpos$mutable, random, false);
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

	private boolean spawnMember(ServerLevel world, BlockPos pos, Random rand, boolean leader) {
		BlockState blockstate = world.getBlockState(pos);
		if (!NaturalSpawner.isValidEmptySpawnBlock(world, pos, blockstate, blockstate.getFluidState(), LostWorldsEntities.FOSSIL_POACHER.get())) {
			return false;
		} else if (!PatrollingMonster.checkPatrollingMonsterSpawnRules(LostWorldsEntities.FOSSIL_POACHER.get(), world, MobSpawnType.PATROL, pos, rand)) {
			return false;
		} else {
			PatrollingMonster patrollerentity = LostWorldsEntities.FOSSIL_POACHER.create(world);
			if (patrollerentity != null) {
				if (leader) {
					patrollerentity.setPatrolLeader(true);
					patrollerentity.findPatrolTarget();
				}

				patrollerentity.setPos((double) pos.getX(), (double) pos.getY(), (double) pos.getZ());
				if (ForgeHooks.canEntitySpawn(patrollerentity, world, pos.getX(), pos.getY(), pos.getZ(), null, MobSpawnType.PATROL) == -1) {
					return false;
				}
				patrollerentity.finalizeSpawn(world, world.getCurrentDifficultyAt(pos), MobSpawnType.PATROL, (SpawnGroupData) null, (CompoundTag) null);
				world.addFreshEntityWithPassengers(patrollerentity);
				return true;
			} else {
				return false;
			}
		}
	}
}
