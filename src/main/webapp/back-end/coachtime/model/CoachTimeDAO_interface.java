package com.coachtime.model;

import java.sql.Date;
import java.util.*;

public interface CoachTimeDAO_interface {
	public void insert(CoachTimeVO coachTimeVO);

	public void update(CoachTimeVO coachTimeVO);

	public void delete(Integer timeID);

	public CoachTimeVO findByPrimaryKey(Integer timeID);
	
	public List<CoachTimeVO> getAll();
	
	public CoachTimeVO getCoachTime(Integer empid);
	
	public CoachTimeVO getCoachDate(Integer empid);

	public List<CoachTimeVO> getAllCoachDate(Integer empid);
	
	public CoachTimeVO getCoachTimeByCoachDate(Integer empid,Date coachdate);
	
	public List<CoachTimeVO> getAllByEmpID(Integer empid);
}
