package cz.pavelkalas.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import cz.pavelkalas.database.SqlConnection;
import cz.pavelkalas.utils.Messager;

/**
 * Player connexion listener class (joining/quitting)
 */
public class ConnectionListener extends SqlConnection implements Listener {

	/**
	 * Instance of player listener.
	 */
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
		
		// open sql connection
		connect();
		
		// checks if is player registered in DB (if yes, require login, not a registration)
		boolean isRegistered = userExists(player);
		
		// close sql connection
		close();
		
		new Thread(new Runnable() {
			public void run() {
				for (int i = 0; i < 3; i++) {
					
					if (!player.isOnline()) {
						break;
					}
					
					if (isRegistered) {
						Messager.sendMessage(player, "Please, login using command /login <password>");
					} else {
						Messager.sendMessage(player, "Please, register using command /register <password> <password again>");
					}
					
					try {
						Thread.sleep(8000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
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
