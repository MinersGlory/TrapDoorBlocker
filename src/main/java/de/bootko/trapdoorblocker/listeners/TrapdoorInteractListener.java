package de.bootko.trapdoorblocker.listeners;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import com.sk89q.worldguard.protection.regions.RegionQuery;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.ArrayList;
import java.util.List;

public class TrapdoorInteractListener implements Listener {

    @EventHandler(priority= EventPriority.HIGH)
    public void onInteract (PlayerInteractEvent event) {
        Block block = event.getClickedBlock();
        Player player = event.getPlayer();

        World world = player.getWorld();
        Location currentLocation = player.getLocation();


        // Put all trapdoor item names into array
        List<Material> trapdoors = new ArrayList<Material>();

        for (Material trapdoor : Material.values()) {
            if(trapdoor.toString().contains("_TRAPDOOR")) {
                trapdoors.add(trapdoor);
            }
        }

        // If trapdoor then block item interact if in region "newhaven"
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.LEFT_CLICK_BLOCK) {
            if (block.getType() == Material.getMaterial(trapdoors.toString())) {

                RegionContainer container = com.sk89q.worldguard.WorldGuard.getInstance().getPlatform().getRegionContainer();
                RegionQuery query = container.createQuery();
                ApplicableRegionSet set = query.getApplicableRegions(BukkitAdapter.adapt(currentLocation));
                event.setCancelled(true);
                trapdoors.remove(currentLocation);
            }
        } else {
            // hmm
        }
    }
}
