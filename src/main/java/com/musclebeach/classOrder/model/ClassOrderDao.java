package com.musclebeach.classOrder.model;

import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ClassOrderDao implements ClassOrderIDao {
    public final static String CLASSNAME = "com.mysql.cj.jdbc.Driver";
    private static final String INSERT = "INSERT INTO class_order (emp_id, mem_id, class_id, order_status) VALUE (?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE class_order SET emp_id = ?, mem_id = ?, class_id = ?, order_status = ? WHERE order_id = ?";
    private static final String DELETE = "DELETE FROM class_order WHERE order_id = ?";
    private static final String GET = "SELECT * FROM class_order WHERE order_id = ?";
    private static final String GETALL = "SELECT * FROM class_order";
    @Resource
    private DataSource dataSource;

    @Override
    public void insert(ClassOrderVO obj) {
        try {
            Class.forName(CLASSNAME);
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT);) {
            preparedStatement.setInt(1, obj.getEmpID());
            preparedStatement.setInt(2, obj.getMemID());
            preparedStatement.setInt(3, obj.getClassID());
            preparedStatement.setInt(4, obj.getOrderStatus());
            int rowCount = preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(ClassOrderVO obj) {
        try {
            Class.forName(CLASSNAME);
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);) {
            preparedStatement.setInt(1, obj.getEmpID());
            preparedStatement.setInt(2, obj.getMemID());
            preparedStatement.setInt(3, obj.getClassID());
            preparedStatement.setInt(4, obj.getOrderStatus());
            preparedStatement.setInt(5, obj.getOrderID());
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
    public ClassOrderVO get(Integer pk) {
        try {
            Class.forName(CLASSNAME);
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET);) {
            preparedStatement.setInt(1, pk);
            ResultSet resultSet = preparedStatement.executeQuery();
            ClassOrderVO classOrderVO = new ClassOrderVO();
            while (resultSet.next()) {
                classOrderVO.setOrderID(resultSet.getInt("order_id"));
                classOrderVO.setMemID(resultSet.getInt("mem_id"));
                classOrderVO.setEmpID(resultSet.getInt("emp_id"));
                classOrderVO.setClassID(resultSet.getInt("class_id"));
                classOrderVO.setOrderStatus(resultSet.getInt("order_status"));
                classOrderVO.setCreateTime(resultSet.getTimestamp("create_time"));
                classOrderVO.setUpdateTime(resultSet.getTimestamp("update_time"));
            }
            return classOrderVO;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<ClassOrderVO> getAll() {
        try {
            Class.forName(CLASSNAME);
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GETALL);) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<ClassOrderVO> list = new ArrayList<>();
            while (resultSet.next()) {
                ClassOrderVO classOrderVO = new ClassOrderVO();
                classOrderVO.setOrderID(resultSet.getInt("order_id"));
                classOrderVO.setMemID(resultSet.getInt("mem_id"));
                classOrderVO.setEmpID(resultSet.getInt("emp_id"));
                classOrderVO.setClassID(resultSet.getInt("class_id"));
                classOrderVO.setOrderStatus(resultSet.getInt("order_status"));
                classOrderVO.setCreateTime(resultSet.getTimestamp("create_time"));
                classOrderVO.setUpdateTime(resultSet.getTimestamp("update_time"));
                list.add(classOrderVO);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
