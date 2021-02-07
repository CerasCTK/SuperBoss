package net.ceras.superboss.gui.listener;

import net.ceras.superboss.SuperBoss;
import net.ceras.superboss.gui.BossTrackerMenu;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class BossTrackerMenuListener implements Listener {
    private SuperBoss main;

    public BossTrackerMenuListener(SuperBoss main) {
        this.main = main;
    }

    @EventHandler
    public void onClick (InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        try {
            boolean menu = ChatColor.translateAlternateColorCodes('&',
                    event.getView().getTitle()).equals(ChatColor.translateAlternateColorCodes('&',
                    main.getFileHandler().getBossTrackerTitle()));

            if (menu) {
                if (event.getCurrentItem() != null) {
                    event.setCancelled(true);

                    ItemStack itemStack = event.getCurrentItem();

                    for (UUID uuid : main.getBoss().getBossData().keySet()) {
                        if (itemStack.equals(main.getBoss().getBossData().get(uuid).getBossItemStack())) {
                            if (event.getClick() == ClickType.RIGHT) {
                                main.getBoss().getBossData().get(uuid).getBoss().remove();
                                main.getBoss().getBossData().remove(uuid);
                                new BossTrackerMenu(main).openInventory(player);
                                break;
                            } else if (event.getClick() == ClickType.LEFT) {
                                Location bossLocation = main.getBoss().getBossData().get(uuid).getBoss().getLocation();

                                player.teleport(bossLocation);
                            }
                        }
                    }
                }
            }
        } catch (NullPointerException exception) {

        }
    }
}
