package UI

import javax.swing.{JTextArea, JScrollPane, JPanel}
import java.awt.{Dimension, BorderLayout}

class MainPane extends JPanel {

  val textArea = new JTextArea()
  val scroll = new JScrollPane(textArea)

  textArea.setText("Coloque aqui seu código")

  scroll.setPreferredSize(new Dimension(800,400))
  setPreferredSize(new Dimension(800,400))
  add(scroll, BorderLayout.CENTER)
}
