package holiday_mod.candies;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

// The sharpened variant of the candy cane, can be obtained by eating a candy cane.
public class SharpenedCandyCane extends Item {
    private final Multimap<Attribute, AttributeModifier> defaultModifiers;

    public SharpenedCandyCane(Properties pProperties) {
        super(pProperties);
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(Attributes.ATTACK_DAMAGE,
                new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Weapon modifier", 4,
                        AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.ATTACK_SPEED,
                new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Weapon modifier", -3.2F,
                        AttributeModifier.Operation.ADDITION));
        this.defaultModifiers = builder.build();
    }

    // I have the properties defined here for continuity.
    public static Properties getProperties() {
        return new Properties()
                .food((new FoodProperties.Builder())
                        .nutrition(4)
                        .saturationMod(1.2F)
                        .effect(() ->
                                new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 10 * 20, 1),
                                1.0F)
                        .alwaysEat()
                        .build());
    }

    public boolean hurtEnemy(ItemStack pStack, @NotNull LivingEntity pTarget, @NotNull LivingEntity pAttacker) {
        pStack.hurtAndBreak(0, pAttacker, (p_43296_) -> {});
        return true;
    }

    @SuppressWarnings("deprecation")
    public @NotNull Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers
            (@NotNull EquipmentSlot pEquipmentSlot) {
        return pEquipmentSlot == EquipmentSlot.MAINHAND ?
                this.defaultModifiers : super.getDefaultAttributeModifiers(pEquipmentSlot);
    }

}
