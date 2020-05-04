package com.floleproto.thetower.utils;

import java.util.*;

public class MapUtils {

    public static LinkedHashMap<UUID, Integer> sortBestPlayer(HashMap<UUID, Integer> maps) {
        LinkedHashMap<UUID, Integer> reverseSortedMap = new LinkedHashMap<>();
        maps.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEachOrdered(x -> reverseSortedMap.put(x.getKey(), x.getValue()));

        return reverseSortedMap;
    }
}
