package com.rangelcham.adventuremod;

import com.rangelcham.adventuremod.custom.effect.ModEffects;
import com.rangelcham.adventuremod.custom.item.ModItems;
import com.rangelcham.adventuremod.dash.DashHandler;
import com.rangelcham.adventuremod.dash.DashKeybind;
import com.rangelcham.adventuremod.doublejump.DoubleJumpHandler;
import com.rangelcham.adventuremod.nbt.PlayerEventHandler;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;

//
//Author: RangelCham73
//

@Mod(AdventureMod.MODID)
public class AdventureMod
{
    public static final String MODID = "adventuremod";

    public AdventureMod(IEventBus modEventBus, ModContainer modContainer)
    {
        modEventBus.addListener(this::commonSetup);
        NeoForge.EVENT_BUS.register(this);

        ModEffects.register(modEventBus);
        ModItems.register(modEventBus);
//        ModBlocks.register(modEventBus);

        modEventBus.addListener(DashKeybind::register);
        modEventBus.addListener(this::addCreative);

        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {

    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if(event.getTabKey() == CreativeModeTabs.COMBAT) {
            event.accept(ModItems.DOUBLEJUMP_CRYSTAL);
            event.accept(ModItems.DASH_CRYSTAL);
        }

//        if(event.getTabKey() == CreativeModeTabs.COLORED_BLOCKS) {
//            event.accept(ModItems.GREENCRYSTAL_BLOCK_ITEM);
//            event.accept(ModItems.BLUECRYSTAL_BLOCK_ITEM);
//        }
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

    @EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
        }
    }
}
