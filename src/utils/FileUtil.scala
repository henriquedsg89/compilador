package utils

import java.io.File
import scala.io.Source

/**
 * User: henrique
 * Date: 15/11/13
 * Time: 11:45
 *
 */
object FileUtil {

  def readAllFile(f : File) : String = {
    val source = Source.fromFile(f)
    val txt = source.mkString
    source.close()
    txt
  }
}
