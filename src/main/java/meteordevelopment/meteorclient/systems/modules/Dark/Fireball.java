/*
 * This file is part of the Meteor Client distribution (https://github.com/MeteorDevelopment/meteor-client).
 * Copyright (c) Meteor Development.
 */

package meteordevelopment.meteorclient.systems.modules.Dark;

import meteordevelopment.meteorclient.events.world.TickEvent;
import meteordevelopment.meteorclient.settings.EnumSetting;
import meteordevelopment.meteorclient.settings.IntSetting;
import meteordevelopment.meteorclient.settings.Setting;
import meteordevelopment.meteorclient.settings.SettingGroup;
import meteordevelopment.meteorclient.systems.modules.Categories;
import meteordevelopment.meteorclient.systems.modules.Module;
import meteordevelopment.orbit.EventHandler;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtDouble;
import net.minecraft.nbt.NbtList;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;

public class Fireball extends Module {
    private final SettingGroup sgGeneral = settings.getDefaultGroup();
    private final Setting<Fireball.Modes> mode = sgGeneral.add(new EnumSetting.Builder<Fireball.Modes>()
        .name("mode")
        .description("the mode")
        .defaultValue(Fireball.Modes.Instant)
        .build());

    private final Setting<Integer> power = sgGeneral.add(new IntSetting.Builder()
        .name("power")
        .description("How big the explosions are")
        .defaultValue(15)
        .min(1)
        .sliderMax(200)
        .build());

    private final Setting<Integer> speed = sgGeneral.add(new IntSetting.Builder()
        .name("speed")
        .description("How fast the fireball moves")
        .defaultValue(15)
        .min(1)
        .sliderMax(200)
        .build());

    public Fireball() {
        super(Categories.Dark, "FireballClicker", "Shoots fireballs where you click");
    }

    @Override
    public void onActivate() {
        if (!mc.player.getAbilities().creativeMode){
            info("You must be in creative mode");
        }
    }

    @EventHandler
    public void onclick(TickEvent.Pre event){
        if (mc.options.attackKey.isPressed() && mc.currentScreen == null) {
            HitResult hr = mc.cameraEntity.raycast(600, 0, true);
            ItemStack rst = mc.player.getMainHandStack();
            Vec3d posrot = mc.player.getRotationVector().multiply(speed.get());
            BlockHitResult bhr = new BlockHitResult(mc.player.getEyePos(), Direction.DOWN, new BlockPos(mc.player.getEyePos()), false);
            Vec3d blockpos = hr.getPos();
            BlockPos pos = new BlockPos(blockpos);
            switch (mode.get()) {
                case Instant -> {
                    Vec3d posrot2 = mc.player.getRotationVector().multiply(100);
                    ItemStack Instant = new ItemStack(Items.ENDERMAN_SPAWN_EGG);
                    NbtCompound tag = new NbtCompound();
                    NbtList Pos = new NbtList();
                    NbtList motion = new NbtList();
                    Pos.add(NbtDouble.of(pos.getX()));
                    Pos.add(NbtDouble.of(pos.getY()));
                    Pos.add(NbtDouble.of(pos.getZ()));
                    motion.add(NbtDouble.of(posrot2.x));
                    motion.add(NbtDouble.of(posrot2.y));
                    motion.add(NbtDouble.of(posrot2.z));
                    tag.put("Pos", Pos);
                    tag.put("Motion", motion);
                    tag.putInt("ExplosionPower", power.get());
                    tag.putString("id", "minecraft:fireball");
                    Instant.setSubNbt("EntityTag", tag);
                    mc.interactionManager.clickCreativeStack(Instant, 36 + mc.player.getInventory().selectedSlot);
                    mc.interactionManager.interactBlock(mc.player, Hand.MAIN_HAND, bhr);
                    mc.interactionManager.clickCreativeStack(rst, 36 + mc.player.getInventory().selectedSlot);
                }
                case fireball -> {
                    ItemStack Motion = new ItemStack(Items.ENDERMAN_SPAWN_EGG);
                    NbtCompound tag = new NbtCompound();
                    NbtList motion = new NbtList();
                    motion.add(NbtDouble.of(posrot.x));
                    motion.add(NbtDouble.of(posrot.y));
                    motion.add(NbtDouble.of(posrot.z));
                    tag.put("Motion", motion);
                    tag.putInt("ExplosionPower", power.get());
                    tag.putString("id", "minecraft:fireball");
                    Motion.setSubNbt("EntityTag", tag);
                    mc.interactionManager.clickCreativeStack(Motion, 36 + mc.player.getInventory().selectedSlot);
                    mc.interactionManager.interactBlock(mc.player, Hand.MAIN_HAND, bhr);
                    mc.interactionManager.clickCreativeStack(rst, 36 + mc.player.getInventory().selectedSlot);
                }
            }
        }
    }

    public enum Modes {
        Instant, fireball
    }
}
