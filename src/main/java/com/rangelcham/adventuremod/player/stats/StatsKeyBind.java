package com.rangelcham.adventuremod.player.stats;

import net.minecraft.client.KeyMapping;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import org.lwjgl.glfw.GLFW;

public class StatsKeyBind {
    public static final KeyMapping STATS_OPEN_SCREEN = new KeyMapping("key.adventuremod.stats",
            GLFW.GLFW_KEY_K,
            "key.categories.adventuremod");

    public static void register(RegisterKeyMappingsEvent event)
    {
        event.register(STATS_OPEN_SCREEN);

    }
}
