package holiday_mod.world.inventory;

import holiday_mod.Main;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.RegistryObject;

public class ModMenuTypes {
    public static final RegistryObject<MenuType<SleighConstructionTableMenu>> SLEIGH_CONSTRUCTION_TABLE_MENU
            = registerMenu(SleighConstructionTableMenu::new, "sleigh_construction_table_menu");

    @SuppressWarnings("SameParameterValue")
    private static <T extends AbstractContainerMenu>RegistryObject<MenuType<T>>
    registerMenu(IContainerFactory<T> factory, String name) {
        return Main.MENUS.register(name, () -> IForgeMenuType.create(factory));
    }

    public static void init() {

    }
}
