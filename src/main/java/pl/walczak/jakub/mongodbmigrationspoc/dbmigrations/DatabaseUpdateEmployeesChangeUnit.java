package pl.walczak.jakub.mongodbmigrationspoc.dbmigrations;


import io.mongock.api.annotations.ChangeUnit;
import io.mongock.api.annotations.Execution;
import io.mongock.api.annotations.RollbackExecution;
import org.springframework.data.mongodb.core.MongoTemplate;
import pl.walczak.jakub.mongodbmigrationspoc.entity.Employee;
import pl.walczak.jakub.mongodbmigrationspoc.repository.EmployeeRepository;

@ChangeUnit(order = "003", id = "update_employees")
public class DatabaseUpdateEmployeesChangeUnit {


    private final MongoTemplate mongoTemplate;

    public DatabaseUpdateEmployeesChangeUnit(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Execution
    public void updateEmployees(EmployeeRepository employeeRepository) {

        employeeRepository.findAll().forEach(employee -> {
            employee.setSalary(0.0);
            employeeRepository.save(employee);
        });
    }

    @RollbackExecution
    public void rollback() {
        mongoTemplate.remove(Employee.class);
    }
}
