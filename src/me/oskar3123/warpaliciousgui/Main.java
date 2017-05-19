package me.oskar3123.warpaliciousgui;

import me.oskar3123.warpaliciousgui.commands.WarpCommand;
import me.oskar3123.warpaliciousgui.events.InventoryListener;
import me.oskar3123.warpaliciousgui.gui.GUIInventoryHolder;
import nl.datdenkikniet.warpalicious.WarpaliciousPlugin;
import nl.datdenkikniet.warpalicious.handling.Warp;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main extends JavaPlugin
{

    private static final String INV_NAME = "Warps";

    //public Inventory inventory;
    private WarpaliciousPlugin warpalicious;

    public void onEnable()
    {
        warpalicious = (WarpaliciousPlugin) Bukkit.getPluginManager().getPlugin("Warpalicious");
        warpalicious.getCommand("warp").setExecutor(new WarpCommand(this, warpalicious.getStrings(), warpalicious.getWarpHandler()));
        Bukkit.getPluginManager().registerEvents(new InventoryListener(warpalicious.getWarpHandler(), warpalicious.getStrings()), this);
    }

    public Inventory createGUI(Player player, int page)
    {
        page--;

        List<Warp> warpList = warpalicious.getWarpHandler().getWarps().stream()
                .filter(warp -> !warp.isPrivate())
                .collect(Collectors.toList());
        warpList.addAll(warpalicious.getWarpHandler().getWarps().stream()
                .filter(Warp::isPrivate)
                .filter(warp -> warp.getOwner().equals(player.getUniqueId()))
                .collect(Collectors.toList()));

        GUIInventoryHolder holder = new GUIInventoryHolder(page + 1);
        Inventory inventory = Bukkit.createInventory(holder, 54, INV_NAME);

        List<Warp> holderList = new ArrayList<>();
        for (int i = page * 36; i < (page * 36) + 36; i++)
        {
            if (warpList.size() - 1 < i) break;
            Warp warp = warpList.get(i);
            holderList.add(warp);
            inventory.addItem(createItem(Material.IRON_BLOCK, ChatColor.YELLOW + warp.getName()));
        }
        holder.setWarpList(holderList);

        for (int i = 36; i < 45; i++)
        {
            inventory.setItem(i, createItem(Material.STAINED_GLASS_PANE, ChatColor.WHITE + ""));
        }

        inventory.setItem(53, createItem(Material.BARRIER, ChatColor.RED + "Close"));
        return inventory;
    }

    private ItemStack createItem(Material material, String name)
    {
        ItemStack itemStack = new ItemStack(material);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(name);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

}
