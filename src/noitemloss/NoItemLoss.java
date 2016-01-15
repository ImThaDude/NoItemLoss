/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package noitemloss;

import java.util.logging.Level;
import java.util.logging.LogRecord;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author Jia Yi
 */
public class NoItemLoss extends JavaPlugin {

    /*
    This plugin prevents players from loosing all inventory in any world.
    It changes all worlds to not loosing items when dead.
    Drops half of your resources on the ground. Including exp.
    If there is only one of that item it evades it.
    It looks at all inventory and drops half of everything.
    */
    
    GameruleBackup KeepInvBackup;
    
    public void onEnable() {
        
        //Register Events
        getServer().getPluginManager().registerEvents(new PlayerDeathListener(), this);
        
        //Create config YML folder and stuff.
        getConfig().options().copyDefaults(true);
        saveConfig();
        
        Bukkit.getLogger().log(new LogRecord(Level.INFO, "GameruleBackup for " + this.getName() + " backing up current."));
        //Creating the backup object uniquely to keepInventory.
        KeepInvBackup = new GameruleBackup("keepInventory", "false");
        //On enable the plugin will turn all of the worlds keepInventory to true. It will store the value tho.
        KeepInvBackup.backupandset(Bukkit.getWorlds(), "true");
        Bukkit.getLogger().log(new LogRecord(Level.INFO, "GameruleBackup for " + this.getName() + " backed up!"));
        
    }
    
    public void onDisable() {
        
        Bukkit.getLogger().log(new LogRecord(Level.INFO, "GameruleBackup for " + this.getName() + " restoring."));
        //Restoring the default world values of keepInventory.
        KeepInvBackup.restore(Bukkit.getWorlds());
        Bukkit.getLogger().log(new LogRecord(Level.INFO, "GameruleBackup for " + this.getName() + " restored!"));
        //Removes the variable.
        KeepInvBackup = null;
        
    }
}
