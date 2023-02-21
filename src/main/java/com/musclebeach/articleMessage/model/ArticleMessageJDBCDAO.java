package com.musclebeach.articleMessage.model;

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
public class ArticleMessageJDBCDAO implements ArticleMessageDAO_interface {

    private static final String INSERT_STMT = "INSERT INTO gym.article_message (art_id,mem_id,msg_content) VALUES (?, ?, ?)";
    private static final String GET_ALL_STMT = "SELECT msg_id,art_id,mem_id,msg_content,msg_stime,msg_status FROM gym.article_message order by msg_id";
    private static final String GET_ONE_STMT = "SELECT msg_ID,art_ID,mem_ID,msg_content,msg_stime,msg_status FROM gym.article_message where msg_id = ?";
    private static final String GET_ONE_STMT_BY_ARTID = "SELECT msg_ID,art_ID,mem_ID,msg_content,msg_stime,msg_status FROM gym.article_message where art_id = ?";
    private static final String UPDATE = "UPDATE gym.article_message set msg_content=?, msg_status=? where msg_id = ?";
    String driver = "com.mysql.cj.jdbc.Driver";
    @Resource
    private DataSource dataSource;

    @Override
    public void insert(ArticleMessageVO articleMessageVO) {

        Connection con = null;
        PreparedStatement pstmt = null;

        try {

            Class.forName(driver);
            con = dataSource.getConnection();
            pstmt = con.prepareStatement(INSERT_STMT);

            pstmt.setInt(1, articleMessageVO.getArtID());
            pstmt.setInt(2, articleMessageVO.getMemID());
            pstmt.setString(3, articleMessageVO.getMsgContent());

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
    public void update(ArticleMessageVO articleMessageVO) {

        Connection con = null;
        PreparedStatement pstmt = null;

        try {

            Class.forName(driver);
            con = dataSource.getConnection();
            pstmt = con.prepareStatement(UPDATE);

            pstmt.setString(1, articleMessageVO.getMsgContent());
            pstmt.setInt(2, articleMessageVO.getMsgStatus());
            pstmt.setInt(3, articleMessageVO.getMsgID());

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
    public ArticleMessageVO findByPrimaryKey(Integer msgID) {

        ArticleMessageVO articleMessageVO = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            Class.forName(driver);
            con = dataSource.getConnection();
            pstmt = con.prepareStatement(GET_ONE_STMT);

            pstmt.setInt(1, msgID);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                articleMessageVO = new ArticleMessageVO();
                articleMessageVO.setMsgID(rs.getInt("msg_id"));
                articleMessageVO.setArtID(rs.getInt("art_id"));
                articleMessageVO.setMemID(rs.getInt("mem_id"));
                articleMessageVO.setMsgContent(rs.getString("msg_content"));
                articleMessageVO.setMsgStime(rs.getTimestamp("msg_stime"));
                articleMessageVO.setMsgStatus(rs.getInt("msg_status"));
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
        return articleMessageVO;
    }

    @Override
    public List<ArticleMessageVO> getAllByArtID(Integer artID) {
        List<ArticleMessageVO> list = new ArrayList<ArticleMessageVO>();
        ArticleMessageVO articleMessageVO = null;

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            Class.forName(driver);
            con = dataSource.getConnection();
            pstmt = con.prepareStatement(GET_ONE_STMT_BY_ARTID);
            pstmt.setInt(1, artID);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                articleMessageVO = new ArticleMessageVO();
                articleMessageVO.setMsgID(rs.getInt("msg_id"));
                articleMessageVO.setArtID(rs.getInt("art_id"));
                articleMessageVO.setMemID(rs.getInt("mem_id"));
                articleMessageVO.setMsgContent(rs.getString("msg_content"));
                articleMessageVO.setMsgStime(rs.getTimestamp("msg_stime"));
                articleMessageVO.setMsgStatus(rs.getInt("msg_status"));
                list.add(articleMessageVO); // Store the row in the list
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
        return list;
    }

    @Override
    public List<ArticleMessageVO> getAll() {
        List<ArticleMessageVO> list = new ArrayList<ArticleMessageVO>();
        ArticleMessageVO articleMessageVO = null;

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            Class.forName(driver);
            con = dataSource.getConnection();
            pstmt = con.prepareStatement(GET_ALL_STMT);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                articleMessageVO = new ArticleMessageVO();
                articleMessageVO.setMsgID(rs.getInt("msg_id"));
                articleMessageVO.setArtID(rs.getInt("art_id"));
                articleMessageVO.setMemID(rs.getInt("mem_id"));
                articleMessageVO.setMsgContent(rs.getString("msg_content"));
                articleMessageVO.setMsgStime(rs.getTimestamp("msg_stime"));
                articleMessageVO.setMsgStatus(rs.getInt("msg_status"));
                list.add(articleMessageVO); // Store the row in the list
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
        return list;
    }


}