package de.bootko.trapdoorblocker.util;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.FlagValueCalculator;
import com.sk89q.worldguard.protection.flags.StateFlag;
import com.sk89q.worldguard.protection.flags.StateFlag.State;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import de.bootko.trapdoorblocker.TrapDoorBlocker;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class FlagUtil {

    public static TrapDoorBlocker plugin;

    public static boolean testFlag(Player player, StateFlag flag) {
        ApplicableRegionSet regions = WorldGuard.getInstance().getPlatform().getRegionContainer().createQuery()
                .getApplicableRegions(BukkitAdapter.adapt(player.getLocation()));
        List<String> checkedRegions = new ArrayList<String>();
        List<ProtectedRegion> checkForRegions = new ArrayList<>();
        List<String> blocked_regions = plugin.getConfig().getStringList("regions.blocked-regions");
        if (blocked_regions != null) {
            for (ProtectedRegion region : regions) {
                if (blocked_regions.contains(region.getId())) {
                    checkForRegions.add(region);

                    // Debug to see what region.getId returns
                    plugin.logger.info(ChatColor.DARK_RED + "Region:" + ChatColor.WHITE + region.getId());
                }
            }
            ProtectedRegion global = WorldGuard.getInstance().getPlatform().getRegionContainer().get(BukkitAdapter.adapt(player.getLocation().getWorld())).getRegion(ProtectedRegion.GLOBAL_REGION);
            FlagValueCalculator calc= new FlagValueCalculator(checkForRegions, global);
            State check = calc.queryValue(null, flag);
            if (check == State.ALLOW) {
                return true;
            } else {
                return false;
            }
        }
        plugin.getLogger().info("blocked_regions is null, please specify a region!");
        return false;

    }

    public FlagUtil(TrapDoorBlocker plugin) {
        this.plugin = plugin;
    }
}
