package kz.aitu.strategy.model;

/**
 * Input for route planning: where the user starts, where they go
 * and the straight-line distance between the two points.
 */
public record Trip(String origin, String destination, double distanceKm) {

    public Trip {
        if (distanceKm <= 0) {
            throw new IllegalArgumentException("Distance must be positive, got " + distanceKm);
        }
    }
}
