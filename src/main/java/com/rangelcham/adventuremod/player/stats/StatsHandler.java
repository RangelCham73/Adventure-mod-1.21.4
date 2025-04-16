package com.rangelcham.adventuremod.player.stats;

import com.rangelcham.adventuremod.AdventureMod;
import com.rangelcham.adventuremod.player.ModPlayer;
import com.rangelcham.adventuremod.quests.Quest;
import com.rangelcham.adventuremod.quests.QuestKeyBind;
import com.rangelcham.adventuremod.quests.QuestScreen;
import com.rangelcham.adventuremod.quests.QuestsHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

import java.util.Map;

@EventBusSubscriber(modid = AdventureMod.MODID)
public class StatsHandler {
    public static ModPlayer modPlayer = new ModPlayer();

    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Pre event) {
        if (!(event.getEntity() instanceof LocalPlayer player)) return;

        if (StatsKeyBind.STATS_OPEN_SCREEN.isDown()) {
            Minecraft mc = Minecraft.getInstance();

            if (mc.screen == null) {
                StatsScreen screen = new StatsScreen(modPlayer.stats);
                mc.setScreen(screen);
            }
        }
    }
}
