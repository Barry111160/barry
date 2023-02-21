package com.musclebeach.backstage.service.impl;

import com.musclebeach.backstage.entity.Member;
import com.musclebeach.backstage.mapper.MemberMapper;
import com.musclebeach.backstage.service.MemberService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("courseMember")
public class MemberServiceImpl implements MemberService {
    @Resource
    private MemberMapper mapper;

    @Override
    public Member getMemberInfo(Integer memID) {
        return mapper.selectMemNameByID(memID);
    }

    @Override
    public Boolean updateMemberAccess(Integer memID) {
        Member member = mapper.selectMemberByID(memID);
        return mapper.updateMemberAccess(member) > 0;
    }
}
