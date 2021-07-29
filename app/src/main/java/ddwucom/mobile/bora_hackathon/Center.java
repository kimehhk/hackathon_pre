package ddwucom.mobile.bora_hackathon;

import java.io.Serializable;

public class Center implements Serializable {
    long _id;
    String receptionContent;
    String institution;
    String number;
    String address;

    public Center(long _id, String receptionContent, String institution, String number, String address) {
        this._id = _id;
        this.receptionContent = receptionContent;
        this.institution = institution;
        this.number = number;
        this.address = address;
    }

    public Center(String receptionContent, String institution, String number, String address) {
        this.receptionContent = receptionContent;
        this.institution = institution;
        this.number = number;
        this.address = address;
    }

    public long get_id() {
        return _id;
    }

    public String getReceptionContent() {
        return receptionContent;
    }

    public String getInstitution() {
        return institution;
    }

    public String getNumber() {
        return number;
    }

    public String getAddress() {
        return address;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public void setReceptionContent(String receptionContent) {
        this.receptionContent = receptionContent;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(_id);
        sb.append(".\t\t");
        sb.append(receptionContent);
        sb.append("\t\t\t(");
        sb.append(institution);
        sb.append("\t\t\t(");
        sb.append(number);
        sb.append("\t\t\t(");
        sb.append(address);
        sb.append(")");
        return sb.toString();
    }
}
