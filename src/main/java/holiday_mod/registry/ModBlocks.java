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

public class ModBlocks {

    public static final RegistryObject<Block> SLEIGH_CONSTRUCTION_TABLE =
            registerBlock(
                    () -> new SleighConstructionTableBlock(BlockBehaviour.Properties.of()));

    private static <T extends Block> RegistryObject<T> registerBlock(Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register("sleigh_construction_table", block);
        registerBlockItem("sleigh_construction_table", toReturn);
        return toReturn;
    }

    @SuppressWarnings("SameParameterValue")
    private static <T extends Block>void registerBlockItem(String name, RegistryObject<T> block) {
        Main.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void init() {
    }
}
