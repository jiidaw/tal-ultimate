package com.talultimate.features;

import com.talultimate.TalUltimatePlugin;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ShopAdminCommand implements CommandExecutor {
    private final TalUltimatePlugin plugin;
    public ShopAdminCommand(TalUltimatePlugin plugin){ this.plugin = plugin; }

    @Override
    public boolean onCommand(@NotNull CommandSender sender,@NotNull Command cmd,@NotNull String label,@NotNull String[] args) {
        if (!(sender instanceof Player p)){ sender.sendMessage("player only"); return true; }
        if (!p.isOp()){ p.sendMessage("§cOP만 사용 가능합니다."); return true; }
        if (args.length < 2){ p.sendMessage("§e사용법: /shopadmin <아이템> <가격>"); return true; }
        String mat = args[0].toUpperCase();
        int price;
        try { price = Integer.parseInt(args[1]); }
        catch (NumberFormatException e){ p.sendMessage("§c숫자를 입력하세요."); return true; }
        plugin.getConfig().set("economy.ore-prices."+mat, price);
        plugin.saveConfig();
        p.sendMessage("§a상점 가격 변경: "+mat+" = "+price+"원");
        return true;
    }
}
