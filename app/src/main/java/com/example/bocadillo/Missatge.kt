import java.util.*

class Missatge {
    var sMissatge: String? = null
    var sUsuari: String? = null
    var lData: Long = 0

    constructor(missatge: String?, usuari: String?) {
        this.sMissatge = missatge
        this.sUsuari = usuari
        lData = Date().getTime()
    }

    constructor() {}


    fun getMissatge(): String? {
        return sMissatge
    }

    fun setMissatge(missatge: String) {
        this.sMissatge = missatge
    }

    fun getUsuari(): String? {
        return sUsuari
    }

    fun setUsuari(usuari: String) {
        sUsuari = usuari
    }

    fun getData(): Long {
        return lData
    }

    fun setData(data: Long) {
        lData = data
    }

}