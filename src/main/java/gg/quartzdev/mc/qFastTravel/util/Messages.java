package gg.quartzdev.mc.qFastTravel.util;

import javax.annotation.Nullable;

import gg.quartzdev.mc.lib.qlibpaper.lang.GenericMessages;
import gg.quartzdev.mc.lib.qlibpaper.lang.QMessage;
import gg.quartzdev.mc.qFastTravel.FastTravelAPI;
import gg.quartzdev.mc.qFastTravel.storage.YMLmessages;

public class Messages extends GenericMessages
{

    //    WITHDRAW CLAIMBLOCKS
    public static QMessage FAST_TRAVEL_COMMAND = new QMessage(
            "<prefix> <red>Syntax: /<label>");

    private static Messages INSTANCE;
    YMLmessages messagesFile;

    public Messages(String consolePrefix, String chatPrefix)
    {
        super(consolePrefix, chatPrefix);
        messagesFile = new YMLmessages(FastTravelAPI.getPlugin(), "messages.yml");
    }

    /**
     * uses reflection to get the {@link QMessage} object from the class
     *
     * @param key the name of the field to get
     * @return the {@link QMessage} or {@link null} if it doesn't exist
     */
    public static @Nullable QMessage getCustomMessage(String key)
    {
        try
        {
            return (QMessage) Messages.class.getField(key).get(QMessage.class);
        } catch (NoSuchFieldException | IllegalAccessException | ClassCastException e)
        {
            return null;
        }
    }

    /**
     * Reloads the messages file
     */
    public void reload()
    {
        messagesFile.reload();
    }
}
