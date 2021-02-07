package net.ceras.superboss.boss;

import net.ceras.superboss.top.TopDetail;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Map;

public class BossDetail {
    private String name;
    private String entityType;
    private String spawnItemName;
    private String rawName;
    private int aliveTimeout;
    private int health;
    private int spawnDamage;
    private double speed;
    private ItemStack helmet;
    private ItemStack chestPlate;
    private ItemStack leggings;
    private ItemStack boots;
    private ItemStack spawnItemType;
    private ItemStack leftWeapon;
    private ItemStack rightWeapon;
    private List<String> skill;
    private List<String> messages;
    private List<String> spawnMessage;
    private Map<String, TopDetail> topDetailMap;

    public BossDetail(String name, String entityType, String spawnItemName, String rawName, int aliveTimeout, int health, int spawnDamage, double speed,
                      ItemStack helmet, ItemStack chestPlate, ItemStack leggings, ItemStack boots, ItemStack spawnItemType, ItemStack leftWeapon,
                      ItemStack rightWeapon, List<String> skill, List<String> messages, List<String> spawnMessage, Map<String, TopDetail> topDetailMap) {
        this.name = name;
        this.entityType = entityType;
        this.spawnItemName = spawnItemName;
        this.rawName = rawName;
        this.aliveTimeout = aliveTimeout;
        this.health = health;
        this.spawnDamage = spawnDamage;
        this.speed = speed;
        this.helmet = helmet;
        this.chestPlate = chestPlate;
        this.leggings = leggings;
        this.boots = boots;
        this.spawnItemType = spawnItemType;
        this.leftWeapon = leftWeapon;
        this.rightWeapon = rightWeapon;
        this.skill = skill;
        this.messages = messages;
        this.spawnMessage = spawnMessage;
        this.topDetailMap = topDetailMap;
    }

    public String getName() {
        return name;
    }

    public String getEntityType() {
        return entityType;
    }

    public String getSpawnItemName() {
        return spawnItemName;
    }

    public String getRawName() {
        return rawName;
    }

    public int getAliveTimeout() {
        return aliveTimeout;
    }

    public int getHealth() {
        return health;
    }

    public int getSpawnDamage() {
        return spawnDamage;
    }

    public double getSpeed() {
        return speed;
    }

    public ItemStack getHelmet() {
        return helmet;
    }

    public ItemStack getChestPlate() {
        return chestPlate;
    }

    public ItemStack getLeggings() {
        return leggings;
    }

    public ItemStack getBoots() {
        return boots;
    }

    public ItemStack getSpawnItemType() {
        return spawnItemType;
    }

    public ItemStack getLeftWeapon() {
        return leftWeapon;
    }

    public ItemStack getRightWeapon() {
        return rightWeapon;
    }

    public List<String> getSkill() {
        return skill;
    }

    public List<String> getMessages() {
        return messages;
    }

    public List<String> getSpawnMessage() {
        return spawnMessage;
    }

    public Map<String, TopDetail> getTopDetailMap() {
        return topDetailMap;
    }
}
