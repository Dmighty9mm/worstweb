package org.kasun.website.Config;

import org.bukkit.configuration.ConfigurationOptions;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.kasun.website.SimpleWebsite;

import java.io.File;

public class MainConfig {
    private File configFile;
    private FileConfiguration config;
    private SimpleWebsite plugin;
    public String indexFile,
            keyStorePassword,
            domain;
    public int port;
    public boolean useSSL,
   whitelistPlaceholders;
    public String[] placeholderWhitelist;


    public MainConfig(SimpleWebsite plugin) {
        this.plugin = plugin;
        plugin.getConfig().options().copyDefaults();
        plugin.saveDefaultConfig();
        configFile = new File(plugin.getDataFolder(), "config.yml");
        config = YamlConfiguration.loadConfiguration(configFile);

        loadWebServer();
        loadSSL();
        loadapi();
    }

    private void loadWebServer() {
        ConfigurationSection webserver = config.getConfigurationSection("webserver");
        indexFile = webserver.getString("indexFile");
        port = webserver.getInt("port");
        domain = webserver.getString("domain");
    }

    private void loadSSL() {
        ConfigurationSection ssl = config.getConfigurationSection("ssl");
        useSSL = ssl.getBoolean("useSSL");
        keyStorePassword = ssl.getString("keyStorePassword");
    }

    private void loadapi() {
        ConfigurationSection api = config.getConfigurationSection("api");
        whitelistPlaceholders = api.getBoolean("whitelistPlaceholders");
        placeholderWhitelist = api.getStringList("placeholder-whitelist").toArray(new String[0]);

    }

}
