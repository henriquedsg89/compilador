package editor

import javax.swing.JTextArea

object Editor extends JTextArea {
  val doc = this.getDocument
  //TODO: Área de edição de texto que será usada pra ser analizada lexicamente e sintaticamente
  //TODO: Criar buffer auxiliar p/ implementar a interface JAVA UndoableEdit
}
