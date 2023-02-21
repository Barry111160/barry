package com.musclebeach.mem.model;

import java.sql.Date;
import java.util.List;

public interface MemDAO_interface {
    void insert(MemVO memVO);

    void update(MemVO memVO);

    void delete(Integer memID);

    MemVO findByPrimaryKey(Integer memID);

    List<MemVO> findByName(String memName);

    MemVO findByPhone(String memPhone);

    MemVO findByBirthday(Date memBirthday);

    MemVO checkAccount(String account);

    List<MemVO> getAll();

    Boolean updatePassWord(Integer memberId, String newPassWord);

    void updateMemAccess(Integer memID);

    void updateMembership(Integer memID);

    void updateMemStatus(Integer memID);
}
