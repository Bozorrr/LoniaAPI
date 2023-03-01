package net.lonia.api.server;

import java.util.Arrays;

public enum Status {
    DEFAULT("Default", "§7Default"),
    LOADING("Loading", "§bLoading"),
    ONLINE("Online", "§aOnline"),
    STAFFONLY("StaffOnly", "§3StaffOnly"),
    RESTART("Restart", "§8Restart"),
    BETATEST("BetaTest", "§9BetaTest"),
    OFFLINE("Offline", "§cOffline");

    private final String name, prefix;

    Status(String name, String prefix) {

        this.name = name;
        this.prefix = prefix;
    }

    public static Status getByName(String name) { return Arrays.stream(values()).filter(r -> r.getName().equalsIgnoreCase(name)).findAny().orElse(DEFAULT); }

    public String getName() { return this.name; }

    public String getPrefix() { return this.prefix; }
}
