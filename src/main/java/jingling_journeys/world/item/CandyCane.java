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

package jingling_journeys.world.item;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import static jingling_journeys.world.food.ModFoods.CANDY_CANE;

public class CandyCane extends Item {
    public CandyCane(Properties pProperties) {
        super(pProperties);
    }

    public static Item.Properties getProperties() {
        return new Item.Properties().food(CANDY_CANE);
    }

    // If the player is not in creative it consumes the item and gives them the sharpened variant.
    @Override
    public @NotNull ItemStack finishUsingItem(@NotNull ItemStack stack, @NotNull Level level, @NotNull LivingEntity livingEntity) {
        ItemStack newStack = super.finishUsingItem(stack, level, livingEntity);
        return (livingEntity instanceof Player player && player.getAbilities().instabuild) ?
                newStack : ModItems.SHARPENED_CANDY_CANE.get().getDefaultInstance();
    }
}
