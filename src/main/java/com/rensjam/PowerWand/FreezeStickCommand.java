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

public class FreezeStickCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            ItemStack freezeStick = new ItemStack(Material.STICK);
            ItemMeta meta = freezeStick.getItemMeta();
            meta.displayName(Component.text("Freeze Stick"));
            freezeStick.setItemMeta(meta);

            player.getInventory().addItem(freezeStick);
            player.sendMessage(Component.text("You have been given the almighty Freeze Stick!").color(TextColor.fromHexString("#FFAA00")));
        }
        return true;
    }
}
