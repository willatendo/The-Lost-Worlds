package lostworlds.client.books.tyrannibook.client.data.element;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class SizedTyrannobookElement extends TyrannobookElement 
{
	public int width, height;

	public SizedTyrannobookElement(int x, int y, int width, int height) 
	{
		super(x, y);

		this.width = width;
		this.height = height;
	}

	public boolean isHovered(double mouseX, double mouseY) 
	{
		return mouseX > this.x && mouseY > this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
	}
}
