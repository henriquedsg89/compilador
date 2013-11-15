package utils

import java.io.{PrintWriter, File}
import scala.io.Source

/**
 * User: henrique
 * Date: 15/11/13
 * Time: 11:45
 *
 */
object FileUtil {

  def writeToFile(file: File, s: String) {
    val writer = new PrintWriter(file)
    writer.write(s)
    writer.close()
  }


  def readAllFile(f : File) : String = {
    val source = Source.fromFile(f)
    val txt = source.mkString
    source.close()
    txt
  }
}
