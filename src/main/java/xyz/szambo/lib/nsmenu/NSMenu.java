package xyz.szambo.lib.nsmenu;

import org.bukkit.plugin.java.JavaPlugin;

public final class NSMenu {

    private static JavaPlugin plugin;

    public static JavaPlugin getPlugin() {
        return plugin;
    }

    public static void setPlugin(final JavaPlugin plugin) {
        NSMenu.plugin = plugin;
    }
}
