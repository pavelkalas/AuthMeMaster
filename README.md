# AuthMeMaster

**AuthMeMaster** is a Spigot plugin for Minecraft that provides user authentication features, including login and registration functionality. The plugin uses an SQLite database to manage user credentials.

## Features

- **User Registration**: Allows players to register with a username and password.
- **User Login**: Enables players to log in using their registered username and password.
- **SQLite Database**: Stores user data in an SQLite database.

## Prerequisites

- **Java**: Version 1.8 or higher
- **Spigot**: Compatible with the Spigot API
- **SQLite JDBC Driver**: Make sure to include the SQLite JDBC driver in your project.

## Configuration

The plugin uses an SQLite database named `sample.db` by default. You can customize the database file name by modifying the plugin’s source code if needed.

## Commands

### `/register <password> <passwordAgain>`

Registers a new user with the provided password. Both password entries must match for successful registration.

**Usage**: `/register <password> <passwordAgain>`

### `/login <password>`

Logs in the user with the provided password.

**Usage**: `/login <password>`

## Example Usage

1. **Registering a User**:
   ```text
   /register mypassword mypasswordagain
    ```
