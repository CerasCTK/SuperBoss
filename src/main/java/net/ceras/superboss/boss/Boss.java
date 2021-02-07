package net.ceras.superboss.boss;

import net.ceras.superboss.SuperBoss;
import net.ceras.superboss.util.ColorManager;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Boss {
    private SuperBoss main;

    private Map<UUID, BossFinder> bossData = new LinkedHashMap();

    public Boss(SuperBoss main) {
        this.main = main;
    }

    public Map<UUID, BossFinder> getBossData() {
        return bossData;
    }

    public Entity spawn(Location loc, BossDetail bd) {
        LivingEntity entity = (LivingEntity) loc.getWorld().spawnEntity(loc, EntityType.valueOf(bd.getEntityType()));

        entity.setCustomNameVisible(true);
        entity.setCustomName(ColorManager.colorize(bd.getName()));
        entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(bd.getHealth());

        entity.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(bd.getSpeed());

        entity.getEquipment().setHelmet(bd.getHelmet());
        entity.getEquipment().setChestplate(bd.getChestPlate());
        entity.getEquipment().setLeggings(bd.getLeggings());
        entity.getEquipment().setBoots(bd.getBoots());

        entity.getEquipment().setItemInMainHand(bd.getRightWeapon());
        entity.getEquipment().setItemInOffHand(bd.getLeftWeapon());
        entity.setInvulnerable(true);

        BossFinder bf = new BossFinder();

        bf.setBd(bd);
        bf.setActivityTimer(bd.getAliveTimeout() * 20);
        bf.setBoss(entity);

        bossData.put(entity.getUniqueId(), bf);

        for (Entity nearEntity : entity.getWorld().getNearbyEntities(entity.getLocation(),
                Integer.valueOf(main.getFileHandler().getRadius().get(0)),
                Integer.valueOf(main.getFileHandler().getRadius().get(1)),
                Integer.valueOf(main.getFileHandler().getRadius().get(2)))) {

            if (nearEntity instanceof Player) {
                for (String message : bd.getSpawnMessage()) {
                    nearEntity.sendMessage(ColorManager.colorize(message));
                }
            }
        }

        for (Entity nearbyEntity : entity.getWorld().getNearbyEntities(entity.getLocation(),10, 10, 10)) {

            if (nearbyEntity instanceof Player) {
                Player player = (Player) nearbyEntity;

                player.damage(bd.getSpawnDamage(), entity);
            }
        }
        return entity;
    }
}
