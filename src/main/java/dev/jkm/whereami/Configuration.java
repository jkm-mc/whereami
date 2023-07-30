package dev.jkm.whereami;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import dev.jkm.whereami.zone.Zone;
import dev.jkm.whereami.zone.PolygonZone;
import dev.jkm.whereami.zone.RectangularZone;

/**
 * This class is responsible for loading the plugin configuration.
 */
public class Configuration {
    /**
     * Load the zones from the configuration file.
     * @param logger The logger to use for logging messages
     * @param config The configuration file
     * @return A map of zone names to zones
     */
    public static Map<String, Zone> loadZones(Logger logger, FileConfiguration config) {
        Map<String, Zone> zones = new HashMap<String, Zone>();

        ConfigurationSection zonesSection = config.getConfigurationSection("zones");

        if (zonesSection == null) {
            logger.log(Level.WARNING, "zones section is empty");
            return zones;
        }

        for (String zoneName : zonesSection.getKeys(false)) {
            ConfigurationSection zoneSection = zonesSection.getConfigurationSection(zoneName);
            if (zoneSection.contains("vertices")) {
                PolygonZone zone = loadPolygonZone(logger, zoneName, zoneSection);

                if (zone == null) {
                    continue;
                }

                zones.put(zoneName, zone);
                logger.log(Level.INFO, "Loaded polygon zone {0} with {1} vertices", new Object[] { zoneName, zone.getVertexCount() });
            } else {
                Zone zone = loadRectangularZone(zoneName, zoneSection);
                zones.put(zoneName, zone);
                logger.log(Level.INFO, "Loaded rectangular zone {0}", zoneName);
            }
        }

        return zones;
    }

    /**
     * Load a rectangular zone from its configuration section.
     * @param name The name of the zone
     * @param config The configuration section for the zone
     * @return The zone
     */
    private static RectangularZone loadRectangularZone(String name, ConfigurationSection config) {
        int x1 = config.getInt("x1");
        int z1 = config.getInt("z1");
        int x2 = config.getInt("x2");
        int z2 = config.getInt("z2");

        return new RectangularZone(name, x1, z1, x2, z2);
    }

    /**
     * Load a polygon zone from its configuration section.
     * @param logger The logger to use for logging messages
     * @param name The name of the zone
     * @param config The configuration section for the zone
     * @return The zone
     */
    private static PolygonZone loadPolygonZone(Logger logger, String name, ConfigurationSection config) {
        List<PolygonZone.PolygonVertex> vertices = new ArrayList<>();
        List<?> verticesSection = (List<List<Number>>) config.getList("vertices");

        if (verticesSection == null || verticesSection.isEmpty()) {
            logger.log(Level.WARNING, "vertices section for zone {0} is null or empty", name);
            return null;
        }

        if (verticesSection.get(0) instanceof List == false) {
            logger.log(Level.WARNING, "vertices section for zone {0} is not a list of lists", name);
            return null;
        }

        for (Object vertexPair : verticesSection) {
            if (vertexPair instanceof List == false) {
                logger.log(Level.WARNING, "vertex pair for zone {0} is not a list", name);
                return null;
            }

            List<?> vertexPairList = (List<?>) vertexPair;
            if (vertexPairList.size() != 2) {
                logger.log(Level.WARNING, "vertex pair for zone {0} does not have 2 elements", name);
                return null;
            }

            if (vertexPairList.get(0) instanceof Number == false) {
                logger.log(Level.WARNING, "first element of vertex pair for zone {0} is not a number", name);
                return null;
            }

            if (vertexPairList.get(1) instanceof Number == false) {
                logger.log(Level.WARNING, "second element of vertex pair for zone {0} is not a number", name);
                return null;
            }

            int x = ((Number) vertexPairList.get(0)).intValue();
            int z = ((Number) vertexPairList.get(1)).intValue();
            vertices.add(new PolygonZone.PolygonVertex(x, z));
        }

        return new PolygonZone(name, vertices.toArray(new PolygonZone.PolygonVertex[0]));
    }
}
