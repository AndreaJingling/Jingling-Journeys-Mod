package holiday_mod.registry;

import holiday_mod.Main;
import holiday_mod.registry.block.sleighConstructionTable.SleighConstructionTableBlock;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

import static holiday_mod.Main.BLOCKS;
import static holiday_mod.registry.ModItems.ALL_ITEMS;

public class ModBlocks {

    public static final RegistryObject<Block> SLEIGH_CONSTRUCTION_TABLE =
            registerBlock("sleigh_construction_table",
                    () -> new SleighConstructionTableBlock(BlockBehaviour.Properties.of()));
    private static RegistryObject<Item> SLEIGH_CONSTRUCTION_TABLE_ITEM;

    @SuppressWarnings("SameParameterValue")
    private static <T extends Block> RegistryObject<T> registerBlock(String id, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(id, block);
        SLEIGH_CONSTRUCTION_TABLE_ITEM = registerBlockItem(id, toReturn);
        return toReturn;
    }

    @SuppressWarnings("SameParameterValue")
    private static <T extends Block>RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        RegistryObject<Item> item = Main.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
        ALL_ITEMS.add(item);
        return item;
    }

    public static void init() {
    }

    @SuppressWarnings("unused")
    public static RegistryObject<Item> getSleighConstructionTableItem() {
        return SLEIGH_CONSTRUCTION_TABLE_ITEM;
    }
}
