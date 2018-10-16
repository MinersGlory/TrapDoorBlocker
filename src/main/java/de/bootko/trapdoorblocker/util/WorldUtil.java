package de.bootko.trapdoorblocker.util;

import com.sk89q.worldguard.WorldGuard;
import org.bukkit.plugin.Plugin;

import static org.bukkit.Bukkit.getServer;


public class WorldUtil {




    // Fetch WorldGuard dependency
    public static WorldGuard getWorldGuard() {
        Plugin plugin = getServer().getPluginManager().getPlugin("WorldGuard");

        //If worldguard not loaded, throw exception

        if (plugin == null || !(plugin instanceof WorldGuard)) {
            return null;
        }

        return (WorldGuard) plugin;
    }





}
