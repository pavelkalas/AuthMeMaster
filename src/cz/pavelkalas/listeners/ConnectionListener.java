package cz.pavelkalas.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class ConnectionListener extends PlayerListener implements Listener {

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		String player = event.getPlayer().getName();
		
		if (!unloggedPlayers.contains(player)) {
			unloggedPlayers.add(player);
		}
	}

	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {
		String player = event.getPlayer().getName();
		
		if (unloggedPlayers.contains(player)) {
			unloggedPlayers.remove(player);
		}
	}
}
