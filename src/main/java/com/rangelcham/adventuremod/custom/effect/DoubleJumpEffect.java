package com.rangelcham.adventuremod.custom.effect;

import com.rangelcham.adventuremod.doublejump.DoubleJumpHandler;
import com.rangelcham.adventuremod.nbt.PlayerAbilityHandler;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public class DoubleJumpEffect extends MobEffect {
    public DoubleJumpEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean applyEffectTick(ServerLevel level, LivingEntity entity, int amplifier) {
        DoubleJumpHandler.unlockedDoubleJump = true;
        return super.applyEffectTick(level, entity, amplifier);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true;
    }
}

