package es.uco.pw.bulletinBoard.data.dao;

public interface IDAO<P,K> {
	public int create(P object);
	public Object read(K id);
	public int update(K id, P object);
	public int delete(K id);
}
