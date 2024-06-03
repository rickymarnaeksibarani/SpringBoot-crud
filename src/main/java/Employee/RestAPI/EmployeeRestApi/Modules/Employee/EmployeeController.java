package Employee.RestAPI.EmployeeRestApi.Modules.Employee;

import Employee.RestAPI.EmployeeRestApi.Exception.CustomRequestException;
import Employee.RestAPI.EmployeeRestApi.Modules.Employee.DTO.EmployeeDTO;
import Employee.RestAPI.EmployeeRestApi.Modules.Employee.DTO.EmployeeRequestDTO;
import Employee.RestAPI.EmployeeRestApi.Modules.Employee.Entity.EmployeeEntity;
import Employee.RestAPI.EmployeeRestApi.Utils.PaginationUtil;
import Employee.RestAPI.EmployeeRestApi.core.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@Slf4j
@RequestMapping("/api/v1/employee")
public class EmployeeController {
    @Autowired
    public EmployeeService employeeService;

    //Creating
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createEmployee(
            @RequestBody @Validated EmployeeDTO request){
       try {
           EmployeeEntity result = employeeService.createEmployee(request);
           ApiResponse<EmployeeEntity> response = new ApiResponse<>(HttpStatus.OK, "Berhasil create cuyy", result);
           return new ResponseEntity<>(response, response.getStatus());
       }
       catch (CustomRequestException error){
           return error.GlobalCustomRequestException(error.getMessage(), error.getStatus());
       }

    }

    //Getting
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getEmployee(EmployeeRequestDTO searchDTO){
        try {
            PaginationUtil<EmployeeEntity, EmployeeEntity> result = employeeService.getAllEmployeeByPagination(searchDTO);
            return new ResponseEntity<>(new ApiResponse<>(HttpStatus.OK, "Ini datanya cuy!!", result), HttpStatus.OK);
        }catch (CustomRequestException error){
            return error.GlobalCustomRequestException(error.getMessage(), error.getStatus());
        }

    }

}
