package de.bootko.trapdoorblocker;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class TrapDoorBlocker extends JavaPlugin {

    @Override
    public void onEnable() {

    }

    @Override
    public void onDisable() {

    }

    @Override
    public void onLoad() {

    }

    private WorldGuardPlugin getWorldGuard() {
        Plugin plugin = getServer().getPluginManager().getPlugin("WorldGuard");

        // If WorldGuard fails to load, tell console and do things.
        if (plugin == null || !(plugin instanceof WorldGuardPlugin)) {
            return null;
            
        }
        return (WorldGuardPlugin) plugin;
    }
}
