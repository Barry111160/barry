package com.musclebeach.cart.mapper;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Map;

@Repository
public class CartMapper {
    @Resource
    HashOperations<Integer, Integer, Integer> operations;

    public void change(Integer memID, Integer proID, Integer count) {
        operations.put(memID, proID, count);
    }

    public void delete(Integer memID, Integer proID) {
        operations.delete(memID, proID);

    }

    public void deleteAll(Integer memID) {
        Map<Integer, Integer> map = selectAllByID(memID);
        map.forEach((k, v) -> {
            delete(memID, k);
        });
    }

    public Map<Integer, Integer> selectAllByID(Integer memID) {
        return operations.entries(memID);
    }
}
