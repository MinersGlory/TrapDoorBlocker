package de.bootko.trapdoorblocker.listeners;

import org.bukkit.Location;
import org.bukkit.Tag;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class TrapdoorInteractListener implements Listener {

    @EventHandler(priority= EventPriority.HIGH)
    public void onInteract (PlayerInteractEvent event) {
        Block block = event.getClickedBlock();
        Player player = event.getPlayer();

        World world = player.getWorld();
        Location currentLocation = player.getLocation();


        // If trapdoor then block item interact if in region "newhaven"
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.LEFT_CLICK_BLOCK) {
            if (Tag.TRAPDOORS.isTagged(block.getType())) {
                player.sendMessage("You can't touch this! ~MC Hammer");
                /* -- WORLDGUARD STUFF, PLACEHOLDER --
                RegionContainer container = com.sk89q.worldguard.WorldGuard.getInstance().getPlatform().getRegionContainer();
                RegionQuery query = container.createQuery();
                ApplicableRegionSet set = query.getApplicableRegions(BukkitAdapter.adapt(currentLocation));
                */


                event.setCancelled(true);
            }
        } else {
            // hmm
        }
    }
}
