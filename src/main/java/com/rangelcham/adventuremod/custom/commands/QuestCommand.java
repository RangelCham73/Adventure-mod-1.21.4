package com.rangelcham.adventuremod.custom.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.rangelcham.adventuremod.quests.QuestsHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.world.entity.player.Player;
import net.minecraft.network.chat.Component;

public class QuestCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher){
        dispatcher.register(
                Commands.literal("quest")
                    .requires(cs -> cs.hasPermission(2))
                        .then(
                            Commands.argument("id", IntegerArgumentType.integer(0, 50))
                                .then(
                                    Commands.argument("step", IntegerArgumentType.integer(0 , 10))
                                    .executes(cs -> execute(
                                            cs.getSource(),
                                            IntegerArgumentType.getInteger(cs, "id"),
                                            IntegerArgumentType.getInteger(cs, "step")
                                    ))
                                )
                        )
        );
    }
    private static int execute(CommandSourceStack command, int id, int step) {
        Minecraft.getInstance().gui.getChat().addMessage(Component.literal(id + " " + step));
        QuestsHandler.doStep(command, id, step);
        return Command.SINGLE_SUCCESS;
    }
}
