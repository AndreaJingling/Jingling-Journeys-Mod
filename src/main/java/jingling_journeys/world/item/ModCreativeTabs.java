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

package jingling_journeys.world.item;

import jingling_journeys.Main;
import jingling_journeys.world.level.block.ModBlocks;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeTabs {
    @SuppressWarnings("unused")
    public static final RegistryObject<CreativeModeTab> CREATIVE_TAB =
            Main.CREATIVE_MODE_TABS.register(Main.MOD_ID, () -> CreativeModeTab.builder()
            .icon(()-> ModItems.CANDY_CANE.get().getDefaultInstance())
            .title(Component.translatable("itemGroup." + Main.MOD_ID + ".creative_tab"))
            .displayItems((parameters, output) -> {
                output.accept(ModItems.CANDY_CANE.get());
                output.accept(ModItems.SHARPENED_CANDY_CANE.get());
                output.accept(ModItems.EXAMPLE_GUMMY_CANDY.get());
                output.accept(ModItems.EXAMPLE_WRAPPED_CANDY.get());
                output.accept(ModItems.GENERIC_SMALL_SLED.get());
                output.accept(ModItems.ELF_SPAWN_EGG.get());
                output.accept(ModItems.REINDEER_SPAWN_EGG.get());
                output.accept(ModBlocks.getSleighConstructionTableItem().get());
                output.accept(ModBlocks.getLeatherworkerTableItem().get());
                output.accept(ModBlocks.getToysmithTableItem().get());
                output.accept(ModBlocks.getChimneyItem().get());
                output.accept(ModBlocks.getFireplaceItem().get());
            }).build());

    // Used to load this class at the needed time from main.
    public static void init() {
    }
}
