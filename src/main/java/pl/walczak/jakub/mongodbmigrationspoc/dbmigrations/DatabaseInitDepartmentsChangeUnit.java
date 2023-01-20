package pl.walczak.jakub.mongodbmigrationspoc.dbmigrations;

import io.mongock.api.annotations.ChangeUnit;
import io.mongock.api.annotations.Execution;
import io.mongock.api.annotations.RollbackExecution;
import org.springframework.data.mongodb.core.MongoTemplate;
import pl.walczak.jakub.mongodbmigrationspoc.entity.Department;
import pl.walczak.jakub.mongodbmigrationspoc.repository.DepartmentRepository;

@ChangeUnit(order = "001", id = "init_departments")
public class DatabaseInitDepartmentsChangeUnit {

    private final MongoTemplate mongoTemplate;

    public DatabaseInitDepartmentsChangeUnit(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Execution
    public void initDepartments(DepartmentRepository departmentRepository) {
        departmentRepository.save(Department.builder()
                .name("Human Resource Management")
                .code("HR")
                .build());
        departmentRepository.save(Department.builder()
                .name("R&D")
                .code("Research and Development (often abbreviated to R&D)")
                .build());
        departmentRepository.save(Department.builder()
                .name("Prod")
                .code("Production")
                .build());
    }

    @RollbackExecution
    public void rollback() {
        mongoTemplate.remove(Department.class);
    }
}
