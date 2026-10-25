package kz.aitu.strategy;

import kz.aitu.strategy.context.Navigator;
import kz.aitu.strategy.model.Trip;
import kz.aitu.strategy.strategy.CarStrategy;
import kz.aitu.strategy.strategy.CycleStrategy;
import kz.aitu.strategy.strategy.PublicTransportStrategy;
import kz.aitu.strategy.strategy.RouteStrategy;
import kz.aitu.strategy.strategy.WalkStrategy;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    private static final Map<String, RouteStrategy> STRATEGIES = new LinkedHashMap<>();

    static {
        register("car", new CarStrategy());
        register("walk", new WalkStrategy());
        register("cycle", new CycleStrategy());
        register("bus", new PublicTransportStrategy());
    }

    private static void register(String key, RouteStrategy strategy) {
        STRATEGIES.put(key, strategy);
    }

    public static void main(String[] args) {
        Trip trip = new Trip("AITU (EXPO)", "Baiterek", 6.5);
        Navigator navigator = new Navigator(STRATEGIES.get("car"));

        printAllRoutes(navigator, trip);
        runInteractiveMode(navigator, trip);
    }

    private static void printAllRoutes(Navigator navigator, Trip trip) {
        System.out.println("=== Runtime strategy switching demo ===");
        STRATEGIES.values().forEach(strategy -> {
            navigator.setRouteStrategy(strategy);
            System.out.println(navigator.planRoute(trip));
        });
    }

    private static void runInteractiveMode(Navigator navigator, Trip trip) {
        System.out.println();
        System.out.println("Choose transport " + STRATEGIES.keySet() + " or 'exit':");
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String choice = scanner.nextLine().trim().toLowerCase();
            if (choice.equals("exit")) {
                return;
            }
            RouteStrategy strategy = STRATEGIES.get(choice);
            if (strategy == null) {
                System.out.println("Unknown transport '" + choice + "'. Available: " + STRATEGIES.keySet());
                continue;
            }
            navigator.setRouteStrategy(strategy);
            System.out.println("Navigator now uses: " + navigator.currentStrategyName());
            System.out.println(navigator.planRoute(trip));
        }
    }
}
