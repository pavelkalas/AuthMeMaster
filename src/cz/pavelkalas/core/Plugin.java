package cz.pavelkalas.core;

import org.bukkit.plugin.java.JavaPlugin;

import cz.pavelkalas.listeners.ChatListener;
import cz.pavelkalas.listeners.ConnectionListener;
import cz.pavelkalas.listeners.PlayerListener;

public class Plugin extends JavaPlugin {

	@Override
	public void onEnable() {
		this.getServer().getPluginManager().registerEvents(new ConnectionListener(), this);
		this.getServer().getPluginManager().registerEvents(new PlayerListener(), this);
		
		this.getCommand("login").setExecutor(new ChatListener());
        this.getCommand("register").setExecutor(new ChatListener());
	}

	@Override
	public void onDisable() {
	}
}
