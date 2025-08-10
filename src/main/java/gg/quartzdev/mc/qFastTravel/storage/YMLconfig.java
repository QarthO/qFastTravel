package gg.quartzdev.mc.qFastTravel.storage;

import org.bukkit.plugin.java.JavaPlugin;

import gg.quartzdev.mc.lib.qlibpaper.storage.QConfiguration;

public class YMLconfig extends QConfiguration{

    public YMLconfig(JavaPlugin plugin, String fileName, boolean useSchema) {
        super(plugin, fileName, useSchema);
        //TODO Auto-generated constructor stub
    }

    @Override
    public void loadAllData() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'loadAllData'");
    }

    @Override
    public void saveAllData() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'saveAllData'");
    }
    
}
