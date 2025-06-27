package com.smartechgroup.e_commerce.service;

import java.util.List;

public interface GenericService<T> {
    T getById(Long id);
    List<T> getAll();
    T save(T t);
}
