package com.rangelcham.adventuremod.custom;

import com.rangelcham.adventuremod.AdventureMod;
import com.rangelcham.adventuremod.custom.block.ModBlocks;
import com.rangelcham.adventuremod.custom.item.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeTab {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, AdventureMod.MODID);

    public static final Supplier<CreativeModeTab> MOD_TAB = CREATIVE_MODE_TAB.register("adventure_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.GREEN_CRYSTAL))
                    .title(Component.nullToEmpty("Objetos aventura"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModBlocks.GREEN_CRYSTAL);
                        output.accept(ModBlocks.BLUE_CRYSTAL);
                        output.accept(ModItems.DOUBLEJUMP_CRYSTAL);
                        output.accept(ModItems.DASH_CRYSTAL);
                        output.accept(ModItems.FUSION_SHARD);
                    }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
