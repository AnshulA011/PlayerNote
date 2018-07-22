package playernote.plugin;

import java.io.Serializable;
import java.util.Date;


public class Note implements Serializable{
	private static final long serialVersionUID = 1L;
	private String msg;
	private Date date;
	private String sender;
	private String other;
	private ID type;
	private int noteID;
	
	public Note(String sender, String other, Date date, String msg, ID type, int noteID) {
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
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getOther() {
		return other;
	}
	public void setOther(String other) {
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
