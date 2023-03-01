package net.lonia.api.rank;

import java.util.*;

public class RankManager {

    private final List<Rank> ranks = new ArrayList<>();

    public void onEnable() {
        new RankList().createRanks();
    }

    public void onDisable() {
        new RankList().removeRanks();
    }

    public List<Rank> getRanks() { return this.ranks; }

}
