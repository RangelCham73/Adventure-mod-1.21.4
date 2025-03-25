package com.rangelcham.adventuremod;

import com.mojang.brigadier.CommandDispatcher;
import com.rangelcham.adventuremod.custom.commands.ModCommands;
import com.rangelcham.adventuremod.custom.commands.QuestCommand;
import com.rangelcham.adventuremod.custom.ModCreativeTab;
import com.rangelcham.adventuremod.custom.block.ModBlocks;
import com.rangelcham.adventuremod.custom.effect.ModEffects;
import com.rangelcham.adventuremod.custom.item.ModItems;
import com.rangelcham.adventuremod.player.abilities.dash.DashKeybind;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.EventPriority;
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
import net.neoforged.neoforge.event.RegisterCommandsEvent;
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
        ModBlocks.register(modEventBus);
        ModCreativeTab.register(modEventBus);

        modEventBus.addListener(DashKeybind::register);
        modEventBus.addListener(this::addCreative);

        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
        new ModCommands();
    }

    private void commonSetup(final FMLCommonSetupEvent event) {

    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
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
