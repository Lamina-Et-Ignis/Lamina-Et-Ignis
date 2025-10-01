package net.lordofthetime.laminaetignis.item.custom;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;

public class MehtySwordItem extends SwordItem {
    private int durabilityUse = 2;

    public MehtySwordItem(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties,int durabilityUse) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
        this.durabilityUse = durabilityUse;
    }



    @Override
    public boolean hasCraftingRemainingItem(ItemStack stack) {
        return stack.getDamageValue() < this.getMaxDamage(stack);
    }

    @Override
    public ItemStack getCraftingRemainingItem(ItemStack itemStack) {
        ItemStack old = itemStack.copy();
        old.setDamageValue(old.getDamageValue()+this.durabilityUse);
        return old;
    }
}
