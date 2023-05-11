package net.codeforyou.springbootthtmeleafcrud.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.codeforyou.springbootthtmeleafcrud.Entity.Student;

public interface StudentRepository extends JpaRepository<Student, String> {

}
