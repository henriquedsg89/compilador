/**
 * Authors: Henrique & Octávio
 * Date: Nov 2013
 */
package utils

import java.io.{PrintWriter, File}
import scala.io.Source

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
