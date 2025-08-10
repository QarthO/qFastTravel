package gg.quartzdev.mc.qFastTravel;

import java.util.List;

import gg.quartzdev.mc.lib.qlibpaper.QLogger;
import gg.quartzdev.mc.lib.qlibpaper.QPluginAPI;
import gg.quartzdev.mc.lib.qlibpaper.commands.CommandManager;
import gg.quartzdev.mc.lib.qlibpaper.lang.GenericMessages;
import gg.quartzdev.mc.qFastTravel.commands.CMDfasttravel;
import gg.quartzdev.mc.qFastTravel.storage.YMLconfig;

public class FastTravelAPI implements QPluginAPI {

    private static QFastTravel pluginInstance;

    private static FastTravelAPI apiInstance;
    private static YMLconfig config;

    private static CommandManager commandManager;
    private static gg.quartzdev.metrics.bukkit.Metrics metrics;
    private final String CONSOLE_PREFIX = "<white>[<color:#ffc629>Task<white>]";
    private final String CHAT_PREFIX = "<color:#ffc629>Taskcrafter <bold><gray>>></bold>";
    private final String MODRINTH_SLUG = "taskcrafter".toLowerCase();
    private final String MODRINTH_LOADER = "paper";

    private FastTravelAPI() {

    }

    private FastTravelAPI(QFastTravel plugin, int bStatsPluginId) {
        pluginInstance = plugin;
        QLogger.init(pluginInstance.getComponentLogger());
        QPluginAPI.register(plugin, bStatsPluginId);
    }

    public static QFastTravel getPlugin() {
        return pluginInstance;
    }



    @SuppressWarnings("SameParameterValue")
    protected static void enable(QFastTravel plugin, int bStatsPluginId)
    {
        if (apiInstance != null)
        {
            QLogger.error(GenericMessages.ERROR_PLUGIN_ENABLE);
            return;
        }
        apiInstance = new FastTravelAPI(plugin, bStatsPluginId);

    }

    protected static void disable()
    {

//        Logs plugin is being disabled
        QLogger.info(GenericMessages.PLUGIN_DISABLE);

//        Clears instances
        apiInstance = null;
        pluginInstance = null;
        config = null;
        if (metrics != null)
        {
            metrics.shutdown();
            metrics = null;
        }
    }

    @SuppressWarnings("UnstableApiUsage")
    public static String getVersion()
    {
        return pluginInstance.getPluginMeta().getVersion();
    }

    public static String getName()
    {
        return pluginInstance.getName();
    }

    public static void loadCustomMessages()
    {
        messages.reload();
    }

    public void setupMetrics(int pluginId)
    {
        metrics = new Metrics(pluginInstance, pluginId);
    }

    public void registerCommands()
    {
        commandManager = new CommandManager(pluginInstance);
        commandManager.add(new CMDfasttravel(), List.of("fasttravel", "ft", "travel", "warp"));
        commandManager.registerCommands();
    }

    @Override
    public void setupConfig() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setupConfig'");
    }

    @Override
    public void registerListeners() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'registerListeners'");
    }


}
