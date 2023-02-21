package com.musclebeach.roomImg.model;

public class RoomImgVO implements java.io.Serializable {
    private Integer imgID;
    private Integer roomID;
    private byte[] roomImg;

    public Integer getImgID() {
        return imgID;
    }

    public void setImgID(Integer imgID) {
        this.imgID = imgID;
    }

    public Integer getRoomID() {
        return roomID;
    }

    public void setRoomID(Integer roomID) {
        this.roomID = roomID;
    }

    public byte[] getRoomImg() {
        return roomImg;
    }

    public void setRoomImg(byte[] roomImg) {
        this.roomImg = roomImg;
    }

}

