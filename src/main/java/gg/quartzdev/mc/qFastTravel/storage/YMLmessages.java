package gg.quartzdev.mc.qFastTravel.storage;

import org.bukkit.plugin.java.JavaPlugin;

import gg.quartzdev.mc.lib.qlibpaper.QLogger;
import gg.quartzdev.mc.lib.qlibpaper.lang.QMessage;
import gg.quartzdev.mc.lib.qlibpaper.storage.QConfiguration;

public class YMLmessages extends QConfiguration
{

    public YMLmessages(JavaPlugin plugin, String fileName)
    {
        super(plugin, fileName, false);
        loadAllData();
    }

    @Override
    public void loadAllData()
    {
        QLogger.info("<prefix> Loading messages");
        for (String key : yamlConfiguration.getKeys(false))
        {
            QMessage message = Messages.getCustomMessage(key);
            if (message == null)
            {
                QLogger.error(Messages.ERROR_UPDATE_MESSAGE.parse("key", key));
                continue;
            }
            message.set(yamlConfiguration.getString(key));
        }
    }

    @Override
    public void saveAllData()
    {

    }
}

