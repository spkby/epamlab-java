package company.model;

import javax.persistence.*;

import static company.Constants.*;

@Entity
@Table(name = "department")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", length = 10, nullable = false, unique = true)
    private String name;

    public Department(String name) {
        setName(name);
    }

    public Department() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name.isEmpty()) {
            throw new IllegalArgumentException(INVALID_DEPARTMENT_NAME + EMPTY);
        }
        if (name.length() > 10) {
            throw new IllegalArgumentException(INVALID_DEPARTMENT_NAME + LENGTH_MORE_20);
        }
        this.name = name;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
