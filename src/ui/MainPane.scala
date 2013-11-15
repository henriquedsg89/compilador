package ui

import javax.swing.{JTextArea, JScrollPane, JPanel}
import java.awt.{Dimension, BorderLayout}

class MainPane(e : Editor) extends JPanel {

  val scroll = new JScrollPane(e)

  e.setText("Coloque aqui seu código")

  scroll.setPreferredSize(new Dimension(800,400))
  setPreferredSize(new Dimension(800,400))
  add(scroll, BorderLayout.CENTER)
}
