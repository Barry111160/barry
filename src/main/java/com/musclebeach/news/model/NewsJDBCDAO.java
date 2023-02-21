package com.musclebeach.news.model;

import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Repository
public class NewsJDBCDAO implements NewsDAO_interface {
    private static final String INSERT_STMT =
            "INSERT INTO news ( emp_id, news_title, news_content)VALUES(?,?,?)";
    private static final String GET_ALL_STMT =
            "SELECT news_id, emp_id, news_title, news_content, news_time FROM news order by news_time desc";
    private static final String GET_ONE_STMT =
            "SELECT news_id, emp_id, news_title, news_content, news_time FROM news where news_id = ?";
    private static final String GET_NEWSBYEMPID_STMT =
            "SELECT news_id, emp_id, news_title, news_content, news_time FROM news where emp_id = ? order by news_time desc";
    private static final String GET_NEWSTITLE_STMT =
            "SELECT news_id, emp_id, news_title, news_content, news_time FROM news where news_title like ? order by news_time desc";
    private static final String DELETE =
            "DELETE FROM news where news_id = ?";
    private static final String UPDATE =
            "UPDATE news set  emp_id=?, news_title=?, news_content=? where news_id = ?";
    String driver = "com.mysql.cj.jdbc.Driver";
    @Resource
    private DataSource dataSource;

    @Override
    public void insert(NewsVO newsVO) {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {

            Class.forName(driver);
            con = dataSource.getConnection();
            pstmt = con.prepareStatement(INSERT_STMT);


            pstmt.setInt(1, newsVO.getEmpID());
            pstmt.setString(2, newsVO.getNewsTitle());
            pstmt.setString(3, newsVO.getNewsContent());


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
    public void update(NewsVO newsVO) {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {

            Class.forName(driver);
            con = dataSource.getConnection();
            pstmt = con.prepareStatement(UPDATE);


            pstmt.setInt(1, newsVO.getEmpID());
            pstmt.setString(2, newsVO.getNewsTitle());
            pstmt.setString(3, newsVO.getNewsContent());
            pstmt.setInt(4, newsVO.getNewsID());

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
    public void delete(Integer newsID) {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {

            Class.forName(driver);
            con = dataSource.getConnection();
            pstmt = con.prepareStatement(DELETE);

            pstmt.setInt(1, newsID);

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
    public NewsVO findByPrimaryKey(Integer newsID) {
        NewsVO newsVO = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {

            Class.forName(driver);

            con = dataSource.getConnection();
            pstmt = con.prepareStatement(GET_ONE_STMT);

            pstmt.setInt(1, newsID);

            rs = pstmt.executeQuery();

            while (rs.next()) {

                newsVO = new NewsVO();
                newsVO.setNewsID(rs.getInt("news_id"));
                newsVO.setEmpID(rs.getInt("emp_id"));
                newsVO.setNewsTitle(rs.getString("news_title"));
                newsVO.setNewsContent(rs.getString("news_content"));
                newsVO.setNewsTime(rs.getTimestamp("news_time"));
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
        return newsVO;
    }

    @Override
    public Set<NewsVO> getNewsByempID(Integer empID) {
        Set<NewsVO> set = new LinkedHashSet<NewsVO>();

        NewsVO newsVO = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {

            Class.forName(driver);

            con = dataSource.getConnection();
            pstmt = con.prepareStatement(GET_NEWSBYEMPID_STMT);

            pstmt.setInt(1, empID);

            rs = pstmt.executeQuery();

            while (rs.next()) {

                newsVO = new NewsVO();
                newsVO.setNewsID(rs.getInt("news_id"));
                newsVO.setEmpID(rs.getInt("emp_id"));
                newsVO.setNewsTitle(rs.getString("news_title"));
                newsVO.setNewsContent(rs.getString("news_content"));
                newsVO.setNewsTime(rs.getTimestamp("news_time"));
                set.add(newsVO);
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
        return set;
    }

    public Set<NewsVO> getNewsByNewsTitle(String newsTitle) {
        Set<NewsVO> set = new LinkedHashSet<NewsVO>();

        NewsVO newsVO = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {

            Class.forName(driver);

            con = dataSource.getConnection();
            pstmt = con.prepareStatement(GET_NEWSTITLE_STMT);

            pstmt.setString(1, "%" + newsTitle + "%");

            rs = pstmt.executeQuery();

            while (rs.next()) {

                newsVO = new NewsVO();
                newsVO.setNewsID(rs.getInt("news_id"));
                newsVO.setEmpID(rs.getInt("emp_id"));
                newsVO.setNewsTitle(rs.getString("news_title"));
                newsVO.setNewsContent(rs.getString("news_content"));
                newsVO.setNewsTime(rs.getTimestamp("news_time"));
                set.add(newsVO);
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
        return set;
    }

    @Override
    public List<NewsVO> getAll() {
        List<NewsVO> list = new ArrayList<NewsVO>();
        NewsVO newsVO = null;

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            Class.forName(driver);
            con = dataSource.getConnection();
            pstmt = con.prepareStatement(GET_ALL_STMT);
            rs = pstmt.executeQuery();

            while (rs.next()) {

                newsVO = new NewsVO();
                newsVO.setNewsID(rs.getInt("news_id"));
                newsVO.setEmpID(rs.getInt("emp_id"));
                newsVO.setNewsTitle(rs.getString("news_title"));
                newsVO.setNewsContent(rs.getString("news_content"));
                newsVO.setNewsTime(rs.getTimestamp("news_time"));
                list.add(newsVO);
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