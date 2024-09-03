package cz.pavelkalas.listeners;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import cz.pavelkalas.database.SqlConnection;
import cz.pavelkalas.utils.Messager;

/**
 * Logging chat commands class
 */
public class ChatListener implements CommandExecutor {

	/**
	 * Instance for sql connexion.
	 */
    private SqlConnection sqlConn;
    
    /**
     * Instance of player listener
     */
    private PlayerListener playerListener;

    public ChatListener(PlayerListener playerListener) {
        this.sqlConn = new SqlConnection();
        this.sqlConn.connect();
        this.playerListener = playerListener;
    }

    /**
     * Then player execute commands starts with "/" slash, this function is being executed.
     */
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            //
            // REQUIRED INPUT:    /login  <your password>
            //
            if (label.equalsIgnoreCase("login")) {
                if (args.length == 1) {
                    String password = args[0];
                    
                    if (sqlConn.matchPassword(player, password)) {
                    	Messager.sendMessage(player, "Successfully logged in!");
                        
                        if (playerListener.unloggedPlayers.contains(player)) {
                        	playerListener.unloggedPlayers.remove(player);
                        }
                    } else {
                    	Messager.sendMessage(player, "Invalid password! Try it again.");
                    }
                    return true;
                } else {
                	Messager.sendMessage(player, "Usage: /login <your password>");
                    return false;
                }
            }

            //
            // REQUIRED INPUT:    /register  <password>   <password again>
            //
            else if (label.equalsIgnoreCase("register")) {
                if (args.length == 2) {
                    String password = args[0];
                    String passwordAgain = args[1];
                    
                    if (password.equals(passwordAgain)) {
                        if (sqlConn.registerUser(player, password)) {
                        	Messager.sendMessage(player, "Registration was successfull! Now login by command /login <your password>");
                        } else {
                        	Messager.sendMessage(player, "Registration failed, try rejoin server and try it again!");
                        }
                    } else {
                    	Messager.sendMessage(player, "Passwords do not match!");
                    }
                    return true;
                } else {
                	Messager.sendMessage(player, "/register <password> <password again>");
                    return false;
                }
            }
        }

        return false;
    }

    /**
     * Closes the database connection.
     */
    public void close() {
        sqlConn.close();
    }
}
