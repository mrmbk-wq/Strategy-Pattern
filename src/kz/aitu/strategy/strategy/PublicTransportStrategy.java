package kz.aitu.strategy.strategy;

import kz.aitu.strategy.model.Route;
import kz.aitu.strategy.model.Trip;

/**
 * Plans a bus route: waiting time at the stop plus riding time, flat fare.
 * Added later to show that a new strategy needs no change in Navigator
 * or in the other strategies.
 */
public class PublicTransportStrategy implements RouteStrategy {

    private static final double BUS_ROUTE_DETOUR_FACTOR = 1.3;
    private static final double BUS_SPEED_KMH = 25.0;
    private static final int AVERAGE_WAIT_MINUTES = 8;
    private static final double FLAT_FARE_KZT = 100.0;

    @Override
    public Route planRoute(Trip trip) {
        double busRouteKm = trip.distanceKm() * BUS_ROUTE_DETOUR_FACTOR;
        int ridingMinutes = (int) Math.ceil(busRouteKm / BUS_SPEED_KMH * 60);
        return new Route(name(), trip, busRouteKm, AVERAGE_WAIT_MINUTES + ridingMinutes, FLAT_FARE_KZT);
    }

    @Override
    public String name() {
        return "Public transport";
    }
}
