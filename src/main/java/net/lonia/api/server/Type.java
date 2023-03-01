package net.lonia.api.server;

import java.util.Arrays;

public enum Type {
    DEFAULT("Default", "§7Default"),
    LOBBY("Lobby", "§7Lobby"),
    AREAPVP("AreaPvP", "§7AreaPvP"),
    JUMP("Jump", "§7Jump"),
    BUILD("Build", "§7Build");

    private final String name, prefix;

    Type(String name, String prefix) {

        this.name = name;
        this.prefix = prefix;
    }


    public static Type getByName(String name) { return Arrays.stream(values()).filter(r -> r.getName().equalsIgnoreCase(name)).findAny().orElse(DEFAULT); }

    public String getName() { return this.name; }

    public String getPrefix() { return this.prefix; }
}
