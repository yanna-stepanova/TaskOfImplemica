package main.java.tasks.task2;

/*
  nr - index of nearby city (starting from 1)
  cost - transportation cost between this city and its neighbor
  p - number of neighbors of this city
 */

import java.util.HashMap;
import java.util.Map;

public class City {
    private final String name;
    private final Map<Integer, Integer> neighbors;

    public City(String name, int p) {
        this.name = name;
        this.neighbors = new HashMap<>(p);
    }

    public Map<Integer, Integer> getNeighbors() {
        return neighbors;
    }

    public void setNeighbors(int nr, int cost) {
        neighbors.put(nr, cost);
    }

    public String getName() {
        return name;
    }
}
