package cz.pavelkalas.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.bukkit.entity.Player;

/**
 * SQL management class for checking passwords of user, registering users or check the existency.
 */
public class SqlConnection {

	/**
	 * Instance of sql connexion.
	 */
    private Connection conn = null;
    
    /**
     * Instance of sql statements.
     */
    private Statement stmt = null;

    public SqlConnection() {
    }

    /**
     * Creates and opens sql connection.
     */
    public void connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:authmemaster_database.db";
            conn = DriverManager.getConnection(url);

            System.out.println("Connection to SQLite has been established.");

            stmt = conn.createStatement();

            String createTableSQL = "CREATE TABLE IF NOT EXISTS players ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "name TEXT NOT NULL,"
                    + "password TEXT NOT NULL"
                    + ");";
            stmt.execute(createTableSQL);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Inserts new registered player information into sql.
     * 
     * @param player - Player instance
     * @param inputedPassword - Player's inputed password
     * @return Returns TRUE if is registration SUCCESS.
     */
    public boolean registerUser(Player player, String inputedPassword) {
        if (userExists(player)) {
            return false;
        }

        String sql = "INSERT INTO players (name, password) VALUES (?, ?);";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, player.getName());
            pstmt.setString(2, inputedPassword); // In real applications, use hashed passwords

            int affectedRows = pstmt.executeUpdate();

            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Checks if player already exists in database (by name)
     * 
     * @param player - Player instance
     * @return Returns TRUE if player is already presented in database.
     */
    public boolean userExists(Player player) {
        String sql = "SELECT COUNT(*) FROM players WHERE name = ?;";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, player.getName());

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Compares a player inputed password with a database saved pasword.
     * 
     * @param player - Player instance
     * @param inputedPassword - Player's inputed password.
     * @return Returns TRUE if password matches by player name.
     */
    public boolean matchPassword(Player player, String inputedPassword) {
        String sql = "SELECT password FROM players WHERE name = ?;";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, player.getName());

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String storedPassword = rs.getString("password");
                return storedPassword.equals(inputedPassword);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Closes the database connection.
     */
    public void close() {
        try {
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
