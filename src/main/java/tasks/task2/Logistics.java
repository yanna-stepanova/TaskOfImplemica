package main.java.tasks.task2;
/*
1. Вхідні дані
Формат вводу:
s — кількість тестів (не більше 10). Кожен тест є окремим випадком зі своїм списком міст та шляхів.
n — кількість міст у тесті (не більше 10,000).
NAME — назва міста (рядок довжиною до 10 символів).
p — кількість сусідніх міст, які мають пряме сполучення з містом NAME.
nr cost — опис прямого з'єднання:
nr — індекс сусіднього міста (починаючи з 1).
cost — вартість транспортування між містом NAME і його сусідом.
r — кількість запитів на пошук шляху (не більше 100).
NAME1 NAME2 — запит на пошук мінімальної вартості транспортування між містами NAME1 (початкове) і NAME2 (кінцеве).
2. Вихідні дані
Для кожного запиту (пари міст NAME1 і NAME2) потрібно вивести:

мінімальну вартість транспортування між цими містами (або -1, якщо шлях неможливий).
3. Приклад:
Вхідні дані:
Копіювати код
1
4
gdansk
2
2 1
3 3
bydgoszcz
3
1 1
3 1
4 4
torun
3
1 3
2 1
4 1
warszawa
2
2 4
3 1

2
gdansk warszawa
bydgoszcz torun
3
2
=============================================================
Розбір прикладу:
Тест №1:
n = 4 — чотири міста: gdansk, bydgoszcz, torun, warszawa.
Опис міст:
gdansk: має 2 сусіди:
Місто з індексом 2 (bydgoszcz), вартість транспортування: 1.
Місто з індексом 3 (torun), вартість транспортування: 3.
bydgoszcz: має 3 сусіди:
Місто з індексом 1 (gdansk), вартість транспортування: 1.
Місто з індексом 3 (torun), вартість транспортування: 1.
Місто з індексом 4 (warszawa), вартість транспортування: 4.
torun: має 3 сусіди:
Місто з індексом 1 (gdansk), вартість транспортування: 3.
Місто з індексом 2 (bydgoszcz), вартість транспортування: 1.
Місто з індексом 4 (warszawa), вартість транспортування: 1.
warszawa: має 2 сусіди:
Місто з індексом 2 (bydgoszcz), вартість транспортування: 4.
Місто з індексом 3 (torun), вартість транспортування: 1.
Запити:
gdansk warszawa — знайти мінімальну вартість шляху з gdansk до warszawa.
bydgoszcz torun — знайти мінімальну вартість шляху з bydgoszcz до torun.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Logistics {
    private static Map<Integer, City> cities;

    public static void main(String[] args) {
        initCities();
        Scanner scanner = new Scanner(System.in);
        System.out.println("How many test will there be? Enter number: ");
        int s = scanner.nextInt();
        ArrayList<LinkedList<String>> names = new ArrayList<>();
        if (s <= 10 && s > 0) {
            for (int i = 0; i < s; i++) {
                LinkedList<String> route = new LinkedList<>();
                route.add(scanner.next());
                route.add(scanner.next());
                names.add(route);
            }
        }
        for (int i = 0; i < s; i++) {
            findShortestRoute(names.get(i));
        }
    }

    private static void findShortestRoute(LinkedList<String> route) {
        int cityFromIndex = getCityIndex(route.get(0));
        int cityToIndex = getCityIndex(route.get(1));
        if (cityFromIndex != -1 || cityToIndex != -1) {
            int price = algorithmDijkstra(cityFromIndex, cityToIndex);
            System.out.println(price);
        }
    }

    private static int algorithmDijkstra(int cityFromIndex, int cityToIndex) {
        int[] distances = new int[cities.size() + 1];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[cityFromIndex] = 0; // this is a value for cityFrom
        PriorityQueue<int[]> priorityQueueCosts = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        priorityQueueCosts.offer(new int[]{cityFromIndex, 0});

        while (!priorityQueueCosts.isEmpty()) {
            int[] currentArray = priorityQueueCosts.poll();
            int cityIndex = currentArray[0];
            int cityDistanceCost = currentArray[1];

            // skip if the route is longer
            if (cityDistanceCost > distances[cityIndex]) continue;

            Map<Integer, Integer> neighborsOfCurrentCity = cities.get(cityIndex).getNeighbors();
            for (Map.Entry<Integer, Integer> neighbor : neighborsOfCurrentCity.entrySet()) {
                checkNeighborsOfCurrentCity(neighbor, cityDistanceCost, distances, priorityQueueCosts);
            }
        }
        return distances[cityToIndex];
    }

    private static void checkNeighborsOfCurrentCity(Map.Entry<Integer, Integer> neighbor,
                                                    int cityDistanceCost, int[] intsDistances,
                                                    PriorityQueue<int[]> pqCost) {
            int neighborIndex = neighbor.getKey();
            int newCost = cityDistanceCost + neighbor.getValue();

            if (newCost < intsDistances[neighborIndex]) {
                intsDistances[neighborIndex] = newCost;
                pqCost.offer(new int[]{neighborIndex, newCost});
            }
    }

    private static int getCityIndex(String cityName) {
        for (Map.Entry<Integer, City> entry : cities.entrySet()) {
            if (entry.getValue().getName().equalsIgnoreCase(cityName)) {
                return entry.getKey();
            }
        }
        return -1; // city not found
    }

    private static void initCities() {
        City gdansk = new City("Gdansk", 2);
        gdansk.setNeighbors(2, 1);
        gdansk.setNeighbors(3, 3);

        City bydgoszcz = new City("Bydgoszcz", 3);
        bydgoszcz.setNeighbors(1, 1);
        bydgoszcz.setNeighbors(3, 1);
        bydgoszcz.setNeighbors(4, 4);

        City torun = new City("Torun", 3);
        torun.setNeighbors(1, 3);
        torun.setNeighbors(2, 1);
        torun.setNeighbors(4, 1);

        City warszawa = new City("Warszawa", 2);
        warszawa.setNeighbors(2, 4);
        warszawa.setNeighbors(3, 1);

        cities = new HashMap<>();
        cities.put(1, gdansk);
        cities.put(2, bydgoszcz);
        cities.put(3, torun);
        cities.put(4, warszawa);
    }
}


