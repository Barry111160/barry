package com.musclebeach.roomOrder.model;

import com.musclebeach.common.util.ApplicationContextUtil;
import com.musclebeach.room.model.RoomService;
import com.musclebeach.room.model.RoomVO;
import org.springframework.context.ApplicationContext;

import java.sql.Date;
import java.sql.Timestamp;

public class RoomOrderVO implements java.io.Serializable {
    private final ApplicationContext context = ApplicationContextUtil.getContext();
    private final RoomService roomService = context.getBean(RoomService.class);
    private Integer orderID;
    private Integer memID;
    private Integer empID;
    private Integer roomID;
    private Timestamp createTime;
    private Timestamp updateTime;
    private Date borrowDate;
    private Integer borrowTime;
    private Integer orderStatus;

    public Integer getOrderID() {
        return orderID;
    }

    public void setOrderID(Integer orderID) {
        this.orderID = orderID;
    }

    public Integer getMemID() {
        return memID;
    }

    public void setMemID(Integer memID) {
        this.memID = memID;
    }

    public Integer getEmpID() {
        return empID;
    }

    public void setEmpID(Integer empID) {
        this.empID = empID;
    }

    public Integer getRoomID() {
        return roomID;
    }

    public void setRoomID(Integer roomID) {
        this.roomID = roomID;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp date) {
        this.createTime = date;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
    }

    public Integer getBorrowTime() {
        return borrowTime;
    }

    public void setBorrowTime(Integer borrowTime) {
        this.borrowTime = borrowTime;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    // for join empName from empID
//    public com.emp.model.EmpVO getEmpVO() {
//    	com.emp.model.EmployeeService employeeService = new com.emp.model.EmployeeService();
//    	com.emp.model.EmpVO empVO = employeeService.getOneEmp(empID);
//	    return empVO;
//    }

    // for join roomName from roomID
    public RoomVO getRoomVO() {
        return roomService.getOneRoom(roomID);
    }

}