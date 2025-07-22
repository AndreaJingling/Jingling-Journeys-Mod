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

package holiday_mod.client.gui.screens.inventory;

import com.mojang.blaze3d.systems.RenderSystem;
import holiday_mod.Main;
import holiday_mod.world.inventory.SleighConstructionTableMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class SleighConstructionTableScreen extends AbstractContainerScreen<SleighConstructionTableMenu> {
    private static final ResourceLocation GUI_SINGLE =
            ResourceLocation.tryBuild(Main.MOD_ID, "textures/gui/singlesleigh_construction_table_gui.png");
    private static final ResourceLocation GUI_NORMAL =
            ResourceLocation.tryBuild(Main.MOD_ID, "textures/gui/sleigh_construction_table_gui.png");
    private final boolean hasExtension;

    public SleighConstructionTableScreen(SleighConstructionTableMenu pMenu, Inventory pPlayerInventory,
                                         Component pTitle, boolean hasExtension) {
        super(pMenu, pPlayerInventory, pTitle);
        this.hasExtension = hasExtension;
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float v, int i, int i1) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, hasExtension ? GUI_NORMAL : GUI_SINGLE);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        guiGraphics.blit(hasExtension ? GUI_NORMAL : GUI_SINGLE, x, y, 0, 0, imageWidth, imageHeight);
    }

    @Override
    public void render(@NotNull GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        renderBackground(guiGraphics);
        super.render(guiGraphics, mouseX, mouseY, delta);
        renderTooltip(guiGraphics, mouseX, mouseY);
    }
}
