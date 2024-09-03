package cz.pavelkalas.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * Player connexion listener class (joining/quitting)
 */
public class ConnectionListener implements Listener {

	private PlayerListener playerListener;
	
	public ConnectionListener(PlayerListener playerListener) {
		this.playerListener = playerListener;
	}
	
	/**
	 * On player join
	 * 
	 * Function is executed when player joins the server.
	 * 
	 * @param event PlayerJoinEvent instance
	 */
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		
		if (player == null) {
			return;
		}
		
		if (!playerListener.unloggedPlayers.contains(player)) {
			playerListener.unloggedPlayers.add(player);
		}
	}

	/**
	 * On player server leave/quit.
	 * 
	 * Function is executed when player joins the server.
	 * 
	 * @param event PlayerQuitEvent instance
	 */
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {
		Player player = event.getPlayer();
		
		if (player == null) {
			return;
		}
		
		if (playerListener.unloggedPlayers.contains(player)) {
			playerListener.unloggedPlayers.remove(player);
		}
	}
}
