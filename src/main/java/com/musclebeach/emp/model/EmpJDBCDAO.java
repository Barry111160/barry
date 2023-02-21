package com.musclebeach.emp.model;

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
public class EmpJDBCDAO implements EmpDAO_interface {
    private static final String INSERT_STMT = "INSERT INTO employee (emp_password, emp_name, emp_hiredate, emp_birthday, emp_phone, emp_email, emp_status, emp_coach_price, emp_img, emp_coach_content) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String GET_ALL_STMT = "SELECT * FROM employee ";
    private static final String GET_ONE_STMT = "SELECT * FROM employee where emp_id = ?";
    private static final String DELETE = "DELETE FROM employee where emp_id = ?";
    private static final String UPDATE = "UPDATE employee set emp_password=?, emp_name=?, emp_hiredate=?, emp_birthday=?, emp_phone=?, emp_email=?, emp_status=?, emp_coach_price=?, emp_img=?, emp_coach_content=? where emp_id = ?";
    private static final String UPDATE_PASSWORD = "UPDATE employee set emp_password=? where emp_id = ?";
    private static final String UPDATE_STATUS = "UPDATE employee set emp_status=? where emp_id = ?";
    private static final String GET_ONE_Name = "SELECT * FROM employee where emp_name = ?";
    private static final String GET_ALL_FUNCTION_EMP_NO_STMT = "SELECT * " + "FROM employee e " + "join emp_access ec "
            + "on e.emp_id = ec.emp_id " + "join emp_access_function eaf " + "on ec.fno_id = eaf.fno_id "
            + "where eaf.fno_id = ?";
    private static final String GET_Name_By_EmpID = "SELECT  emp_name FROM employee where emp_id = ?";
    private static final String GET_COACHLIST = "SELECT *FROM employee where emp_coach_price > 0";
    String driver = "com.mysql.cj.jdbc.Driver";
    @Resource
    private DataSource dataSource;

    @Override
    public List<EmpVO> findByName(String empName) {
        List<EmpVO> list = new ArrayList<EmpVO>();
        EmpVO empVO = null;

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            Class.forName(driver);
            con = dataSource.getConnection();
            pstmt = con.prepareStatement(GET_ONE_Name);

            pstmt.setString(1, empName);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                empVO = new EmpVO();
                empVO.setEmpID(rs.getInt("emp_id"));
                empVO.setPassword(rs.getString("emp_password"));
                empVO.setEmpName(rs.getString("emp_name"));
                empVO.setHiredate(rs.getDate("emp_hiredate"));
                empVO.setEmpBirthday(rs.getDate("emp_birthday"));
                empVO.setEmpPhone(rs.getString("emp_phone"));
                empVO.setEmpMail(rs.getString("emp_email"));
                empVO.setEmpStatus(rs.getInt("emp_status"));
                empVO.setCoachPrice(rs.getInt("emp_coach_price"));
                empVO.setEmpImg(rs.getBytes("emp_img"));
                empVO.setCoachContent(rs.getString("emp_coach_content"));
                list.add(empVO);
            }

        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Couldn't load database driver. " + e.getMessage());

        } catch (SQLException se) {
            throw new RuntimeException("A database error occured. " + se.getMessage());

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
    public void updateStatus(Integer empID, Integer status) {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {

            Class.forName(driver);
            con = dataSource.getConnection();
            pstmt = con.prepareStatement(UPDATE_STATUS);

            pstmt.setInt(1, status);
            pstmt.setInt(2, empID);

            pstmt.executeUpdate();

        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Couldn't load database driver. " + e.getMessage());

        } catch (SQLException se) {
            throw new RuntimeException("A database error occured. " + se.getMessage());

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
    public void updatePassword(Integer empID, String password) {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {

            Class.forName(driver);
            con = dataSource.getConnection();
            pstmt = con.prepareStatement(UPDATE_PASSWORD);

            pstmt.setString(1, password);
            pstmt.setInt(2, empID);

            pstmt.executeUpdate();

        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Couldn't load database driver. " + e.getMessage());

        } catch (SQLException se) {
            throw new RuntimeException("A database error occured. " + se.getMessage());

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
    public void insert(EmpVO empVO) {

        Connection con = null;
        PreparedStatement pstmt = null;

        try {

            Class.forName(driver);
            con = dataSource.getConnection();
            pstmt = con.prepareStatement(INSERT_STMT);

            pstmt.setString(1, empVO.getPassword());
            pstmt.setString(2, empVO.getEmpName());
            pstmt.setDate(3, empVO.getHiredate());
            pstmt.setDate(4, empVO.getEmpBirthday());
            pstmt.setString(5, empVO.getEmpPhone());
            pstmt.setString(6, empVO.getEmpMail());
            pstmt.setInt(7, empVO.getEmpStatus());
            pstmt.setInt(8, empVO.getCoachPrice());
            pstmt.setBytes(9, empVO.getEmpImg());
            pstmt.setString(10, empVO.getCoachContent());

            pstmt.executeUpdate();

        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Couldn't load database driver. " + e.getMessage());

        } catch (SQLException se) {
            throw new RuntimeException("A database error occured. " + se.getMessage());

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
    public void update(EmpVO empVO) {

        Connection con = null;
        PreparedStatement pstmt = null;

        try {

            Class.forName(driver);
            con = dataSource.getConnection();
            pstmt = con.prepareStatement(UPDATE);

            pstmt.setString(1, empVO.getPassword());
            pstmt.setString(2, empVO.getEmpName());
            pstmt.setDate(3, empVO.getHiredate());
            pstmt.setDate(4, empVO.getEmpBirthday());
            pstmt.setString(5, empVO.getEmpPhone());
            pstmt.setString(6, empVO.getEmpMail());
            pstmt.setInt(7, empVO.getEmpStatus());
            pstmt.setInt(8, empVO.getCoachPrice());
            pstmt.setBytes(9, empVO.getEmpImg());
            pstmt.setString(10, empVO.getCoachContent());
            pstmt.setInt(11, empVO.getEmpID());

            pstmt.executeUpdate();

            // Handle any driver errors
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
            // Handle any SQL errors
        } catch (SQLException se) {
            throw new RuntimeException("A database error occured. " + se.getMessage());
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
    public void delete(Integer empID) {

        Connection con = null;
        PreparedStatement pstmt = null;

        try {

            Class.forName(driver);
            con = dataSource.getConnection();
            pstmt = con.prepareStatement(DELETE);

            pstmt.setInt(1, empID);

            pstmt.executeUpdate();

        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Couldn't load database driver. " + e.getMessage());

        } catch (SQLException se) {
            throw new RuntimeException("A database error occured. " + se.getMessage());

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
    public EmpVO findByPrimaryKey(Integer empID) {

        EmpVO empVO = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            Class.forName(driver);
            con = dataSource.getConnection();
            pstmt = con.prepareStatement(GET_ONE_STMT);

            pstmt.setInt(1, empID);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                empVO = new EmpVO();
                empVO.setEmpID(rs.getInt("emp_id"));
                empVO.setPassword(rs.getString("emp_password"));
                empVO.setEmpName(rs.getString("emp_name"));
                empVO.setHiredate(rs.getDate("emp_hiredate"));
                empVO.setEmpBirthday(rs.getDate("emp_birthday"));
                empVO.setEmpPhone(rs.getString("emp_phone"));
                empVO.setEmpMail(rs.getString("emp_email"));
                empVO.setEmpStatus(rs.getInt("emp_status"));
                empVO.setCoachPrice(rs.getInt("emp_coach_price"));
                empVO.setEmpImg(rs.getBytes("emp_img"));
                empVO.setCoachContent(rs.getString("emp_coach_content"));
            }

        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Couldn't load database driver. " + e.getMessage());

        } catch (SQLException se) {
            throw new RuntimeException("A database error occured. " + se.getMessage());

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
        return empVO;
    }

    @Override
    public List<EmpVO> getAll() {
        List<EmpVO> list = new ArrayList<EmpVO>();
        EmpVO empVO = null;

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            Class.forName(driver);
            con = dataSource.getConnection();
            pstmt = con.prepareStatement(GET_ALL_STMT);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                empVO = new EmpVO();
                empVO.setEmpID(rs.getInt("emp_id"));
                empVO.setPassword(rs.getString("emp_password"));
                empVO.setEmpName(rs.getString("emp_name"));
                empVO.setHiredate(rs.getDate("emp_hiredate"));
                empVO.setEmpBirthday(rs.getDate("emp_birthday"));
                empVO.setEmpPhone(rs.getString("emp_phone"));
                empVO.setEmpMail(rs.getString("emp_email"));
                empVO.setEmpStatus(rs.getInt("emp_status"));
                empVO.setCoachPrice(rs.getInt("emp_coach_price"));
                empVO.setEmpImg(rs.getBytes("emp_img"));
                empVO.setCoachContent(rs.getString("emp_coach_content"));
                list.add(empVO);
            }

        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Couldn't load database driver. " + e.getMessage());

        } catch (SQLException se) {
            throw new RuntimeException("A database error occured. " + se.getMessage());

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
    public List<EmpVO> getFunctionEmp(Integer fID) {
        List<EmpVO> list = new ArrayList<EmpVO>();
        EmpVO empVO = null;

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            Class.forName(driver);
            con = dataSource.getConnection();
            pstmt = con.prepareStatement(GET_ALL_FUNCTION_EMP_NO_STMT);
            pstmt.setInt(1, fID);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                empVO = new EmpVO();
                empVO.setEmpID(rs.getInt("emp_id"));
                empVO.setPassword(rs.getString("emp_password"));
                empVO.setEmpName(rs.getString("emp_name"));
                empVO.setHiredate(rs.getDate("emp_hiredate"));
                empVO.setEmpBirthday(rs.getDate("emp_birthday"));
                empVO.setEmpPhone(rs.getString("emp_phone"));
                empVO.setEmpMail(rs.getString("emp_email"));
                empVO.setEmpStatus(rs.getInt("emp_status"));
                empVO.setCoachPrice(rs.getInt("emp_coach_price"));
                empVO.setEmpImg(rs.getBytes("emp_img"));
                empVO.setCoachContent(rs.getString("emp_coach_content"));
                list.add(empVO);
            }
            // Handle any driver errors
        } catch (SQLException se) {
            throw new RuntimeException("A database error occured. " + se.getMessage());
            // Clean up JDBC resources
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
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
    public EmpVO getNameByEmpid(Integer empID) {

        EmpVO empVO = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            Class.forName(driver);
            con = dataSource.getConnection();
            pstmt = con.prepareStatement(GET_Name_By_EmpID);

            pstmt.setInt(1, empID);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                empVO = new EmpVO();
                empVO.setEmpName(rs.getString("emp_name"));

            }

        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Couldn't load database driver. " + e.getMessage());

        } catch (SQLException se) {
            throw new RuntimeException("A database error occured. " + se.getMessage());

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
        return empVO;
    }

    @Override
    public List<EmpVO> getCoachList() {
        List<EmpVO> list = new ArrayList<EmpVO>();
        EmpVO empVO = null;

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            Class.forName(driver);
            con = dataSource.getConnection();
            pstmt = con.prepareStatement(GET_COACHLIST);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                empVO = new EmpVO();
                empVO.setEmpID(rs.getInt("emp_id"));
                empVO.setPassword(rs.getString("emp_password"));
                empVO.setEmpName(rs.getString("emp_name"));
                empVO.setHiredate(rs.getDate("emp_hiredate"));
                empVO.setEmpBirthday(rs.getDate("emp_birthday"));
                empVO.setEmpPhone(rs.getString("emp_phone"));
                empVO.setEmpMail(rs.getString("emp_email"));
                empVO.setEmpStatus(rs.getInt("emp_status"));
                empVO.setCoachPrice(rs.getInt("emp_coach_price"));
                empVO.setEmpImg(rs.getBytes("emp_img"));
                empVO.setCoachContent(rs.getString("emp_coach_content"));
                list.add(empVO);
            }

        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Couldn't load database driver. " + e.getMessage());

        } catch (SQLException se) {
            throw new RuntimeException("A database error occured. " + se.getMessage());

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
}
