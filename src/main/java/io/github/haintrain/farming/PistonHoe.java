package io.github.haintrain.farming;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPistonExtendEvent;
import org.bukkit.material.Crops;
import org.bukkit.plugin.Plugin;

public class PistonHoe implements Listener{
    private Plugin plugin;

    public PistonHoe(Plugin plugin)
    {
        this.plugin = plugin;

    }

    @EventHandler
    public void onBlockBreak(BlockPistonExtendEvent event) {

        Block block = event.getBlock();
        Block targetBlock = block.getRelative(event.getDirection(), 1);

        if(targetBlock.getState().getData() instanceof Crops){
            targetBlock.setType(Material.AIR);
        }
    }

}
