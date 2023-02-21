package com.musclebeach.classSchedule.model;


import com.musclebeach.common.util.CompositeQuery.jdbcUtil_CompositeQuery_ClassSchedule;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class ClassScheduleDao implements ClassScheduleIDao {
    public final static String CLASSNAME = "com.mysql.cj.jdbc.Driver";
    private static final String INSERT = "INSERT INTO class_schedule (class_id, start_time, end_time) VALUE (?, ?, ?)";
    private static final String UPDATE = "UPDATE class_schedule SET class_id = ?, start_time = ?, end_time = ? WHERE time_id = ?";
    private static final String DELETE = "DELETE FROM class_schedule WHERE time_id = ?";
    private static final String GET = "SELECT time_id,class_id , start_time , end_time  FROM class_schedule WHERE time_id = ?";
    private static final String GETBYCLASSID = "SELECT time_id,class_id , start_time , end_time  FROM class_schedule WHERE class_id = ?";
    private static final String GETALL = "SELECT * FROM class_schedule order by time_id";
    private static final String GET_CLASS_COUNT_STRING = "SELECT count(class_id) from class_schedule where class_id=?";
    @Resource
    private DataSource dataSource;

    @Override
    public void insert(ClassScheduleVO obj) {
        try {
            Class.forName(CLASSNAME);
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT);) {
            preparedStatement.setInt(1, obj.getClassID());
            preparedStatement.setTimestamp(2, obj.getStartTime());
            preparedStatement.setTimestamp(3, obj.getEndTime());
            int rowCount = preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(ClassScheduleVO obj) {
        try {
            Class.forName(CLASSNAME);
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);) {
            preparedStatement.setInt(1, obj.getClassID());
            preparedStatement.setTimestamp(2, obj.getStartTime());
            preparedStatement.setTimestamp(3, obj.getEndTime());
            preparedStatement.setInt(4, obj.getTimeID());
            int rowCount = preparedStatement.executeUpdate();
            System.out.println("靽格 " + rowCount + " 蝑���");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Integer pk) {
        try {
            Class.forName(CLASSNAME);
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE);) {
            preparedStatement.setInt(1, pk);
            int rowCount = preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ClassScheduleVO getClassCount(Integer classID) {
        try {
            Class.forName(CLASSNAME);
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_CLASS_COUNT_STRING);) {
            preparedStatement.setInt(1, classID);
            ResultSet resultSet = preparedStatement.executeQuery();
            ClassScheduleVO classScheduleVO = new ClassScheduleVO();
            while (resultSet.next()) {
                classScheduleVO.setClassCount(resultSet.getInt(1));

            }
            return classScheduleVO;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ClassScheduleVO getClass(Integer classID) {
        try {
            Class.forName(CLASSNAME);
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GETBYCLASSID);) {
            preparedStatement.setInt(1, classID);
            ResultSet resultSet = preparedStatement.executeQuery();
            ClassScheduleVO classScheduleVO = new ClassScheduleVO();
            while (resultSet.next()) {
                classScheduleVO.setTimeID(resultSet.getInt(1));
                classScheduleVO.setClassID(resultSet.getInt(2));
                classScheduleVO.setStartTime(resultSet.getTimestamp(3));
                classScheduleVO.setEndTime(resultSet.getTimestamp(4));
            }
            return classScheduleVO;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ClassScheduleVO get(Integer pk) {
        try {
            Class.forName(CLASSNAME);
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET);) {
            preparedStatement.setInt(1, pk);
            ResultSet resultSet = preparedStatement.executeQuery();
            ClassScheduleVO classScheduleVO = new ClassScheduleVO();
            while (resultSet.next()) {
                classScheduleVO.setTimeID(resultSet.getInt(1));
                classScheduleVO.setClassID(resultSet.getInt(2));
                classScheduleVO.setStartTime(resultSet.getTimestamp(3));
                classScheduleVO.setEndTime(resultSet.getTimestamp(4));
            }
            return classScheduleVO;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


//	@Override
//	public List<ClassScheduleVO> getAll(Map<String, String[]> map) {
//		try {
//			Class.forName(CLASSNAME);
//		} catch (ClassNotFoundException e1) {
//			e1.printStackTrace();
//		}
//		try (Connection connection = dataSource.getConnection();
//				PreparedStatement preparedStatement = connection.prepareStatement(GETALL);) {
//			ResultSet resultSet = preparedStatement.executeQuery();
//			ClassScheduleVO classScheduleVO = new ClassScheduleVO();
//			List<ClassScheduleVO> list = new ArrayList<>();
//			while (resultSet.next()) {
//				classScheduleVO.setTimeID(resultSet.getInt(1));
//				classScheduleVO.setClassID(resultSet.getInt(2));
//				classScheduleVO.setStartTime(resultSet.getTimestamp(3));
//				classScheduleVO.setEndTime(resultSet.getTimestamp(4));
//				list.add(classScheduleVO);
//			}
//			return list;
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//	}

    @Override
    public List<ClassScheduleVO> getAll() {
        try {
            Class.forName(CLASSNAME);
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GETALL);) {
            ResultSet resultSet = preparedStatement.executeQuery();

            List<ClassScheduleVO> list = new ArrayList<>();
            while (resultSet.next()) {
                ClassScheduleVO classScheduleVO = new ClassScheduleVO();
                classScheduleVO.setTimeID(resultSet.getInt(1));
                classScheduleVO.setClassID(resultSet.getInt(2));
                classScheduleVO.setStartTime(resultSet.getTimestamp(3));
                classScheduleVO.setEndTime(resultSet.getTimestamp(4));
                list.add(classScheduleVO);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<ClassScheduleVO> getAll(Map<String, String[]> map) {
        final String finalSQL = "SELECT a.time_id,a.class_id,a.start_time,a.end_time,b.mem_id,b.order_status from class_schedule AS a JOIN class_order AS b on a.class_id = b.class_id" + jdbcUtil_CompositeQuery_ClassSchedule.get_WhereCondition(map) + " and b.order_status=1 order by a.time_id;";
        try {
            Class.forName(CLASSNAME);
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(finalSQL);) {
            System.out.println("●●finalSQL(by DAO) = " + finalSQL);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<ClassScheduleVO> list = new ArrayList<ClassScheduleVO>();
            while (resultSet.next()) {
                ClassScheduleVO ClassScheduleVO = new ClassScheduleVO();
                ClassScheduleVO.setTimeID(resultSet.getInt(1));
                ClassScheduleVO.setClassID(resultSet.getInt(2));
                ClassScheduleVO.setStartTime(resultSet.getTimestamp(3));
                ClassScheduleVO.setEndTime(resultSet.getTimestamp(4));
                ClassScheduleVO.setMemID(resultSet.getInt(5));

                list.add(ClassScheduleVO);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}