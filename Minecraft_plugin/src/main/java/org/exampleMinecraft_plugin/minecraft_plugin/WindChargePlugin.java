package org.exampleMinecraft_plugin.minecraft_plugin;

import org.bukkit.plugin.java.JavaPlugin;

public class WindChargePlugin extends JavaPlugin {
    @Override
    public void onEnable() {
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new WindChargeListener(this), this);
        getLogger().info("WindChargePlugin ativado!");
    }

    @Override
    public void onDisable() {
        getLogger().info("WindChargePlugin desativado!");
    }
}
