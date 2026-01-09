/**
 *         Jingling Journeys (WIP description)<br>
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

package jingling_journeys.data;

import jingling_journeys.data.loot.JinglingJourneysBlockLootTables;
import jingling_journeys.data.loot.JinglingJourneysChestLootTables;
import jingling_journeys.data.loot.JinglingJourneysEntityLootTables;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.List;
import java.util.Set;

public class ModLootTableProvider {
    public static LootTableProvider create(PackOutput packOutput) {
        return new LootTableProvider(packOutput, Set.of(), List.of(
                new LootTableProvider
                        .SubProviderEntry(JinglingJourneysBlockLootTables::new, LootContextParamSets.BLOCK),
                new LootTableProvider
                        .SubProviderEntry(JinglingJourneysEntityLootTables::new, LootContextParamSets.ENTITY),
                new LootTableProvider
                        .SubProviderEntry(JinglingJourneysChestLootTables::new, LootContextParamSets.CHEST)));
    }
}
