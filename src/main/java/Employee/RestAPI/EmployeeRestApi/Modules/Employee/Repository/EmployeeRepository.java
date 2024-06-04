package Employee.RestAPI.EmployeeRestApi.Modules.Employee.Repository;

import Employee.RestAPI.EmployeeRestApi.Modules.Employee.Entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long>, JpaSpecificationExecutor<EmployeeEntity> {
    boolean existsByPersonalName(String name);

}
