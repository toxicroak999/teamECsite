package com.internousdev.gerbera.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.internousdev.gerbera.dto.MCategoryDTO;
import com.internousdev.gerbera.util.DBConnector;

public class MCategoryDAO {
	public List<MCategoryDTO> getMCategoryList(){
		DBConnector db=new DBConnector();
		Connection con=db.getConnection();
		List<MCategoryDTO> mCategoryList=new ArrayList<MCategoryDTO>();
		String sql="SELECT * FROM m_category";
		try{
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				MCategoryDTO dto=new MCategoryDTO();
				dto.setId(rs.getInt("id"));
				dto.setCategoryId(rs.getInt("category_id"));
				dto.setCategoryName(rs.getString("category_name"));
				dto.setCategoryDescription(rs.getString("category_description"));
				dto.setInsertDate(rs.getDate("insert_date"));
				dto.setUpdateDate(rs.getDate("update_date"));
				mCategoryList.add(dto);
			}
			Iterator<MCategoryDTO> iterator=mCategoryList.iterator();
			if(!(iterator.hasNext())){
				mCategoryList=null;
			}
		}catch (SQLException e){
			e.printStackTrace();
		}
		try{
			con.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return mCategoryList;
	}

}
