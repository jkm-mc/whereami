package dev.jkm.whereami.zone;

import dev.jkm.whereami.zone.Zone;

/**
 * A polygonal zone, made up of a list of vertices.
 */
public class PolygonZone implements Zone {
    /**
     * Represents a vertex in the polygon.
     */
    public static class PolygonVertex {
        private final double x;
        private final double z;

        public PolygonVertex(double x, double z) {
            this.x = x;
            this.z = z;
        }

        /**
         * Get the x coordinate of the vertex.
         * @return The x coordinate of the vertex
         */
        public double getX() {
            return this.x;
        }

        /**
         * Get the z coordinate of the vertex.
         * @return The z coordinate of the vertex
         */
        public double getZ() {
            return this.z;
        }

        /**
         * Returns true if the given point is on the left side of the line segment between this vertex and the next vertex.
         * @param nextVertex The next vertex in the polygon
         * @param x The x coordinate of the point
         * @param z The z coordinate of the point
         * @return True if the given point is on the left side of the line segment between this vertex and the next vertex
         */
        public boolean isOnLeft(PolygonVertex prevVertex, double x, double z) {
            if ((z < prevVertex.getZ() && z >= this.z) || (z < this.z && z >= prevVertex.getZ())) {
                double intersectX = prevVertex.getX() + (this.x - prevVertex.getX()) * (z - prevVertex.getZ()) / (this.z - prevVertex.getZ());
                return x <= intersectX;
            }
            
            return false;
        }
    }

    private PolygonVertex[] vertices;
    private final String name;

    /**
     * Create a new polygonal zone.
     * @param name The name of the zone
     * @param vertices The vertices of the polygon
     */
    public PolygonZone(String name, PolygonVertex[] vertices) {
        this.name = name;
        this.vertices = vertices;
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
     */
    public boolean contains(double x, double z) {
        boolean result = false;
        for (int currentVertex = 0, previousVertex = vertices.length - 1; currentVertex < vertices.length; previousVertex = currentVertex++) {
            if (vertices[currentVertex].isOnLeft(vertices[previousVertex], x, z)) {
                result = !result;
            }
        }
        return result;
    }

    /**
     * Get the number of vertices in the polygon.
     * @return The number of vertices in the polygon
     */
    public int getVertexCount() {
        return this.vertices.length;
    }
}
