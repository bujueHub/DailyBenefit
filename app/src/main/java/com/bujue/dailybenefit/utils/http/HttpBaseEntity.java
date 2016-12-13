package com.bujue.dailybenefit.utils.http;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 表情Base基类
 *
 * @author bujue
 * @date 16/10/17
 */
public class HttpBaseEntity {

    public Status status; // 结果状态

    public static class Status implements Parcelable {
        public int code; // 结果错误码
        public String msg; // 结果错误信息

        public Status() {
        }

        public static final Creator<Status> CREATOR = new Creator<Status>() {
            @Override
            public Status createFromParcel(Parcel in) {
                return new Status(in);
            }

            @Override
            public Status[] newArray(int size) {
                return new Status[size];
            }
        };

        public Status(Parcel source) {
            code = source.readInt();
            int flag = source.readInt();
            msg = flag == 1 ? source.readString() : null;
        }


        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(code);
            if (msg != null) {
                dest.writeInt(1);
                dest.writeString(msg);
            } else {
                dest.writeInt(0);
            }
        }

        @Override
        public String toString() {
            return "Status{" +
                    "code=" + code +
                    ", msg='" + msg + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "EmotionBaseEntity{" +
                "status=" + status +
                '}';
    }
}
