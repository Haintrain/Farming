package io.github.haintrain.farming;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class TrampleToggle implements CommandExecutor {
    private Player player;
    private Boolean tramp;
    private Plugin plugin;

    public TrampleToggle(Plugin plugin)
    {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        tramp = Boolean.valueOf(args[0]);

        //Checks to see if sender is player
        if (sender instanceof Player) {
            player = (Player) sender;


            //Sets config file of player depending on number of args
            if(args.length == 0){
                player.sendMessage(plugin.getConfig().getString("User." + player.getName() + ".Trample_"));
            }
            else if(args.length == 1 && player.hasPermission("trample.toggle")){
                plugin.getConfig().set("User." + player.getName() + ".Trample_", tramp);
            }
            else if(args.length == 2 && player.hasPermission("trample.toggle.others")){
                if(plugin.getConfig().get("User." + args[1]) != null){
                    plugin.getConfig().set("User." + args[1] + ".Trample_", tramp);
                }
            }
            else{
                player.sendMessage("Incorrect syntax. Please use /trample true||false (player)");
            }

            plugin.saveConfig();
            player.sendMessage("Set Trample To : " + tramp);
        }



        else{
            Bukkit.getConsoleSender().sendMessage("I guess send from player idk");
        }


        return true;
    }
}

