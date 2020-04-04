/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.spleefleague.superjump.game.versus;

import com.spleefleague.core.menu.InventoryMenu;
import com.spleefleague.superjump.game.SJArena;
import com.spleefleague.superjump.game.versus.classic.ClassicSJArena;
import com.spleefleague.superjump.game.versus.shuffle.ShuffleSJArena;
import org.bukkit.ChatColor;
import org.bukkit.Material;

/**
 * @author NickM13
 */
public class VersusSJArena extends SJArena {

    public static InventoryMenu createMenu() {
        String mainColor = ChatColor.AQUA + "" + ChatColor.BOLD;
        InventoryMenu menu = InventoryMenu.createMenu()
                .setTitle("Versus SuperJump Menu")
                .setName(mainColor + "SuperJump: Versus")
                .setDescription("The SuperJump multiplayer experience.")
                .setDisplayItem(Material.DIAMOND_AXE, 22);
        
        menu.addMenuItem(ClassicSJArena.createMenu(), 3);
        menu.addMenuItem(ShuffleSJArena.createMenu(), 5);
        
        return menu;
    }
}
