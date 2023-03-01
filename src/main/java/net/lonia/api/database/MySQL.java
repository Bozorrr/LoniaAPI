package net.lonia.api.database;

import net.lonia.api.utils.Level;
import org.apache.commons.dbcp2.BasicDataSource;
import org.bukkit.Bukkit;

import java.sql.*;
import java.util.function.*;

public class MySQL {

    private final BasicDataSource connectionPool;

    public MySQL(BasicDataSource connectionPool) {

        this.connectionPool = connectionPool;
    }

    public Connection getConnection() throws SQLException {

        return this.connectionPool.getConnection();
    }


    public void update(String qry) {

        try (
                Connection c = getConnection();
                PreparedStatement s = c.prepareStatement(qry)
        ) {

            s.executeUpdate();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


    public Object query(String qry, Function<ResultSet, Object> consumer) {

        try (
                Connection c = getConnection();
                PreparedStatement s = c.prepareStatement(qry);
                ResultSet rs = s.executeQuery()
        ) {

            return consumer.apply(rs);
        }
        catch (SQLException e) {
            throw new IllegalStateException(e.getMessage());
        }
    }


    public void query(String qry, Consumer<ResultSet> consumer) {

        try (
                Connection c = getConnection();
                PreparedStatement s = c.prepareStatement(qry);
                ResultSet rs = s.executeQuery()
        ) {

            consumer.accept(rs);
        }
        catch (SQLException e) {
            throw new IllegalStateException(e.getMessage());
        }
    }


    public void createTablesData_Servers() {

        Bukkit.getConsoleSender().sendMessage(Level.INFO + "Creating non-existent tables for data_servers");

        update("CREATE TABLE IF NOT EXISTS data_servers (" +
                       "`#` INT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                       "server_id VARCHAR(255), " +
                       "server_name VARCHAR(255), " +
                       "bungee_name VARCHAR(255), " +
                       "server_ip VARCHAR(255), " +
                       "server_type VARCHAR(255), " +
                       "server_status VARCHAR(255), " +
                       "server_material VARCHAR(255), " +
                       "connected_player VARCHAR(255))");
    }

    public void createTablesData_Ranks() {

        Bukkit.getConsoleSender().sendMessage(Level.INFO + "INFO Creating non-existent tables for data_ranks");

        update("CREATE TABLE IF NOT EXISTS data_ranks (" +
                       "`#` INT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                       "name VARCHAR(255), " +
                       "prefix VARCHAR(255), " +
                       "color VARCHAR(255), " +
                       "power INTEGER)"
        );
    }

    public void createTablesData_Players() {

        Bukkit.getConsoleSender().sendMessage(Level.INFO + "INFO Creating non-existent tables for data_players");

        update("CREATE TABLE IF NOT EXISTS data_players (" +
                       "`#` INT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                       "pseudo VARCHAR(255), " +
                       "uuid VARCHAR(255), " +
                       "grade VARCHAR(255), " +
                       "etoile INTEGER," +
                       "argent INTEGER)"
        );
    }
}

