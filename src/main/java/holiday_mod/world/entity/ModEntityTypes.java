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

package holiday_mod.world.entity;

import holiday_mod.world.entity.npc.ElfEntity;
import holiday_mod.world.entity.vehicle.GenericSmallSledEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

import static holiday_mod.Main.ENTITY_TYPES;

public class ModEntityTypes {
    public static final RegistryObject<EntityType<ElfEntity>> elfEntityType
            = registerEntity("elf",
            () -> EntityType.Builder.of(ElfEntity::new, MobCategory.MISC)
                    .sized(0.625F, 0.9375F)
                    .clientTrackingRange(10)
                    .build("elf"));
    public static final RegistryObject<EntityType<GenericSmallSledEntity>> genericSmallSledEntityType
            = registerEntity("generic_small_sled",
            () -> EntityType.Builder.
                            <GenericSmallSledEntity>of(GenericSmallSledEntity::new, MobCategory.MISC)
                    .sized(1.0F, 0.5F)
                    .clientTrackingRange(10)
                    .build("generic_small_sled"));

    public static void init() {
    }

    @SuppressWarnings("SameParameterValue")
    private static <T extends EntityType<?>> RegistryObject<T> registerEntity(String id, Supplier<T> block) {
        return ENTITY_TYPES.register(id, block);
    }
}
