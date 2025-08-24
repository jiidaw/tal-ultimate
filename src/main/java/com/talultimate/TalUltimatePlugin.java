package com.talultimate;

import org.bukkit.plugin.java.JavaPlugin;

public class TalUltimatePlugin extends JavaPlugin {
    @Override public void onEnable() { getLogger().info("TALUltimate loaded!"); }
    @Override public void onDisable() { getLogger().info("TALUltimate disabled!"); }
}
