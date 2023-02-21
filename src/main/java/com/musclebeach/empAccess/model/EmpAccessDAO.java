package com.musclebeach.empAccess.model;

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
public class EmpAccessDAO implements EmpAccessDAO_interface {
    public static final String UPDATE = "UPDATE emp_access set fno_id = ? where emp_id = ? and fno_id = ?";
    private static final String INSERT_STMT = "INSERT INTO emp_access (emp_id, fno_id) VALUES (?, ?)";
    private static final String GET_ALL_STMT = "SELECT * FROM emp_access ";
    private static final String GET_ONE_Fno = "SELECT * FROM emp_access where fno_id = ?";
    private static final String GET_ONE_Emp = "SELECT * FROM emp_access where emp_id = ?";
    private static final String DELETE = "DELETE FROM emp_access where emp_id = ? and fno_id = ?";
    private static final String GET_ONE_STMT = "SELECT * FROM emp_access where emp_id = ? and fno_id=?";
    String driver = "com.mysql.cj.jdbc.Driver";
    @Resource
    private DataSource dataSource;

    @Override
    public void insert(EmpAccessVO empAccessVO) {

        Connection con = null;
        PreparedStatement pstmt = null;

        try {

            Class.forName(driver);
            con = dataSource.getConnection();
            pstmt = con.prepareStatement(INSERT_STMT);

            pstmt.setInt(1, empAccessVO.getEmpID());
            pstmt.setInt(2, empAccessVO.getfID());

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
    public void delete(Integer empID, Integer fID) {

        Connection con = null;
        PreparedStatement pstmt = null;

        try {

            Class.forName(driver);
            con = dataSource.getConnection();
            pstmt = con.prepareStatement(DELETE);

            pstmt.setInt(1, empID);
            pstmt.setInt(2, fID);

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
    public List<EmpAccessVO> getAll() {
        List<EmpAccessVO> list = new ArrayList<EmpAccessVO>();
        EmpAccessVO empAccessVO = null;

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            Class.forName(driver);
            con = dataSource.getConnection();
            pstmt = con.prepareStatement(GET_ALL_STMT);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                empAccessVO = new EmpAccessVO();
                empAccessVO.setEmpID(rs.getInt("emp_id"));
                empAccessVO.setfID(rs.getInt("fno_id"));
                list.add(empAccessVO);
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
    public List<EmpAccessVO> getFno(Integer fID) {
        List<EmpAccessVO> list = new ArrayList<EmpAccessVO>();
        EmpAccessVO empAccessVO = null;

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            Class.forName(driver);
            con = dataSource.getConnection();
            pstmt = con.prepareStatement(GET_ONE_Fno);
            pstmt.setInt(1, fID);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                empAccessVO = new EmpAccessVO();
                empAccessVO.setEmpID(rs.getInt("emp_id"));
                empAccessVO.setfID(rs.getInt("fno_id"));
                list.add(empAccessVO);
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
    public List<EmpAccessVO> getEmp(Integer empID) {
        List<EmpAccessVO> list = new ArrayList<EmpAccessVO>();
        EmpAccessVO empAccessVO = null;

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            Class.forName(driver);
            con = dataSource.getConnection();
            pstmt = con.prepareStatement(GET_ONE_Emp);
            pstmt.setInt(1, empID);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                empAccessVO = new EmpAccessVO();
                empAccessVO.setEmpID(rs.getInt("emp_id"));
                empAccessVO.setfID(rs.getInt("fno_id"));
                list.add(empAccessVO);
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
    public void update(EmpAccessVO empAccessVO, Integer fno_id) {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            Class.forName(driver);
            con = dataSource.getConnection();
            pstmt = con.prepareStatement(UPDATE);
            pstmt.setInt(1, fno_id);
            pstmt.setInt(2, empAccessVO.getEmpID());
            pstmt.setInt(3, empAccessVO.getfID());
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
    public EmpAccessVO findByPrimaryKey(Integer empID, Integer fID) {

        EmpAccessVO empAccessVO = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            Class.forName(driver);
            con = dataSource.getConnection();
            pstmt = con.prepareStatement(GET_ONE_STMT);

            pstmt.setInt(1, empID);
            pstmt.setInt(2, fID);

            rs = pstmt.executeQuery();

            while (rs.next()) {

                empAccessVO = new EmpAccessVO();
                empAccessVO.setEmpID(rs.getInt("emp_id"));
                empAccessVO.setfID(rs.getInt("fno_id"));

            }

            // Handle any driver errors
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
            // Handle any SQL errors
        } catch (SQLException se) {
            throw new RuntimeException("A database error occured. " + se.getMessage());
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
        return empAccessVO;
    }
}
