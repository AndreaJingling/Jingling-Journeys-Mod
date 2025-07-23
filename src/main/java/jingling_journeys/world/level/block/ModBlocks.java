/**
 *         Jingling Journeys (WIP description)<br>
 *         Copyright (C) 2025  Jingling Journeys Team (WIP name)<br>
 *         <br>
 *         This file part of Jingling Journeys.<br>
 *         <br>
 *         This program is free software: you can redistribute it and/or modify<br>
 *         it under the terms of the GNU Lesser General Public License as published by<br>
 *         the Free Software Foundation, either version 3 of the License, or<br>
 *         (at your option) any later version.<br>
 *         <br>
 *         This program is distributed in the hope that it will be useful,<br>
 *         but WITHOUT ANY WARRANTY; without even the implied warranty of<br>
 *         MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the<br>
 *         GNU Lesser General Public License for more details.<br>
 *         <br>
 *         You should have received a copy of the GNU Lesser General Public License<br>
 *         along with this program.  If not, see <https://www.gnu.org/licenses/>.<br>
 */

package jingling_journeys.world.level.block;

import jingling_journeys.Main;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

import static jingling_journeys.Main.BLOCKS;

public class ModBlocks {

    public static final RegistryObject<Block> SLEIGH_CONSTRUCTION_TABLE =
            registerBlock("sleigh_construction_table",
                    () -> new SleighConstructionTableBlock(BlockBehaviour.Properties.of()));
    private static RegistryObject<Item> SLEIGH_CONSTRUCTION_TABLE_ITEM;
    public static final RegistryObject<Block> LEATHERWORKER_TABLE =
            registerBlock("leatherworker_table",
                    () -> new LeatherworkerTableBlock(BlockBehaviour.Properties.of()));
    private static RegistryObject<Item> LEATHERWORKER_TABLE_ITEM;
    public static final RegistryObject<Block> TOYSMITH_TABLE =
            registerBlock("toysmith_table",
                    () -> new ToysmithTableBlock(BlockBehaviour.Properties.of()));
    private static RegistryObject<Item> TOYSMITH_TABLE_ITEM;
    public static final RegistryObject<Block> FIREPLACE =
            registerBlock("fireplace",
                    () -> new FireplaceBlock(BlockBehaviour.Properties.of()));
    private static RegistryObject<Item> FIREPLACE_ITEM;
    public static final RegistryObject<Block> CHIMNEY =
            registerBlock("chimney",
                    () -> new ChimneyBlock(BlockBehaviour.Properties.of()));
    private static RegistryObject<Item> CHIMNEY_ITEM;

    @SuppressWarnings("SameParameterValue")
    private static <T extends Block> RegistryObject<T> registerBlock(String id, Supplier<T> block) {
        return BLOCKS.register(id, block);
    }

    @SuppressWarnings("SameParameterValue")
    private static <T extends Block>RegistryObject<Item> registerBlockItem(RegistryObject<T> block) {
        assert block.getId() != null;
        return Main.ITEMS.register(block.getId().getPath(), () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void init() {
        SLEIGH_CONSTRUCTION_TABLE_ITEM = registerBlockItem(SLEIGH_CONSTRUCTION_TABLE);
        LEATHERWORKER_TABLE_ITEM = registerBlockItem(LEATHERWORKER_TABLE);
        TOYSMITH_TABLE_ITEM = registerBlockItem(TOYSMITH_TABLE);
        CHIMNEY_ITEM = registerBlockItem(CHIMNEY);
        FIREPLACE_ITEM = registerBlockItem(FIREPLACE);
    }

    public static RegistryObject<Item> getSleighConstructionTableItem() {
        return SLEIGH_CONSTRUCTION_TABLE_ITEM;
    }

    public static RegistryObject<Item> getLeatherworkerTableItem() {
        return LEATHERWORKER_TABLE_ITEM;
    }

    public static RegistryObject<Item> getToysmithTableItem() {
        return TOYSMITH_TABLE_ITEM;
    }

    public static RegistryObject<Item> getChimneyItem() {
        return CHIMNEY_ITEM;
    }

    public static RegistryObject<Item> getFireplaceItem() {
        return FIREPLACE_ITEM;
    }
}
