package meteordevelopment.meteorclient.systems.modules.Dark;

import meteordevelopment.meteorclient.MeteorClient;
import meteordevelopment.meteorclient.events.world.TickEvent;
import meteordevelopment.meteorclient.gui.GuiTheme;
import meteordevelopment.meteorclient.gui.widgets.WLabel;
import meteordevelopment.meteorclient.gui.widgets.WWidget;
import meteordevelopment.meteorclient.gui.widgets.containers.WHorizontalList;
import meteordevelopment.meteorclient.gui.widgets.pressable.WButton;
import meteordevelopment.meteorclient.settings.IntSetting;
import meteordevelopment.meteorclient.settings.Setting;
import meteordevelopment.meteorclient.settings.SettingGroup;
import meteordevelopment.meteorclient.systems.modules.Categories;
import meteordevelopment.meteorclient.systems.modules.Category;
import meteordevelopment.meteorclient.systems.modules.Module;
import meteordevelopment.meteorclient.systems.modules.misc.BookBot;
import meteordevelopment.meteorclient.utils.player.ChatUtils;
import meteordevelopment.orbit.EventHandler;
import org.lwjgl.BufferUtils;
import org.lwjgl.PointerBuffer;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.util.tinyfd.TinyFileDialogs;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Random;

public class FileSpammer extends Module {
    private final SettingGroup sgGeneral = settings.getDefaultGroup();
    private final Setting<Integer> delay = sgGeneral.add(new IntSetting.Builder()
        .name("delay")
        .description("The amount of delay between writing books.")
        .defaultValue(20)
        .min(1)
        .sliderRange(1, 200)
        .build()
    );


    char[] filechars = null;

    private File file = new File(MeteorClient.FOLDER, "Spammertext.txt");
    private final PointerBuffer filters;

    private int delayTimer;

    public int iterator, bookCount;


    public FileSpammer() {
        super(Categories.Dark, "File Spammer", "Sure a txt file");

        if (!file.exists()) {
            file = null;
        }

        filters = BufferUtils.createPointerBuffer(1);

        ByteBuffer txtFilter = MemoryUtil.memASCII("*.txt");

        filters.put(txtFilter);
        filters.rewind();
    }

    @Override
    public WWidget getWidget(GuiTheme theme) {
        WHorizontalList list = theme.horizontalList();

        WButton selectFile = list.add(theme.button("Select File")).widget();

        WLabel fileName = list.add(theme.label((file != null && file.exists()) ? file.getName() : "No file selected.")).widget();

        selectFile.action = () -> {
            String path = TinyFileDialogs.tinyfd_openFileDialog(
                "Select File",
                new File(MeteorClient.FOLDER, "Spammertext.txt").getAbsolutePath(),
                filters,
                null,
                false
            );

            if (path != null) {
                file = new File(path);
                fileName.set(file.getName());
            }
        };

        return list;
    }

    @Override
    public void onActivate() {
        if ((file == null || !file.exists())) {
            info("No file selected, please select a file in the GUI.");
            toggle();
            return;
        }

        delayTimer = delay.get();
        bookCount = 0;
    }


    @EventHandler
    private void ontick(TickEvent event) {
        if (delay.get() <= 0) {

            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

                String line;
                while ((line = reader.readLine()) != null) {
                    ChatUtils.sendPlayerMsg(line);
                }

            } catch (IOException ignored) {
                error("Failed to read the file.");
            }

            delayTimer = delay.get();
        }
        else {
            delayTimer--;
        }
    }
}
