package ru.lavafrai;

import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.logging.Logger;


public class Fireballs implements Listener {
    private final Logger jlogger;

    public Fireballs(Logger _jlogger) {
        jlogger = _jlogger;
    }


    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event){
        if (event.getAction() == Action.RIGHT_CLICK_AIR && event.getPlayer().getInventory().getItemInMainHand().getType() == Material.SAND){
            Player player = event.getPlayer();

            player.getInventory().getItemInMainHand().setAmount(player.getInventory().getItemInMainHand().getAmount() - 1);

            double pitch = ((player.getLocation().getPitch() + 90) * Math.PI) / 180;
            double yaw = ((player.getLocation().getYaw() + 90) * Math.PI) / 180;

            double x = Math.sin(pitch) * Math.cos(yaw);
            double y = Math.sin(pitch) * Math.sin(yaw);
            double z = Math.cos(pitch);

            Vector vector = new Vector(x, z, y);

            Location spawnAt = event.getPlayer().getEyeLocation().toVector().add(event.getPlayer().getEyeLocation().getDirection()).toLocation(event.getPlayer().getWorld());

            Fireball fireball = event.getPlayer().getWorld().spawn(spawnAt, Fireball.class);
            fireball.setDirection(vector.multiply(10));
            fireball.setIsIncendiary(true);
            fireball.setYield(50);
            fireball.setVelocity(fireball.getDirection().multiply(50));
        }
    }
}
