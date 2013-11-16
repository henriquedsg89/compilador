/**
 * Authors: Henrique & Octávio
 * Date: Nov 2013
 */
package ui

import javax.swing._
import java.awt.{Dimension, BorderLayout}
import java.awt.event.{ActionEvent, ActionListener}

import java.util.logging.Logger
import utils.FileUtil
import controller.Controller

/** Classes responsável por criar e interagir com a interface */
class MainWindow(name: String, controller: Controller) extends JFrame {

  val log = Logger.getLogger("MainWindow")

  val menu = new JMenuBar()

  val file = new JMenu("Arquivo")
  val load = new JMenuItem("Carregar")
  val save = new JMenuItem("Save")

  val anal = new JMenu("Analisador")
  val lex = new JMenuItem("Léxico")
  val sin = new JMenuItem("Sintático")
  val sem = new JMenuItem("Semântico")

  val editor = new Editor()
  val errorArea = new ErrorArea()

  val fc = new JFileChooser()

  /* criando os menus */
  def createMenu() {
    load.addActionListener(loadAction)
    save.addActionListener(saveAction)

    lex.addActionListener(lexAction)
    sin.addActionListener(sinAction)

    file.add(load)
    file.add(save)

    anal.add(lex)
    anal.add(sin)
    anal.add(sem)


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

    val scroll = new JScrollPane(editor)
    scroll.setPreferredSize(new Dimension(800,400))
    setPreferredSize(new Dimension(800,400))

    setLayout(new BorderLayout())
    add(scroll, BorderLayout.CENTER)
    add(new JScrollPane(errorArea), BorderLayout.SOUTH)

    setVisible(true)
  }

  /* acao invocada pelo menu lexico */
  val lexAction = new ActionListener {
    def actionPerformed(e: ActionEvent) {
      // chama o controller para validar
      val error = controller.validateLexical(editor.getText)

      //coloca na area de erro a msg (primeira parte da tupla)
      errorArea.setText(error._1)
      editor.requestFocus()
      //posiciona o cursor no token com error (segunda parte da tupla)
      editor.setCaretPosition(error._2)
    }
  }

  val sinAction = new ActionListener {
    def actionPerformed(e: ActionEvent) {
      val error = controller.validateSyntatic(editor.getText)
      errorArea.setText(error._1)
      editor.requestFocus()
      editor.setCaretPosition(error._2)
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

          editor.setText(fileTxt)

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

          val txt = editor.getText

          FileUtil.writeToFile(file, txt)
          log.info("Txt to file = " + txt)

          editor.setText(txt)

        }
        case _ => {
          log.info("FileC Cancel")
        }
      }
    }
  }
}
