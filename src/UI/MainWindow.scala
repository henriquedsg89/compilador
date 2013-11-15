package UI

import javax.swing._
import java.awt.{Color, Dimension, BorderLayout}

class MainWindow(val name: String) extends JFrame {
  val menu = new JMenuBar()
  val file = new JMenu("Arquivo")
  val lex = new JMenu("An. Léxico")
  val sin = new JMenu("An. Sintático")
  val sem = new JMenu("An. Semântico")
  val southPanel: JPanel = new JPanel()

  def createMenu() {
    menu.add(file)
    menu.add(lex)
    menu.add(sin)
    menu.add(sem)
    setJMenuBar(menu)
  }

  def init() = {
    createMenu()
    setTitle(name)
    setSize(800, 600)
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
    setLocationRelativeTo(null)

    setLayout(new BorderLayout())
    add(new JTextArea(), BorderLayout.CENTER)
    add(southPanel, BorderLayout.SOUTH)

    setVisible(true)
  }
  def initSouthPanel() {
    southPanel.setSize(100, 500)
    southPanel.setBackground(Color.black)
  }
}
