package me.oskar3123.warpaliciousgui.events;

import me.oskar3123.warpaliciousgui.gui.GUIInventoryHolder;
import nl.datdenkikniet.warpalicious.config.messages.Strings;
import nl.datdenkikniet.warpalicious.handling.TeleportMode;
import nl.datdenkikniet.warpalicious.handling.Warp;
import nl.datdenkikniet.warpalicious.handling.WarpHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.InventoryHolder;

public class InventoryListener implements Listener
{

    //private WarpHandler handler;
    private Strings strings;

    public InventoryListener(WarpHandler handler, Strings strings)
    {
        //this.handler = handler;
        this.strings = strings;
    }

    @EventHandler
    public void inv(InventoryClickEvent event)
    {
        InventoryHolder inventoryHolder = event.getInventory().getHolder();
        if (!(inventoryHolder instanceof GUIInventoryHolder))
        {
            return;
        }
        GUIInventoryHolder holder = (GUIInventoryHolder) inventoryHolder;
        event.setCancelled(true);

        if (holder.getWarpCount() < event.getSlot() + 1 && event.getSlot() < 36)
        {
            return;
        }

        Player player = (Player) event.getWhoClicked();

        if (event.getSlot() < 36)
        {
            Warp warp = holder.getWarp(event.getSlot());
            if (warp == null) return;
            warp.warp(player, TeleportMode.COMMAND, strings);
        }
        else if (event.getSlot() == 53)
        {
            player.closeInventory();
        }
    }

    @EventHandler
    public void drag(InventoryDragEvent event)
    {
        if (!(event.getInventory().getHolder() instanceof GUIInventoryHolder))
        {
            return;
        }
        event.setCancelled(true);
    }

}
