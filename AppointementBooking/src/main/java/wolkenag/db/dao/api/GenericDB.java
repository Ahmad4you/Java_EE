package wolkenag.db.dao.api;

import java.sql.SQLException;
import java.util.List;

public interface GenericDB<Typ> {

	int saveItem(Typ item) throws SQLException;

	List<Typ> findAllItems() throws SQLException;

	Typ findById(int id) throws SQLException;

	int updateItem(Typ item) throws SQLException;

	int deleteById(int id) throws SQLException;
}
