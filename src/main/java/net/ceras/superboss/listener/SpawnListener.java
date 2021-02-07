package net.ceras.superboss.listener;

import net.ceras.superboss.SuperBoss;
import net.ceras.superboss.boss.BossDetail;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class SpawnListener implements Listener {
    private SuperBoss main;

    public SpawnListener(SuperBoss main) {
        this.main = main;
    }

    @EventHandler
    public void onBossSpawn(PlayerInteractEvent e) {
        if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            for (BossDetail bd : main.getFileHandler().getBossCache().values()) {
                try {
                    if (e.getItem().isSimilar(bd.getSpawnItemType())) {
                        String cords = String.valueOf((int) e.getClickedBlock().getLocation().getX() + ", "
                                + (int) e.getClickedBlock().getLocation().getY() + ", "
                                + (int) e.getClickedBlock().getLocation().getZ());

                        e.setCancelled(true);

                        if (main.getFileHandler().getSpawnBlockData().containsValue(cords)) {
                            e.getPlayer().getInventory().getItemInMainHand().setAmount(e.getPlayer().getInventory().getItemInMainHand().getAmount() - 1);
                            main.getSpawnAnimation().play(e.getClickedBlock().getLocation(), bd);
                        }
                    }
                } catch (NullPointerException noItem) {
                }
            }
        }
    }

    @EventHandler
    public void onBossBlockPlace(BlockPlaceEvent e) {
        if (e.getItemInHand().isSimilar(main.getFileHandler().getSpawnBlockType())) {
            String cords = String.valueOf(e.getBlock().getX() + ", " + e.getBlock().getY()
                    + ", " + e.getBlock().getZ());

            main.getFileHandler().getSpawnBlockData().put(
                    "block" + (main.getFileHandler().getSpawnBlockData().size() + 1), cords);

            main.getFileManager().modifyBlockData.set("cords" + ".block" +
                    main.getFileHandler().getSpawnBlockData().size(), cords);

            main.getFileManager().saveFile();
        }
    }

    @EventHandler
    public void onBossBlockRemove(BlockBreakEvent e) {
        String cords = String.valueOf(e.getBlock().getX() + ", " + e.getBlock().getY()
                + ", " + e.getBlock().getZ());

        String blockName = null;

        for (String value : main.getFileHandler().getSpawnBlockData().keySet()) {
            if (main.getFileHandler().getSpawnBlockData().get(value).equals(cords)) {
                blockName = value;
                break;
            }
        }

        if (blockName != null) {
            main.getFileManager().getModifyBlockData().set("cords." + blockName, null);
            main.getFileHandler().getSpawnBlockData().remove(blockName);
            main.getFileManager().saveFile();
        }
    }
}
