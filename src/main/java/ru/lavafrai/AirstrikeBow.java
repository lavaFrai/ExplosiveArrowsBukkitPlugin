package ru.lavafrai;
import org.bukkit.enchantments.Enchantment;
import ru.lavafrai.Enchantments.ExplosiveArrows;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.metadata.FixedMetadataValue;



import java.util.logging.Logger;


public class AirstrikeBow implements Listener {
    private final Logger jlogger;

    private final testLavaPlugin plugin;

    public AirstrikeBow(Logger _jlogger, testLavaPlugin _plugin) {
        plugin = _plugin;
        jlogger = _jlogger;
    }

    private void doExplosion(Location position) {
        position.getWorld().createExplosion(position, 40, true);
    }

    @EventHandler
    public void onArrowHit(ProjectileHitEvent event) {
        Entity entity = event.getEntity();
        Location position = entity.getLocation();

        if (event.getEntity().getType() == EntityType.ARROW && entity.hasMetadata("arrowType")) {
            entity.remove();

            // jlogger.info("Arrow hit at " + position.getX() + " " + position.getY() + " " + position.getZ());

            doExplosion(position);
        }
    }

    @EventHandler
    public void EntityShootBowEvent(EntityShootBowEvent event) {
        if (event.getEntity() instanceof Player && event.getBow().containsEnchantment(new ExplosiveArrows())) {

            Arrow arrow = (Arrow) event.getProjectile();
            arrow.setMetadata("arrowType", new FixedMetadataValue(plugin, "airstrike") {
            });
        }
        event.getBow().addEnchantment(new ExplosiveArrows(), 1);
    }
}
