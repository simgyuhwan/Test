package com.only.practice.wefun;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Gyuhwan
 */
public class 주문음식 {

  public String[] solution(String[] orders) {
    Map<String, Set<String>> customerOrders = new HashMap<>();
    int max = 0;

    for (String order : orders) {
      String[] split = order.split(" ");
      String name = split[0];

      Set<String> menuSet = customerOrders.getOrDefault(name, new HashSet<>());

      for (int i = 1; i < split.length; i++) {
        menuSet.add(split[i]);
      }

      customerOrders.put(name, menuSet);
      max = Math.max(max, menuSet.size());
    }

    int finalMax = max;
    List<String> maxOrders = customerOrders.entrySet().stream()
        .filter(entry -> entry.getValue().size() == finalMax)
        .map(Entry::getKey)
        .sorted()
        .collect(Collectors.toList());

    return maxOrders.toArray(new String[0]);
  }

}
