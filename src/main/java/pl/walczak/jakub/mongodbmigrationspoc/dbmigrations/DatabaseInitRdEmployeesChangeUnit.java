package pl.walczak.jakub.mongodbmigrationspoc.dbmigrations;

import io.mongock.api.annotations.ChangeUnit;
import io.mongock.api.annotations.Execution;
import io.mongock.api.annotations.RollbackExecution;
import org.springframework.data.mongodb.core.MongoTemplate;
import pl.walczak.jakub.mongodbmigrationspoc.entity.Department;
import pl.walczak.jakub.mongodbmigrationspoc.entity.Employee;
import pl.walczak.jakub.mongodbmigrationspoc.repository.DepartmentRepository;
import pl.walczak.jakub.mongodbmigrationspoc.repository.EmployeeRepository;

import java.util.Optional;

@ChangeUnit(order = "003", id = "init_rd_employees")
public class DatabaseInitRdEmployeesChangeUnit {

    private final MongoTemplate mongoTemplate;

    public DatabaseInitRdEmployeesChangeUnit(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Execution
    public void initRadEmployees(EmployeeRepository employeeRepository,
                                 DepartmentRepository departmentRepository) {
        Optional<Department> radDepartment = departmentRepository.findAllByCode("R&D");

        if (radDepartment.isPresent()) {
            Employee employee1 = employeeRepository.save(Employee.builder()
                    .firstName("Luis")
                    .lastName("Siruis")
                    .email("siruis.luis@test.com")
                    .department(radDepartment.get())
                    .build());
            employeeRepository.save(employee1);
        }
    }

    @RollbackExecution
    public void rollback() {
        mongoTemplate.remove(Employee.class);
    }
}
