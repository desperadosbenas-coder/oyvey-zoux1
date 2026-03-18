package me.alpha432.oyvey.features.modules.hud;

import me.alpha432.oyvey.event.impl.render.Render2DEvent;
import me.alpha432.oyvey.features.modules.client.HudModule;
import me.alpha432.oyvey.features.settings.Setting;
import me.alpha432.oyvey.util.BuildConfig;
import me.alpha432.oyvey.util.TextUtil;

public class WatermarkHudModule extends HudModule {

    public Setting<String>  text        = str("Text", "zoux1");
    public Setting<Boolean> fullVersion = new Setting<>("FullVersion", false);
    public Setting<Boolean> showVersion = bool("ShowVersion", true);

    public WatermarkHudModule() {
        super("Watermark", "Display watermark", 100, 10);
        if (BuildConfig.USING_GIT) {
            register(fullVersion);
        }
    }

    @Override
    protected void render(Render2DEvent e) {
        super.render(e);

        String name    = "§b§l" + text.getValue();
        String version = showVersion.getValue()
                ? " §8v§7" + BuildConfig.VERSION
                : "";
        String branch  = (fullVersion.getValue() && BuildConfig.USING_GIT)
                ? " §8(" + BuildConfig.BRANCH + "§8-§7" + BuildConfig.HASH + "§8)"
                : "";

        String watermarkString = name + version + branch;

        e.getContext().drawString(mc.font, watermarkString,
                (int) getX(), (int) getY(), -1);

        setWidth(mc.font.width(watermarkString));
        setHeight(mc.font.lineHeight);
    }
}
