package org.pipeman.bestlobbyplugin;

import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scoreboard.Scoreboard;

public class PlayerHandling implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
//        String pName = event.getPlayer().getName();
//        if (!pName.matches("^([a-z]|[A-Z]|_|[0-9])+$")
//                || pName.length() < 3 || pName.length() > 16) {
//            event.getPlayer().kick(Component.text(ChatColor.RED +
//                    "Your name contains invalid characters! \n"
//                    + "If you believe this is wrong, open a ticket on our Discord-Server"));
//
//            return;
//        }

        Scoreboard sc = event.getPlayer().getScoreboard();
        if (sc.getTeam("hub") == null) {
            sc.registerNewTeam("hub");
        }
        sc.getTeam("hub").addPlayer(event.getPlayer());
        ItemStack newStack = new ItemStack(Material.COMPASS);
        ItemMeta meta = newStack.getItemMeta();
        meta.displayName(Component.text("Server Selector"));
        newStack.setItemMeta(meta);
        event.getPlayer().getInventory().setItem(0, newStack);
    }

//    @EventHandler
//    public void onPlayerMove(PlayerMoveEvent event) {
//        if (event.hasChangedBlock() && event.getTo().toVector().isInAABB(BestLobbyPlugin.PLATFORM_1, BestLobbyPlugin.PLATFORM_2)) {
//            MlgSettings.displaySettings(event.getPlayer());
//        }
//    }
}
