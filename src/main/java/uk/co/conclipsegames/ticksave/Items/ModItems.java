package uk.co.conclipsegames.ticksave.Items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by James on 20/08/2017.
 */
public class ModItems {
    public static ItemBase record;
    public static ItemBase replay;
    public static void init() {
        record = register(new ItemBase("recorder").setCreativeTab(CreativeTabs.REDSTONE));
        replay = register(new ItemBase("replay").setCreativeTab(CreativeTabs.REDSTONE));

    }

    private static <T extends Item> T register(T item) {
        GameRegistry.register(item);

        if (item instanceof ItemBase) {
            ((ItemBase)item).registerItemModel();
        }

        return item;
    }
}
