package com.devjethava.composeboilerplate.network.response

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.JsonObject
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * General response class for API
 */
class ApiResponseModel() : Parcelable {
    @SerializedName("status")
    @Expose
    var status: String? = null

    @SerializedName("message")
    @Expose
    var message: String? = null

    @SerializedName("data")
    @Expose
    var data: JsonObject? = null

    constructor(parcel: Parcel) : this() {
        status = parcel.readString()
        message = parcel.readString()
    }

    override fun toString(): String {
        return "ApiResponseModel{" +
                "status='" + status + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}'
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(status)
        parcel.writeString(message)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ApiResponseModel> {
        override fun createFromParcel(parcel: Parcel): ApiResponseModel {
            return ApiResponseModel(parcel)
        }

        override fun newArray(size: Int): Array<ApiResponseModel?> {
            return arrayOfNulls(size)
        }
    }
}