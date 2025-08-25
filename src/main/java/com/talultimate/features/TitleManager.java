package com.talultimate.features;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class TitleManager {
    private final JavaPlugin plugin;
    public TitleManager(JavaPlugin plugin){ this.plugin = plugin; }
    public void setCurrent(Player p, String id){ p.sendMessage("§b칭호 설정: " + id); }
    public void grant(Player p, String id, boolean silent){ p.sendMessage("§b칭호 획득: " + id); }
}
