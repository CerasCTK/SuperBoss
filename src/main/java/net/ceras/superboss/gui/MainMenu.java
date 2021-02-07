package net.ceras.superboss.gui;

import net.ceras.superboss.SuperBoss;
import net.ceras.superboss.util.InventoryBuilder;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class MainMenu {
    private SuperBoss main;

    public MainMenu(SuperBoss main) {
        this.main = main;
    }

    public void openInventory(Player player) {
        Inventory mainMenu = new InventoryBuilder(main.getFileHandler().getMenuSize(),
                main.getFileHandler().getTitleName())
                .setItem(main.getFileHandler().getBossItemsSlot(), main.getFileHandler().getBossItemsItem())
                .setItem(main.getFileHandler().getBossTrackerSlot(), main.getFileHandler().getBossTrackerNameItem())
                .build();

        player.openInventory(mainMenu);
    }
}
