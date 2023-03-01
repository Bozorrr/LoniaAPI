package net.lonia.api.account;

import net.lonia.api.LoniaAPI;
import net.lonia.api.rank.*;
import org.bukkit.entity.Player;

import java.sql.SQLException;

public class PlayerAccount {

    private static final String TABLE = "data_players";
    private final        Player player;
    private final        String uuid;

    public PlayerAccount(Player player) {

        this.player = player;
        this.uuid = player.getUniqueId().toString();
    }


    public static PlayerAccount getAccount(Player player) {

        return LoniaAPI.get().getAccountManager().getPlayerAccountList().stream().filter(a -> (a.getPlayer() == player)).findFirst().get();
    }

    public Player getPlayer() {

        return this.player;
    }

    public void setup() {

        LoniaAPI.get().getAccountManager().getPlayerAccountList().add(this);
        LoniaAPI.get().getDataBaseManager().getMySQL().query("SELECT * FROM " + TABLE + " WHERE uuid='" + this.uuid + "'", rs -> {

            try {
                if (!rs.next()) {
                    LoniaAPI.get().getDataBaseManager().getMySQL().update("INSERT INTO " + TABLE + " (pseudo, uuid, grade, etoile, argent) " + "VALUES ('" + player.getDisplayName() + "', '" + player.getUniqueId() + "', '" + RankList.JOUEUR.getName() + "', '0', '0')");
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    public void delete() {

        LoniaAPI.get().getAccountManager().getPlayerAccountList().remove(this);
    }


    public Rank getRank() {

        return (Rank) LoniaAPI.get().getDataBaseManager().getMySQL().query("SELECT * FROM " + TABLE + " WHERE uuid='" + this.uuid + "'", rs -> {

            try {
                if (rs.next()) {
                    return Rank.getByName(rs.getString("grade"));
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
            return RankList.JOUEUR;
        });
    }


    public void setRank(Rank rank) {

        LoniaAPI.get().getDataBaseManager().getMySQL()
                .update("UPDATE " + TABLE + " SET grade='" + rank.getName() + "' WHERE uuid='" + this.uuid + "'");
    }


    public int getEtoile() {

        return (int) LoniaAPI.get().getDataBaseManager().getMySQL().query("SELECT * FROM " + TABLE + " WHERE uuid='" + this.uuid + "'", rs -> {

            try {
                if (rs.next()) {
                    return rs.getInt("etoile");
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
            return 0;
        });
    }


    public void setEtoile(int etoile) {

        LoniaAPI.get().getDataBaseManager().getMySQL()
                .update("UPDATE " + TABLE + " SET etoile='" + etoile + "' WHERE uuid='" + this.uuid + "'");
    }

    public void addEtoile(int etoile) {

        setEtoile(getEtoile() + etoile);
    }

    public void removeEtoile(int etoile) {

        setEtoile((getEtoile() < etoile) ? 0 : (getEtoile() - etoile));
    }


    public int getArgent() {

        return (int) LoniaAPI.get().getDataBaseManager().getMySQL().query("SELECT * FROM " + TABLE + " WHERE uuid='" + this.uuid + "'", rs -> {

            try {
                if (rs.next()) {
                    return rs.getInt("argent");
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
            return 0;
        });
    }


    public void setArgent(int argent) {

        LoniaAPI.get().getDataBaseManager().getMySQL()
                .update("UPDATE " + TABLE + " SET argent='" + argent + "' WHERE uuid='" + this.uuid + "'");
    }

    public void addArgent(int argent) {

        setArgent(getArgent() + argent);
    }

    public void removeArgent(int argent) {

        setArgent((getArgent() < argent) ? 0 : (getArgent() - argent));
    }
}