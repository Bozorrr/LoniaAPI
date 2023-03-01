package net.lonia.api.hologram;

import net.lonia.api.LoniaAPI;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;

import java.util.*;

public class Hologram {

    private final List<EntityArmorStand> armorStands = new ArrayList<>();
    private final List<String>           lines       = new ArrayList<>();
    private final Location               location;

    public Hologram(Location location) {

        this.location = location;
    }

    public void create(Player player) {

        int i = 0;
        for (String line : lines) {
            EntityArmorStand armorStand = new EntityArmorStand(((CraftWorld) location.getWorld()).getHandle(), location.getX(), (location.getY()+ 0.5) - (i * 0.25), location.getZ());
            armorStand.setCustomName(line);
            armorStand.setCustomNameVisible(true);
            armorStand.setInvisible(true);
            armorStand.setSmall(true);
            armorStands.add(armorStand);

            PacketPlayOutSpawnEntityLiving packet = new PacketPlayOutSpawnEntityLiving(armorStand);
            ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);

            i++;
        }
    }

    public void remove(Player player) {

        for (EntityArmorStand armorStand : armorStands) {
            PacketPlayOutEntityDestroy packet = new PacketPlayOutEntityDestroy(armorStand.getId());
            ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
        }
    }

    public void addLine(String line, Player player) {

        lines.add(line);

        int              i          = lines.size() - 1;
        EntityArmorStand armorStand = new EntityArmorStand(((CraftWorld) location.getWorld()).getHandle(), location.getX(), location.getY() - (i * 0.25), location.getZ());
        armorStand.setCustomName(line);
        armorStand.setCustomNameVisible(true);
        armorStand.setInvisible(true);
        armorStand.setSmall(true);
        armorStands.add(armorStand);

        PacketPlayOutSpawnEntityLiving packet = new PacketPlayOutSpawnEntityLiving(armorStand);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);

        for (Player p : location.getWorld().getPlayers()) {
            if (p != player) {
                PacketPlayOutEntityMetadata metadataPacket = new PacketPlayOutEntityMetadata(armorStand.getId(), armorStand.getDataWatcher(), true);
                ((CraftPlayer) p).getHandle().playerConnection.sendPacket(metadataPacket);
            }
        }
    }

    public void removeLine(int index, Player player) {

        EntityArmorStand armorStand = armorStands.get(index);
        armorStands.remove(index);
        lines.remove(index);

        PacketPlayOutEntityDestroy packet = new PacketPlayOutEntityDestroy(armorStand.getId());
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);

        for (Player p : location.getWorld().getPlayers()) {
            if (p != player) {
                PacketPlayOutEntityMetadata metadataPacket = new PacketPlayOutEntityMetadata(armorStand.getId(), armorStand.getDataWatcher(), true);
                ((CraftPlayer) p).getHandle().playerConnection.sendPacket(metadataPacket);
            }
        }
    }


    public void onClick(Player player) {

        for (int i = 0; i < armorStands.size(); i++) {
            EntityArmorStand armorStand = armorStands.get(i);
            String           text       = lines.get(i);
            Location         location   = armorStand.getBukkitEntity().getLocation();

            // Ajouter un événement clic à l'entité armor stand
            armorStand.getBukkitEntity().setMetadata("hologram", new FixedMetadataValue(LoniaAPI.get().getPlugin(), this));
            armorStand.getBukkitEntity().setMetadata("line", new FixedMetadataValue(LoniaAPI.get().getPlugin(), text));
            armorStand.getBukkitEntity().setMetadata("player", new FixedMetadataValue(LoniaAPI.get().getPlugin(), player));

            // Créer un paquet pour ajouter l'événement clic à l'entité armor stand
            PacketPlayOutEntityMetadata metadataPacket = new PacketPlayOutEntityMetadata(armorStand.getId(), armorStand.getDataWatcher(), true);
            ((CraftPlayer) player).getHandle().playerConnection.sendPacket(metadataPacket);
        }
    }

    public List<String> getLines() { return lines; }
}
