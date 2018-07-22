package playernote.cmd;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
	//How many notes to display in chat
	private final int DISPLAYAMT = 8;
	private NoteHandler notehdlr;
	public GetNote(NoteHandler notehdlr) {
		this.notehdlr = notehdlr;
	}
	
	//Function to see if a string is an int
	public boolean isInteger(String s) {
	    try { 
	        Integer.parseInt(s); 
	    } catch(NumberFormatException e) { 
	        return false; 
	    } catch(NullPointerException e) {
	        return false;
	    }
	    return true;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		/*
		 * 1. Format: /getplayernote <player> <+ or - or !>
		 * 2. Format: /getplayernote <player> 
		 * 3. Format: /getplayernote <player> <pagenumber>
		 * 4. Format: /getplayernote <player> <+ or - or !> <pagenumber>
		 * TODO: 3 & 4
		 * 1. Returns a specific list of notes
		 * 2. Returns all notes
		 */
		
		if (label.equalsIgnoreCase("getplayernote") && sender.hasPermission("getplayernote.permission") && args.length >= 1) {
			//Going through all players in the server notes
			for (UUID pid : notehdlr.getServerNotes().keySet()) {
				OfflinePlayer player = Bukkit.getOfflinePlayer(pid);
				
				//Check if that player is the one the person is looking for
				if (player.getName().equalsIgnoreCase(args[0])) {
					
					//Saving all the notes of the person they specified
					ArrayList<Note> pNotes = notehdlr.getNotes(player.getUniqueId());
					
					//Make it so that it always shows correct total pages
					int sizeOfNotes = pNotes.size();
					int totalPages = sizeOfNotes/DISPLAYAMT + 1;
					
					int pageNum = 1;
					
					//Clamp the size of each page
					int startIndex = (pageNum-1) * DISPLAYAMT;
					int endIndex = pageNum * DISPLAYAMT;
					ID constantType = null;
					//For command 2 & 3, [Reference the multi-line comment below onCommand function]
			
					if (args.length > 1 && isInteger(args[1])) {
						pageNum = Integer.parseInt(args[1]);
					}
					else {
						if (args.length > 1) {
							if (args.length == 3) {
								pageNum = Integer.parseInt(args[2]);
							}
							if (pageNum > totalPages || pageNum <= 0) {
								sender.sendMessage("Page number is invalid!");
								return false;
							}
							else {
								if (args[1].equalsIgnoreCase("+")) {
									constantType = ID.POSITIVE;
								}
								else if (args[1].equalsIgnoreCase("-")) {
									constantType = ID.NEGATIVE;
								}
								else if (args[1].equalsIgnoreCase("!")) {
									constantType = ID.ISSUE;
								}
								else {
									sender.sendMessage("Invalid type!");
									return false;
								}
							}
						}		
					}
					
					if (pageNum >= 1) {
						//Basic layout when sent
						sender.sendMessage(player.getName() + "'s Notes");
						sender.sendMessage("----------------------------");
						SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
						
						//Limiting displaying of notes to the amount specified in the variable DISPLAYAMT
						for(int i = startIndex; i < endIndex; i++) {
							if (i > sizeOfNotes-1) {
								break;
							}
							String msg = "[" + format.format(pNotes.get(i).getDate())+ "] " + pNotes.get(i).getSender() + ": " + pNotes.get(i).getMsg();
							if (constantType != null) {
								if(pNotes.get(i).getType() == constantType)
									sender.sendMessage(constantType.getColor() + Integer.toString(notehdlr.getNotes(pid).indexOf(pNotes.get(i))) + ". " + "(" + args[1] + ")" + msg);
							}
							else {
								if (pNotes.get(i).getType() == ID.POSITIVE) {
									sender.sendMessage(pNotes.get(i).getType().getColor() + Integer.toString(notehdlr.getNotes(pid).indexOf(pNotes.get(i))) + ". " + "(+)" + msg);
								}
								else if(pNotes.get(i).getType() == ID.NEGATIVE) {
									sender.sendMessage(pNotes.get(i).getType().getColor() + Integer.toString(notehdlr.getNotes(pid).indexOf(pNotes.get(i)))+ ". " +"(-)" + msg);
								}
								else if(pNotes.get(i).getType() == ID.ISSUE) {
									sender.sendMessage(pNotes.get(i).getType().getColor() + Integer.toString(notehdlr.getNotes(pid).indexOf(pNotes.get(i)))+ ". "+ "(!)" + msg);
								}
							}
						}
						sender.sendMessage("Page: " + pageNum + "/" + totalPages);
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
