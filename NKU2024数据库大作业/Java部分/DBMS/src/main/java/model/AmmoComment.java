package model;
import java.sql.Timestamp;

public class AmmoComment {
    private long id;
    private long userId;
    private long ammoId;
    private String userAccount;
    private String content;
    private Timestamp commentTime;
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public long getAmmoId() {
		return ammoId;
	}
	public void setAmmoId(long ammoId) {
		this.ammoId = ammoId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Timestamp getCommentTime() {
		return commentTime;
	}
	public void setCommentTime(Timestamp commentTime) {
		this.commentTime = commentTime;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUserAccount() {
		return userAccount;
	}
	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}
}
