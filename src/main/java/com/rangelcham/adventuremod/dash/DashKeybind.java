package com.rangelcham.adventuremod.dash;

import net.minecraft.client.KeyMapping;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import org.lwjgl.glfw.GLFW;

public class DashKeybind {
    public static final KeyMapping DASH_ACTION = new KeyMapping("key.adventuremod.dash_action",
                                                                      GLFW.GLFW_KEY_LEFT_ALT,
                                                              "key.categories.adventuremod");

    public static void register(RegisterKeyMappingsEvent event)
    {
        event.register(DASH_ACTION);

    }
}
