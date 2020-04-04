/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.spleefleague.superjump.game.conquest;

import com.spleefleague.core.annotation.DBField;
import com.spleefleague.core.annotation.DBLoad;
import com.spleefleague.core.menu.InventoryMenu;
import com.spleefleague.superjump.game.SJArena;
import com.spleefleague.superjump.game.SJMode;
import java.util.Collections;
import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.Material;

/**
 * @author NickM13
 */
public class ConquestSJArena extends SJArena {
    
    @DBField
    protected List<Double> starTimes;
    
    @DBLoad(fieldname="starTimes")
    protected void loadStarTimes(List<Double> times) {
        Collections.sort(times);
        starTimes = times;
    }
    
    public int getStar(Double time) {
        int star = 3;
        for (Double t : starTimes) {
            if (time < t) {
                return star;
            }
            star--;
        }
        return 0;
    }
    
    public ConquestSJArena() {
        mode = SJMode.CONQUEST.getArenaMode();
    }
    
    public static InventoryMenu createMenu() {
        String mainColor = ChatColor.AQUA + "" + ChatColor.BOLD;
        InventoryMenu menu = InventoryMenu.createMenu()
                .setTitle("Conquest SuperJump Menu")
                .setName(mainColor + "SuperJump: Conquest")
                .setDescription("Conquest Description")
                .setDisplayItem(Material.DIAMOND_AXE, 21);
        
        ConquestPack.init();
        for (ConquestPack pack : ConquestPack.getAllPacks()) {
            menu.addMenuItem(pack.createMenu());
        }
        
        return menu;
    }
}
