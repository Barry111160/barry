package com.musclebeach.room.model;

public class RoomVO implements java.io.Serializable {
    private Integer roomID;
    private String roomName;
    private String roomAddress;
    private String roomContent;
    private Integer roomPrice;
    private Integer roomStatus;
    private byte[] roomImg;

    public byte[] getRoomImg() {
        return roomImg;
    }

    public void setRoomImg(byte[] roomImg) {
        this.roomImg = roomImg;
    }

    public Integer getRoomID() {
        return roomID;
    }

    public void setRoomID(Integer roomID) {
        this.roomID = roomID;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getRoomAddress() {
        return roomAddress;
    }

    public void setRoomAddress(String roomAddress) {
        this.roomAddress = roomAddress;
    }

    public String getRoomContent() {
        return roomContent;
    }

    public void setRoomContent(String roomContent) {
        this.roomContent = roomContent;
    }

    public Integer getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(Integer roomPrice) {
        this.roomPrice = roomPrice;
    }

    public Integer getRoomStatus() {
        return roomStatus;
    }

    public void setRoomStatus(Integer roomStatus) {
        this.roomStatus = roomStatus;
    }


}
