package edu.alsjava.courses.demoluis.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.jetbrains.annotations.NotNull;

/**
 * Created by aluis on 12/11/19.
 */
public class BigData implements Parcelable {

    private String name;
    private String description;
    private int age;
    private String role;
    private String tshirtSize;

    public BigData() {
    }

    private BigData(@NotNull Parcel parcel) {
        name = parcel.readString();
        description = parcel.readString();
        age = parcel.readInt();
        role = parcel.readString();
        tshirtSize = parcel.readString();
    }

    public static final Creator<BigData> CREATOR = new Creator<BigData>() {
        @Override
        public BigData createFromParcel(Parcel in) {
            return new BigData(in);
        }

        @Override
        public BigData[] newArray(int size) {
            return new BigData[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getTshirtSize() {
        return tshirtSize;
    }

    public void setTshirtSize(String tshirtSize) {
        this.tshirtSize = tshirtSize;
    }

    @NotNull
    @Override
    public String toString() {
        return "BigData{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", age=" + age +
                ", role='" + role + '\'' +
                ", tshirtSize='" + tshirtSize + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(description);
        dest.writeInt(age);
        dest.writeString(role);
        dest.writeString(tshirtSize);
    }
}
