package company.model;

import javax.persistence.*;
import java.sql.Date;

import static company.Constants.*;

@Entity
@Table(name = "holiday")
public class Holiday {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "date_to", nullable = false)
    private Date dateTo;

    @Column(name = "date_from", nullable = false)
    private Date dateFrom;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Employee employee;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Status status;

    public Holiday() {
    }

    public Holiday(Date dateTo, Date dateFrom, Employee employee, Status status) {
        setDateTo(dateTo);
        setDateFrom(dateFrom);
        setEmployee(employee);
        setStatus(status);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        if (employee == null) {
            throw new IllegalArgumentException(INVALID_HOLIDAY_EMPLOYEE + STRING_NULL);
        }
        this.employee = employee;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        if (status == null) {
            throw new IllegalArgumentException(INVALID_HOLIDAY_STATUS + STRING_NULL);
        }
        this.status = status;
    }

    @Override
    public String toString() {
        return "Holiday{" +
                "id=" + id +
                ", dateTo=" + dateTo +
                ", dateFrom=" + dateFrom +
                ", employee=" + employee +
                ", status=" + status +
                '}';
    }
}
