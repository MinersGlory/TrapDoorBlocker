package de.bootko.trapdoorblocker.listeners;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldguard.LocalPlayer;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.association.RegionAssociable;
import com.sk89q.worldguard.protection.flags.Flags;
import com.sk89q.worldguard.protection.flags.StateFlag;
import de.bootko.trapdoorblocker.TrapDoorBlocker;
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

import static de.bootko.trapdoorblocker.util.FlagUtil.testFlag;

public class TrapdoorInteractListener implements Listener {

    public TrapDoorBlocker plugin;

    @EventHandler(priority= EventPriority.HIGH)
    public void onInteract (PlayerInteractEvent event) {
        Block block = event.getClickedBlock();
        Player player = event.getPlayer();
        World world = player.getWorld();
        Location currentLocation = player.getLocation();
        double locx = currentLocation.getX();
        double locy = currentLocation.getY();
        double locz = currentLocation.getZ();
        Location loc = new Location(player.getWorld(), locx, locy, locz);


        // If trapdoor then block item interact if in the region and the user does not have build access
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.LEFT_CLICK_BLOCK) {
            if (Tag.TRAPDOORS.isTagged(block.getType())) {
                boolean canBuild = testFlag(event.getPlayer(), Flags.BUILD);
                if (!canBuild) {
                    event.setCancelled(true);
                } else {
                    event.setCancelled(false);
                }
            }
        }
    }



    public TrapdoorInteractListener(TrapDoorBlocker plugin) {
        this.plugin = plugin;
    }
}
