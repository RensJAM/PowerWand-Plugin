package com.rensjam.PowerWand;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.awt.*;

public class LightningStickCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            ItemStack lightningStick = new ItemStack(Material.STICK);
            ItemMeta meta = lightningStick.getItemMeta();
            meta.displayName(Component.text("Lightning Stick"));
            lightningStick.setItemMeta(meta);

            player.getInventory().addItem(lightningStick);
            player.sendMessage(Component.text("You have been given the almighty Lightning Stick!").color(TextColor.fromHexString("#FFAA00")));
        }
        return true;
    }
}
