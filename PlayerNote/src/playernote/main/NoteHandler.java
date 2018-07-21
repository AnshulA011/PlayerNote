package playernote.main;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.entity.Player;

public class NoteHandler {
	private HashMap<UUID, ArrayList<Note>> serverNotes;
	public NoteHandler(HashMap<UUID, ArrayList<Note>> serverNotes) {
		this.serverNotes = serverNotes;
	}
	
	public void addNote(Player creator, Player other, Date date, String msg, ID type) {
		UUID pid = creator.getUniqueId();
		if(serverNotes.containsKey(pid)) {
			
			int noteId = serverNotes.get(pid).size()+1;
			serverNotes.get(pid).add(new Note(creator, other, date, msg, type, noteId));
		}
		else {
			serverNotes.put(pid, new ArrayList<Note>());
			serverNotes.get(pid).add(new Note(creator, other, date, msg, type, 1));
		}
	}
	public void removeNote(UUID playerUUID, int noteId) {
		serverNotes.get(playerUUID).remove(noteId);
	}
	public ArrayList<Note> getNotes(UUID playerUUID){
		return serverNotes.get(playerUUID);	
	}
}
