package lostworlds.client.books.tyrannibook.client.data;

public class AppearanceData implements DataItem {
	public int coverColor = 0x8B4631;
	public int arrowColor = 0xFFFFD3;
	public int arrowColorHover = 0xFF541C;
	public int hoverColor = 0x77EE541C;
	public int slotColor = 0xFF844C;
	public int lockedSectionColor = 0x000000;
	public float scale = 0.5F;
	public String title = "";
	public String subtitle = "";
	public boolean drawPageNumbers = true;
	public boolean drawSectionListText = false;
	public int structureButtonColor = 0xe3E3BC;
	public int structureButtonColorHovered = 0x76D1E8;
	public int structureButtonColorToggled = 0x67C768;

	@Override
	public void load() {
	}
}
