package com.musclebeach.roomOrder.model;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RoomOrderService {

    @Resource
    private RoomOrderDAO_interface dao;

    public RoomOrderVO addOrder(Integer memID, Integer roomID, java.sql.Date borrowDate,
                                Integer borrowTime, Integer orderStatus) {

        RoomOrderVO roomOrderVO = new RoomOrderVO();

        roomOrderVO.setMemID(memID);
        roomOrderVO.setRoomID(roomID);
        roomOrderVO.setBorrowDate(borrowDate);
        roomOrderVO.setBorrowTime(borrowTime);
        roomOrderVO.setOrderStatus(orderStatus);
        dao.insert(roomOrderVO);

        return roomOrderVO;
    }

    public RoomOrderVO updateOrder(Integer orderID, Integer memID, Integer empID, Integer roomID, java.sql.Timestamp createTime,
                                   java.sql.Timestamp updateTime, java.sql.Date borrowDate, Integer borrowTime, Integer orderStatus) {

        RoomOrderVO roomOrderVO = new RoomOrderVO();

        roomOrderVO.setOrderID(orderID);
        roomOrderVO.setMemID(memID);
        roomOrderVO.setEmpID(empID);
        roomOrderVO.setRoomID(roomID);
        roomOrderVO.setCreateTime(createTime);
        roomOrderVO.setUpdateTime(updateTime);
        roomOrderVO.setBorrowDate(borrowDate);
        roomOrderVO.setBorrowTime(borrowTime);
        roomOrderVO.setOrderStatus(orderStatus);
        dao.update(roomOrderVO);

        return roomOrderVO;
    }

    public void deleteOrder(Integer orderID) {
        dao.delete(orderID);
    }

    public RoomOrderVO getOneOrder(Integer orderID) {
        return dao.findByPrimaryKey(orderID);
    }

    public List<RoomOrderVO> getByMem(Integer memID) {
        return dao.findByMember(memID);
    }

    public List<RoomOrderVO> getByEmp(Integer empID) {
        return dao.findByEmployee(empID);
    }

    public List<RoomOrderVO> getAll() {
        return dao.getAll();
    }

    public List<RoomOrderVO> getPendingOrder() {
        return dao.getPendingOrder();
    }

    public void confirmRoomOrder(Integer orderID, Integer employeeID) {
        dao.confirmRoomOrder(orderID, employeeID);
    }

    public void cancelApplication(Integer orderID) {
        dao.cancelApplication(orderID);
    }

    public void cancelRoomOrder(Integer orderId, Integer employeeId) {
        dao.cancelRoomOrder(orderId, employeeId);
    }
}
