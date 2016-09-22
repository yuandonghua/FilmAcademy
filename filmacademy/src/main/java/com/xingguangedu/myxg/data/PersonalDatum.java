package com.xingguangedu.myxg.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @description:个人资料
 * @author:袁东华 created at 2016/8/26 0026 下午 2:46
 */
public class PersonalDatum implements Parcelable {
    //电影id
    private String id = "";
    private String name = "";
    private String phone = "";
    private String role = "";
    private String school = "";
    private String grade = "";
    private String specialty = "";
    private String train = "";
    private String award = "";
    private String password = "";

    public PersonalDatum() {
    }

    protected PersonalDatum(Parcel in) {
        id = in.readString();
        name = in.readString();
        phone = in.readString();
        role = in.readString();
        school = in.readString();
        grade = in.readString();
        specialty = in.readString();
        train = in.readString();
        award = in.readString();
        password = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(phone);
        dest.writeString(role);
        dest.writeString(school);
        dest.writeString(grade);
        dest.writeString(specialty);
        dest.writeString(train);
        dest.writeString(award);
        dest.writeString(password);

    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<PersonalDatum> CREATOR = new Creator<PersonalDatum>() {
        @Override
        public PersonalDatum createFromParcel(Parcel in) {
            return new PersonalDatum(in);
        }

        @Override
        public PersonalDatum[] newArray(int size) {
            return new PersonalDatum[size];
        }
    };

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getTrain() {
        return train;
    }

    public void setTrain(String train) {
        this.train = train;
    }

    public String getAward() {
        return award;
    }

    public void setAward(String award) {
        this.award = award;
    }
}
