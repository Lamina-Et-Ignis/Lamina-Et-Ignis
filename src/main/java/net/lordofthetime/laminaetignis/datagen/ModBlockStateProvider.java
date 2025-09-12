package net.lordofthetime.laminaetignis.datagen;

import net.lordofthetime.laminaetignis.block.ModBlocks;
import net.lordofthetime.laminaetignis.LaminaEtIgnis;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, LaminaEtIgnis.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {


        for (RegistryObject<Block> block : ModBlocks.BLOCKS.getEntries()) {
            System.out.println(block.getId());
            String name = block.getId().getPath();
            if(name.endsWith("leaves")){
                leavesBlock(block); //leaves
            } else if (name.endsWith("log")) {
                Block baseBlock = getBaseBlock(name);
                if(name.startsWith("stripped")){
                    axisBlock(((RotatedPillarBlock) block.get() ),blockTexture(baseBlock),
                            ResourceLocation.tryBuild(LaminaEtIgnis.MODID, "block/"+name+ "_top"));
                    //stripped log blocks
                }
                else{

                    logBlock((RotatedPillarBlock) block.get()); //log blocks
                }
                blockItem(block,name);
            }else if (name.endsWith("wood")) { // wood blocks
                Block baseBlock = getBaseBlock(name);
                if (name.startsWith("stripped")) {
                    axisBlock(((RotatedPillarBlock) block.get()), blockTexture(baseBlock), blockTexture(baseBlock)); //stripped wood blocks
                } else {
                    axisBlock(((RotatedPillarBlock) block.get()), blockTexture(baseBlock), blockTexture(baseBlock)); //wood blocks
                }
                blockItem(block,name);
            }else if(name.contains("brick_")){
                Block baseBlock = getBaseBlock(name.replaceAll("(_stairs|_slab|_wall)","").replace("brick","bricks"));
                if(name.endsWith("slab")){
                    slabBlock(((SlabBlock) block.get()),blockTexture(baseBlock),blockTexture(baseBlock));
                }else if(name.endsWith("stairs")){
                    stairsBlock(((StairBlock) block.get()),blockTexture(baseBlock));
                }else{
                    wallBlock(((WallBlock) block.get()),blockTexture(baseBlock));
                }

            }else if(name.contains("plank_")){
                Block baseBlock = getBaseBlock(name.replaceAll("(_stairs|_slab|_wall)","").replace("plank","planks"));
                if(name.endsWith("slab")){
                    slabBlock(((SlabBlock) block.get()),blockTexture(baseBlock),blockTexture(baseBlock));
                }else if(name.endsWith("stairs")){
                    stairsBlock(((StairBlock) block.get()),blockTexture(baseBlock));
                }else{
                    wallBlock(((WallBlock) block.get()),blockTexture(baseBlock));
                }

            }else {
                simpleBlockState(block); //all other blocks
            }
        }

    }
    private Block getBaseBlock(String id){
        return ForgeRegistries.BLOCKS.getValue(ResourceLocation.tryParse(LaminaEtIgnis.MODID + ":"+ id));
    }
    private void simpleBlockState(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }
    private void blockItem(RegistryObject<Block> blockRegistryObject,String name) {
        simpleBlockItem(blockRegistryObject.get(), new ModelFile.UncheckedModelFile(LaminaEtIgnis.MODID +
                ":block/" + name));
    }
    private void leavesBlock(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(),
                models().singleTexture(ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath(), ResourceLocation.tryParse("minecraft:block/leaves"),
                        "all", blockTexture(blockRegistryObject.get())).renderType("cutout"));
    }
}
