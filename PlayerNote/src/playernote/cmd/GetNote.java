package playernote.cmd;

import java.text.SimpleDateFormat;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import playernote.plugin.ID;
import playernote.plugin.Note;
import playernote.plugin.NoteHandler;

public class GetNote implements CommandExecutor{
	
	private NoteHandler notehdlr;
	public GetNote(NoteHandler notehdlr) {
		this.notehdlr = notehdlr;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		/*
		 * 1. Format: /getplayernote <player> <+ or - or !>
		 * 2. Format: /getplayernote <player> 
		 * 
		 * 1. Returns a specific list of notes
		 * 2. Returns all notes
		 */

		if (label.equalsIgnoreCase("getplayernote") && sender.hasPermission("getplayernote.permission") && args.length >= 1) {

			for (UUID pid : notehdlr.getServerNotes().keySet()) {
				OfflinePlayer player = Bukkit.getOfflinePlayer(pid);
				if (player.getName().equalsIgnoreCase(args[0])) {
					sender.sendMessage(player.getName() + "'s Notes");
					sender.sendMessage("----------------------------");
					SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
					if(args.length == 1) {
						for(Note note : notehdlr.getNotes(player.getUniqueId())) {
							
							String msg = "[" + format.format(note.getDate())+ "] " + note.getSender() + ": " + note.getMsg();
							
							if (note.getType() == ID.POSITIVE) {
								sender.sendMessage(note.getType().getColor() + Integer.toString(notehdlr.getNotes(pid).indexOf(note)) + ". " + "(+)" + msg);
							}
							else if(note.getType() == ID.NEGATIVE) {
								sender.sendMessage(note.getType().getColor() + Integer.toString(notehdlr.getNotes(pid).indexOf(note))+ ". " +"(-)" + msg);
							}
							else if(note.getType() == ID.ISSUE) {
								sender.sendMessage(note.getType().getColor() + Integer.toString(notehdlr.getNotes(pid).indexOf(note))+ ". "+ "(!)" + msg);
							}
						}
					}
					else{
						ID goalType = null;
						String typeMsg = "";
						if (args[1].equalsIgnoreCase("+")) {
							goalType = ID.POSITIVE;
							typeMsg = ID.POSITIVE.getColor() + "(+)";
						}
						else if (args[1].equalsIgnoreCase("-")) {
							goalType = ID.NEGATIVE;
							typeMsg = ID.NEGATIVE.getColor() + "(-)";
						}
						else if (args[1].equalsIgnoreCase("!")) {
							goalType = ID.ISSUE;
							typeMsg = ID.ISSUE.getColor() + "(!)";
						}
						
						if (goalType != null) {
							for(Note note : notehdlr.getNotes(player.getUniqueId())) {
								if (note.getType() == goalType) {
									String msg = "[" + format.format(note.getDate())+ "] " + note.getSender()+ ": " + note.getMsg();
									sender.sendMessage(typeMsg + Integer.toString(notehdlr.getNotes(pid).indexOf(note)) + ". " + msg);
								}
								
							}								
						}
						else {
							sender.sendMessage("Invalid type!");
						}
					}
					return true;
				}
			}
			sender.sendMessage("Unknown Minecraft Username!");
			return false;
		}
		return false;
	}
	
}
