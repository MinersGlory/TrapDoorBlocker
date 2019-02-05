package de.bootko.trapdoorblocker.listeners;

import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.managers.RegionManager;
import de.bootko.trapdoorblocker.util.WorldUtil;
import org.bukkit.Location;

public class RegionListener {
    // Not yet used, will be for checking if the user can build in the region

    Location loc = null;

    // Check worldguard region
    WorldGuard wg = WorldUtil.getWorldGuard();
    RegionManager regions = null;


}
