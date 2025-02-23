package jacksunderscoreusername.holiday_mod.registry;

import jacksunderscoreusername.holiday_mod.Main;
import jacksunderscoreusername.holiday_mod.registry.block.sleighConstructionTable.SleighConstructionTableBlock;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

import static jacksunderscoreusername.holiday_mod.Main.BLOCKS;

public class HolidayBlocks {

    public static final RegistryObject<Block> SLEIGH_CONSTRUCTION_TABLE = registerBlock("sleigh_construction_table", () -> new SleighConstructionTableBlock(BlockBehaviour.Properties.of()));

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block>RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return Main.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void init() {
    }
}
