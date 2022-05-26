package org.pipeman.bestlobbyplugin;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.ipvp.canvas.Menu;
import org.ipvp.canvas.slot.Slot;
import org.ipvp.canvas.type.ChestMenu;

public class CompassGui2 implements Listener {
    private static final Menu menu = createMenu();

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        if (event.getMaterial() != Material.COMPASS) return;
        event.setCancelled(true);

        menu.open(event.getPlayer());
    }

    private static Menu createMenu() {
        ChestMenu menu = ChestMenu.builder(6)
                .title("Server Menu")
                .build();

        ItemStack stack = createPlayerHead("LetsTim");
        menu.getSlot(1, 5).setItem(setName(stack, "Support"));
        menu.getSlot(1, 5).setClickHandler((player, clickInformation) -> {
            player.sendMessage(
                    ChatColor.YELLOW + "[Support] " + ChatColor.GRAY +  "LetsTim wartet mit freuden darauf, dir bei " +
                            "deinen Problemen zu helfen! Discord: " + ChatColor.WHITE + "LetsTim#1012");
            clickInformation.getClickedMenu().close(player);
        });

        addItem(menu.getSlot(3, 3), "Survival", ci(Material.DIAMOND_SWORD, "Survival"));
        addItem(menu.getSlot(3, 4), "PartyGames", ci(Material.CAKE, "Party Games"));
        addItem(menu.getSlot(3, 5), "rst", setName(createPlayerHead("rstRichard"), "Rst's Server"));
        addItem(menu.getSlot(3, 6), "Event", ci(Material.FIREWORK_ROCKET, "Events"));
        addItem(menu.getSlot(3, 7), "StreamerSagt", setName(createPlayerHead("Brian_FNA"), "Streamer Sagt"));

        addItem(menu.getSlot(4, 3), "FFA", ci(Material.IRON_AXE, "FFA"));
        addItem(menu.getSlot(4, 4), "Bauserver", ci(Material.GRASS_BLOCK, "Bauserver"));

        addItem(menu.getSlot(4, 6), "mapmaking", ci(Material.MAP, "Mapmaking"));
        addItem(menu.getSlot(4, 7), "te", ci(Material.POPPY, "???"));

        // Werbung
        setSlotWithLink(menu.getSlot(6, 2), Material.CLOCK, "TrashTok",
                "Schau doch gerne auf unserem TikTok Profil vorbei um tolle und lustige Videos zu sehen.",
                "https://www.tiktok.com/@letstim.net?is_from_webapp=1&sender_device=pc");

        setSlotWithLink(menu.getSlot(6, 3), Material.MUSIC_DISC_CHIRP,"Instagram",
                "Schau doch gerne auf unserm Instagram Profil vorbei um tolle Bilder und Storys zu erhalten.",
                "https://instagram.com/letstimnet?igshid=YmMyMTA2M2Y=");

        setSlotWithLink(menu.getSlot(6, 5), Material.COBWEB, "Website",
                "Schau doch gerne auf unserer Website vorbei um alles rund um das Netzwerk zu erfahren.",
                "https://www.letstim.net/");

        setSlotWithLink(menu.getSlot(6, 7), Material.PINK_TERRACOTTA, "YouTube",
                "Schau doch gerne auf unserem YouTube Kanal vorbei um hilfreiche Tutorials zu sehen",
                "https://youtube.com/channel/UC9cQC1IbYoNILhQrmXg-4mw");

        setSlotWithLink(menu.getSlot(6, 8), Material.GRAY_DYE, "Discord",
                "Schau doch gerne auf unserem Discord Server vorbei um Infos & Updates zu erhalten und mit der Community zu chatten.",
                "https://discord.gg/khYfqAHxMd");

        return menu;

        // case DIAMOND_SWORD -> sendPlayerToServer(p, "Survival");
        //case CAKE -> sendPlayerToServer(p, "PartyGames");
        //case PLAYER_HEAD -> {
        //    sendPlayerToServer(p, "");
        //    sendPlayerToServer(p, "");
        //}
        //case FIREWORK_ROCKET -> sendPlayerToServer(p, "Event");
        //case IRON_AXE -> sendPlayerToServer(p, "FFA");
        //case GRASS_BLOCK -> sendPlayerToServer(p, "Bauserver");
        //case MAP -> sendPlayerToServer(p, "mapmaking");
        //case POPPY -> sendPlayerToServer(p, "te");
        //case CLOCK -> sendPlayerToServer(p, "");
        //case MUSIC_DISC_CHIRP -> sendPlayerToServer(p, "");
        //case COBWEB -> sendPlayerToServer(p, "");
        //case PINK_TERRACOTTA -> sendPlayerToServer(p, "");
        //case GRAY_DYE -> sendPlayerToServer(p, "");
    }

    private static void setSlotWithLink(Slot slot, Material mat, String title, String message, String url) {
        ItemStack out = new ItemStack(mat);
        ItemMeta itemMeta = out.getItemMeta();
        itemMeta.displayName(Component.text(title));
        out.setItemMeta(itemMeta);
        slot.setItem(out);
        Component comp =
                Component.text(ChatColor.YELLOW + "[Werbung] " + ChatColor.GRAY + title + ": " + message)
                        .clickEvent(ClickEvent.clickEvent(ClickEvent.Action.OPEN_URL, url));
        slot.setClickHandler((player, clickInformation) -> {
            player.sendMessage(comp);
            clickInformation.getClickedMenu().close(player);
        });
    }

    private static ItemStack ci(Material mat, String title) {
        ItemStack out = new ItemStack(mat);
        ItemMeta itemMeta = out.getItemMeta();
        itemMeta.displayName(Component.text(title));
        out.setItemMeta(itemMeta);
        return out;
    }

    private static ItemStack setName(ItemStack stack, String name) {
        ItemMeta itemMeta = stack.getItemMeta();
        itemMeta.displayName(Component.text(name));
        stack.setItemMeta(itemMeta);
        return stack;
    }

    private static ItemStack createPlayerHead(String playerName) {
        ItemStack item = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta skullMeta = (SkullMeta) item.getItemMeta();
        skullMeta.setOwningPlayer(Bukkit.getOfflinePlayer(playerName));
        item.setItemMeta(skullMeta);
        return item;
    }

    private static void addItem(Slot slot, String servername, ItemStack stack) {
        slot.setItem(stack);
        slot.setClickHandler((player, clickInformation) -> sendPlayerToServer(player, servername));
    }

    private static void sendPlayerToServer(Player p, String server) {
        BestLobbyPlugin.logger.info("Sending " + p.getName() + " to server " + server + ".");
        ByteArrayDataOutput dataOutput = ByteStreams.newDataOutput();
        dataOutput.writeUTF("Connect");
        dataOutput.writeUTF(server);
        p.sendPluginMessage(BestLobbyPlugin.plugin, "BungeeCord", dataOutput.toByteArray());
    }
}
