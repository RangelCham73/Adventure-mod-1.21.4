package com.rangelcham.adventuremod.custom.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.rangelcham.adventuremod.quests.QuestsArgumentType;
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
                            Commands.argument("id", QuestsArgumentType.quests())
                                .then(
                                    Commands.argument("step", IntegerArgumentType.integer(1, 10))
                                    .executes(cs -> execute(
                                            cs.getSource(),
                                            QuestsArgumentType.getQuest(),
                                            IntegerArgumentType.getInteger(cs, "step")
                                    ))
                                )
                        )
        );
    }
    private static int execute(CommandSourceStack command, String quest, int step) {
        if(command.getEntity() instanceof Player){
            Minecraft.getInstance().gui.getChat().addMessage(Component.literal(quest + " " + step));
        }
        return Command.SINGLE_SUCCESS;
    }
}
