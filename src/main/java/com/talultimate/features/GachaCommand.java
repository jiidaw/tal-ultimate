package com.talultimate.features;

import com.talultimate.TalUltimatePlugin;
import org.bukkit.command.*;
import org.jetbrains.annotations.NotNull;

public class GachaCommand implements CommandExecutor {
    private final TalUltimatePlugin plugin; private final boolean title;
    public GachaCommand(TalUltimatePlugin plugin, boolean title){ this.plugin=plugin; this.title=title; }
    @Override public boolean onCommand(@NotNull CommandSender s,@NotNull Command c,@NotNull String l,@NotNull String[] a){
        if (!(s instanceof org.bukkit.entity.Player p)){ s.sendMessage("player only"); return true; }
        if (title) p.sendMessage("§7(임시) 칭호 가챠는 추후 구현");
        else TalUltimatePlugin.GACHA.gachaItem(p, a.length>0 && a[0].equalsIgnoreCase("premium"));
        return true;
    }
}
