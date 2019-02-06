package de.bootko.trapdoorblocker;

import de.bootko.trapdoorblocker.listeners.TrapdoorInteractListener;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.logging.Logger;

public class TrapDoorBlocker extends JavaPlugin {

    public static TrapDoorBlocker plugin;

    public final Logger logger = getLogger();
    File config = new File(this.getDataFolder(), "config.yml");



    @Override
    public void onEnable() {
        plugin = this;


        if (config.exists()) {

            if ((getServer().getPluginManager().getPlugin("WorldGuard") != null)) {
                logger.info("TrapDoorBlocker has linked into WorldGuard and has been enabled.");
                getServer().getPluginManager().registerEvents(new TrapdoorInteractListener(this), this);
            } else {
                logger.info("WorldGuard 7 failed to load, please try again later.");
            }


        } else {
            this.saveDefaultConfig();
            logger.info("Generated default config.yml");
            getServer().getPluginManager().registerEvents(new TrapdoorInteractListener(this), this);
        }
    }

    @Override
    public void onDisable() {
        logger.info("TrapDoorBlocker has been disabled.");
        plugin = null;

    }

    @Override
    public void onLoad() {

    }

    /**
     * Get an instance of the main class
     *
     * @return an instance of the main class
     */
    public static TrapDoorBlocker getPlugin() {
        return plugin;
    }
}
