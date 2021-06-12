package pl.kinga.wristapp.util


import java.security.SecureRandom
import java.util.*

class RandomString(length: Int = 21, random: Random = SecureRandom(), symbols: String = alphanum) {

    private val random: Random
    private val symbols: CharArray
    private val buf: CharArray

    init {
        require(length >= 1)
        require(symbols.length >= 2)
        this.random = random
        this.symbols = symbols.toCharArray()
        buf = CharArray(length)
    }

    companion object {
        const val upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
        val lower: String = upper.toLowerCase(Locale.ROOT)
        const val digits = "0123456789"
        val alphanum = upper + lower + digits

        fun upper(length: Int = 6) = RandomString(length, symbols = upper)
    }

    fun nextString(): String {
        for (idx in buf.indices) buf[idx] = symbols[random.nextInt(symbols.size)]
        return String(buf)
    }
}
