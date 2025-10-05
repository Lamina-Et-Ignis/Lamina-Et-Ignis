package net.lordofthetime.laminaetignis.recipe;

import net.lordofthetime.laminaetignis.LaminaEtIgnis;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModRecipes {

    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, LaminaEtIgnis.MODID);

    public static final RegistryObject<RecipeSerializer<DryingRecipe>> DRYING_SERIALIZER =
            SERIALIZERS.register("drying",() -> DryingRecipe.Serializer.INSTANCE);

    public static void register(IEventBus eventBus){
        SERIALIZERS.register(eventBus);
    }
}
