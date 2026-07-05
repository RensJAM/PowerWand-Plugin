package com.rensjam.PowerWand;

import com.destroystokyo.paper.ParticleBuilder;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.UUID;

public final class PowerWand extends JavaPlugin {

    private final HashMap<UUID, Integer> lastUsed = new HashMap<>();
    private final HashMap<UUID, Integer> lastFrozen = new HashMap<>();

    @Override
    public void onEnable() {
        saveDefaultConfig();

        this.getCommand("lightningstick").setExecutor(new LightningStickCommand());
        this.getCommand("freezestick").setExecutor(new FreezeStickCommand());

        getServer().getPluginManager().registerEvents(new StickListener(), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public class StickListener implements Listener {

        @EventHandler
        public void onRightClick(PlayerInteractEvent event) {

            Player player = event.getPlayer();

            if (player.getInventory().getItemInMainHand().getType() == Material.STICK) {
                if (player.getInventory().getItemInMainHand().getItemMeta().hasDisplayName()) {
                    Component stickName = player.getInventory().getItemInMainHand().getItemMeta().displayName();
                    String plainStickName = PlainTextComponentSerializer.plainText().serialize(stickName);

                    if (plainStickName.equals("Lightning Stick")) {
                        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {

                            UUID uuid = player.getUniqueId();

                            int currentTick = Bukkit.getCurrentTick();
                            int cooldownTime = getConfig().getInt("cooldownTime") * 20;
                            int lastTick = lastUsed.getOrDefault(uuid, 0);

                            if (currentTick - lastTick >= cooldownTime) {
                                int maxReach = getConfig().getInt("lightningRange");
                                Block block = player.getTargetBlockExact(maxReach);

                                if (block != null) {

                                    World world = player.getWorld();
                                    world.strikeLightning(block.getLocation());
                                    lastUsed.put(uuid, currentTick);
                                }
                            } else {
                                player.sendMessage("§cCooldown!");
                            }
                        }
                    }

                    else if (plainStickName.equals("Freeze Stick")) {
                        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {

                            UUID uuid = player.getUniqueId();

                            int currentTick = Bukkit.getCurrentTick();
                            int cooldownTime = getConfig().getInt("cooldownTime") * 20;
                            int freezeTime = getConfig().getInt("freezeTime") * 20;
                            int lastTick = lastUsed.getOrDefault(uuid, 0);

                            if (currentTick - lastTick >= cooldownTime) {
                                int maxReach = getConfig().getInt("freezeRange");
                                Entity entity = player.getTargetEntity(maxReach);

                                if (entity != null) {

                                    entity.setVelocity(new Vector(0, 0, 0));
                                    entity.setGravity(false);

                                    Bukkit.getScheduler().runTaskLater(
                                            com.rensjam.PowerWand.PowerWand.this,
                                            () -> entity.setGravity(true),
                                            freezeTime
                                    );

                                    new ParticleBuilder(Particle.SNOWFLAKE)
                                            .location(entity.getLocation().add(0, 1, 0))
                                            .count(3)
                                            .offset(0.5, 0.5, 0.5)
                                            .extra(0.0)
                                            .receivers(64)
                                            .spawn();

                                    lastFrozen.put(entity.getUniqueId(), currentTick);
                                    lastUsed.put(uuid, currentTick);
                                }
                            } else {
                                player.sendMessage("§cCooldown!");
                            }
                        }
                    }
                }
            }
        }
    }
}