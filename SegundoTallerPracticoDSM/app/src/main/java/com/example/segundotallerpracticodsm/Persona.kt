package com.example.segundotallerpracticodsm


class Persona {
    fun key(key: String?) {
    }
    var OpcionID: String? = null
    var nombre: String? = null
    var nota1: String? = null
    var nota2: String? = null
    var nota3: String? = null
    var nota4: String? = null
    var nota5: String? = null
    var tv1: String? = null
    var key: String? = null
    var per: MutableMap<String, Boolean> = HashMap()

    constructor() {}
    constructor(OpcionID: String?,nombre: String?,nota1: String?,nota2: String?,nota3: String?,nota4: String?,nota5: String?,tv1: String? ) {
        this.OpcionID = OpcionID
        this.nombre = nombre
        this.nota1 = nota1
        this.nota2 = nota2
        this.nota3 = nota3
        this.nota4 = nota4
        this.nota5 = nota5
        this.tv1 = tv1
    }

    fun toMap(): Map<String, Any?> {
        return mapOf(
            "OpcionID" to OpcionID,
            "nombre" to nombre,
            "nota1" to nota1,
            "nota2" to nota2,
            "nota3" to nota3,
            "nota4" to nota4,
            "nota5" to nota5,
            "tv1" to tv1,
            "key" to key,
            "per" to per
        )
    }



}