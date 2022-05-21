package lostworlds.server.impl;

import static lostworlds.api.APIRegistry.SOFT_DIRT_FUNCTIONS;
import static lostworlds.api.APIRegistry.SOFT_STONE_FUNCTIONS;

import lostworlds.server.impl.block.softdirt.AnomalocarisFossilDirtFunction;
import lostworlds.server.impl.block.softdirt.FeatherDirtFunction;
import lostworlds.server.impl.block.softdirt.FossilizedFeatherDirtFunction;
import lostworlds.server.impl.block.softdirt.FossilizedNautilusDirtFunction;
import lostworlds.server.impl.block.softdirt.FossilizedSkinDirtFunction;
import lostworlds.server.impl.block.softdirt.PalaeoniscumFossilDirtFunction;
import lostworlds.server.impl.block.softdirt.SpawnEggDirtFunction;
import lostworlds.server.impl.block.softstone.AnomalocarisFossilStoneFunction;
import lostworlds.server.impl.block.softstone.FossilStoneFunction;
import lostworlds.server.impl.block.softstone.FossilizedNautilusStoneFunction;
import lostworlds.server.impl.block.softstone.PalaeoniscumFossilStoneFunction;

public class ImplInit {

	public static void init() {
		// APISoftDirtFunction
		SOFT_DIRT_FUNCTIONS.add(new AnomalocarisFossilDirtFunction());
		SOFT_DIRT_FUNCTIONS.add(new FeatherDirtFunction());
		SOFT_DIRT_FUNCTIONS.add(new FossilizedFeatherDirtFunction());
		SOFT_DIRT_FUNCTIONS.add(new FossilizedNautilusDirtFunction());
		SOFT_DIRT_FUNCTIONS.add(new FossilizedSkinDirtFunction());
		SOFT_DIRT_FUNCTIONS.add(new PalaeoniscumFossilDirtFunction());
		SOFT_DIRT_FUNCTIONS.add(new SpawnEggDirtFunction());
		//
		SOFT_STONE_FUNCTIONS.add(new AnomalocarisFossilStoneFunction());
		SOFT_STONE_FUNCTIONS.add(new FossilStoneFunction());
		SOFT_STONE_FUNCTIONS.add(new FossilizedNautilusStoneFunction());
		SOFT_STONE_FUNCTIONS.add(new PalaeoniscumFossilStoneFunction());
	}
}
