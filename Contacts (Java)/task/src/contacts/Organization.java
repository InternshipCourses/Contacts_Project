package contacts;

public class Organization extends ContactDetails{
    private String organizationName;
    private  String address;

    // Todo If there is an error add primary
    public Organization(String name,String organizationAddress , String phoneNumber) {
        super(phoneNumber,false);
        this.address = organizationAddress;
        this.organizationName = name;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber(){
        return super.phoneNumber;
    }

    public void setPhoneNumber(String number){
        super.phoneNumber = number;
    }
}
