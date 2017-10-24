package model;

public class Number {
    private String num;
    private PhoneNumberEnum PhoneNumberEnum;

    public Number(String value, PhoneNumberEnum type) {
        this.num = value;
        this.PhoneNumberEnum = type;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public void setPhoneNumberEnum(model.PhoneNumberEnum phoneNumberEnum) {
        PhoneNumberEnum = phoneNumberEnum;
    }

    public String getNum() {
        return num;
    }

    public PhoneNumberEnum getPhoneNumberEnum() {
        return PhoneNumberEnum;
    }

    @Override
    public String toString() {
        return "Number{" +
                "type='" + PhoneNumberEnum +
                ", number=" + num +
                '}';
    }
}
