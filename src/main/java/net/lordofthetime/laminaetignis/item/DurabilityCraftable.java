package net.lordofthetime.laminaetignis.item;

import net.minecraft.world.item.ItemStack;

public interface DurabilityCraftable {

    int getDurabilityUse();

    default boolean hasDurability(ItemStack stack) {
        return stack.getDamageValue() < stack.getMaxDamage();
    }

    default ItemStack applyDurability(ItemStack itemStack) {
        ItemStack old = itemStack.copy();
        old.setDamageValue(old.getDamageValue() + getDurabilityUse());
        return old;
    }
}
