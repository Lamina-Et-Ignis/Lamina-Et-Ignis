package net.lordofthetime.laminaetignis.block;

import net.lordofthetime.laminaetignis.LaminaEtIgnis;
import net.lordofthetime.laminaetignis.item.ModItems;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, LaminaEtIgnis.MODID);
    public static final DeferredRegister<Block> ORES = DeferredRegister.create(ForgeRegistries.BLOCKS, LaminaEtIgnis.MODID);



    //ores
    public static final RegistryObject<Block> TIN_ORE = registerOre("tin_ore", () -> new DropExperienceBlock(BlockBehaviour
            .Properties.copy(Blocks.STONE)
            .requiresCorrectToolForDrops()
            .strength(4F)
            .explosionResistance(4F),UniformInt.of(3,6)));

    //oresands
    public static final RegistryObject<Block> IRON_SAND = registerOre("iron_sand", () -> new DropExperienceBlock(BlockBehaviour
            .Properties.copy(Blocks.SAND)
            .requiresCorrectToolForDrops()
            .strength(0.5f)));
    public static final RegistryObject<Block> CRUDE_IRON_SAND = registerOre("crude_iron_sand", () -> new DropExperienceBlock(BlockBehaviour
            .Properties.copy(Blocks.SAND)
            .requiresCorrectToolForDrops()
            .strength(0.5f)));

    public static final RegistryObject<Block> GOLD_SAND = registerOre("gold_sand", () -> new DropExperienceBlock(BlockBehaviour
            .Properties.copy(Blocks.SAND)
            .requiresCorrectToolForDrops()
            .strength(0.5f)));
    public static final RegistryObject<Block> CRUDE_GOLD_SAND = registerOre("crude_gold_sand", () -> new DropExperienceBlock(BlockBehaviour
            .Properties.copy(Blocks.SAND)
            .requiresCorrectToolForDrops()
            .strength(0.5f)));
    //cobble
    public static final RegistryObject<Block> GRANITE_COBBLE =
            registerBricks("granite_cobble",() -> Blocks.COBBLESTONE);
    public static final RegistryObject<Block> GRANITE_COBBLE_STAIRS =
            registerStoneStairs("granite_cobble_stairs",GRANITE_COBBLE);
    public static final RegistryObject<Block> GRANITE_COBBLE_SLAB =
            registerStoneSlab("granite_cobble_slab",GRANITE_COBBLE);
    public static final RegistryObject<Block> GRANITE_COBBLE_WALL =
            registerStoneWall("granite_cobble_wall",GRANITE_COBBLE);

    public static final RegistryObject<Block> DIORITE_COBBLE =
            registerBricks("diorite_cobble",() -> Blocks.COBBLESTONE);
    public static final RegistryObject<Block> DIORITE_COBBLE_STAIRS =
            registerStoneStairs("diorite_cobble_stairs",DIORITE_COBBLE);
    public static final RegistryObject<Block> DIORITE_COBBLE_SLAB =
            registerStoneSlab("diorite_cobble_slab",DIORITE_COBBLE);
    public static final RegistryObject<Block> DIORITE_COBBLE_WALL =
            registerStoneWall("diorite_cobble_wall",DIORITE_COBBLE);

    public static final RegistryObject<Block> ANDESITE_COBBLE =
            registerBricks("andesite_cobble",() -> Blocks.COBBLESTONE);
    public static final RegistryObject<Block> ANDESITE_COBBLE_STAIRS =
            registerStoneStairs("andesite_cobble_stairs",ANDESITE_COBBLE);
    public static final RegistryObject<Block> ANDESITE_COBBLE_SLAB =
            registerStoneSlab("andesite_cobble_slab",ANDESITE_COBBLE);
    public static final RegistryObject<Block> ANDESITE_COBBLE_WALL =
            registerStoneWall("andesite_cobble_wall",ANDESITE_COBBLE);

    public static final RegistryObject<Block> CALCITE_COBBLE =
            registerBricks("calcite_cobble",() -> Blocks.COBBLESTONE);
    public static final RegistryObject<Block> CALCITE_COBBLE_STAIRS =
            registerStoneStairs("calcite_cobble_stairs",GRANITE_COBBLE);
    public static final RegistryObject<Block> CALCITE_COBBLE_SLAB =
            registerStoneSlab("calcite_cobble_slab",GRANITE_COBBLE);
    public static final RegistryObject<Block> CALCITE_COBBLE_WALL =
            registerStoneWall("calcite_cobble_wall",GRANITE_COBBLE);

    public static final RegistryObject<Block> TUFF_COBBLE =
            registerBricks("tuff_cobble",() -> Blocks.COBBLESTONE);
    public static final RegistryObject<Block> TUFF_COBBLE_STAIRS =
            registerStoneStairs("tuff_cobble_stairs",GRANITE_COBBLE);
    public static final RegistryObject<Block> TUFF_COBBLE_SLAB =
            registerStoneSlab("tuff_cobble_slab",GRANITE_COBBLE);
    public static final RegistryObject<Block> TUFF_COBBLE_WALL =
            registerStoneWall("tuff_cobble_wall",GRANITE_COBBLE);

    //Granite Bricks
    public static final RegistryObject<Block> GRANITE_BRICKS =
            registerBricks("granite_bricks",() -> Blocks.STONE_BRICKS);
    public static final RegistryObject<Block> GRANITE_BRICK_STAIRS =
            registerStoneStairs("granite_brick_stairs",GRANITE_BRICKS);
    public static final RegistryObject<Block> GRANITE_BRICK_SLAB =
            registerStoneSlab("granite_brick_slab",GRANITE_BRICKS);
    public static final RegistryObject<Block> GRANITE_BRICK_WALL =
            registerStoneWall("granite_brick_wall",GRANITE_BRICKS);

    public static final RegistryObject<Block> CRUDE_GRANITE_BRICKS =
            registerBricks("crude_granite_bricks",() -> Blocks.STONE_BRICKS);
    public static final RegistryObject<Block> CRUDE_GRANITE_BRICK_STAIRS =
            registerStoneStairs("crude_granite_brick_stairs",CRUDE_GRANITE_BRICKS);
    public static final RegistryObject<Block> CRUDE_GRANITE_BRICK_SLAB =
            registerStoneSlab("crude_granite_brick_slab",CRUDE_GRANITE_BRICKS);
    public static final RegistryObject<Block> CRUDE_GRANITE_BRICK_WALL =
            registerStoneWall("crude_granite_brick_wall",CRUDE_GRANITE_BRICKS);
    //Diorite bricks
    public static final RegistryObject<Block> DIORITE_BRICKS =
            registerBricks("diorite_bricks",() -> Blocks.STONE_BRICKS);
    public static final RegistryObject<Block> DIORITE_BRICK_STAIRS =
            registerStoneStairs("diorite_brick_stairs",DIORITE_BRICKS);
    public static final RegistryObject<Block> DIORITE_BRICK_SLAB =
            registerStoneSlab("diorite_brick_slab",DIORITE_BRICKS);
    public static final RegistryObject<Block> DIORITE_BRICK_WALL =
            registerStoneWall("diorite_brick_wall",DIORITE_BRICKS);

    public static final RegistryObject<Block> CRUDE_DIORITE_BRICKS =
            registerBricks("crude_diorite_bricks",() -> Blocks.STONE_BRICKS);
    public static final RegistryObject<Block> CRUDE_DIORITE_BRICK_STAIRS =
            registerStoneStairs("crude_diorite_brick_stairs",CRUDE_DIORITE_BRICKS);
    public static final RegistryObject<Block> CRUDE_DIORITE_BRICK_SLAB =
            registerStoneSlab("crude_diorite_brick_slab",CRUDE_DIORITE_BRICKS);
    public static final RegistryObject<Block> CRUDE_DIORITE_BRICK_WALL =
            registerStoneWall("crude_diorite_brick_wall",CRUDE_DIORITE_BRICKS);

    //Andesite bricks
    public static final RegistryObject<Block> ANDESITE_BRICKS =
            registerBricks("andesite_bricks",() -> Blocks.STONE_BRICKS);
    public static final RegistryObject<Block> ANDESITE_BRICK_STAIRS =
            registerStoneStairs("andesite_brick_stairs",ANDESITE_BRICKS);
    public static final RegistryObject<Block> ANDESITE_BRICK_SLAB =
            registerStoneSlab("andesite_brick_slab",ANDESITE_BRICKS);
    public static final RegistryObject<Block> ANDESITE_BRICK_WALL =
            registerStoneWall("andesite_brick_wall",ANDESITE_BRICKS);

    public static final RegistryObject<Block> CRUDE_ANDESITE_BRICKS =
            registerBricks("crude_andesite_bricks",() -> Blocks.STONE_BRICKS);
    public static final RegistryObject<Block> CRUDE_ANDESITE_BRICK_STAIRS =
            registerStoneStairs("crude_andesite_brick_stairs",CRUDE_ANDESITE_BRICKS);
    public static final RegistryObject<Block> CRUDE_ANDESITE_BRICK_SLAB =
            registerStoneSlab("crude_andesite_brick_slab",CRUDE_ANDESITE_BRICKS);
    public static final RegistryObject<Block> CRUDE_ANDESITE_BRICK_WALL =
            registerStoneWall("crude_andesite_brick_wall",CRUDE_ANDESITE_BRICKS);

    //Calcite bricks
    public static final RegistryObject<Block> CALCITE_BRICKS =
            registerBricks("calcite_bricks",() -> Blocks.STONE_BRICKS);
    public static final RegistryObject<Block> CALCITE_BRICK_STAIRS =
            registerStoneStairs("calcite_brick_stairs",GRANITE_BRICKS);
    public static final RegistryObject<Block> CALCITE_BRICK_SLAB =
            registerStoneSlab("calcite_brick_slab",GRANITE_BRICKS);
    public static final RegistryObject<Block> CALCITE_BRICK_WALL =
            registerStoneWall("calcite_brick_wall",GRANITE_BRICKS);

    public static final RegistryObject<Block> CRUDE_CALCITE_BRICKS =
            registerBricks("crude_calcite_bricks",() -> Blocks.STONE_BRICKS);
    public static final RegistryObject<Block> CRUDE_CALCITE_BRICK_STAIRS =
            registerStoneStairs("crude_calcite_brick_stairs",CRUDE_CALCITE_BRICKS);
    public static final RegistryObject<Block> CRUDE_CALCITE_BRICK_SLAB =
            registerStoneSlab("crude_calcite_brick_slab",CRUDE_CALCITE_BRICKS);
    public static final RegistryObject<Block> CRUDE_CALCITE_BRICK_WALL =
            registerStoneWall("crude_calcite_brick_wall",CRUDE_CALCITE_BRICKS);

    //Tuff bricks
    public static final RegistryObject<Block> TUFF_BRICKS =
            registerBricks("tuff_bricks",() -> Blocks.STONE_BRICKS);
    public static final RegistryObject<Block> TUFF_BRICK_STAIRS =
            registerStoneStairs("tuff_brick_stairs",TUFF_BRICKS);
    public static final RegistryObject<Block> TUFF_BRICK_SLAB =
            registerStoneSlab("tuff_brick_slab",TUFF_BRICKS);
    public static final RegistryObject<Block> TUFF_BRICK_WALL =
            registerStoneWall("tuff_brick_wall",TUFF_BRICKS);

    public static final RegistryObject<Block> CRUDE_TUFF_BRICKS =
            registerBricks("crude_tuff_bricks",() -> Blocks.STONE_BRICKS);
    public static final RegistryObject<Block> CRUDE_TUFF_BRICK_STAIRS =
            registerStoneStairs("crude_tuff_brick_stairs",CRUDE_TUFF_BRICKS);
    public static final RegistryObject<Block> CRUDE_TUFF_BRICK_SLAB =
            registerStoneSlab("crude_tuff_brick_slab",CRUDE_TUFF_BRICKS);
    public static final RegistryObject<Block> CRUDE_TUFF_BRICK_WALL =
            registerStoneWall("crude_tuff_brick_wall",CRUDE_TUFF_BRICKS);

    //Stone and deepslate crude variants
    public static final RegistryObject<Block> CRUDE_STONE_BRICKS =
            registerBricks("crude_stone_bricks", () -> Blocks.STONE_BRICKS);
    public static final RegistryObject<Block> CRUDE_STONE_BRICK_STAIRS =
            registerStoneStairs("crude_stone_brick_stairs",CRUDE_STONE_BRICKS);
    public static final RegistryObject<Block> CRUDE_STONE_BRICK_SLAB =
            registerStoneSlab("crude_stone_brick_slab",CRUDE_STONE_BRICKS);
    public static final RegistryObject<Block> CRUDE_STONE_BRICK_WALL =
            registerStoneWall("crude_stone_brick_wall",CRUDE_STONE_BRICKS);

    public static final RegistryObject<Block> CRUDE_DEEPSLATE_BRICKS =
            registerBricks("crude_deepslate_bricks", () -> Blocks.DEEPSLATE_BRICKS);
    public static final RegistryObject<Block> CRUDE_DEEPSLATE_BRICK_STAIRS =
            registerStoneStairs("crude_deepslate_brick_stairs",CRUDE_DEEPSLATE_BRICKS);
    public static final RegistryObject<Block> CRUDE_DEEPSLATE_BRICK_SLAB =
            registerStoneSlab("crude_deepslate_brick_slab",CRUDE_DEEPSLATE_BRICKS);
    public static final RegistryObject<Block> CRUDE_DEEPSLATE_BRICK_WALL =
            registerStoneWall("crude_deepslate_brick_wall",CRUDE_DEEPSLATE_BRICKS);

    private static RegistryObject<Block> registerBricks(String name, Supplier<Block> usedBlock) {
        return registerBlock(name, () -> new Block(BlockBehaviour
                .Properties.copy(usedBlock.get())
                .requiresCorrectToolForDrops()));
    }

    private static RegistryObject<Block> registerStoneStairs(String name, Supplier<Block> usedBlock) {
        return registerBlock(name, () -> new StairBlock(usedBlock.get().defaultBlockState(),
                BlockBehaviour.Properties.copy(usedBlock.get()).requiresCorrectToolForDrops()));
    }

    private static RegistryObject<Block> registerStoneSlab(String name, Supplier<Block> usedBlock) {
        return registerBlock(name, () -> new SlabBlock(BlockBehaviour
                .Properties.copy(usedBlock.get())
                .requiresCorrectToolForDrops()));
    }

    private static RegistryObject<Block> registerStoneWall(String name, Supplier<Block> usedBlock) {
        return registerBlock(name, () -> new WallBlock(BlockBehaviour
                .Properties.copy(usedBlock.get())
                .requiresCorrectToolForDrops()));
    }
    private static<T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block){
        RegistryObject<T> toReturn = BLOCKS.register(name,block);
        registerBlockItem(name,toReturn);
        return toReturn;
    }
    private static<T extends Block> RegistryObject<T> registerOre(String name, Supplier<T> block){
        RegistryObject<T> toReturn = BLOCKS.register(name,block);
        registerBlockItem(name,toReturn);
        return toReturn;
    }

    private static<T extends Block>RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block){
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
        ORES.register(eventBus);
    }
}
