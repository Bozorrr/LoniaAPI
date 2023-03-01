package net.lonia.api.tools.itembuilder;

import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.*;

import java.util.*;

public class ItemBuilder {

    private final ItemStack is;

    public ItemBuilder(Material m) {

        this(m, 1);
    }

    public ItemBuilder(ItemStack is) {

        this.is = is;
    }

    public ItemBuilder(Material m, int amount) {

        is = new ItemStack(m, amount);
    }

    public ItemBuilder(Material m, int amount, int meta) {

        is = new ItemStack(m, amount, (short) meta);
    }

    public ItemBuilder setDurability(short dur) {

        is.setDurability(dur);
        return this;
    }

    public ItemBuilder setName(String name) {

        ItemMeta im = is.getItemMeta();
        im.setDisplayName(name);
        is.setItemMeta(im);
        return this;
    }

    public ItemBuilder addUnsafeEnchantment(Enchantment ench, int level) {

        is.addUnsafeEnchantment(ench, level);
        return this;
    }

    public ItemBuilder removeEnchantment(Enchantment ench) {

        is.removeEnchantment(ench);
        return this;
    }

    public ItemBuilder setSkullOwner(String owner) {

        try {
            SkullMeta im = (SkullMeta) is.getItemMeta();
            im.setOwner(owner);
            is.setItemMeta(im);
        }
        catch (ClassCastException ignored) {
        }
        return this;
    }

    public ItemBuilder addEnchant(Enchantment ench, int level) {

        ItemMeta im = is.getItemMeta();
        im.addEnchant(ench, level, true);
        is.setItemMeta(im);
        return this;
    }

    public ItemBuilder addItemFlag(ItemFlag itemFlag) {

        ItemMeta im = is.getItemMeta();
        im.addItemFlags(itemFlag);
        is.setItemMeta(im);
        return this;
    }

    public ItemBuilder setInfinityDurability() {

        is.setDurability(Short.MAX_VALUE);
        return this;
    }

    public ItemBuilder setLore(String... lore) {

        ItemMeta im = is.getItemMeta();
        im.setLore(Arrays.asList(lore));
        is.setItemMeta(im);
        return this;
    }

    public ItemBuilder setLore(List<String> lore) {

        ItemMeta im = is.getItemMeta();
        im.setLore(lore);
        is.setItemMeta(im);
        return this;
    }

    @SuppressWarnings("deprecation")
    public ItemBuilder setWoolColor(DyeColor color) {

        if (!is.getType().equals(Material.WOOL))
            return this;
        this.is.setDurability(color.getWoolData());
        return this;
    }

    public ItemBuilder setBannerColor(DyeColor color) {

        BannerMeta meta = (BannerMeta) is.getItemMeta();
        meta.setBaseColor(color);
        is.setItemMeta(meta);
        return this;
    }

    public ItemBuilder setLeatherArmorColor(Color color) {

        try {
            LeatherArmorMeta im = (LeatherArmorMeta) is.getItemMeta();
            im.setColor(color);
            is.setItemMeta(im);
        }
        catch (ClassCastException expected) {
        }
        return this;
    }

    public ItemStack toItemStack() {

        return is;
    }

}