package net.lonia.api.rank;

import net.lonia.api.LoniaAPI;

import java.sql.SQLException;


public class Rank {

    private String name;
    private String prefix;
    private String color;
    private int    power;

    public Rank(String name, String prefix, String color, int power) {

        this.name = name;
        this.prefix = prefix;
        this.color = color;
        this.power = power;
    }


    public static Rank getByName(String name) { return LoniaAPI.get().getRankManager().getRanks().stream().filter(r -> r.getName().equalsIgnoreCase(name)).findAny().orElse(RankList.JOUEUR); }


    public static Rank getByPower(int power) { return LoniaAPI.get().getRankManager().getRanks().stream().filter(r -> (r.getPower() == power)).findAny().orElse(RankList.JOUEUR); }

    public void createRank() {

        LoniaAPI.get().getRankManager().getRanks().add(this);
        LoniaAPI.get().getDataBaseManager().getMySQL().query("SELECT * FROM data_ranks WHERE name='" + this.name + "'", rs -> {

            try {
                if (!rs.next()) {
                    LoniaAPI.get().getDataBaseManager().getMySQL().update("INSERT INTO data_ranks (name, prefix, color, power) " + "VALUES ('" + name + "', '" + prefix + "', '" + color + "', '" + power + "')");
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    public void delete() { LoniaAPI.get().getRankManager().getRanks().remove(this); }

    public String getName() { return this.name; }
    public String getPrefix() { return this.prefix; }
    public String getColor() { return this.color; }
    public int getPower() { return this.power; }
}


