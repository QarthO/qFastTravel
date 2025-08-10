package gg.quartzdev.mc.qFastTravel.commands;

import java.util.List;

import org.jetbrains.annotations.NotNull;

import gg.quartzdev.mc.lib.qlibpaper.commands.QCMD;
import io.papermc.paper.command.brigadier.CommandSourceStack;

public class CMDfasttravel extends QCMD{

    public CMDfasttravel() {
        super(
            "qfasttravel",
            "Fast Travel Command",
            "qfasttravel.use",
            "qfasttravel",
            false
        );
    }

    @Override
    public void execute(@NotNull CommandSourceStack commandSourceStack, @NotNull String[] args) {
        switch (args.length)
        {
            case 0 -> commandSourceStack.getSender().sendRichMessage("<red>no args");
            case 1 -> commandSourceStack.getSender().sendRichMessage("<blue>1 args");
            case 2 -> commandSourceStack.getSender().sendRichMessage("<green>2 args");
            default -> commandSourceStack.getSender().sendRichMessage("<green>everything else args");
        }
    }

    @Override
    public @NotNull List<String> tabCompletionLogic(@NotNull CommandSourceStack commandSourceStack, @NotNull String[] args) {
        return switch(args.length)
        {
            case 0 -> List.of("zero", "args", "found");
            case 1 -> List.of("1", "arg");
            case 2 -> List.of("TWO ARGS");
            default -> List.of();
        };
    }
    
    
}
