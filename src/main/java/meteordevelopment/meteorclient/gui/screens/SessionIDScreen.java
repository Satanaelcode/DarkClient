package meteordevelopment.meteorclient.gui.screens;

import meteordevelopment.meteorclient.MeteorClient;
import meteordevelopment.meteorclient.gui.GuiTheme;
import meteordevelopment.meteorclient.gui.WindowScreen;
import meteordevelopment.meteorclient.gui.screens.settings.SetSession;
import meteordevelopment.meteorclient.gui.widgets.WLabel;
import meteordevelopment.meteorclient.gui.widgets.WWidget;
import meteordevelopment.meteorclient.gui.widgets.containers.WTable;
import meteordevelopment.meteorclient.gui.widgets.input.WTextBox;
import meteordevelopment.meteorclient.systems.accounts.types.TheAlteningAccount;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.util.Session;

import java.util.Optional;
import java.util.Set;

public class SessionIDScreen extends WindowScreen {
    MinecraftClient mc = MinecraftClient.getInstance();

    public SessionIDScreen(GuiTheme theme) {
        super(theme, "Session ID login");
    }

    @Override
    public void initWidgets() {
        WTable t = add(theme.table()).expandX().widget();

        // Token
        t.add(theme.label("UserName"));
        WTextBox USER = t.add(theme.textBox("")).minWidth(220).expandX().widget();
        t.row();

        t.add(theme.label("UUID"));
        WTextBox UUID = t.add(theme.textBox("")).minWidth(220).expandX().widget();
        t.row();

        t.add(theme.label("Session"));
        WTextBox ID = t.add(theme.textBox("")).minWidth(220).expandX().widget();
        t.row();

        // Add
        t.add(theme.button("Done")).minWidth(220).expandX().widget().action = () -> {
            if (ID.get().isEmpty() || UUID.get().isEmpty() || USER.get().isEmpty()) return;

            SetSession.username = USER.get();
            SetSession.UUID = UUID.get();
            SetSession.accessToken = ID.get();

            SetSession.sessionid = "token:" + SetSession.accessToken + ":" + SetSession.UUID;
            SetSession.originalSession = false;
            mc.setScreen(new TitleScreen());
        };

        t.add(theme.button("Return ACC")).minWidth(220).expandX().widget().action = () -> {
            SetSession.username = MeteorClient.BOOTNAME;
            SetSession.UUID = MeteorClient.BOOTUUID;
            SetSession.accessToken = MeteorClient.BOOTSESSION;

            SetSession.sessionid = "token:" + SetSession.accessToken + ":" + SetSession.UUID;
            SetSession.originalSession = false;
            mc.setScreen(new TitleScreen());
        };
    }
}

