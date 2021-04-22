package com.example.luevents


class events {
    var id: Int=0
    var name:String?=null
    var location:String?=null

    constructor(){

    }
    constructor(id: Int, name:String,location:String ){

        this.id= id
        this.name= name
        this.location= location

    }
}