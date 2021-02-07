package net.ceras.superboss.filemanager;

import net.ceras.superboss.SuperBoss;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class FileManager {
    private SuperBoss main;

    public File boss;
    public File blockData;
    public File messageData;
    public YamlConfiguration modifyBoss;
    public YamlConfiguration modifyBlockData;
    public YamlConfiguration modifyMessageData;

    public FileManager(SuperBoss main)
    {
        this.main = main;
        initializeFiles();
    }

    public SuperBoss getMain() {
        return main;
    }

    public File getBoss() {
        return boss;
    }

    public File getBlockData() {
        return blockData;
    }

    public File getMessageData() {
        return messageData;
    }

    public YamlConfiguration getModifyBoss() {
        return modifyBoss;
    }

    public YamlConfiguration getModifyBlockData() {
        return modifyBlockData;
    }

    public YamlConfiguration getModifyMessageData() {
        return modifyMessageData;
    }

    public void initializeFiles() {
        try {
            initializeYMLFiles();
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    public void initializeYMLFiles() throws IOException {
        boss = new File(Bukkit.getServer().getPluginManager().getPlugin("SuperBoss").getDataFolder(), "boss.yml");

        blockData = new File(Bukkit.getServer().getPluginManager().getPlugin("SuperBoss").getDataFolder(), "blockdata.yml");

        messageData = new File(Bukkit.getServer().getPluginManager().getPlugin("SuperBoss").getDataFolder(), "messages.yml");

        if (!boss.exists()) {
            main.saveResource("boss.yml", false);
        }

        if (!blockData.exists()) {
            main.saveResource("blockdata.yml", false);
        }

        if (!messageData.exists()) {
            main.saveResource("messages.yml", false);
        }

        modifyBoss = YamlConfiguration.loadConfiguration(boss);
        modifyBlockData = YamlConfiguration.loadConfiguration(blockData);
        modifyMessageData = YamlConfiguration.loadConfiguration(messageData);
    }

    public void saveFile() {
        try {
            modifyBlockData.save(blockData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void reloadConfig() {
        modifyBoss = YamlConfiguration.loadConfiguration(boss);
        modifyBlockData = YamlConfiguration.loadConfiguration(blockData);
        modifyMessageData = YamlConfiguration.loadConfiguration(messageData);
        main.reloadConfig();

        saveFile();
    }
}
