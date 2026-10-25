package kz.aitu.strategy.strategy;

import kz.aitu.strategy.model.Route;
import kz.aitu.strategy.model.Trip;

public class WalkStrategy implements RouteStrategy {

    private static final double FOOTPATH_DETOUR_FACTOR = 1.05;
    private static final double WALKING_SPEED_KMH = 5.0;

    @Override
    public Route planRoute(Trip trip) {
        double pathDistanceKm = trip.distanceKm() * FOOTPATH_DETOUR_FACTOR;
        int minutes = (int) Math.ceil(pathDistanceKm / WALKING_SPEED_KMH * 60);
        return new Route(name(), trip, pathDistanceKm, minutes, 0);
    }

    @Override
    public String name() {
        return "Walk";
    }
}
