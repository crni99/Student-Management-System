package com.crni99.studentms.storage;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.crni99.studentms.domain.Student;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {

	Optional<Student> findStudentById(Long id);

	@Query(value = "SELECT * FROM students " 
				 + "WHERE email = :email", nativeQuery = true)
	Student findStudentByEmail(@Param("email") String email);

	@Query(value = "SELECT * FROM students " 
				 + "WHERE index_number = :indexNumber", nativeQuery = true)
	Student findStudentByIndexNumber(@Param("indexNumber") int indexNumber);

	@Query(value = "SELECT * FROM students " 
			 	 + "WHERE date_of_birth BETWEEN :dob1 AND :dob2", nativeQuery = true)
	List<Student> findBetweenTwoDOB(@Param("dob1") LocalDate dob1, @Param("dob2") LocalDate dob2);
	
	@Modifying
	@Query(value = "DELETE FROM students " 
		 	 	 + "WHERE email = :email", nativeQuery = true)
	void deleteStudentByEmail(@Param("email") String email);

}
