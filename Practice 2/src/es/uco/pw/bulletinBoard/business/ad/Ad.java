package es.uco.pw.bulletinBoard.business.ad;

import java.time.LocalDate;
import java.util.ArrayList;

import es.uco.pw.bulletinBoard.business.user.User;

public class Ad {
	Integer id;
	String title;
	String body;
	Integer ownerUser;
	AdType type;
	AdStatus status;
	LocalDate dateOfExpiry;
	ArrayList<User> recipients;
	
	public Ad(Integer id, String title, String body, Integer ownerUser, AdType type, AdStatus status, LocalDate dateOfExpiry) {
		this.id = id;
		this.title = title;
		this.body = body;
		this.ownerUser = ownerUser;
		this.type = type;
		this.status = status;
		this.dateOfExpiry = dateOfExpiry;
	}
	public Ad(String title, String body, Integer ownerUser, AdType type, AdStatus status, LocalDate dateOfExpiry) {
		this.id = null;
		this.title = title;
		this.body = body;
		this.ownerUser = ownerUser;
		this.type = type;
		this.status = status;
		this.dateOfExpiry = dateOfExpiry;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Integer getOwnerUser() {
		return ownerUser;
	}

	public void setOwnerUser(Integer ownerUser) {
		this.ownerUser = ownerUser;
	}

	public AdType getType() {
		return type;
	}

	public void setType(AdType type) {
		this.type = type;
	}

	public AdStatus getStatus() {
		return status;
	}

	public void setStatus(AdStatus status) {
		this.status = status;
	}

	public LocalDate getDateOfExpiry() {
		return dateOfExpiry;
	}

	public void setDateOfExpiry(LocalDate dateOfExpiry) {
		this.dateOfExpiry = dateOfExpiry;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((body == null) ? 0 : body.hashCode());
		result = prime * result + ((dateOfExpiry == null) ? 0 : dateOfExpiry.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((ownerUser == null) ? 0 : ownerUser.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ad other = (Ad) obj;
		if (body == null) {
			if (other.body != null)
				return false;
		} else if (!body.equals(other.body))
			return false;
		if (dateOfExpiry == null) {
			if (other.dateOfExpiry != null)
				return false;
		} else if (!dateOfExpiry.equals(other.dateOfExpiry))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (ownerUser == null) {
			if (other.ownerUser != null)
				return false;
		} else if (!ownerUser.equals(other.ownerUser))
			return false;
		if (status != other.status)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (type != other.type)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Ad [id=" + id + ", title=" + title + ", body=" + body + ", ownerUser=" + ownerUser + ", type=" + type
				+ ", status=" + status + ", dateOfExpiry=" + dateOfExpiry + "]";
	}
	
	
	
}
