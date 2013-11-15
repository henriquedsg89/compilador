package UI

import javax.swing.JTextArea
import java.awt.Dimension

/**
 * User: henrique
 * Date: 15/11/13
 * Time: 12:10
 *
 */
class ErrorArea extends JTextArea {

  setPreferredSize(new Dimension(750, 120))
  setText("Area de erros")
}
