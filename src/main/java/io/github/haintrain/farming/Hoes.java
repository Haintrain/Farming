package io.github.haintrain.farming;

import org.bukkit.CropState;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.Crops;
import org.bukkit.material.MaterialData;
import org.bukkit.plugin.Plugin;

import java.util.Random;

public class Hoes implements Listener{

    private Plugin plugin;

        public Hoes(Plugin plugin)
        {
            this.plugin = plugin;
        }


        @EventHandler
        public void onBlockBreak(BlockBreakEvent event) {

            Player player = event.getPlayer();
            Block block = event.getBlock();

            ItemStack item = player.getInventory().getItemInMainHand();

            int enchantNum = item.getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS);
            int fortune = randomRange(0, enchantNum);
            //Do something with fortune enchant if hoes gonna get fortune, yadadada fortune has rng calculations


            if (isFullyGrown(block)) {
                if (item.getType() == Material.DIAMOND_HOE) {
                    spawnItem(2 + fortune, block, enchantNum);
                    item.setDurability((short)(item.getDurability() + 1));
                    checkItem(item, player);
                } else if (item.getType() == Material.IRON_HOE || item.getType() == Material.GOLD_HOE) {
                    spawnItem(1 + fortune, block, enchantNum);
                    item.setDurability((short)(item.getDurability() + 1));
                    checkItem(item, player);
                } else if (item.getType() == Material.WOOD_HOE || item.getType() == Material.STONE_HOE) {
                    spawnItem(0 + fortune, block, enchantNum);
                    item.setDurability((short)(item.getDurability() + 1));
                    checkItem(item, player);
                } else {
                    event.getBlock().setType(Material.AIR);
                }
            }
            else if(block.getState().getData() instanceof Crops){
                event.getBlock().setType(Material.AIR);
            }

        }

        public void checkItem(ItemStack hoe, Player player){
            if (hoe.getType().getMaxDurability() == hoe.getDurability()) {
                player.getInventory().removeItem(hoe);
            }
        }

        //Spawns items, add RNG thing if needed
        public void spawnItem(int number, Block crop, int fortuneNum){
            Material mat = crop.getType();

            int random = randomRange(0, 100);
            //Use this number and and if else statement to spawn in various materials

            if (0 <= random && random <= 2){
                crop.getWorld().dropItem(crop.getLocation(), new ItemStack(Material.DIAMOND_HOE));
            }

            //Should I use a Case/Switch?
            if(mat == Material.CROPS){
                crop.getWorld().dropItem(crop.getLocation(), new ItemStack(Material.WHEAT, number));
            }
            else if(mat == Material.CARROT){
                crop.getWorld().dropItem(crop.getLocation(), new ItemStack(Material.CARROT_ITEM, number * 3));
            }
            else if(mat == Material.POTATO){
                crop.getWorld().dropItem(crop.getLocation(), new ItemStack(Material.POTATO_ITEM, number * 3));
            }
            else if(mat == Material.NETHER_WARTS){
                crop.getWorld().dropItem(crop.getLocation(), new ItemStack(Material.NETHER_WARTS, number * 3));
            }
            else if(mat == Material.BEETROOT){
                crop.getWorld().dropItem(crop.getLocation(), new ItemStack(Material.BEETROOT, number));
            }
            else crop.getWorld().dropItem(crop.getLocation(), new ItemStack(Material.CARROT, number * 3));

            //Do something with the random range for special drops
        }

        //Checks if is crop and fully grown
        public boolean isFullyGrown(Block block) {

            MaterialData md = block.getState().getData();

            if(md instanceof Crops) {
                return (((Crops) md).getState() == CropState.RIPE);
            }
            else {
                return false;
            }
        }

        public int randomRange(int low, int high)
        {
            Random generator = new Random();
            return generator.nextInt(high - low + 1) + low;
        }
}
