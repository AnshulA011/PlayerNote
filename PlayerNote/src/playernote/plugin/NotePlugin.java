package playernote.plugin;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.plugin.java.JavaPlugin;

import playernote.cmd.CreateNote;
import playernote.cmd.GetNote;
import playernote.cmd.RemoveNote;

public class NotePlugin extends JavaPlugin{
	private HashMap<UUID, ArrayList<Note>> servernotes;
	//TODO: Add pages to notes
	//TODO: Remove option for notes
	
	public void onEnable() {
		servernotes = readNotes();
		if (servernotes == null) {
			servernotes = new HashMap<UUID, ArrayList<Note>>();
		}
		NoteHandler notehdlr = new NoteHandler(servernotes);
		getServer().getPluginManager().registerEvents(new PlayerLogin(notehdlr), this);
		getCommand("playernote").setExecutor(new CreateNote(notehdlr));
		getCommand("getplayernote").setExecutor(new GetNote(notehdlr));
		getCommand("rmplayernote").setExecutor(new RemoveNote(notehdlr));
	}
	
	public void onDisable() {
		writeNotes(servernotes);
	}
	
	public void writeNotes(HashMap<UUID, ArrayList<Note>> notes){
		try {
			ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("notesdata.bin"));
			os.writeObject(notes);
			os.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@SuppressWarnings("unchecked")
	public HashMap<UUID, ArrayList<Note>> readNotes(){
		HashMap<UUID, ArrayList<Note>> notes = null;
		try {
			ObjectInputStream is = new ObjectInputStream(new FileInputStream("notesdata.bin"));
			notes = (HashMap<UUID, ArrayList<Note>>) is.readObject();
			is.close();
		} catch (IOException | ClassNotFoundException e) {
			
		}
		return notes;
	}
	
}
