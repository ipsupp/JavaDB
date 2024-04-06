package domain;

import java.util.Objects;

public class Agency {
    protected String id;
    protected String agency_name;

    public Agency(String agency_id, String agency_name) {
        this.id = agency_id;
        this.agency_name = agency_name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAgency_name() {
        return agency_name;
    }

    @Override
    public String toString() {
        return agency_name + " " + id + '\n';
    }
}
