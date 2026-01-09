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

package jingling_journeys.stats;

import jingling_journeys.Main;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.stats.StatFormatter;
import net.minecraft.stats.StatType;
import net.minecraft.stats.Stats;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegisterEvent;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = Main.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModStats {
    public static final ResourceLocation INTERACT_WITH_SLEIGH_CONSTRUCTION_TABLE
            = makeCustomStat("interact_with_sleigh_construction_table");
    public static final ResourceLocation INTERACT_WITH_TOYSMITH_TABLE
            = makeCustomStat("interact_with_toysmith_table");
    public static final ResourceLocation INTERACT_WITH_LEATHERWORKER_TABLE
            = makeCustomStat("interact_with_leatherworker_table");
    public static final ResourceLocation SLEIGH_ONE_CM
            = makeCustomStat("sleigh_one_cm");
    public static final ResourceLocation REINDEER_ONE_CM
            = makeCustomStat("reindeer_one_cm");

    private static RegistryObject<StatType<?>> registerRegistryType(String id, Registry<?> registry) {
        return Main.STAT_TYPES.register(id, () -> new StatType<>(registry));
    }


    @SuppressWarnings("SameParameterValue")
    public static void registerCustomStat(RegisterEvent event, ResourceLocation value, StatFormatter pFormatter) {
        event.register(Registries.CUSTOM_STAT, value, () -> value);
        Stats.CUSTOM.get(value, pFormatter);
    }

    @SuppressWarnings("SameParameterValue")
    private static ResourceLocation makeCustomStat(String pKey) {
        return ResourceLocation.tryBuild(Main.MOD_ID, pKey);
    }

    // Used to load this class at the needed time from main.
    public static void init() {
    }
}
