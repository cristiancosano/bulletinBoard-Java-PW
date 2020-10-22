package es.uco.pw.bulletinBoard.business;

public class Interest {
	private Integer id;
	private String value;
	
	public Interest(Integer id, String value) {
		this.id = id;
		this.value = value;
	}
	
	public Interest(String value) {
		this.id = null;
		this.value = value;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)							return true;
		if (obj == null)							return false;
		if (getClass() != obj.getClass())			return false;
		
		Interest other = (Interest) obj;
		
		if (id == null && other.id != null)			return false;
		else if (!id.equals(other.id)) 				return false;
		if (value == null && other.value != null)	return false;
		else if (!value.equals(other.value))		return false;
		
		return true;
	}

	@Override
	public String toString() {
		return "Interest [id=" + id + ", value=" + value + "]";
	}	
	
}
