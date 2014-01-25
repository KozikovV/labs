package zinchenko.domain;

import javax.persistence.*;

/**
 * User: zinchenko
 * Date: 19.01.14
 */
@Entity
@Table(name = "EMPLOYEE_PERSON")
@PrimaryKeyJoinColumns(value = {
        @PrimaryKeyJoinColumn(name = "FIRST_NAME"),
        @PrimaryKeyJoinColumn(name = "LAST_NAME")
})
public class EmployeePerson extends Person {

    @Column(name = "DEPARTMENT_NAME")
    private String departmentName;

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}
