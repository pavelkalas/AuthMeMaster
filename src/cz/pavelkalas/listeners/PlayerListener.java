package cz.pavelkalas.listeners;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

/**
 * Player listener class (move, etc..)
 */
public class PlayerListener implements Listener {
	
	/**
	 * List of un authed players with data structure of Player instance
	 */
	public List<Player> unloggedPlayers = new ArrayList<Player>();

	/**
	 * On player move.
	 * 
	 * This function is called when player do move like, x, y, z, pitch or yaw
	 * 
	 * @param event PlayerMoveEvent instance
	 */
	@EventHandler
	public void onMove(PlayerMoveEvent event) {
		Player player = event.getPlayer();
		
		if (player == null) {
			return;
		}
		
		if (unloggedPlayers.contains(player)) {
			Location location = player.getLocation();
			location.setYaw(0.0f);
			location.setPitch(0.0f);
			player.teleport(location);
		}
	}
}
