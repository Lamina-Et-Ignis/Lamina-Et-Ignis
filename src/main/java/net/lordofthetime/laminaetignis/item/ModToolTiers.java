package net.lordofthetime.laminaetignis.item;

import net.lordofthetime.laminaetignis.LaminaEtIgnis;
import net.lordofthetime.laminaetignis.tags.ModTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.TierSortingRegistry;

import java.util.List;

public class ModToolTiers {
    public static Tier BONE;
    public static Tier TIN;
    public static Tier COPPER;

    static {
        BONE = TierSortingRegistry.registerTier(
                new ForgeTier(5,64,1f,1f,20,
                        ModTags.Blocks.NEEDS_BONE_TOOL,() -> Ingredient.of(Items.BONE)),
                ResourceLocation.tryBuild(LaminaEtIgnis.MODID,"bone"),
                List.of(Tiers.WOOD),
                List.of() // temporarily empty, will update after TIN is created
        );

        COPPER = TierSortingRegistry.registerTier(
                new ForgeTier(7,350,3f,3f,5,
                        ModTags.Blocks.NEEDS_COPPER_TOOL,() -> Ingredient.of(Items.COPPER_INGOT)),
                ResourceLocation.tryBuild(LaminaEtIgnis.MODID,"copper"),
                List.of(Tiers.STONE),
                List.of(Tiers.IRON)
        );

        TIN = TierSortingRegistry.registerTier(
                new ForgeTier(6,120,2f,3f,10,
                        ModTags.Blocks.NEEDS_TIN_TOOL,() -> Ingredient.of(ModItems.TIN_INGOT.get())),
                ResourceLocation.tryBuild(LaminaEtIgnis.MODID,"tin"),
                List.of(BONE),
                List.of(COPPER)
        );
    }
}
