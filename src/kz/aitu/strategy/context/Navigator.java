package kz.aitu.strategy.context;

import kz.aitu.strategy.model.Route;
import kz.aitu.strategy.model.Trip;
import kz.aitu.strategy.strategy.RouteStrategy;

import java.util.Objects;

/**
 * Context: keeps a reference to the current {@link RouteStrategy}
 * and delegates route planning to it. It knows nothing about how
 * any concrete strategy works, and the strategy can be replaced at runtime.
 */
public class Navigator {

    private RouteStrategy routeStrategy;

    public Navigator(RouteStrategy routeStrategy) {
        setRouteStrategy(routeStrategy);
    }

    public void setRouteStrategy(RouteStrategy routeStrategy) {
        this.routeStrategy = Objects.requireNonNull(routeStrategy, "routeStrategy must not be null");
    }

    public Route planRoute(Trip trip) {
        return routeStrategy.planRoute(trip);
    }

    public String currentStrategyName() {
        return routeStrategy.name();
    }
}
