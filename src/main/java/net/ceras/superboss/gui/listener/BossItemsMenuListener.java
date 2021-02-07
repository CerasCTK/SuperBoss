package net.ceras.superboss.gui.listener;

import net.ceras.superboss.SuperBoss;
import net.ceras.superboss.boss.BossDetail;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class BossItemsMenuListener implements Listener {
    private SuperBoss main;

    public BossItemsMenuListener(SuperBoss main) {
        this.main = main;
    }

    @EventHandler
    public void onClick (InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        try {
            boolean mainMenu = ChatColor.translateAlternateColorCodes('&',
                    event.getView().getTitle()).equals(ChatColor.translateAlternateColorCodes('&',
                    main.getFileHandler().getBossItemsTitle()));

            if (mainMenu) {
                if (event.getCurrentItem() != null) {
                    event.setCancelled(true);

                    ItemStack itemStack = event.getCurrentItem();

                    if (itemStack.equals(main.getFileHandler().getSpawnBlockType())) {
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
                                "boss give %player% block"
                                        .replace("%player%", player.getName()));
                    }

                    for (BossDetail bd : main.getFileHandler().getBossCache().values()) {
                        if (itemStack.equals(bd.getSpawnItemType())) {
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
                                    "boss give %player% %spawn%".replace("%player%", player.getName())
                                            .replace("%spawn%", bd.getRawName()));
                        }
                    }
                }
            }
        }
        catch (NullPointerException exception) {

        }
    }
}
