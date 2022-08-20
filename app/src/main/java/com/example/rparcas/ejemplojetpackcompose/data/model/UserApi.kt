package com.example.rparcas.ejemplojetpackcompose.data.model

import com.google.gson.annotations.SerializedName


/*
 Formato JSON
 "results": [
    {
      "name": {
        "first": "Jennie",
        "last": "Nichols"
      },
      "dob": {
        "age": 30
      },
    }
 */
data class UserApi(
    @SerializedName("results") val results:List<Results> // va a ser siempre 1
)

data class Results(
    @SerializedName("name") val userName: UserName,
    @SerializedName("dob") val userDob: UserDob
)

data class UserName(@SerializedName("first") val name:String,@SerializedName("last") val lastName:String)
data class UserDob(@SerializedName("age") val age:String)