package net.lordofthetime.laminaetignis.item.custom;

import net.lordofthetime.laminaetignis.item.DurabilityCraftable;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TieredItem;

public class KnifeItem extends TieredItem implements DurabilityCraftable {
    public KnifeItem(Tier pTier, Properties pProperties) {
        super(pTier, pProperties);
    }

    @Override
    public int getDurabilityUse() {
        return 1;
    }
    @Override
    public boolean hasCraftingRemainingItem(ItemStack stack) {
        return hasDurability(stack);
    }

    @Override
    public ItemStack getCraftingRemainingItem(ItemStack itemStack) {
        return applyDurability(itemStack);
    }
}
