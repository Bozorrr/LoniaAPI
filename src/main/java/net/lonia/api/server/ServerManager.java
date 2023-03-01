package net.lonia.api.server;

import net.lonia.api.LoniaAPI;
import org.bukkit.*;

import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;

public class ServerManager {

    private List<Server> servers = new ArrayList<>();

    public static final Server lobby1   = new Server("20", "Lobby#1"    , "lobby#1"     , Type.LOBBY.getName()  , Status.DEFAULT.getName(), Material.BEACON     , "§9Lobby#1"   , "§bLobby#1"   , "§3Lobby#1"   , 0);
    public static final Server areaPvP1 = new Server("30", "AreaPvP#1"  , "areapvp#1"   , Type.AREAPVP.getName(), Status.DEFAULT.getName(), Material.IRON_SWORD , "§9AreaPvP#1" , "§bAreaPvP#1" , "§3AreaPvP#1" , 0);
    public static final Server jump1    = new Server("40", "Jump#1"     , "jump#1"      , Type.JUMP.getName()   , Status.DEFAULT.getName(), Material.GRASS      , "§9Jump#1"    , "§bJump#1"    , "§3Jump#1"    , 0);
    public static final Server build1   = new Server("50", "Build#1"    , "build#1"     , Type.BUILD.getName()  , Status.DEFAULT.getName(), Material.BRICK      , "§9Build#1"   , "§bBuild#1"   , "§3Build#1"   , 0);
    public static final Server build2   = new Server("51", "Build#2"    , "build#2"     , Type.BUILD.getName()  , Status.DEFAULT.getName(), Material.BRICK      , "§9Build#2"   , "§bBuild#2"   , "§3Build#2"   , 0);
    public static final Server build3   = new Server("52", "Build#3"    , "build#3"     , Type.BUILD.getName()  , Status.DEFAULT.getName(), Material.BRICK      , "§9Build#3"   , "§bBuild#3"   , "§3Build#3"   , 0);
    public static final Server build4   = new Server("53", "Build#4"    , "build#4"     , Type.BUILD.getName()  , Status.DEFAULT.getName(), Material.BRICK      , "§9Build#4"   , "§bBuild#4"   , "§3Build#4"   , 0);

    public ServerManager() {
        servers.add(lobby1);
        servers.add(areaPvP1);
        servers.add(jump1);
        servers.add(build1);
        servers.add(build2);
        servers.add(build3);
        servers.add(build4);
    }

    public static String getServerIDByServerName(String server_name) {
        return (String) LoniaAPI.get().getDataBaseManager().getMySQL().query("SELECT * FROM " + Server.databaseName + " WHERE server_name='" + server_name + "'", rs -> {
            try {
                if (rs.next())
                    return Material.getMaterial(rs.getString("server_id"));
            }
            catch (SQLException e) {
                Bukkit.getLogger().log(Level.SEVERE, "NO SERVER ID FOUND FOR SERVER NAME: " + server_name + ", PLEASE CHECK SERVER NAME !");
                e.printStackTrace();
            }
            return null;
        });
    }

    public static String getServerNameByServerID(String id) {
        return (String) LoniaAPI.get().getDataBaseManager().getMySQL().query("SELECT * FROM " + Server.databaseName + " WHERE server_id='" + id + "'", rs -> {
            try {
                if (rs.next())
                    return rs.getString("server_name");
            }
            catch (SQLException e) {
                Bukkit.getLogger().log(Level.SEVERE, "NO SERVER NAME FOUND FOR SERVER ID: " + id + ", PLEASE CHECK ID !");
                e.printStackTrace();
            }
            return null;
        });
    }

    public static String getBungeeServerNameByServerID(String id) {
        return (String) LoniaAPI.get().getDataBaseManager().getMySQL().query("SELECT * FROM " + Server.databaseName + " WHERE server_id='" + id + "'", rs -> {
            try {
                if (rs.next())
                    return rs.getString("bungee_name");
            }
            catch (SQLException e) {
                Bukkit.getLogger().log(Level.SEVERE, "NO SERVER BUNGEE NAME FOUND FOR SERVER ID: " + id + ", PLEASE CHECK ID !");
                e.printStackTrace();
            }
            return null;
        });
    }

    public static Type getServerTypeByServerID(String id) {
        return (Type) LoniaAPI.get().getDataBaseManager().getMySQL().query("SELECT * FROM " + Server.databaseName + " WHERE server_id='" + id + "'", rs -> {
            try {
                if (rs.next())
                    return Type.getByName(rs.getString("server_type"));
            }
            catch (SQLException e) {
                Bukkit.getLogger().log(Level.SEVERE, "NO SERVER TYPE FOUND FOR SERVER ID: " + id + ", PLEASE CHECK ID !");
                e.printStackTrace();
            }
            return null;
        });
    }

    public static Status getServerStatusByServerID(String id) {
        return (Status) LoniaAPI.get().getDataBaseManager().getMySQL().query("SELECT * FROM " + Server.databaseName + " WHERE server_id='" + id + "'", rs -> {
            try {
                if (rs.next())
                    return Status.getByName(rs.getString("server_status"));
            }
            catch (SQLException e) {
                Bukkit.getLogger().log(Level.SEVERE, "NO SERVER STATUS FOUND FOR SERVER ID: " + id + ", PLEASE CHECK ID !");
                e.printStackTrace();
            }
            return null;
        });
    }

    public static Material getServerMaterialByServerID(String id) {
        return (Material) LoniaAPI.get().getDataBaseManager().getMySQL().query("SELECT * FROM " + Server.databaseName + " WHERE server_id='" + id + "'", rs -> {
            try {
                if (rs.next())
                    return Material.getMaterial(rs.getString("server_material"));
            }
            catch (SQLException e) {
                Bukkit.getLogger().log(Level.SEVERE, "NO SERVER MATERIAL FOUND FOR SERVER ID: " + id + ", PLEASE CHECK ID !");
                e.printStackTrace();
            }
            return null;
        });
    }

    public static String getConnectedPlayerByServerID(String id) {
        return (String) LoniaAPI.get().getDataBaseManager().getMySQL().query("SELECT * FROM " + Server.databaseName + " WHERE server_id='" + id + "'", rs -> {
            try {
                if (rs.next())
                    return rs.getString("connected_player");
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
            return "0";
        });
    }

    public List<Server> getServers() { return servers; }

}

