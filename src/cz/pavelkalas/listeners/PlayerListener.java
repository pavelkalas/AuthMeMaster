package cz.pavelkalas.listeners;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerListener implements Listener {
	
	public List<Player> unloggedPlayers = new ArrayList<Player>();

	@EventHandler
	public void onMove(PlayerMoveEvent event) {
	}
}
