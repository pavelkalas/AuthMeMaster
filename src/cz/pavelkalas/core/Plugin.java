package cz.pavelkalas.core;

import org.bukkit.plugin.java.JavaPlugin;

import cz.pavelkalas.listeners.ChatListener;
import cz.pavelkalas.listeners.ConnectionListener;
import cz.pavelkalas.listeners.PlayerListener;

/**
 * Main classfile for plugin entry.
 */
public class Plugin extends JavaPlugin {

	PlayerListener playerListener = new PlayerListener();
	
	/**
	 * Function executed at plugin load.
	 */
	@Override
	public void onEnable() {
		this.getServer().getPluginManager().registerEvents(new ConnectionListener(playerListener), this);
		this.getServer().getPluginManager().registerEvents(playerListener, this);
		
		this.getCommand("login").setExecutor(new ChatListener(playerListener));
        this.getCommand("register").setExecutor(new ChatListener(playerListener));
	}

	/**
	 * Function executed at plugin unload (while stopping or reloading server)
	 */
	@Override
	public void onDisable() {
	}
}
