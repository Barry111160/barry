package com.musclebeach.empAccess.model;

import com.musclebeach.accessFunction.model.AccessFunctionService;
import com.musclebeach.accessFunction.model.AccessFunctionVO;
import com.musclebeach.common.util.ApplicationContextUtil;
import com.musclebeach.emp.model.EmpService;
import com.musclebeach.emp.model.EmpVO;
import org.springframework.context.ApplicationContext;

public class EmpAccessVO {
    private final ApplicationContext context = ApplicationContextUtil.getContext();
    private final AccessFunctionService accessFunctionService = context.getBean(AccessFunctionService.class);
    private final EmpService empService = context.getBean(EmpService.class);
    private Integer empID;
    private Integer fID;

    public Integer getEmpID() {
        return empID;
    }

    public void setEmpID(Integer empID) {
        this.empID = empID;
    }

    public Integer getfID() {
        return fID;
    }

    public void setfID(Integer fID) {
        this.fID = fID;
    }

    public AccessFunctionVO getFunctionVO() {
        return accessFunctionService.getOneFunction(fID);
    }

    public EmpVO getAdminVO() {
        return empService.getOneEmp(empID);
    }
}
