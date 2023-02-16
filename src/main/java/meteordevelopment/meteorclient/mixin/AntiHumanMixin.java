package meteordevelopment.meteorclient.mixin;

import meteordevelopment.meteorclient.systems.modules.Modules;
import meteordevelopment.meteorclient.systems.modules.movement.NoFall;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(PlayerMoveC2SPacket.class)
public class AntiHumanMixin {
    @ModifyVariable(at = @At("HEAD"), method = "<init>", ordinal = 0, argsOnly = true)
    private static double roundX(double x) {
        return roundCoordinates(x);
    }

    @ModifyVariable(at = @At("HEAD"), method = "<init>", ordinal = 2, argsOnly = true)
    private static double roundZ(double z) {
        return roundCoordinates(z);
    }

    // Remember to do this vehicles

    private static double roundCoordinates(double x) {
        x = Math.round(x * 100) / 100d;
        // idk how this works but it's important to prevent floating point errors from something I read on the internet
        // https://jorianwoltjer.com/blog/post/hacking/playing-on-the-liveoverflow-minecraft-hacking-server
        x = Math.nextAfter(x, x + Math.signum(x));
        // Better to send something inaccurate than to get kicked
        if ((int) (x * 1000) % 10 != 0) x = Math.ceil(x);
        return x;
    }
}

