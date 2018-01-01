package io.github.haintrain.farming;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;


public class MainFarming extends JavaPlugin implements Listener{

    private Plugin plugin;
    private Trample trample;

    @Override
    public void onEnable() {
        plugin = this;
        trample = new Trample(plugin);

        //Registers events of all the classes
        registerEvents(this, new Hoes(plugin), trample, new PlayerJoin(plugin, trample));
        //Sets up the command
        getCommand("trample").setExecutor(new TrampleToggle(plugin));

        //Default config stuff
        createConfig();
        loadConfig();
    }

    public static void registerEvents(org.bukkit.plugin.Plugin plugin, Listener... listeners) {
        for (Listener listener : listeners) {
            Bukkit.getServer().getPluginManager().registerEvents(listener, plugin);
        }
    }

    public void loadConfig(){
        plugin.getConfig().options().copyDefaults(true);
        saveConfig();
    }

    private void createConfig() {
        try {
            if (!getDataFolder().exists()) {
                getDataFolder().mkdirs();
            }
            File file = new File(getDataFolder(), "config.yml");
            if (!file.exists()) {
                getLogger().info("Config.yml not found, creating!");
                saveDefaultConfig();
            } else {
                getLogger().info("Config.yml found, loading!");
            }
        } catch (Exception e) {
            e.printStackTrace();

        }

    }


}

