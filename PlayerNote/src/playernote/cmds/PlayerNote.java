package playernote.cmds;

import java.util.Date;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import playernote.main.ID;
import playernote.main.NoteHandler;

public class PlayerNote implements CommandExecutor{
	
	private NoteHandler notehdl;
	
	public PlayerNote(NoteHandler notehdl) {
		this.notehdl = notehdl;
	}
	
	/*
	 * Format: /playernote [to] [+ or - or !] [msg] 
	 */
	
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (label.equalsIgnoreCase("playernote") && sender.hasPermission("playernote.permission")) {

			if (args.length == 3) {
				for (Player other : Bukkit.getOnlinePlayers()) {
					if (other.getName() == args[0]) {
						ID type = null;
						switch(args[1]) {
							case "+":
								type = ID.POSITIVE;
							case "-":
								type = ID.NEGATIVE;
							case "!":
								type = ID.ISSUE;	
						}
						if (type != null) {
							Date currentDate = new Date();
							notehdl.addNote(sender, other, currentDate, args[3], type);
						}
						else {
							return false;
						}
						
					}
				}
			}
			else {
				return false;
			}
				

		}
		return false;
	}

}
