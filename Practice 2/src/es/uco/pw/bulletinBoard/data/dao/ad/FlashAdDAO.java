package es.uco.pw.bulletinBoard.data.dao.ad;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import es.uco.pw.bulletinBoard.business.ad.Ad;
import es.uco.pw.bulletinBoard.business.ad.FlashAd;
import es.uco.pw.bulletinBoard.data.dao.common.AbstractDAO;
import es.uco.pw.bulletinBoard.data.dao.common.DAOException;

public class FlashAdDAO extends AbstractDAO<FlashAd, Integer> {
	
	private AdDAO adDAO = new AdDAO();
	
	public FlashAdDAO(){
		this.tableName= "AD_HAS_DATE_OF_PUBLICATION";
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
	protected void setObjectStatement(PreparedStatement preparedStatement, FlashAd flashAd) throws DAOException {
		
	}

	@Override
	protected void updateIdFromGeneratedKeys(ResultSet generatedKeys, FlashAd flashAd) throws DAOException {
		try {
			if(generatedKeys.next()) flashAd.setId(generatedKeys.getInt(1));
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected FlashAd readObject(ResultSet resultSet) throws DAOException {
		FlashAd flashAd = null;
		try {
			if(resultSet.next()) {
				Ad ad = this.adDAO.readObject(resultSet);
				LocalDate startDate = resultSet.getDate("start_date").toLocalDate();
				LocalDate endDate = resultSet.getDate("end_date").toLocalDate();
				
				flashAd = new FlashAd(ad, startDate, endDate);

			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return flashAd;
	}
	
	@Override
	public int create(FlashAd object) throws DAOException {
		int status = 0;
		try{
			Ad ad = object.toAd();
			this.adDAO.create(ad);
			System.out.println(ad);
			object.setId(ad.getId());
			String query = this.sql.getProperty("AD_HAS_DATE_OF_PUBLICATION_CREATE");
			try {
				PreparedStatement ps = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
				ps.setInt(1, object.getId());
				ps.setDate(2, Date.valueOf(object.getStartDate()));
				ps.setDate(3, Date.valueOf(object.getEndDate()));	
				System.out.println(ps);
				status = ps.executeUpdate();
			}
			catch(SQLException e) {
				System.err.println(e.getMessage());
			}
		}
		catch(Exception e){
			throw new DAOException(e.getMessage(), e);
		}
		return status;
	}
	
	@Override
	public FlashAd read(Integer id) throws DAOException {
		
		Ad ad = this.adDAO.read(id);
		LocalDate startDate = this.getStartDate(id);
		LocalDate endDate = this.getEndDate(id);
		
		return new FlashAd(ad, startDate, endDate);
	}
	
	@Override
	public int delete(Integer id) throws DAOException {
		int status = 0;
		String queryKey = this.tableName.toUpperCase()+"_DELETE";
		String query = this.sql.getProperty(queryKey);
		try {
			PreparedStatement ps= this.connection.prepareStatement(query);
			this.setIdStatement(ps, id);
			status = ps.executeUpdate();
			this.adDAO.delete(id);
		}
		catch(Exception e) {
			throw new DAOException(e.getMessage(), e);
		}
		return status;
	}
	
	public LocalDate getStartDate(Integer id) {
		LocalDate startDate = null;
		String query = this.sql.getProperty("GET_START_DATE");
		try {
			PreparedStatement ps = this.connection.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				startDate = rs.getDate("start_date").toLocalDate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return startDate;
	}
	
	public LocalDate getEndDate(Integer id) {
		LocalDate endDate = null;
		String query = this.sql.getProperty("GET_END_DATE");
		try {
			PreparedStatement ps = this.connection.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				endDate = rs.getDate("end_date").toLocalDate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return endDate;
	}
	
}
