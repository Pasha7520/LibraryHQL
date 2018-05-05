package service;

import java.sql.SQLException;
import java.util.List;

import entity.Author;

public interface AuthorService {
	public List<Author> findAuthorsId(List<Author> list) throws SQLException;
	public List<Author> createAuthor(String authorName) throws SQLException;
	public void checkingOrWritingNewAuthor(List<Author> list) throws SQLException;
	public void checkingOrDeleteAuthorNonBook() throws SQLException;
}
