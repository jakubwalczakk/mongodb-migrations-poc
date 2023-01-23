package pl.walczak.jakub.mongodbmigrationspoc.dbmigrations;


import io.mongock.api.annotations.ChangeUnit;
import io.mongock.api.annotations.Execution;
import io.mongock.api.annotations.RollbackExecution;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.mongodb.core.MongoTemplate;
import pl.walczak.jakub.mongodbmigrationspoc.entity.Employee;
import pl.walczak.jakub.mongodbmigrationspoc.repository.EmployeeRepository;

@Log4j2
@ChangeUnit(order = "004", id = "update_employees")
public class DatabaseUpdateEmployeesChangeUnit {


    private final MongoTemplate mongoTemplate;

    public DatabaseUpdateEmployeesChangeUnit(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Execution
    public void updateEmployees(EmployeeRepository employeeRepository) {
        log.info("Update employees");
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
