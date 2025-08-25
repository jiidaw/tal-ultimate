package com.talultimate.features;

import com.talultimate.TalUltimatePlugin;
import com.talultimate.core.WeightedPicker;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Map;

public class GachaManager {

    private final TalUltimatePlugin plugin;

    // ← 생성자 추가 (에러 원인 1 해결)
    public GachaManager(TalUltimatePlugin plugin) {
        this.plugin = plugin;
    }

    /** 아이템 가챠 (premium=true면 프리미엄 가격 사용) */
    public void gachaItem(Player p, boolean premium) {
        int price = plugin.getConfig()
                .getInt("gacha.item." + (premium ? "premium-price" : "normal-price"), 1000);

        if (!TalUltimatePlugin.ECON.take(p, price)) {
            p.sendMessage("§e잔액이 부족합니다.");
            return;
        }

        // 풀 불러오기
        List<Map<?, ?>> pool = plugin.getConfig().getMapList("gacha.item.pool");

        WeightedPicker<R> picker = new WeightedPicker<>();

        // ← 안전 캐스팅으로 CAP#1 타입 오류 제거 (에러 원인 2 해결)
        for (Map<?, ?> m : pool) {
            Object typeObj = m.get("type");
            String type = (typeObj != null) ? typeObj.toString() : "LOSE";

            Object matObj = m.get("material");
            String material = (matObj != null) ? matObj.toString() : "";

            Object amountObj = m.get("amount");
            int amount = (amountObj instanceof Number) ? ((Number) amountObj).intValue() : 1;

            Object weightObj = m.get("weight");
            int weight = (weightObj instanceof Number) ? ((Number) weightObj).intValue() : 1;

            picker.add(new R(type, material, amount, weight), weight);
        }

        R pick = picker.pick();
        if (pick == null) {
            p.sendMessage("§7꽝…");
            return;
        }

        switch (pick.type) {
            case "LOSE" -> p.sendMessage("§7꽝…");
            case "ORE", "SEED" -> {
                try {
                    Material mat = Material.valueOf(pick.material);
                    p.getInventory().addItem(new ItemStack(mat, pick.amount));
                    p.sendMessage("§a당첨: " + mat + " x" + pick.amount);
                } catch (Exception ex) {
                    p.sendMessage("§c보상 지급 오류: " + pick.material);
                }
            }
            default -> p.sendMessage("§e미정의 타입: " + pick.type);
        }
    }

    /** 가챠 항목 */
    public record R(String type, String material, int amount, int weight) {}
}
