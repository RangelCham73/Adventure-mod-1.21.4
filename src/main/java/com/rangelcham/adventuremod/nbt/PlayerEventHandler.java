package com.rangelcham.adventuremod.nbt;

import com.mojang.brigadier.CommandDispatcher;
import com.rangelcham.adventuremod.AdventureMod;
import com.rangelcham.adventuremod.custom.commands.QuestCommand;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.entity.EntityLeaveLevelEvent;

@EventBusSubscriber(modid = AdventureMod.MODID)
public class PlayerEventHandler {

    @SubscribeEvent
    public static void onPlayerJoin(EntityJoinLevelEvent event) {
        if ((event.getEntity() instanceof ServerPlayer player)) {
            PlayerAbilityHandler.loadAbilities(player);
            PlayerQuestHandler.loadQuests(player);
            PlayerStatHandler.loadStats(player);
        }
        if ((event.getEntity() instanceof LocalPlayer player)) {// debug
            PlayerAbilityHandler.loadAbilities(player);
            PlayerQuestHandler.loadQuests(player);
            PlayerStatHandler.loadStats(player);
        }
    }

    @SubscribeEvent
    public static void onPlayerLeave(EntityLeaveLevelEvent event) {
        if ((event.getEntity() instanceof LocalPlayer player)) {
            PlayerAbilityHandler.saveAbilities(player);
            PlayerQuestHandler.saveQuests(player);
            PlayerStatHandler.loadStats(player);
        }
        if ((event.getEntity() instanceof ServerPlayer player)) {
            PlayerAbilityHandler.saveAbilities(player);
            PlayerQuestHandler.saveQuests(player);
            PlayerStatHandler.loadStats(player);
        }
    }

    @SubscribeEvent
    public static void onRegisterCommands(RegisterCommandsEvent event) {
        CommandDispatcher<CommandSourceStack> dispatcher = event.getDispatcher();
        QuestCommand.register(dispatcher);
    }
}