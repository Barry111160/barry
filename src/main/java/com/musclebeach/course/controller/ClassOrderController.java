package com.musclebeach.course.controller;

import com.musclebeach.common.controller.Code;
import com.musclebeach.common.controller.Result;
import com.musclebeach.course.entity.ClassOrder;
import com.musclebeach.course.service.ClassOrderService;
import com.musclebeach.mem.model.MemVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/classOrders")
public class ClassOrderController {
    @Resource
    private ClassOrderService service;

    @PostMapping
    public Result insert(@RequestBody ClassOrder classOrder) {
        Boolean insert = service.insert(classOrder);
        Integer code = insert ? Code.CREATE_OK : Code.CREATE_ERR;
        String msg = insert ? "新增成功" : "新增失敗";
        return new Result(code, insert, msg);
    }

    @PostMapping("/reservation")
    public Result reserve(@RequestBody ClassOrder classOrder) {
        Boolean reserve = service.reserve(classOrder);
        Integer code = reserve ? Code.CREATE_OK : Code.CREATE_ERR;
        String msg = reserve ? "恭喜你，預約成功!" : "報名失敗，已經額滿或你已預約啦!";
        return new Result(code, reserve, msg);
    }

    @GetMapping
    public Result selectAll() {
        List<ClassOrder> classOrders = service.selectAll();
        Integer code = !classOrders.isEmpty() ? Code.READ_OK : Code.READ_ERR;
        String msg = !classOrders.isEmpty() ? "成功" : "查無此項目";
        return new Result(code, classOrders, msg);
    }

    @GetMapping("/{orderID}")
    public Result selectByID(@PathVariable Integer orderID) {
        ClassOrder classOrder = service.selectByID(orderID);
        Integer code = classOrder != null ? Code.READ_OK : Code.READ_ERR;
        String msg = classOrder != null ? "成功" : "查無此項目";
        return new Result(code, classOrder, msg);
    }

    // 個人課程管理-查看個人已預約課程課表
    @GetMapping("/memID")
    public Result selectByMemID(HttpServletRequest request) {
        MemVO memVO = (MemVO) request.getSession().getAttribute("memVO");
        Integer memID = -1;
        if (memVO != null) {
            memID = memVO.getMemID();
        }
        List<ClassOrder> classOrders = service.selectByMemID(memID);
        Integer code = !classOrders.isEmpty() ? Code.READ_OK : Code.READ_ERR;
        String msg = !classOrders.isEmpty() ? "成功" : "查無此項目";
        return new Result(code, classOrders, msg);
    }

    @GetMapping("/classID/{classID}")
    public Result countByClassID(@PathVariable Integer classID) {
        Integer count = service.countByClassID(classID);
        Integer code = count != null ? Code.READ_OK : Code.READ_ERR;
        String msg = count != null ? "成功" : "查無此項目";
        return new Result(code, count, msg);
    }

    @PutMapping
    public Result update(@RequestBody ClassOrder classOrder) {
        Boolean update = service.update(classOrder);
        Integer code = update ? Code.UPDATE_OK : Code.UPDATE_ERR;
        String msg = update ? "成功" : "修改失敗";
        return new Result(code, update, msg);
    }

    @PutMapping("/cancel")
    public Result cancel(@RequestBody ClassOrder classOrder) {
        Boolean cancel = service.cancel(classOrder);
        Integer code = cancel ? Code.UPDATE_OK : Code.UPDATE_ERR;
        String msg = cancel ? "取消成功" : "取消失敗";
        return new Result(code, cancel, msg);
    }
}
