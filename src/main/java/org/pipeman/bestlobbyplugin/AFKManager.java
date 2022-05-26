package org.pipeman.bestlobbyplugin;

import com.destroystokyo.paper.event.server.ServerTickStartEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.HashMap;

public class AFKManager implements Listener {
    HashMap<Player, Integer> lastActions = new HashMap<>();
    HashMap<Player, Integer> freezeTicks = new HashMap<>();

    @EventHandler
    public void playerMoveEvent(PlayerMoveEvent e) {
        if (e.hasChangedPosition() ||e.hasChangedOrientation()) {
            playerReset(e.getPlayer());
        }
    }

    private void playerReset(Player p) {
        if (lastActions.replace(p, Bukkit.getCurrentTick()) == null) {
            lastActions.put(p, Bukkit.getCurrentTick());
        }
    }

    @EventHandler
    public void tickEvent(ServerTickStartEvent e) {
        lastActions.entrySet().removeIf((entry) -> (!entry.getKey().isOnline()));

        lastActions.forEach((p, lastAction) -> {
            if (Bukkit.getCurrentTick() - lastAction > 1200) {
                freezeTicks.putIfAbsent(p, 0);
                freezeTicks.replace(p, (freezeTicks.get(p) > p.getMaxFreezeTicks() ? 141 : freezeTicks.get(p) + 1));
                p.setFreezeTicks(freezeTicks.get(p));
            }
        });

//            lastActions.forEach((p, lastAction) -> {
//                if (Bukkit.getCurrentTick() - lastAction > 1200) {
//                    freezeTicks.putIfAbsent(p, 0);
//                    freezeTicks.replace(p, (freezeTicks.get(p) > p.getMaxFreezeTicks() ? 141 : freezeTicks.get(p) + 1));
//                    p.setFreezeTicks(freezeTicks.get(p));
//                }
//                if (!p.isOnline()) {
//                    lastActions.remove(p);
//                }
//            });

    }
}
