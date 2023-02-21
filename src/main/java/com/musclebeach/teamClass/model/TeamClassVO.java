package com.musclebeach.teamClass.model;

import java.io.Serializable;

public class TeamClassVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer classID;
    private Integer empID;
    private Integer typeID;
    private String className;
    private Integer peopleMax;
    private String classContent;
    private Integer classStatus;

    public TeamClassVO() {
    }

    public TeamClassVO(Integer classID, Integer empID, Integer typeID, String className, Integer peopleMax,
                       String classContent, Integer classStatus) {
        this.classID = classID;
        this.empID = empID;
        this.typeID = typeID;
        this.className = className;
        this.peopleMax = peopleMax;
        this.classContent = classContent;
        this.classStatus = classStatus;
    }

    public Integer getClassID() {
        return classID;
    }

    public void setClassID(Integer classID) {
        this.classID = classID;
    }

    public Integer getEmpID() {
        return empID;
    }

    public void setEmpID(Integer empID) {
        this.empID = empID;
    }

    public Integer getTypeID() {
        return typeID;
    }

    public void setTypeID(Integer typeID) {
        this.typeID = typeID;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Integer getPeopleMax() {
        return peopleMax;
    }

    public void setPeopleMax(Integer peopleMax) {
        this.peopleMax = peopleMax;
    }

    public String getClassContent() {
        return classContent;
    }

    public void setClassContent(String classContent) {
        this.classContent = classContent;
    }

    public Integer getClassStatus() {
        return classStatus;
    }

    public void setClassStatus(Integer classStatus) {
        this.classStatus = classStatus;
    }

    @Override
    public String toString() {
        return "TeamClassVO [classID=" + classID + ", empID=" + empID + ", typeID=" + typeID + ", className="
                + className + ", peopleMax=" + peopleMax + ", classContent=" + classContent + ", classStatus="
                + classStatus + "]";
    }

}
