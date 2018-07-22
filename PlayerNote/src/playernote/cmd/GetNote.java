package playernote.cmd;

import java.text.SimpleDateFormat;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

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
			for (Player player : Bukkit.getOnlinePlayers()) {
				if (player.getName().equalsIgnoreCase(args[0])) {
					sender.sendMessage(player.getName() + "'s Notes");
					sender.sendMessage("----------------------------");
					SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
					if(notehdlr.hasNotes(player.getUniqueId())) {
						for(Note note : notehdlr.getNotes(player.getUniqueId())) {
							
							String msg = "[" + format.format(note.getDate())+ "] " + note.getSender().getName() + ": " + note.getMsg();
							
							if (note.getType() == ID.POSITIVE) {
									sender.sendMessage(ChatColor.GREEN + "(+)" + msg);
							}
							else if(note.getType() == ID.NEGATIVE) {
								sender.sendMessage(ChatColor.RED + "(-)" + msg);
							}
							else if(note.getType() == ID.ISSUE) {
								sender.sendMessage(ChatColor.DARK_RED + "(!)" + msg);
							}
						}
					}
				}
				else {
					sender.sendMessage("Invalid name!");
					return false;
				}
			}
			
			return true;
		}
		return false;
	}
	
}
