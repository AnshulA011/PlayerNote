package playernote.plugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.plugin.java.JavaPlugin;

import playernote.cmd.CreateNote;

public class NotePlugin extends JavaPlugin{
	public void onEnable() {
		getCommand("playernote").setExecutor(new CreateNote(new NoteHandler(new HashMap<UUID, ArrayList<Note>>())));
	}
	public void onDisable() {
		
	}
}
