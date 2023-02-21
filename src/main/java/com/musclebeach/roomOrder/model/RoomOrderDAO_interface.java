package com.musclebeach.roomOrder.model;

import java.util.List;

public interface RoomOrderDAO_interface {
    void insert(RoomOrderVO roomOrderVO);

    void update(RoomOrderVO roomOrderVO);

    void delete(Integer orderID);

    RoomOrderVO findByPrimaryKey(Integer orderID);

    List<RoomOrderVO> findByMember(Integer memID);

    List<RoomOrderVO> findByEmployee(Integer memID);

    List<RoomOrderVO> getAll();

    List<RoomOrderVO> getAllOrder();

    List<RoomOrderVO> getPendingOrder();

    void confirmRoomOrder(Integer orderID, Integer employeeID);

    void cancelApplication(Integer orderID);

    void cancelRoomOrder(Integer orderId, Integer employeeId);

}
