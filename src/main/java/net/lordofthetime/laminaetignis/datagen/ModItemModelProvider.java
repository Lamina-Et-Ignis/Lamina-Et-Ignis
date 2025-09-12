package net.lordofthetime.laminaetignis.datagen;

import net.lordofthetime.laminaetignis.block.ModBlocks;
import net.lordofthetime.laminaetignis.item.ModItems;
import net.lordofthetime.laminaetignis.LaminaEtIgnis;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
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


//        wallItem(CORRUPTED_BRICK_WALL,ModBlocks.CORRUPTED_BRICKS);
//        wallItem(MAGICAL_BRICK_WALL,ModBlocks.MAGICAL_BRICKS);

        for(RegistryObject<Item> item : ModItems.ITEMS.getEntries()){
            System.out.println(item.getId());
            if(!String.valueOf(item.getId()).endsWith("wall")){
                simpleItem(item);
            }

        }

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
