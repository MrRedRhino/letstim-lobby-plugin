package org.pipeman.bestlobbyplugin.mlg;

import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.pipeman.bestlobbyplugin.BestLobbyPlugin;

import java.util.ArrayList;
import java.util.Arrays;

public class MlgSettings implements Listener {
    ArrayList<Component> lines;

    @EventHandler
    public void onInventoryInteract(PlayerInteractEvent event) {
        if (event.getClickedBlock() == null || event.getInteractionPoint() == null) return;
        if (event.getClickedBlock().getLocation().getBlockX() != BestLobbyPlugin.INV_SETTINGS_X
                || event.getClickedBlock().getLocation().getBlockY() != BestLobbyPlugin.INV_SETTINGS_Y
                || event.getClickedBlock().getLocation().getBlockZ() != BestLobbyPlugin.INV_SETTINGS_Z
                || event.getInteractionPoint().getY() >= BestLobbyPlugin.INV_SETTINGS_Y + 0.64)
            return;
        double interactHeight = event.getInteractionPoint().getY();

        event.getPlayer().removeScoreboardTag("MLG_INV_0");
        event.getPlayer().removeScoreboardTag("MLG_INV_1");
        event.getPlayer().removeScoreboardTag("MLG_INV_2");

        if (interactHeight < BestLobbyPlugin.INV_SETTINGS_Y + 0.43) {
            lines = MlgUtil.generateSign("MLG_INV_2");
            event.getPlayer().addScoreboardTag("MLG_INV_2");
        } else if (interactHeight < BestLobbyPlugin.INV_SETTINGS_Y + 0.54) {
            lines = MlgUtil.generateSign("MLG_INV_1");
            event.getPlayer().addScoreboardTag("MLG_INV_1");
        } else if (interactHeight < BestLobbyPlugin.INV_SETTINGS_Y + 0.64) {
            lines = MlgUtil.generateSign("MLG_INV_0");
            event.getPlayer().addScoreboardTag("MLG_INV_0");
        }
        event.getPlayer().sendSignChange(event.getClickedBlock().getLocation(), lines);
    }

    @EventHandler
    public void onHeightInteract(PlayerInteractEvent event) {

        if (event.getClickedBlock() == null || event.getInteractionPoint() == null) return;
        if (event.getClickedBlock().getLocation().getBlockX() != BestLobbyPlugin.HEIGHT_SETTINGS_X
                || event.getClickedBlock().getLocation().getBlockY() != BestLobbyPlugin.HEIGHT_SETTINGS_Y
                || event.getClickedBlock().getLocation().getBlockZ() != BestLobbyPlugin.HEIGHT_SETTINGS_Z
                || event.getInteractionPoint().getY() >= BestLobbyPlugin.HEIGHT_SETTINGS_Y + 0.64)
            return;
        double interactHeight = event.getInteractionPoint().getY();

        event.getPlayer().removeScoreboardTag("MLG_HEIGHT_0");
        event.getPlayer().removeScoreboardTag("MLG_HEIGHT_1");
        event.getPlayer().removeScoreboardTag("MLG_HEIGHT_2");

        if (interactHeight < BestLobbyPlugin.HEIGHT_SETTINGS_Y + 0.43) {
            lines = MlgUtil.generateSign("MLG_HEIGHT_2");
            event.getPlayer().addScoreboardTag("MLG_HEIGHT_2");
        } else if (interactHeight < BestLobbyPlugin.HEIGHT_SETTINGS_Y + 0.54) {
            lines = MlgUtil.generateSign("MLG_HEIGHT_1");
            event.getPlayer().addScoreboardTag("MLG_HEIGHT_1");
        } else if (interactHeight < BestLobbyPlugin.HEIGHT_SETTINGS_Y + 0.64) {
            lines = MlgUtil.generateSign("MLG_HEIGHT_0");
            event.getPlayer().addScoreboardTag("MLG_HEIGHT_0");
        }
        event.getPlayer().sendSignChange(event.getClickedBlock().getLocation(), lines);
    }

    public static void displaySettings(Player p) {
        Location inv_settings_sign = new Location(p.getWorld(),
                BestLobbyPlugin.INV_SETTINGS_X,
                BestLobbyPlugin.INV_SETTINGS_Y,
                BestLobbyPlugin.INV_SETTINGS_Z
        );

        Location height_settings_sign = new Location(p.getWorld(),
                BestLobbyPlugin.HEIGHT_SETTINGS_X,
                BestLobbyPlugin.HEIGHT_SETTINGS_Y,
                BestLobbyPlugin.HEIGHT_SETTINGS_Z
        );

        Location start_sign = new Location(p.getWorld(),
                BestLobbyPlugin.PLAY_SIGN_X,
                BestLobbyPlugin.PLAY_SIGN_Y,
                BestLobbyPlugin.PLAY_SIGN_Z
        );

        if (p.getScoreboardTags().contains("MLG_INV_0")) {
            p.sendSignChange(inv_settings_sign, MlgUtil.generateSign("MLG_INV_0"));
        } else if (p.getScoreboardTags().contains("MLG_INV_1")) {
            p.sendSignChange(inv_settings_sign, MlgUtil.generateSign("MLG_INV_1"));
        } else if (p.getScoreboardTags().contains("MLG_INV_2")) {
            p.sendSignChange(inv_settings_sign, MlgUtil.generateSign("MLG_INV_2"));
        }
        if (p.getScoreboardTags().contains("MLG_HEIGHT_0")) {
            p.sendSignChange(height_settings_sign, MlgUtil.generateSign("MLG_HEIGHT_0"));
        } else if (p.getScoreboardTags().contains("MLG_HEIGHT_1")) {
            p.sendSignChange(height_settings_sign, MlgUtil.generateSign("MLG_HEIGHT_1"));
        } else if (p.getScoreboardTags().contains("MLG_HEIGHT_2")) {
            p.sendSignChange(height_settings_sign, MlgUtil.generateSign("MLG_HEIGHT_2"));
        }
        ArrayList<Component> lines = new ArrayList<>(Arrays.asList(Component.empty(),
                Component.text(ChatColor.GREEN + "" + ChatColor.BOLD + "START!"),
                Component.empty(), Component.empty()));
        p.sendSignChange(start_sign, lines);
    }
}
