import controller.{ID_Constante, ID_Programa, Controller}
import javax.swing.SwingUtilities
import ui.MainWindow

object Main extends App {
  SwingUtilities.invokeLater(new Runnable() {
    def run() {
      val controller = new Controller
      val win = new MainWindow("Compilador INE5622", controller)
      win.init()
    }
  })
}
