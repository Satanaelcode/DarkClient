package meteordevelopment.meteorclient.systems.commands.commands;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import meteordevelopment.meteorclient.systems.commands.Command;
import net.minecraft.command.CommandSource;

import static com.mojang.brigadier.Command.SINGLE_SUCCESS;

public class WorldBorderCommand extends Command {
    public WorldBorderCommand() {
        super("worldborder", "Set border size");
    }

    @Override
    public void build(LiteralArgumentBuilder<CommandSource> builder) {
        builder.then(argument("size", IntegerArgumentType.integer()).executes(context -> {
            int size = IntegerArgumentType.getInteger(context, "size");
            mc.player.getWorld().getWorldBorder().setSize((double)size);
            return SINGLE_SUCCESS;
        }));
    }
}
