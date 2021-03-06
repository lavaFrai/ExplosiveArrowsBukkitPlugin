package ru.lavafrai.Enchantments;

import io.papermc.paper.enchantments.EnchantmentRarity;
import net.kyori.adventure.text.Component;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.EntityCategory;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import ru.lavafrai.testLavaPlugin;

import java.util.Set;


public class ExplosiveArrows extends Enchantment implements Listener {
    public ExplosiveArrows() {
        super(new NamespacedKey(testLavaPlugin.getPlugin(), "ExplosiveArrows"));
    }

    @Override
    @NotNull
    public EnchantmentTarget getItemTarget() {
        return EnchantmentTarget.BOW;
    }

    @Override
    @NotNull
    public String getName() {
        return "Explosive Arrow";
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public int getStartLevel() {
        return 1;
    }

    @Override
    public @NotNull NamespacedKey getKey() {
        return super.getKey();
    }

    @Override
    public boolean isTreasure() {
        return false;
    }

    @Override
    public boolean isCursed() {
        return false;
    }

    @Override
    public boolean conflictsWith(Enchantment other) {
        if (other.getKey() == Enchantment.ARROW_FIRE.getKey()) {
            return true;
        }

        return false;
    }

    @Override
    public boolean canEnchantItem(ItemStack item) {
        return true;
    }

    // Autogenerated
    @Override
    public @NotNull Component displayName(int level) {
        return null;
    }

    @Override
    public boolean isTradeable() {
        return false;
    }

    @Override
    public boolean isDiscoverable() {
        return false;
    }

    @Override
    public @NotNull EnchantmentRarity getRarity() {
        return null;
    }

    @Override
    public float getDamageIncrease(int level, @NotNull EntityCategory entityCategory) {
        return 0;
    }

    @Override
    public @NotNull Set<EquipmentSlot> getActiveSlots() {
        return null;
    }
}
