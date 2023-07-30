package dev.jkm.whereami.zone;

/**
 * Represents a zone in the world, that is,
 * a region of the world that has a name and can be queried to see if it contains a given point.
 */
public interface Zone {
    /**
     * Get the name of the zone.
     * @return The name of the zone
     */
    public String getName();

    /**
     * Check if the zone contains the given point.
     * @param x The x coordinate of the point
     * @param z The z coordinate of the point
     * @return true if the zone contains the point, false otherwise
     */
    public boolean contains(double x, double z);
}
