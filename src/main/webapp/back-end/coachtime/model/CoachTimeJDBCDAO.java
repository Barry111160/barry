package com.coachtime.model;

import java.util.*;
import java.sql.*;
import java.sql.Date;

public class CoachTimeJDBCDAO implements CoachTimeDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/gym?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "1127";

	private static final String INSERT_STMT = 
		"INSERT INTO coach_time (emp_id,coach_time,coach_date) VALUES (?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT time_id,emp_id,coach_time,coach_date FROM coach_time order by time_id";
	private static final String GET_ALL_STMTbyEmpid = 
			"SELECT time_id,emp_id,coach_time,coach_date FROM coach_time where emp_id = ?";
	private static final String GET_ONE_STMT = 
		"SELECT time_id,emp_id,coach_time,coach_date FROM coach_time where time_id = ?";
	private static final String GET_CoachTime_STMT = 
		"SELECT coach_time FROM coach_time where emp_id = ?";
	private static final String GET_CoachDate_STMT = 
		"SELECT coach_date FROM coach_time where emp_id = ?";
	private static final String DELETE = 
		"DELETE FROM coach_time where time_id = ?";
	private static final String UPDATE = 
		"UPDATE coach_time set emp_id=?, coach_time=?, coach_date=? where time_id = ?";
	private static final String GET_COACHTIME_BY_COACHDATE = 
			"SELECT coach_time FROM coach_time where (emp_id =? && coach_date= ?)";
	@Override
	public void insert(CoachTimeVO coachTimeVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, coachTimeVO.getEmpID());
			pstmt.setString(2, coachTimeVO.getCoachTime());
			pstmt.setDate(3, coachTimeVO.getCoachDate());
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. insert "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. insert "
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
	public void update(CoachTimeVO coachTimeVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);


			pstmt.setInt(1, coachTimeVO.getEmpID());
			pstmt.setString(2, coachTimeVO.getCoachTime());
			pstmt.setDate(3, coachTimeVO.getCoachDate());
			pstmt.setInt(4, coachTimeVO.getTimeID());
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. update"
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. update"
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
	public void delete(Integer timeID) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, timeID);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. delete "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. ~delete "
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
	public CoachTimeVO findByPrimaryKey(Integer timeID) {

		CoachTimeVO coachTimeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, timeID);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// 也稱為 Domain objects
				coachTimeVO = new CoachTimeVO();
				coachTimeVO.setTimeID(rs.getInt("time_id"));
				coachTimeVO.setEmpID(rs.getInt("emp_id"));
				coachTimeVO.setCoachTime(rs.getString("coach_Time"));
				coachTimeVO.setCoachDate(rs.getDate("coach_Date"));
				
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. getpri "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. getpri "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
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
		return coachTimeVO;
	}

	@Override
	public CoachTimeVO getCoachTime(Integer empID) {

		CoachTimeVO coachTimeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_CoachTime_STMT);

			pstmt.setInt(1, empID);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// 也稱為 Domain objects
				coachTimeVO = new CoachTimeVO();
				coachTimeVO.setCoachTime(rs.getString("coach_Time"));

				
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. getpri "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. getpri "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
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
		return coachTimeVO;
	}
	
	
	@Override
	public CoachTimeVO getCoachDate(Integer empID) {

		CoachTimeVO coachTimeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_CoachDate_STMT);

			pstmt.setInt(1, empID);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// 也稱為 Domain objects
				coachTimeVO = new CoachTimeVO();
				coachTimeVO.setCoachDate(rs.getDate("coach_Date"));

				
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. getpri "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. getpri "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
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
		return coachTimeVO;
	}
	
	
	@Override
	public CoachTimeVO getCoachTimeByCoachDate(Integer empID,Date coachDate) {

		CoachTimeVO coachTimeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_COACHTIME_BY_COACHDATE);

			pstmt.setInt(1, empID);
			pstmt.setDate(2, coachDate);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// 也稱為 Domain objects
				coachTimeVO = new CoachTimeVO();
				coachTimeVO.setCoachTime(rs.getString("coach_Time"));

			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. getpri "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. getpri "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
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
		return coachTimeVO;
	}
	
	
	
	@Override
	public List<CoachTimeVO> getAllCoachDate(Integer empID) {
		List<CoachTimeVO> list = new ArrayList<CoachTimeVO>();
		CoachTimeVO coachTimeVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_CoachDate_STMT);
			pstmt.setInt(1, empID);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				//  也稱為 Domain objects
				coachTimeVO = new CoachTimeVO();
				coachTimeVO.setCoachDate(rs.getDate("coach_Date"));
				
				list.add(coachTimeVO); // Store the row in the list
			}

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
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
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
		return list;
	}
	
	
	
	@Override
	public List<CoachTimeVO> getAllByEmpID(Integer empID) {
		List<CoachTimeVO> list = new ArrayList<CoachTimeVO>();
		CoachTimeVO coachTimeVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMTbyEmpid);
			pstmt.setInt(1, empID);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				//  也稱為 Domain objects
				coachTimeVO = new CoachTimeVO();
				coachTimeVO.setTimeID(rs.getInt("time_id"));
				coachTimeVO.setEmpID(rs.getInt("emp_id"));
				coachTimeVO.setCoachTime(rs.getString("coach_Time"));
				coachTimeVO.setCoachDate(rs.getDate("coach_Date"));
				list.add(coachTimeVO); // Store the row in the list
			}

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
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
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
		return list;
	}
	
	
	@Override
	public List<CoachTimeVO> getAll() {
		List<CoachTimeVO> list = new ArrayList<CoachTimeVO>();
		CoachTimeVO coachTimeVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				//  也稱為 Domain objects
				coachTimeVO = new CoachTimeVO();
				coachTimeVO.setTimeID(rs.getInt("time_id"));
				coachTimeVO.setEmpID(rs.getInt("emp_id"));
				coachTimeVO.setCoachTime(rs.getString("coach_Time"));
				coachTimeVO.setCoachDate(rs.getDate("coach_Date"));
				
				list.add(coachTimeVO); // Store the row in the list
			}

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
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
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
		return list;
	}

	
	
	
	public static void main(String[] args) {

		CoachTimeJDBCDAO dao = new CoachTimeJDBCDAO();

//		 新增
//		CoachTimeVO coachTimeVO1 = new CoachTimeVO();
//		coachTimeVO1.setTimeID(1);
//		coachTimeVO1.setEmpID(5);
//		coachTimeVO1.setCoachTime("測試JDBC");
//		coachTimeVO1.setCoachDate(java.sql.Date.valueOf("1987-08-07"));
//		dao.insert(coachTimeVO1);

		// 更新
//		CoachTimeVO coachTimeVO2 = new CoachTimeVO();
//		coachTimeVO2.setTimeID(7);
//		coachTimeVO2.setEmpID(3);
//		coachTimeVO2.setCoachTime("測試JDBCTEST");
//		coachTimeVO2.setCoachDate(java.sql.Timestamp.valueOf("1986-07-05"));
//		dao.update(coachTimeVO2);

		// 刪除
//		dao.delete(none);

		// 查詢
//		CoachTimeVO coachTimeVO3 = dao.findByPrimaryKey(3);
//		System.out.print(coachTimeVO3.getTimeID() + ",");
//		System.out.print(coachTimeVO3.getEmpID() + ",");
//		System.out.print(coachTimeVO3.getCoachTime() + ",");
//		System.out.print(coachTimeVO3.getCoachDate() + ",");
//		System.out.println("---------------------");

//		CoachTimeVO coachTimeVO3 = dao.getCoachDate(6);
//		System.out.print(coachTimeVO3.getCoachDate() + ",");
//		System.out.println("---------------------");
		
//		CoachTimeVO coachTimeVO3 = dao.getCoachTimeByCoachDate(6,java.sql.Date.valueOf("2023-02-06"));
//		System.out.print(coachTimeVO3.getCoachTime());
//		System.out.println("---------------------");
		

		// 查詢
//		List<CoachTimeVO> list = dao.getAll();
//		for (CoachTimeVO cTime : list) {
//			System.out.print(cTime.getTimeID() + ",");
//			System.out.print(cTime.getEmpID() + ",");
//			System.out.print(cTime.getCoachTime() + ",");
//			System.out.print(cTime.getCoachDate() + ",");
//			System.out.println();
//		}
		
		
//		List<CoachTimeVO> list = dao.testtt(6);
//
//		for (CoachTimeVO cTime : list) {
//			System.out.print(cTime.getCoachDate() + ",");
//			System.out.println();
//		}
	}
}