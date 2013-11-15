package ui

import javax.swing._
import java.awt.{Color, Dimension, BorderLayout}
import java.awt.event.{ActionEvent, ActionListener}

import java.util.logging.Logger
import utils.FileUtil
import gals._

class MainWindow(val name: String) extends JFrame {

  val log = Logger.getLogger("MainWindow")

  val menu = new JMenuBar()

  val file = new JMenu("Arquivo")
  val load = new JMenuItem("Carregar")
  val save = new JMenuItem("Save")

  val anal = new JMenu("Analisador")
  val lex = new JMenuItem("Léxico")
  val sin = new JMenuItem("Sintático")
  val sem = new JMenuItem("Semântico")

  val editor = new Editor
  val errorArea = new ErrorArea()

  val fc = new JFileChooser()

  def createMenu() {
    load.addActionListener(loadAction)
    save.addActionListener(saveAction)
    lex.addActionListener(lexAction)

    file.add(load)
    file.add(save)

    anal.add(lex)
    anal.add(sin)
    anal.add(sem)


    menu.add(file)
    menu.add(anal)

    setJMenuBar(menu)
  }

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

  val lexAction = new ActionListener {
    def actionPerformed(e: ActionEvent) {
      val lexico = new Lexico
      lexico.setInput(editor.getText)

      var token: Token = lexico.nextToken()

      do {
        log.info("Read token = " + token)
        token = lexico.nextToken()
      } while (token != null)

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
