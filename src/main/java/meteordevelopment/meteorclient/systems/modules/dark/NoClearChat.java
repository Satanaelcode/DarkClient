/*
 * This file is part of the Meteor Client distribution (https://github.com/MeteorDevelopment/meteor-client).
 * Copyright (c) Meteor Development.
 */

package meteordevelopment.meteorclient.systems.modules.dark;

import meteordevelopment.meteorclient.events.game.ReceiveMessageEvent;
import meteordevelopment.meteorclient.systems.modules.Categories;
import meteordevelopment.meteorclient.systems.modules.Module;
import meteordevelopment.orbit.EventHandler;

public class NoClearChat extends Module {
    public NoClearChat() {
        super(Categories.Dark, "No Chat Clear", "Stops shitty skript ClearChat");
    }

    @EventHandler
    private void revieveMessage(ReceiveMessageEvent event){
        String message = event.getMessage().getString();

        if (message == "" || message == " " || message == "  "){
            event.cancel();
        }
    }
}
