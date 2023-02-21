package com.musclebeach.roomImg.model;

import java.util.List;

public interface RoomImgDAO_interface {
    void insert(RoomImgVO roomImgVO);

    void update(RoomImgVO roomImgVO);

    void delete(Integer imgID);

    RoomImgVO findByPrimaryKey(Integer imgID);

    List<RoomImgVO> getAll();

}
