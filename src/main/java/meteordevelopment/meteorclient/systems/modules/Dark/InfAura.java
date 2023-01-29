/*
 * This file is part of the Meteor Client distribution (https://github.com/MeteorDevelopment/meteor-client).
 * Copyright (c) Meteor Development.
 */

package meteordevelopment.meteorclient.systems.modules.Dark;

import it.unimi.dsi.fastutil.objects.Object2BooleanMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import meteordevelopment.meteorclient.events.render.Render3DEvent;
import meteordevelopment.meteorclient.events.world.TickEvent;
import meteordevelopment.meteorclient.settings.EntityTypeListSetting;
import meteordevelopment.meteorclient.settings.EnumSetting;
import meteordevelopment.meteorclient.settings.Setting;
import meteordevelopment.meteorclient.settings.SettingGroup;
import meteordevelopment.meteorclient.systems.friends.Friends;
import meteordevelopment.meteorclient.systems.modules.Categories;
import meteordevelopment.meteorclient.systems.modules.Module;
import meteordevelopment.meteorclient.utils.entity.SortPriority;
import meteordevelopment.meteorclient.utils.entity.TargetUtils;
import meteordevelopment.meteorclient.utils.player.PlayerUtils;
import meteordevelopment.meteorclient.utils.render.color.Color;
import meteordevelopment.orbit.EventHandler;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import net.minecraft.util.math.Vec3d;

public class InfAura extends Module {
    private final SettingGroup sgGeneral = this.settings.getDefaultGroup();

    private final Setting<SortPriority> priority = this.sgGeneral.add((Setting)((EnumSetting.Builder)((EnumSetting.Builder)((EnumSetting.Builder)(new EnumSetting.Builder())
        .name("priority"))
        .description("How to filter targets within range."))
        .defaultValue(SortPriority.LowestHealth))
        .build());

    private final Setting<Object2BooleanMap<EntityType<?>>> entities = this.sgGeneral.add((Setting)((EntityTypeListSetting.Builder)((EntityTypeListSetting.Builder)(new EntityTypeListSetting.Builder())
        .name("entities"))
        .description("Entities to attack."))
        .onlyAttackable()
        .build());

    public HashMap<Vec3d, Vec3d> positions;

    public InfAura() {
        super(Categories.Dark, "inf-aura", "xd");
        this.positions = new HashMap<>();
    }

    public void onActivate() {}

    @EventHandler
    public void onTick(TickEvent.Pre e) {
        if (this.mc.player == null || this.mc.world == null || this.mc.interactionManager == null)
            return;
        if (this.mc.player.getAttackCooldownProgress(0.0F) < 1.0F)
            return;
        this.positions.clear();
        List<Entity> targets = new ArrayList<>();
        TargetUtils.getList(targets, this::entityCheck, (SortPriority)this.priority.get(), 1);
        Optional<Entity> entity = Optional.empty();
        if (targets.size() > 0)
            entity = Optional.of(targets.get(0));
        Vec3d op = this.mc.player.getPos();
        if (entity.isPresent()) {
            Vec3d playerPos = this.mc.player.getPos();
            teleportFromTo(this.mc, playerPos, ((Entity)entity.get()).getPos());
            this.mc.interactionManager.attackEntity((PlayerEntity) this.mc.player, entity.get());
            teleportFromTo(this.mc, ((Entity)entity.get()).getPos(), playerPos);
            this.mc.player.setPosition(playerPos);
            while (op != this.mc.player.getPos())
                teleportFromTo(this.mc, this.mc.player.getPos(), op);
        }
    }

    @EventHandler
    public void onRender3D(Render3DEvent e) {
        for (Map.Entry<Vec3d, Vec3d> entry : this.positions.entrySet())
            e.renderer.line(((Vec3d)entry.getKey()).x, ((Vec3d)entry.getKey()).y, ((Vec3d)entry.getKey()).z, ((Vec3d)entry.getValue()).x, ((Vec3d)entry.getValue()).y, ((Vec3d)entry.getValue()).z, Color.WHITE);
    }

    private void teleportFromTo(MinecraftClient mc, Vec3d fromPos, Vec3d toPos) {
        if (mc.player == null)
            return;
        double distancePerBlock = 8.5D;
        double targetDistance = Math.ceil(fromPos.distanceTo(toPos) / distancePerBlock);
        Vec3d prevPos = fromPos;
        for (int i = 1; i <= targetDistance; i++) {
            Vec3d tempPos = fromPos.lerp(toPos, i / targetDistance);
            this.positions.put(prevPos, tempPos);
            prevPos = tempPos;
            mc.player.networkHandler.sendPacket(new PlayerMoveC2SPacket.PositionAndOnGround(tempPos.x, tempPos.y, tempPos.z, true));
            if (i % 4 == 0)
                try {
                    Thread.sleep(0L);
                } catch (InterruptedException interruptedException) {}
        }
    }

    public boolean entityCheck(Entity entity) {
        if (entity.equals(this.mc.player) || entity.equals(this.mc.cameraEntity))
            return false;
        if ((entity instanceof LivingEntity && ((LivingEntity)entity).isDead()) || !entity.isAlive())
            return false;
        if (!((Object2BooleanMap)this.entities.get()).getBoolean(entity.getType()))
            return false;
        if (!PlayerUtils.isWithin(entity, 150.0D))
            return false;
        if (entity instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) entity;
            if (player.isCreative())
                return false;
            if (!Friends.get().shouldAttack(player))
                return false;
        }
        return true;
    }
}
