package kz.aitu.strategy.strategy;

import kz.aitu.strategy.model.Route;
import kz.aitu.strategy.model.Trip;

/**
 * Strategy interface: one algorithm for turning a trip into a route.
 * Every transport mode implements it in its own way.
 */
public interface RouteStrategy {

    Route planRoute(Trip trip);

    String name();
}
