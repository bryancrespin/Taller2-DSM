package com.example.segundotallerpracticodsm

class Persona2 {
    fun key(key: String?) {
    }


    var OpcionID2: String? = null
    var nombre: String? = null
    var sueldo: String? = null
    var tv2: String? = null
    var key: String? = null
    var per: MutableMap<String, Boolean> = HashMap()

    constructor() {}
    constructor(OpcionID2: String?,nombre: String?,sueldo: String?,tv2: String? ) {
        this.OpcionID2 = OpcionID2
        this.nombre = nombre

        this.sueldo = sueldo

        this.tv2 = tv2
    }

    fun toMap(): Map<String, Any?> {
        return mapOf(
            "OpcionID2" to OpcionID2,
            "nombre" to nombre,

            "sueldo" to sueldo,

            "tv2" to tv2,
            "key" to key,
            "per" to per
        )
    }



}