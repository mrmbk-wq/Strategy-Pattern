package kz.aitu.strategy.model;

public record Trip(String origin, String destination, double distanceKm) {

    public Trip {
        if (distanceKm <= 0) {
            throw new IllegalArgumentException("Distance must be positive, got " + distanceKm);
        }
    }
}
