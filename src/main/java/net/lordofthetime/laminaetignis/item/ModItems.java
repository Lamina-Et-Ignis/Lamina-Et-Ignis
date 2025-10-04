package net.lordofthetime.laminaetignis.item;

import net.lordofthetime.laminaetignis.LaminaEtIgnis;
import net.lordofthetime.laminaetignis.item.custom.*;
import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, LaminaEtIgnis.MODID);
    //sling
    public static final RegistryObject<Item> SLING = ITEMS.register("sling", () -> new SlingItem(new Item.Properties().durability(100)));
    public static final RegistryObject<Item> PEBBLE = ITEMS.register("pebble", () -> new PebbleItem(new Item.Properties()));

    public static final RegistryObject<Item> CALCITE_PEBBLE = ITEMS.register("calcite_pebble", () -> new PebbleItem(new Item.Properties()));
    public static final RegistryObject<Item> TUFF_PEBBLE = ITEMS.register("tuff_pebble", () -> new PebbleItem(new Item.Properties()));
    public static final RegistryObject<Item> DEEPSLATE_PEBBLE = ITEMS.register("deepslate_pebble", () -> new PebbleItem(new Item.Properties()));
    public static final RegistryObject<Item> GRANITE_PEBBLE = ITEMS.register("granite_pebble", () -> new PebbleItem(new Item.Properties()));
    public static final RegistryObject<Item> ANDESITE_PEBBLE = ITEMS.register("andesite_pebble", () -> new PebbleItem(new Item.Properties()));
    public static final RegistryObject<Item> DIORITE_PEBBLE = ITEMS.register("diorite_pebble", () -> new PebbleItem(new Item.Properties()));

    public static final RegistryObject<Item> COW_HIDE_PELT_CLOAK = ITEMS.register("cow_hide_pelt_cloak", () -> new ArmorItem(ModArmorMaterials.COWHIDE, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    //wool production
    public static final RegistryObject<Item> SHEEPSKIN = ITEMS.register("sheepskin", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> FLEECE = ITEMS.register("fleece", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> GREASY_FLEECE = ITEMS.register("greasy_fleece", () -> new WashableItem(new Item.Properties(),FLEECE.get()));


    //crude copper materials
    public static final RegistryObject<Item> CRUDE_COPPER_INGOT = ITEMS.register("crude_copper_ingot", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> CRUDE_COPPER_PLATE = ITEMS.register("crude_copper_plate", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> CRUDE_COPPER_NUGGET = ITEMS.register("crude_copper_nugget", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> CRUDE_COPPER_CHIP = ITEMS.register("crude_copper_chip", () -> new Item(new Item.Properties()));

    //copper materials
    public static final RegistryObject<Item> COPPER_PLATE = ITEMS.register("copper_plate", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> COPPER_NUGGET = ITEMS.register("copper_nugget", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> COPPER_CHIP = ITEMS.register("copper_chip", () -> new Item(new Item.Properties()));

    //crude gold materials
    public static final RegistryObject<Item> CRUDE_GOLD_INGOT = ITEMS.register("crude_gold_ingot", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> CRUDE_GOLD_PLATE = ITEMS.register("crude_gold_plate", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> CRUDE_GOLD_NUGGET = ITEMS.register("crude_gold_nugget", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> CRUDE_GOLD_GRIT = ITEMS.register("crude_gold_grit", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> CRUDE_GOLD_CHIP = ITEMS.register("crude_gold_chip", () -> new Item(new Item.Properties()));

    //gold materials
    public static final RegistryObject<Item> GOLD_PLATE = ITEMS.register("gold_plate", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> GOLD_GRIT = ITEMS.register("gold_grit", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> GOLD_CHIP = ITEMS.register("gold_chip", () -> new Item(new Item.Properties()));

    //crude iron materials
    public static final RegistryObject<Item> CRUDE_IRON_INGOT = ITEMS.register("crude_iron_ingot", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> CRUDE_IRON_PLATE = ITEMS.register("crude_iron_plate", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> CRUDE_IRON_NUGGET = ITEMS.register("crude_iron_nugget", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> CRUDE_IRON_GRIT = ITEMS.register("crude_iron_grit", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> CRUDE_IRON_CHIP = ITEMS.register("crude_iron_chip", () -> new Item(new Item.Properties()));

    //iron materials
    public static final RegistryObject<Item> IRON_PLATE = ITEMS.register("iron_plate", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> IRON_GRIT = ITEMS.register("iron_grit", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> IRON_CHIP = ITEMS.register("iron_chip", () -> new Item(new Item.Properties()));

    //crude tin materials
    public static final RegistryObject<Item> CRUDE_TIN_INGOT = ITEMS.register("crude_tin_ingot", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> CRUDE_TIN_PLATE = ITEMS.register("crude_tin_plate", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> CRUDE_TIN_NUGGET = ITEMS.register("crude_tin_nugget", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> CRUDE_TIN_CHIP = ITEMS.register("crude_tin_chip", () -> new Item(new Item.Properties()));

    //tin materials
    public static final RegistryObject<Item> TIN_INGOT = ITEMS.register("tin_ingot", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> TIN_PLATE = ITEMS.register("tin_plate", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> TIN_NUGGET = ITEMS.register("tin_nugget", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> TIN_CHIP = ITEMS.register("tin_chip", () -> new Item(new Item.Properties()));

    //Ores
    public static final RegistryObject<Item> RAW_TIN = ITEMS.register("raw_tin", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> GOLDVEIN = ITEMS.register("goldvein", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> LEATHER_STRAP = ITEMS.register("leather_strap", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CHALKOUS = ITEMS.register("chalkous", () -> new Item(new Item.Properties()));

    //hides
    public static final RegistryObject<Item> COWHIDE = ITEMS.register("cowhide", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> HIDE = ITEMS.register("hide", () -> new Item(new Item.Properties()));

    //knifes
    public static final RegistryObject<Item> COPPER_KNIFE = ITEMS.register("copper_knife", () -> new KnifeItem(ModToolTiers.COPPER,new Item.Properties().durability(100)));


    //sewing
    public static final RegistryObject<Item> BONE_SHARD = ITEMS.register("bone_shard", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> BONE_NEEDLE = ITEMS.register("bone_needle", () -> new DurabilityCraftingItem(new Item.Properties().durability(16),1));
    public static final RegistryObject<Item> CORD_SPOOL = ITEMS.register("cord_spool", () -> new DurabilityCraftingItem(new Item.Properties().durability(16),1));


    //blueprints
    public static final RegistryObject<Item> SCROLL = ITEMS.register("scroll", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> HELLENIC_BLUEPRINT = ITEMS.register("hellenic_blueprint", () -> new DurabilityCraftingItem(new Item.Properties().durability(64),1));
    public static final RegistryObject<Item> ROMAN_BLUEPRINT = ITEMS.register("roman_blueprint", () -> new DurabilityCraftingItem(new Item.Properties().durability(64),1));

    //wooden tools
    public static final RegistryObject<Item> WOODEN_HAMMER = ITEMS.register("wooden_hammer", () -> new HammerPickaxeItem(Tiers.WOOD,0,-2.5f,new Item.Properties().durability(32),1));
    public static final RegistryObject<Item> WOODEN_CLUB = ITEMS.register("wooden_club", () -> new SwordItem(Tiers.WOOD,2,-3,new Item.Properties()));
    public static final RegistryObject<Item> WRAPPED_WOODEN_CLUB = ITEMS.register("wrapped_wooden_club", () -> new SwordItem(Tiers.WOOD,2,-2.7f,new Item.Properties()));
    //copper tools
    public static final RegistryObject<Item> CRUDE_COPPER_HAMMER = ITEMS.register("crude_copper_hammer", () -> new HammerPickaxeItem(ModToolTiers.COPPER,1,-2.5f,new Item.Properties().durability(100),1));
    public static final RegistryObject<Item> CRUDE_COPPER_MEHTY = ITEMS.register("crude_copper_mehty_pipe", () -> new MehtySwordItem(ModToolTiers.COPPER,0,-2.7f,new Item.Properties().durability(100),2));
    public static final RegistryObject<Item> COPPER_HAMMER = ITEMS.register("copper_hammer", () -> new HammerPickaxeItem(ModToolTiers.COPPER,2,-2.5f,new Item.Properties().durability(150),1));
    public static final RegistryObject<Item> COPPER_MEHTY = ITEMS.register("copper_mehty_pipe", () -> new MehtySwordItem(ModToolTiers.COPPER,2,-2.7f,new Item.Properties().durability(150),2));
    //tin tools
    public static final RegistryObject<Item> CRUDE_TIN_HAMMER = ITEMS.register("crude_tin_hammer", () -> new HammerPickaxeItem(ModToolTiers.TIN,1,-2.5f,new Item.Properties().durability(80),1));
    public static final RegistryObject<Item> CRUDE_TIN_MEHTY = ITEMS.register("crude_tin_mehty_pipe", () -> new MehtySwordItem(ModToolTiers.TIN,-1,-2.7f,new Item.Properties().durability(80),2));
    public static final RegistryObject<Item> TIN_HAMMER = ITEMS.register("tin_hammer", () -> new HammerPickaxeItem(ModToolTiers.TIN,2,-2.5f,new Item.Properties().durability(120),1));
    public static final RegistryObject<Item> TIN_MEHTY = ITEMS.register("tin_mehty_pipe", () -> new MehtySwordItem(ModToolTiers.TIN,1,-2.7f,new Item.Properties().durability(120),2));
    //iron tools





    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
