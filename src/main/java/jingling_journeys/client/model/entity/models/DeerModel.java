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
// Made with Blockbench 4.12.6
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports
package jingling_journeys.client.model.entity.models;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import jingling_journeys.Main;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class DeerModel<T extends Entity> extends EntityModel<T> {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into
    // this model's constructor
    public static final ModelLayerLocation LAYER_LOCATION =
            new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(Main.MOD_ID, "deer"), "main");
    private final ModelPart deer;
    private final ModelPart head;
    private final ModelPart body;
    private final ModelPart reins;
    private final ModelPart saddle;

    public DeerModel(ModelPart root) {
        this.deer = root.getChild("deer");
        this.head = this.deer.getChild("head");
        this.body = this.deer.getChild("body");
        this.reins = root.getChild("reins");
        this.saddle = root.getChild("saddle");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition deer = partdefinition.addOrReplaceChild("deer", CubeListBuilder.create()
                        .texOffs(0, 41)
                        .addBox(1.0F, -11.0F, -7.5F, 3.0F, 11.0F,
                                3.0F, new CubeDeformation(0.0F))
                        .texOffs(12, 41).addBox(-4.0F, -11.0F, -7.5F,
                                3.0F, 11.0F, 3.0F, new CubeDeformation(0.0F))
                        .texOffs(24, 42).addBox(1.0F, -11.0F, 6.5F,
                                3.0F, 11.0F, 3.0F, new CubeDeformation(0.0F))
                        .texOffs(36, 42).addBox(-4.0F, -11.0F, 6.5F,
                                3.0F, 11.0F, 3.0F, new CubeDeformation(0.0F)),
                PartPose.offset(0.0F, 24.0F, -1.0F));

        PartDefinition head = deer.addOrReplaceChild("head", CubeListBuilder.create()
                        .texOffs(26, 28).addBox(-2.5F, -6.0F, -5.0F,
                                5.0F, 9.0F, 5.0F, new CubeDeformation(0.0F))
                        .texOffs(0, 28).addBox(-2.5F, -11.0F, -8.0F,
                                5.0F, 5.0F, 8.0F, new CubeDeformation(0.0F))
                        .texOffs(48, 46).addBox(-2.0F, -9.0F, -11.0F,
                                4.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)),
                PartPose.offset(0.0F, -17.0F, -6.0F));

        PartDefinition antlersright_r1 = head.addOrReplaceChild("antlersright_r1", CubeListBuilder.create()
                        .texOffs(48, 37).addBox(-1.5F, -8.0F, 0.0F,
                                7.0F, 9.0F, 0.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(-2.5F, -11.0F, -4.0F,
                        -0.0873F, -0.9163F, -0.6545F));

        PartDefinition antlersleft_r1 = head.addOrReplaceChild("antlersleft_r1", CubeListBuilder.create()
                        .texOffs(46, 28).addBox(-5.5F, -8.0F, 0.0F,
                                7.0F, 9.0F, 0.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(2.5F, -11.0F, -4.0F,
                        -0.0873F, 0.9163F, 0.6545F));

        PartDefinition earright_r1 = head.addOrReplaceChild("earright_r1", CubeListBuilder.create()
                        .texOffs(0, 55).addBox(-1.0F, -1.0F, -0.5F,
                                2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(-2.5F, -11.0F, -1.75F,
                        0.0F, 0.0F, -0.7854F));

        PartDefinition earleft_r1 = head.addOrReplaceChild("earleft_r1", CubeListBuilder.create()
                        .texOffs(54, 55).addBox(-2.0F, -1.0F, -0.5F,
                                3.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(2.5F, -12.0F, -3.75F,
                        0.0479F, 0.2129F, 0.2233F));

        PartDefinition earleft_r2 = head.addOrReplaceChild("earleft_r2", CubeListBuilder.create()
                        .texOffs(54, 52).addBox(-1.0F, -1.0F, -0.5F,
                                2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(2.5F, -11.0F, -1.75F,
                        0.0F, 0.0F, 0.7854F));

        PartDefinition body = deer.addOrReplaceChild("body", CubeListBuilder.create()
                        .texOffs(0, 0).addBox(-5.0F, -19.0F, -9.0F,
                                10.0F, 8.0F, 20.0F, new CubeDeformation(0.0F)),
                PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition tail_r1 = body.addOrReplaceChild("tail_r1", CubeListBuilder.create()
                        .texOffs(48, 52).addBox(-0.5F, 0.0F, 0.0F,
                                2.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(-0.5F, -18.7412F, 10.0341F,
                        0.2618F, 0.0F, 0.0F));

        PartDefinition reins = partdefinition.addOrReplaceChild("reins", CubeListBuilder.create(),
                PartPose.offset(0.0F, 32.0F, -3.0F));

        PartDefinition saddle = partdefinition.addOrReplaceChild("saddle", CubeListBuilder.create(),
                PartPose.offset(0.0F, 12.0F, 5.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        deer.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        reins.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        saddle.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}