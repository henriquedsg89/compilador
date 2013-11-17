/**
 * Authors: Henrique & Octávio
 * Date: Nov 2013
 */
package ui

import javax.swing.JTextArea
import java.awt.Dimension

class ErrorArea extends JTextArea {

  setPreferredSize(new Dimension(750, 120))
  setText("Area de erros")
}
