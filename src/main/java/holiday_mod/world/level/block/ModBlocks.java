/**
 *         Minecraft Holiday Mod (WIP description)<br>
 *         Copyright (C) 2025  Minecraft Holiday Mod Team (WIP name)<br>
 *         <br>
 *         This file part of Minecraft Holiday Mod.<br>
 *         <br>
 *         This program is free software: you can redistribute it and/or modify<br>
 *         it under the terms of the GNU General Public License as published by<br>
 *         the Free Software Foundation, either version 3 of the License, or<br>
 *         (at your option) any later version.<br>
 *         <br>
 *         This program is distributed in the hope that it will be useful,<br>
 *         but WITHOUT ANY WARRANTY; without even the implied warranty of<br>
 *         MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the<br>
 *         GNU General Public License for more details.<br>
 *         <br>
 *         You should have received a copy of the GNU General Public License<br>
 *         along with this program.  If not, see <https://www.gnu.org/licenses/>.<br>
 */

package holiday_mod.world.level.block;

import holiday_mod.Main;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

import static holiday_mod.Main.BLOCKS;

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
        return Main.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void init() {
    }

    public static RegistryObject<Item> getSleighConstructionTableItem() {
        return SLEIGH_CONSTRUCTION_TABLE_ITEM;
    }
}
