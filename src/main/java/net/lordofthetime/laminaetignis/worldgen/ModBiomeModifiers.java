package net.lordofthetime.laminaetignis.worldgen;

import net.lordofthetime.laminaetignis.LaminaEtIgnis;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers;
import net.minecraftforge.registries.ForgeRegistries;

public class ModBiomeModifiers {
    public static final ResourceKey<BiomeModifier> ADD_TIN_ORE = registerKey("add_tin_ore");

    public static final ResourceKey<BiomeModifier> ADD_CRUDE_IRON_SAND_ORE = registerKey("add_crude_iron_sand_ore");
    public static final ResourceKey<BiomeModifier> ADD_IRON_SAND_ORE = registerKey("add_iron_sand_ore");

    public static final ResourceKey<BiomeModifier> ADD_CRUDE_GOLD_SAND_ORE = registerKey("add_crude_gold_sand_ore");
    public static final ResourceKey<BiomeModifier> ADD_GOLD_SAND_ORE = registerKey("add_gold_sand_ore");



    public static void bootstrap(BootstapContext<BiomeModifier> context) {
        var placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        var biomes = context.lookup(Registries.BIOME);

        context.register(ADD_TIN_ORE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.TIN_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));

        context.register(ADD_CRUDE_IRON_SAND_ORE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_RIVER),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.CRUDE_IRON_SAND_PLACED_KEY)),
                GenerationStep.Decoration.TOP_LAYER_MODIFICATION));
        context.register(ADD_IRON_SAND_ORE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_RIVER),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.IRON_SAND_PLACED_KEY)),
                GenerationStep.Decoration.TOP_LAYER_MODIFICATION));

        context.register(ADD_CRUDE_GOLD_SAND_ORE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.CRUDE_GOLD_SAND_PLACED_KEY)),
                GenerationStep.Decoration.TOP_LAYER_MODIFICATION));
        context.register(ADD_GOLD_SAND_ORE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_RIVER),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.GOLD_SAND_PLACED_KEY)),
                GenerationStep.Decoration.TOP_LAYER_MODIFICATION));

    }

    private static ResourceKey<BiomeModifier> registerKey(String name) {
        return ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS, ResourceLocation.tryBuild(LaminaEtIgnis.MODID, name));
    }
}
