/*
 * This file is part of the Meteor Client distribution (https://github.com/MeteorDevelopment/meteor-client).
 * Copyright (c) Meteor Development.
 */

package meteordevelopment.meteorclient.systems.modules.dark;

import meteordevelopment.meteorclient.systems.hud.Hud;
import meteordevelopment.meteorclient.systems.modules.Categories;
import meteordevelopment.meteorclient.systems.modules.Module;
import meteordevelopment.meteorclient.systems.modules.Modules;

import java.util.ArrayList;

public class Panic extends Module {
    public Panic() {
        super(Categories.Dark, "Panic", "Disables all modules");
    }

    @Override
    public void onActivate() {
        new ArrayList<>(Modules.get().getActive()).forEach(Module::toggle);
        Hud.get().active = false;
    }
}
