package com.musclebeach.room.model;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RoomService {
    @Resource
    private RoomDAO_interface dao;

    public RoomVO addRoom(String roomName, String roomAddress, String roomContent,
                          Integer roomPrice, Integer roomStatus, byte[] roomImg) {

        RoomVO roomVO = new RoomVO();

        roomVO.setRoomName(roomName);
        roomVO.setRoomAddress(roomAddress);
        roomVO.setRoomContent(roomContent);
        roomVO.setRoomPrice(roomPrice);
        roomVO.setRoomStatus(roomStatus);
        roomVO.setRoomImg(roomImg);

        dao.insert(roomVO);

        return roomVO;
    }

    public RoomVO updateRoom(Integer roomID, String roomName, String roomAddress, String roomContent,
                             Integer roomPrice, Integer roomStatus, byte[] roomImg) {

        RoomVO roomVO = new RoomVO();

        roomVO.setRoomID(roomID);
        roomVO.setRoomName(roomName);
        roomVO.setRoomAddress(roomAddress);
        roomVO.setRoomContent(roomContent);
        roomVO.setRoomPrice(roomPrice);
        roomVO.setRoomStatus(roomStatus);
        roomVO.setRoomImg(roomImg);
        dao.update(roomVO);

        return roomVO;
    }

    public void deleteRoom(Integer roomID) {
        dao.delete(roomID);
    }

    public RoomVO getOneRoom(Integer roomID) {
        return dao.findByPrimaryKey(roomID);
    }

    public List<RoomVO> getAllIncZ() {
        return dao.getAllIncZ();
    }

    public List<RoomVO> getAll() {
        return dao.getAll();
    }

}
