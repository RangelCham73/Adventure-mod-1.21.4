package com.rangelcham.adventuremod.item;

import com.rangelcham.adventuremod.AdventureMod;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

//
//Author: RangelCham73
//

public class ModItems {
    public static final DeferredRegister.Items ITEMS =
            DeferredRegister.createItems(AdventureMod.MODID);


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
