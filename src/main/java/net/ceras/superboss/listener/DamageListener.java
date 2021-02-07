package net.ceras.superboss.listener;

import net.ceras.superboss.SuperBoss;
import net.ceras.superboss.api.CerasAPI;
import net.ceras.superboss.top.TopDetail;
import net.ceras.superboss.util.ColorManager;
import net.ceras.superboss.util.ListSorter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class DamageListener implements Listener {
    private SuperBoss main;

    public DamageListener(SuperBoss main) {
        this.main = main;
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {
        for (UUID uuid : main.getBoss().getBossData().keySet()) {
            if (event.getEntity().getUniqueId().equals(uuid)) {

                if (event.getDamager() instanceof Player) {
                    if (main.getBoss().getBossData().get(uuid).getDamagers().get(event.getDamager().getUniqueId()) == null) {
                        main.getBoss().getBossData().get(uuid).getDamagers().put(event.getDamager().getUniqueId(), event.getFinalDamage());
                        // System.out.println("yes");
                    } else {
                        main.getBoss().getBossData().get(uuid).getDamagers().put(event.getDamager().getUniqueId(),
                                main.getBoss().getBossData().get(uuid).getDamagers().get(event.getDamager().getUniqueId()) + event.getFinalDamage());
                    }
                    // System.out.println(main.getBoss().getBossData().get(uuid).getDamagers().get(e.getDamager().getUniqueId()));
                    main.getBoss().getBossData().get(uuid).getActivityTask().cancel();
                    main.getBossActivity().setTimeoutTimer(event.getEntity());

                    for (Entity entity : event.getEntity().getWorld().getNearbyEntities(event.getEntity().getLocation(),
                            Integer.valueOf(main.getFileHandler().getRadius().get(0)),
                            Integer.valueOf(main.getFileHandler().getRadius().get(1)),
                            Integer.valueOf(main.getFileHandler().getRadius().get(2)))) {

                        if (entity instanceof Player) {
                            if (!main.getBoss().getBossData().get(uuid).getNearbyPlayers().contains(entity.getUniqueId())) {
                                main.getBoss().getBossData().get(uuid).getNearbyPlayers().add(entity.getUniqueId());
                            }
                        }
                    }

                    Random rand = new Random();

                    int chance = rand.nextInt();

                    for (UUID playerUUID : main.getBoss().getBossData().get(uuid).getNearbyPlayers()) {
                        if (chance < 5) {
                            int chatRandom = rand.nextInt(main.getBoss().getBossData().get(uuid).getBd().getMessages().size());

                            Bukkit.getPlayer(playerUUID).sendMessage(ColorManager.colorize(
                                    main.getBoss().getBossData().get(uuid).getBd().getMessages().get(chatRandom)));
                        }

                        if (CerasAPI.getAbilityListener() != null)
                            CerasAPI.getAbilityListener().onAbilityUse(chance,
                                    main.getBoss().getBossData().get(uuid).getBd().getSkill(),
                                    Bukkit.getPlayer(playerUUID),
                                    ListSorter.sort(main.getBoss().getBossData().get(uuid).getDamagers()), event.getEntity());
                    }
                }
            }
        }
    }

    @EventHandler
    public void onBossDeath(EntityDeathEvent event) {
        for (UUID uuid : main.getBoss().getBossData().keySet()) {
            if (event.getEntity().getUniqueId().equals(uuid)) {
                main.getBoss().getBossData().get(uuid).getActivityTask().cancel();
                main.getBoss().getBossData().get(uuid).setActivityTask(null);

                int count = 0;

                List<String> globalMessage = main.getFileHandler().getDefeatedGlobalMessage();

                List<String> newGlobalMessage = new ArrayList<>();

                main.getBoss().getBossData().get(uuid).setDamagers(ListSorter.sort(main.getBoss().getBossData().get(uuid).getDamagers()));

                for (TopDetail td : main.getBoss().getBossData().get(uuid).getBd().getTopDetailMap().values()) {
                    try {
                        UUID playerUUID = (UUID) main.getBoss().getBossData().get(uuid).getDamagers().keySet().toArray()[count];

                        Player player = Bukkit.getPlayer(playerUUID);

                        for (String command : td.getCommand()) {
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
                                    command.replace("%player%", player.getName()));
                        }

                        for (String message : td.getMessagePlayer()) {
                            player.sendMessage(message.replace("%player%", player.getName()));
                        }

                        for (String message : globalMessage) {
                            if (message.contains("%top" + (count + 1) + "%")) {
                                newGlobalMessage.add(message.replace("%top" + (count + 1) + "%",player.getName())
                                        .replace("%damage%", String.valueOf(
                                                String.format("%.2f",
                                                        (Double) main.getBoss().getBossData().get(uuid).getDamagers().values().toArray()[count]))));
                            }
                        }
                        count++;
                    } catch (ArrayIndexOutOfBoundsException exception) {

                    }
                }

                for (String message : newGlobalMessage) {
                    Bukkit.broadcastMessage(message);
                }

                main.getBoss().getBossData().remove(uuid);
            }
        }
    }
}
