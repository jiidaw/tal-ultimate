package com.talultimate;

import com.talultimate.features.*;
import org.bukkit.plugin.java.JavaPlugin;

public final class TalUltimatePlugin extends JavaPlugin {

    public static WarpManager WARPS;
    public static GachaManager GACHA;
    public static TitleManager TITLES;
    public static EconomyManager ECON;

    @Override
    public void onEnable() {
        saveDefaultConfig();

        WARPS = new WarpManager(this);
        GACHA = new GachaManager(this);   // ← plugin 주입
        TITLES = new TitleManager(this);
        ECON  = new EconomyManager(this);

        getServer().getPluginManager().registerEvents(new AdvancementListener(this), this);
        getServer().getPluginManager().registerEvents(new LevelRewardListener(), this);

        getCommand("menu").setExecutor(new UiMenuCommand(this));
        getCommand("gachaitem").setExecutor(new GachaCommand(this, false));
        getCommand("gachatitle").setExecutor(new GachaCommand(this, true));
        getCommand("settitle").setExecutor(new SetTitleCommand());
        getCommand("shopadmin").setExecutor(new ShopAdminCommand(this));
        getCommand("광장").setExecutor(new WarpCommand(this, "plaza", "tal.warp.plaza"));
        getCommand("결투장").setExecutor(new WarpCommand(this, "arena", "tal.warp.arena"));

        getLogger().info("TALUltimate loaded!");
    }

    @Override
    public void onDisable() {
        getLogger().info("TALUltimate disabled.");
    }
}
