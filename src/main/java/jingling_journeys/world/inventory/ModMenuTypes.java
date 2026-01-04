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

package jingling_journeys.world.inventory;

import jingling_journeys.Main;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.RegistryObject;

public class ModMenuTypes {
    public static final RegistryObject<MenuType<SleighConstructionTableMenu>> SLEIGH_CONSTRUCTION_TABLE_MENU_SINGLE
            = registerMenu((int windowId, Inventory playerInv, FriendlyByteBuf extraData) ->
            new SleighConstructionTableMenu(windowId, playerInv, extraData, ContainerLevelAccess.NULL, false),
            "sleigh_construction_table_menu_single");
    public static final RegistryObject<MenuType<SleighConstructionTableMenu>> SLEIGH_CONSTRUCTION_TABLE_MENU_EXTENDED
            = registerMenu((int windowId, Inventory playerInv, FriendlyByteBuf extraData) ->
            new SleighConstructionTableMenu(windowId, playerInv, extraData, ContainerLevelAccess.NULL, true),
            "sleigh_construction_table_menu_extended");

    @SuppressWarnings("SameParameterValue")
    private static <T extends AbstractContainerMenu>RegistryObject<MenuType<T>>
    registerMenu(IContainerFactory<T> factory, String name) {
        return Main.MENUS.register(name, () -> IForgeMenuType.create(factory));
    }

    // Used to load this class at the needed time from main.
    public static void init() {

    }
}
