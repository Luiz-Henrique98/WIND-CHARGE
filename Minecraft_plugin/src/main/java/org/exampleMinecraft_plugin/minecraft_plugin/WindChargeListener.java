package org.exampleMinecraft_plugin.minecraft_plugin;


import org.bukkit.Particle;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

public class WindChargeListener implements Listener {
    private final JavaPlugin plugin;

    public WindChargeListener(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onProjectileLaunch(ProjectileLaunchEvent event) {
        Projectile projectile = event.getEntity();

        if (projectile.getType() == EntityType.ARROW) {
            FileConfiguration config = plugin.getConfig();
            double speedMultiplier = config.getDouble("speedMultiplier");

            Vector velocity = projectile.getVelocity().multiply(speedMultiplier);
            projectile.setVelocity(velocity);

            if (config.getBoolean("addParticles")) {
                projectile.getWorld().spawnParticle(Particle.CRIT, projectile.getLocation(), 30, 0.5, 0.5, 0.5, 0.1);
            }
        }
    }

    @EventHandler
    public void onProjectileHit(ProjectileHitEvent event) {
        Projectile projectile = event.getEntity();

        if (projectile.getType() == EntityType.ARROW) {
            FileConfiguration config = plugin.getConfig();
            double explosionPower = config.getDouble("explosionPower");

            if (explosionPower > 0) {
                projectile.getWorld().createExplosion(projectile.getLocation(), (float) explosionPower);
            }
        }
    }
}
