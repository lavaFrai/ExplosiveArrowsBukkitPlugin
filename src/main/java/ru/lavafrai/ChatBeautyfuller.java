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

public class ChatBeautyfuller implements Listener {
    private final Logger jlogger;

    public ChatBeautyfuller(Logger _jlogger) {
        jlogger = _jlogger;
    }

    @EventHandler
    public void onChat(AsyncChatEvent event) {
        event.renderer((source, sourceDisplayName, message, viewer) ->
                Component.text()
                        .append(sourceDisplayName.color(TextColor.fromHexString("#0000ff")))
                        .append(Component.text(" > "))
                        .append(message)
                        .build());
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        event.quitMessage(Component.text()
                .append(Component.text("["))
                .append(Component.text(event.getPlayer().getName(), TextColor.fromHexString("#ff2803")))
                .append(Component.text("] "))
                .append(Component.text(" leave the game"))
                .build());
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        event.joinMessage(Component.text()
                .append(Component.text("["))
                .append(Component.text(event.getPlayer().getName(), TextColor.fromHexString("#28ff03")))
                .append(Component.text("] "))
                .append(Component.text(" joined the game"))
                .build());
    }

    @EventHandler
    public void onBlockPlaced(BlockPlaceEvent event) {
         jlogger.info("[" + event.getPlayer().getName() + "]" + " placed " + event.getBlock().getBlockData().getAsString() + "block at " +
                 event.getBlock().getX() + " " +
                 event.getBlock().getY() + " " +
                 event.getBlock().getZ());
    }

    /*@EventHandler
    public void onItemDropped(BlockBreakEvent event) {
        if (event.getBlock().getType() == Material.SAND ||
            event.getBlock().getType() == Material.DIRT) {

            event.setDropItems(false);

            event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), new ItemStack(Material.DIAMOND, 1));
            event.setExpToDrop(1);
        }
    }*/

    @EventHandler
    public void onCreeperExplode(EntityExplodeEvent event) {
        if (event.getEntity().getType().equals(EntityType.CREEPER)) {

            event.setCancelled(true);
            event.getLocation().getWorld().createExplosion(event.getLocation(), 100);

        }
    }

}