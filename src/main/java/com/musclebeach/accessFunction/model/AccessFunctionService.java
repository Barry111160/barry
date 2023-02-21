package com.musclebeach.accessFunction.model;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AccessFunctionService {
    @Resource
    private AccessFunctionDAO_interface dao;

    public AccessFunctionVO addEmpAccess(Integer FID, String FName) {

        AccessFunctionVO accessFunctionVO = new AccessFunctionVO();

        accessFunctionVO.setfID(FID);
        accessFunctionVO.setfName(FName);
        dao.insert(accessFunctionVO);

        return accessFunctionVO;
    }

    public void deleteEmpAccess(Integer FID) {
        dao.delete(FID);
    }

    public List<AccessFunctionVO> getAll() {
        return dao.getAll();
    }

    public AccessFunctionVO getOneFunction(Integer FID) {
        return dao.findByPrimaryKey(FID);
    }
}
