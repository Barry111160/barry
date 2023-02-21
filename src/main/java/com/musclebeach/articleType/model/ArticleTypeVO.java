package com.musclebeach.articleType.model;

public class ArticleTypeVO implements java.io.Serializable {
    private Integer typeID;
    private String typeName;

    public Integer getTypeID() {
        return typeID;
    }

    public void setTypeID(Integer typeID) {
        this.typeID = typeID;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
