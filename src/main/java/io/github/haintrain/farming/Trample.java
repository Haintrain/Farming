package io.github.haintrain.farming;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.Plugin;

public class Trample implements Listener{

    private Plugin plugin;
    private Boolean tramp;

    public Trample(Plugin plugin)
    {
        this.plugin = plugin;
    }

    public void setTramp(Boolean tramp){
        this.tramp = tramp;
    }
    public Boolean getTramp(){
        return tramp;
    }

    @EventHandler
    public void noUproot(PlayerInteractEvent event)
    {
        Player player = event.getPlayer();

        tramp = plugin.getConfig().getBoolean("User." + player.getName() + player.getUniqueId() + ".Trample_");

        if(event.getAction() == Action.PHYSICAL && event.getClickedBlock().getType() == Material.SOIL && tramp == false) {
            event.setCancelled(true);
        }

        player.sendMessage("Trample Is: " + tramp.toString());
    }



}
