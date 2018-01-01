package io.github.haintrain.farming;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;

public class PlayerJoin implements Listener{

    private Plugin plugin;
    private Boolean tramp;
    private Trample trample;

    public PlayerJoin(Plugin plugin, Trample trample){
        this.plugin = plugin;
        this.trample = trample;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        tramp = plugin.getConfig().getBoolean("User." + player.getUniqueId() + ".Trample_");

        //Checks to see if player has already been assigned trample value and sets if there is none
        if(tramp.equals(null)) {
            player.sendMessage("Setting Tramp");
            plugin.getConfig().set("User." + player.getUniqueId() + ".Trample_", true);
            plugin.saveConfig();
        }

        //Sets tramp of Trample class
        trample.setTramp(tramp);
        player.sendMessage(tramp.toString());
    }
}
