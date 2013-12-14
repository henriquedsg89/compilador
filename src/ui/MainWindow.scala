/**
 * Authors: Henrique & Octávio
 * Date: Nov 2013
 */
package ui

import javax.swing._
import java.awt.{Dimension, BorderLayout}
import java.awt.event.{InputEvent, KeyEvent, ActionEvent, ActionListener}
import java.util.logging.Logger
import utils.FileUtil
import controller.Controller

/** Classe responsável por criar e interagir com a interface da janela principal */
class MainWindow(name: String, controller: Controller) extends JFrame {

  val log = Logger.getLogger("MainWindow")

  val menu = new JMenuBar()

  val file = new JMenu("File")
  val load = new JMenuItem("Open")
  val save = new JMenuItem("Save")
  val undo = new JMenuItem("Undo")
  val redo = new JMenuItem("Redo")

  val anal = new JMenu("Analisador")
  val lex = new JMenuItem("Léxico")
  val sin = new JMenuItem("Sintático e Semântico")

  val editor: Editor = new Editor()
  editor.init()
  val errorArea = new ErrorArea()

  val fc = new JFileChooser()

  /* criando os menus */
  def createMenu() {
    load.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK))
    load.setActionCommand("open")
    load.addActionListener(loadAction)

    save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK))
    save.setActionCommand("save")
    save.addActionListener(saveAction)

    undo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_MASK))
    undo.setActionCommand("undo")
    undo.addActionListener(undoAction)

    redo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, InputEvent.CTRL_MASK))
    redo.setActionCommand("redo")
    redo.addActionListener(redoAction)

    lex.addActionListener(lexAction)
    sin.addActionListener(sinAction)

    file.add(load)
    file.add(save)
    file.addSeparator()
    file.add(undo)
    file.add(redo)

    anal.add(lex)
    anal.add(sin)


    menu.add(file)
    menu.add(anal)

    setJMenuBar(menu)
  }

  /* iniciando, definindo tamanho etc*/
  def init() = {
    createMenu()
    setTitle(name)
    setSize(800, 600)
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
    setLocationRelativeTo(null)

    setPreferredSize(new Dimension(800,400))

    setLayout(new BorderLayout())
    add(editor, BorderLayout.CENTER)
    add(new JScrollPane(errorArea), BorderLayout.SOUTH)

    setVisible(true)
  }

  /* acao invocada pelo menu lexico */
  val lexAction = new ActionListener {
    def actionPerformed(e: ActionEvent) {
      errorArea.setText("")
      // chama o controller para validar
      val error = controller.validateLexical(editor.textArea.getText)

      //coloca na area de erro a msg (primeira parte da tupla)
      errorArea.setText(error._1)
      editor.textArea.requestFocus()
      //posiciona o cursor no token com error (segunda parte da tupla)
      editor.textArea.setCaretPosition(error._2)
    }
  }

  val sinAction = new ActionListener {
    def actionPerformed(e: ActionEvent) {
      errorArea.setText("")
      val error = controller.validateSyntatic(editor.textArea.getText)
      errorArea.setText(error._1)
      editor.textArea.requestFocus()
      editor.textArea.setCaretPosition(error._2)
    }
  }

  val loadAction = new ActionListener {
    def actionPerformed(e: ActionEvent) {
      val ret = fc.showOpenDialog(MainWindow.this)

      ret match {
        case JFileChooser.APPROVE_OPTION  => {
          val file = fc.getSelectedFile
          log.info("File selected " + file.getName)

          val fileTxt = FileUtil.readAllFile(file)
          log.info("File txt = " + fileTxt)

          editor.textArea.setText(fileTxt)

        }
        case _ => {
          log.info("FileC Cancel")
        }
      }
    }
  }

  val saveAction = new ActionListener {
    def actionPerformed(e: ActionEvent) {
      val ret = fc.showSaveDialog(MainWindow.this)

      ret match {
        case JFileChooser.APPROVE_OPTION  => {
          val file = fc.getSelectedFile
          log.info("File selected " + file.getName)

          val txt = editor.textArea.getText

          FileUtil.writeToFile(file, txt)
          log.info("Txt to file = " + txt)

          editor.textArea.setText(txt)

        }
        case _ => {
          log.info("FileC Cancel")
        }
      }
    }
  }

  val undoAction = new ActionListener {
    def actionPerformed(e: ActionEvent) {
      editor.undo()
    }
  }

  val redoAction = new ActionListener {
    def actionPerformed(e: ActionEvent) {
      editor.redo()
    }
  }
}