package serviceImpl;

import java.sql.SQLException;
import java.util.List;

import DAOImpl.DAORackImpl;
import entity.Rack;
import service.RackServise;

public class RackServiceImpl implements RackServise {

	
	public int findfreeRac() throws SQLException{

		DAORackImpl DAOrack = new DAORackImpl();
		for(Rack rack : DAOrack.getAll()){
			if((rack.getCapacity()-DAOrack.bookBelongRack(rack.getId())) > 0){
				return rack.getId();
			}
		}

		
		return 7;
		
	}

}
