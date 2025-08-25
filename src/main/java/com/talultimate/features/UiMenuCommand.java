package com.talultimate.features;

import org.bukkit.Bukkit;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class UiMenuCommand implements CommandExecutor, Listener {
    private final JavaPlugin plugin;
    public UiMenuCommand(JavaPlugin plugin){ this.plugin = plugin; }
    @Override
    public boolean onCommand(@NotNull CommandSender s,@NotNull Command c,@NotNull String l,@NotNull String[] a){
        if (!(s instanceof Player p)){ s.sendMessage("player only"); return true; }
        Bukkit.getPluginManager().registerEvents(this, plugin);
        p.sendMessage("§a메뉴(간소화): /gachaitem /gachatitle /settitle");
        return true;
    }
}
