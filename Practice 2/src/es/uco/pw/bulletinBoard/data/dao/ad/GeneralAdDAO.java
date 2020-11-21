package es.uco.pw.bulletinBoard.data.dao.ad;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import es.uco.pw.bulletinBoard.business.ad.GeneralAd;
import es.uco.pw.bulletinBoard.data.dao.common.AbstractDAO;
import es.uco.pw.bulletinBoard.data.dao.common.DAOException;

public class GeneralAdDAO extends AbstractDAO<GeneralAd, Integer> {
	
	public GeneralAdDAO() {
		this.tableName = "AD";
	}

	@Override
	protected void setIdStatement(PreparedStatement preparedStatement, Integer id) throws DAOException {
		try {
			preparedStatement.setInt(1, id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void setObjectStatement(PreparedStatement preparedStatement, GeneralAd object) throws DAOException {
		
		
	}

	@Override
	protected void updateIdFromGeneratedKeys(ResultSet generatedKeys, GeneralAd object) throws DAOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected GeneralAd readObject(ResultSet resultSet) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}
	

}
