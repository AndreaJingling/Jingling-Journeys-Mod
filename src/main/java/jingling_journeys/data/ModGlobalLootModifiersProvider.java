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

import jingling_journeys.loot.AddItemModifier;
import jingling_journeys.Main;
import jingling_journeys.world.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.minecraftforge.common.loot.LootTableIdCondition;
import net.minecraftforge.registries.RegistryObject;

public class ModGlobalLootModifiersProvider extends GlobalLootModifierProvider {
    public ModGlobalLootModifiersProvider(PackOutput output) {
        super(output, Main.MOD_ID);
    }

    @Override
    protected void start() {
        for (String path : new String[]{
                "village_armorer",
                "village_butcher",
                "village_cartographer",
                "village_desert_house",
                "village_fisher",
                "village_fletcher",
                "village_mason",
                "village_plains_house",
                "village_savanna_house",
                "village_shepherd",
                "village_snowy_house",
                "village_tannery",
                "village_taiga_house",
                "village_temple",
                "village_toolsmith",
                "village_weaponsmith",
                "abandoned_mineshaft",
                "shipwreck_map",
                "shipwreck_supply",
                "shipwreck_treasure",
                "underwater_ruins_big",
                "underwater_ruins_small",
                "nether_bridge"
        }) {
            ResourceLocation location =
                    ResourceLocation.tryParse("chests/" + (path.contains("village") ? "village/" : "") + path);
            assert location != null;
            for (RegistryObject<Item> item : ModItems.ALL_CANDIES) {
                assert item.getId() != null;
                add(path + "_" + item.getId().getPath(), new AddItemModifier(new LootItemCondition[]{
                        LootItemRandomChanceCondition.randomChance((float) (0.5 / (float) ModItems.ALL_CANDIES.size()))
                                .build(),
                        new LootTableIdCondition.Builder(location).build()
                }, item.get(), 1, 5));
            }
        }
    }
}
