package playernote.cmd;

import java.util.Date;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import playernote.plugin.ID;
import playernote.plugin.NoteHandler;

public class CreateNote implements CommandExecutor{
	private NoteHandler notehdlr;
	
	public CreateNote(NoteHandler notehdlr) {
		this.notehdlr = notehdlr;
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (label.equalsIgnoreCase("playernote") && args.length >= 3){
			Player other = null;
			for (Player p : Bukkit.getOnlinePlayers()) {
				if (p.getName() == args[1]) {
					other = p;
				}
			}
			if (other != null) {
				String msg = "";
				for (int i = 3; i < args.length; i++) {
					msg += args[i];
				}
				ID type = null;
				switch (args[2]) {
				case "+":
					type = ID.POSITIVE;
				case "-":
					type = ID.NEGATIVE;
				case "!":
					type = ID.ISSUE;
				}
				if(type != null) {
					notehdlr.addNote(sender, other, new Date(), msg, type);
					sender.sendMessage("Note has been added!");
				}
				else {
					sender.sendMessage("Invalid type!");
					return false;
				}
			}
			else {
				sender.sendMessage("Invalid player!");
				return false;
			}
			
			return true;
		}
		return false;
	}	
}

