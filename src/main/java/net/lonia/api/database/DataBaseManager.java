package net.lonia.api.database;

import org.apache.commons.dbcp2.BasicDataSource;
import org.bukkit.Bukkit;

import java.util.logging.Level;

public class DataBaseManager {

    private BasicDataSource connectionPool;
    private MySQL mySQL;

    public DataBaseManager() {

        Bukkit.getLogger().log(Level.INFO, "Connection to MySQL in progress..");
        this.connectionPool = new BasicDataSource();
        this.connectionPool.setDriverClassName("com.mysql.jdbc.Driver");
        this.connectionPool.setUsername("root");
        this.connectionPool.setPassword("");
        this.connectionPool.setUrl("jdbc:mysql://localhost:3306/lonia?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true");
        this.connectionPool.setInitialSize(1);
        this.connectionPool.setMaxTotal(10);
        mySQL = new MySQL(connectionPool);
        mySQL.createTablesData_Servers();
        mySQL.createTablesData_Ranks();
        mySQL.createTablesData_Players();
    }

    public MySQL getMySQL() { return mySQL; }
}
