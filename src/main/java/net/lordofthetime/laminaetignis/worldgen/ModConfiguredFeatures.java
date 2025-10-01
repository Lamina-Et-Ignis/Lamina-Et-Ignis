package net.lordofthetime.laminaetignis.worldgen;

import net.lordofthetime.laminaetignis.LaminaEtIgnis;
import net.lordofthetime.laminaetignis.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.List;

public class ModConfiguredFeatures {

    public static final ResourceKey<ConfiguredFeature<?,?>> TIN_ORE_KEY = registerKey("tin_ore");

    public static final ResourceKey<ConfiguredFeature<?,?>> CRUDE_IRON_SAND_KEY = registerKey("crude_iron_sand_ore");
    public static final ResourceKey<ConfiguredFeature<?,?>> IRON_SAND_KEY = registerKey("iron_sand_ore");

    public static final ResourceKey<ConfiguredFeature<?,?>> CRUDE_GOLD_SAND_KEY = registerKey("crude_gold_sand_ore");
    public static final ResourceKey<ConfiguredFeature<?,?>> GOLD_SAND_KEY = registerKey("gold_sand_ore");

    public static  void bootStrap(BootstapContext<ConfiguredFeature<?,?>> context){
        RuleTest stoneReplaceable = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest sandReplaceable = new BlockMatchTest(Blocks.SAND);

        List<OreConfiguration.TargetBlockState> tin_ore = List.of(OreConfiguration.target(stoneReplaceable,
                ModBlocks.TIN_ORE.get().defaultBlockState()));

        List<OreConfiguration.TargetBlockState> crude_iron_sand = List.of(OreConfiguration.target(sandReplaceable,
                ModBlocks.CRUDE_IRON_SAND.get().defaultBlockState()));
        List<OreConfiguration.TargetBlockState> iron_sand = List.of(OreConfiguration.target(sandReplaceable,
                ModBlocks.IRON_SAND.get().defaultBlockState()));

        List<OreConfiguration.TargetBlockState> crude_gold_sand = List.of(OreConfiguration.target(sandReplaceable,
                ModBlocks.CRUDE_GOLD_SAND.get().defaultBlockState()));
        List<OreConfiguration.TargetBlockState> gold_sand = List.of(OreConfiguration.target(sandReplaceable,
                ModBlocks.GOLD_SAND.get().defaultBlockState()));


        register(context, TIN_ORE_KEY, Feature.ORE, new OreConfiguration(tin_ore,9));

        register(context, CRUDE_IRON_SAND_KEY, Feature.ORE, new OreConfiguration(crude_iron_sand,32));
        register(context, IRON_SAND_KEY, Feature.ORE, new OreConfiguration(iron_sand,32));
        register(context, CRUDE_GOLD_SAND_KEY, Feature.ORE, new OreConfiguration(crude_gold_sand,32));
        register(context, GOLD_SAND_KEY, Feature.ORE, new OreConfiguration(gold_sand,32));

    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.tryBuild(LaminaEtIgnis.MODID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstapContext<ConfiguredFeature<?, ?>> context, ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
