package lostworlds.client.screen.tablet;

import java.text.NumberFormat;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;

import lostworlds.server.LostWorldsUtils;
import lostworlds.server.entity.terrestrial.TaggedMob;
import lostworlds.server.entity.utils.enums.ActivityType;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;

public class TabletScreen extends Screen {
	public static final ResourceLocation TEXTURE = LostWorldsUtils.rL("textures/gui/tablet.png");
	public static final int FONT_COLOUR = 0x000000;
	private final TaggedMob entity;
	public final int texWidth;
	public final int texHeight;
	protected int titleLabelX;
	protected int titleLabelY;
	public int left;
	public int top;
	public float xMouse;
	public float yMouse;
	protected int imageWidth = 256;
	protected int imageHeight = 180;

	public TabletScreen(TaggedMob entity) {
		super(entity.getDisplayName());

		this.entity = entity;

		this.titleLabelX = 22;
		this.titleLabelY = 20;

		this.texWidth = 255;
		this.texHeight = 192;
	}

	@Override
	public boolean isPauseScreen() {
		return false;
	}

	@Override
	public void render(PoseStack poseStack, int mouseX, int mouseY, float partialTicks) {
		this.xMouse = (float) mouseX;
		this.yMouse = (float) mouseY;
		this.renderBackgroundElements(poseStack);
		this.font.draw(poseStack, this.title, (float) this.titleLabelX, (float) this.titleLabelY, FONT_COLOUR);
		this.renderTextStuff(poseStack, mouseX, mouseY, partialTicks);
		super.render(poseStack, mouseX, mouseY, partialTicks);
		this.renderComponentHoverEffect(poseStack, Style.EMPTY, mouseX, mouseY);
		int i = (this.width - this.imageWidth) / 2;
		int j = (this.height - this.imageHeight) / 2;

		poseStack.pushPose();
		poseStack.scale(0.25F, 0.25F, 0.25F);
		InventoryScreen.renderEntityInInventory(i + 140, j + 100, 17, (float) (i + 51) - this.xMouse, (float) (j + 75 - 50) - this.yMouse, this.entity);
		poseStack.popPose();
	}

	public void renderBackgroundElements(PoseStack stack) {
		this.renderBackground(stack, 0);
		RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
		RenderSystem.setShaderTexture(0, TEXTURE);
		stack.translate((this.width / 2) - (this.texWidth / 2), (this.height / 2) - (this.texHeight / 2), 0);
		blit(stack, 0, 0, 0, 0, this.texWidth, this.texHeight);
	}

	public void renderTextStuff(PoseStack poseStack, int mouseX, int mouseY, float partialTicks) {
		this.font.draw(poseStack, TabletLang.AGE, 22.0F, 100.0F, FONT_COLOUR);
		this.font.draw(poseStack, age(), 110.0F, 100.0F, FONT_COLOUR);
		this.font.draw(poseStack, TabletLang.HEATH, 22.0F, 110.0F, FONT_COLOUR);
		this.font.draw(poseStack, heath(), 110.0F, 110.0F, FONT_COLOUR);
		this.font.draw(poseStack, TabletLang.HUNGER, 22.0F, 120.0F, FONT_COLOUR);
		this.font.draw(poseStack, hunger(), 110.0F, 120.0F, FONT_COLOUR);
		this.font.draw(poseStack, TabletLang.DIET, 22.0F, 130.0F, FONT_COLOUR);
		this.font.draw(poseStack, diet(), 110.0F, 130.0F, FONT_COLOUR);
		this.font.draw(poseStack, TabletLang.ACTIVITY_TYPE, 22.0F, 140.0F, FONT_COLOUR);
		this.font.draw(poseStack, activity(), 110.0F, 140.0F, FONT_COLOUR);
		this.font.draw(poseStack, TabletLang.CONTRACEPTIVES, 22.0F, 150.0F, FONT_COLOUR);
		this.font.draw(poseStack, contraceptives(), 110.0F, 150.0F, FONT_COLOUR);
		this.font.draw(poseStack, TabletLang.TAGGED_TO, 22.0F, 160.0F, FONT_COLOUR);
		this.font.draw(poseStack, owner(), 110.0F, 160.0F, FONT_COLOUR);
	}

	private Component activity() {
		return this.entity.activity() == ActivityType.CATHEMERAL ? LostWorldsUtils.tTC("tablet", "cathemeral") : this.entity.activity() == ActivityType.NOCTURNAL ? LostWorldsUtils.tTC("tablet", "nocturnal") : this.entity.activity() == ActivityType.CREPUSCULAR ? LostWorldsUtils.tTC("tablet", "crepuscular") : LostWorldsUtils.tTC("tablet", "diurnal");
	}

	private Component contraceptives() {
		return this.entity.isOnContraceptives() ? LostWorldsUtils.tTC("tablet", "on_contraceptives") : LostWorldsUtils.tTC("tablet", "off_contraceptives");
	}

	private Component age() {
		int age = this.entity.getAge();
		return age > -1 ? LostWorldsUtils.tTC("tablet", "adult") : LostWorldsUtils.tTC("tablet", "baby");
	}

	private Component heath() {
		NumberFormat numberInstance = NumberFormat.getNumberInstance();
		float heath = this.entity.getHealth();
		String numberHeath = numberInstance.format(heath);
		return new TextComponent(numberHeath);
	}

	private Component hunger() {
		int hunger = this.entity.getHunger();
		int maxhunger = this.entity.maxHunger();
		return hunger >= maxhunger / 2 ? LostWorldsUtils.tTC("tablet", "full") : hunger >= 1 && hunger < 1 ? LostWorldsUtils.tTC("tablet", "satiated") : hunger >= -3000 && hunger <= 0 ? LostWorldsUtils.tTC("tablet", "hungry") : LostWorldsUtils.tTC("tablet", "starving");
	}

	private Component owner() {
		String tag = this.entity.getTaggedToName();
		return new TextComponent(tag);
	}

	private Component diet() {
		return LostWorldsUtils.tTC("tablet", this.entity.diet().toString().toLowerCase());
	}
}
