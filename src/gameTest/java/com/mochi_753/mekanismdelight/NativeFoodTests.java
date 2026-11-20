package com.mochi_753.mekanismdelight;
import com.mochi_753.mekanismdelight.common.compat.mekanism.MekanismDelightItems;
import com.mochi_753.mekanismdelight.common.item.RadioactiveFoodItem;
import com.mochi_753.mekanismdelight.common.item.alloy.AlloyShakeItem;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.gametest.framework.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.*;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.*;
import net.neoforged.neoforge.gametest.*;
import java.util.*;

@GameTestHolder("mekanismdelight") @PrefixGameTestTemplate(false)
public final class NativeFoodTests {
    private static Item item(String n){return BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath("mekanismdelight",n));}
    @GameTest(template="empty") public static void originalPackFoodsKeepIdsFoodAndComponents(GameTestHelper h){
        List<String> ids=List.of("alloy_jelly_medley","formerly_robit","robit_stew","infused_shake","reinforced_shake","atomic_shake","infused_jelly","reinforced_jelly","atomic_jelly","polonium_ice_cream","plutonium_hamburger","polonium_hamburger","sawdust_hamburger","salted_rice_ball","sps_pie","antimatter_stew","fissile_fuel_drink","dt_drink");
        h.assertTrue(BuiltInRegistries.ITEM.keySet().stream().filter(id->id.getNamespace().equals("mekanismdelight")).count()==18,"Exactly the original pack's 18 active items must remain");
        for(String id:ids){var s=new ItemStack(item(id),3);h.assertTrue(!s.is(Items.AIR),"Original id must exist: "+id);s.set(DataComponents.CUSTOM_NAME,net.minecraft.network.chat.Component.literal("migration "+id));var restored=ItemStack.parseOptional(h.getLevel().registryAccess(),(net.minecraft.nbt.CompoundTag)s.save(h.getLevel().registryAccess()));h.assertTrue(ItemStack.matches(s,restored),"Native component roundtrip must retain "+id);}
        var food=new ItemStack(item("infused_jelly")).get(DataComponents.FOOD);h.assertTrue(food.nutrition()==2&&food.eatSeconds()==0.8F,"Original jelly nutrition and fast consumption must remain");h.succeed();
    }
    @GameTest(template="empty") public static void allOriginalActiveRecipesLoadWithReachableIngredients(GameTestHelper h){
        List<String> names=List.of("alloy_jelly_medley","atomic_shake","infused_shake","reinforced_shake","polonium_hamburger","plutonium_hamburger","sawdust_hamburger","salted_rice_ball","sps_pie","cooking/dt_drink","cooking/robit_stew","cutting/robit","injecting/fissile_fuel_drink","nucleosynthesizing/antimatter_stew","processing/lategame/polonium_ice_cream");
        for(String n:names){var r=h.getLevel().getRecipeManager().byKey(ResourceLocation.fromNamespaceAndPath("mekanismdelight",n));h.assertTrue(r.isPresent(),"Original active recipe must load: "+n);for(var ingredient:r.orElseThrow().value().getIngredients())if(!ingredient.isEmpty())h.assertTrue(ingredient.getItems().length>0,"Recipe ingredient must be obtainable: "+n);}
        h.succeed();
    }
    @GameTest(template="empty") public static void alloyShakeRemovesOnlyTierLimitedMilkCurableHarmfulEffects(GameTestHelper h){
        var cow=h.spawn(EntityType.COW,new BlockPos(1,1,1));
        cow.addEffect(new MobEffectInstance(MobEffects.POISON,200));cow.addEffect(new MobEffectInstance(MobEffects.WEAKNESS,200));cow.addEffect(new MobEffectInstance(MobEffects.REGENERATION,200));
        ((AlloyShakeItem)item("infused_shake")).affectConsumer(new ItemStack(item("infused_shake")),h.getLevel(),cow);
        h.assertTrue(cow.getActiveEffects().size()==2&&cow.hasEffect(MobEffects.REGENERATION),"Infused shake removes exactly one harmful effect and keeps beneficial effects");
        ((AlloyShakeItem)item("atomic_shake")).affectConsumer(new ItemStack(item("atomic_shake")),h.getLevel(),cow);
        h.assertTrue(cow.getActiveEffects().size()==1&&cow.hasEffect(MobEffects.REGENERATION),"Atomic shake removes remaining harmful effects");h.succeed();
    }
    @GameTest(template="empty") public static void effectRemovalStillHonorsNativeCancellation(GameTestHelper h){
        var cow=h.spawn(EntityType.COW,new BlockPos(1,1,1));cow.addEffect(new MobEffectInstance(MobEffects.POISON,200));
        java.util.function.Consumer<net.neoforged.neoforge.event.entity.living.MobEffectEvent.Remove> listener=e->{if(e.getEntity()==cow)e.setCanceled(true);};
        net.neoforged.neoforge.common.NeoForge.EVENT_BUS.addListener(listener);
        try{((AlloyShakeItem)item("atomic_shake")).affectConsumer(new ItemStack(item("atomic_shake")),h.getLevel(),cow);h.assertTrue(cow.hasEffect(MobEffects.POISON),"Canceling native removal must retain effect");}finally{net.neoforged.neoforge.common.NeoForge.EVENT_BUS.unregister(listener);}h.succeed();
    }
    @GameTest(template="empty") public static void radioactiveFoodUsesActualMekanismEntityCapability(GameTestHelper h){
        var cow=h.spawn(EntityType.COW,new BlockPos(1,1,1));var radiation=cow.getCapability(mekanism.common.capabilities.Capabilities.RADIATION_ENTITY);h.assertTrue(radiation!=null,"Actual living radiation capability required");double before=radiation.getRadiation();((RadioactiveFoodItem)item("polonium_ice_cream")).affectConsumer(new ItemStack(item("polonium_ice_cream")),h.getLevel(),cow);h.assertTrue(radiation.getRadiation()==before+50,"Original dose of 50 must remain");h.succeed();
    }
    @GameTest(template="empty") public static void jellyFeastRetainsThreeDistinctServingsAndBlockState(GameTestHelper h){
        var block=BuiltInRegistries.BLOCK.get(ResourceLocation.parse("mekanismdelight:alloy_jelly_medley"));var feast=(com.mochi_753.mekanismdelight.common.block.AlloyJellyMedleyBlock)block;
        for(int n=1;n<=3;n++){var state=block.defaultBlockState().setValue(feast.getServingsProperty(),n);h.setBlock(new BlockPos(1,1,1),state);h.assertTrue(h.getBlockState(new BlockPos(1,1,1)).getValue(feast.getServingsProperty())==n,"Serving state must persist");h.assertTrue(feast.getServingItem(state).is(item(List.of("atomic_jelly","reinforced_jelly","infused_jelly").get(n-1))),"Original serving order must remain");}h.succeed();
    }
}
