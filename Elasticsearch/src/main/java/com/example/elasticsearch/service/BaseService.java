package com.example.elasticsearch.service;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * @Author viper
 * @Date 2023/3/9 下午 02:22
 * @Version 1.0
 */
@Service
public abstract class BaseService<T, M extends BaseMapper<T>> {
    @Autowired
    protected M baseMapper;
    
    public boolean insert(T entity) {
        return baseMapper.insert(entity) > 0;
    }
    
    public boolean deleteById(Serializable id) {
        return baseMapper.deleteById(id) > 0;
    }
    
    public List<T> getAll(){
        return baseMapper.selectList(null);
    }
    
}