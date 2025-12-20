package com.krowfeather.dao;

import com.krowfeather.entity.Registration;
import com.krowfeather.util.JPAUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.List;

public class RegistrationDao extends BaseDao<Registration, Long> {

    /**
     * 复杂查询1：查询每个学生及其已选课程的总学分
     * 使用JOIN、GROUP BY、SUM聚合函数
     * 
     * @return 返回Object[]数组列表，每个数组包含[学生ID, 学生姓名, 总学分]
     */
    public List<Object[]> findStudentsWithTotalCredits() {
        try (EntityManager manager = JPAUtils.getEntityManager()) {
            // JPQL查询：关联Registration、Student和Course表，按学生分组并计算总学分
            String jpql = "SELECT r.student.id, r.student.name, SUM(r.course.credit) " +
                    "FROM Registration r " +
                    "JOIN r.student s " +
                    "JOIN r.course c " +
                    "GROUP BY r.student.id, r.student.name " +
                    "ORDER BY SUM(r.course.credit) DESC";

            Query query = manager.createQuery(jpql);
            return (List<Object[]>) query.getResultList();
        }
    }

    /**
     * 复杂查询2：查询每门课程的选课人数和平均学生年龄
     * 使用JOIN、GROUP BY、COUNT和AVG聚合函数、HAVING子句
     * 
     * @return 返回Object[]数组列表，每个数组包含[课程ID, 课程名称, 选课人数, 平均年龄]
     */
    public List<Object[]> findCoursesWithEnrollmentStats() {
        try (EntityManager manager = JPAUtils.getEntityManager()) {
            // JPQL查询：统计每门课程的选课人数和选课学生的平均年龄
            String jpql = "SELECT r.course.id, r.course.courseName, " +
                    "COUNT(DISTINCT r.student.id) as enrollmentCount, " +
                    "AVG(r.student.age) as avgAge " +
                    "FROM Registration r " +
                    "JOIN r.course c " +
                    "JOIN r.student s " +
                    "WHERE r.student.age IS NOT NULL " +
                    "GROUP BY r.course.id, r.course.courseName " +
                    "HAVING COUNT(DISTINCT r.student.id) > 0 " +
                    "ORDER BY enrollmentCount DESC, avgAge DESC";

            Query query = manager.createQuery(jpql);
            return (List<Object[]>) query.getResultList();
        }
    }
}
