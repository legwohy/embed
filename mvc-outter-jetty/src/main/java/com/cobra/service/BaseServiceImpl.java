package com.cobra.service;

import com.cobra.util.BaseDao;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class BaseServiceImpl<T> implements BaseService<T> {
    
    private static Logger logger = LoggerFactory.getLogger(BaseServiceImpl.class);
    
    private BaseDao<T> baseDao;

    public void setBaseDao(BaseDao<T> baseDao) {
        this.baseDao = baseDao;
    }

    @Override
    public int insert(T record) {
        insertModelInitial(record);
        return baseDao.insert(record);
    }

    @Override
    public int insertSelective(T record) {
        // TODO Auto-generated method stub
        insertModelInitial(record);
        return baseDao.insertSelective(record);
    }

    @Override
    public int insertList(List<T> recordList) {
        // TODO Auto-generated method stub
        return baseDao.insertList(recordList);
    }

    @Override
    public int insertUseGeneratedKeys(T record) {
        // TODO Auto-generated method stub
        insertModelInitial(record);
        return baseDao.insertUseGeneratedKeys(record);
    }

    @Override
    public int updateByPrimaryKey(T record) {
        // TODO Auto-generated method stub
        updateModelInitial(record);
        return baseDao.updateByPrimaryKey(record);
    }

    @Override
    public int updateByPrimaryKeySelective(T record) {
        // TODO Auto-generated method stub
        updateModelInitial(record);
        return baseDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public List<T> selectAll() {
        // TODO Auto-generated method stub
        return baseDao.selectAll();
    }

    @Override
    public List<T> select(T record) {
        // TODO Auto-generated method stub
        return baseDao.select(record);
    }

    @Override
    public T selectByPrimaryKey(Object key) {
        // TODO Auto-generated method stub
        return baseDao.selectByPrimaryKey(key);
    }

    @Override
    public int selectCount(T record) {
        // TODO Auto-generated method stub
        return baseDao.selectCount(record);
    }

    @Override
    public T selectOne(T record) {
        // TODO Auto-generated method stub
        return baseDao.selectOne(record);
    }

    @Override
    public int deleteByPrimaryKey(Object key) {
        // TODO Auto-generated method stub
        return baseDao.deleteByPrimaryKey(key);
    }

    @Override
    public int delete(T record) {
        // TODO Auto-generated method stub
        return baseDao.delete(record);
    }
    
    private void updateModelInitial(T record)
    {
        try
        {
            Date now = new Date(System.currentTimeMillis());
            Class<?> clazz = record.getClass();
            Method getUpdateTime = clazz.getMethod("getUpdateTime");
            Method setUpdateTime = clazz.getMethod("setUpdateTime", getUpdateTime.getReturnType());
            setUpdateTime.invoke(record, now);
            // Method getLastUpdateBy = clazz.getMethod("getLastUpdateBy");
            // Method setLastUpdateBy = clazz.getMethod("setLastUpdateBy",
            // getLastUpdateBy.getReturnType());
            // setLastUpdateBy.invoke(record, UserLoginUtils.getCurrentUsername());
        }
        catch (Exception e)
        {
            logger.error(e.toString());
        }
    }
    
    private void insertModelInitial(T record)
    {
        Class<?> clazz = record.getClass();
        try
        {
            Method getId = clazz.getMethod("getId");
            Object id = getId.invoke(record);

            if (id == null && "java.lang.String".equals(getId.getReturnType().getName()))
            {
                Method setId = clazz.getMethod("setId", getId.getReturnType());
                setId.invoke(record, UUID.randomUUID().toString());
            }
        }
        catch (Exception e)
        {
            String error = record.getClass().getName() + ".ID自动赋值发生错误，找不到ID属性。";
            logger.error(error, e);
            throw new RuntimeException(error);
        }
        try
        {

            Date now = new Date(System.currentTimeMillis());
            Method getCreateTime = clazz.getMethod("getCreateTime");
            Object createDate = getCreateTime.invoke(record);

            if (createDate == null)
            {
                Method setCreateTime = clazz.getMethod("setCreateTime", getCreateTime.getReturnType());
                if ("java.lang.String".equals(getCreateTime.getReturnType().getName()))
                {
                    setCreateTime.invoke(record, this.dateToString(now));
                }
                else
                {
                    setCreateTime.invoke(record, now);
                }
            }

            Method getUpdateTime = clazz.getMethod("getUpdateTime");
            Object lastUpdateDate = getUpdateTime.invoke(record);

            if (lastUpdateDate == null)
            {
                Method setUpdateTime = clazz.getMethod("setUpdateTime", getUpdateTime.getReturnType());
                if ("java.lang.String".equals(getUpdateTime.getReturnType().getName()))
                {
                    setUpdateTime.invoke(record, this.dateToString(now));
                }
                else
                {
                    setUpdateTime.invoke(record, now);
                }
            }

        }
        catch (Exception e)
        {
            logger.error(e.toString());
        }
    }



    private static String dateToString(Date date)
    {
        String str = null;
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        if (date != null)
        {
            str = sdf.format(date);
        }
        return str;
    }

}
