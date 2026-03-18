package me.alpha432.oyvey.features.modules.client;

import me.alpha432.oyvey.event.impl.ClientEvent;
import me.alpha432.oyvey.event.system.Subscribe;
import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.features.settings.Setting;
import me.alpha432.oyvey.util.TextUtil;
import me.alpha432.oyvey.util.player.ChatUtil;

public class NotificationsModule extends Module {

    public Setting<Boolean> moduleToggle = bool("Module Toggle", true);
    public Setting<Boolean> showPrefix   = bool("Show Prefix",   true);

    public NotificationsModule() {
        super("Notifications", "Displays notifications for client events", Category.CLIENT);
    }

    @Subscribe
    public void onClient(ClientEvent event) {
        if (!moduleToggle.getValue()
                || event.getType() != ClientEvent.Type.TOGGLE_MODULE
                || event.getFeature() instanceof ClickGuiModule) return;

        boolean on     = event.getFeature().isEnabled();
        String  name   = event.getFeature().getName();
        String  state  = on ? "§a§lon" : "§c§loff";
        String  prefix = showPrefix.getValue() ? "§b§lzoux1 §8| §r" : "";

        ChatUtil.sendMessage(prefix + name + " §8→ " + state);
    }
}
