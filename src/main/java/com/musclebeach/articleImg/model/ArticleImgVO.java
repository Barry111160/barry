package com.musclebeach.articleImg.model;

public class ArticleImgVO implements java.io.Serializable{
	private Integer imgID;
	private byte[] artImg;
	private Integer artID;

	public Integer getImgID() {
		return imgID;
	}
	public void setImgID(Integer imgID) {
		this.imgID = imgID;
	}
	public byte[] getArtImg() {
		return artImg;
	}
	public void setArtImg(byte[] artImg) {
		this.artImg = artImg;
	}
	public Integer getArtID() {
		return artID;
	}
	public void setArtID(Integer artID) {
		this.artID = artID;
	}



}
