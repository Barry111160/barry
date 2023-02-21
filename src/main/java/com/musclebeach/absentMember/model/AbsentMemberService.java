package com.musclebeach.absentMember.model;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AbsentMemberService {
    @Resource
    private AbsentMemberIDao dao;

    public AbsentMemberVO addAbsentMember(Integer timeID, Integer memID) {
        AbsentMemberVO vo = new AbsentMemberVO();
        vo.setTimeID(timeID);
        vo.setMemID(memID);
        dao.insert(vo);
        return vo;
    }

    public AbsentMemberVO updateAbsentMember(Integer timeID, Integer memID) {
        AbsentMemberVO vo = new AbsentMemberVO();
        vo.setTimeID(timeID);
        vo.setMemID(memID);
        dao.update(vo);
        return vo;
    }

    public void deleteAbsentMember(Integer timeID, Integer memID) {
        AbsentMemberVO vo = new AbsentMemberVO();
        vo.setMemID(memID);
        vo.setTimeID(timeID);
        dao.delete(vo);

    }

    public AbsentMemberVO getOne(Integer timeID, Integer memID) {
        AbsentMemberVO vo = new AbsentMemberVO();
        vo.setMemID(memID);
        vo.setTimeID(timeID);
        dao.get(vo);
        return vo;
    }

    public AbsentMemberVO getAbsentCount(Integer classID, Integer memID) {

        return dao.getAbsentCount(classID, memID);

    }

    public void suspendMembership(Integer memID) {

        dao.suspendMembership(memID);

    }

    public List<AbsentMemberVO> getAll() {
        return dao.getAll();
    }

}
