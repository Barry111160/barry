package com.musclebeach.article.model;

import com.musclebeach.articleImg.model.ArticleImgJDBCDAO;
import com.musclebeach.articleImg.model.ArticleImgVO;
import jdbc.util.CompositeQuery.jdbcUtil_CompositeQuery_Article;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Repository
public class ArticleJDBCDAO implements ArticleDAO_interface {

    private static final String INSERT_STMT =
            "INSERT INTO gym.article (mem_id,type_id,art_title,art_content) VALUES ( ?, ?, ?, ?)";
    private static final String GET_ALL_STMT =
            "SELECT art_id,mem_id,type_id,art_title,art_content,art_stime,art_ltime,art_status FROM gym.article order by art_ltime desc";
    private static final String GET_ALL_STMT_BY_TYPE_ID =
            "SELECT art_id,mem_id,type_id,art_title,art_content,art_stime,art_ltime,art_status FROM gym.article where type_id = ? order by art_ltime desc";
    private static final String GET_ALL_STMT_BY_ART_ID =
            "SELECT art_id,mem_id,type_id,art_title,art_content,art_stime,art_ltime,art_status FROM gym.article where art_id = ? order by art_ltime desc";
    private static final String GET_ALL_STMT_BY_MEM_ID =
            "SELECT art_id,mem_id,type_id,art_title,art_content,art_stime,art_ltime,art_status FROM gym.article where mem_id = ? order by art_ltime desc";
    private static final String GET_ALL_STMT_BY_ARTICLE_TITLE_OR_ARTICLE_CONTENT =
            "SELECT art_id,mem_id,type_id,art_title,art_content,art_stime,art_ltime,art_status FROM gym.article where  art_title like ? or  art_content like ? order by art_ltime desc;";
    private static final String GET_ONE_STMT =
            "SELECT art_id,mem_id,type_id,art_title,art_content,art_stime,art_ltime,art_status FROM gym.article where art_id = ?";
    private static final String UPDATE =
            "UPDATE gym.article set mem_id=?, type_id=?, art_title=?, art_content=?, art_status=? where art_id = ?";
    String driver = "com.mysql.cj.jdbc.Driver";
    @Resource
    private DataSource dataSource;

    @Override
    public void insert(ArticleVO articleVO) {

        Connection con = null;
        PreparedStatement pstmt = null;

        try {

            Class.forName(driver);
            con = dataSource.getConnection();
            pstmt = con.prepareStatement(INSERT_STMT);

            pstmt.setInt(1, articleVO.getMemID());
            pstmt.setInt(2, articleVO.getTypeID());
            pstmt.setString(3, articleVO.getArtTitle());
            pstmt.setString(4, articleVO.getArtContent());

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
    public void update(ArticleVO articleVO) {

        Connection con = null;
        PreparedStatement pstmt = null;

        try {

            Class.forName(driver);
            con = dataSource.getConnection();
            pstmt = con.prepareStatement(UPDATE);

            pstmt.setInt(1, articleVO.getMemID());
            pstmt.setInt(2, articleVO.getTypeID());
            pstmt.setString(3, articleVO.getArtTitle());
            pstmt.setString(4, articleVO.getArtContent());
            pstmt.setInt(5, articleVO.getArtStatus());
            pstmt.setInt(6, articleVO.getArtID());

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
    public ArticleVO findByPrimaryKey(Integer artID) {

        ArticleVO articleVO = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            Class.forName(driver);
            con = dataSource.getConnection();
            pstmt = con.prepareStatement(GET_ONE_STMT);

            pstmt.setInt(1, artID);

            rs = pstmt.executeQuery();

            while (rs.next()) {

                articleVO = new ArticleVO();
                articleVO.setArtID(rs.getInt("art_id"));
                articleVO.setMemID(rs.getInt("mem_id"));
                articleVO.setTypeID(rs.getInt("type_id"));
                articleVO.setArtTitle(rs.getString("art_title"));
                articleVO.setArtContent(rs.getString("art_content"));
                articleVO.setArtStime(rs.getTimestamp("art_stime"));
                articleVO.setArtLtime(rs.getTimestamp("art_ltime"));
                articleVO.setArtStatus(rs.getInt("art_status"));
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
        return articleVO;
    }

    @Override
    public List<ArticleVO> getAll() {
        List<ArticleVO> list = new ArrayList<ArticleVO>();
        ArticleVO articleVO = null;

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
                articleVO = new ArticleVO();
                articleVO.setArtID(rs.getInt("art_id"));
                articleVO.setMemID(rs.getInt("mem_id"));
                articleVO.setTypeID(rs.getInt("type_id"));
                articleVO.setArtTitle(rs.getString("art_title"));
                articleVO.setArtContent(rs.getString("art_content"));
                articleVO.setArtStime(rs.getTimestamp("art_stime"));
                articleVO.setArtLtime(rs.getTimestamp("art_ltime"));
                articleVO.setArtStatus(rs.getInt("art_status"));
                list.add(articleVO); // Store the row in the list
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
    public List<ArticleVO> getAllByTypeID(Integer typeID) {
        List<ArticleVO> list = new ArrayList<ArticleVO>();
        ArticleVO articleVO = null;

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            Class.forName(driver);
            con = dataSource.getConnection();
            pstmt = con.prepareStatement(GET_ALL_STMT_BY_TYPE_ID);
            pstmt.setInt(1, typeID);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                // empVO 也稱為 Domain objects
                articleVO = new ArticleVO();
                articleVO.setArtID(rs.getInt("art_id"));
                articleVO.setMemID(rs.getInt("mem_id"));
                articleVO.setTypeID(rs.getInt("type_id"));
                articleVO.setArtTitle(rs.getString("art_title"));
                articleVO.setArtContent(rs.getString("art_content"));
                articleVO.setArtStime(rs.getTimestamp("art_stime"));
                articleVO.setArtLtime(rs.getTimestamp("art_ltime"));
                articleVO.setArtStatus(rs.getInt("art_status"));
                list.add(articleVO); // Store the row in the list
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
    public List<ArticleVO> getAllByArtID(Integer artID) {
        List<ArticleVO> list = new ArrayList<ArticleVO>();
        ArticleVO articleVO = null;

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
                articleVO = new ArticleVO();
                articleVO.setArtID(rs.getInt("art_id"));
                articleVO.setMemID(rs.getInt("mem_id"));
                articleVO.setTypeID(rs.getInt("type_id"));
                articleVO.setArtTitle(rs.getString("art_title"));
                articleVO.setArtContent(rs.getString("art_content"));
                articleVO.setArtStime(rs.getTimestamp("art_stime"));
                articleVO.setArtLtime(rs.getTimestamp("art_ltime"));
                articleVO.setArtStatus(rs.getInt("art_status"));
                list.add(articleVO); // Store the row in the list
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
    public List<ArticleVO> getAllByMemID(Integer memID) {
        List<ArticleVO> list = new ArrayList<ArticleVO>();
        ArticleVO articleVO = null;

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            Class.forName(driver);
            con = dataSource.getConnection();
            pstmt = con.prepareStatement(GET_ALL_STMT_BY_MEM_ID);
            pstmt.setInt(1, memID);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                // empVO 也稱為 Domain objects
                articleVO = new ArticleVO();
                articleVO.setArtID(rs.getInt("art_id"));
                articleVO.setMemID(rs.getInt("mem_id"));
                articleVO.setTypeID(rs.getInt("type_id"));
                articleVO.setArtTitle(rs.getString("art_title"));
                articleVO.setArtContent(rs.getString("art_content"));
                articleVO.setArtStime(rs.getTimestamp("art_stime"));
                articleVO.setArtLtime(rs.getTimestamp("art_ltime"));
                articleVO.setArtStatus(rs.getInt("art_status"));
                list.add(articleVO); // Store the row in the list
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
    public List<ArticleVO> getAllByArticleTitleOrArticleContent(String artTitle, String artContent) {
        List<ArticleVO> list = new ArrayList<ArticleVO>();
        ArticleVO articleVO = null;

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            Class.forName(driver);
            con = dataSource.getConnection();
            pstmt = con.prepareStatement(GET_ALL_STMT_BY_ARTICLE_TITLE_OR_ARTICLE_CONTENT);
            pstmt.setString(1, "%" + artTitle + "%");
            pstmt.setString(2, "%" + artContent + "%");
            rs = pstmt.executeQuery();

            while (rs.next()) {
                // empVO 也稱為 Domain objects
                articleVO = new ArticleVO();
                articleVO.setArtID(rs.getInt("art_id"));
                articleVO.setMemID(rs.getInt("mem_id"));
                articleVO.setTypeID(rs.getInt("type_id"));
                articleVO.setArtTitle(rs.getString("art_title"));
                articleVO.setArtContent(rs.getString("art_content"));
                articleVO.setArtStime(rs.getTimestamp("art_stime"));
                articleVO.setArtLtime(rs.getTimestamp("art_ltime"));
                articleVO.setArtStatus(rs.getInt("art_status"));
                list.add(articleVO); // Store the row in the list
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

    //	複合查詢
    @Override
    public List<ArticleVO> getAll(Map<String, String[]> map) {
        List<ArticleVO> list = new ArrayList<ArticleVO>();
        ArticleVO articleVO = null;

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            Class.forName(driver);
            con = dataSource.getConnection();
            String finalSQL = "SELECT * FROM gym.article a "
                    + "JOIN gym.member m ON a.mem_id = m.mem_id "
                    + jdbcUtil_CompositeQuery_Article.get_WhereCondition(map)
                    + " order by art_ltime desc";
            pstmt = con.prepareStatement(finalSQL);
            System.out.println("●●finalSQL(by DAO) = " + finalSQL);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                articleVO = new ArticleVO();
                articleVO.setArtID(rs.getInt("art_id"));
                articleVO.setMemID(rs.getInt("mem_id"));
                articleVO.setTypeID(rs.getInt("type_id"));
                articleVO.setArtTitle(rs.getString("art_title"));
                articleVO.setArtContent(rs.getString("art_content"));
                articleVO.setArtStime(rs.getTimestamp("art_stime"));
                articleVO.setArtLtime(rs.getTimestamp("art_ltime"));
                articleVO.setArtStatus(rs.getInt("art_status"));
//				articleVO.setMemID(rs.getInt("mem_id"));
//				articleVO.setTypeID(rs.getInt("type_id"));
//				articleVO.setArtTitle(rs.getString("art_title"));
//				articleVO.setArtContent(rs.getString("art_content"));

                list.add(articleVO); // Store the row in the List
            }

            // Handle any SQL errors
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Couldn't load database driver. "
                    + e.getMessage());
            // Handle any SQL errors
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

    @Override
    public void insertWithArticleImgs(ArticleVO articleVO, List<ArticleImgVO> list) {

        Connection con = null;
        PreparedStatement pstmt = null;

        try {

            Class.forName(driver);
            con = dataSource.getConnection();

            // 1●設定於 pstm.executeUpdate()之前
            con.setAutoCommit(false);

            // 先新增文章
            String[] cols = {"art_id"};
            pstmt = con.prepareStatement(INSERT_STMT, cols);
            pstmt.setInt(1, articleVO.getMemID());
            pstmt.setInt(2, articleVO.getTypeID());
            pstmt.setString(3, articleVO.getArtTitle());
            pstmt.setString(4, articleVO.getArtContent());
            Statement stmt = con.createStatement();
            pstmt.executeUpdate();
            //掘取對應的自增主鍵值
            String next_art_id = null;
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                next_art_id = rs.getString(1);
                System.out.println("自增主鍵值= " + next_art_id + "(剛新增成功的文章編號)");
            } else {
                System.out.println("未取得自增主鍵值");
            }
            rs.close();
            // 再同時新增圖片
            ArticleImgJDBCDAO dao = new ArticleImgJDBCDAO();
            System.out.println("list.size()-A=" + list.size());
            for (ArticleImgVO aArticleImg : list) {
                aArticleImg.setArtID(Integer.valueOf(next_art_id));
                dao.insert2(aArticleImg, con);
            }

            // 2●設定於 pstm.executeUpdate()之後
            con.commit();
            con.setAutoCommit(true);
            System.out.println("list.size()-B=" + list.size());
            System.out.println("新增文章編號" + next_art_id + "時,共有圖片" + list.size()
                    + "張同時被新增");

            // Handle any driver errors
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Couldn't load database driver. "
                    + e.getMessage());
            // Handle any SQL errors
        } catch (SQLException se) {
            if (con != null) {
                try {
                    // 3●設定於當有exception發生時之catch區塊內
                    System.err.print("Transaction is being ");
                    System.err.println("rolled back-由-dept");
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
