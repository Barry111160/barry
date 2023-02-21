package com.musclebeach.teamClass.model;

import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TeamClassDao implements TeamClassIDao {
    public final static String CLASSNAME = "com.mysql.cj.jdbc.Driver";
    private static final String INSERT = "INSERT INTO `gym`.`team_class` ( `emp_id`, `type_id`, `class_name`, `people_max`, `class_content`, `class_status`) VALUES( ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE `gym`.`team_class` SET `emp_id` = ?, `type_id` = ?, `class_name` = ?,  `people_max` = ?, `class_content` = ?, `class_status` = ? WHERE `class_id` = ?";
    private static final String DELETE = "DELETE FROM `gym`.`team_class` WHERE `class_id` = ?";
    private static final String GET = "SELECT * FROM `gym`.`team_class` WHERE `class_id` = ?";
    private static final String GETALL = "SELECT * FROM `gym`.`team_class`";
    private static final String GETONETYPE = "SELECT * FROM `gym`.`team_class` WHERE `type_id` = ?";
    @Resource
    private DataSource dataSource;

    @Override
    public void insert(TeamClassVO obj) {
        try {
            Class.forName(CLASSNAME);
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT);) {
            preparedStatement.setInt(1, obj.getEmpID());
            preparedStatement.setInt(2, obj.getTypeID());
            preparedStatement.setString(3, obj.getClassName());
            preparedStatement.setInt(4, obj.getPeopleMax());
            preparedStatement.setString(5, obj.getClassContent());
            preparedStatement.setInt(6, obj.getClassStatus());
            int rowCount = preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(TeamClassVO obj) {
        try {
            Class.forName(CLASSNAME);
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);) {
            preparedStatement.setInt(1, obj.getEmpID());
            preparedStatement.setInt(2, obj.getTypeID());
            preparedStatement.setString(3, obj.getClassName());
            preparedStatement.setInt(4, obj.getPeopleMax());
            preparedStatement.setString(5, obj.getClassContent());
            preparedStatement.setInt(6, obj.getClassStatus());
            preparedStatement.setInt(7, obj.getClassID());
            int rowCount = preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Integer pk) {
        try {
            Class.forName(CLASSNAME);
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE);) {
            preparedStatement.setInt(1, pk);
            int rowCount = preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public TeamClassVO get(Integer pk) {
        try {
            Class.forName(CLASSNAME);
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET);) {
            preparedStatement.setInt(1, pk);
            ResultSet resultSet = preparedStatement.executeQuery();
            TeamClassVO teamClassVO = new TeamClassVO();
            while (resultSet.next()) {
                teamClassVO.setClassID(resultSet.getInt(1));
                teamClassVO.setEmpID(resultSet.getInt(2));
                teamClassVO.setTypeID(resultSet.getInt(3));
                teamClassVO.setClassName(resultSet.getString(4));
                teamClassVO.setPeopleMax(resultSet.getInt(5));
                teamClassVO.setClassContent(resultSet.getString(6));
                teamClassVO.setClassStatus(resultSet.getInt(7));
            }
            return teamClassVO;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<TeamClassVO> getAll() {
        try {
            Class.forName(CLASSNAME);
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GETALL);) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<TeamClassVO> list = new ArrayList<>();
            while (resultSet.next()) {
                TeamClassVO teamClassVO = new TeamClassVO();
                teamClassVO.setClassID(resultSet.getInt(1));
                teamClassVO.setEmpID(resultSet.getInt(2));
                teamClassVO.setTypeID(resultSet.getInt(3));
                teamClassVO.setClassName(resultSet.getString(4));
                teamClassVO.setPeopleMax(resultSet.getInt(5));
                teamClassVO.setClassContent(resultSet.getString(6));
                teamClassVO.setClassStatus(resultSet.getInt(7));
                list.add(teamClassVO);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public List<TeamClassVO> getClass(Integer typeID) {
        try {
            Class.forName(CLASSNAME);
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GETONETYPE);) {
            preparedStatement.setInt(1, typeID);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<TeamClassVO> list = new ArrayList<>();
            while (resultSet.next()) {
                TeamClassVO teamClassVO = new TeamClassVO();
                teamClassVO.setClassID(resultSet.getInt(1));
                teamClassVO.setEmpID(resultSet.getInt(2));
                teamClassVO.setTypeID(resultSet.getInt(3));
                teamClassVO.setClassName(resultSet.getString(4));
                teamClassVO.setPeopleMax(resultSet.getInt(5));
                teamClassVO.setClassContent(resultSet.getString(6));
                teamClassVO.setClassStatus(resultSet.getInt(7));
                list.add(teamClassVO);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
