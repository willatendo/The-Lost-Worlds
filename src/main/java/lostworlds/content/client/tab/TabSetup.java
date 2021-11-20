package lostworlds.content.client.tab;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;

import org.lwjgl.glfw.GLFW;

import com.google.common.collect.Lists;

import lostworlds.content.ModUtils;
import lostworlds.content.client.tab.widgets.IconButton;
import lostworlds.content.client.tab.widgets.TagButton;
import lostworlds.content.server.init.ItemInit;
import lostworlds.library.block.Plants;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.inventory.CreativeScreen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.client.event.ClientPlayerNetworkEvent;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.ForgeRegistries;

public class TabSetup 
{
	private static final ResourceLocation ICONS = ModUtils.rL("textures/gui/icons.png");
	private static int startIndex;

	private List<TagFilter> itemfilters;
	private List<TagButton> buttons;
	private Button btnScrollUp;
	private Button btnScrollDown;
	private Button btnEnableAll;
	private Button btnDisableAll;
	private boolean viewingLWTab;
	private int guiCenterX = 0;
	private int guiCenterY = 0;

	@SubscribeEvent
	public void onPlayerLogout(ClientPlayerNetworkEvent.LoggedOutEvent event) 
	{
		this.itemfilters = null;
	}

	@SubscribeEvent
	public void onScreenInit(GuiScreenEvent.InitGuiEvent.Post event) 
	{
		if(event.getGui() instanceof CreativeScreen) 
		{
			if(this.itemfilters == null) 
			{
				this.compileItems();
			}

			this.viewingLWTab = false;
			this.guiCenterX = ((CreativeScreen) event.getGui()).getGuiLeft();
			this.guiCenterY = ((CreativeScreen) event.getGui()).getGuiTop();
			this.buttons = new ArrayList<>();

			event.addWidget(this.btnScrollUp = new IconButton(this.guiCenterX - 22, this.guiCenterY - 12, ModUtils.tTC("gui", "scroll_filters_up"), button -> 
			{
				if(startIndex > 0)
				{
					startIndex--;
				}
				this.updateTagButtons();
			}, ICONS, 64, 0));

			event.addWidget(this.btnScrollDown = new IconButton(this.guiCenterX - 22, this.guiCenterY + 127, ModUtils.tTC("gui", "scroll_filters_down"), button -> 
			{
				if(startIndex <= this.itemfilters.size() - 4 - 1)
				{
					startIndex++;
				}
				this.updateTagButtons();
			}, ICONS, 80, 0));

			event.addWidget(this.btnEnableAll = new IconButton(this.guiCenterX - 50, this.guiCenterY + 10, ModUtils.tTC("gui", "enable_filters"), button -> 
			{
				this.itemfilters.forEach(filters -> filters.setEnabled(true));
				this.buttons.forEach(TagButton::updateState);
				Screen screen = Minecraft.getInstance().screen;
				if(screen instanceof CreativeScreen) 
				{
					this.updateItems((CreativeScreen) screen);
				}
			}, ICONS, 96, 0));

			event.addWidget(this.btnDisableAll = new IconButton(this.guiCenterX - 50, this.guiCenterY + 32, ModUtils.tTC("gui", "disable_filters"), button -> 
			{
				this.itemfilters.forEach(filters -> filters.setEnabled(false));
				this.buttons.forEach(TagButton::updateState);
				Screen screen = Minecraft.getInstance().screen;
				if(screen instanceof CreativeScreen) 
				{
					this.updateItems((CreativeScreen) screen);
				}
			}, ICONS, 112, 0));

			this.btnScrollUp.visible = false;
			this.btnScrollDown.visible = false;
			this.btnEnableAll.visible = false;
			this.btnDisableAll.visible = false;

			this.updateTagButtons();

			CreativeScreen screen = (CreativeScreen) event.getGui();
			if(screen.getSelectedTab() == ModUtils.ITEMS.getId()) 
			{
				this.btnScrollUp.visible = true;
				this.btnScrollDown.visible = true;
				this.btnEnableAll.visible = true;
				this.btnDisableAll.visible = true;
				this.viewingLWTab = true;
				this.buttons.forEach(button -> button.visible = true);
				this.updateItems(screen);
			}
			else if(screen.getSelectedTab() == ModUtils.BLOCKS.getId()) 
			{
				this.btnScrollUp.visible = true;
				this.btnScrollDown.visible = true;
				this.btnEnableAll.visible = true;
				this.btnDisableAll.visible = true;
				this.viewingLWTab = true;
				this.buttons.forEach(button -> button.visible = true);
				this.updateItems(screen);
			}
		}
	}

	@SubscribeEvent
	public void onScreenClick(GuiScreenEvent.MouseClickedEvent.Pre event) 
	{
		if(event.getButton() != GLFW.GLFW_MOUSE_BUTTON_LEFT)
		{
			return;
		}

		if(event.getGui() instanceof CreativeScreen) 
		{
			for(Button button : this.buttons) 
			{
				if(button.isMouseOver(event.getMouseX(), event.getMouseY())) 
				{
					if(button.mouseClicked(event.getMouseX(), event.getMouseY(), event.getButton())) 
					{
						return;
					}
				}
			}
		}
	}

	@SubscribeEvent
	public void onScreenDrawPre(GuiScreenEvent.DrawScreenEvent.Pre event) 
	{
		if(event.getGui() instanceof CreativeScreen) 
		{
			CreativeScreen screen = (CreativeScreen) event.getGui();
			if(screen.getSelectedTab() == ModUtils.ITEMS.getId()) 
			{
				if(!this.viewingLWTab) 
				{
					this.updateItems(screen);
					this.viewingLWTab = true;
				}
			} 
			else if(screen.getSelectedTab() == ModUtils.BLOCKS.getId()) 
			{
				if(!this.viewingLWTab) 
				{
					this.updateItems(screen);
					this.viewingLWTab = true;
				}
			} 
			else 
			{
				this.viewingLWTab = false;
			}
		}
	}

	@SubscribeEvent
	public void onScreenDrawPost(GuiScreenEvent.DrawScreenEvent.Post event) 
	{
		if(event.getGui() instanceof CreativeScreen) 
		{
			CreativeScreen screen = (CreativeScreen) event.getGui();
			this.guiCenterX = screen.getGuiLeft();
			this.guiCenterY = screen.getGuiTop();

			if(screen.getSelectedTab() == ModUtils.ITEMS.getId())  
			{
				this.btnScrollUp.visible = true;
				this.btnScrollDown.visible = true;
				this.btnEnableAll.visible = true;
				this.btnDisableAll.visible = true;
				this.buttons.forEach(button -> button.visible = true);

				this.buttons.forEach(button -> 
				{
					button.render(event.getMatrixStack(), event.getMouseX(), event.getMouseY(), event.getRenderPartialTicks());
				});

				this.buttons.forEach(button -> 
				{
					if(button.isMouseOver(event.getMouseX(), event.getMouseY())) 
					{
						screen.renderTooltip(event.getMatrixStack(), button.getCategory().getName(), event.getMouseX(), event.getMouseY());
					}
				});

				if(this.btnEnableAll.isMouseOver(event.getMouseX(), event.getMouseY())) 
				{
					screen.renderTooltip(event.getMatrixStack(), this.btnEnableAll.getMessage(), event.getMouseX(), event.getMouseY());
				}

				if(this.btnDisableAll.isMouseOver(event.getMouseX(), event.getMouseY())) 
				{
					screen.renderTooltip(event.getMatrixStack(), this.btnDisableAll.getMessage(), event.getMouseX(), event.getMouseY());
				}
			} 
			else if(screen.getSelectedTab() == ModUtils.ITEMS.getId())  
			{
				this.btnScrollUp.visible = true;
				this.btnScrollDown.visible = true;
				this.btnEnableAll.visible = true;
				this.btnDisableAll.visible = true;
				this.buttons.forEach(button -> button.visible = true);

				this.buttons.forEach(button -> 
				{
					button.render(event.getMatrixStack(), event.getMouseX(), event.getMouseY(), event.getRenderPartialTicks());
				});

				this.buttons.forEach(button -> 
				{
					if(button.isMouseOver(event.getMouseX(), event.getMouseY())) 
					{
						screen.renderTooltip(event.getMatrixStack(), button.getCategory().getName(), event.getMouseX(), event.getMouseY());
					}
				});

				if(this.btnEnableAll.isMouseOver(event.getMouseX(), event.getMouseY())) 
				{
					screen.renderTooltip(event.getMatrixStack(), this.btnEnableAll.getMessage(), event.getMouseX(), event.getMouseY());
				}

				if(this.btnDisableAll.isMouseOver(event.getMouseX(), event.getMouseY())) 
				{
					screen.renderTooltip(event.getMatrixStack(), this.btnDisableAll.getMessage(), event.getMouseX(), event.getMouseY());
				}
			} 
			else 
			{
				this.btnScrollUp.visible = false;
				this.btnScrollDown.visible = false;
				this.btnEnableAll.visible = false;
				this.btnDisableAll.visible = false;
				this.buttons.forEach(button -> button.visible = false);
			}
		}
	}

	private void updateTagButtons() 
	{
		final Button.IPressable pressable = button -> 
		{
			Screen screen = Minecraft.getInstance().screen;
			if(screen instanceof CreativeScreen) 
			{
				this.updateItems((CreativeScreen) screen);
			}
		};
		this.buttons.clear();
		for(int i = startIndex; i < startIndex + 4 && i < this.itemfilters.size(); i++) 
		{
			TagButton button = new TagButton(this.guiCenterX - 28, this.guiCenterY + 29 * (i - startIndex) + 10, this.itemfilters.get(i), pressable);
			this.buttons.add(button);
		}
		this.btnScrollUp.active = startIndex > 0;
		this.btnScrollDown.active = startIndex <= this.itemfilters.size() - 4 - 1;
	}

	private void updateItems(CreativeScreen screen) 
	{
		CreativeScreen.CreativeContainer container = screen.getMenu();
		LinkedHashSet<Item> categorisedItems = new LinkedHashSet<>();
		for(TagFilter filter : this.itemfilters) 
		{
			if(filter.isEnabled()) 
			{
				categorisedItems.addAll(filter.getItems());
			}
		}

		NonNullList<ItemStack> newItems = NonNullList.create();
		for(Item item : categorisedItems) 
		{
			item.fillItemCategory(ModUtils.ITEMS, newItems);
		}
		
		for(Item item : categorisedItems) 
		{
			item.fillItemCategory(ModUtils.BLOCKS, newItems);
		}

		container.items.clear();
		container.items.addAll(newItems);
		container.items.sort(Comparator.comparingInt(o -> Item.getId(o.getItem())));
		container.scrollTo(0);
	}

	private void compileItems() 
	{
		//Items
		final TagFilter TOOL = new TagFilter(ModUtils.rL("tools"), ItemInit.CRYSTAL_SCARAB_SWORD.getDefaultInstance());
		final TagFilter ARMOUR = new TagFilter(ModUtils.rL("armour"), ItemInit.CLOTH_MASK.getDefaultInstance());
		final TagFilter UTILITIES = new TagFilter(ModUtils.rL("utilities"), ItemInit.WET_PAPER.getDefaultInstance());
		final TagFilter ELECTRONICS = new TagFilter(ModUtils.rL("electronics"), ItemInit.COPPER_WIRE.getDefaultInstance());
		final TagFilter DECORATION = new TagFilter(ModUtils.rL("decoration"), ItemInit.AMBER_KEYCHAIN.getDefaultInstance());
		final TagFilter MISCELLANEOUS = new TagFilter(ModUtils.rL("miscellaneous"), ItemInit.EMPTY_VILE.getDefaultInstance());
		final TagFilter CREATURE_ITEMS = new TagFilter(ModUtils.rL("creature_items"), ItemInit.AMBER.getDefaultInstance());
		final TagFilter PLANT_ITEMS = new TagFilter(ModUtils.rL("plant_items"), Plants.ALETHOPTERIS.getDrop().getDefaultInstance());
		TagFilter[] itemFilters = new TagFilter[] { TOOL, ARMOUR, UTILITIES, ELECTRONICS, DECORATION, MISCELLANEOUS, CREATURE_ITEMS, PLANT_ITEMS };

		ForgeRegistries.ITEMS.getValues().stream().filter(item -> item.getItemCategory() == ModUtils.ITEMS).filter(item -> item.getRegistryName().getNamespace().equals(ModUtils.ID)).forEach(item -> 
		{
			item.getTags().forEach(location -> 
			{
				for(TagFilter filter : itemFilters) 
				{
					if(location.equals(filter.getTag())) 
					{
						filter.add(item);
					}
				}
			});
		});
		
		//Blocks
		final TagFilter SOILS = new TagFilter(ModUtils.rL("soils"), ItemInit.CRYSTAL_SCARAB_SWORD.getDefaultInstance());
		TagFilter[] blockFilters = new TagFilter[] { SOILS };

		ForgeRegistries.ITEMS.getValues().stream().filter(item -> item.getItemCategory() == ModUtils.BLOCKS).filter(item -> item.getRegistryName().getNamespace().equals(ModUtils.ID)).forEach(item -> 
		{
			item.getTags().forEach(location -> 
			{
				for(TagFilter filter : blockFilters) 
				{
					if(location.equals(filter.getTag())) 
					{
						filter.add(item);
					}
				}
			});
		});

		this.itemfilters = new ArrayList<>();
		this.itemfilters.addAll(Arrays.asList(itemFilters));
	}
	
	public static class TagFilter 
	{
		private final List<Item> items = Lists.newArrayList();
		private final ResourceLocation tag;
		private final TranslationTextComponent name;
		private final ItemStack icon;
		private boolean enabled = true;

		public TagFilter(ResourceLocation tag, ItemStack icon) 
		{
			this.tag = tag;
			this.name = new TranslationTextComponent(String.format("gui.%s.%s", tag.getNamespace(), tag.getPath().replace("/", ".")));
			this.icon = icon;
		}

		public ResourceLocation getTag() 
		{
			return tag;
		}

		public ItemStack getIcon() 
		{
			return this.icon;
		}

		public TranslationTextComponent getName() 
		{
			return this.name;
		}

		public void setEnabled(boolean enabled) 
		{
			this.enabled = enabled;
		}

		public boolean isEnabled() 
		{
			return this.enabled;
		}

		public void add(Item item) 
		{
			this.items.add(item);
		}

		public void add(Block block) 
		{
			this.items.add(Item.byBlock(block));
		}

		public List<Item> getItems() 
		{
			return this.items;
		}
	}
}
