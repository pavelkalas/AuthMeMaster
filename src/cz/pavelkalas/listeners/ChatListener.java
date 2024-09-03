package cz.pavelkalas.listeners;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import cz.pavelkalas.database.SqlConnection;

public class ChatListener extends PlayerListener implements CommandExecutor {

    private SqlConnection sqlConn;

    public ChatListener() {
        this.sqlConn = new SqlConnection();
        this.sqlConn.connect();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (label.equalsIgnoreCase("login")) {
                if (args.length == 1) {
                    String password = args[0];
                    
                    if (sqlConn.matchPassword(player, password)) {
                        player.sendMessage("Logged in!");
                        
                        if (unloggedPlayers.contains(player)) {
                        	unloggedPlayers.remove(player);
                        }
                    } else {
                        player.sendMessage("Invalid password!");
                    }
                    return true;
                } else {
                    player.sendMessage("Usage: /login <password>");
                    return false;
                }
            }

            if (label.equalsIgnoreCase("register")) {
                if (args.length == 2) {
                    String password = args[0];
                    String passwordAgain = args[1];
                    
                    if (password.equals(passwordAgain)) {
                        if (sqlConn.registerUser(player, password)) {
                            player.sendMessage("Registration successful!");
                        } else {
                            player.sendMessage("Registration failed.");
                        }
                    } else {
                        player.sendMessage("Passwords do not match.");
                    }
                    return true;
                } else {
                    player.sendMessage("Usage: /register <password> <password again>");
                    return false;
                }
            }
        }

        return false;
    }

    public void closeConnection() {
        sqlConn.close();
    }
}
