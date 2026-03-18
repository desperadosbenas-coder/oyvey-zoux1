package me.alpha432.oyvey.features.modules.hud;

import me.alpha432.oyvey.event.impl.render.Render2DEvent;
import me.alpha432.oyvey.features.modules.client.HudModule;
import me.alpha432.oyvey.features.settings.Setting;
import net.minecraft.ChatFormatting;

public class CoordinatesHudModule extends HudModule {

    public Setting<Boolean> nether    = bool("Nether", false);
    public Setting<Boolean> showLabel = bool("Labels", true);
    public Setting<Boolean> brackets  = bool("Brackets", true);

    public CoordinatesHudModule() {
        super("Coordinates", "Display coordinates", 160, 20);
    }

    @Override
    protected void render(Render2DEvent e) {
        super.render(e);
        if (nullCheck()) return;

        int x = mc.player.getBlockX();
        int y = mc.player.getBlockY();
        int z = mc.player.getBlockZ();

        String xStr = (showLabel.getValue() ? "§7x §f" : "") + x;
        String yStr = (showLabel.getValue() ? " §7y §f" : " ") + y;
        String zStr = (showLabel.getValue() ? " §7z §f" : " ") + z;

        String coordsStr = xStr + yStr + zStr;

        if (nether.getValue()) {
            boolean inNether = mc.player.level().dimension()
                    .location().getPath().equals("the_nether");
            int nx = inNether ? x * 8 : x / 8;
            int nz = inNether ? z * 8 : z / 8;
            String dim = inNether ? "§cNether §7→ §fOverworld" : "§aOverworld §7→ §cNether";
            coordsStr += "  " + (brackets.getValue() ? "§8[" : "") + dim
                    + (showLabel.getValue() ? " §7x §f" : " ") + nx
                    + (showLabel.getValue() ? " §7z §f" : " ") + nz
                    + (brackets.getValue() ? "§8]" : "");
        }

        String prefix = "§b§lzoux1 §8| §r";
        String full   = prefix + coordsStr;

        e.getContext().drawString(mc.font, full,
                (int) getX(), (int) getY(), -1);

        setWidth(mc.font.width(full));
        setHeight(mc.font.lineHeight);
    }
}
