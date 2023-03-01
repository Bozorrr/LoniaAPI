package net.lonia.api.tools.clearlag;

import org.bukkit.Bukkit;
import org.bukkit.entity.*;

import java.util.*;

public class ClearLag {

    public void clearLag() {

        List<Entity>     entityList      = Bukkit.getServer().getWorlds().get(0).getEntities();
        List<EntityType> whiteListEntity = new ArrayList<>();

        whiteListEntity.add(EntityType.ARMOR_STAND);
        whiteListEntity.add(EntityType.ITEM_FRAME);
        whiteListEntity.add(EntityType.SNOWBALL);

        if (entityList.size() == 0) {
            return;
        }

        for (Entity entity : entityList) {
            if (!(entity instanceof Player) &&
                    !whiteListEntity.contains(entity.getType()))
                entity.remove();
        }
    }
}