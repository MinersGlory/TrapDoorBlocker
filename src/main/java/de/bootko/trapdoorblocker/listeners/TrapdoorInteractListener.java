package de.bootko.trapdoorblocker.listeners;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldguard.LocalPlayer;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.association.RegionAssociable;
import com.sk89q.worldguard.protection.flags.Flags;
import com.sk89q.worldguard.protection.flags.StateFlag;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
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

import java.util.List;

public class TrapdoorInteractListener implements Listener {

    public TrapDoorBlocker plugin;

    @EventHandler(priority= EventPriority.HIGH)
    public void onInteract (PlayerInteractEvent event) {
        Block block = event.getClickedBlock();
        Player player = event.getPlayer();

        World world = player.getWorld();
        Location currentLocation = player.getLocation();


        // If trapdoor then block item interact if in the region and the user does not have build access
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.LEFT_CLICK_BLOCK) {
            if (Tag.TRAPDOORS.isTagged(block.getType())) {

                RegionManager rm  = WorldGuard.getInstance().getPlatform().getRegionContainer().get(BukkitAdapter.adapt(world));
                ApplicableRegionSet set = rm.getApplicableRegions( BukkitAdapter.asVector(currentLocation));
                List<String> blocked_regions = plugin.getConfig().getStringList("regions.blocked-regions");
                if (blocked_regions != null) {
                    for (ProtectedRegion region : set) {
                        if (blocked_regions.contains(region.getId())) {

                            LocalPlayer wgPlayer = WorldGuardPlugin.inst().wrapPlayer(player);
                            if (!StateFlag.test(WorldGuard.getInstance().getPlatform().getRegionContainer().createQuery().queryState(BukkitAdapter.adapt(currentLocation), (RegionAssociable) wgPlayer, Flags.BUILD))) {
                                event.setCancelled(true);
                            }
                            // If can build allow interaction
                            if (StateFlag.test(WorldGuard.getInstance().getPlatform().getRegionContainer().createQuery().queryState(BukkitAdapter.adapt(currentLocation), (RegionAssociable) wgPlayer, Flags.BUILD))) {
                                event.setCancelled(false);
                            }

                        }
                    }
                }
            }
        }
    }

    public TrapdoorInteractListener(TrapDoorBlocker plugin) {
        this.plugin = plugin;
    }
}
