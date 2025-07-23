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

package jingling_journeys.data.loot;

import jingling_journeys.Main;
import jingling_journeys.world.level.block.ModBlocks;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public class JinglingJourneysLootTables extends BlockLootSubProvider {
    public JinglingJourneysLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        drop(ModBlocks.SLEIGH_CONSTRUCTION_TABLE);
        drop(ModBlocks.LEATHERWORKER_TABLE);
        drop(ModBlocks.TOYSMITH_TABLE);
    }

    @Override
    protected @NotNull Iterable<Block> getKnownBlocks() {
        return Main.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }

    // HELPER METHODS
    @SuppressWarnings("SameParameterValue")
    private void drop(RegistryObject<Block> block) {
        this.dropSelf(block.get());
    }
}
