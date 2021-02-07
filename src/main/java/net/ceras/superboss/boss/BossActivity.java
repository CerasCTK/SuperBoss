package net.ceras.superboss.boss;

import net.ceras.superboss.SuperBoss;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;

public class BossActivity {
    private SuperBoss main;

    public BossActivity(SuperBoss main) {
        this.main = main;
    }

    public void setTimeoutTimer(Entity entity) {
        main.getBoss().getBossData().get(entity.getUniqueId()).setActivityTask(
                Bukkit.getScheduler().runTaskLater(main, new Runnable() {

                    public void run() {
                        System.out.println(System.currentTimeMillis() - main.getBoss().getBossData().get(entity.getUniqueId()).getOldActivityTimer()
                                > (main.getBoss().getBossData().get(entity.getUniqueId()).getActivityTimer() / 20) * 1000);

                        entity.remove();
                        main.getBoss().getBossData().get(entity.getUniqueId()).getActivityTask().cancel();
                        System.out.println("removed");
                    }
                }, main.getBoss().getBossData().get(entity.getUniqueId()).getActivityTimer()));
    }
}
