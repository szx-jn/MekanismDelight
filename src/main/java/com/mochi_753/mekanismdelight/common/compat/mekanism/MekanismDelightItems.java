package com.mochi_753.mekanismdelight.common.compat.mekanism;

import com.mochi_753.mekanismdelight.MekanismDelight;
import com.mochi_753.mekanismdelight.common.FoodValues;
import com.mochi_753.mekanismdelight.common.item.AntimatterStewItem;
import com.mochi_753.mekanismdelight.common.item.RadioactiveFoodItem;
import com.mochi_753.mekanismdelight.common.item.RobitStewItem;
import com.mochi_753.mekanismdelight.common.item.SPSPieItem;
import com.mochi_753.mekanismdelight.common.item.alloy.AlloyFoodTier;
import com.mochi_753.mekanismdelight.common.item.alloy.AlloyJellyItem;
import com.mochi_753.mekanismdelight.common.item.alloy.AlloyShakeItem;
import com.mochi_753.mekanismdelight.common.register.MekanismDelightCreativeTab;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;
import vectorwing.farmersdelight.common.item.ConsumableItem;

@SuppressWarnings("unused")
public class MekanismDelightItems {
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Registries.ITEM, MekanismDelight.MOD_ID);

    public static final DeferredHolder<Item, Item> ALLOY_JELLY_MEDLEY = ITEMS.register("alloy_jelly_medley",
            () -> new BlockItem(MekanismDelightBlocks.ALLOY_JELLY_MEDLEY.get(), new Item.Properties().craftRemainder(Items.GLASS_BOTTLE)));

    public static final DeferredHolder<Item, Item> FORMERLY_ROBIT = ITEMS.register("formerly_robit",
            () -> new Item(new Item.Properties()));

    // Foods
    public static final DeferredHolder<Item, Item> ROBIT_STEW = ITEMS.register("robit_stew",
            () -> new RobitStewItem(new Item.Properties().craftRemainder(Items.BOWL).stacksTo(16).food(FoodValues.ROBIT_STEW_FOOD_PROPERTY)));
    public static final DeferredHolder<Item, Item> INFUSED_SHAKE = ITEMS.register("infused_shake",
            () -> new AlloyShakeItem(new Item.Properties().craftRemainder(Items.GLASS_BOTTLE).stacksTo(16).food(FoodValues.ALLOY_SHAKE_FOOD_PROPERTY), AlloyFoodTier.INFUSED));
    public static final DeferredHolder<Item, Item> REINFORCED_SHAKE = ITEMS.register("reinforced_shake",
            () -> new AlloyShakeItem(new Item.Properties().craftRemainder(Items.GLASS_BOTTLE).stacksTo(16).food(FoodValues.ALLOY_SHAKE_FOOD_PROPERTY), AlloyFoodTier.REINFORCED));
    public static final DeferredHolder<Item, Item> ATOMIC_SHAKE = ITEMS.register("atomic_shake",
            () -> new AlloyShakeItem(new Item.Properties().craftRemainder(Items.GLASS_BOTTLE).stacksTo(16).food(FoodValues.ALLOY_SHAKE_FOOD_PROPERTY), AlloyFoodTier.ATOMIC));
    public static final DeferredHolder<Item, Item> INFUSED_JELLY = ITEMS.register("infused_jelly",
            () -> new AlloyJellyItem(new Item.Properties().craftRemainder(Items.GLASS_BOTTLE).stacksTo(16).food(FoodValues.ALLOY_JELLY_FOOD_PROPERTY), AlloyFoodTier.INFUSED));
    public static final DeferredHolder<Item, Item> REINFORCED_JELLY = ITEMS.register("reinforced_jelly",
            () -> new AlloyJellyItem(new Item.Properties().craftRemainder(Items.GLASS_BOTTLE).stacksTo(16).food(FoodValues.ALLOY_JELLY_FOOD_PROPERTY), AlloyFoodTier.REINFORCED));
    public static final DeferredHolder<Item, Item> ATOMIC_JELLY = ITEMS.register("atomic_jelly",
            () -> new AlloyJellyItem(new Item.Properties().craftRemainder(Items.GLASS_BOTTLE).stacksTo(16).food(FoodValues.ALLOY_JELLY_FOOD_PROPERTY), AlloyFoodTier.ATOMIC));
    public static final DeferredHolder<Item, Item> POLONIUM_ICE_CREAM = ITEMS.register("polonium_ice_cream",
            () -> new RadioactiveFoodItem(new Item.Properties().craftRemainder(Items.GLASS_BOTTLE).stacksTo(16).food(FoodValues.POLONIUM_ICE_CREAM_FOOD_PROPERTY), 50.0));
    public static final DeferredHolder<Item, Item> PLUTONIUM_HAMBURGER = ITEMS.register("plutonium_hamburger",
            () -> new RadioactiveFoodItem(new Item.Properties().food(FoodValues.RADIATION_HAMBURGER_FOOD_PROPERTY), 50.0));
    public static final DeferredHolder<Item, Item> POLONIUM_HAMBURGER = ITEMS.register("polonium_hamburger",
            () -> new RadioactiveFoodItem(new Item.Properties().food(FoodValues.RADIATION_HAMBURGER_FOOD_PROPERTY), 50.0));
    public static final DeferredHolder<Item, Item> SAWDUST_HAMBURGER = ITEMS.register("sawdust_hamburger",
            () -> new ConsumableItem(new Item.Properties().food(FoodValues.SAWDUST_HAMBURGER_FOOD_PROPERTY)));
    public static final DeferredHolder<Item, Item> SALTED_RICE_BALL = ITEMS.register("salted_rice_ball",
            () -> new ConsumableItem(new Item.Properties().food(FoodValues.SALTED_RICE_BALL_FOOD_PROPERTY), true));
    public static final DeferredHolder<Item, Item> SPS_PIE = ITEMS.register("sps_pie",
            () -> new SPSPieItem(new Item.Properties().food(FoodValues.SPS_PIE_FOOD_PROPERTY)));
    public static final DeferredHolder<Item, Item> ANTIMATTER_STEW = ITEMS.register("antimatter_stew",
            () -> new AntimatterStewItem(new Item.Properties().craftRemainder(Items.BOWL).stacksTo(16).food(FoodValues.ANTIMATTER_STEW_FOOD_PROPERTY)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

    public static void onBuildCreativeModeTabContents(BuildCreativeModeTabContentsEvent event) {
        if (event.getTab().equals(MekanismDelightCreativeTab.TAB_MEKANISM_DELIGHT.get())) {
            ITEMS.getEntries().forEach(registryObject -> event.accept(registryObject.get()));
        }
    }
}
