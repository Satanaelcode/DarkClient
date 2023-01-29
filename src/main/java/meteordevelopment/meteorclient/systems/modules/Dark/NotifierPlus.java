/*
 * This file is part of the Meteor Client distribution (https://github.com/MeteorDevelopment/meteor-client).
 * Copyright (c) Meteor Development.
 */

package meteordevelopment.meteorclient.systems.modules.Dark;

import meteordevelopment.meteorclient.events.entity.EntityAddedEvent;
import meteordevelopment.meteorclient.settings.BoolSetting;
import meteordevelopment.meteorclient.settings.Setting;
import meteordevelopment.meteorclient.settings.SettingGroup;
import meteordevelopment.meteorclient.systems.modules.Categories;
import meteordevelopment.meteorclient.systems.modules.Module;
import meteordevelopment.orbit.EventHandler;
import net.minecraft.entity.player.PlayerEntity;

public class NotifierPlus extends Module {

    private final SettingGroup sgGeneral = settings.getDefaultGroup();

    private final Setting<Boolean> NotifyOpped = sgGeneral.add(new BoolSetting.Builder()
        .name("Opped")
        .description("Notifies you if opped players enter your view")
        .defaultValue(true)
        .build()
    );

    public NotifierPlus() {
        super(Categories.Dark, "Notifier+", "Notifies you of specific things");
    }

    @EventHandler
    private void onEntityAdded(EntityAddedEvent event) {
        if (event.entity.isPlayer() && NotifyOpped.get()){
            PlayerEntity player = (PlayerEntity) event.entity;
            if (player.hasPermissionLevel(4)){
                info("An OPPED player has entered your render distance");
            }
        }
    }
}
