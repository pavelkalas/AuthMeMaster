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

## Installation

1. **Download the Plugin**:
   - Download the latest version of the plugin JAR file from the [releases page](https://github.com/pavelkalas/AuthMeMaster/releases).

2. **Add to Server**:
   - Place the downloaded JAR file into the `plugins` folder of your Spigot server.

3. **Start Your Server**:
   - Restart your Spigot server to load the plugin.

## Configuration

The plugin uses an SQLite database named `authmemaster_database.db` by default. You can customize the database file name by modifying the plugin’s source code if needed.

## Commands

### `/register <password> <passwordAgain>`

Registers a new user with the provided password. Both password entries must match for successful registration.

**Usage**: `/register <password> <my password again>`

### `/login <password>`

Logs in the user with the provided password.

**Usage**: `/login <my password>`

## Example Usage

1. **Registering a User**:
   ```text
   /register <my password> <my password again>
