package es.uco.pw.bulletinBoard.business;

public class UserHasInterest {
	private Integer id;
	private Integer userId;
	private Integer interestId;
	
	public UserHasInterest(Integer id, Integer userId, Integer interestId) {
		this.id = id;
		this.userId = userId;
		this.interestId = interestId;
	}
	
	public UserHasInterest(Integer userId, Integer interestId) {
		this.id = null;
		this.userId = userId;
		this.interestId = interestId;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getInterestId() {
		return interestId;
	}

	public void setInterestId(Integer interestId) {
		this.interestId = interestId;
	}


	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((interestId == null) ? 0 : interestId.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)									return true;
		if (obj == null)									return false;
		if (getClass() != obj.getClass())					return false;
		
		UserHasInterest other = (UserHasInterest) obj;
		
		if (id == null && other.id != null)					return false;
		else if (!id.equals(other.id))						return false;
		if (interestId == null && other.interestId != null)	return false;
		else if (!interestId.equals(other.interestId))		return false;
		if (userId == null && other.userId != null)			return false;
		else if (!userId.equals(other.userId))				return false;
		return true;
	}

	@Override
	public String toString() {
		return "UserHasInterest [id=" + id + ", userId=" + userId + ", interestId=" + interestId + "]";
	}
	
	
}
