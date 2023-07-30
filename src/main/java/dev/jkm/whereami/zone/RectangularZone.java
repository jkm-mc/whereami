package dev.jkm.whereami.zone;

/**
 * A rectangular zone, made up of two points (top left and bottom right)
 */
public class RectangularZone implements Zone {
    private final String name;
    private final int x1;
    private final int z1;
    private final int x2;
    private final int z2;

    /**
     * Create a new rectangular zone.
     * @param name The name of the zone
     * @param x1 The x coordinate of the top left point
     * @param z1 The z coordinate of the top left point
     * @param x2 The x coordinate of the bottom right point
     * @param z2 The z coordinate of the bottom right point
     */
    public RectangularZone(String name, int x1, int z1, int x2, int z2) {
        this.name = name;

        this.x1 = x1;
        this.z1 = z1;
        this.x2 = x2;
        this.z2 = z2;
    }

    /**
     * Get the name of the zone.
     * @return The name of the zone
     */
    public String getName() {
        return this.name;
    }

    /**
     * Check if the zone contains the given point.
     * @param x The x coordinate of the point
     * @param z The z coordinate of the point
     * @return true if the zone contains the point, false otherwise
     */
    public boolean contains(double x, double z) {
        return x >= this.x1 && x <= this.x2 && z >= this.z1 && z <= this.z2;
    }
}
