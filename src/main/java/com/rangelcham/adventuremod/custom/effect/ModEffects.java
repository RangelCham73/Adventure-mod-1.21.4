package com.rangelcham.adventuremod.custom.effect;

import com.rangelcham.adventuremod.AdventureMod;
import net.minecraft.client.gui.font.glyphs.BakedGlyph;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(BuiltInRegistries.MOB_EFFECT, AdventureMod.MODID);

    public static final Holder<MobEffect> DOUBLEJUMP_EFFECT = MOB_EFFECTS.register("doublejump",
            () -> new DoubleJumpEffect(MobEffectCategory.NEUTRAL, 0x0));

    public static void register(IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }
}
