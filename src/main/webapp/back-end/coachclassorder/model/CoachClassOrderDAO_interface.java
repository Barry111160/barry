package com.coachclassorder.model;

import java.sql.Date;
import java.util.*;

import com.coachtime.model.CoachTimeVO;

public interface CoachClassOrderDAO_interface {
	public void insert(CoachClassOrderVO coachClassOrderVO);

	public void update(CoachClassOrderVO coachClassOrderVO);

	public void delete(Integer orderID);

	public CoachClassOrderVO findByPrimaryKey(Integer orderID);
	
	public List<CoachClassOrderVO> getAll();
	
	public List<CoachClassOrderVO> getAllCoachPeriodByEmpAndClassTime(Integer empid,Date coachdate);
	
	public List<CoachClassOrderVO> getAllbyMem(Integer memid);
}
