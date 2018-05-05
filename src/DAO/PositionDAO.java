package DAO;

import java.sql.SQLException;

import entity.Person;

public interface PositionDAO {
	public int getNubmerOfPosition(String namePosition) throws SQLException;
}
