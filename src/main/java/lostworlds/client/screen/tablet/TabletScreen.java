package lostworlds.client.screen.tablet;

import java.text.NumberFormat;

import com.mojang.blaze3d.matrix.MatrixStack;

import lostworlds.server.LostWorldsUtils;
import lostworlds.server.entity.terrestrial.TaggedEntity;
import lostworlds.server.entity.utils.enums.ActivityType;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

public class TabletScreen extends AbstractTableScreen {
	private final TaggedEntity entity;

	public TabletScreen(TaggedEntity entity) {
		super(entity.getDisplayName());

		this.entity = entity;
	}

	@Override
	public void render(MatrixStack stack, int mouseX, int mouseY, float partialTicks) {
		super.render(stack, mouseX, mouseY, partialTicks);
		this.font.draw(stack, TabletLang.AGE, 22.0F, 100.0F, FONT_COLOUR);
		this.font.draw(stack, age(), 110.0F, 100.0F, FONT_COLOUR);
		this.font.draw(stack, TabletLang.HEATH, 22.0F, 110.0F, FONT_COLOUR);
		this.font.draw(stack, heath(), 110.0F, 110.0F, FONT_COLOUR);
		this.font.draw(stack, TabletLang.HUNGER, 22.0F, 120.0F, FONT_COLOUR);
		this.font.draw(stack, hunger(), 110.0F, 120.0F, FONT_COLOUR);
		this.font.draw(stack, TabletLang.DIET, 22.0F, 130.0F, FONT_COLOUR);
		this.font.draw(stack, diet(), 110.0F, 130.0F, FONT_COLOUR);
		this.font.draw(stack, TabletLang.ACTIVITY_TYPE, 22.0F, 140.0F, FONT_COLOUR);
		this.font.draw(stack, activity(), 110.0F, 140.0F, FONT_COLOUR);
		this.font.draw(stack, TabletLang.CONTRACEPTIVES, 22.0F, 150.0F, FONT_COLOUR);
		this.font.draw(stack, contraceptives(), 110.0F, 150.0F, FONT_COLOUR);
		this.font.draw(stack, TabletLang.TAGGED_TO, 22.0F, 160.0F, FONT_COLOUR);
		this.font.draw(stack, owner(), 110.0F, 160.0F, FONT_COLOUR);
	}

	private ITextComponent activity() {
		return this.entity.activity() == ActivityType.CATHEMERAL ? LostWorldsUtils.tTC("tablet", "cathemeral") : this.entity.activity() == ActivityType.NOCTURNAL ? LostWorldsUtils.tTC("tablet", "nocturnal") : this.entity.activity() == ActivityType.CREPUSCULAR ? LostWorldsUtils.tTC("tablet", "crepuscular") : LostWorldsUtils.tTC("tablet", "diurnal");
	}

	private ITextComponent contraceptives() {
		return this.entity.isOnContraceptives() ? LostWorldsUtils.tTC("tablet", "on_contraceptives") : LostWorldsUtils.tTC("tablet", "off_contraceptives");
	}

	private ITextComponent age() {
		int age = this.entity.getAge();
		return age > -1 ? LostWorldsUtils.tTC("tablet", "adult") : LostWorldsUtils.tTC("tablet", "baby");
	}

	private ITextComponent heath() {
		NumberFormat numberInstance = NumberFormat.getNumberInstance();
		float heath = this.entity.getHealth();
		String numberHeath = numberInstance.format(heath);
		return new StringTextComponent(numberHeath);
	}

	private ITextComponent hunger() {
		int hunger = this.entity.getHunger();
		int maxhunger = this.entity.maxHunger();
		return hunger >= maxhunger / 2 ? LostWorldsUtils.tTC("tablet", "full") : hunger >= 1 && hunger < 1 ? LostWorldsUtils.tTC("tablet", "satiated") : hunger >= -3000 && hunger <= 0 ? LostWorldsUtils.tTC("tablet", "hungry") : LostWorldsUtils.tTC("tablet", "starving");
	}

	private ITextComponent owner() {
		String tag = this.entity.getTaggedToName();
		return new StringTextComponent(tag);
	}

	private ITextComponent diet() {
		return LostWorldsUtils.tTC("tablet", this.entity.diet().toString().toLowerCase());
	}
}
