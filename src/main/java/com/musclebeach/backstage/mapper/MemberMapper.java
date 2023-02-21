package com.musclebeach.backstage.mapper;

import com.musclebeach.backstage.entity.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MemberMapper {
    @Select("select mem_id, mem_name, mem_status, mem_access from member where mem_id = #{memID}")
    Member selectMemNameByID(Integer memID);

    @Select("select * from member where  mem_id = #{memID}")
    Member selectMemberByID(Integer memID);

    Integer updateMemberAccess(Member member);
}
