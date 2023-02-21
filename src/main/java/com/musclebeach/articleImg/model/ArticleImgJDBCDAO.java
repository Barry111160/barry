package com.musclebeach.articleImg.model;

import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ArticleImgJDBCDAO implements ArticleImgDAO_interface {

    private static final String INSERT_STMT =
            "INSERT INTO gym.article_img (art_img,art_id) VALUES (?, ?)";
    private static final String GET_ALL_STMT =
            "SELECT img_id,art_img,art_id FROM gym.article_img order by img_id";
    private static final String GET_ALL_STMT_BY_ART_ID =
            "SELECT img_id,art_img,art_id FROM gym.article_img where art_id = ?";
    private static final String GET_ONE_STMT =
            "SELECT img_id,art_img,art_id FROM gym.article_img where img_id = ?";
    private static final String GET_ONE_STMT_BY_ARTID =
            "SELECT img_id,art_img,art_id FROM gym.article_img where art_id = ?";
    private static final String DELETE =
            "DELETE FROM gym.article_img where img_id = ?";
    private static final String UPDATE =
            "UPDATE gym.article_img set art_img=? where img_id = ?";
    String driver = "com.mysql.cj.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/db01?serverTimezone=Asia/Taipei";
    String userid = "root";
    String passwd = "password";
    @Resource
    private DataSource dataSource;

    @Override
    public void insert(ArticleImgVO articleImgVO) {

        Connection con = null;
        PreparedStatement pstmt = null;

        try {

            Class.forName(driver);
            con = dataSource.getConnection();
            pstmt = con.prepareStatement(INSERT_STMT);

            pstmt.setBytes(1, articleImgVO.getArtImg());
            pstmt.setInt(2, articleImgVO.getArtID());


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
    public void update(ArticleImgVO articleImgVO) {

        Connection con = null;
        PreparedStatement pstmt = null;

        try {

            Class.forName(driver);
            con = dataSource.getConnection();
            pstmt = con.prepareStatement(UPDATE);

            pstmt.setBytes(1, articleImgVO.getArtImg());


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
    public void delete(Integer imgID) {

        Connection con = null;
        PreparedStatement pstmt = null;

        try {

            Class.forName(driver);
            con = dataSource.getConnection();
            pstmt = con.prepareStatement(DELETE);

            pstmt.setInt(1, imgID);

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
    public List<ArticleImgVO> getAllByArtID(Integer artID) {
        List<ArticleImgVO> list = new ArrayList<ArticleImgVO>();
        ArticleImgVO articleImgVO = null;

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            Class.forName(driver);
            con = dataSource.getConnection();
            pstmt = con.prepareStatement(GET_ALL_STMT_BY_ART_ID);
            pstmt.setInt(1, artID);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                // empVO 也稱為 Domain objects
                articleImgVO = new ArticleImgVO();
                articleImgVO.setImgID(rs.getInt("img_id"));
                articleImgVO.setArtImg(rs.getBytes("art_img"));
                articleImgVO.setArtID(rs.getInt("art_id"));

                list.add(articleImgVO); // Store the row in the list
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
    public List<ArticleImgVO> getAll() {
        List<ArticleImgVO> list = new ArrayList<ArticleImgVO>();
        ArticleImgVO articleImgVO = null;

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            Class.forName(driver);
            con = dataSource.getConnection();
            pstmt = con.prepareStatement(GET_ALL_STMT);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                // empVO 也稱為 Domain objects
                articleImgVO = new ArticleImgVO();
                articleImgVO.setImgID(rs.getInt("img_id"));
                articleImgVO.setArtImg(rs.getBytes("art_img"));
                articleImgVO.setArtID(rs.getInt("art_id"));
                list.add(articleImgVO); // Store the row in the list
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
    public ArticleImgVO findByPrimaryKey(Integer imgID) {

        ArticleImgVO articleImgVO = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            Class.forName(driver);
            con = dataSource.getConnection();
            pstmt = con.prepareStatement(GET_ONE_STMT);

            pstmt.setInt(1, imgID);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                // empVo 也稱為 Domain objects
                articleImgVO = new ArticleImgVO();
                articleImgVO.setImgID(rs.getInt("img_id"));
                articleImgVO.setArtImg(rs.getBytes("art_img"));
                articleImgVO.setArtID(rs.getInt("art_id"));
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
        return articleImgVO;
    }

    @Override
    public ArticleImgVO findByArtID(Integer artID) {

        ArticleImgVO articleImgVO = null;
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
                // empVo 也稱為 Domain objects
                articleImgVO = new ArticleImgVO();
                articleImgVO.setImgID(rs.getInt("img_id"));
                articleImgVO.setArtImg(rs.getBytes("art_img"));
                articleImgVO.setArtID(rs.getInt("art_id"));
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
        return articleImgVO;
    }


    @Override
    public void insert2(ArticleImgVO articleImgVO, Connection con) {

        PreparedStatement pstmt = null;

        try {

            pstmt = con.prepareStatement(INSERT_STMT);

            pstmt.setBytes(1, articleImgVO.getArtImg());
            pstmt.setInt(2, articleImgVO.getArtID());


            Statement stmt = con.createStatement();
            pstmt.executeUpdate();

            // Handle any SQL errors
        } catch (SQLException se) {
            if (con != null) {
                try {
                    // 3●設定於當有exception發生時之catch區塊內
                    System.err.print("Transaction is being ");
                    System.err.println("rolled back-由-ArticleImg");
                    con.rollback();
                } catch (SQLException excep) {
                    throw new RuntimeException("rollback error occured. "
                            + excep.getMessage());
                }
            }
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
        }

    }

    @Override
    public void insertImgBatch(List<ArticleImgVO> articleImgVOList) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_STMT)
        ) {
            for (ArticleImgVO articleImgVO : articleImgVOList) {
                preparedStatement.setBytes(1, articleImgVO.getArtImg());
                preparedStatement.setInt(2, articleImgVO.getArtID());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
