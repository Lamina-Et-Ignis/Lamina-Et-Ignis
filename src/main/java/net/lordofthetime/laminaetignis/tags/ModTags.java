package net.lordofthetime.laminaetignis.tags;

import net.lordofthetime.laminaetignis.LaminaEtIgnis;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static class Blocks{

        public static final TagKey<Block> NEEDS_BONE_TOOL = tag("needs_bone_tool");
        public static final TagKey<Block> NEEDS_TIN_TOOL = tag("needs_tin_tool");
        public static final TagKey<Block> NEEDS_COPPER_TOOL = tag("needs_copper_tool");

        private static TagKey<Block> tag(String name){
            return BlockTags.create(ResourceLocation.tryBuild(LaminaEtIgnis.MODID,name));
        }
    }
    public static class Items{

        public static final TagKey<Item> SLING_AMMO = tag("sling_ammo");
        public static final TagKey<Item> KNIFES = tag("knifes");
        public static final TagKey<Item> HIDES = tag("hides");
        public static final TagKey<Item> HAMMER_TIER_0 = tag("hammer_tier_0");
        public static final TagKey<Item> HAMMER_TIER_1 = tag("hammer_tier_1");

        private static TagKey<Item> tag(String name){
            return ItemTags.create(ResourceLocation.tryBuild(LaminaEtIgnis.MODID,name));
        }
    }

}
