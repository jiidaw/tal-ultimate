package com.talultimate.features;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class EconomyManager {
    private final JavaPlugin plugin;
    private final ConcurrentHashMap<UUID, Long> balances = new ConcurrentHashMap<>();
    public EconomyManager(JavaPlugin plugin){ this.plugin = plugin; }

    public long get(Player p){ return balances.getOrDefault(p.getUniqueId(), 0L); }
    public void set(Player p, long value){ if (value<0) value=0; balances.put(p.getUniqueId(), value); }
    public void give(Player p, long amount){ if (amount<=0) return; set(p, get(p)+amount); }
    public boolean take(Player p, long amount){
        if (amount<=0) return true;
        long cur = get(p);
        if (cur < amount) return false;
        set(p, cur-amount); return true;
    }
}
