package pl.walczak.jakub.mongodbmigrationspoc.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.walczak.jakub.mongodbmigrationspoc.entity.Employee;

@Repository
public interface EmployeeRepository extends MongoRepository<Employee, String> {
}
