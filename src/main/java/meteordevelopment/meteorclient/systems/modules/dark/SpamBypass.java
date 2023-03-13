package meteordevelopment.meteorclient.systems.modules.dark;

import meteordevelopment.meteorclient.events.world.TickEvent;
import meteordevelopment.meteorclient.settings.IntSetting;
import meteordevelopment.meteorclient.settings.Setting;
import meteordevelopment.meteorclient.settings.SettingGroup;
import meteordevelopment.meteorclient.settings.StringSetting;
import meteordevelopment.meteorclient.systems.modules.Categories;
import meteordevelopment.meteorclient.systems.modules.Module;
import meteordevelopment.meteorclient.utils.player.ChatUtils;
import meteordevelopment.orbit.EventHandler;

public class SpamBypass extends Module {

    private final SettingGroup sgGeneral = this.settings.getDefaultGroup();

    private final Setting<String> bc = sgGeneral.add(new StringSetting.Builder().name("Command").description("Bypass Command").defaultValue("/skill").build());

    private final Setting<String> message = sgGeneral.add(new StringSetting.Builder().name("Message").description("Message To Spam").defaultValue("Jinx").build());

    private final Setting<Integer> amount = sgGeneral.add(new IntSetting.Builder()
        .name("amount")
        .description("The amount of messages to send per tick")
        .defaultValue(10)
        .min(1)
        .sliderMax(20)
        .build()
    );

    private int timer;

    public SpamBypass() {
        super(Categories.Dark, "Spam-Bypass", "Better than spam.");
    }

    @EventHandler
    private void onTick(TickEvent.Post event) {
        if (timer < 0) {
            ChatUtils.sendPlayerMsg(bc + " " + message);
            timer = 20;
        }
        if (timer == 0) {
            for (int i = 0; i < amount.get(); i++) {
                ChatUtils.sendPlayerMsg(bc + " " + message);
            }
        } else {
            timer--;
        }
    }
}
