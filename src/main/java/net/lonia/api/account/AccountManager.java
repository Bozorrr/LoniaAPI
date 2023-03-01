package net.lonia.api.account;

import java.util.*;

public class AccountManager {

    private List<PlayerAccount> playerAccountList =new ArrayList<>();
    private List<ServerAccount> serverAccountList = new ArrayList<>();

    public List<PlayerAccount> getPlayerAccountList() { return this.playerAccountList; }
    public List<ServerAccount> getServerAccountList() { return this.serverAccountList; }
}
