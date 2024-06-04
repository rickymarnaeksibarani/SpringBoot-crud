package Employee.RestAPI.EmployeeRestApi.Modules.Employee;

import Employee.RestAPI.EmployeeRestApi.Exception.CustomRequestException;
import Employee.RestAPI.EmployeeRestApi.Modules.Employee.DTO.EmployeeDTO;
import Employee.RestAPI.EmployeeRestApi.Modules.Employee.DTO.EmployeeRequestDTO;
import Employee.RestAPI.EmployeeRestApi.Modules.Employee.Entity.EmployeeEntity;
import Employee.RestAPI.EmployeeRestApi.Modules.Employee.Repository.EmployeeRepository;
import Employee.RestAPI.EmployeeRestApi.Utils.PaginationUtil;
import jakarta.persistence.criteria.Predicate;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRespository;

    //CRUD
    //Create(Post)
    public EmployeeEntity createEmployee(EmployeeDTO request){
        boolean existByEmployeeName = employeeRespository.existsByPersonalName(request.getPersonalName());
        if (existByEmployeeName){
            throw new CustomRequestException("Employee name/number already exist, double check again",HttpStatus.CONFLICT);
        }
        EmployeeEntity data = new EmployeeEntity();
        data.setPersonalName(request.getPersonalName());
        data.setPersonalNumber(request.getPersonalNumber());
        return employeeRespository.save(data);
    }

    //Read(Getting)
    public PaginationUtil<EmployeeEntity, EmployeeEntity> getAllEmployeeByPagination(EmployeeRequestDTO searchRequest){
        Specification<EmployeeEntity> specification = (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (searchRequest.getSearchTerm() != null) {
                predicates.add(
                        builder.or(
                                builder.like(builder.upper(root.get("personalName")), "%" + searchRequest.getSearchTerm().toUpperCase() + "%"),
                                builder.like(builder.upper(root.get("personalNumber")), "%" + searchRequest.getSearchTerm().toUpperCase() + "%")
                        )
                );
            }

            return query.where(predicates.toArray(new Predicate[]{})).getRestriction();
        };
        Pageable paging = PageRequest.of(searchRequest.getPage()-1, searchRequest.getSize());
        Page<EmployeeEntity> pagedResult = employeeRespository.findAll(specification, paging);
        return new PaginationUtil<>(pagedResult, EmployeeEntity.class);
    }

    //Getting by ID
    public EmployeeEntity getEmployeeById(Long id_employee) {
        EmployeeEntity result = employeeRespository.findById(id_employee).orElse(null);
        if (result == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"ID" + id_employee + " not found");
        }
        return result;
    }

    //Update by Id
    @Transactional
    public EmployeeEntity updateEmployee(Long id_employee, EmployeeDTO request) {
        EmployeeEntity employee = employeeRespository.findById(id_employee)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.CONFLICT, "Employee does not exists"));
        employee.setPersonalNumber(request.getPersonalNumber());
        employee.setPersonalName(request.getPersonalName());
        return employeeRespository.save(employee);
    }
    @Transactional
    public void deleteEmployee(Long id_employee){
        EmployeeEntity findData = employeeRespository.findById(id_employee)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee does not exist"));
        employeeRespository.delete(findData);
    }
}
