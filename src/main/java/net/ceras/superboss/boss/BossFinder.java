package net.ceras.superboss.boss;

import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitTask;

import java.util.*;

public class BossFinder {
    private BossDetail bd;
    private Entity boss;
    private ItemStack bossItemStack;
    private int activityTimer;
    private long oldActivityTimer;
    private BukkitTask activityTask;
    private Map<UUID, Double> damagers = new LinkedHashMap<>();

    private List<UUID> nearbyPlayers = new ArrayList<>();

    public BossDetail getBd() {
        return bd;
    }

    public void setBd(BossDetail bd) {
        this.bd = bd;
    }

    public Entity getBoss() {
        return boss;
    }

    public void setBoss(Entity boss) {
        this.boss = boss;
    }

    public ItemStack getBossItemStack() {
        return bossItemStack;
    }

    public void setBossItemStack(ItemStack bossItemStack) {
        this.bossItemStack = bossItemStack;
    }

    public int getActivityTimer() {
        return activityTimer;
    }

    public void setActivityTimer(int activityTimer) {
        this.activityTimer = activityTimer;
    }

    public long getOldActivityTimer() {
        return oldActivityTimer;
    }

    public void setOldActivityTimer(long oldActivityTimer) {
        this.oldActivityTimer = oldActivityTimer;
    }

    public BukkitTask getActivityTask() {
        return activityTask;
    }

    public void setActivityTask(BukkitTask activityTask) {
        this.activityTask = activityTask;
    }

    public Map<UUID, Double> getDamagers() {
        return damagers;
    }

    public void setDamagers(Map<UUID, Double> damagers) {
        this.damagers = damagers;
    }

    public List<UUID> getNearbyPlayers() {
        return nearbyPlayers;
    }
}
