package playernote.cmd;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import playernote.plugin.NoteHandler;

public class WipeNotes implements CommandExecutor{
	NoteHandler notehdlr;
	public WipeNotes(NoteHandler notehdlr) {
		this.notehdlr = notehdlr;
	}
	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("clearplayernotes") && args.length == 1 && sender.hasPermission("clearplayernotes.permission")) {
			OfflinePlayer player = Bukkit.getOfflinePlayer(args[0]);
			if(player != null) {
				if(notehdlr.hasNotes(player.getUniqueId())) {
					notehdlr.wipe(player.getUniqueId());
				}
				else {
					sender.sendMessage("Player does not have an entry!");
				}
			}
			else {
				sender.sendMessage("Player does not exist!");
			}
			return true;
		}
		return false;
	}

}
