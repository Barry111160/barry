package com.musclebeach.absentMember.model;

import java.util.List;

public interface AbsentMemberIDao {
    void insert(AbsentMemberVO obj);

    void update(AbsentMemberVO obj);

    void delete(AbsentMemberVO obj);

    void suspendMembership(Integer memID);

    AbsentMemberVO getAbsentCount(Integer classID, Integer memID);

    AbsentMemberVO get(AbsentMemberVO obj);

    List<AbsentMemberVO> getAll();

//	List<AbsentMemberVO> getOneMemAbsentList();
}
