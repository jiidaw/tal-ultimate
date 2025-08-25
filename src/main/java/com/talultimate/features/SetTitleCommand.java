package com.talultimate.features;

import com.talultimate.TalUltimatePlugin;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SetTitleCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender s,@NotNull Command c,@NotNull String l,@NotNull String[] a){
        if (!(s instanceof Player p)){ s.sendMessage("player only"); return true; }
        if (a.length < 1){ p.sendMessage("§e사용법: /settitle <titleId>"); return true; }
        TalUltimatePlugin.TITLES.setCurrent(p, a[0]);
        p.sendMessage("§a칭호가 변경되었습니다: " + a[0]);
        return true;
    }
}
