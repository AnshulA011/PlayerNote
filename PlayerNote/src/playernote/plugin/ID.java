package playernote.plugin;

import org.bukkit.ChatColor;

public enum ID {
	POSITIVE(ChatColor.GREEN),
	NEGATIVE(ChatColor.RED),
	ISSUE(ChatColor.YELLOW);
	
	private ChatColor color;
	
	public ChatColor getColor() {
		return this.color;
	}
	
	private ID (ChatColor color) {
		this.color = color;
	}


}
