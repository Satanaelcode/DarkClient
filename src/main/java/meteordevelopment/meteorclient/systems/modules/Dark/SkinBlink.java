/*
 * This file is part of the Meteor Client distribution (https://github.com/MeteorDevelopment/meteor-client).
 * Copyright (c) Meteor Development.
 */

package meteordevelopment.meteorclient.systems.modules.Dark;

import meteordevelopment.meteorclient.events.world.TickEvent;
import meteordevelopment.meteorclient.systems.modules.Categories;
import meteordevelopment.meteorclient.systems.modules.Category;
import meteordevelopment.meteorclient.systems.modules.Module;
import meteordevelopment.orbit.EventHandler;
import net.minecraft.client.render.entity.PlayerModelPart;

import java.util.Random;

public class SkinBlink extends Module {

    public SkinBlink() {
        super(Categories.Dark, "Skin Blinker", "Toggles skin layers at random");
    }

    @EventHandler
    private void onTick(TickEvent.Pre event){
        Random rand = new Random();

        if(rand.nextInt(4) != 0)
            return;

        for(PlayerModelPart part : PlayerModelPart.values())
            mc.options.togglePlayerModelPart(part, !mc.options.isPlayerModelPartEnabled(part));
    }
}
