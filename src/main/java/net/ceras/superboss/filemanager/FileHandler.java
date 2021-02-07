package net.ceras.superboss.filemanager;

import net.ceras.superboss.SuperBoss;
import net.ceras.superboss.boss.BossDetail;
import net.ceras.superboss.top.TopDetail;
import net.ceras.superboss.util.ColorManager;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

public class FileHandler {
    private SuperBoss main;

    private Map<String, BossDetail> bossCache = new HashMap();
    private ItemStack spawnBlockType;
    private ItemStack bossTrackerNameItem;
    private ItemStack bossItemsItem;
    private Map<String, String> spawnBlockData;
    private List<String> defeatedGlobalMessage;
    private List<String> defeatedPlayerMessage;
    private List<String> radius;
    private String titleName;
    private String bossTrackerName;
    private String bossItemsName;
    private String bossItemsTitle;
    private String bossTrackerTitle;
    private int menuSize;
    private int bossTrackerSlot;
    private int bossItemsSlot;

    public FileHandler(SuperBoss main) {
        this.main = main;
        this.initiateCache();
    }

    public Map<String, BossDetail> getBossCache() {
        return bossCache;
    }

    public ItemStack getSpawnBlockType() {
        return spawnBlockType;
    }

    public ItemStack getBossTrackerNameItem() {
        return bossTrackerNameItem;
    }

    public ItemStack getBossItemsItem() {
        return bossItemsItem;
    }

    public Map<String, String> getSpawnBlockData() {
        return spawnBlockData;
    }

    public List<String> getDefeatedGlobalMessage() {
        return defeatedGlobalMessage;
    }

    public List<String> getDefeatedPlayerMessage() {
        return defeatedPlayerMessage;
    }

    public List<String> getRadius() {
        return radius;
    }

    public String getTitleName() {
        return titleName;
    }

    public String getBossTrackerName() {
        return bossTrackerName;
    }

    public String getBossItemsName() {
        return bossItemsName;
    }

    public String getBossItemsTitle() {
        return bossItemsTitle;
    }

    public String getBossTrackerTitle() {
        return bossTrackerTitle;
    }

    public int getMenuSize() {
        return menuSize;
    }

    public int getBossTrackerSlot() {
        return bossTrackerSlot;
    }

    public int getBossItemsSlot() {
        return bossItemsSlot;
    }

    public void initiateCache() {
        spawnBlockData = new HashMap<>();
        bossItemsTitle = main.getConfig().getString("gui.items.bossItems.title");
        bossTrackerTitle = main.getConfig().getString("gui.items.bossTracker.title");

        titleName = main.getConfig().getString("gui.title");
        menuSize = main.getConfig().getInt("gui.size");
        bossTrackerName = main.getConfig().getString("gui.items.bossTracker.name");
        bossTrackerSlot = main.getConfig().getInt("gui.items.bossTracker.slot");

        bossItemsName = main.getConfig().getString("gui.items.bossItems.name");
        bossItemsSlot = main.getConfig().getInt("gui.items.bossItems.slot");

        String bossTrackerItemName = main.getConfig().getString("gui.items.bossTracker.item");
        List<String> bossTrackerLore = main.getConfig().getStringList("gui.items.bossTracker.lore");

        String bossItemsItemName = main.getConfig().getString("gui.items.bossItems.item");
        List<String> bossItemsLore = main.getConfig().getStringList("gui.items.bossItems.lore");

        try {
            bossItemsItem = new ItemStack(Material.valueOf(bossItemsItemName));
        } catch (IllegalArgumentException itemNotFound) {
            System.out.println(main.getMessageHandler().getPrefix() + ChatColor.YELLOW + bossItemsItemName +
                    ChatColor.GRAY + " cannot be found. Please check the material again." +
                    ChatColor.RED + " - setting item as STONE.");

            bossItemsItem = new ItemStack(Material.STONE);
        } finally {
            ItemMeta itemMeta = bossItemsItem.getItemMeta();

            itemMeta.setDisplayName(ColorManager.colorize(bossItemsName));

            itemMeta.setLore(ColorManager.colorize(bossItemsLore));

            bossItemsItem.setItemMeta(itemMeta);
        }

        String spawnBlockItemName = main.getConfig().getString("settings.spawnBlock.name");
        List<String> spawnBlockItemLore = ColorManager.colorize(main.getConfig().getStringList("settings.spawnBlock.lore"));
        String spawnBlockItemMaterial = main.getConfig().getString("settings.spawnBlock.type");

        defeatedGlobalMessage = main.getFileManager().getModifyMessageData().getStringList("messages.defeatedGlobal");
        defeatedPlayerMessage = main.getFileManager().getModifyMessageData().getStringList("messages.defeatedPlayer");

        radius = Arrays.asList(main.getConfig().getString("settings.talkRange").split(", "));

        try {
            spawnBlockType = new ItemStack(Material.valueOf(spawnBlockItemMaterial));
        } catch (IllegalArgumentException itemNotFound) {
            System.out.println(main.getMessageHandler().getPrefix() + ChatColor.YELLOW + spawnBlockItemMaterial +
                    ChatColor.GRAY + " cannot be found. Please check the material again." +
                    ChatColor.RED + " - setting item as STONE.");

            spawnBlockType = new ItemStack(Material.STONE);
        } finally {
            ItemMeta itemMeta = spawnBlockType.getItemMeta();

            itemMeta.setDisplayName(ColorManager.colorize(spawnBlockItemName));
            itemMeta.setLore(ColorManager.colorize(spawnBlockItemLore));

            spawnBlockType.setItemMeta(itemMeta);
        }

        try {
            for (String values : main.getFileManager().getModifyBlockData().getConfigurationSection("cords").getKeys(false)) {
                spawnBlockData.put(values, main.getFileManager().getModifyBlockData().getString("cords." + values));
            }
        } catch (NullPointerException notGenerated) {

        }

        System.out.println(main.getMessageHandler().getPrefix() + ChatColor.YELLOW + spawnBlockData.size() +
                ChatColor.GRAY + " boss spawn blocks loaded up.");

        for (String value : main.getFileManager().getModifyBoss().getConfigurationSection("bosses").getKeys(false)) {
            String name = main.getFileManager().getModifyBoss().getString("bosses." + value + ".name");
            String rawName = value;
            String entityType = main.getFileManager().getModifyBoss().getString("bosses." + value + ".type");
            int aliveTimeout = main.getFileManager().getModifyBoss().getInt("bosses." + value + ".aliveTimeout");
            int spawnDamage = main.getFileManager().getModifyBoss().getInt("bosses." + value + ".spawnDamage");

            double speed = main.getFileManager().getModifyBoss().getDouble("bosses." + value + ".speed");

            int health = main.getFileManager().getModifyBoss().getInt("bosses." + value + ".health");

            String skill = main.getFileManager().getModifyBoss().getString("bosses." + value + ".skill");

            List<String> skillList = Arrays.asList(skill.split(", "));

            String spawnItemName = main.getFileManager().getModifyBoss().getString("bosses." + value + ".spawnItem.name");

            String spawnItemTypeName = main.getFileManager().getModifyBoss().getString("bosses." + value + ".spawnItem.type");
            List<String> lore = ColorManager.colorize(main.getFileManager().getModifyBoss().getStringList("bosses." + value + ".spawnItem.lore"));
            ItemStack spawnItemType = null;

            List<String> randomBossMessages = main.getFileManager().getModifyBoss().getStringList("bosses." + value + ".messages");

            List<String> spawnMessage = main.getFileManager().getModifyBoss().getStringList("bosses." + value + ".spawnMessage");

            try {
                spawnItemType = new ItemStack(Material.valueOf(spawnItemTypeName));
            } catch (IllegalArgumentException itemNotFound) {
                System.out.println(main.getMessageHandler().getPrefix() + ChatColor.YELLOW + spawnItemTypeName +
                        ChatColor.GRAY + " cannot be found. Please check the material again." +
                        ChatColor.RED + " - setting item as STONE.");

                spawnItemType = new ItemStack(Material.STONE);
            } finally {
                ItemMeta itemMeta = spawnItemType.getItemMeta();

                itemMeta.setDisplayName(ColorManager.colorize(spawnItemName));
                itemMeta.setLore(lore);

                spawnItemType.setItemMeta(itemMeta);
            }

            String helmetName = main.getFileManager().getModifyBoss().getString("bosses." + value + ".equipment.helmet");
            String chestPlateName = main.getFileManager().getModifyBoss().getString("bosses." + value + ".equipment.chestPlate");
            String leggingsName = main.getFileManager().getModifyBoss().getString("bosses." + value + ".equipment.leggings");
            String bootsName = main.getFileManager().getModifyBoss().getString("bosses." + value + ".equipment.boots");

            String leftWeaponName = main.getFileManager().getModifyBoss().getString("bosses." + value + ".equipment.leftHand");
            String rightWeaponName = main.getFileManager().getModifyBoss().getString("bosses." + value + ".equipment.rightHand");

            ItemStack helmet, chestPlate, leggings, boots, leftWeapon, rightWeapon;

            try {
                leftWeapon = new ItemStack(Material.valueOf(leftWeaponName));
            } catch (IllegalArgumentException itemNotFound) {
                System.out.println(main.getMessageHandler().getPrefix() + ChatColor.YELLOW + leftWeaponName +
                        ChatColor.GRAY + " cannot be found. Please check the material again." +
                        ChatColor.RED + " - setting item as AIR.");

                leftWeapon = new ItemStack(Material.AIR);
            }

            try {
                rightWeapon = new ItemStack(Material.valueOf(rightWeaponName));
            } catch (IllegalArgumentException itemNotFound) {

                System.out.println(main.getMessageHandler().getPrefix() + ChatColor.YELLOW + rightWeaponName +
                        ChatColor.GRAY + " cannot be found. Please check the material again." +
                        ChatColor.RED + " - setting item as AIR.");

                rightWeapon = new ItemStack(Material.AIR);
            }

            try {
                helmet = new ItemStack(Material.valueOf(helmetName));
            } catch (IllegalArgumentException itemNotFound) {
                System.out.println(main.getMessageHandler().getPrefix() + ChatColor.YELLOW + helmetName +
                        ChatColor.GRAY + " cannot be found. Please check the material again." +
                        ChatColor.RED + " - setting item as AIR.");

                helmet = new ItemStack(Material.AIR);
            }

            try {
                chestPlate = new ItemStack(Material.valueOf(chestPlateName));
            } catch (IllegalArgumentException itemNotFound) {
                System.out.println(main.getMessageHandler().getPrefix() + ChatColor.YELLOW + chestPlateName +
                        ChatColor.GRAY + " cannot be found. Please check the material again." +
                        ChatColor.RED + " - setting item as AIR.");

                chestPlate = new ItemStack(Material.AIR);
            }

            try {
                leggings = new ItemStack(Material.valueOf(leggingsName));
            } catch (IllegalArgumentException itemNotFound) {
                System.out.println(main.getMessageHandler().getPrefix() + ChatColor.YELLOW + leggingsName +
                        ChatColor.GRAY + " cannot be found. Please check the material again." +
                        ChatColor.RED + " - setting item as AIR.");

                leggings = new ItemStack(Material.AIR);
            }

            try {
                boots = new ItemStack(Material.valueOf(bootsName));
            } catch (IllegalArgumentException itemNotFound) {
                System.out.println(main.getMessageHandler().getPrefix() + ChatColor.YELLOW + bootsName +
                        ChatColor.GRAY + " cannot be found. Please check the material again." +
                        ChatColor.RED + " - setting item as AIR.");

                boots = new ItemStack(Material.AIR);
            }

            Map<String, TopDetail> topDetailMap = new LinkedHashMap<>();

            for (String prize : main.getFileManager().getModifyBoss().getConfigurationSection("bosses." + value
                    + ".prizes").getKeys(false)) {
                List<String> command = main.getFileManager().getModifyBoss()
                        .getStringList("bosses." + value + ".prizes." + prize + ".command");

                List<String> messagePlayer = ColorManager.colorize(main.getFileManager().getModifyBoss()
                        .getStringList("bosses." + value + ".prizes." + prize + ".messagePlayer"));

                topDetailMap.put(prize, new TopDetail(command, messagePlayer));
            }

            bossCache.put(value, new BossDetail(name, entityType, spawnItemName, rawName, aliveTimeout, health, spawnDamage, speed, helmet, chestPlate, leggings, boots,
                    spawnItemType, leftWeapon, rightWeapon, skillList, randomBossMessages, spawnMessage, topDetailMap));

            System.out.println(ChatColor.YELLOW + value.toUpperCase() + ChatColor.GRAY + " has been loaded up.");
        }
    }
}
