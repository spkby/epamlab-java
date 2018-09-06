package company.model;

import javax.persistence.*;

import static company.Constants.*;

@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", length = 10, nullable = false, unique = true)
    private String name;

    public Role() {
    }

    public Role(String name) {
        setName(name);
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
            throw new IllegalArgumentException(INVALID_ROLE_NAME +STRING_NULL);
        }
        if (name.length() > 10) {
            throw new IllegalArgumentException(INVALID_ROLE_NAME+ LENGTH_MORE_10);
        }
        this.name = name;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
