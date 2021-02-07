package net.ceras.superboss.util;

import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;

public class ColorManager {
    public ColorManager() {
    }

    public static String colorize(String message)
    {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static List<String> colorize(List<String> message)
    {
        List<String> colorizedString = new ArrayList<>();

        for(String listMessage : message)
        {
            colorizedString.add(ChatColor.translateAlternateColorCodes('&', listMessage));
        }

        return colorizedString;
    }
}
