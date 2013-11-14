package UI

import javax.swing.{JPanel, JMenu, JMenuBar, JFrame}
import java.awt.{Button, BorderLayout}

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
    setSize(800, 600)
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
    setLocationRelativeTo(null)

    setLayout(new BorderLayout())
    add(new MainPane(), BorderLayout.CENTER)

    setVisible(true)
  }
}
