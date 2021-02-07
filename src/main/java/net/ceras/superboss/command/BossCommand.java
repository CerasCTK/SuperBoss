package net.ceras.superboss.command;

import net.ceras.superboss.SuperBoss;
import net.ceras.superboss.gui.MainMenu;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BossCommand implements CommandExecutor {
    private SuperBoss main;
    private MainMenu mainMenu;

    public BossCommand(SuperBoss main) {
        this.main = main;
        mainMenu = new MainMenu(main);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = null;

        if (sender instanceof Player) {
            player = (Player) sender;
        }

        if (args.length > 2) {
            if (sender.hasPermission("superboss.admin.give")) {
                if (args[0].equals("give")) {
                    try {
                        Player target = Bukkit.getPlayer(args[1]);

                        if (args[2].equalsIgnoreCase("block")) {
                            target.getInventory().addItem(main.getFileHandler().getSpawnBlockType());
                            return false;
                        }

                        for (String boss : main.getFileHandler().getBossCache().keySet()) {
                            if (args[2].equalsIgnoreCase(boss)) {
                                target.getInventory().addItem(main.getFileHandler().getBossCache().get(boss).getSpawnItemType());
                            }
                        }
                    } catch (NullPointerException playerNotFound) {
                        sender.sendMessage("Player is not found.");
                    }
                }
            }
        } else if (args.length > 0 && args.length < 2) {
            if (args[0].equalsIgnoreCase("reload")) {
                if (sender.hasPermission("superboss.admin.reload")) {
                    main.getFileManager().reloadConfig();
                }
            }
        } else {
            if (sender.hasPermission("superboss.admin.give")) {
                mainMenu.openInventory(player);
                //sender.sendMessage(	ChatColor.BLUE + "CerasBoss" + ChatColor.GRAY + " by Astero\n");
            }
        }
        return false;
    }
}
