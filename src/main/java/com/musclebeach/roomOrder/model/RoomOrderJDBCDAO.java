package com.musclebeach.roomOrder.model;

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
public class RoomOrderJDBCDAO implements RoomOrderDAO_interface {
    private static final String INSERT_STMT =
            "INSERT INTO room_order (mem_id, room_id, borrow_date, borrow_time, order_status) VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE =
            "UPDATE room_order set mem_id=?, emp_id=?, room_id=?, borrow_date=?, borrow_time=?, order_status=? where order_id = ?";
    private static final String DELETE =
            "DELETE FROM room_order where order_id = ?";
    private static final String GET_ONE_MEM =
            "SELECT * FROM room_order where mem_id = ? order by createtime desc";
    private static final String GET_BY_EMPLOYEE =
            "SELECT * FROM room_order where emp_id = ? order by createtime desc";
    private static final String GET_ONE_STMT =
            "SELECT * FROM room_order where order_id = ?";
    private static final String GET_ALL_STMT =
            "SELECT * FROM room_order order by order_id";
    private static final String GET_PENDING_ORDER =
            "SELECT * FROM room_order WHERE emp_id is null or order_status = 2;";
    private static final String GET_ALL_ORDER =
            "SELECT order_id, mem_id, room_name, borrow_date, borrow_time, order_status, emp_name\r\n"
                    + "FROM room_order ro\r\n"
                    + "join room r on r.room_id = ro.room_id\r\n"
                    + "join employee e on e.emp_id = ro.emp_id\r\n"
                    + "order by order_id;";
    private static final String UPDATE_BY_CONFIRM =
            "UPDATE room_order SET emp_id = ? WHERE order_id = ?";
    private static final String UPDATE_BY_CANCELAPPLY =
            "UPDATE room_order SET order_status = 2 WHERE order_id = ?";
    private static final String UPDATE_BY_CANCELED =
            "UPDATE room_order SET order_status = 0, emp_id = ? WHERE order_id = ?";
    String driver = "com.mysql.cj.jdbc.Driver";
    @Resource
    private DataSource dataSource;

    @Override
    public void insert(RoomOrderVO roomOrderVO) {

        Connection con = null;
        PreparedStatement pstmt = null;

        try {

            Class.forName(driver);
            con = dataSource.getConnection();
            pstmt = con.prepareStatement(INSERT_STMT);

            pstmt.setInt(1, roomOrderVO.getMemID());
            pstmt.setInt(2, roomOrderVO.getRoomID());
            pstmt.setDate(3, roomOrderVO.getBorrowDate());
            pstmt.setInt(4, roomOrderVO.getBorrowTime());
            pstmt.setInt(5, roomOrderVO.getOrderStatus());

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
    public void update(RoomOrderVO roomOrderVO) {

        Connection con = null;
        PreparedStatement pstmt = null;

        try {

            Class.forName(driver);
            con = dataSource.getConnection();
            pstmt = con.prepareStatement(UPDATE);

            pstmt.setInt(1, roomOrderVO.getMemID());
            pstmt.setInt(2, roomOrderVO.getEmpID());
            pstmt.setInt(3, roomOrderVO.getRoomID());
            pstmt.setDate(4, roomOrderVO.getBorrowDate());
            pstmt.setInt(5, roomOrderVO.getBorrowTime());
            pstmt.setInt(6, roomOrderVO.getOrderStatus());
            pstmt.setInt(7, roomOrderVO.getOrderID());

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
    public void delete(Integer roomOrderVO) {

        Connection con = null;
        PreparedStatement pstmt = null;

        try {

            Class.forName(driver);
            con = dataSource.getConnection();
            pstmt = con.prepareStatement(DELETE);

            pstmt.setInt(1, roomOrderVO);

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
    public RoomOrderVO findByPrimaryKey(Integer orderID) {

        RoomOrderVO roomOrderVO = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            Class.forName(driver);
            con = dataSource.getConnection();
            pstmt = con.prepareStatement(GET_ONE_STMT);

            pstmt.setInt(1, orderID);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                roomOrderVO = new RoomOrderVO();
                roomOrderVO.setOrderID(rs.getInt("order_id"));
                roomOrderVO.setMemID(rs.getInt("mem_id"));
                roomOrderVO.setEmpID(rs.getInt("emp_id"));
                roomOrderVO.setCreateTime(rs.getTimestamp("createtime"));
                roomOrderVO.setUpdateTime(rs.getTimestamp("updatetime"));
                roomOrderVO.setBorrowDate(rs.getDate("borrow_date"));
                roomOrderVO.setBorrowTime(rs.getInt("borrow_time"));
                roomOrderVO.setOrderStatus(rs.getInt("order_status"));
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
        return roomOrderVO;
    }

    public List<RoomOrderVO> findByMember(Integer memID) {
        List<RoomOrderVO> list = new ArrayList<RoomOrderVO>();
        RoomOrderVO roomOrderVO = null;

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            Class.forName(driver);
            con = dataSource.getConnection();
            pstmt = con.prepareStatement(GET_ONE_MEM);

            pstmt.setInt(1, memID);

            rs = pstmt.executeQuery();
            while (rs.next()) {
                roomOrderVO = new RoomOrderVO();
                roomOrderVO.setOrderID(rs.getInt("order_id"));
                roomOrderVO.setMemID(rs.getInt("mem_id"));
                roomOrderVO.setEmpID(rs.getInt("emp_id"));
                roomOrderVO.setRoomID(rs.getInt("room_id"));
                roomOrderVO.setCreateTime(rs.getTimestamp("createtime"));
                roomOrderVO.setUpdateTime(rs.getTimestamp("updatetime"));
                roomOrderVO.setBorrowDate(rs.getDate("borrow_date"));
                roomOrderVO.setBorrowTime(rs.getInt("borrow_time"));
                roomOrderVO.setOrderStatus(rs.getInt("order_status"));
                list.add(roomOrderVO); // Store the row in the list
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
    public List<RoomOrderVO> findByEmployee(Integer empID) {
        List<RoomOrderVO> list = new ArrayList<RoomOrderVO>();
        RoomOrderVO roomOrderVO = null;

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            Class.forName(driver);
            con = dataSource.getConnection();
            pstmt = con.prepareStatement(GET_BY_EMPLOYEE);

            pstmt.setInt(1, empID);

            rs = pstmt.executeQuery();
            while (rs.next()) {
                roomOrderVO = new RoomOrderVO();
                roomOrderVO.setOrderID(rs.getInt("order_id"));
                roomOrderVO.setMemID(rs.getInt("mem_id"));
                roomOrderVO.setRoomID(rs.getInt("room_id"));
                roomOrderVO.setCreateTime(rs.getTimestamp("createtime"));
                roomOrderVO.setUpdateTime(rs.getTimestamp("updatetime"));
                roomOrderVO.setBorrowDate(rs.getDate("borrow_date"));
                roomOrderVO.setBorrowTime(rs.getInt("borrow_time"));
                roomOrderVO.setOrderStatus(rs.getInt("order_status"));
                list.add(roomOrderVO); // Store the row in the list
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
    public List<RoomOrderVO> getAll() {
        List<RoomOrderVO> list = new ArrayList<RoomOrderVO>();
        RoomOrderVO roomOrderVO = null;

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            Class.forName(driver);
            con = dataSource.getConnection();
            pstmt = con.prepareStatement(GET_ALL_STMT);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                roomOrderVO = new RoomOrderVO();
                roomOrderVO.setOrderID(rs.getInt("order_id"));
                roomOrderVO.setMemID(rs.getInt("mem_id"));
                roomOrderVO.setEmpID(rs.getInt("emp_id"));
                roomOrderVO.setRoomID(rs.getInt("room_id"));
                roomOrderVO.setCreateTime(rs.getTimestamp("createtime"));
                roomOrderVO.setUpdateTime(rs.getTimestamp("updatetime"));
                roomOrderVO.setBorrowDate(rs.getDate("borrow_date"));
                roomOrderVO.setBorrowTime(rs.getInt("borrow_time"));
                roomOrderVO.setOrderStatus(rs.getInt("order_status"));
                list.add(roomOrderVO); // Store the row in the list
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
    public List<RoomOrderVO> getPendingOrder() {
        List<RoomOrderVO> list = new ArrayList<RoomOrderVO>();
        RoomOrderVO roomOrderVO = null;

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            Class.forName(driver);
            con = dataSource.getConnection();
            pstmt = con.prepareStatement(GET_PENDING_ORDER);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                roomOrderVO = new RoomOrderVO();
                roomOrderVO.setOrderID(rs.getInt("order_id"));
                roomOrderVO.setMemID(rs.getInt("mem_id"));
                roomOrderVO.setEmpID(rs.getInt("emp_id"));
                roomOrderVO.setRoomID(rs.getInt("room_id"));
                roomOrderVO.setCreateTime(rs.getTimestamp("createtime"));
                roomOrderVO.setUpdateTime(rs.getTimestamp("updatetime"));
                roomOrderVO.setBorrowDate(rs.getDate("borrow_date"));
                roomOrderVO.setBorrowTime(rs.getInt("borrow_time"));
                roomOrderVO.setOrderStatus(rs.getInt("order_status"));
                list.add(roomOrderVO); // Store the row in the list
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
    public List<RoomOrderVO> getAllOrder() {
        List<RoomOrderVO> list = new ArrayList<RoomOrderVO>();
        RoomOrderVO roomOrderVO = null;

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            Class.forName(driver);
            con = dataSource.getConnection();
            pstmt = con.prepareStatement(GET_ALL_ORDER);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                roomOrderVO = new RoomOrderVO();
                roomOrderVO.setOrderID(rs.getInt("order_id"));
                roomOrderVO.setMemID(rs.getInt("mem_id"));
                roomOrderVO.setEmpID(rs.getInt("emp_id"));
                String emp_name = rs.getString(rs.getInt("emp_id"));
                roomOrderVO.setRoomID(rs.getInt("room_id"));
                String room_name = rs.getString(rs.getInt("room_id"));
                roomOrderVO.setCreateTime(rs.getTimestamp("createtime"));
                roomOrderVO.setUpdateTime(rs.getTimestamp("updatetime"));
                roomOrderVO.setBorrowDate(rs.getDate("borrow_date"));
                roomOrderVO.setBorrowTime(rs.getInt("borrow_time"));
                roomOrderVO.setOrderStatus(rs.getInt("order_status"));
                list.add(roomOrderVO); // Store the row in the list
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
    public void confirmRoomOrder(Integer orderID, Integer employeeID) {

        Connection con = null;
        PreparedStatement pstmt = null;

        try {

            Class.forName(driver);
            con = dataSource.getConnection();
            pstmt = con.prepareStatement(UPDATE_BY_CONFIRM);

            pstmt.setInt(1, employeeID);
            pstmt.setInt(2, orderID);

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
    public void cancelApplication(Integer orderID) {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {

            Class.forName(driver);
            con = dataSource.getConnection();
            pstmt = con.prepareStatement(UPDATE_BY_CANCELAPPLY);
            pstmt.setInt(1, orderID);
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
    public void cancelRoomOrder(Integer orderID, Integer empID) {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {

            Class.forName(driver);
            con = dataSource.getConnection();
            pstmt = con.prepareStatement(UPDATE_BY_CANCELED);
            pstmt.setInt(1, empID);
            pstmt.setInt(2, orderID);
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


}
