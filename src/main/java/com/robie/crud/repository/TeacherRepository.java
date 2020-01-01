package com.robie.crud.repository;

import com.robie.crud.entity.Teacher;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepository extends CrudRepository <Teacher, Long> {
    List<Teacher> findByName(String name);
}
