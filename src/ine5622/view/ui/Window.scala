package ine5622.view.ui

import javax.swing.{JMenu, JMenuBar, JFrame}

/**
 * User: henrique
 * Date: 29/10/13
 * Time: 21:46
 *
 */
class Window extends JFrame {

  val menu = new JMenuBar
  val file = new JMenu("Arquivo")
  val lex = new JMenu("An. Léxico")
  val sin = new JMenu("An. Sintático")
  val sem = new JMenu("An. Semântico")

  def createMenu {
    menu.add(file)
    menu.add(lex)
    menu.add(sin)
    menu.add(sem)
    setJMenuBar(menu)
  }

  def init() = {
    createMenu

    setTitle("Compilador INE5622")
    setSize(450, 350)
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
    setLocationRelativeTo(null)

    setVisible(true)
  }

}
