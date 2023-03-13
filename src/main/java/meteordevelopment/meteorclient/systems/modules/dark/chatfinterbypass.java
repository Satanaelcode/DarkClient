/*
 * This file is part of the Meteor Client distribution (https://github.com/MeteorDevelopment/meteor-client).
 * Copyright (c) Meteor Development.
 */

package meteordevelopment.meteorclient.systems.modules.dark;

import meteordevelopment.meteorclient.events.game.SendMessageEvent;
import meteordevelopment.meteorclient.systems.modules.Categories;
import meteordevelopment.meteorclient.systems.modules.Module;
import meteordevelopment.orbit.EventHandler;

public class chatfinterbypass extends Module {
    public chatfinterbypass() {
        super(Categories.Dark, "Chat Filter Bypass", "Bypasses Chat Filters");
    }

    @EventHandler
    private void OnMessage(SendMessageEvent event){
        String message = event.message;
        message = message.replace("a", "a\u200C");
        message = message.replace("e", "e\u200C");
        message = message.replace("i", "i\u200C");
        message = message.replace("o", "o\u200C");
        message = message.replace("u", "u\u200C");
        event.message = message;
    }
}
