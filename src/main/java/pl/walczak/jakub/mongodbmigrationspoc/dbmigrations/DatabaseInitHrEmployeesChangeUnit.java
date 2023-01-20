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

@ChangeUnit(order = "002", id = "init_hr_departments")
public class DatabaseInitHrEmployeesChangeUnit {

    private final MongoTemplate mongoTemplate;

    public DatabaseInitHrEmployeesChangeUnit(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Execution
    public void initHrEmployees(EmployeeRepository employeeRepository,
                                DepartmentRepository departmentRepository) {
        Optional<Department> hrDepartment = departmentRepository.findAllByCode("HR");

        if (hrDepartment.isPresent()) {
            Employee employee1 = employeeRepository.save(Employee.builder()
                    .firstName("Dioms")
                    .lastName("Kane")
                    .email("diom.kan@yahoo.fr")
                    .department(hrDepartment.get())
                    .build());
            employeeRepository.save(employee1);

            Employee employee2 = employeeRepository.save(Employee.builder()
                    .firstName("Astrid")
                    .lastName("Flob")
                    .email("f.astrid@demo.com")
                    .department(hrDepartment.get())
                    .build());
            employeeRepository.save(employee2);
        }
    }

    @RollbackExecution
    public void rollback() {
        mongoTemplate.remove(Employee.class);
    }
}
