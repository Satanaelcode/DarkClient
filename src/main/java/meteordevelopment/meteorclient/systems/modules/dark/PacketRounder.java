package meteordevelopment.meteorclient.systems.modules.dark;

import meteordevelopment.meteorclient.events.packets.PacketEvent;
import meteordevelopment.meteorclient.mixin.accessor.PlayerMoveAccessor;
import meteordevelopment.meteorclient.mixin.accessor.VehicleMoveAccessor;
import meteordevelopment.meteorclient.systems.modules.Categories;
import meteordevelopment.meteorclient.systems.modules.Module;
import meteordevelopment.meteorclient.utils.dark.DarkUtils;
import meteordevelopment.orbit.EventHandler;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import net.minecraft.network.packet.c2s.play.VehicleMoveC2SPacket;

public class PacketRounder extends Module {
    public PacketRounder() {
        super(Categories.Dark, "Packet Rounder", "Im gonna kms");
    }

    @Override
    public void onActivate() {
        super.onActivate();
    }

    @EventHandler
    private void onpacket(PacketEvent.Sent event){
        if (event.packet instanceof PlayerMoveC2SPacket packet){
            if (packet instanceof PlayerMoveC2SPacket.PositionAndOnGround || packet instanceof PlayerMoveC2SPacket.Full){
                PlayerMoveAccessor accessor = (PlayerMoveAccessor) packet;
                double x = packet.getX(0);
                double z = packet.getZ(0);

                x = DarkUtils.roundCoordinates(x);
                z = DarkUtils.roundCoordinates(z);

                accessor.setX(x);
                accessor.setZ(z);
            }
        }
        if (event.packet instanceof VehicleMoveC2SPacket packet){
            VehicleMoveAccessor accessor = (VehicleMoveAccessor) packet;
            double x = packet.getX();
            double z = packet.getZ();

            x = DarkUtils.roundCoordinates(x);
            z = DarkUtils.roundCoordinates(z);

            accessor.setX(x);
            accessor.setZ(z);
        }
    }
}
