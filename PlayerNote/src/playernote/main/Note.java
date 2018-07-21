package playernote.main;

import java.util.Date;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class Note {
	private String msg;
	private Date date;
	private CommandSender sender;
	private Player other;
	private ID type;
	private int noteID;
	public Note() {}
	public Note(CommandSender sender, Player other, Date date, String msg, ID type, int noteID) {
		this.msg = msg;
		this.date = date;
		this.sender = sender;
		this.other = other;
		this.type = type;
		this.noteID = noteID;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public CommandSender getSender() {
		return sender;
	}
	public void setSender(CommandSender sender) {
		this.sender = sender;
	}
	public Player getOther() {
		return other;
	}
	public void setOther(Player other) {
		this.other = other;
	}
	public ID getType() {
		return type;
	}
	public void setType(ID type) {
		this.type = type;
	}
	public int getNoteID() {
		return noteID;
	}
	public void setNoteID(int noteID) {
		this.noteID = noteID;
	}
}
