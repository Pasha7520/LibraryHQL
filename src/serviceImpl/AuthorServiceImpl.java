package serviceImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import service.AuthorService;
import DAOImpl.DAOAuthorImpl;
import entity.Author;


public class AuthorServiceImpl implements AuthorService {

	@Override
	public List<Author> createAuthor(String authorName) throws SQLException {
		ArrayList<Author> list = new ArrayList();
		String [] authors = authorName.split(",");
		
		for(String s : authors){
			Author author = new Author();
			author.setAuthorName(s);
			list.add(author);
		}
		return list;
	}

	@Override
	public void checkingOrWritingNewAuthor(List<Author> list) throws SQLException {
		DAOAuthorImpl daoAuthorImpl = new DAOAuthorImpl();
		List<Author> authorlist = daoAuthorImpl.getAll();
		boolean into = true;
		for(Author a : list){
			into=true;
			for(Author a1:authorlist){
				if(a.getAuthorName().equals(a1.getAuthorName())){
					into = false;
				}
			}
			if(into){
				daoAuthorImpl.add(a);
			}
		}
		
	}

	@Override
	public List<Author> findAuthorsId(List<Author> list) throws SQLException {
		DAOAuthorImpl daoAuthorImpl = new DAOAuthorImpl();
		List<Author> authorlist = daoAuthorImpl.getAll();
		for(Author a : list){
			for(Author a1:authorlist){
				if(a.getAuthorName().equals(a1.getAuthorName())){
					a.setId(a1.getId());
				}
			}
			
		}
		
		return list;
	}

	@Override
	public void checkingOrDeleteAuthorNonBook() throws SQLException {
		DAOAuthorImpl daoAuthorImpl = new DAOAuthorImpl();
		List<Author> authorlist = daoAuthorImpl.getAll();
			for(Author a1:authorlist){
				if(a1.getListBook().isEmpty()){
					daoAuthorImpl.delete(a1);	
					}
			}
			
		
		
		
		
	}
	
	
}
