package pl.walczak.jakub.mongodbmigrationspoc.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.walczak.jakub.mongodbmigrationspoc.entity.Department;

import java.util.Optional;

@Repository
public interface DepartmentRepository extends MongoRepository<Department, String> {

    Optional<Department> findAllByCode(String code);
}
