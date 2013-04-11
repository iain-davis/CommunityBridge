package org.ruhlendavis.mc.communitybridge;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

/**
 * Class to hold a player's group membership state
 *
 * @author Iain E. Davis <iain@ruhlendavis.org>
 */
public class PlayerGroupState
{
	private String fileName;
	private File playerFolder;

	private String webappPrimaryGroupID;
	private List webappGroupIDs;

	private String permissionsSystemPrimaryGroupName;
	private List permissionsSystemGroupNames;


	public PlayerGroupState(String playerName, File playerDataFolder)
	{
		this.fileName = playerName + ".yml";
		this.playerFolder = playerDataFolder;
		this.webappGroupIDs = new ArrayList();
		this.permissionsSystemGroupNames = new ArrayList();
	}

	public List identifyAdditions(PlayerGroupState newState)
	{
		return new ArrayList();
	}

	public List identifyRemovals(PlayerGroupState newState)
	{
		return new ArrayList();
	}

	public boolean generate()
	{
		return true;
	}

	public void load()
	{
		File playerFile = new File(playerFolder, fileName);

		if (playerFile.exists())
		{
			FileConfiguration playerData = YamlConfiguration.loadConfiguration(playerFile);
			webappPrimaryGroupID = playerData.getString("webapp.primary-group-id", "");
			webappGroupIDs = playerData.getStringList("webapp.group-ids");
			permissionsSystemPrimaryGroupName = playerData.getString("permissions-system.primary-group-name", "");
			permissionsSystemGroupNames = playerData.getStringList("permissions-system.group-names");
		}
	}

	public void save() throws IOException
	{
		File playerFile = new File(playerFolder, fileName);

		FileConfiguration playerData = new YamlConfiguration();
		playerData.set("webapp.primary-group-id", webappPrimaryGroupID);
		playerData.set("webapp.group-ids", webappGroupIDs);
		playerData.set("permissions-system.primary-group-name", permissionsSystemPrimaryGroupName);
		playerData.set("permissions-system.group-names", permissionsSystemGroupNames);

		playerData.save(playerFile);
	}
}
