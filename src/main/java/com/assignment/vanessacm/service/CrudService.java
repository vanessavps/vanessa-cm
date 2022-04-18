package com.assignment.vanessacm.service;

import com.assignment.vanessacm.utils.ObjectCopyUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public abstract class CrudService<T> implements Crud<T> {
    protected final JpaRepository<T, Integer> repository;

    public CrudService(JpaRepository<T, Integer> repository) {
        this.repository = repository;
    }

    @Override
    public T save(T obj) {
        return this.repository.save(obj);
    }

    public T findById(Integer id) {
        return this.repository.findById(id)
                              .orElse(null);
    }

    public List<T> findAll(Integer page, Integer elements) {
        if (page != null && elements != null) {
            Pageable pageWithElements = PageRequest.of(page, elements);
            return this.repository.findAll(pageWithElements)
                                  .getContent();
        }

        return this.repository.findAll();
    }

    public void deleteById(Integer id) {
        this.repository.deleteById(id);
    }

    public T update(Integer id, T obj) {
        T existentObj = this.findById(id);
        if (existentObj != null) {
            ObjectCopyUtils.copyNonNullProperties(obj, existentObj);
            return this.save(existentObj);
        }
        return null;
    }
}
