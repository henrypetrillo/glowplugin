package comeanhur.me;

import com.sun.jdi.CharType;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class AnhursGlowPlugin extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getLogger().info("AnhursGlowPlugin has been enabled.");
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        getLogger().info("AnhursGlowPlugin has been disabled.");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        // Check if command was run by anything besides a player
        if (sender instanceof Player ) {

            // Execute openGUI function
            openGUI((Player) sender);
        } else {
            sender.sendMessage("Only players can use this command!");
        }
        return true;
    }
    private void openGUI(Player player) {
        Inventory gui = Bukkit.createInventory(player, 9, "Select a glow color...");

        ItemStack red_wool = new ItemStack(Material.RED_WOOL);
        ItemMeta redMeta = red_wool.getItemMeta();
        redMeta.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "Red");
        red_wool.setItemMeta(redMeta);
        gui.addItem(red_wool);

        ItemStack orange_wool = new ItemStack(Material.ORANGE_WOOL);
        ItemMeta orangeMeta = orange_wool.getItemMeta();
        orangeMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Orange");
        orange_wool.setItemMeta(orangeMeta);
        gui.addItem(orange_wool);

        ItemStack yellow_wool = new ItemStack(Material.YELLOW_WOOL);
        ItemMeta yellowMeta = yellow_wool.getItemMeta();
        yellowMeta.setDisplayName(ChatColor.YELLOW + "" + ChatColor.BOLD + "Yellow");
        yellow_wool.setItemMeta(yellowMeta);
        gui.addItem(yellow_wool);

        ItemStack green_wool = new ItemStack(Material.GREEN_WOOL);
        ItemMeta greenMeta = green_wool.getItemMeta();
        greenMeta.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "Green");
        green_wool.setItemMeta(greenMeta);
        gui.addItem(green_wool);

        ItemStack blue_wool = new ItemStack(Material.BLUE_WOOL);
        ItemMeta blueMeta = blue_wool.getItemMeta();
        blueMeta.setDisplayName(ChatColor.BLUE + "" + ChatColor.BOLD + "Blue");
        blue_wool.setItemMeta(blueMeta);
        gui.addItem(blue_wool);

        ItemStack barrier = new ItemStack(Material.BARRIER);
        ItemMeta barrierMeta = barrier.getItemMeta();
        barrierMeta.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "Remove your glow");
        barrier.setItemMeta(barrierMeta);
        gui.addItem(barrier);

        player.openInventory(gui);
    }

    @EventHandler
    public void onSelection(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player player)) {
            return;
        }

        Inventory inventory = event.getClickedInventory();

        if (inventory != null && event.getView().getTitle().equals("Select a glow color...")) {
            event.setCancelled(true);

            ItemStack clickedItem = event.getCurrentItem();
            if (clickedItem != null && clickedItem.getType() != Material.AIR) {

                Scoreboard scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();

                if (clickedItem.getType() == Material.RED_WOOL) {
                    Team team = scoreboard.getTeam("red");
                    if (team == null) {
                        team = scoreboard.registerNewTeam("red");
                        ChatColor color = ChatColor.RED;
                        team.setColor(color);
                    }
                    team.addEntry(player.getName());
                    player.setGlowing(true);
                    player.sendMessage(ChatColor.RED + "Red " + ChatColor.YELLOW + "glow effect enabled.");
                }
                if (clickedItem.getType() == Material.ORANGE_WOOL) {
                    Team team = scoreboard.getTeam("orange");
                    if (team == null) {
                        team = scoreboard.registerNewTeam("orange");
                        ChatColor color = ChatColor.GOLD;
                        team.setColor(color);
                    }
                    team.addEntry(player.getName());
                    player.setGlowing(true);
                    player.sendMessage(ChatColor.GOLD + "Orange " + ChatColor.YELLOW + "glow effect enabled.");
                }
                if (clickedItem.getType() == Material.YELLOW_WOOL) {
                    Team team = scoreboard.getTeam("yellow");
                    if (team == null) {
                        team = scoreboard.registerNewTeam("yellow");
                        ChatColor color = ChatColor.YELLOW;
                        team.setColor(color);
                    }
                    team.addEntry(player.getName());
                    player.setGlowing(true);
                    player.sendMessage(ChatColor.YELLOW + "Yellow " + ChatColor.YELLOW + "glow effect enabled.");
                }
                if (clickedItem.getType() == Material.GREEN_WOOL) {
                    Team team = scoreboard.getTeam("green");
                    if (team == null) {
                        team = scoreboard.registerNewTeam("green");
                        ChatColor color = ChatColor.GREEN;
                        team.setColor(color);
                    }
                    team.addEntry(player.getName());
                    player.setGlowing(true);
                    player.sendMessage(ChatColor.GREEN + "Green " + ChatColor.YELLOW + "glow effect enabled.");
                }
                if (clickedItem.getType() == Material.BLUE_WOOL) {
                    Team team = scoreboard.getTeam("blue");
                    if (team == null) {
                        team = scoreboard.registerNewTeam("blue");
                        ChatColor color = ChatColor.BLUE;
                        team.setColor(color);
                    }
                    team.addEntry(player.getName());
                    player.setGlowing(true);
                    player.sendMessage(ChatColor.BLUE + "Blue " + ChatColor.YELLOW + "glow effect enabled.");
                }
                if (clickedItem.getType() == Material.BARRIER) {
                    player.setGlowing(false);
                    for (Team team : scoreboard.getTeams()) {
                        team.removeEntry(player.getName());
                    }
                }
                player.closeInventory();
            }
        }
    }
}