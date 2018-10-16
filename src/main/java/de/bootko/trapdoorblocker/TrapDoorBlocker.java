package de.bootko.trapdoorblocker;

import com.sk89q.worldguard.WorldGuard;
import de.bootko.trapdoorblocker.listeners.TrapdoorInteractListener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class TrapDoorBlocker extends JavaPlugin {

    Logger logger = getLogger();

    @Override
    public void onEnable() {

        logger.info("TrapDoorBlocker has been enabled.");
        getServer().getPluginManager().registerEvents(new TrapdoorInteractListener(), this);
    }

    @Override
    public void onDisable() {

    }

    @Override
    public void onLoad() {

    }

    private WorldGuard getWorldGuard() {
        Plugin wgp = getServer().getPluginManager().getPlugin("WorldGuard");

        // If WorldGuard fails to load, tell console and do things.
        if (wgp == null || !(wgp instanceof WorldGuard)) {
            logger.info("WorldGuard 7 has not been found. Please download and try again");
            
        }
        return (WorldGuard) wgp;
    }
}
