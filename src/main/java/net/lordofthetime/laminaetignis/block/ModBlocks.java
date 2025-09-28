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

    public static final RegistryObject<Block> CRUDE_STONE_BRICKS =
            registerBlock("crude_stone_bricks",() -> new Block(BlockBehaviour
                    .Properties.copy(Blocks.STONE_BRICKS)
                    .requiresCorrectToolForDrops()));
    //cobble
    public static final RegistryObject<Block> GRANITE_COBBLE =
            registerBlock("granite_cobble",() -> new Block(BlockBehaviour
                    .Properties.copy(Blocks.COBBLESTONE)
                    .requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> GRANITE_COBBLE_STAIRS =
            registerBlock("granite_cobble_stairs",() -> new StairBlock(() ->  ModBlocks.GRANITE_COBBLE.get().defaultBlockState(),BlockBehaviour
                    .Properties.copy(ModBlocks.GRANITE_COBBLE.get())
                    .requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> GRANITE_COBBLE_SLAB =
            registerBlock("granite_cobble_slab",() -> new SlabBlock(BlockBehaviour
                    .Properties.copy(ModBlocks.GRANITE_COBBLE.get())
                    .requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> GRANITE_COBBLE_WALL =
            registerBlock("granite_cobble_wall",() -> new WallBlock(BlockBehaviour
                    .Properties.copy(ModBlocks.GRANITE_COBBLE.get())
                    .requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> DIORITE_COBBLE =
            registerBlock("diorite_cobble",() -> new Block(BlockBehaviour
                    .Properties.copy(Blocks.COBBLESTONE)
                    .requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> DIORITE_COBBLE_STAIRS =
            registerBlock("diorite_cobble_stairs",() -> new StairBlock(() ->  ModBlocks.DIORITE_COBBLE.get().defaultBlockState(),BlockBehaviour
                    .Properties.copy(ModBlocks.GRANITE_COBBLE.get())
                    .requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> DIORITE_COBBLE_SLAB =
            registerBlock("diorite_cobble_slab",() -> new SlabBlock(BlockBehaviour
                    .Properties.copy(ModBlocks.GRANITE_COBBLE.get())
                    .requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> DIORITE_COBBLE_WALL =
            registerBlock("diorite_cobble_wall",() -> new WallBlock(BlockBehaviour
                    .Properties.copy(ModBlocks.DIORITE_COBBLE.get())
                    .requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> ANDESITE_COBBLE =
            registerBlock("andesite_cobble",() -> new Block(BlockBehaviour
                    .Properties.copy(Blocks.COBBLESTONE)
                    .requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> ANDESITE_COBBLE_STAIRS =
            registerBlock("andesite_cobble_stairs",() -> new StairBlock(() ->  ModBlocks.ANDESITE_COBBLE.get().defaultBlockState(),BlockBehaviour
                    .Properties.copy(ModBlocks.ANDESITE_COBBLE.get())
                    .requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> ANDESITE_COBBLE_SLAB =
            registerBlock("andesite_cobble_slab",() -> new SlabBlock(BlockBehaviour
                    .Properties.copy(ModBlocks.ANDESITE_COBBLE.get())
                    .requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> ANDESITE_COBBLE_WALL =
            registerBlock("andesite_cobble_wall",() -> new WallBlock(BlockBehaviour
                    .Properties.copy(ModBlocks.ANDESITE_COBBLE.get())
                    .requiresCorrectToolForDrops()));

    //Granite Bricks
    public static final RegistryObject<Block> GRANITE_BRICKS =
            registerBlock("granite_bricks",() -> new Block(BlockBehaviour
                    .Properties.copy(Blocks.STONE_BRICKS)
                    .requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> GRANITE_BRICK_STAIRS =
            registerBlock("granite_brick_stairs",() -> new StairBlock(() ->  ModBlocks.GRANITE_BRICKS.get().defaultBlockState(),BlockBehaviour
                    .Properties.copy(Blocks.STONE_BRICKS)
                    .requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> GRANITE_BRICK_SLAB =
            registerBlock("granite_brick_slab",() -> new SlabBlock(BlockBehaviour
                    .Properties.copy(Blocks.STONE_BRICKS)
                    .requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> GRANITE_BRICK_WALL =
            registerBlock("granite_brick_wall",() -> new WallBlock(BlockBehaviour
                    .Properties.copy(Blocks.STONE_BRICKS)
                    .requiresCorrectToolForDrops()));
    //Diorite bricks
    public static final RegistryObject<Block> DIORITE_BRICKS =
            registerBlock("diorite_bricks",() -> new Block(BlockBehaviour
                    .Properties.copy(Blocks.STONE_BRICKS)
                    .requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> DIORITE_BRICK_STAIRS =
            registerBlock("diorite_brick_stairs",() -> new StairBlock(() ->  ModBlocks.GRANITE_BRICKS.get().defaultBlockState(),BlockBehaviour
                    .Properties.copy(Blocks.STONE_BRICKS)
                    .requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> DIORITE_BRICK_SLAB =
            registerBlock("diorite_brick_slab",() -> new SlabBlock(BlockBehaviour
                    .Properties.copy(Blocks.STONE_BRICKS)
                    .requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> DIORITE_BRICK_WALL =
            registerBlock("diorite_brick_wall",() -> new WallBlock(BlockBehaviour
                    .Properties.copy(Blocks.STONE_BRICKS)
                    .requiresCorrectToolForDrops()));
    //Andesite bricks
    public static final RegistryObject<Block> ANDESITE_BRICKS =
            registerBlock("andesite_bricks",() -> new Block(BlockBehaviour
                    .Properties.copy(Blocks.STONE_BRICKS)
                    .requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> ANDESITE_BRICK_STAIRS =
            registerBlock("andesite_brick_stairs",() -> new StairBlock(() ->  ModBlocks.GRANITE_BRICKS.get().defaultBlockState(),BlockBehaviour
                    .Properties.copy(Blocks.STONE_BRICKS)
                    .requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> ANDESITE_BRICK_SLAB =
            registerBlock("andesite_brick_slab",() -> new SlabBlock(BlockBehaviour
                    .Properties.copy(Blocks.STONE_BRICKS)
                    .requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> ANDESITE_BRICK_WALL =
            registerBlock("andesite_brick_wall",() -> new WallBlock(BlockBehaviour
                    .Properties.copy(Blocks.STONE_BRICKS)
                    .requiresCorrectToolForDrops()));
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
