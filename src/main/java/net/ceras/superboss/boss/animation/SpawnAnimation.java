package net.ceras.superboss.boss.animation;

import net.ceras.superboss.SuperBoss;
import net.ceras.superboss.boss.BossDetail;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;

public class SpawnAnimation {
    private SuperBoss main;

    private int count = 0;
    private int jumpHeight = 4;

    public SpawnAnimation(SuperBoss main) {
        this.main = main;
    }

    public void play(Location loc, BossDetail bd) {
        Block enderEgg = loc.getBlock().getRelative(0, 10,0);

        enderEgg.setType(Material.DRAGON_EGG);

        lightningTask(loc, bd);
    }

    private void lightningTask(Location loc, BossDetail bd) {
        Bukkit.getScheduler().runTaskLater(main, new Runnable() {

            public void run() {
                loc.getWorld().strikeLightning(loc);

                for(int i = 2; i <= jumpHeight; i++) {
                    int lastNumber = 0;

                    if(i == jumpHeight) {
                        lastNumber = 10;
                    }
                    spawnTask(loc, i, lastNumber, bd);
                }
            }
        }, 30L);
    }

    private void spawnTask(Location loc, int height, int lastNumber, BossDetail bd) {

        Bukkit.getScheduler().runTaskLater(main, new Runnable() {

            public void run() {
                loc.getBlock().getRelative(0, 1,0).setType(Material.AIR);
                loc.getBlock().getRelative(0, height,0).setType(Material.DRAGON_EGG);
                loc.getBlock().getRelative(0, height - 1,0).setType(Material.BARRIER);
            }
        }, 30L + (height * 4)); // 50L, 60L, 70L, 80L

        Bukkit.getScheduler().runTaskLater(main, new Runnable() {

            public void run() {
                loc.getBlock().getRelative(0, height,0).setType(Material.AIR);
                loc.getBlock().getRelative(0, height - 1,0).setType(Material.AIR);

                if(height == jumpHeight)
                {
                    loc.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, loc.getBlock().getRelative(0, jumpHeight, 0).getLocation(), 5);

                    Location jumpHeightLoc = loc.getBlock().getRelative(0, jumpHeight, 0).getLocation();
                    loc.getWorld().strikeLightning(jumpHeightLoc);

                    Entity entity = main.getBoss().spawn(jumpHeightLoc, bd);

                    main.getBossActivity().setTimeoutTimer(entity);

                    Bukkit.getScheduler().runTaskLater(main, new Runnable() {
                        public void run() {
                            entity.setInvulnerable(false);
                        }
                    }, 60L);
                }
            }
        }, (50L  + (height * 4))); // 70L, 80L, 90L, 100L

        //(50L  + (height * 10) - 10) + lastNumber); // 70L, 80L, 90L, 100L
    }
}
