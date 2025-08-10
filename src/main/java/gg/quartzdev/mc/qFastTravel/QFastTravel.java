package gg.quartzdev.mc.qFastTravel;

import org.bukkit.plugin.java.JavaPlugin;

public final class QFastTravel extends JavaPlugin
{

    @Override
    public void onEnable()
    {
        FastTravelAPI.enable(this, 12345);
    }

    @Override
    public void onDisable()
    {
        // Plugin shutdown logic
    }

}
