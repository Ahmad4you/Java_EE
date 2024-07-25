/**
 * 
 */
package com.home.connpolling;


import java.util.List;

import jakarta.ejb.Stateless;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

/**
 * 
 * @author Ahmad Alrefai
 */
@Stateless
public class SysConfigBean {

	/*
	 * EntityManager verwendet intern den Connection Pool
	 * wird automatisch eine Verbindung aus dem Pool geholt und der Transaktion zugeordnet.
	 * 
	 */
	@PersistenceContext
	private EntityManager em;

//	public String getSysConfig() throws SQLException, NamingException, Exception {
//		String sql = "SELECT variable, value FROM sys_config";
//
//		try (Connection conn = ConnectionPool.getConnection();
//				PreparedStatement ps = conn.prepareStatement(sql);
//				ResultSet rs = ps.executeQuery();
//				Jsonb jsonb = JsonbBuilder.create()) {
//
//			List<SysConfig> list = new ArrayList<>();
//			while (rs.next()) {
//				list.add(new SysConfig(rs.getString("variable"), rs.getString("value")));
//			}
//
//			String json = jsonb.toJson(list);
////            rs.close();
////            conn.close();
//			return json;
//		}
//	}
	
	public String getSysConfig() {
        List<SysConfig> list = em.createQuery("SELECT s FROM SysConfig s", SysConfig.class)
                                 .getResultList();

        try (Jsonb jsonb = JsonbBuilder.create()) {
            return jsonb.toJson(list);
        } catch (Exception e) {
            throw new RuntimeException("Error converting to JSON", e);
        }
    }
}
