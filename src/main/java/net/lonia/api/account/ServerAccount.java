package net.lonia.api.account;

import net.lonia.api.LoniaAPI;
import net.lonia.api.server.*;
import org.bukkit.Material;

import java.sql.SQLException;

public class ServerAccount {

    private static final String            TABLE = Server.databaseName;
    private final        org.bukkit.Server server;
    private final        String            id;
    private final Status status;

    public ServerAccount(org.bukkit.Server server) {

        this.server = server;
        this.status = Status.DEFAULT;
        this.id = server.getServerId();
    }


    public static ServerAccount getServerAccount(org.bukkit.Server server) {

        return LoniaAPI.get().getAccountManager().getServerAccountList().stream().filter(a -> (a.getServer() == server)).findFirst().get();
    }

    public org.bukkit.Server getServer() {

        return this.server;
    }

    public void setup() {

        LoniaAPI.get().getAccountManager().getServerAccountList().add(this);
        LoniaAPI.get().getDataBaseManager().getMySQL().query("SELECT * FROM " + TABLE + " WHERE server_id='" + this.id + "'", rs -> {

            try {
                if (!rs.next()) {
                    LoniaAPI.get().getDataBaseManager().getMySQL().update("INSERT INTO " + TABLE + " (server_id, server_name, bungee_name, server_ip, server_type, server_status, server_material, connected_player) " + "VALUES ('" + this.id + "', '" + this.server.getServerName() + "', '" + ServerManager.getBungeeServerNameByServerID(this.id) + "', '" + this.server.getIp() + ":" + this.server.getPort() + "', '" + Type.DEFAULT.getName() + "', '" + this.status + "', '" + Material.AIR.getId() + "', '0')");
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    public void delete() {

        LoniaAPI.get().getAccountManager().getServerAccountList().remove(this);
    }

    public String getServerID() {

        return (String) LoniaAPI.get().getDataBaseManager().getMySQL().query("SELECT * FROM " + TABLE + " WHERE server_id='" + this.id + "'", rs -> {

            try {
                if (rs.next()) {
                    return rs.getString("server_id");
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
            return Integer.valueOf(0);
        });
    }


    public String getServerName() {

        return (String) LoniaAPI.get().getDataBaseManager().getMySQL().query("SELECT * FROM " + TABLE + " WHERE server_id='" + this.id + "'", rs -> {

            try {
                if (rs.next()) {
                    return rs.getString("server_name");
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
            return "UnDefinied";
        });
    }


    public String getBungeeName() {

        return (String) LoniaAPI.get().getDataBaseManager().getMySQL().query("SELECT * FROM " + TABLE + " WHERE server_id='" + this.id + "'", rs -> {

            try {
                if (rs.next()) {
                    return rs.getString("bungee_name");
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
            return "UnDefinied";
        });
    }


    public String getServerIP() {

        return (String) LoniaAPI.get().getDataBaseManager().getMySQL().query("SELECT * FROM " + TABLE + " WHERE server_id='" + this.id + "'", rs -> {

            try {
                if (rs.next()) {
                    return rs.getString("server_ip");
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
            return "0.0.0.0";
        });
    }


    public Type getServerType() {

        return (Type) LoniaAPI.get().getDataBaseManager().getMySQL().query("SELECT * FROM " + TABLE + " WHERE server_id='" + this.id + "'", rs -> {

            try {
                if (rs.next()) {
                    return Type.getByName(rs.getString("server_type"));
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
            return Type.DEFAULT;
        });
    }


    public void setServerType(Type type) {

        LoniaAPI.get().getDataBaseManager().getMySQL().update("UPDATE " + TABLE + " SET server_type='" + type.getName() + "' WHERE server_id='" + this.id + "'");
    }


    public Status getServerStatus() {

        return (Status) LoniaAPI.get().getDataBaseManager().getMySQL().query("SELECT * FROM " + TABLE + " WHERE server_id='" + this.id + "'", rs -> {

            try {
                if (rs.next()) {
                    return Status.getByName(rs.getString("server_status"));
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
            return Status.DEFAULT;
        });
    }


    public void setServerStatus(Status status) {

        LoniaAPI.get().getDataBaseManager().getMySQL().update("UPDATE " + TABLE + " SET server_status='" + status.getName() + "' WHERE server_id='" + this.id + "'");
    }

    public void setMaterial(Material material) {

        LoniaAPI.get().getDataBaseManager().getMySQL().update("UPDATE " + TABLE + " SET server_material='" + material.getId() + "' WHERE server_id='" + this.id + "'");
    }

    public Material getMaterial() {

        return (Material) LoniaAPI.get().getDataBaseManager().getMySQL().query("SELECT * FROM " + TABLE + " WHERE server_id='" + this.id + "'", rs -> {

            try {
                if (rs.next()) {
                    return Material.getMaterial(rs.getString("server_material"));
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
            return "0";
        });
    }

    public void setConnectedPlayer(int connected_player) {

        LoniaAPI.get().getDataBaseManager().getMySQL().update("UPDATE " + TABLE + " SET connected_player='" + connected_player + "' WHERE server_id='" + this.id + "'");
    }

    public String getConnectedPlayer() {

        return (String) LoniaAPI.get().getDataBaseManager().getMySQL().query("SELECT * FROM " + TABLE + " WHERE server_id='" + this.id + "'", rs -> {

            try {
                if (rs.next()) {
                    return rs.getString("connected_player");
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
            return "0";
        });
    }
}