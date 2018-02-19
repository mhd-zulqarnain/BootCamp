package fahee.example.com.inovisionhome.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Zul Qarnain on 2/14/2018.
 */

public class Contacts implements Parcelable {
    String name;
    String imgAddress;

    public Contacts(String name, String imgAddress) {
        this.name = name;
        this.imgAddress = imgAddress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgAddress() {
        return imgAddress;
    }

    public void setImgAddress(String imgAddress) {
        this.imgAddress = imgAddress;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.imgAddress);
    }

    protected Contacts(Parcel in) {
        this.name = in.readString();
        this.imgAddress = in.readString();
    }

    public static final Parcelable.Creator<Contacts> CREATOR = new Parcelable.Creator<Contacts>() {
        @Override
        public Contacts createFromParcel(Parcel source) {
            return new Contacts(source);
        }

        @Override
        public Contacts[] newArray(int size) {
            return new Contacts[size];
        }
    };
}
