package com.cocobites.roomdb.data.model.db;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "note")
public class Note implements Parcelable {

    /*@ColumnInfo(name = "created_at")
    public String createdAt;*/

    @PrimaryKey
    public Long id;

    public String title;

    public String content;

    public int bgColorInt;

    /*@ColumnInfo(name = "updated_at")
    public String updatedAt;*/

    public Note(String title, String content, int bgColorInt) {
        this.title = title;
        this.content = content;
        this.bgColorInt = bgColorInt;
    }

    protected Note(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readLong();
        }
        title = in.readString();
        content = in.readString();
        bgColorInt = in.readInt();
    }

    public static final Creator<Note> CREATOR = new Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel in) {
            return new Note(in);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public int getBgColorInt() {
        return bgColorInt;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(id);
        }
        dest.writeString(title);
        dest.writeString(content);
        dest.writeInt(bgColorInt);
    }
}
