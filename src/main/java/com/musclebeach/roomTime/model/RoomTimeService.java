package com.musclebeach.roomTime.model;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Date;
import java.util.List;

@Service
public class RoomTimeService {
    @Resource
    private RoomTimeDAO_interface dao;


    public RoomTimeVO addRoomTime(Integer roomID, java.sql.Date roomDate, String roomTime) {

        RoomTimeVO roomTimeVO = new RoomTimeVO();

        roomTimeVO.setRoomID(roomID);
        roomTimeVO.setRoomDate(roomDate);
        roomTimeVO.setRoomTime(roomTime);
        dao.insert(roomTimeVO);

        return roomTimeVO;
    }

    public RoomTimeVO updateRoomTime(Integer timeID, Integer roomID, java.sql.Date roomDate, String roomTime) {

        RoomTimeVO roomTimeVO = new RoomTimeVO();

        roomTimeVO.setTimeID(timeID);
        roomTimeVO.setRoomID(roomID);
        roomTimeVO.setRoomDate(roomDate);
        roomTimeVO.setRoomTime(roomTime);
        dao.update(roomTimeVO);

        return roomTimeVO;
    }

    public void deleteRoomTime(Integer timeID) {
        dao.delete(timeID);
    }

    public RoomTimeVO getOneRoomeTime(Integer timeID) {
        return dao.findByPrimaryKey(timeID);
    }

    public RoomTimeVO getOneByRoomAndDate(Integer roomID, Date roomDate) {
        return dao.findByRoomAndDate(roomID, roomDate);
    }

    public List<RoomTimeVO> getAll() {
        return dao.getAll();
    }

    public Integer getDates(Date startTime, Date endTime) {
        return dao.getDates(startTime, endTime);
    }

    public String findByBorrowDate(Integer roomID, Date borrowDate) {
        return dao.findByBorrowDate(roomID, borrowDate);
    }

    public void updateByOrder(String roomTime, Integer roomID, Date borrowDate) {
        dao.updateByOrder(roomTime, roomID, borrowDate);

    }
}
