package com.feedthebeast.Transformer;

import java.util.Map;

import cpw.mods.fml.relauncher.IFMLLoadingPlugin;

public class TeamLoadingPlugin implements IFMLLoadingPlugin
{
	@Override
	public String[] getASMTransformerClass()
	{
		return new String[] { TeamClassTransformer.class.getName() };
	}

	@Override
	public String getModContainerClass()
	{
		return null;
	}

	@Override
	public String getSetupClass()
	{
		return null;
	}

	@Override
	public void injectData(Map<String, Object> data)
	{
		// TODO Auto-generated method stub

	}

	@Override
	@Deprecated
	public String[] getLibraryRequestClass()
	{
		return null;
	}

}
