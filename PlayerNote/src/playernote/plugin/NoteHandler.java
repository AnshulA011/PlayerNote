package playernote.plugin;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;


public class NoteHandler {
	private HashMap<UUID, ArrayList<Note>> serverNotes;
	public NoteHandler(HashMap<UUID, ArrayList<Note>> serverNotes) {
		this.serverNotes = serverNotes;
	}
	
	public void addNote(CommandSender sender, OfflinePlayer other, Date date, String msg, ID type) {
		UUID pid = other.getUniqueId();
		int noteId = serverNotes.get(pid).size()+1;
		serverNotes.get(pid).add(new Note(sender.getName(), other.getName(), date, msg, type, noteId));
	}
	public void removeNote(UUID playerUUID, int noteId) {
		serverNotes.get(playerUUID).remove(noteId);
	}
	public void wipe(UUID playerUUID) {
		serverNotes.remove(playerUUID);
		serverNotes.put(playerUUID, new ArrayList<Note>());
	}
	public ArrayList<Note> getNotes(UUID playerUUID){
		return serverNotes.get(playerUUID);	
	}
	public HashMap<UUID, ArrayList<Note>> getServerNotes(){
		return serverNotes;
	}
	public void setServerNotes(HashMap<UUID, ArrayList<Note>> serverNotes) {
		this.serverNotes = serverNotes;
	}
	public boolean hasNotes(UUID playerUUID) {
		if(serverNotes.containsKey(playerUUID)) {
			return true;
		}
		else{
			return false;
		}
	}
	public void createPage(UUID pid) {
		serverNotes.put(pid, new ArrayList<Note>());
	}
}
