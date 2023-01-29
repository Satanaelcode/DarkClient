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
import meteordevelopment.meteorclient.systems.modules.Module;
import meteordevelopment.meteorclient.utils.entity.SortPriority;
import meteordevelopment.meteorclient.utils.entity.TargetUtils;
import meteordevelopment.meteorclient.utils.player.PlayerUtils;
import meteordevelopment.meteorclient.utils.render.color.Color;
import meteordevelopment.orbit.EventHandler;
import net.minecraft.class_1297;
import net.minecraft.class_1299;
import net.minecraft.class_1309;
import net.minecraft.class_1657;
import net.minecraft.class_243;
import net.minecraft.class_2596;
import net.minecraft.class_2828;
import net.minecraft.class_310;

public class InfAura extends Module {
    private final SettingGroup sgGeneral = this.settings.getDefaultGroup();

    private final Setting<SortPriority> priority = this.sgGeneral.add((Setting)((EnumSetting.Builder)((EnumSetting.Builder)((EnumSetting.Builder)(new EnumSetting.Builder())
            .name("priority"))
            .description("How to filter targets within range."))
            .defaultValue(SortPriority.LowestHealth))
            .build());

    private final Setting<Object2BooleanMap<class_1299<?>>> entities = this.sgGeneral.add((Setting)((EntityTypeListSetting.Builder)((EntityTypeListSetting.Builder)(new EntityTypeListSetting.Builder())
            .name("entities"))
            .description("Entities to attack."))
            .onlyAttackable()
            .build());

    public HashMap<class_243, class_243> positions;

    public InfAura() {
        super(Categories.Dark, "inf-aura", "Destroy Your Enemies with this Module, Its Basically KillAura but with inf reach!");
        this.positions = new HashMap<>();
    }

    public void onActivate() {}

    @EventHandler
    public void onTick(TickEvent.Pre e) {
        if (this.mc.field_1724 == null || this.mc.field_1687 == null || this.mc.field_1761 == null)
            return;
        if (this.mc.field_1724.method_7261(0.0F) < 1.0F)
            return;
        this.positions.clear();
        List<class_1297> targets = new ArrayList<>();
        TargetUtils.getList(targets, this::entityCheck, (SortPriority)this.priority.get(), 1);
        Optional<class_1297> entity = Optional.empty();
        if (targets.size() > 0)
            entity = Optional.of(targets.get(0));
        class_243 op = this.mc.field_1724.method_19538();
        if (entity.isPresent()) {
            class_243 playerPos = this.mc.field_1724.method_19538();
            teleportFromTo(this.mc, playerPos, ((class_1297)entity.get()).method_19538());
            this.mc.field_1761.method_2918((class_1657)this.mc.field_1724, entity.get());
            teleportFromTo(this.mc, ((class_1297)entity.get()).method_19538(), playerPos);
            this.mc.field_1724.method_33574(playerPos);
            while (op != this.mc.field_1724.method_19538())
                teleportFromTo(this.mc, this.mc.field_1724.method_19538(), op);
        }
    }

    @EventHandler
    public void onRender3D(Render3DEvent e) {
        for (Map.Entry<class_243, class_243> entry : this.positions.entrySet())
            e.renderer.line(((class_243)entry.getKey()).field_1352, ((class_243)entry.getKey()).field_1351, ((class_243)entry.getKey()).field_1350, ((class_243)entry.getValue()).field_1352, ((class_243)entry.getValue()).field_1351, ((class_243)entry.getValue()).field_1350, Color.WHITE);
    }

    private void teleportFromTo(class_310 mc, class_243 fromPos, class_243 toPos) {
        if (mc.field_1724 == null)
            return;
        double distancePerBlock = 8.5D;
        double targetDistance = Math.ceil(fromPos.method_1022(toPos) / distancePerBlock);
        class_243 prevPos = fromPos;
        for (int i = 1; i <= targetDistance; i++) {
            class_243 tempPos = fromPos.method_35590(toPos, i / targetDistance);
            this.positions.put(prevPos, tempPos);
            prevPos = tempPos;
            mc.field_1724.field_3944.method_2883((class_2596)new class_2828.class_2829(tempPos.field_1352, tempPos.field_1351, tempPos.field_1350, true));
            if (i % 4 == 0)
                try {
                    Thread.sleep(0L);
                } catch (InterruptedException interruptedException) {}
        }
    }

    public boolean entityCheck(class_1297 entity) {
        if (entity.equals(this.mc.field_1724) || entity.equals(this.mc.field_1719))
            return false;
        if ((entity instanceof class_1309 && ((class_1309)entity).method_29504()) || !entity.method_5805())
            return false;
        if (!((Object2BooleanMap)this.entities.get()).getBoolean(entity.method_5864()))
            return false;
        if (!PlayerUtils.isWithin(entity, 150.0D))
            return false;
        if (entity instanceof class_1657) {
            class_1657 player = (class_1657)entity;
            if (player.method_7337())
                return false;
            if (!Friends.get().shouldAttack(player))
                return false;
        }
        return true;
    }
}


