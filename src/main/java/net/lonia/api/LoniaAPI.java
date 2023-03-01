package net.lonia.api;

import net.lonia.api.account.AccountManager;
import net.lonia.api.database.*;
import net.lonia.api.rank.RankManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Map;

public class LoniaAPI {

    private static LoniaAPI instance;
    private JavaPlugin plugin;

    public LoniaAPI(JavaPlugin plugin) {
        instance = this;
        this.plugin = plugin;
    }


    public static LoniaAPI get() { return instance; }
    public JavaPlugin getPlugin() { return this.plugin; }
    public DataBaseManager getDataBaseManager() { return new DataBaseManager(); }
    public AccountManager getAccountManager() { return new AccountManager(); }
    public RankManager getRankManager() { return new RankManager(); }
}