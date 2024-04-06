package domain;


public class TCompany {
    protected String id;
    protected String company_name;

    public TCompany(String company_id, String company_name) {
        this.id = company_id;
        this.company_name = company_name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompany_name() {
        return company_name;
    }

    @Override
    public String toString() {
        return company_name + " " + id + '\n';
    }

}