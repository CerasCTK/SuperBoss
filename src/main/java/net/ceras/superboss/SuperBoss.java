package net.ceras.superboss;

import net.ceras.superboss.boss.Boss;
import net.ceras.superboss.boss.BossActivity;
import net.ceras.superboss.boss.animation.SpawnAnimation;
import net.ceras.superboss.command.BossCommand;
import net.ceras.superboss.filemanager.FileHandler;
import net.ceras.superboss.filemanager.FileManager;
import net.ceras.superboss.filemanager.MessageHandler;
import net.ceras.superboss.gui.listener.BossItemsMenuListener;
import net.ceras.superboss.gui.listener.BossTrackerMenuListener;
import net.ceras.superboss.gui.listener.MainMenuListener;
import net.ceras.superboss.listener.DamageListener;
import net.ceras.superboss.listener.SpawnListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import static org.bukkit.Bukkit.getPluginManager;

public final class SuperBoss extends JavaPlugin {
    private FileHandler fileHandler;
    private FileManager fileManager;
    private MessageHandler messageHandler;
    private SpawnAnimation spawnAnimation;
    private Boss boss;
    private BossActivity bossActivity;

    public FileHandler getFileHandler() {
        return fileHandler;
    }

    public FileManager getFileManager() {
        return fileManager;
    }

    public MessageHandler getMessageHandler() {
        return messageHandler;
    }

    public SpawnAnimation getSpawnAnimation() {
        return spawnAnimation;
    }

    public Boss getBoss() {
        return boss;
    }

    public BossActivity getBossActivity() {
        return bossActivity;
    }

    public SuperBoss() {

    }

    @Override
    public void onEnable() {
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        fileManager = new FileManager(this);
        messageHandler = new MessageHandler();
        fileHandler = new FileHandler(this);

        System.out.println(" ");

        System.out.println(ChatColor.BLUE + "→" + ChatColor.GRAY + " Cached Files are loaded up!");

        getPluginManager().registerEvents(new SpawnListener(this), this);
        getPluginManager().registerEvents(new DamageListener(this), this);
        getPluginManager().registerEvents(new MainMenuListener(this), this);
        getPluginManager().registerEvents(new BossItemsMenuListener(this), this);
        getPluginManager().registerEvents(new BossTrackerMenuListener(this), this);
        System.out.println(ChatColor.BLUE + "→" + ChatColor.GRAY + " Listeners are loaded up!");

        getCommand("boss").setExecutor(new BossCommand(this));
        System.out.println(ChatColor.BLUE + "→" + ChatColor.GRAY + " Commands are loaded up!");

        spawnAnimation = new SpawnAnimation(this);
        boss = new Boss(this);
        bossActivity = new BossActivity(this);

        System.out.println(" ");

        System.out.println(ChatColor.BLUE + "SuperBoss has been sucessfully loaded up!\n");
    }

    @Override
    public void onDisable() {

    }

    private void loadConfig() {
        getConfig().options().copyDefaults();
        saveDefaultConfig();
    }

}
