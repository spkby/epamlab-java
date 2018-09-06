package company.Hibernate;

public class CreateAndFillDB {

    public static void main(String[] args) {

        ReCreateDBUtil.main(new String[]{});

        try {
            FillConstantsUtil.fill();
            FillSampesUtil.fill();
        } finally {
            HibernateUtil.shutdown();
        }
    }
}
