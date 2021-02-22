import java.util.*

class Mail {
    var sMail: String? = null
    var sUsuari: String? = null

    constructor(mail: String?, usuari: String?) {
        this.sMail = mail
        this.sUsuari = usuari
    }

    constructor() {}


    fun getMail(): String? {
        return sMail
    }

    fun setMail(Mail: String) {
        this.sMail = Mail
    }

    fun getUsuari(): String? {
        return sUsuari
    }

    fun setMessageUser(usuari: String) {
        sUsuari = usuari
    }

    }

}