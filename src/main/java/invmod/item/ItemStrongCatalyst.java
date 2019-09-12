package invmod.item;

import net.minecraft.item.Item;


public class ItemStrongCatalyst extends Item
{

	private final String name = "strongCatalyst";

	public ItemStrongCatalyst()
	{
		//this.setRegistryName(this.name);
		//GameRegistry.register(this);
		//this.setUnlocalizedName(Reference.MODID + "_" + this.name);
		this.setMaxStackSize(1);
		//this.setCreativeTab(mod_Invasion.tabInvmod);
	}

	public String getName()
	{
		return this.name;
	}
}
