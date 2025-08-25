public class GachaManager {
    private final TalUltimatePlugin plugin;

    public GachaManager(TalUltimatePlugin plugin) {
        this.plugin = plugin;
    }
}
package com.talultimate.features;

import com.talultimate.TalUltimatePlugin;
import com.talultimate.core.WeightedPicker;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Map;

public class GachaManager {
    public void gachaItem(Player p, boolean premium){
        int price = TalUltimatePlugin.getPlugin(TalUltimatePlugin.class).getConfig()
                .getInt("gacha.item."+(premium?"premium-price":"normal-price"), 1000);
        if (!TalUltimatePlugin.ECON.take(p, price)){ p.sendMessage("§e잔액 부족"); return; }

        WeightedPicker<R> picker = new WeightedPicker<>();
        List<Map<?,?>> pool = TalUltimatePlugin.getPlugin(TalUltimatePlugin.class).getConfig().getMapList("gacha.item.pool");

        for (Map<?,?> m : pool){
            String type = String.valueOf(m.getOrDefault("type","LOSE"));
            String material = String.valueOf(m.getOrDefault("material",""));
            int amount = ((Number)m.getOrDefault("amount",1)).intValue();
            int weight = ((Number)m.getOrDefault("weight",1)).intValue();
            picker.add(new R(type, material, amount), weight);
        }

        R pick = picker.pick();
        if (pick==null){ p.sendMessage("§e당첨 없음"); return; }
        switch (pick.type){
            case "LOSE" -> p.sendMessage("§7꽝…");
            case "ORE","SEED" -> {
                try{
                    Material mat = Material.valueOf(pick.material);
                    p.getInventory().addItem(new ItemStack(mat, pick.amount));
                    p.sendMessage("§a당첨: "+mat+" x"+pick.amount);
                }catch(Exception ex){ p.sendMessage("§e보상 오류"); }
            }
            default -> p.sendMessage("§e기타 보상 처리 안됨");
        }
    }
    public record R(String type, String material, int amount){}
}
