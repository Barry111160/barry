package com.musclebeach.question.model;

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
public class QuestionJDBCDAO implements QuestionDAO_interface {
    private static final String INSERT_STMT = "INSERT INTO question ( question_title, question_content)VALUES(?,?)";
    private static final String GET_ALL_STMT = "SELECT question_id, question_title, question_content FROM question order by question_id ";
    private static final String GET_ONE_STMT = "SELECT question_id,question_title, question_content FROM question where  question_id = ?";
    private static final String GET_QUEBYQUETITLE_STMT = "SELECT question_id,question_title, question_content FROM question where question_title like ?";
    private static final String GET_QUEBYQUECONTENT_STMT = "SELECT question_id,question_title, question_content FROM question where question_content like ?";
    private static final String DELETE = "DELETE FROM question where question_id = ?";
    private static final String UPDATE = "UPDATE question set question_title=?, question_content=? where question_id = ?";
    String driver = "com.mysql.cj.jdbc.Driver";
    @Resource
    private DataSource dataSource;

    @Override
    public void insert(QuestionVO questionVO) {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {

            Class.forName(driver);
            con = dataSource.getConnection();
            pstmt = con.prepareStatement(INSERT_STMT);

            pstmt.setString(1, questionVO.getQuestionTitle());
            pstmt.setString(2, questionVO.getQuestionContent());

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
    public void update(QuestionVO questionVO) {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {

            Class.forName(driver);
            con = dataSource.getConnection();
            pstmt = con.prepareStatement(UPDATE);

            pstmt.setString(1, questionVO.getQuestionTitle());
            pstmt.setString(2, questionVO.getQuestionContent());
            pstmt.setInt(3, questionVO.getQuestionID());

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
    public void delete(Integer questionID) {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {

            Class.forName(driver);
            con = dataSource.getConnection();
            pstmt = con.prepareStatement(DELETE);

            pstmt.setInt(1, questionID);

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

    public Set<QuestionVO> getQuestionByQuestionTitle(String questionTitle) {
        Set<QuestionVO> set = new LinkedHashSet<QuestionVO>();

        QuestionVO questionVO = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {

            Class.forName(driver);

            con = dataSource.getConnection();
            pstmt = con.prepareStatement(GET_QUEBYQUETITLE_STMT);

            pstmt.setString(1, "%" + questionTitle + "%");

            rs = pstmt.executeQuery();

            while (rs.next()) {

                questionVO = new QuestionVO();
                questionVO.setQuestionID(rs.getInt("question_id"));
                questionVO.setQuestionTitle(rs.getString("question_title"));
                questionVO.setQuestionContent(rs.getString("question_content"));
                set.add(questionVO);
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
        return set;
    }

    @Override
    public Set<QuestionVO> getQuestionByQuestionContent(String questionContent) {
        Set<QuestionVO> set = new LinkedHashSet<QuestionVO>();

        QuestionVO questionVO = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {

            Class.forName(driver);

            con = dataSource.getConnection();
            pstmt = con.prepareStatement(GET_QUEBYQUECONTENT_STMT);

            pstmt.setString(1, "%" + questionContent + "%");

            rs = pstmt.executeQuery();

            while (rs.next()) {

                questionVO = new QuestionVO();

                questionVO.setQuestionID(rs.getInt("question_id"));
                questionVO.setQuestionTitle(rs.getString("question_title"));
                questionVO.setQuestionContent(rs.getString("question_content"));

                set.add(questionVO);
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
        return set;
    }

    @Override
    public List<QuestionVO> getAll() {
        List<QuestionVO> list = new ArrayList<QuestionVO>();
        QuestionVO questionVO = null;

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            Class.forName(driver);
            con = dataSource.getConnection();
            pstmt = con.prepareStatement(GET_ALL_STMT);
            rs = pstmt.executeQuery();

            while (rs.next()) {

                questionVO = new QuestionVO();
                questionVO.setQuestionID(rs.getInt("question_id"));
                questionVO.setQuestionTitle(rs.getString("question_title"));
                questionVO.setQuestionContent(rs.getString("question_content"));
                list.add(questionVO);
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
    public QuestionVO findByPrimaryKey(Integer questionID) {
        QuestionVO questionVO = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {

            Class.forName(driver);

            con = dataSource.getConnection();
            pstmt = con.prepareStatement(GET_ONE_STMT);

            pstmt.setInt(1, questionID);

            rs = pstmt.executeQuery();

            while (rs.next()) {

                questionVO = new QuestionVO();
                questionVO.setQuestionID(rs.getInt("question_id"));
                questionVO.setQuestionTitle(rs.getString("question_title"));
                questionVO.setQuestionContent(rs.getString("question_content"));

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
        return questionVO;
    }

}
