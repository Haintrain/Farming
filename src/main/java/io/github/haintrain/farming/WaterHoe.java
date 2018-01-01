package io.github.haintrain.farming;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFromToEvent;
import org.bukkit.material.Crops;
import org.bukkit.plugin.Plugin;

public class WaterHoe implements Listener{
    private Plugin plugin;

    public WaterHoe(Plugin plugin)
    {
        this.plugin = plugin;
    }

    @EventHandler
    public void onBlockBreak(BlockFromToEvent event) {

        Block block = event.getBlock();
        Block targetBlock = block.getRelative(event.getFace(), 1);

        if(targetBlock.getState().getData() instanceof Crops){
            targetBlock.setType(Material.AIR);
        }
    }

}
