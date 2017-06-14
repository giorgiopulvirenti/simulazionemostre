package it.polito.tdp.artsmia.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.polito.tdp.artsmia.model.ArtObject;



public class ArtsmiaDAO {
	Map <Integer,ArtObject> object=new HashMap<Integer,ArtObject>();
	Map <Integer,Exhibitions> exhibition=new HashMap<Integer,Exhibitions>();

	public List<ArtObject> listObject() {
		
		String sql = "SELECT * from objects";

		List<ArtObject> result = new ArrayList<>();

		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet res = st.executeQuery();

			while (res.next()) {
				
				ArtObject a=new ArtObject(res.getInt("object_id"), res.getString("classification"), res.getString("continent"), 
						res.getString("country"), res.getInt("curator_approved"), res.getString("dated"), res.getString("department"), 
						res.getString("medium"), res.getString("nationality"), res.getString("object_name"), res.getInt("restricted"), 
						res.getString("rights_type"), res.getString("role"), res.getString("room"), res.getString("style"), res.getString("title"));
				object.put(a.getObjectId(), a);
				result.add(a);
			}

			conn.close();
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
public void connectionsObjectExhibitions() {
		
		String sql = "SELECT * from exhibition_objects";

		//List<Exhibitions> result = new ArrayList<>();

		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet res = st.executeQuery();

			while (res.next()) {
			//	System.out.println(res.getInt("exhibition_id")+" "+res.getInt("object_id"));
				if(this.exhibition.containsKey(res.getInt("exhibition_id")))
					this.exhibition.get(res.getInt("exhibition_id")).addObject(this.object.get(res.getInt("object_id")));
				if(this.object.containsKey(res.getInt("object_id")))
					this.object.get(res.getInt("object_id")).addObject(this.exhibition.get(res.getInt("exhibition_id")));
				
			}

			conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	
		}
	}
public List<Exhibitions> listExibition() {
	
	String sql = "SELECT * from exhibitions";

	List<Exhibitions> result = new ArrayList<>();

	Connection conn = DBConnect.getConnection();

	try {
		PreparedStatement st = conn.prepareStatement(sql);

		ResultSet res = st.executeQuery();

		while (res.next()) {
			
			Exhibitions e=new Exhibitions(
					res.getInt("exhibition_id"), res.getString("exhibition_department"), res.getString("exhibition_title"), 
					res.getInt("begin"), res.getInt("end"));
			System.out.println(e);
			exhibition.put(res.getInt("exhibition_id"), e);
			result.add(e);
		}

		conn.close();
		return result;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return null;
	}
}
}
