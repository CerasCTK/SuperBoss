package net.ceras.superboss.gui;

import net.ceras.superboss.SuperBoss;
import net.ceras.superboss.util.ColorManager;
import net.ceras.superboss.util.InventoryBuilder;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BossTrackerMenu {
    private SuperBoss main;

    public BossTrackerMenu(SuperBoss main) {
        this.main = main;
    }

    public void openInventory(Player player) {
        Inventory mainMenu = new InventoryBuilder(main.getFileHandler().getMenuSize(),
                main.getFileHandler().getBossTrackerTitle()).build();

        int bossCount = 1;

        for(UUID uuid : main.getBoss().getBossData().keySet()) {
            ItemStack itemStack = new ItemStack(Material.DRAGON_EGG);

            ItemMeta itemMeta = itemStack.getItemMeta();

            itemMeta.setDisplayName(ChatColor.BOLD + "BOSS #" + bossCount);

            List<String> arrayList = new ArrayList<>();
            arrayList.add("&7&l- CORDS: &e" +
                    String.valueOf(main.getBoss().getBossData().get(uuid).getBoss().getLocation().getX()) +
                    String.valueOf(main.getBoss().getBossData().get(uuid).getBoss().getLocation().getX()) +
                    String.valueOf(main.getBoss().getBossData().get(uuid).getBoss().getLocation().getX()));

            arrayList.add("&7&l- WORD: &e" + main.getBoss().getBossData().get(uuid).getBoss().getWorld().getName());

            itemMeta.setLore(ColorManager.colorize(arrayList));
            itemStack.setItemMeta(itemMeta);

            bossCount++;

            mainMenu.addItem(itemStack);

            main.getBoss().getBossData().get(uuid).setBossItemStack(itemStack);

            System.out.println(main.getBoss().getBossData().get(uuid).getBoss());
        }

        player.openInventory(mainMenu);
    }
}
