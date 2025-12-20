package com.krowfeather.dao;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

// 基础DAO接口 - 定义通用的数据访问操作
// @param <T> 实体类型
// @param <K> 主键类型，必须实现Serializable接口
public interface IBaseDao<T, K extends Serializable> {
    // 根据主键查找实体
    // @param id 实体主键
    // @return 返回Optional包装的实体对象，避免空指针异常
    Optional<T> findById(K id);

    // 查找所有实体
    // @return 实体对象列表
    List<T> findAll();

    // 删除实体
    // @param entity 要删除的实体对象
    void delete(T entity);

    // 更新实体
    // @param entity 要更新的实体对象
    void update(T entity);

    // 保存或更新实体
    // @param entity 要保存的实体对象
    void save(T entity);
}
