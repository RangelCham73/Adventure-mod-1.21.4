package com.rangelcham.adventuremod.nbt;

import net.minecraft.client.player.LocalPlayer;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.entity.EntityLeaveLevelEvent;

public class PlayerEventHandler {

    @SubscribeEvent
    public static void onPlayerJoin(EntityJoinLevelEvent event) {
        if ((event.getEntity() instanceof ServerPlayer player))
            PlayerAbilityHandler.loadAbilities(player);
    }

    @SubscribeEvent
    public static void onPlayerLeave(EntityLeaveLevelEvent event) {
        if ((event.getEntity() instanceof ServerPlayer player))
            PlayerAbilityHandler.saveAbilities(player);
    }
}