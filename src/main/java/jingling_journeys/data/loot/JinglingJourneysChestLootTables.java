/**
 *         Jingling Journeys - Give your Minecraft World a little Festive Cheer, Sleigh Riding Fun, and 
 *         The Spirit of the Season.<br>
 *         Copyright (C) 2025-2026  Jingling Journeys Team<br>
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

package jingling_journeys.data.loot;

import jingling_journeys.Main;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import org.jetbrains.annotations.NotNull;

import java.util.function.BiConsumer;

public class JinglingJourneysChestLootTables implements LootTableSubProvider {
    @Override
    public void generate(@NotNull BiConsumer<ResourceLocation, LootTable.Builder> pOutput) {
        pOutput.accept(ResourceLocation.fromNamespaceAndPath(Main.MOD_ID, "chests/village/village_leatherworker"),
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(15.0f))
                                .setBonusRolls(ConstantValue.exactly(0.0f))
                                .add(LootItem.lootTableItem(Items.LEATHER)
                                        .setWeight(50)
                                        .setQuality(4))
                                .add(LootItem.lootTableItem(Items.WHEAT)
                                        .setWeight(25))
                                .add(LootItem.lootTableItem(Items.BEEF)
                                        .setWeight(25))
                                .add(LootItem.lootTableItem(Items.LEATHER_HORSE_ARMOR)
                                        .setWeight(1))
                                .add(LootItem.lootTableItem(Items.LEATHER_HELMET)
                                        .setWeight(1))
                                .add(LootItem.lootTableItem(Items.LEATHER_CHESTPLATE)
                                        .setWeight(1))
                                .add(LootItem.lootTableItem(Items.LEATHER_LEGGINGS)
                                        .setWeight(1))
                                .add(LootItem.lootTableItem(Items.LEATHER_BOOTS)
                                        .setWeight(1))
                                .add(LootItem.lootTableItem(Items.RED_DYE)
                                        .setWeight(15))
                        ));
    }
}
