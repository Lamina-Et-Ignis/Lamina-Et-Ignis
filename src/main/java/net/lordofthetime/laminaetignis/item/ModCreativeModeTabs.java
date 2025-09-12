package net.lordofthetime.laminaetignis.item;

import net.lordofthetime.laminaetignis.LaminaEtIgnis;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, LaminaEtIgnis.MODID);

    public static final RegistryObject<CreativeModeTab> DumbAdditionsTab = CREATIVE_MODE_TABS.register(LaminaEtIgnis.MODID.toString(), () -> CreativeModeTab.builder()
            .icon(() -> new ItemStack(ModItems.CRUDE_COPPER_INGOT.get()))
            .title(Component.translatable("creativetab.laminaetignistab"))
            .displayItems((itemDisplayParameters, output) -> {
                for(RegistryObject<Item> item : ModItems.ITEMS.getEntries()){
                    output.accept(item.get());
                }

            })
            .build());

    public static void register(IEventBus eventBus){
        CREATIVE_MODE_TABS.register(eventBus);
    }
}