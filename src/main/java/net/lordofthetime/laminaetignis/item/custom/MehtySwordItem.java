package net.lordofthetime.laminaetignis.item.custom;

import net.lordofthetime.laminaetignis.item.DurabilityCraftable;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;

public class MehtySwordItem extends SwordItem implements DurabilityCraftable {
    private int durabilityUse = 2;

    public MehtySwordItem(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties,int durabilityUse) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
        this.durabilityUse = durabilityUse;
    }



    @Override
    public boolean hasCraftingRemainingItem(ItemStack stack) {
        return hasDurability(stack);
    }

    @Override
    public ItemStack getCraftingRemainingItem(ItemStack itemStack) {
        return applyDurability(itemStack);
    }

    @Override
    public int getDurabilityUse() {
        return durabilityUse;
    }
}
