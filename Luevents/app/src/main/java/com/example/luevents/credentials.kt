package com.example.luevents

class credentials {
    var id1: Int=0
    var name:Int?=null
    var Password:String?=null

    constructor(){

    }
    constructor(id: Int, name:Int,location:String ){

        this.id1= id
        this.name= name
        this.Password= location

    }
}