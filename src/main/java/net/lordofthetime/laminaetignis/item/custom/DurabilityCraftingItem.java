package net.lordofthetime.laminaetignis.item.custom;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class DurabilityCraftingItem extends Item {
    public DurabilityCraftingItem(Properties pProperties) {
        super(pProperties);
    }
    private int durabilityUse = 1;

    public void setDurabilityUse(int durabilityUse){
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
