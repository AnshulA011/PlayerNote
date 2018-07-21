package playernote.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.plugin.java.JavaPlugin;

import playernote.cmds.PlayerNote;

public class PlayerNotePlugin extends JavaPlugin{

	public void onEnable() {
		//TODO: Notehandler's hashmap should come from a file
		NoteHandler noteHandler = new NoteHandler(new HashMap<UUID, ArrayList<Note>>());
		getCommand("playernote").setExecutor(new PlayerNote(noteHandler));
		getLogger().info("PlayerNote Plugin is now enabled!");
	}

	public void onDisable() {
		
	}
}
