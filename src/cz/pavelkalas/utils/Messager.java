package cz.pavelkalas.utils;

import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

/**
 * Class for customizing chat messages, adding prefixes to messages etc.
 */
public class Messager {
	
	/**
	 * Adds a custom prefix to message.
	 * 
	 * @param player - Player instance 
	 * @param message - Message content
	 */
	public static void sendMessage(Player player, String message) {
		player.sendMessage(ChatColor.RED + "Auth" + ChatColor.GOLD+ "Me" + ChatColor.YELLOW +"Master" + ChatColor.WHITE + " >> " + message);
	}
}
