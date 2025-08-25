package com.talultimate.features;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.configuration.ConfigurationSection;

public class AdvancementListener implements Listener {
    private final JavaPlugin plugin;
    public AdvancementListener(JavaPlugin plugin){ this.plugin = plugin; }
    public ConfigurationSection titlesList() { return plugin.getConfig().getConfigurationSection("titles.list"); }
}
