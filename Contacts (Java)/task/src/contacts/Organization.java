package contacts;

public class Organization extends ContactDetails{
    private String organizationName;
    private  String address;

    public Organization(String name,String organizationAddress , String phoneNumber) {
        super(phoneNumber,false);
        this.address = organizationAddress;
        this.organizationName = name;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String newOrganizationName) {
        this.organizationName = newOrganizationName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String newAddress) {
        this.address = newAddress;
    }
    @Override
    public String getPhoneNumber(){
        return super.phoneNumber;
    }

    @Override
    public void setPhoneNumber(String number){
        super.phoneNumber = number;
    }
}
