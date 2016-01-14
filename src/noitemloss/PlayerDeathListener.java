/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package noitemloss;

import java.util.Iterator;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.Plugin;

/*
This will listen to player death and cut inventory in floor half. When player dies
*/

public class PlayerDeathListener implements Listener {
    
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {

        Player p = event.getEntity();
        if (p.getGameMode() == GameMode.SURVIVAL || p.getGameMode() == GameMode.ADVENTURE) {
            PlayerInventory inv = p.getInventory();
            Location loc = p.getLocation();
            for (ItemStack item : inv) {
                if (item != null && item.getAmount() > 1) {
                    ItemStack dropItem = item;
                    int amountRemoved = item.getAmount() / 2;
                    item.setAmount(item.getAmount() - amountRemoved);
                    dropItem.setAmount(amountRemoved);
                    p.getWorld().dropItemNaturally(loc, dropItem);
                }
            }
        }
    }
}
