package com.musclebeach.creditCard.model;


import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CreditCardService {
    @Resource
    private CreditCardDAO_interface dao;

    public CreditCardVO addCard(Integer memID, String ccNumbe, String ccName, String ccTime, String ccvc) {

        CreditCardVO vo = new CreditCardVO();
        vo.setMemID(memID);
        vo.setCcNumber(ccNumbe);
        vo.setCcName(ccName);
        vo.setCcTime(ccTime);
        vo.setCcvc(ccvc);
        dao.insert(vo);
        return vo;
    }

    public CreditCardVO updateCard(Integer ccID, Integer memID, String ccNumbe, String ccName, String ccTime,
                                   String ccvc) {

        CreditCardVO vo = new CreditCardVO();
        vo.setCcID(ccID);
        vo.setMemID(memID);
        vo.setCcNumber(ccNumbe);
        vo.setCcName(ccName);
        vo.setCcTime(ccTime);
        vo.setCcvc(ccvc);
        dao.update(vo);
        return vo;

    }

    public CreditCardVO deleteCard(Integer ccID) {
        dao.delete(ccID);
        return null;
    }

    public CreditCardVO getOneCard(Integer ccID) {
        return dao.findByPrimaryKey(ccID);
    }

    public List<CreditCardVO> getAll() {
        return dao.getAll();
    }
}
