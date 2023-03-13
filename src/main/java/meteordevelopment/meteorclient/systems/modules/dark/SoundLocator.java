package meteordevelopment.meteorclient.systems.modules.dark;

import meteordevelopment.meteorclient.events.packets.PacketEvent;
import meteordevelopment.meteorclient.systems.modules.Categories;
import meteordevelopment.meteorclient.systems.modules.Module;
import meteordevelopment.orbit.EventHandler;
import net.minecraft.network.packet.s2c.play.PlaySoundS2CPacket;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.Vec3d;

public class SoundLocator extends Module {
    public SoundLocator() {
        super(Categories.Dark, "Sound Locator", "Locate Sound Events");
    }

    @EventHandler
    private void onevent(PacketEvent.Receive event){
        if (event.packet instanceof PlaySoundS2CPacket packet){
            Vec3d soundpos = new Vec3d(packet.getX(), packet.getY(), packet.getZ());

            if (packet.getSound() == SoundEvents.BLOCK_END_PORTAL_SPAWN){
                info("End Portal Activated At " + soundpos);
            }

            if (packet.getSound().equals(SoundEvents.ENTITY_WITHER_SPAWN)){
                info("Wither Spawned At " + soundpos);
            }
        }
    }
}

