package com.my.restfulapi.common.dao.impl;

import com.my.restfulapi.common.dao.IBaseDao;
import com.my.restfulapi.common.mapper.MyMapper;
import org.apache.ibatis.exceptions.TooManyResultsException;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Condition;

import java.util.List;

public abstract class BaseDao<T> implements IBaseDao<T> {

    @Autowired
    private MyMapper<T> myMapper;

    @Override
    public int save(T model) {
        return myMapper.insertSelective(model);
    }

    @Override
    public int batchSave(List<T> models) {
        return myMapper.insertList(models);
    }

    @Override
    public int deleteById(Integer id) {
        return myMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int deleteByIds(String ids) {
        return myMapper.deleteByIds(ids);
    }

    @Override
    public int update(T model) {
        return myMapper.updateByPrimaryKeySelective(model);
    }

    @Override
    public T findById(Integer id) {
        return (T) myMapper.selectByPrimaryKey(id);
    }

    @Override
    public T findBy(String property, Object value) throws TooManyResultsException {
        return null;
    }

    @Override
    public List<T> findByIds(String ids) {
        return myMapper.selectByIds(ids);
    }

    @Override
    public List<T> findByCondition(Condition condition) {
        return myMapper.selectByCondition(condition);
    }

    @Override
    public List<T> findAll() {
        return myMapper.selectAll();
    }
}
