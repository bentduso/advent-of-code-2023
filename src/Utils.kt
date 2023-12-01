import java.math.BigInteger
import java.security.MessageDigest
import kotlin.io.path.Path
import kotlin.io.path.readLines

/**
 * Reads lines from the given input txt file.
 *
 * @param name the name of the input txt file
 * @return a list of strings, where each string represents a line from the input file
 */
fun readInput(name: String): List<String> = Path("src/$name.txt").readLines()

/**
 * Converts the string to its MD5 hash value.
 *
 * @return the MD5 hash value of the string as a hexadecimal string representation.
 */
fun String.md5(): String = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
    .toString(16)
    .padStart(32, '0')

/**
 * The cleaner shorthand for printing output.
 *
 * Prints the given object or value to the standard output stream using the println() function. The method can be called on any object or value, and it will be printed to the console.
 *
 * @receiver The object or value to print.
 */
fun Any?.println() = println(this)
