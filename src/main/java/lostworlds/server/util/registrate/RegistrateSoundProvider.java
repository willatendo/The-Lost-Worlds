package lostworlds.server.util.registrate;

import com.tterrag.registrate.AbstractRegistrate;
import com.tterrag.registrate.providers.RegistrateProvider;

import net.minecraft.data.DataGenerator;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.SoundDefinition;
import net.minecraftforge.common.data.SoundDefinitionsProvider;
import net.minecraftforge.fml.LogicalSide;

public class RegistrateSoundProvider extends SoundDefinitionsProvider implements RegistrateProvider {
	private final AbstractRegistrate<?> owner;

	public RegistrateSoundProvider(AbstractRegistrate<?> owner, DataGenerator generator, ExistingFileHelper helper) {
		super(generator, owner.getModid(), helper);
		this.owner = owner;
	}

	@Override
	public LogicalSide getSide() {
		return LogicalSide.CLIENT;
	}

	@Override
	public void registerSounds() {
		this.owner.genData(LostWorldsRegistrate.SOUNDS, this);
	}

	public void addSound(SoundEvent soundEvent, SoundDefinition definition) {
		this.add(soundEvent, definition);
	}
}
