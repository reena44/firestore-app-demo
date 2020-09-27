package com.example.firestoreapp

class Detail {
    var name: String? = null
        private set
    var age: String? = null
        private set
    var address: String? = null
        private set

    constructor() {}
    constructor(name: String?, age: String?, address: String?) {
        this.name = name
        this.age = age
        this.address = address
    }

}