package xyz.szambo.lib.nsmenu.menu;

import lombok.Getter;
import lombok.NonNull;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import xyz.szambo.lib.nsmenu.utils.ChatUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class MenuManager implements Listener {

    @Getter
    private Inventory inventory;
    @Getter
    private String title;
    @Getter
    private int rows;
    @Getter
    private boolean cancelInvClick;
    private final Map<Integer, Consumer<InventoryClickEvent>> events = new HashMap<>();

    public void createMenu(String title, int rows, Boolean cancelInventoryClick) {
        this.title = title;
        this.rows = rows;
        this.cancelInvClick = cancelInventoryClick;
        inventory = Bukkit.createInventory(null, rows*9, ChatUtil.fixColor(title));
    }

    public void setItem(@NonNull ItemStack itemStack, int slot) {
        inventory.setItem(slot, itemStack);
    }


    public void setItemWithEvent(@NonNull ItemStack itemStack, int slot, Consumer<InventoryClickEvent> e) {
        setAction(itemStack, e, slot);
        inventory.setItem(slot, itemStack);

    }

    public void fillInventory(@NonNull ItemStack itemStack) {
        for (int i = 0; i < rows*9; i++) {
            inventory.setItem(i, itemStack);
        }
    }

    public void openMenu(Player player) {
        player.openInventory(inventory);
    }

    public void setAction(@NonNull ItemStack itemStack ,@NonNull Consumer<InventoryClickEvent> e, int slot) {
        events.put(slot, e);
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        this.events.getOrDefault(event.getRawSlot(), e -> {
            if (cancelInvClick) {
                e.setCancelled(true);
            }
        }).accept(event);

        if (cancelInvClick) {
            event.setCancelled(true);
        }
    }




}
