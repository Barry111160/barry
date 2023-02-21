package com.musclebeach.absentMember.model;

import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AbsentMemberDao implements AbsentMemberIDao {

    public final static String CLASSNAME = "com.mysql.cj.jdbc.Driver";
    private static final String INSERT = "INSERT INTO absent_member (time_id, mem_id) VALUE (?, ?)";
    private static final String DELETE = "DELETE FROM absent_member WHERE time_id = ?and  mem_id = ?";
    private static final String GET = "SELECT * FROM absent_member WHERE time_id = ? and mem_id = ?";
    private static final String GETALL = "SELECT * FROM absent_member order by time_id";
    private static final String GET_ABSENT_COUNT = "SELECT count(a.class_id),a.class_id,b.mem_id from  class_schedule AS a JOIN absent_member AS b on a.time_id=b.time_id \r\n"
            + "where a.class_id=? and b.mem_id=?;";
    private static final String MEMBERSUSPENSION =
            "update  member set mem_access=2 where mem_id=?";
    @Resource
    private DataSource dataSource;

    @Override
    public void suspendMembership(Integer memID) {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {

            Class.forName(CLASSNAME);
            con = dataSource.getConnection();
            pstmt = con.prepareStatement(MEMBERSUSPENSION);
            pstmt.setInt(1, memID);
            pstmt.executeUpdate();

            // Handle any driver errors
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Couldn't load database driver. "
                    + e.getMessage());
            // Handle any SQL errors
        } catch (SQLException se) {
            throw new RuntimeException("A database error occured. "
                    + se.getMessage());
            // Clean up JDBC resources
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException se) {
                    se.printStackTrace(System.err);
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (Exception e) {
                    e.printStackTrace(System.err);
                }
            }
        }
    }

    @Override
    public void insert(AbsentMemberVO obj) {
        try {
            Class.forName(CLASSNAME);
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT)) {
            preparedStatement.setInt(1, obj.getTimeID());
            preparedStatement.setInt(2, obj.getMemID());
            int rowCount = preparedStatement.executeUpdate();
            System.out.println("新增 " + rowCount + " 筆資料");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(AbsentMemberVO obj) {
//        try {
//            Class.forName(CLASSNAME);
//        } catch (ClassNotFoundException e1) {
//            e1.printStackTrace();
//        }
//        try (Connection connection = dataSource.getConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);) {
//            preparedStatement.setInt(1, obj.getTimeID());
//            preparedStatement.setInt(2, obj.getMemID());
//            int rowCount = preparedStatement.executeUpdate();
//            System.out.println("修改 " + rowCount + " 筆資料");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    @Override
    public void delete(AbsentMemberVO obj) {
        try {
            Class.forName(CLASSNAME);
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
            preparedStatement.setInt(1, obj.getTimeID());
            preparedStatement.setInt(2, obj.getMemID());
            int rowCount = preparedStatement.executeUpdate();
            System.out.println("刪除 " + rowCount + " 筆資料");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public AbsentMemberVO get(AbsentMemberVO obj) {
        try {
            Class.forName(CLASSNAME);
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET)) {
            preparedStatement.setInt(1, obj.getTimeID());
            preparedStatement.setInt(2, obj.getMemID());
            ResultSet resultSet = preparedStatement.executeQuery();
            AbsentMemberVO absentMemberVO = new AbsentMemberVO();
            while (resultSet.next()) {
                absentMemberVO.setTimeID(resultSet.getInt(1));
                absentMemberVO.setMemID(resultSet.getInt(2));
            }
            return absentMemberVO;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
//	@Override
//	public List<AbsentMemberVO> getOneMemAbsentList(){
//		try {
//			Class.forName(CLASSNAME);
//		} catch (ClassNotFoundException e1) {
//			e1.printStackTrace();
//		}try (Connection connection = dataSource.getConnection();
//				PreparedStatement preparedStatement = connection.prepareStatement(GET_ABSENTCLASS_STRING);) {
//			ResultSet resultSet = preparedStatement.executeQuery();
//			List<AbsentMemberVO> list = new ArrayList<>();
//			while (resultSet.next()) {
//				AbsentMemberVO absentMemberVO = new AbsentMemberVO();
//				absentMemberVO.setTimeID(resultSet.getInt(1));
//				absentMemberVO.setMemID(resultSet.getInt(2));
//				list.add(absentMemberVO);;
//			}
//			return list;
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
//	

    @Override
    public List<AbsentMemberVO> getAll() {
        try {
            Class.forName(CLASSNAME);
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GETALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<AbsentMemberVO> list = new ArrayList<>();
            while (resultSet.next()) {
                AbsentMemberVO absentMemberVO = new AbsentMemberVO();
                absentMemberVO.setTimeID(resultSet.getInt(1));
                absentMemberVO.setMemID(resultSet.getInt(2));
                list.add(absentMemberVO);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public AbsentMemberVO getAbsentCount(Integer classID, Integer memID) {

        try {
            Class.forName(CLASSNAME);
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ABSENT_COUNT)) {
            preparedStatement.setInt(1, classID);
            preparedStatement.setInt(2, memID);
            ResultSet resultSet = preparedStatement.executeQuery();

            AbsentMemberVO absentMemberVO = new AbsentMemberVO();
            while (resultSet.next()) {
                absentMemberVO.setCount(resultSet.getInt(1));

            }
            return absentMemberVO;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
