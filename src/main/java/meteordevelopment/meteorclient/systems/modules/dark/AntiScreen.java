package meteordevelopment.meteorclient.systems.modules.dark;

import meteordevelopment.meteorclient.events.game.OpenScreenEvent;
import meteordevelopment.meteorclient.settings.BoolSetting;
import meteordevelopment.meteorclient.settings.Setting;
import meteordevelopment.meteorclient.settings.SettingGroup;
import meteordevelopment.meteorclient.systems.modules.Categories;
import meteordevelopment.meteorclient.systems.modules.Module;
import meteordevelopment.orbit.EventHandler;
import net.minecraft.client.gui.screen.CreditsScreen;
import net.minecraft.client.gui.screen.DemoScreen;

public class AntiScreen extends Module {
    private final SettingGroup sgGeneral = settings.getDefaultGroup();

    // General

    private final Setting<Boolean> endScreen = sgGeneral.add(new BoolSetting.Builder()
        .name("end-screen")
        .description("Removes the end screen after finishing the game.")
        .defaultValue(true)
        .build()
    );

    private final Setting<Boolean> demoScreen = sgGeneral.add(new BoolSetting.Builder()
        .name("demo-screen")
        .description("Removes the demo screen.")
        .defaultValue(true)
        .build()
    );

    // Constructor

    public AntiScreen() {
        super(Categories.Dark, "anti-screen", "Removes certain screens in the game.");
    }

    // Getter

    public boolean cancelEndScreen() {
        return this.endScreen.get();
    }

    public boolean cancelDemoScreen() {
        return this.demoScreen.get();
    }

    @EventHandler
    private void kys(OpenScreenEvent event){
        if(endScreen.get() && event.screen instanceof CreditsScreen){
            event.cancel();
        }
        if(demoScreen.get() && event.screen instanceof DemoScreen){
            event.cancel();
        }
    }
}
