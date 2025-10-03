package net.lordofthetime.laminaetignis.datagen;

import net.lordofthetime.laminaetignis.block.ModBlocks;
import net.lordofthetime.laminaetignis.item.ModItems;
import net.lordofthetime.laminaetignis.LaminaEtIgnis;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.WallBlock;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Objects;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, LaminaEtIgnis.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {


        wallItem(ModBlocks.ANDESITE_BRICK_WALL,ModBlocks.ANDESITE_BRICKS);
        wallItem(ModBlocks.DIORITE_BRICK_WALL,ModBlocks.DIORITE_BRICKS);
        wallItem(ModBlocks.GRANITE_BRICK_WALL,ModBlocks.GRANITE_BRICKS);
        wallItem(ModBlocks.CALCITE_BRICK_WALL,ModBlocks.CALCITE_BRICKS);
        wallItem(ModBlocks.TUFF_BRICK_WALL,ModBlocks.TUFF_BRICKS);

        wallItem(ModBlocks.CRUDE_ANDESITE_BRICK_WALL,ModBlocks.CRUDE_ANDESITE_BRICKS);
        wallItem(ModBlocks.CRUDE_DIORITE_BRICK_WALL,ModBlocks.CRUDE_DIORITE_BRICKS);
        wallItem(ModBlocks.CRUDE_GRANITE_BRICK_WALL,ModBlocks.CRUDE_GRANITE_BRICKS);
        wallItem(ModBlocks.CRUDE_CALCITE_BRICK_WALL,ModBlocks.CRUDE_CALCITE_BRICKS);
        wallItem(ModBlocks.CRUDE_TUFF_BRICK_WALL,ModBlocks.CRUDE_TUFF_BRICKS);
        wallItem(ModBlocks.CRUDE_DEEPSLATE_BRICK_WALL,ModBlocks.CRUDE_DEEPSLATE_BRICKS);
        wallItem(ModBlocks.CRUDE_STONE_BRICK_WALL,ModBlocks.CRUDE_STONE_BRICKS);

        wallItem(ModBlocks.ANDESITE_COBBLE_WALL,ModBlocks.ANDESITE_COBBLE);
        wallItem(ModBlocks.DIORITE_COBBLE_WALL,ModBlocks.DIORITE_COBBLE);
        wallItem(ModBlocks.GRANITE_COBBLE_WALL,ModBlocks.GRANITE_COBBLE);
        wallItem(ModBlocks.CALCITE_COBBLE_WALL,ModBlocks.CALCITE_COBBLE);
        wallItem(ModBlocks.TUFF_COBBLE_WALL,ModBlocks.TUFF_COBBLE);





        for(RegistryObject<Item> item : ModItems.ITEMS.getEntries()){
            System.out.println(item.getId());
            if(item.get() instanceof BlockItem blockItem) {
                Block block = blockItem.getBlock(); // get the Block behind the BlockItem
                if(block instanceof WallBlock) {
                    continue;
                }
            }
            simpleItem(item);

        };
    }
    public void wallItem(RegistryObject<Block> block, RegistryObject<Block> baseBlock) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/wall_inventory"))
                .texture("wall",  ResourceLocation.tryBuild(LaminaEtIgnis.MODID, "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
    }

    public void specialBlockItem(RegistryObject<Block> block) {
        this.withExistingParent(LaminaEtIgnis.MODID + ":" + ForgeRegistries.BLOCKS.getKey(block.get()).getPath(),
                modLoc("block/" + ForgeRegistries.BLOCKS.getKey(block.get()).getPath()));
    }

    private boolean itemBlocks(String id){
        for(RegistryObject<Block> block : ModBlocks.BLOCKS.getEntries()){
            if(Objects.equals(String.valueOf(block.getId()), id)){
                if(id.endsWith("stairs") || id.endsWith("slab")){
                    specialBlockItem(block);
                    return true;
                }
                return true;
            }
        }

        //uncomment  when ores are added

//        for(RegistryObject<Block> block : ModBlocks.ORES.getEntries()){
//            if(block.getId().toString()==id){
//                return true;
//            }
//        }
        return false;
    }
    private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        if(!itemBlocks(String.valueOf(item.getId()))){
            return withExistingParent(item.getId().getPath(),
                    ResourceLocation.tryParse("item/generated")).texture("layer0",
                    ResourceLocation.tryBuild(LaminaEtIgnis.MODID, "item/" + item.getId().getPath()));
        }

        return null;
    }

}
