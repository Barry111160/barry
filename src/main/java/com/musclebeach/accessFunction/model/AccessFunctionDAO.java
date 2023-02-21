package com.musclebeach.accessFunction.model;

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
public class AccessFunctionDAO implements AccessFunctionDAO_interface {
    private static final String INSERT_STMT = "INSERT INTO emp_access_function (fno_name) VALUES (?)";
    private static final String GET_ALL_STMT = "SELECT * FROM emp_access_function ";
    private static final String GET_ONE_STMT = "SELECT * FROM emp_access_function where fno_id = ?";
    private static final String DELETE = "DELETE FROM emp_access_function where fno_id = ?";
    String driver = "com.mysql.cj.jdbc.Driver";
    @Resource
    private DataSource dataSource;

    @Override
    public void insert(AccessFunctionVO accessFunctionVO) {

        Connection con = null;
        PreparedStatement pstmt = null;

        try {

            Class.forName(driver);
            con = dataSource.getConnection();
            pstmt = con.prepareStatement(INSERT_STMT);

            pstmt.setString(1, accessFunctionVO.getfName());

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
    public void delete(Integer fID) {

        Connection con = null;
        PreparedStatement pstmt = null;

        try {

            Class.forName(driver);
            con = dataSource.getConnection();
            pstmt = con.prepareStatement(DELETE);

            pstmt.setInt(1, fID);

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
    public List<AccessFunctionVO> getAll() {
        List<AccessFunctionVO> list = new ArrayList<AccessFunctionVO>();
        AccessFunctionVO accessFunctionVO = null;

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            Class.forName(driver);
            con = dataSource.getConnection();
            pstmt = con.prepareStatement(GET_ALL_STMT);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                accessFunctionVO = new AccessFunctionVO();
                accessFunctionVO.setfID(rs.getInt("fno_id"));
                accessFunctionVO.setfName(rs.getString("fno_name"));
                list.add(accessFunctionVO);
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
    public AccessFunctionVO findByPrimaryKey(Integer fID) {
        AccessFunctionVO accessFunctionVO = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            Class.forName(driver);
            con = dataSource.getConnection();
            pstmt = con.prepareStatement(GET_ONE_STMT);
            pstmt.setInt(1, fID);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                accessFunctionVO = new AccessFunctionVO();
                accessFunctionVO.setfID(rs.getInt("fno_id"));
                accessFunctionVO.setfName(rs.getString("fno_name"));
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
        return accessFunctionVO;
    }
}

