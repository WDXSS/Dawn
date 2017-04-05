package com.dawndemo.Bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

public class TypeBean implements Parcelable, Serializable {
	/** 直播分类 */
//	@Transient
	public static final int LIVE = 1;

//	@Id
	public long id;
	public long typeId;
	/**父级分类id*/
	public long parentId;
	public String name;
//	@Transient
	/**分类排序值*/
	public int order;
//	@Transient
	/**分类等级（1 一级分类，2 二级分类）*/
	public int rank;
	/** 排序 */
	public int sort;
	/** 分类类型1：自定义，2：系统分类，4：频道 ,8推荐，16 关注*/
	/** 分类类型（1用户增加分类 2 全部分类 3其他分类） */
	public int type;
//	@Transient
	/**内容数量（只有type=3的才会有此值）(0 没内容,1 有内容)*/
	public int contentCount;
	/** 业务类型 */
	public int bizType;

//	@Transient
	public List<TypeBean> datas;

//	@Transient
	public boolean isCheck = false;

	public TypeBean(Parcel in) {
		id = in.readLong();
		typeId = in.readLong();
		parentId = in.readLong();
		name = in.readString();
		order = in.readInt();
		rank = in.readInt();
		sort = in.readInt();
		type = in.readInt();
		contentCount = in.readInt();
		bizType = in.readInt();
		datas = in.createTypedArrayList(TypeBean.CREATOR);
		isCheck = in.readByte() != 0;
	}
	public TypeBean() {}

	public static final Creator<TypeBean> CREATOR = new Creator<TypeBean>() {
		@Override
		public TypeBean createFromParcel(Parcel in) {
			return new TypeBean(in);
		}

		@Override
		public TypeBean[] newArray(int size) {
			return new TypeBean[size];
		}
	};

	public long getParentId() {
		return parentId;
	}

	public void setParentId(long parentId) {
		this.parentId = parentId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getTypeId() {
		return typeId;
	}

	public void setTypeId(long typeId) {
		this.typeId = typeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getBizType() {
		return bizType;
	}

	public void setBizType(int bizType) {
		this.bizType = bizType;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeLong(id);
		dest.writeLong(typeId);
		dest.writeLong(parentId);
		dest.writeString(name);
		dest.writeInt(order);
		dest.writeInt(rank);
		dest.writeInt(sort);
		dest.writeInt(type);
		dest.writeInt(contentCount);
		dest.writeInt(bizType);
		dest.writeTypedList(datas);
		dest.writeByte((byte) (isCheck ? 1 : 0));
	}
}
