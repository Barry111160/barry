package com.musclebeach.course.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.musclebeach.backstage.entity.Member;
import lombok.Data;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class AbsentMember implements Serializable {
    private ClassSchedule classSchedule;
    private Member member;
}
