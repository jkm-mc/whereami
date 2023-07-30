package dev.jkm.whereami;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import dev.jkm.whereami.zone.Zone;
import dev.jkm.whereami.zone.PolygonZone;
import dev.jkm.whereami.zone.RectangularZone;

public class WhereAmIPlugin extends JavaPlugin {
    private Map<String, Zone> zones = new HashMap<String, Zone>();

    @Override
    public void onEnable() {
        saveDefaultConfig();

        loadZones();

        getCommand("whereami").setExecutor(new WhereAmICommand(this));
    }

    @Override
    public void onDisable() {
        getLogger().log(Level.INFO, "{0}.onDisable()", this.getClass().getName());
    }

    /**
     * Get the zone that contains the given location.
     * 
     * TODO handle overlapping zones
     * TODO consider using a more efficient search
     * 
     * @param location The location to check
     * @return The zone that contains the location, or null if no zone contains the location
     */
    public Zone getZone(Location location) {
        for (Zone zone : zones.values()) {
            if (zone.contains(location.getX(), location.getZ())) {
                return zone;
            }
        }

        return null;
    }

    /**
     * Load the zones from the configuration file.
     */
    private void loadZones() {
        FileConfiguration config = getConfig();

        zones = Configuration.loadZones(getLogger(), config);
        getLogger().log(Level.INFO, "Loaded {0} zones", zones.size());
    }
}
