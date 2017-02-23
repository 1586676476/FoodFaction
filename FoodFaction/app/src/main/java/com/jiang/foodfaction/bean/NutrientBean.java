package com.jiang.foodfaction.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by dllo on 17/2/23.
 */

public class NutrientBean implements Parcelable{


    private List<TypesBean> types;

    protected NutrientBean(Parcel in) {
        types = in.createTypedArrayList(TypesBean.CREATOR);
    }

    public static final Creator<NutrientBean> CREATOR = new Creator<NutrientBean>() {
        @Override
        public NutrientBean createFromParcel(Parcel in) {
            return new NutrientBean(in);
        }

        @Override
        public NutrientBean[] newArray(int size) {
            return new NutrientBean[size];
        }
    };

    public List<TypesBean> getTypes() {
        return types;
    }

    public void setTypes(List<TypesBean> types) {
        this.types = types;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(types);
    }

    public void addAll(NutrientBean bean) {

    }

    public static class TypesBean implements Parcelable {
        /**
         * code : calory
         * name : 热量
         * index : 2
         */

        private String code;
        private String name;
        private String index;

        protected TypesBean(Parcel in) {
            code = in.readString();
            name = in.readString();
            index = in.readString();
        }

        public static final Creator<TypesBean> CREATOR = new Creator<TypesBean>() {
            @Override
            public TypesBean createFromParcel(Parcel in) {
                return new TypesBean(in);
            }

            @Override
            public TypesBean[] newArray(int size) {
                return new TypesBean[size];
            }
        };

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getIndex() {
            return index;
        }

        public void setIndex(String index) {
            this.index = index;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(code);
            dest.writeString(name);
            dest.writeString(index);
        }
    }
}
