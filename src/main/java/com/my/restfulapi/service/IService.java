package com.my.restfulapi.service;

import org.apache.ibatis.exceptions.TooManyResultsException;
import tk.mybatis.mapper.entity.Condition;

import java.util.List;

public interface IService<T> {
    /**
     * 插入
     * @param model
     * @return
     */
    int save(T model);

    /**
     * 批量插入
     * @param models
     */
    int batchSave(List<T> models);

    /**
     * 通过主鍵刪除
     *
     * @param id
     */
    int deleteById(Integer id);

    /**
     * 批量刪除
     * eg：ids -> “1,2,3,4”
     * @param ids
     */
    int deleteByIds(String ids);

    /**
     * 更新
     * @param model
     */
    int update(T model);

    /**
     * 通过ID查找
     * @param id
     * @return
     */
    T findById(Integer id);

    /**
     * 通过某个成员属性查找,value需符合unique约束
     *
     * @param property
     * @param value
     * @return
     * @throws TooManyResultsException
     */
    T findBy(String property, Object value) throws TooManyResultsException;

    /**
     * 通过多个ID查找
     * eg：ids -> “1,2,3,4”
     * @param ids
     * @return
     */
    List<T> findByIds(String ids);

    /**
     *
     * @param condition
     * @return
     */
    List<T> findByCondition(Condition condition);//根据条件查找

    /**
     * 获取所有
     * @return
     */
    List<T> findAll();
}
