package Employee.RestAPI.EmployeeRestApi.Modules.Employee.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employee")
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "employeeName")
    private String personalName;

    @Column(name = "personalNumber")
    private String personalNumber;

    @CreationTimestamp
    @Column(name = "create_at")
    private LocalDateTime create_at;

    @UpdateTimestamp
    @Column(name = "update_at")
    private LocalDateTime update_at;

}
