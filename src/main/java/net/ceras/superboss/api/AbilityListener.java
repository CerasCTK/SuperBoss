package net.ceras.superboss.api;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface AbilityListener {
    public void onAbilityUse(int chance, List<String> skill, Player player, Map<UUID, Double> topDamagers, Entity entity);
}
