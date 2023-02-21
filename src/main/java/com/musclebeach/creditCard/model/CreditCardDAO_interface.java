package com.musclebeach.creditCard.model;

import java.util.List;


public interface CreditCardDAO_interface {
    public void insert(CreditCardVO creditCardVO);

    public void update(CreditCardVO creditCardVO);

    public void delete(Integer ccID);

    public CreditCardVO findByPrimaryKey(Integer ccID);

    public List<CreditCardVO> getAll();
}
