package com.musclebeach.roomTime.model;

import java.sql.Date;
import java.util.List;

public interface RoomTimeDAO_interface {
    void insert(RoomTimeVO roomTimeVO);

    void update(RoomTimeVO roomTimeVO);

    void delete(Integer timeID);

    RoomTimeVO findByPrimaryKey(Integer timeID);

    RoomTimeVO findByRoomAndDate(Integer roomID, Date roomDate);

    List<RoomTimeVO> getAll();

    Integer getDates(Date startTime, Date endTime);

    String findByBorrowDate(Integer roomID, Date borrowDate);

    void updateByOrder(String roomTime, Integer roomID, Date borrowDate);
}
