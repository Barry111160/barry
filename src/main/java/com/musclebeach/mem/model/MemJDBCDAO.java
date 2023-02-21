package com.musclebeach.mem.model;

import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MemJDBCDAO implements MemDAO_interface {
    private static final String INSERT_STMT =
            "INSERT INTO member (mem_name, mem_account, mem_password, mem_phone, mem_birthday, mem_address, mem_email, mem_status, mem_access) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String GET_ALL_STMT =
            "SELECT * FROM member ";
    private static final String GET_ONE_STMT =
            "SELECT * FROM member where mem_id = ?";
    private static final String GET_BY_ACCOUNT =
            "SELECT * FROM member where mem_account = ?";
    private static final String GET_BY_NAME =
            "SELECT * FROM member where mem_name = ?";
    private static final String GET_BY_PHONE =
            "SELECT * FROM member where mem_phone = ?";
    private static final String GET_BY_BIRTH =
            "SELECT * FROM member where mem_birthday = ?";
    private static final String DELETE =
            "DELETE FROM member where mem_id = ?";
    private static final String UPDATE =
            "UPDATE member set mem_name=?, mem_account=?, mem_phone=?, mem_address=?, mem_email=? where mem_id = ?";
    private static final String UPDATE_PASSWORD =
            "UPDATE member set mem_password=? where mem_id = ?";
    private static final String UPDATE_MEMBERSHIP =
            "UPDATE member set mem_access=0 where mem_id = ?";
    private static final String UPDATE_MEMBERSTATUS =
            "UPDATE member set mem_status=2 where mem_id = ?";
    private static final String UPDATEMEMACCESS =
            "update member set mem_access=1,membership=DATE_ADD(membership,INTERVAL 365 day)  where mem_id=?";
    String driver = "com.mysql.cj.jdbc.Driver";
    @Resource
    private DataSource dataSource;

    @Override
    public void insert(MemVO memVO) {

        Connection con = null;
        PreparedStatement pstmt = null;

        try {

            Class.forName(driver);
            con = dataSource.getConnection();
            pstmt = con.prepareStatement(INSERT_STMT);

            pstmt.setString(1, memVO.getMemName());
            pstmt.setString(2, memVO.getAccount());
            pstmt.setString(3, memVO.getPassword());
            pstmt.setString(4, memVO.getMemPhone());
            pstmt.setDate(5, memVO.getMemBirthday());
            pstmt.setString(6, memVO.getMemAddress());
            pstmt.setString(7, memVO.getMemMail());
            pstmt.setInt(8, memVO.getMemStatus());
            pstmt.setInt(9, memVO.getMemAccess());

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
    public void update(MemVO memVO) {

        Connection con = null;
        PreparedStatement pstmt = null;

        try {

            Class.forName(driver);
            con = dataSource.getConnection();
            pstmt = con.prepareStatement(UPDATE);

            pstmt.setString(1, memVO.getMemName());
            pstmt.setString(2, memVO.getAccount());
            pstmt.setString(3, memVO.getMemPhone());
            pstmt.setString(4, memVO.getMemAddress());
            pstmt.setString(5, memVO.getMemMail());
            pstmt.setInt(6, memVO.getMemID());

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
    public void updateMemAccess(Integer memID) {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {

            Class.forName(driver);
            con = dataSource.getConnection();
            pstmt = con.prepareStatement(UPDATEMEMACCESS);
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

    public Boolean updatePassWord(Integer memberId, String newPassWord) {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {

            Class.forName(driver);
            con = dataSource.getConnection();
            pstmt = con.prepareStatement(UPDATE_PASSWORD);
            pstmt.setString(1, newPassWord);
            pstmt.setInt(2, memberId);
            pstmt.executeUpdate();

            return true;
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
    public void updateMembership(Integer memID) {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {

            Class.forName(driver);
            con = dataSource.getConnection();
            pstmt = con.prepareStatement(UPDATE_MEMBERSHIP);
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
    public void updateMemStatus(Integer memID) {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {

            Class.forName(driver);
            con = dataSource.getConnection();
            pstmt = con.prepareStatement(UPDATE_MEMBERSTATUS);
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
    public void delete(Integer memID) {

        Connection con = null;
        PreparedStatement pstmt = null;

        try {

            Class.forName(driver);
            con = dataSource.getConnection();
            pstmt = con.prepareStatement(DELETE);

            pstmt.setInt(1, memID);

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
    public MemVO findByPrimaryKey(Integer memID) {

        MemVO memVO = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            Class.forName(driver);
            con = dataSource.getConnection();
            pstmt = con.prepareStatement(GET_ONE_STMT);

            pstmt.setInt(1, memID);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                memVO = new MemVO();
                memVO.setMemID(rs.getInt("mem_id"));
                memVO.setMemName(rs.getString("mem_name"));
                memVO.setAccount(rs.getString("mem_account"));
                memVO.setPassword(rs.getString("mem_password"));
                memVO.setMemPhone(rs.getString("mem_phone"));
                memVO.setMemBirthday(rs.getDate("mem_birthday"));
                memVO.setMemAddress(rs.getString("mem_address"));
                memVO.setMemMail(rs.getString("mem_email"));
                memVO.setMemStatus(rs.getInt("mem_status"));
                memVO.setMemAccess(rs.getInt("mem_access"));
                memVO.setMembership(rs.getDate("membership"));
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
        return memVO;
    }

    @Override
    public List<MemVO> findByName(String memName) {
        List<MemVO> listByName = new ArrayList<MemVO>();
        MemVO memVO = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            Class.forName(driver);
            con = dataSource.getConnection();
            pstmt = con.prepareStatement(GET_BY_NAME);

            pstmt.setString(1, memName);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                memVO = new MemVO();
                memVO.setMemID(rs.getInt("mem_id"));
                memVO.setMemName(rs.getString("mem_name"));
                memVO.setAccount(rs.getString("mem_account"));
                memVO.setPassword(rs.getString("mem_password"));
                memVO.setMemPhone(rs.getString("mem_phone"));
                memVO.setMemBirthday(rs.getDate("mem_birthday"));
                memVO.setMemAddress(rs.getString("mem_address"));
                memVO.setMemMail(rs.getString("mem_email"));
                memVO.setMemStatus(rs.getInt("mem_status"));
                memVO.setMemAccess(rs.getInt("mem_access"));
                memVO.setMembership(rs.getDate("membership"));
                listByName.add(memVO);
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
        return listByName;
    }

    @Override
    public MemVO findByPhone(String memPhone) {

        MemVO memVO = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            Class.forName(driver);
            con = dataSource.getConnection();
            pstmt = con.prepareStatement(GET_BY_PHONE);

            pstmt.setString(1, memPhone);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                memVO = new MemVO();
                memVO.setMemID(rs.getInt("mem_id"));
                memVO.setMemName(rs.getString("mem_name"));
                memVO.setAccount(rs.getString("mem_account"));
                memVO.setPassword(rs.getString("mem_password"));
                memVO.setMemPhone(rs.getString("mem_phone"));
                memVO.setMemBirthday(rs.getDate("mem_birthday"));
                memVO.setMemAddress(rs.getString("mem_address"));
                memVO.setMemMail(rs.getString("mem_email"));
                memVO.setMemStatus(rs.getInt("mem_status"));
                memVO.setMemAccess(rs.getInt("mem_access"));
                memVO.setMembership(rs.getDate("membership"));
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
        return memVO;
    }

    @Override
    public MemVO findByBirthday(Date memBirthday) {

        MemVO memVO = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            Class.forName(driver);
            con = dataSource.getConnection();
            pstmt = con.prepareStatement(GET_BY_BIRTH);

            pstmt.setDate(1, memBirthday);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                memVO = new MemVO();
                memVO.setMemID(rs.getInt("mem_id"));
                memVO.setMemName(rs.getString("mem_name"));
                memVO.setAccount(rs.getString("mem_account"));
                memVO.setPassword(rs.getString("mem_password"));
                memVO.setMemPhone(rs.getString("mem_phone"));
                memVO.setMemBirthday(rs.getDate("mem_birthday"));
                memVO.setMemAddress(rs.getString("mem_address"));
                memVO.setMemMail(rs.getString("mem_email"));
                memVO.setMemStatus(rs.getInt("mem_status"));
                memVO.setMemAccess(rs.getInt("mem_access"));
                memVO.setMembership(rs.getDate("membership"));
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
        return memVO;
    }

    @Override
    public MemVO checkAccount(String account) {
        MemVO memVO = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            Class.forName(driver);
            con = dataSource.getConnection();
            pstmt = con.prepareStatement(GET_BY_ACCOUNT);

            pstmt.setString(1, account);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                memVO = new MemVO();
                memVO.setMemID(rs.getInt("mem_id"));
                memVO.setMemName(rs.getString("mem_name"));
                memVO.setAccount(rs.getString("mem_account"));
                memVO.setPassword(rs.getString("mem_password"));
                memVO.setMemPhone(rs.getString("mem_phone"));
                memVO.setMemBirthday(rs.getDate("mem_birthday"));
                memVO.setMemAddress(rs.getString("mem_address"));
                memVO.setMemMail(rs.getString("mem_email"));
                memVO.setMemStatus(rs.getInt("mem_status"));
                memVO.setMemAccess(rs.getInt("mem_access"));
                memVO.setMembership(rs.getDate("membership"));
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
        return memVO;
    }

    @Override
    public List<MemVO> getAll() {
        List<MemVO> list = new ArrayList<MemVO>();
        MemVO memVO = null;

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            Class.forName(driver);
            con = dataSource.getConnection();
            pstmt = con.prepareStatement(GET_ALL_STMT);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                memVO = new MemVO();
                memVO.setMemID(rs.getInt("mem_id"));
                memVO.setMemName(rs.getString("mem_name"));
                memVO.setAccount(rs.getString("mem_account"));
                memVO.setPassword(rs.getString("mem_password"));
                memVO.setMemPhone(rs.getString("mem_phone"));
                memVO.setMemBirthday(rs.getDate("mem_birthday"));
                memVO.setMemAddress(rs.getString("mem_address"));
                memVO.setMemMail(rs.getString("mem_email"));
                memVO.setMemStatus(rs.getInt("mem_status"));
                memVO.setMemAccess(rs.getInt("mem_access"));
                memVO.setMembership(rs.getDate("membership"));
                list.add(memVO);
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


