package com.rangelcham.adventuremod.nbt;

import com.rangelcham.adventuremod.AdventureMod;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.entity.EntityLeaveLevelEvent;

@EventBusSubscriber(modid = AdventureMod.MODID)
public class PlayerEventHandler {

    @SubscribeEvent
    public static void onPlayerJoin(EntityJoinLevelEvent event) {
        if ((event.getEntity() instanceof ServerPlayer player)) {
//            Minecraft.getInstance().gui.getChat().addMessage(Component.literal("Carga de datos en ServerPlayer")); // debug
            PlayerAbilityHandler.loadAbilities(player);
        }
        if ((event.getEntity() instanceof LocalPlayer player)) {
//            Minecraft.getInstance().gui.getChat().addMessage(Component.literal("Carga de datos en LocalPlayer")); // debug
            PlayerAbilityHandler.loadAbilities(player);
        }
    }

    @SubscribeEvent
    public static void onPlayerLeave(EntityLeaveLevelEvent event) {
        if ((event.getEntity() instanceof LocalPlayer player)) {
//            Minecraft.getInstance().gui.getChat().addMessage(Component.literal("Guardar datos en LocalPlayer")); // debug
            PlayerAbilityHandler.saveAbilities(player);
        }
        if ((event.getEntity() instanceof ServerPlayer player)) {
//            Minecraft.getInstance().gui.getChat().addMessage(Component.literal("Guardar datos en ServerPlayer")); // debug
            PlayerAbilityHandler.saveAbilities(player);
        }
    }
}