package pl.walczak.jakub.mongodbmigrationspoc;

import io.mongock.runner.springboot.EnableMongock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableMongock
public class MongodbMigrationsPocApplication {

    public static void main(String[] args) {
        SpringApplication.run(MongodbMigrationsPocApplication.class, args);
    }

}
