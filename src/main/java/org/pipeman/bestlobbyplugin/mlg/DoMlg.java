package org.pipeman.bestlobbyplugin.mlg;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.pipeman.bestlobbyplugin.BestLobbyPlugin;

public class DoMlg implements Listener {

    @EventHandler
    public void onStartSignClicked(PlayerInteractEvent event) {
        if (event.getClickedBlock() == null) return;
        if (event.getClickedBlock().getLocation().getBlockX() != BestLobbyPlugin.PLAY_SIGN_X
                || event.getClickedBlock().getLocation().getBlockY() != BestLobbyPlugin.PLAY_SIGN_Y
                || event.getClickedBlock().getLocation().getBlockZ() != BestLobbyPlugin.PLAY_SIGN_Z)
            return;

        Player p = event.getPlayer();

        for (ItemStack s : p.getInventory()) {
            if (s == null) continue;
            if (s.isSimilar(new ItemStack(Material.WATER_BUCKET)) || s.isSimilar(new ItemStack(Material.BUCKET))) {
                p.getInventory().remove(s);
            }
        }

        if (p.getScoreboardTags().contains("MLG_INV_0")) {
            int slotId = 9 + (int)(Math.random() * ((35 - 9) + 1));
            p.getInventory().setItem(slotId, new ItemStack(Material.WATER_BUCKET));

        } else if (p.getScoreboardTags().contains("MLG_INV_1")) {
            int slotId = (int)(Math.random() * ((8) + 1));
            p.getInventory().setItem(slotId, new ItemStack(Material.WATER_BUCKET));
        } else if (p.getScoreboardTags().contains("MLG_INV_2")) {
            int slotId = 0;
            p.getInventory().setItem(slotId, new ItemStack(Material.WATER_BUCKET));
        }

        if (p.getScoreboardTags().contains("MLG_HEIGHT_0")) {
            p.teleport(p.getLocation().add(0, 20, 0));
        } else if (p.getScoreboardTags().contains("MLG_HEIGHT_1")) {
            p.teleport(p.getLocation().add(0, 50, 0));
        } else if (p.getScoreboardTags().contains("MLG_HEIGHT_2")) {
            int additionalHeight = 20 + (int)(Math.random() * ((80 - 20) + 1));
            p.teleport(p.getLocation().add(0, additionalHeight, 0));
        }
    }
}
