package com.mochi_753.mekanismdelight.common;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.food.FoodProperties;
import vectorwing.farmersdelight.common.registry.ModEffects;

public class FoodValues {
    public static final FoodProperties ALLOY_SHAKE_FOOD_PROPERTY =
            new FoodProperties.Builder().nutrition(6).saturationModifier(0.75F).build();
    public static final FoodProperties ANTIMATTER_STEW_FOOD_PROPERTY =
            new FoodProperties.Builder().nutrition(9999999 * 2).saturationModifier(0.5F).alwaysEdible().build();
    public static final FoodProperties ALLOY_JELLY_FOOD_PROPERTY =
            new FoodProperties.Builder().nutrition(2).saturationModifier(1.0F).fast().build();
    public static final FoodProperties DT_DRINK_FOOD_PROPERTY =
            new FoodProperties.Builder().alwaysEdible().effect(() -> new MobEffectInstance(ModEffects.NOURISHMENT, 6000, 0), 1.0F).build();
    public static final FoodProperties POLONIUM_ICE_CREAM_FOOD_PROPERTY =
            new FoodProperties.Builder().nutrition(4).saturationModifier(1.0F).build();
    public static final FoodProperties RADIATION_HAMBURGER_FOOD_PROPERTY =
            new FoodProperties.Builder().nutrition(8).saturationModifier(0.75F).alwaysEdible().build();
    public static final FoodProperties ROBIT_STEW_FOOD_PROPERTY =
            new FoodProperties.Builder().nutrition(16).saturationModifier(0.75F).build();
    public static final FoodProperties SALTED_RICE_BALL_FOOD_PROPERTY =
            new FoodProperties.Builder().nutrition(4).saturationModifier(0.5F).effect(() -> new MobEffectInstance(ModEffects.COMFORT, 300, 0), 1.0F).build();
    public static final FoodProperties SAWDUST_HAMBURGER_FOOD_PROPERTY =
            new FoodProperties.Builder().nutrition(12).saturationModifier(0.75F).build();
    public static final FoodProperties SPS_PIE_FOOD_PROPERTY =
            new FoodProperties.Builder().nutrition(16).saturationModifier(0.5F).build();
}
