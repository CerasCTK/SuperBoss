package net.ceras.superboss.gui.listener;

import net.ceras.superboss.SuperBoss;
import net.ceras.superboss.gui.BossItemsMenu;
import net.ceras.superboss.gui.BossTrackerMenu;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class MainMenuListener implements Listener {
    private SuperBoss main;

    public MainMenuListener(SuperBoss main) {
        this.main = main;
    }

    @EventHandler
    public void onClick (InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        try {
            boolean menu = ChatColor.translateAlternateColorCodes('&',
                    event.getView().getTitle()).equals(ChatColor.translateAlternateColorCodes('&',
                    main.getFileHandler().getTitleName()));

            if (menu) {
                if (event.getCurrentItem() != null) {
                    event.setCancelled(true);

                    ItemStack itemStack = event.getCurrentItem();

                    if (itemStack.equals(main.getFileHandler().getBossItemsItem())) {
                        new BossItemsMenu(main).openInventory(player);
                    }
                    else if (itemStack.equals(main.getFileHandler().getBossTrackerNameItem())) {
                        new BossTrackerMenu(main).openInventory(player);
                    }
                }
            }
        } catch(NullPointerException exception) {

        }
    }
}
