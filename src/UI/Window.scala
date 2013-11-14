package UI

import javax.swing.{JMenu, JMenuBar, JFrame}
import java.awt.BorderLayout

class Window extends JFrame {

  val menu = new JMenuBar()
  val file = new JMenu("Arquivo")
  val lex = new JMenu("An. Léxico")
  val sin = new JMenu("An. Sintático")
  val sem = new JMenu("An. Semântico")
  val mainPane = new MainPane()

  def createMenu() {
    menu.add(file)
    menu.add(lex)
    menu.add(sin)
    menu.add(sem)
    setJMenuBar(menu)
  }

  def init() = {
    createMenu()

    setTitle("Compilador INE5622")
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
    setLocationRelativeTo(null)

    setLayout(new BorderLayout())

//    getContentPane().add(mainPane)

    pack()
  }
}
