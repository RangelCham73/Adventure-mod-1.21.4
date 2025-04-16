package com.rangelcham.adventuremod.custom.effect;

import com.rangelcham.adventuremod.AdventureMod;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(BuiltInRegistries.MOB_EFFECT, AdventureMod.MODID);

    public static final Holder<MobEffect> DOUBLEJUMP_EFFECT = MOB_EFFECTS.register("doublejump",
            () -> new DoubleJumpEffect(MobEffectCategory.NEUTRAL, 0x0));

    public static final Holder<MobEffect> DASH_EFFECT = MOB_EFFECTS.register("dash",
            () -> new DashEffect(MobEffectCategory.NEUTRAL, 0x0));

    public static final Holder<MobEffect> FUSION_SHARD_EFFECT = MOB_EFFECTS.register("fusionshard",
            () -> new FusionEffect(MobEffectCategory.NEUTRAL, 0x0));

    public static void register(IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }
}
