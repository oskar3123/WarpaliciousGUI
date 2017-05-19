package me.oskar3123.warpaliciousgui.gui;

import nl.datdenkikniet.warpalicious.handling.Warp;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

import java.util.List;

public class GUIInventoryHolder implements InventoryHolder
{

    private int page;
    private List<Warp> warpList;

    public GUIInventoryHolder(int page)
    {
        this.page = page;
    }

    @Override
    public Inventory getInventory()
    {
        return null;
    }

    public int getPage()
    {
        return page;
    }

    public Warp getWarp(int slot)
    {
        return warpList.get(slot);
    }

    public void setWarpList(List<Warp> warpList)
    {
        this.warpList = warpList;
    }

    public int getWarpCount()
    {
        return warpList.size();
    }

}
