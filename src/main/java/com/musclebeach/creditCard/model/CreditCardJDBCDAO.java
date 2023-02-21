package com.musclebeach.creditCard.model;

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
public class CreditCardJDBCDAO implements CreditCardDAO_interface {
    private static final String INSERT_STMT =
            "INSERT INTO credit_card ( mem_id, cc_number, cc_name, cc_time, cc_vc) VALUES (?, ?, ?, ?, ?)";
    private static final String GET_ALL_STMT =
            "SELECT cc_id, mem_id, cc_number, cc_name, cc_time, cc_vc FROM credit_card order by cc_id";
    private static final String GET_ONE_STMT =
            "SELECT cc_id, mem_id, cc_number, cc_name, cc_time, cc_vc FROM credit_card where cc_id = ?";
    private static final String DELETE =
            "DELETE FROM credit_card where cc_id = ?";
    private static final String UPDATE =
            "UPDATE credit_card set mem_id=?, cc_number=?, cc_name=?, cc_time=?, cc_vc=? where cc_id = ?";
    String driver = "com.mysql.cj.jdbc.Driver";
    @Resource
    private DataSource dataSource;

    @Override
    public void insert(CreditCardVO creditCardVO) {

        Connection con = null;
        PreparedStatement pstmt = null;

        try {

            Class.forName(driver);
            con = dataSource.getConnection();
            pstmt = con.prepareStatement(INSERT_STMT);


            pstmt.setInt(1, creditCardVO.getMemID());
            pstmt.setString(2, creditCardVO.getCcNumber());
            pstmt.setString(3, creditCardVO.getCcName());
            pstmt.setString(4, creditCardVO.getCcTime());
            pstmt.setString(5, creditCardVO.getCcvc());

            pstmt.executeUpdate();


        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Couldn't load database driver. "
                    + e.getMessage());

        } catch (SQLException se) {
            throw new RuntimeException("A database error occured. "
                    + se.getMessage());

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
    public void update(CreditCardVO creditCardVO) {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {

            Class.forName(driver);
            con = dataSource.getConnection();
            pstmt = con.prepareStatement(UPDATE);


            pstmt.setInt(1, creditCardVO.getMemID());
            pstmt.setString(2, creditCardVO.getCcNumber());
            pstmt.setString(3, creditCardVO.getCcName());
            pstmt.setString(4, creditCardVO.getCcTime());
            pstmt.setString(5, creditCardVO.getCcvc());
            pstmt.setInt(6, creditCardVO.getCcID());

            pstmt.executeUpdate();


        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Couldn't load database driver. "
                    + e.getMessage());

        } catch (SQLException se) {
            throw new RuntimeException("A database error occured. "
                    + se.getMessage());

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
    public void delete(Integer ccID) {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {

            Class.forName(driver);
            con = dataSource.getConnection();
            pstmt = con.prepareStatement(DELETE);

            pstmt.setInt(1, ccID);

            pstmt.executeUpdate();

        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Couldn't load database driver. "
                    + e.getMessage());

        } catch (SQLException se) {
            throw new RuntimeException("A database error occured. "
                    + se.getMessage());

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
    public CreditCardVO findByPrimaryKey(Integer ccID) {

        CreditCardVO creditCardVO = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            Class.forName(driver);
            con = dataSource.getConnection();
            pstmt = con.prepareStatement(GET_ONE_STMT);

            pstmt.setInt(1, ccID);

            rs = pstmt.executeQuery();

            while (rs.next()) {

                creditCardVO = new CreditCardVO();
                creditCardVO.setCcID(rs.getInt("cc_id"));
                creditCardVO.setMemID(rs.getInt("mem_id"));
                creditCardVO.setCcNumber(rs.getString("cc_number"));
                creditCardVO.setCcName(rs.getString("cc_name"));
                creditCardVO.setCcTime(rs.getString("cc_time"));
                creditCardVO.setCcvc(rs.getString("cc_vc"));

            }


        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Couldn't load database driver. "
                    + e.getMessage());

        } catch (SQLException se) {
            throw new RuntimeException("A database error occured. "
                    + se.getMessage());

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
        return creditCardVO;
    }

    @Override
    public List<CreditCardVO> getAll() {
        List<CreditCardVO> list = new ArrayList<CreditCardVO>();
        CreditCardVO creditCardVO = null;

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            Class.forName(driver);
            con = dataSource.getConnection();
            pstmt = con.prepareStatement(GET_ALL_STMT);
            rs = pstmt.executeQuery();

            while (rs.next()) {

                creditCardVO = new CreditCardVO();
                creditCardVO.setCcID(rs.getInt("cc_id"));
                creditCardVO.setMemID(rs.getInt("mem_id"));
                creditCardVO.setCcNumber(rs.getString("cc_number"));
                creditCardVO.setCcName(rs.getString("cc_name"));
                creditCardVO.setCcTime(rs.getString("cc_time"));
                creditCardVO.setCcvc(rs.getString("cc_vc"));
                list.add(creditCardVO);
            }


        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Couldn't load database driver. "
                    + e.getMessage());

        } catch (SQLException se) {
            throw new RuntimeException("A database error occured. "
                    + se.getMessage());

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


