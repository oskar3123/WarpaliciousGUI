package me.oskar3123.warpaliciousgui.commands;

import me.oskar3123.warpaliciousgui.Main;
import nl.datdenkikniet.warpalicious.config.messages.Strings;
import nl.datdenkikniet.warpalicious.handling.WarpHandler;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WarpCommand implements CommandExecutor
{

    private Main plugin;
    //private Strings strings;
    //private WarpHandler warpHandler;

    public WarpCommand(Main plugin, Strings strings, WarpHandler warpHandler)
    {
        this.plugin = plugin;
        //this.strings = strings;
        //this.warpHandler = warpHandler;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        if (!(sender instanceof Player))
        {
            sender.sendMessage("Player only!");
            return true;
        }
        int page = args.length > 0 ? Integer.valueOf(args[0]) : 1;
        Player player = (Player) sender;
        player.openInventory(plugin.createGUI(player, page));
        //sender.sendMessage("opened xdd");
        return true;
    }

}
