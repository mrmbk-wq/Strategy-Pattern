package kz.aitu.strategy.model;

/**
 * Result of route planning produced by a {@code RouteStrategy}.
 */
public record Route(String transportMode,
                    Trip trip,
                    double travelDistanceKm,
                    int durationMinutes,
                    double costKzt) {

    @Override
    public String toString() {
        return String.format("%-17s %s -> %s | %6.1f km | %4d min | %7.0f KZT",
                transportMode, trip.origin(), trip.destination(),
                travelDistanceKm, durationMinutes, costKzt);
    }
}
