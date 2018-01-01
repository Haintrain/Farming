package io.github.haintrain.farming;

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

        if (sender instanceof Player) {
            player = (Player) sender;
            plugin.getConfig().set("User." + player.getUniqueId() + ".Trample_", tramp);
            plugin.saveConfig();
            player.sendMessage("Set Trample To : " + tramp);
        }


        if(args.length == 1 && player.hasPermission("trample.toggle")){

        }
        else{

        }


        return true;
    }
}

