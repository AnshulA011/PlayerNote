package playernote.plugin;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerLogin implements Listener{
	private NoteHandler notehdlr;
	public PlayerLogin(NoteHandler notehdlr) {
		this.notehdlr = notehdlr;
	}
	public void onPlayerJoin(PlayerJoinEvent pje){
		Player player = pje.getPlayer();
		if(!notehdlr.hasNotes(player.getUniqueId())) {
			notehdlr.createPage(player.getUniqueId());
		}
	}
}
