package net.ceras.superboss.gui;

import net.ceras.superboss.SuperBoss;
import net.ceras.superboss.boss.BossDetail;
import net.ceras.superboss.util.InventoryBuilder;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class BossItemsMenu {
    private SuperBoss main;

    public BossItemsMenu(SuperBoss main) {
        this.main = main;
    }

    public void openInventory(Player player) {
        Inventory menu = new InventoryBuilder(main.getFileHandler().getMenuSize(),
                main.getFileHandler().getBossItemsTitle()).build();

        menu.addItem(main.getFileHandler().getSpawnBlockType());

        for (BossDetail bd : main.getFileHandler().getBossCache().values()) {
            menu.addItem(bd.getSpawnItemType());
        }

        player.openInventory(menu);
    }
}
