package com.musclebeach.mem.model;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Date;
import java.util.List;

@Service
public class MemService {
    @Resource
    private MemDAO_interface dao;

    public MemVO addMem(String memName, String account, String password, String memPhone, Date memBirthday,
                        String memAddress, String memMail, Integer memStatus, Integer memAccess) {

        MemVO memVO = new MemVO();

        memVO.setMemName(memName);
        memVO.setAccount(account);
        memVO.setPassword(password);
        memVO.setMemPhone(memPhone);
        memVO.setMemBirthday(memBirthday);
        memVO.setMemAddress(memAddress);
        memVO.setMemMail(memMail);
        memVO.setMemStatus(memStatus);
        memVO.setMemAccess(memAccess);
        dao.insert(memVO);

        return memVO;
    }

    public MemVO updateMem(Integer memID, String memName, String account,
                           String memPhone, String memAddress, String memMail) {

        MemVO memVO = new MemVO();

        memVO.setMemID(memID);
        memVO.setMemName(memName);
        memVO.setAccount(account);
        memVO.setMemPhone(memPhone);
        memVO.setMemAddress(memAddress);
        memVO.setMemMail(memMail);
        dao.update(memVO);

        return memVO;
    }

    public void updateMembership(Integer memID) {
        dao.updateMembership(memID);
    }

    public void deleteMem(Integer memID) {
        dao.delete(memID);
    }

    public MemVO getOneMem(Integer memID) {
        return dao.findByPrimaryKey(memID);
    }

    public List<MemVO> getByName(String memName) {
        return dao.findByName(memName);
    }

    public MemVO getByPhone(String memPhone) {
        return dao.findByPhone(memPhone);
    }

    public MemVO getByBirth(Date memBirthday) {
        return dao.findByBirthday(memBirthday);
    }

    public MemVO getAccount(String account) {
        return dao.checkAccount(account);
    }

    public List<MemVO> getAll() {
        return dao.getAll();
    }

    public Boolean updatePassWord(Integer memberId, String newPassWord) {
        return dao.updatePassWord(memberId, newPassWord);
    }

    public void updateMemAccess(Integer memID) {
        dao.updateMemAccess(memID);
    }

    public void updateMemStatus(Integer memID) {
        dao.updateMemStatus(memID);
    }
}
