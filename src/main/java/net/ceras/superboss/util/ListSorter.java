package net.ceras.superboss.util;

import java.util.*;

public class ListSorter {
    public static Map<UUID, Double> sort(Map<UUID, Double> map) {
        List<Double> tempList = new ArrayList<>();
        Map<Double, UUID> tempMap = new HashMap<>();

        Map<UUID, Double> finalMap = new LinkedHashMap<>();

        for(UUID uuid : map.keySet()) {
            tempMap.put(map.get(uuid), uuid);
        }

        for(double value : map.values()) {
            tempList.add(value);
        }

        Collections.sort(tempList);
        Collections.reverse(tempList);

        for(double value : tempList) {
            finalMap.put(tempMap.get(value), value);
        }

        return finalMap;
    }
}
