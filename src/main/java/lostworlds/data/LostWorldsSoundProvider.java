package lostworlds.data;

import com.mojang.datafixers.util.Pair;

import lostworlds.client.sounds.LostWorldsSounds;
import lostworlds.server.LostWorldsUtils;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.SoundDefinition;
import net.minecraftforge.common.data.SoundDefinition.Sound;
import net.minecraftforge.common.data.SoundDefinition.SoundType;
import net.minecraftforge.common.data.SoundDefinitionsProvider;

public class LostWorldsSoundProvider extends SoundDefinitionsProvider {
	public LostWorldsSoundProvider(DataGenerator generator, ExistingFileHelper helper) {
		super(generator, LostWorldsUtils.ID, helper);
	}

	@Override
	public void registerSounds() {
		for (Pair<SoundEvent, String> events : LostWorldsSounds.SOUNDS) {
			this.add(events.getFirst(), SoundDefinition.definition().with(Sound.sound(events.getFirst().getLocation(), SoundType.SOUND)).subtitle(events.getSecond()));
		}
	}
}
