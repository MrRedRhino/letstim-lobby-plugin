package org.pipeman.bestlobbyplugin;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;
import org.ipvp.canvas.MenuFunctionListener;

import java.util.logging.Logger;

public final class BestLobbyPlugin extends JavaPlugin {
    public static Plugin plugin;
    public static final int INV_SETTINGS_X = -149;
    public static final float INV_SETTINGS_Y = 15;
    public static final int INV_SETTINGS_Z = 157;
    public static final int HEIGHT_SETTINGS_X = -150;
    public static final float HEIGHT_SETTINGS_Y = 15;
    public static final int HEIGHT_SETTINGS_Z = 157;
    public static final int PLAY_SIGN_X = -151;
    public static final float PLAY_SIGN_Y = 15;
    public static final int PLAY_SIGN_Z = 157;
    public static final Vector PLATFORM_1 = new Vector(-154, 14, 150);
    public static final Vector PLATFORM_2 = new Vector(-146, 17, 158);

    public static Logger logger;

    @Override
    public void onLoad() {
        plugin = this;
        logger = this.getLogger();
    }

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new PlayerHandling(), this);
        Bukkit.getPluginManager().registerEvents(new AFKManager(), this);

        Bukkit.getPluginManager().registerEvents(new MenuFunctionListener(), this);

        Bukkit.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");

        Bukkit.getPluginManager().registerEvents(new CompassGui2(), this);

//        CompassGui compassGui = new CompassGui();
//        Bukkit.getPluginManager().registerEvents(compassGui, this);
//        PacketEvents.get().registerListener(compassGui);
//        PacketEvents.get().init();

//        Bukkit.getPluginManager().registerEvents(new MlgSettings(), this);
//        Bukkit.getPluginManager().registerEvents(new DoMlg(), this);
        // Plugin startup logic
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
