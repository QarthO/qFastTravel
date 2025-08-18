package gg.quartzdev.mc.qFastTravel;

import java.util.ArrayList;
import java.util.List;

import org.bstats.bukkit.Metrics;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import dev.jorel.commandapi.CommandAPI;
import dev.jorel.commandapi.CommandAPICommand;
import io.papermc.paper.dialog.Dialog;
import io.papermc.paper.math.Position;
import io.papermc.paper.registry.data.dialog.ActionButton;
import io.papermc.paper.registry.data.dialog.DialogBase;
import io.papermc.paper.registry.data.dialog.action.DialogAction;
import io.papermc.paper.registry.data.dialog.type.DialogType;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickCallback;

public final class QFastTravel extends JavaPlugin
{

    private static final int COLUMNS = 9;
    private static final int CANVAS_SIZE = 2001;


    @Override
    public void onLoad() {

        CommandAPICommand fastTravel = new CommandAPICommand("qfasttravel")
        .withAliases("fasttravel", "ft", "travel", "warp")
        .withPermission("qfasttravel.use")
        .executesPlayer((player, args) -> {
            showButtonGridDialog(player);
        });

        fastTravel.register();
    }

    @Override
    public void onEnable()
    {
        Metrics metrics = new Metrics(this, 26863);
        CommandAPI.onEnable();
    }

    @Override
    public void onDisable()
    {
        CommandAPI.onDisable();
        // Plugin shutdown logic
    }

    public void showButtonGridDialog(Player player) {
        // Create action buttons for 3x3 grid

        int columns = 9;

        List<ActionButton> actionButtons = new ArrayList<>();
        
        // Create 9 buttons for the 3x3 grid
        for (int i = 0; i < columns * columns; i++) {
            Position position = convertButtonIndexToPosition(i);
            ActionButton button = ActionButton.create(
                Component.empty(), // Empty label for no text
                Component.text(position.blockX() + ", " + position.blockZ()),
                20, // Width
                createTeleportAction(position)
            );
            actionButtons.add(button);
        }
        
        Dialog dialog = Dialog.create(builder -> builder.empty()
            .base(DialogBase.builder(Component.text("Fast Travel"))
                .build())
            .type(DialogType.multiAction(actionButtons, null, columns)) // 3 columns for 3x3 grid
        );

        // Show the dialog to the player
        player.showDialog(dialog);
    }

    private Position convertButtonIndexToPosition(int index) {
        // Calculate row and column from the index
        int row = index / COLUMNS;    // Which row (0 to COLUMNS-1)
        int col = index % COLUMNS;    // Which column (0 to COLUMNS-1)
        
        // Calculate the step size between each button position
        // Since we have COLUMNS buttons across CANVAS_SIZE, and we want to center them
        int stepSize = CANVAS_SIZE / (COLUMNS - 1);
        
        // Calculate the starting position (top-left corner)
        int halfCanvas = CANVAS_SIZE / 2;
        
        // Calculate x coordinate: starts at -halfCanvas, increases by stepSize for each column
        int x = -halfCanvas + (col * stepSize);
        
        // Calculate z coordinate: starts at +halfCanvas, decreases by stepSize for each row
        int z = halfCanvas - (row * stepSize);

        return Position.block(x, 0, z);
    }

    private DialogAction createTeleportAction(Position position) {
        return DialogAction.customClick(
            (view, audience) -> {
                if (audience instanceof Player teleportPlayer) {
                    teleportPlayer(teleportPlayer, position);
                    teleportPlayer.setFlying(true);
                }
            },
            ClickCallback.Options.builder().uses(ClickCallback.UNLIMITED_USES).build()
        );
    }

    private void teleportPlayer(Player player, Position position) {
        player.sendRichMessage("<green>Fast Traveling to <color:#ccfffd><x>, <y>, <z></color>...",
            net.kyori.adventure.text.minimessage.tag.resolver.Placeholder.component("x", Component.text(position.blockX())),
            net.kyori.adventure.text.minimessage.tag.resolver.Placeholder.component("y", Component.text(position.blockY())),
            net.kyori.adventure.text.minimessage.tag.resolver.Placeholder.component("z", Component.text(position.blockZ()))
        );
        player.teleportAsync(position.toLocation(player.getWorld()));
    }
}
