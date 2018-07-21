package playernote.main;

import java.util.Date;

import org.bukkit.entity.Player;


public class Note {
	private String msg;
	private Date date;
	private Player creator;
	private Player other;
	private ID type;
	private int noteID;
	
	public Note(Player creator, Player other, Date date, String msg, ID type, int noteID) {
		this.msg = msg;
		this.date = date;
		this.creator = creator;
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
	public Player getCreator() {
		return creator;
	}
	public void setCreator(Player creator) {
		this.creator = creator;
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
