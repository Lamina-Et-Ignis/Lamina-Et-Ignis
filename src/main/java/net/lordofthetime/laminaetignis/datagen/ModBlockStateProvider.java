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

        for (RegistryObject<Block> ore : ModBlocks.ORES.getEntries()) {
            System.out.println("Genereting BlockState for "+ore.getId());
            simpleBlockState(ore);
        }
        for (RegistryObject<Block> block : ModBlocks.BLOCKS.getEntries()) {
            System.out.println("Genereting BlockState for "+block.getId());
            String name = block.getId().getPath();
            Block baseBlock = getBaseBlock(name.replaceAll("(_stairs|_slab|_wall|_fence|_button|_fance_gate)","")
                    .replace("brick","bricks")
                    .replace("plank","planks"));
            if(block.get() instanceof SlabBlock){
                slabBlock(((SlabBlock) block.get()),blockTexture(baseBlock),blockTexture(baseBlock));
            }else if(block.get() instanceof StairBlock){
                stairsBlock(((StairBlock) block.get()),blockTexture(baseBlock));
            }else if(block.get() instanceof WallBlock){
                wallBlock(((WallBlock) block.get()),blockTexture(baseBlock));
            }else if(block.get() instanceof LeavesBlock){
                leavesBlock(block); //leaves
            } else if (block.get() instanceof RotatedPillarBlock) {
                baseBlock = getBaseBlock(name);
                if(name.endsWith("log")){
                    if(name.startsWith("stripped")){
                        axisBlock(((RotatedPillarBlock) block.get() ),blockTexture(baseBlock),
                                ResourceLocation.tryBuild(LaminaEtIgnis.MODID, "block/"+name+ "_top"));
                        //stripped log blocks
                    }
                    else{

                        logBlock((RotatedPillarBlock) block.get()); //log blocks
                    }
                }else if (name.endsWith("wood")) { // wood blocks
                    baseBlock = getBaseBlock(name);
                    if (name.startsWith("stripped")) {
                        axisBlock(((RotatedPillarBlock) block.get()), blockTexture(baseBlock), blockTexture(baseBlock)); //stripped wood blocks
                    } else {
                        axisBlock(((RotatedPillarBlock) block.get()), blockTexture(baseBlock), blockTexture(baseBlock)); //wood blocks
                    }
                    blockItem(block, name);
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
