package playernote.plugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.plugin.java.JavaPlugin;

import playernote.cmd.CreateNote;
import playernote.cmd.GetNote;

public class NotePlugin extends JavaPlugin{
	public void onEnable() {
		NoteHandler notehdlr = new NoteHandler(new HashMap<UUID, ArrayList<Note>>());
		getCommand("playernote").setExecutor(new CreateNote(notehdlr));
		getCommand("getplayernote").setExecutor(new GetNote(notehdlr));
	}
	public void onDisable() {
		
	}
}
