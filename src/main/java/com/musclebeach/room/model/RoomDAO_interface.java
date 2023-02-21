package com.musclebeach.room.model;

import java.util.List;

public interface RoomDAO_interface {
    void insert(RoomVO roomVO);

    void update(RoomVO roomVO);

    void delete(Integer roomID);

    RoomVO findByPrimaryKey(Integer roomID);

    List<RoomVO> getAll();

    List<RoomVO> getAllIncZ();
}
