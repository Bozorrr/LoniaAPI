package net.lonia.api.server;

import net.lonia.api.LoniaAPI;
import org.bukkit.Material;

import java.sql.SQLException;

public class Server {

    public static final String databaseName = "data_servers";


    private String id, serverName, bungeeName, type, status, prefix1, prefix2, prefix3;
    private Material material;
    private int connectedPlayers;

    public Server(String id, String serverName, String bungeeName, String type, String status, Material material, String prefix1, String prefix2, String prefix3, int connectedPlayers) {

        this.id = id;
        this.serverName = serverName;
        this.bungeeName = bungeeName;
        this.type = type;
        this.status = status;
        this.material = material;
        this.prefix1 = prefix1;
        this.prefix2 = prefix2;
        this.prefix3 = prefix3;
        this.connectedPlayers = connectedPlayers;
    }

    public String getId() { return id; }
    public String getServerName() { return serverName; }
    public String getBungeeName() { return bungeeName; }
    public Type getType() { return Type.getByName(this.type); }
    public Status getStatus() { return Status.getByName(this.status); }
    public Material getMaterial() { return this.material; }
    public String getPrefix1() { return this.prefix1; }
    public String getPrefix2() { return this.prefix2; }
    public String getPrefix3() { return this.prefix3; }

    public void setType(Type type) { this.type = type.getName(); }
    public void setStatus(Status status) { this.status = status.getName(); }

    public static void reloadServersStatus() {
        ServerManager serverManager = new ServerManager();

        for (Server server : serverManager.getServers()) {
            LoniaAPI.get().getDataBaseManager().getMySQL()
                    .query("SELECT * FROM data_servers WHERE server_name='" + server.getServerName() + "'", rs -> {
                        try {
                            if (rs.next()) {
                                Status serverStatus = Status.getByName(rs.getString("server_status"));

                                server.setStatus(serverStatus);
                            }
                        }
                        catch (SQLException e) {
                            e.printStackTrace();
                        }
                    });
        }
    }
}
