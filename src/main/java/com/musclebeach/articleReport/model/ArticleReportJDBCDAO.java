package com.musclebeach.articleReport.model;

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
public class ArticleReportJDBCDAO implements ArticleReportDAO_interface {
    private static final String INSERT_STMT = "INSERT INTO gym.article_report (art_id,mem_id,report_content) VALUES (?, ?, ?)";
    private static final String GET_ALL_STMT = "SELECT report_id,art_id,mem_id,report_content,report_stime,report_status FROM gym.article_report order by report_id";
    private static final String GET_ONE_STMT = "SELECT report_id,art_id,mem_id,report_content,report_stime,report_status FROM gym.article_report where report_id = ?";
    private static final String DELETE = "DELETE FROM gym.article_report where report_id = ?";
    private static final String UPDATE = "UPDATE gym.article_report set report_status=? where report_id = ?";
    String driver = "com.mysql.cj.jdbc.Driver";

    @Resource
    private DataSource dataSource;


    @Override
    public void insert(ArticleReportVO articleReportVO) {

        Connection con = null;
        PreparedStatement pstmt = null;

        try {

            Class.forName(driver);
            con = dataSource.getConnection();
            pstmt = con.prepareStatement(INSERT_STMT);

            pstmt.setInt(1, articleReportVO.getArtID());
            pstmt.setInt(2, articleReportVO.getMemID());
            pstmt.setString(3, articleReportVO.getReportContent());

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
    public void update(ArticleReportVO articleReportVO) {

        Connection con = null;
        PreparedStatement pstmt = null;

        try {

            Class.forName(driver);
            con = dataSource.getConnection();
            pstmt = con.prepareStatement(UPDATE);

            pstmt.setInt(1, articleReportVO.getReportStatus());
            pstmt.setInt(2, articleReportVO.getReportID());

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
    public void delete(Integer reportID) {

        Connection con = null;
        PreparedStatement pstmt = null;

        try {

            Class.forName(driver);
            con = dataSource.getConnection();
            pstmt = con.prepareStatement(DELETE);

            pstmt.setInt(1, reportID);

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
    public ArticleReportVO findByPrimaryKey(Integer reportID) {

        ArticleReportVO articleReportVO = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            Class.forName(driver);
            con = dataSource.getConnection();
            pstmt = con.prepareStatement(GET_ONE_STMT);

            pstmt.setInt(1, reportID);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                articleReportVO = new ArticleReportVO();
                articleReportVO.setReportID(rs.getInt("report_id"));
                articleReportVO.setArtID(rs.getInt("art_id"));
                articleReportVO.setMemID(rs.getInt("mem_id"));
                articleReportVO.setReportContent(rs.getString("report_content"));
                articleReportVO.setReportStime(rs.getTimestamp("report_stime"));
                articleReportVO.setReportStatus(rs.getInt("report_status"));
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
        return articleReportVO;
    }

    @Override
    public List<ArticleReportVO> getAll() {
        List<ArticleReportVO> list = new ArrayList<ArticleReportVO>();
        ArticleReportVO articleReportVO = null;

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            Class.forName(driver);
            con = dataSource.getConnection();
            pstmt = con.prepareStatement(GET_ALL_STMT);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                articleReportVO = new ArticleReportVO();
                articleReportVO.setReportID(rs.getInt("report_id"));
                articleReportVO.setArtID(rs.getInt("art_id"));
                articleReportVO.setMemID(rs.getInt("mem_id"));
                articleReportVO.setReportContent(rs.getString("report_content"));
                articleReportVO.setReportStime(rs.getTimestamp("report_stime"));
                articleReportVO.setReportStatus(rs.getInt("report_status"));
                list.add(articleReportVO); // Store the row in the list
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