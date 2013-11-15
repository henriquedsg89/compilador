import javax.swing.SwingUtilities
import ui.MainWindow

object Main extends App {
  SwingUtilities.invokeLater(new Runnable() {
    def run() {
      val win = new MainWindow("Compilador INE5622")
      win.init()
    }
  })
}
