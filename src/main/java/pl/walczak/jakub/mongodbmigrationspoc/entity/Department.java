package pl.walczak.jakub.mongodbmigrationspoc.entity;

import lombok.*;
import org.springframework.data.annotation.Id;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Department {
    @Id
    private String id;

    private String code;

    private String name;
}
