package tk.mwacha.entities;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Where;
import tk.mwacha.events.EmployeeContent;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Where(clause = "deleted_at is null")
public class Employee implements Serializable {

    @Id
    private UUID id;
    private String email;
    private String employeeName;
    private LocalDateTime deletedAt;

    public void disable() {
        this.deletedAt = LocalDateTime.now();
    }


    public static Employee fromEmployeeContent(UUID id, EmployeeContent content) {
        return Employee.builder().id(id).employeeName(content.getEmployeeName()).email(content.getEmail()).build();
    }
}
