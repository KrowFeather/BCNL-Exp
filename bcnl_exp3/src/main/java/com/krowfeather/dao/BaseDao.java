package com.krowfeather.dao;

import com.krowfeather.util.JPAUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

// 基础DAO实现类，提供通用的数据库操作方法
// T表示实体类型，K表示主键类型且必须实现Serializable接口
public class BaseDao<T,K extends Serializable> implements IBaseDao<T,K> {
    // 实体类的Class对象，用于JPA操作时指定实体类型（如find、HQL构建）
    private Class<T> clz;

    // 无参构造器：通过反射获取泛型实际类型，初始化实体类Class对象
    public  BaseDao() {
        // 获取当前类的直接父类（必须是参数化类型，即子类明确指定了泛型）
        ParameterizedType parametClass = (ParameterizedType) this.getClass().getGenericSuperclass();
        // 获取父类泛型的实际类型参数（如<User, Long>中的User和Long）
        Type[] actualTypeArguments = parametClass.getActualTypeArguments();
        // 第一个泛型参数即为实体类T，强转为Class<T>并赋值
        clz = (Class<T>) actualTypeArguments[0];
    }

    // 保存实体对象到数据库
    @Override
    public void save(T object) {
        // 获取JPA实体管理器（EntityManager是JPA操作的核心入口，由工具类统一管理）
        EntityManager manager = JPAUtils.getEntityManager();
        // 开启事务（JPA中写操作必须在事务内执行，否则抛出异常）
        manager.getTransaction().begin();
        // 执行保存操作：将实体持久化到数据库
        manager.persist(object);
        // 提交事务：确认数据写入数据库
        manager.getTransaction().commit();
        // 关闭实体管理器：释放资源（无论事务成功与否都需关闭，避免连接泄露）
        manager.close();
    }

    // 根据主键查找实体对象
    @Override
    public Optional<T> findById(K id) {
        // 获取JPA实体管理器
        EntityManager manager = JPAUtils.getEntityManager();
        // 执行查询：clz指定查询的实体类型，id为查询条件（主键）
        T object = manager.find(clz, id);
        // 关闭实体管理器
        manager.close();
        // 用Optional包装返回，避免返回null导致空指针异常
        return Optional.ofNullable(object);
    }

    // 更新实体对象
    @Override
    public void update(T object) {
        // 获取JPA实体管理器
        EntityManager manager = JPAUtils.getEntityManager();
        // 开启事务
        manager.getTransaction().begin();
        // 执行更新操作：merge会将游离实体的状态同步到数据库
        manager.merge(object);
        // 提交事务
        manager.getTransaction().commit();
        // 关闭实体管理器
        manager.close();
    }

    // 删除实体对象
    @Override
    public void delete(T object) {
        // 获取JPA实体管理器
        EntityManager manager = JPAUtils.getEntityManager();
        // 开启事务
        manager.getTransaction().begin();
        // 将游离状态的实体转为托管状态（若直接remove游离实体会抛出IllegalArgumentException）
        T managedEntity = manager.merge(object);
        // 执行删除操作：删除托管状态的实体
        manager.remove(managedEntity);
        // 提交事务
        manager.getTransaction().commit();
        // 关闭实体管理器
        manager.close();
    }

    // 查找所有实体对象
    @Override
    public List<T> findAll() {
        // 获取JPA实体管理器
        EntityManager manager = JPAUtils.getEntityManager();
        // 构建HQL语句：select t from 实体全类名 t（t为别名，clz.getName()获取实体全类名，避免硬编码）
        String hql = "select t from " + clz.getName() + " t";
        // 创建Query对象：指定HQL语句（JPA标准查询对象，支持参数绑定、分页等）
        Query query = manager.createQuery(hql);
        // 执行查询：getResultList()返回所有匹配记录，无记录时返回空列表
        List<T> list = query.getResultList();
        // 关闭实体管理器
        manager.close();
        // 返回查询结果
        return list;
    }
}
