package meteordevelopment.meteorclient.systems.modules.Dark;

import baritone.Baritone;
import baritone.api.BaritoneAPI;
import baritone.api.IBaritone;
import baritone.api.pathing.goals.GoalGetToBlock;
import baritone.pathing.calc.AStarPathFinder;
import meteordevelopment.meteorclient.events.entity.player.SendMovementPacketsEvent;
import meteordevelopment.meteorclient.events.packets.PacketEvent;
import meteordevelopment.meteorclient.events.world.TickEvent;
import meteordevelopment.meteorclient.settings.BoolSetting;
import meteordevelopment.meteorclient.settings.DoubleSetting;
import meteordevelopment.meteorclient.settings.Setting;
import meteordevelopment.meteorclient.settings.SettingGroup;
import meteordevelopment.meteorclient.systems.modules.Categories;
import meteordevelopment.meteorclient.systems.modules.Module;
import meteordevelopment.meteorclient.systems.modules.combat.Criticals;
import meteordevelopment.meteorclient.utils.misc.BaritoneUtils;
import meteordevelopment.meteorclient.utils.network.PacketUtils;
import meteordevelopment.meteorclient.utils.network.PacketUtilsUtil;
import meteordevelopment.orbit.EventHandler;
import net.fabricmc.loader.impl.lib.sat4j.core.Vec;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.debug.DebugRenderer;
import net.minecraft.client.render.debug.PathfindingDebugRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.pathing.Path;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;

import java.util.Objects;
import java.util.Optional;

public class JReach extends Module {
    private Optional<Entity> LastEntity = null;

    private final SettingGroup sgGeneral = settings.getDefaultGroup();

    public final Setting<Boolean> ArmSwing = sgGeneral.add(new BoolSetting.Builder()
        .name("ArmSwing")
        .description("Automatically draws your bow.")
        .defaultValue(false)
        .build()
    );

    private final Setting<Double> distance = sgGeneral.add(new DoubleSetting.Builder()
        .name("delta")
        .description("the length of each teleport")
        .defaultValue(8.5)
        .min(2)
        .max(10)
        .build()
    );

    public JReach() {
        super(Categories.Dark, "Reach+", "More than 6 block reach");
    }

    @Override
    public void onActivate() {
        Criticals criticals = new Criticals();
        if (criticals.isActive()){
            criticals.toggle();
            info("NO CRITICALS FOR YOU BITCH (basically no desync)");
        }
    }

    @EventHandler
    private void onTick(TickEvent.Pre event) {

        Optional<Entity> entity = DebugRenderer.getTargetedEntity(mc.player, (int) 150);

        if (mc.options.attackKey.isPressed()) {
            Vec3d op = mc.player.getPos();
            if (entity.isPresent()) {
                if (Objects.requireNonNull(mc.player).getAttackCooldownProgress(0) < 1) {
                    return;
                }

                Vec3d playerPos = mc.player.getPos();

                teleportFromTo(mc, playerPos, entity.get().getPos());

                mc.interactionManager.attackEntity(mc.player, entity.get());
                if (ArmSwing.get()) mc.player.swingHand(Hand.MAIN_HAND);
                LastEntity = entity;

                teleportFromTo(mc, entity.get().getPos(), playerPos);

                AStarPathFinder.mc.player.setPosition(playerPos);
            }
        }
    }

    private void teleportFromTo(MinecraftClient mc, Vec3d fromPos, Vec3d toPos){
        double distancePerBlock = distance.get();
        double targetDistance = Math.ceil(fromPos.distanceTo(toPos) / distancePerBlock);
        for (int i =1; i<=targetDistance; i++){
            Vec3d tempPos = fromPos.lerp(toPos, i / targetDistance);
            AStarPathFinder.mc.player.networkHandler.sendPacket(new PlayerMoveC2SPacket.PositionAndOnGround(tempPos.x, tempPos.y, tempPos.z, true));

            if (i%4 == 0){
                try{
                    Thread.sleep((long)((1/20) * 1000));
                }
                catch (InterruptedException e){

                }
            }
        }
    }
}
