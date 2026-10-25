package kz.aitu.strategy.strategy;

import kz.aitu.strategy.model.Route;
import kz.aitu.strategy.model.Trip;

/** Plans a route along bike lanes; a shared bike is paid per started 30 minutes. */
public class CycleStrategy implements RouteStrategy {

    private static final double BIKE_LANE_DETOUR_FACTOR = 1.15;
    private static final double CYCLING_SPEED_KMH = 15.0;
    private static final int RENTAL_PERIOD_MINUTES = 30;
    private static final double RENTAL_PRICE_KZT = 300.0;

    @Override
    public Route planRoute(Trip trip) {
        double laneDistanceKm = trip.distanceKm() * BIKE_LANE_DETOUR_FACTOR;
        int minutes = (int) Math.ceil(laneDistanceKm / CYCLING_SPEED_KMH * 60);
        int rentalPeriods = (int) Math.ceil((double) minutes / RENTAL_PERIOD_MINUTES);
        return new Route(name(), trip, laneDistanceKm, minutes, rentalPeriods * RENTAL_PRICE_KZT);
    }

    @Override
    public String name() {
        return "Bicycle";
    }
}
