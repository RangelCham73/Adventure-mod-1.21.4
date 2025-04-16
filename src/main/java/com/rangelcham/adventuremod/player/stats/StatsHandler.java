package com.rangelcham.adventuremod.player.stats;

import com.rangelcham.adventuremod.AdventureMod;
import com.rangelcham.adventuremod.player.ModPlayer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

@EventBusSubscriber(modid = AdventureMod.MODID)
public class StatsHandler {
    private static final Minecraft mc = Minecraft.getInstance();
    public static ModPlayer modPlayer = new ModPlayer();  // Asumiendo que esta clase tiene la instancia del jugador

    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Pre event) {
        if (!(event.getEntity() instanceof LocalPlayer player)) return;

        // Si la tecla de abrir estadísticas está presionada y no hay pantalla abierta, abre la pantalla de estadísticas
        if (StatsKeyBind.STATS_OPEN_SCREEN.isDown() && mc.screen == null) {
            StatsScreen screen = new StatsScreen(modPlayer.stats);
            mc.setScreen(screen);
        }
    }
}
