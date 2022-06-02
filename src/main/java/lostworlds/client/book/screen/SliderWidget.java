package lostworlds.client.book.screen;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.util.Mth;

public class SliderWidget extends Widget {
	public final ElementScreen slider;
	public final ElementScreen sliderHighlighted;
	public final ElementScreen sliderDisabled;
	public final ElementScreen slideBarTop;
	public final ElementScreen slideBarBottom;
	public final ScalableElementScreen slideBar;

	protected int minValue;
	protected int maxValue;
	protected int increment;

	protected int currentValue;
	public int sliderOffset;
	protected boolean enabled;
	protected boolean hidden;

	protected boolean isScrolling;
	protected boolean isHighlighted;

	private int clickY;
	private boolean clickedBar;
	private boolean leftMouseDown = false;

	public SliderWidget(ElementScreen slider, ElementScreen sliderHighlighted, ElementScreen sliderDisabled, ElementScreen slideBarTop, ElementScreen slideBarBottom, ScalableElementScreen slideBar) {
		this.slider = slider;
		this.sliderHighlighted = sliderHighlighted;
		this.sliderDisabled = sliderDisabled;
		this.slideBar = slideBar;
		this.slideBarTop = slideBarTop;
		this.slideBarBottom = slideBarBottom;

		this.height = slideBar.h;
		this.width = slideBar.w;
		this.currentValue = this.minValue = 0;
		this.maxValue = slideBar.h;
		this.increment = 1;

		this.sliderOffset = Mth.abs(slideBar.w - slider.w) / 2;

		this.isScrolling = false;
		this.isHighlighted = false;

		this.enabled = true;
		this.hidden = false;
	}

	public void setSize(int height) {
		this.height = height;
	}

	public void setSliderParameters(int min, int max, int stepsize) {
		this.minValue = min;
		this.maxValue = max;
		this.increment = stepsize;

		this.setSliderValue(this.currentValue);
	}

	public int getValue() {
		if (this.isHidden()) {
			return 0;
		}
		return Math.min(this.maxValue, Math.max(this.minValue, this.currentValue));
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public void enable() {
		this.enabled = true;
	}

	public void disable() {
		this.enabled = false;
	}

	public boolean isEnabled() {
		return this.enabled;
	}

	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}

	public void hide() {
		this.hidden = true;
	}

	public void show() {
		this.hidden = false;
	}

	public boolean isHidden() {
		return this.hidden;
	}

	@Override
	public void draw(PoseStack matrixStack) {
		if (this.hidden) {
			return;
		}

		this.slideBarTop.draw(matrixStack, this.xPos, this.yPos);
		this.slideBar.drawScaledY(matrixStack, this.xPos, this.yPos + this.slideBarTop.h, this.getUsableSlidebarHeight());
		this.slideBarBottom.draw(matrixStack, this.xPos, this.yPos + this.height - this.slideBarBottom.h);

		int x = this.xPos + this.sliderOffset;
		int y = this.yPos + this.getSliderTop();

		if (this.enabled) {
			if (this.isScrolling) {
				this.sliderDisabled.draw(matrixStack, x, y);
			} else if (this.isHighlighted) {
				this.sliderHighlighted.draw(matrixStack, x, y);
			} else {
				this.slider.draw(matrixStack, x, y);
			}
		} else {
			this.sliderDisabled.draw(matrixStack, x, y);
		}
	}

	public void update(int mouseX, int mouseY) {
		if (!this.enabled || this.hidden) {
			return;
		}

		int x = mouseX - this.xPos;
		int y = mouseY - this.yPos;

		if (!this.leftMouseDown && this.clickedBar) {
			this.clickedBar = false;
		}

		if (!this.leftMouseDown && this.isScrolling) {
			this.isScrolling = false;
		} else if (this.isScrolling) {
			float d = this.maxValue - this.minValue;
			float val = (float) (y - this.clickY) / (float) (this.getUsableSlidebarHeight() - this.slider.h);
			val *= d;

			if (val < (float) this.increment / 2f) {
				this.setSliderValue(this.minValue);
			} else if (val > this.maxValue - ((float) this.increment / 2f)) {
				this.setSliderValue(this.maxValue);
			} else {
				this.setSliderValue((int) (this.minValue + (float) this.increment * Math.round(val)));
			}
		} else if (x >= 0 && y >= this.getSliderTop() && x - this.sliderOffset <= this.slider.w && y <= this.getSliderTop() + this.slider.h) {
			this.isHighlighted = true;
			if (this.leftMouseDown) {
				this.isScrolling = true;
				this.clickY = y - this.getSliderTop();
			}
		} else if (this.leftMouseDown && !this.clickedBar && x >= 0 && y >= 0 && x <= this.slideBar.w && y <= this.height) {
			if (y < this.getSliderTop()) {
				this.decrement();
			} else {
				this.increment();
			}

			this.clickedBar = true;
		} else {
			this.isHighlighted = false;
		}
	}

	@Override
	public void handleMouseClicked(int mouseX, int mouseY, int mouseButton) {
		if (mouseButton == 0) {
			this.leftMouseDown = true;
		}
	}

	@Override
	public void handleMouseReleased() {
		this.leftMouseDown = false;
	}
	
	public boolean mouseScrolled(double scrollData, boolean useMouseWheel) {
		if (useMouseWheel) {
			if (scrollData > 0.0) {
				this.decrement();
				return true;
			} else if (scrollData < 0.0) {
				this.increment();
				return true;
			}
		}

		return true;
	}

	public int increment() {
		this.setSliderValue(this.currentValue + this.increment);
		return this.currentValue;
	}

	public int decrement() {
		this.setSliderValue(this.currentValue - this.increment);
		return this.currentValue;
	}

	public int setSliderValue(int val) {
		if (val > this.maxValue) {
			val = this.maxValue;
		} else if (val < this.minValue) {
			val = this.minValue;
		}

		this.currentValue = val;
		return this.currentValue;
	}

	private int getSliderTop() {
		float d = this.maxValue - this.minValue;
		d = (float) (this.currentValue - this.minValue) / d;
		d *= this.getUsableSlidebarHeight() - this.slider.h;

		return (int) d + this.slideBarTop.h;
	}

	private int getUsableSlidebarHeight() {
		return this.height - this.slideBarTop.h - this.slideBarBottom.h;
	}
}
