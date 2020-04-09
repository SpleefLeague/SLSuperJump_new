/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.spleefleague.superjump.game.versus;

import com.spleefleague.core.menu.InventoryMenuAPI;
import com.spleefleague.core.menu.InventoryMenuItem;
import com.spleefleague.superjump.game.SJArena;
import com.spleefleague.superjump.game.versus.classic.ClassicSJArena;
import com.spleefleague.superjump.game.versus.shuffle.ShuffleSJArena;
import org.bukkit.ChatColor;
import org.bukkit.Material;

/**
 * @author NickM13
 */
public class VersusSJArena extends SJArena {

    public static InventoryMenuItem createMenu() {
        String mainColor = ChatColor.AQUA + "" + ChatColor.BOLD;
        InventoryMenuItem menuItem = InventoryMenuAPI.createItem()
                .setName(mainColor + "SuperJump: Versus")
                .setDescription("The SuperJump multiplayer experience.")
                .setDisplayItem(Material.DIAMOND_AXE, 22)
                .createLinkedContainer("Versus SuperJump Menu");
        
        menuItem.getLinkedContainer().addMenuItem(ClassicSJArena.createMenu(), 3);
        menuItem.getLinkedContainer().addMenuItem(ShuffleSJArena.createMenu(), 5);
        
        return menuItem;
    }
}
