package kz.aitu.strategy.strategy;

import kz.aitu.strategy.model.Route;
import kz.aitu.strategy.model.Trip;

/** Plans a route along city roads by car and charges for fuel. */
public class CarStrategy implements RouteStrategy {

    private static final double ROAD_DETOUR_FACTOR = 1.25;
    private static final double AVERAGE_SPEED_KMH = 40.0;
    private static final double FUEL_LITRES_PER_KM = 0.08;
    private static final double FUEL_PRICE_KZT = 280.0;

    @Override
    public Route planRoute(Trip trip) {
        double roadDistanceKm = trip.distanceKm() * ROAD_DETOUR_FACTOR;
        int minutes = (int) Math.ceil(roadDistanceKm / AVERAGE_SPEED_KMH * 60);
        double fuelCost = roadDistanceKm * FUEL_LITRES_PER_KM * FUEL_PRICE_KZT;
        return new Route(name(), trip, roadDistanceKm, minutes, fuelCost);
    }

    @Override
    public String name() {
        return "Car";
    }
}
