package com.musclebeach.roomTime.model;

import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RoomTimeDAO implements RoomTimeDAO_interface {

    private static final String INSERT_STMT = "INSERT INTO room_time (room_id, room_date, room_time) VALUES (?, ?, ?)";
    private static final String GET_ALL_STMT = "SELECT * FROM room_time ";
    private static final String GET_ONE_BYID = "SELECT * FROM room_time where time_id = ?";
    private static final String GET_ONE_BYDATE = "SELECT * FROM room_time where room_id = ? and room_date = ?";
    private static final String DELETE = "DELETE FROM room_time where time_id = ?";
    private static final String UPDATE = "UPDATE room_time set room_id = ?, room_date = ?, room_time = ? where time_id = ?";
    private static final String GET_DATE = "select datediff(?,?) as dates";
    private static final String GET_ROOM_ROOMTIME = "SELECT room_time FROM room_time where room_id = ? and room_date = ?";
    private static final String UPDATE_BY_ORDER = "UPDATE room_time SET room_time = ? where room_id = ? and room_date = ?";
    String driver = "com.mysql.cj.jdbc.Driver";
    @Resource
    private DataSource dataSource;

    @Override
    public void insert(RoomTimeVO roomTimeVO) {

        Connection con = null;
        PreparedStatement pstmt = null;

        try {

            Class.forName(driver);
            con = dataSource.getConnection();
            pstmt = con.prepareStatement(INSERT_STMT);

            pstmt.setInt(1, roomTimeVO.getRoomID());
            pstmt.setDate(2, roomTimeVO.getRoomDate());
            pstmt.setString(3, roomTimeVO.getRoomTime());

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
    public void update(RoomTimeVO roomTimeVO) {

        Connection con = null;
        PreparedStatement pstmt = null;

        try {

            Class.forName(driver);
            con = dataSource.getConnection();
            pstmt = con.prepareStatement(UPDATE);

            pstmt.setInt(1, roomTimeVO.getRoomID());
            pstmt.setDate(2, roomTimeVO.getRoomDate());
            pstmt.setString(3, roomTimeVO.getRoomTime());
            pstmt.setInt(4, roomTimeVO.getTimeID());

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
    public void delete(Integer timeID) {

        Connection con = null;
        PreparedStatement pstmt = null;

        try {

            Class.forName(driver);
            con = dataSource.getConnection();
            pstmt = con.prepareStatement(DELETE);

            pstmt.setInt(1, timeID);

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
    public RoomTimeVO findByPrimaryKey(Integer timeID) {

        RoomTimeVO roomTimeVO = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            Class.forName(driver);
            con = dataSource.getConnection();
            pstmt = con.prepareStatement(GET_ONE_BYID);

            pstmt.setInt(1, timeID);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                roomTimeVO = new RoomTimeVO();
                roomTimeVO.setTimeID(rs.getInt("time_id"));
                roomTimeVO.setRoomID(rs.getInt("room_id"));
                roomTimeVO.setRoomDate(rs.getDate("room_date"));
                roomTimeVO.setRoomTime(rs.getString("room_time"));
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
        return roomTimeVO;
    }

    @Override
    public RoomTimeVO findByRoomAndDate(Integer roomID, Date roomDate) {

        RoomTimeVO roomTimeVO = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            Class.forName(driver);
            con = dataSource.getConnection();
            pstmt = con.prepareStatement(GET_ONE_BYDATE);

//			DateFormat dateFormatYMD = new SimpleDateFormat("yyyy/MM/dd");
//			String DateYMD = dateFormatYMD.format(roomDate);
//			java.sql.Date date = new java.sql.Date(0000-00-00);

            pstmt.setInt(1, roomID);
            pstmt.setDate(2, roomDate);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                roomTimeVO = new RoomTimeVO();
                roomTimeVO.setTimeID(rs.getInt("time_id"));
                roomTimeVO.setRoomID(rs.getInt("room_id"));
                roomTimeVO.setRoomDate(rs.getDate("room_date"));
                roomTimeVO.setRoomTime(rs.getString("room_time"));
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
        return roomTimeVO;
    }

    @Override
    public List<RoomTimeVO> getAll() {
        List<RoomTimeVO> list = new ArrayList<RoomTimeVO>();
        RoomTimeVO roomTimeVO = null;

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            Class.forName(driver);
            con = dataSource.getConnection();
            pstmt = con.prepareStatement(GET_ALL_STMT);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                roomTimeVO = new RoomTimeVO();
                roomTimeVO.setTimeID(rs.getInt("time_id"));
                roomTimeVO.setRoomID(rs.getInt("room_id"));
                roomTimeVO.setRoomDate(rs.getDate("room_date"));
                roomTimeVO.setRoomTime(rs.getString("room_time"));
                list.add(roomTimeVO);
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
    public Integer getDates(Date startTime, Date endTime) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Integer dates = null;

        try {

            Class.forName(driver);
            con = dataSource.getConnection();
            pstmt = con.prepareStatement(GET_DATE);

            pstmt.setDate(1, endTime);
            pstmt.setDate(2, startTime);

            rs = pstmt.executeQuery();
            while (rs.next()) {
                dates = rs.getInt("dates");
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
        return dates;
    }

    @Override
    public String findByBorrowDate(Integer roomID, Date borrowDate) {
        RoomTimeVO roomTimeVO = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            Class.forName(driver);
            con = dataSource.getConnection();
            pstmt = con.prepareStatement(GET_ROOM_ROOMTIME);

            pstmt.setInt(1, roomID);
            pstmt.setDate(2, borrowDate);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                roomTimeVO = new RoomTimeVO();
                roomTimeVO.setRoomTime(rs.getString("room_time"));
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
        return roomTimeVO.getRoomTime();
    }

    @Override
    public void updateByOrder(String roomTime, Integer roomID, Date borrowDate) {

        Connection con = null;
        PreparedStatement pstmt = null;

        try {

            Class.forName(driver);
            con = dataSource.getConnection();
            pstmt = con.prepareStatement(UPDATE_BY_ORDER);

            pstmt.setString(1, roomTime);
            pstmt.setInt(2, roomID);
            pstmt.setDate(3, borrowDate);

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
}
