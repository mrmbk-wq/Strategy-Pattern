package kz.aitu.strategy.strategy;

import kz.aitu.strategy.model.Route;
import kz.aitu.strategy.model.Trip;

public interface RouteStrategy {

    Route planRoute(Trip trip);

    String name();
}
