package Employee.RestAPI.EmployeeRestApi.Modules.Employee.DTO;

import lombok.Data;

@Data
public class EmployeeRequestDTO {
    private Integer page;

    private Integer size;
    private String searchTerm;

    EmployeeRequestDTO(){
        if (this.getPage()==null){
            this.page = 1;
        }
        if (this.getSize()==null){
            this.size = 10;
        }
    }
}
