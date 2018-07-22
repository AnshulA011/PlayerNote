package playernote.cmd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import playernote.plugin.Note;
import playernote.plugin.NoteHandler;

public class WipeServerNotes implements CommandExecutor{
	NoteHandler notehdlr;
	public WipeServerNotes(NoteHandler notehdlr) {
		this.notehdlr = notehdlr;
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("clearservernotes") && args.length == 1 && sender.hasPermission("clearservernotes.permission")) {
			notehdlr.setServerNotes(new HashMap<UUID, ArrayList<Note>>());
			return true;
		}
		return false;
	}

}
