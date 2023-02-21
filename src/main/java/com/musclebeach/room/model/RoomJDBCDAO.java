package com.musclebeach.room.model;

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
public class RoomJDBCDAO implements RoomDAO_interface {
    private static final String INSERT_STMT =
            "INSERT INTO room (room_name, room_address, room_content, room_price, room_status, room_img) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String GET_ALL_STMT =
            "SELECT * FROM room where room_status=1 order by room_iD";
    private static final String GET_ALL_STMTINCZ =
            "SELECT * FROM room order by room_iD";
    private static final String GET_ONE_STMT =
            "SELECT * FROM room where room_iD = ?";
    private static final String DELETE =
            "DELETE FROM room where room_iD = ?";
    private static final String UPDATE =
            "UPDATE room set room_name=?, room_address=?, room_content=?, room_price=?, room_status=?, room_img=? where room_iD = ?";
    String driver = "com.mysql.cj.jdbc.Driver";
    @Resource
    private DataSource dataSource;

    @Override
    public void insert(RoomVO roomVO) {

        Connection con = null;
        PreparedStatement pstmt = null;

        try {

            Class.forName(driver);
            con = dataSource.getConnection();
            pstmt = con.prepareStatement(INSERT_STMT);

            pstmt.setString(1, roomVO.getRoomName());
            pstmt.setString(2, roomVO.getRoomAddress());
            pstmt.setString(3, roomVO.getRoomContent());
            pstmt.setInt(4, roomVO.getRoomPrice());
            pstmt.setInt(5, roomVO.getRoomStatus());
            pstmt.setBytes(6, roomVO.getRoomImg());

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
    public void update(RoomVO roomVO) {

        Connection con = null;
        PreparedStatement pstmt = null;

        try {

            Class.forName(driver);
            con = dataSource.getConnection();
            pstmt = con.prepareStatement(UPDATE);

            pstmt.setString(1, roomVO.getRoomName());
            pstmt.setString(2, roomVO.getRoomAddress());
            pstmt.setString(3, roomVO.getRoomContent());
            pstmt.setInt(4, roomVO.getRoomPrice());
            pstmt.setInt(5, roomVO.getRoomStatus());
            pstmt.setBytes(6, roomVO.getRoomImg());
            pstmt.setInt(7, roomVO.getRoomID());

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
    public void delete(Integer roomID) {

        Connection con = null;
        PreparedStatement pstmt = null;

        try {

            Class.forName(driver);
            con = dataSource.getConnection();
            pstmt = con.prepareStatement(DELETE);

            pstmt.setInt(1, roomID);

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
    public RoomVO findByPrimaryKey(Integer roomID) {

        RoomVO roomVO = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            Class.forName(driver);
            con = dataSource.getConnection();
            pstmt = con.prepareStatement(GET_ONE_STMT);

            pstmt.setInt(1, roomID);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                roomVO = new RoomVO();
                roomVO.setRoomID(rs.getInt("room_id"));
                roomVO.setRoomName(rs.getString("room_name"));
                roomVO.setRoomAddress(rs.getString("room_address"));
                roomVO.setRoomContent(rs.getString("room_content"));
                roomVO.setRoomPrice(rs.getInt("room_price"));
                roomVO.setRoomStatus(rs.getInt("room_status"));
                roomVO.setRoomImg(rs.getBytes("room_img"));
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
        return roomVO;
    }

    @Override
    public List<RoomVO> getAll() {
        List<RoomVO> list = new ArrayList<RoomVO>();
        RoomVO roomVO = null;

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            Class.forName(driver);
            con = dataSource.getConnection();
            pstmt = con.prepareStatement(GET_ALL_STMT);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                // empVO �]�٬� Domain objects
                roomVO = new RoomVO();
                roomVO.setRoomID(rs.getInt("room_id"));
                roomVO.setRoomName(rs.getString("room_name"));
                roomVO.setRoomAddress(rs.getString("room_address"));
                roomVO.setRoomContent(rs.getString("room_content"));
                roomVO.setRoomPrice(rs.getInt("room_price"));
                roomVO.setRoomStatus(rs.getInt("room_status"));
                roomVO.setRoomImg(rs.getBytes("room_img"));
                list.add(roomVO); // Store the row in the list
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
    public List<RoomVO> getAllIncZ() {
        List<RoomVO> list = new ArrayList<RoomVO>();
        RoomVO roomVO = null;

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            Class.forName(driver);
            con = dataSource.getConnection();
            pstmt = con.prepareStatement(GET_ALL_STMTINCZ);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                // empVO �]�٬� Domain objects
                roomVO = new RoomVO();
                roomVO.setRoomID(rs.getInt("room_id"));
                roomVO.setRoomName(rs.getString("room_name"));
                roomVO.setRoomAddress(rs.getString("room_address"));
                roomVO.setRoomContent(rs.getString("room_content"));
                roomVO.setRoomPrice(rs.getInt("room_price"));
                roomVO.setRoomStatus(rs.getInt("room_status"));
                roomVO.setRoomImg(rs.getBytes("room_img"));
                list.add(roomVO); // Store the row in the list
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

}
