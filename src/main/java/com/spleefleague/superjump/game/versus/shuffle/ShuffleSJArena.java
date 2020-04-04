/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.spleefleague.superjump.game.versus.shuffle;

import com.spleefleague.core.annotation.DBField;
import com.spleefleague.core.game.Arena;
import static com.spleefleague.core.game.Arena.getArenas;
import com.spleefleague.core.menu.InventoryMenu;
import com.spleefleague.superjump.SuperJump;
import com.spleefleague.superjump.game.SJMode;
import com.spleefleague.superjump.game.versus.VersusSJArena;
import org.bukkit.ChatColor;
import org.bukkit.Material;

/**
 * @author NickM13
 */
public class ShuffleSJArena extends VersusSJArena {
    
     public enum ShuffleDifficulty {
         EASY(0),
         MEDIUM(1),
         HARD(2),
         INSANE(3);
         
         int difficulty;
         
         ShuffleDifficulty(int dif) {
             difficulty = dif;
         }
     }
    
    @DBField
    protected Integer jumpcount;
    @DBField
    protected ShuffleDifficulty difficulty;
    
    public ShuffleSJArena() {
        mode = SJMode.SHUFFLE.getArenaMode();
    }
    
    @Override
    public int getDifficulty() {
        return difficulty.difficulty;
    }
    
    public int getJumpCount() {
        return jumpcount;
    }
    
    public static InventoryMenu createMenu() {
        String mainColor = ChatColor.AQUA + "" + ChatColor.BOLD;
        InventoryMenu menu = InventoryMenu.createMenu()
                .setTitle("Shuffle SuperJump Menu")
                .setName(mainColor + "SuperJump: Shuffle")
                .setDescription("Shuffle Description")
                .setDisplayItem(Material.DIAMOND_AXE, 20);
        
        getArenas(SJMode.SHUFFLE.getArenaMode()).forEach((String s, Arena arena) -> menu.addMenuItem(InventoryMenu.createItem()
                .setName(arena.getDisplayName())
                .setDescription(cp -> arena.getDescription())
                .setDisplayItem(Material.DIAMOND_AXE, 16 + arena.getDifficulty())
                .setAction(cp -> SuperJump.getInstance().queuePlayer(SJMode.SHUFFLE.getArenaMode(), SuperJump.getInstance().getPlayers().get(cp), arena))));
        
        return menu;
    }
    
}
