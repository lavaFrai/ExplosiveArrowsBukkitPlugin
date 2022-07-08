package ru.lavafrai;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.plugin.java.JavaPlugin;

import ru.lavafrai.Enchantments.ExplosiveArrows;

import java.lang.reflect.Field;
import java.util.logging.Logger;

import static org.bukkit.enchantments.Enchantment.registerEnchantment;


public class testLavaPlugin extends JavaPlugin {
    @Getter
    private static testLavaPlugin instance; // Класс плагина.
    @Getter
    private static Logger jlogger; // Логгер.
    @Override
    public void onEnable() {
        // Устанавливаем наши статичные переменные:
        instance = this;
        jlogger = super.getLogger();
        // Выводим в консоль сообщение Hello from LiteSMT
        // от имени плагина.
        jlogger.info("Hello by. lava_frai!");

        ExplosiveArrows explosiveArrows = new ExplosiveArrows();

        try {
            Field f = Enchantment.class.getDeclaredField("acceptingNew");
            f.setAccessible(true);
            f.set(null, true);
        } catch (Exception e) {
            e.printStackTrace();
        }


        try {
            registerEnchantment(explosiveArrows);
        } catch (Exception e) {}

        Bukkit.getPluginManager().registerEvents(new ChatBeautyfuller(jlogger), this);
        Bukkit.getPluginManager().registerEvents(new CustomEnchantments(jlogger), this);
        Bukkit.getPluginManager().registerEvents(new Fireballs(jlogger), this);
        Bukkit.getPluginManager().registerEvents(new AirstrikeBow(jlogger, this), this);
        Bukkit.getPluginManager().registerEvents(explosiveArrows, this);
    }

    public static testLavaPlugin getPlugin(){
        return instance;
    }

}
