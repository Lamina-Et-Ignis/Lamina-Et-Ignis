package net.lordofthetime.laminaetignis.gui.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.lordofthetime.laminaetignis.LaminaEtIgnis;
import net.lordofthetime.laminaetignis.gui.menu.AdvancedBarrelMenu;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.templates.FluidTank;

import static com.mojang.blaze3d.systems.RenderSystem.setShaderColor;

public class AdvancedBarrelScreen extends AbstractContainerScreen<AdvancedBarrelMenu> {
    private static final ResourceLocation TEXTURE =
            ResourceLocation.tryBuild(LaminaEtIgnis.MODID, "textures/gui/advanced_barrel_gui.png");

    public AdvancedBarrelScreen(AdvancedBarrelMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    @Override
    protected void init() {
        super.init();
        this.titleLabelY = 10000;
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        guiGraphics.blit(TEXTURE, x, y, 0, 0, imageWidth, imageHeight);

        FluidTank fluidTank = menu.advancedBarrel.getFluidTank();

        if(!fluidTank.isEmpty()){
            renderTank(guiGraphics,fluidTank.getFluid(),fluidTank.getCapacity(), x,y);
        }

//        renderProgressArrow(guiGraphics,x,y);

        //Render measuring after fluid so its always above fluid render
        guiGraphics.blit(TEXTURE, x+26, y + 23, 177, 27, 10, 32);


    }

    private void renderTank(GuiGraphics guiGraphics, FluidStack fluid, int capacity, int x, int y) {
        TextureAtlasSprite sprite = Minecraft.getInstance()
                .getTextureAtlas(InventoryMenu.BLOCK_ATLAS)
                .apply(IClientFluidTypeExtensions.of(fluid.getFluid()).getStillTexture(fluid));

        int color = IClientFluidTypeExtensions.of(fluid.getFluid()).getTintColor(fluid);
        float a = ((color >> 24) & 0xFF) / 255f;
        float r = ((color >> 16) & 0xFF) / 255f;
        float g = ((color >> 8) & 0xFF) / 255f;
        float b = (color & 0xFF) / 255f;

        x += 26;
        y += 54;
        int maxY = (int)((fluid.getAmount() /  (float)capacity) * 64);

        for(int i = 0; i <= 4; i++){
            int ty = i * 16;
            int renderY = y-ty;

            if(ty < maxY) {
                guiGraphics.blit(x, renderY, 0, 16, 16, sprite, r, g, b, a);
                guiGraphics.blit(x + 16, renderY, 0, 16, 16, sprite, r, g, b, a);
            }
            if(ty >= maxY){
                //renders texture to hide part of the rendered fluid
                guiGraphics.blit(ResourceLocation.tryBuild(LaminaEtIgnis.MODID,"textures/gui/empty.png"),
                        x, renderY+16, 32, ty-maxY, 0, 0, 32,
                        ty-maxY, 32, ty-maxY);
                break;
            }
        }
    }




    private void renderProgressArrow(GuiGraphics guiGraphics, int x, int y) {
        if(menu.isCrafting()) {
            guiGraphics.blit(TEXTURE, x + 103, y + 25, 176, 0, 8, menu.getScaledProgress());
        }
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        renderBackground(guiGraphics);
        super.render(guiGraphics, mouseX, mouseY, delta);
        renderTooltip(guiGraphics, mouseX, mouseY);
    }
}
