package lostworlds.server.species;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

import lostworlds.server.LostWorldsRegistries;
import lostworlds.server.LostWorldsUtils;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;

public class SpeciesTypeCommand implements Command<CommandSourceStack> {
	public static final SpeciesTypeCommand INSTANCE = new SpeciesTypeCommand();

	public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
		dispatcher.register(Commands.literal("getAllSpeciesTypes").executes(INSTANCE));
	}

	@Override
	public int run(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
		for (SpeciesType speciesTypes : LostWorldsRegistries.SPECIES_TYPES_REGISTRY.get()) {
			String id = LostWorldsRegistries.SPECIES_TYPES_REGISTRY.get().getKey(speciesTypes).toString();
			context.getSource().sendSuccess(LostWorldsUtils.tTCA("command", "getAllSpeciesTypes.success", id), true);
		}

		return 0;
	}
}
