package net.lordofthetime.laminaetignis.item.custom;

import net.lordofthetime.laminaetignis.item.DurabilityCraftable;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class DurabilitySpoolItem extends Item implements DurabilityCraftable {

    private final int durabilityUse;

    public DurabilitySpoolItem(Properties pProperties, int durabilityUse) {
        super(pProperties);
        this.durabilityUse = durabilityUse;
    }

    @Override
    public int getDurabilityUse() {
        return durabilityUse;
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
