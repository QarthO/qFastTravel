package gg.quartzdev.mc.qFastTravel;

import org.bukkit.plugin.java.JavaPlugin;

import dev.jorel.commandapi.CommandAPICommand;
import io.papermc.paper.dialog.Dialog;
import io.papermc.paper.registry.data.dialog.DialogBase;
import io.papermc.paper.registry.data.dialog.type.DialogType;
import net.kyori.adventure.text.Component;

public final class QFastTravel extends JavaPlugin
{

    @Override
    public void onEnable()
    {
        
        CommandAPICommand fastTravel = new CommandAPICommand("qfasttravel")
        .withAliases("fasttravel", "ft", "travel", "warp")
        .withPermission("qfasttravel.use")
        .executesPlayer((player, args) -> {
            player.showDialog(fastTravelDialog());
        });

        fastTravel.register();
    }

    @Override
    public void onDisable()
    {
        // Plugin shutdown logic
    }

    public Dialog fastTravelDialog() {
        Dialog dialog = Dialog.create(builder -> builder.empty()
        .base(DialogBase.builder(Component.text("Test Title")).build())
        .type(DialogType.notice()));

        return dialog;
    }
}
