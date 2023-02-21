package com.musclebeach.empAccess.controller;

import com.musclebeach.accessFunction.model.AccessFunctionService;
import com.musclebeach.common.util.ApplicationContextUtil;
import com.musclebeach.empAccess.model.EmpAccessService;
import com.musclebeach.empAccess.model.EmpAccessVO;
import org.springframework.context.ApplicationContext;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@WebServlet("/back-end/empAccess/empAccess.do")
public class EmpAccessServlet extends HttpServlet {
    private final ApplicationContext context = ApplicationContextUtil.getContext();
    private final EmpAccessService empAccessSvc = context.getBean(EmpAccessService.class);
    private final AccessFunctionService functionSvc = context.getBean(AccessFunctionService.class);
    private final EmpAccessService accessSvc = context.getBean(EmpAccessService.class);

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doPost(req, res);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");

        if ("getName_For_Display".equals(action)) {
            Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
            req.setAttribute("errorMsgs", errorMsgs);

            try {
                String empName = req.getParameter("empName");
                if (empName.equals("0")) {
                    errorMsgs.put("empName", "請選擇員工姓名");
                }
                if (!errorMsgs.isEmpty()) {
                    RequestDispatcher failureView = req.getRequestDispatcher("/back-end/empAccess/select_page1.jsp");
                    failureView.forward(req, res);
                    return;
                }

                RequestDispatcher successView = req.getRequestDispatcher("/back-end/empAccess/listNameAuthority.jsp");
                successView.forward(req, res);
            } catch (Exception e) {
                errorMsgs.put("Exception", e.getMessage());
                RequestDispatcher failureView = req.getRequestDispatcher("/back-end/empAccess/select_page1.jsp");
                failureView.forward(req, res);
            }

        }

        if ("getFunction_For_Display".equals(action)) {
            Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
            req.setAttribute("errorMsgs", errorMsgs);

            try {
                String fID = req.getParameter("fID");
                if (fID.equals("0")) {
                    errorMsgs.put("fID", "請選擇權限名稱");
                }
                if (!errorMsgs.isEmpty()) {
                    RequestDispatcher failureView = req.getRequestDispatcher("/back-end/empAccess/select_page1.jsp");
                    failureView.forward(req, res);
                    return;
                }

                RequestDispatcher successView = req.getRequestDispatcher("/back-end/empAccess/listFx_noAuthority.jsp");
                successView.forward(req, res);
            } catch (Exception e) {
                errorMsgs.put("Exception", e.getMessage());
                RequestDispatcher failureView = req.getRequestDispatcher("/back-end/empAccess/select_page1.jsp");
                failureView.forward(req, res);
            }
        }

        if ("getOne_For_Update".equals(action)) {
            Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
            req.setAttribute("errorMsgs", errorMsgs);

            try {
                Integer empID = Integer.valueOf(req.getParameter("empID"));

                List<EmpAccessVO> list = empAccessSvc.getEmp(empID);
                req.setAttribute("list", list);

                RequestDispatcher successView = req
                        .getRequestDispatcher("/back-end/empAccess/update_Access_input1.jsp");
                successView.forward(req, res);

            } catch (Exception e) {
                errorMsgs.put("Exception", e.getMessage());
                RequestDispatcher failureView = req.getRequestDispatcher("/back-end/empAccess/listAllAuthority.jsp");
                failureView.forward(req, res);
            }
        }

        if ("update".equals(action)) {
            Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
            req.setAttribute("errorMsgs", errorMsgs);


            try {
                Integer empID = Integer.valueOf(req.getParameter("empID"));
                Set<Integer> fIDSet = new LinkedHashSet<Integer>();
                for (int i = 1; i <= functionSvc.getAll().size(); i++) {
                    if (req.getParameter("fID" + i) == null) {
                        continue;
                    } else {
                        fIDSet.add(Integer.valueOf(req.getParameter("fID" + i)));
                    }
                }

                List<EmpAccessVO> accessVOlist = accessSvc.getEmp(empID);
                Set<Integer> fIDOriginSet = new LinkedHashSet<Integer>();
                for (EmpAccessVO accessVO : accessVOlist) {
                    fIDOriginSet.add(accessVO.getfID());
                }
                for (Integer fIDOrigin : fIDOriginSet) {
                    if (!fIDSet.contains(fIDOrigin)) {
                        accessSvc.deleteEmpAccess(empID, fIDOrigin);
                    }
                }
                for (Integer fID : fIDSet) {
                    if (!fIDOriginSet.contains(fID)) {
                        accessSvc.addEmpAccess(empID, fID);
                    }
                }

                RequestDispatcher successView = req.getRequestDispatcher("/back-end/empAccess/listAllAuthority.jsp");
                successView.forward(req, res);
            } catch (Exception e) {
                errorMsgs.put("Exception", e.getMessage());
                RequestDispatcher failureView = req.getRequestDispatcher("/back-end/empAccess/listAllAuthority.jsp");
                failureView.forward(req, res);
            }
        }

//		if ("getEmpID_For_Display".equals(action)) {
//
//			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			/*************************** 1.��隢�� - 頛詨�撘�隤方��� **********************/
//			String str = req.getParameter("empID"); 
//			Integer empID = Integer.valueOf(str);
//			/*************************** 2.���閰Ｚ��� *****************************************/
//			EmpAccessService empAccessService = new EmpAccessService();
//			List<EmpAccessVO> empAccessVO = empAccessService.getEmp(empID);
//			
//			if (empAccessVO.isEmpty()) {
//				errorMsgs.put("empID","甇文撌亦隞颱����");
//			}
//			if (!errorMsgs.isEmpty()) {
//				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/empFunc/listAllEmpFunc.jsp");
//				failureView.forward(req, res);
//				return;
//			}
//
//			/*************************** 3.�閰Ｗ���,皞��漱(Send the Success view) *************/
//			req.setAttribute("empAccessVO", empAccessVO); 
//			String url = "/back_end/empFunc/listOneEmpFunc.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url); 
//			successView.forward(req, res);
//		}
//		
//		if ("getFuncID_For_Display".equals(action)) {
//
//			/*************************** 1.��隢�� - 頛詨�撘�隤方��� **********************/
//			String str = req.getParameter("fID"); 
//			Integer fID = Integer.valueOf(str);
//			/*************************** 2.���閰Ｚ��� *****************************************/
//			EmpAccessService empAccessService = new EmpAccessService();
//			List<EmpAccessVO> empAccessVO = empAccessService.getFno(fID);
//			/*************************** 3.�閰Ｗ���,皞��漱(Send the Success view) *************/
//			req.setAttribute("empAccessVO", empAccessVO); 
//			String url = "/back_end/empFunc/listOneEmpFunc.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url); 
//			successView.forward(req, res);
//		}
//		
//		if ("delete".equals(action)){
//			String str = req.getParameter("empID"); 
//			Integer empID = Integer.valueOf(str);
//			String str2 = req.getParameter("fID"); 
//			Integer fID = Integer.valueOf(str2);
//			
//			EmpAccessService empAccessService = new EmpAccessService();
//			empAccessService.deleteEmpAccess(empID, fID);
//			
//			String url = "/back_end/empFunc/listAllEmpFunc.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url); 
//			successView.forward(req, res);
//		}
//		
//		if("insert".equals(action)){
//			List<String> errorMsgs = new LinkedList<String>();
//			req.setAttribute("errorMsgs", errorMsgs);
//			
//			String str1 = req.getParameter("empID"); 
//			Integer empID = Integer.parseInt(str1);
//			String str2 = req.getParameter("fID"); 
//			Integer fID = Integer.valueOf(str2);
//			
//			// 閬隤方���閬憓�����撌脩�����
//			EmpAccessService empAccessService = new EmpAccessService();
//			List<EmpAccessVO> list = empAccessService.getEmp(empID);
//			for(EmpAccessVO empAccessVO : list) {
//				if(empAccessVO.getfID() == fID) {
//					errorMsgs.add("甇文撌亙歇��府甈��"); 
//				}
//			}
//			if (!errorMsgs.isEmpty()) {
//				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/empFunc/addEmpFunc.jsp");
//				failureView.forward(req, res);
//				return;
//			}
//			
//			// �瘝�隤歹�憓�
//			empAccessService.addEmpAccess(empID, fID);
//			// ���撌亦楊��閰Ｖ�甈∴�蒂銝策���憿舐內
//			List<EmpAccessVO> list2 = empAccessService.getEmp(empID);
//			req.setAttribute("list2", list2);
//			
//			String url = "/back_end/empFunc/listOneEmpFunc.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url); 
//			successView.forward(req, res);
//		}

    }
}
