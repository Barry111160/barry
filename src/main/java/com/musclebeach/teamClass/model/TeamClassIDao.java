package com.musclebeach.teamClass.model;

import java.util.List;

public interface TeamClassIDao {
    void insert(TeamClassVO obj);

    void update(TeamClassVO obj);

    void delete(Integer pk);

    TeamClassVO get(Integer pk);

    List<TeamClassVO> getClass(Integer typeID);

    List<TeamClassVO> getAll();
}
