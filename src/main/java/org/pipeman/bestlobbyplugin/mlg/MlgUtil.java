package org.pipeman.bestlobbyplugin.mlg;

import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.Arrays;

public class MlgUtil {
    static ArrayList<Component> generateSign(String setting) {
        if (setting.startsWith("MLG_INV_")) {
            ArrayList<Component> lines = new ArrayList<>(Arrays.asList(
                    Component.text(ChatColor.GREEN + "" + ChatColor.BOLD + "Bucket:"),
                    Component.text(ChatColor.WHITE + "Inventory"),
                    Component.text(ChatColor.WHITE + "Hotbar"),
                    Component.text(ChatColor.WHITE + "1st hotbarslot")
            ));

            switch (setting) {
                case "MLG_INV_0":
                    lines.set(1, Component.text(ChatColor.WHITE + ">Inventory<"));
                    return lines;

                case "MLG_INV_1":
                    lines.set(2, Component.text(ChatColor.WHITE + ">Hotbar<"));
                    return lines;

                case "MLG_INV_2":
                    lines.set(3, Component.text(ChatColor.WHITE + ">1st hotbarslot<"));
                    return lines;
            }
        } else {
            ArrayList<Component> lines = new ArrayList<>(Arrays.asList(
                    Component.text(ChatColor.GREEN + "" + ChatColor.BOLD + "Height:"),
                    Component.text(ChatColor.WHITE + "20"),
                    Component.text(ChatColor.WHITE + "50"),
                    Component.text(ChatColor.WHITE + "RANDOM")
            ));

            switch (setting) {
                case "MLG_HEIGHT_0":
                    lines.set(1, Component.text(ChatColor.WHITE + ">20<"));
                    return lines;

                case "MLG_HEIGHT_1":
                    lines.set(2, Component.text(ChatColor.WHITE + ">50<"));
                    return lines;

                case "MLG_HEIGHT_2":
                    lines.set(3, Component.text(ChatColor.WHITE + ">RANDOM<"));
                    return lines;
            }
        }
        return new ArrayList<>();
    }
}
