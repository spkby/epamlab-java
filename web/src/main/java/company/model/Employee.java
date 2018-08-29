package company.model;


import javax.persistence.*;
import java.sql.Date;

import static company.Constants.*;

@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name = "birthday", nullable = false)
    private Date birthday;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Role role;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Department department;

    @OneToOne(cascade = CascadeType.ALL)
    private Salary salary;

    public Employee() {
    }

    public Employee(String name, Date birthday, Role role, Department department, Salary salary) {
        setName(name);
        setBirthday(birthday);
        setRole(role);
        setDepartment(department);
        setSalary(salary);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public Role getRole() {
        return role;
    }

    public Department getDepartment() {
        return department;
    }

    public Salary getSalary() {
        return salary;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        if (name.isEmpty()) {
            throw new IllegalArgumentException(INVALID_EMPLOYEE_NAME + EMPTY);
        }
        if (name.length() > 50) {
            throw new IllegalArgumentException(INVALID_EMPLOYEE_NAME + LENGTH_MORE_50);
        }
        this.name = name;
    }

    public void setBirthday(Date birthday) {
        if (birthday == null) {
            throw new IllegalArgumentException(INVALID_EMPLOYEE_BIRTHDAY + STRING_NULL);
        }
        this.birthday = birthday;
    }

    public void setRole(Role role) {
        if (role == null) {
            throw new IllegalArgumentException(INVALID_EMPLOYEE_ROLE + STRING_NULL);
        }
        this.role = role;
    }

    public void setDepartment(Department department) {
        if (department == null) {
            throw new IllegalArgumentException(INVALID_EMPLOYEE_DEPARTMENT + STRING_NULL);
        }
        this.department = department;
    }

    public void setSalary(Salary salary) {
        if (salary == null) {
            throw new IllegalArgumentException(INVALID_EMPLOYEE_SALARY + STRING_NULL);
        }
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthday=" + birthday +
                ", role=" + role +
                ", department=" + department +
                ", salary=" + salary +
                '}';
    }
}
