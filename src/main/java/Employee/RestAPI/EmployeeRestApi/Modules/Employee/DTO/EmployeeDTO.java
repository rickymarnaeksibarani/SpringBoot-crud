package Employee.RestAPI.EmployeeRestApi.Modules.Employee.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EmployeeDTO {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("personalName")
    private String personalName;
    @JsonProperty("personalNumber")
    private String personalNumber;
    @JsonProperty("create_at")
    private LocalDateTime create_at;
    @JsonProperty("update_at")
    private LocalDateTime update_at;


}
