package serviceImpl;

import service.PositionService;

public class PositionServiceImpl implements PositionService{

	@Override
	public boolean checkNumberOfPosition(int positionNumber) {
		if(positionNumber > 0 && positionNumber < 5){
			return true;

		}else {
			System.out.println("Sach a position doesn't exist!!");
			
			return false;
		}
		
	}

}
