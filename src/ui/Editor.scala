package ui

import javax.swing._
import javax.swing.undo.{CannotRedoException, CannotUndoException, UndoManager}
import java.awt.{BorderLayout, Color, Font}
import javax.swing.event._
import javax.swing.text.Element
import java.util.logging.Logger
import java.awt.event.ActionEvent

class Editor extends JPanel {
  var scrollPane: JScrollPane = _
  var textArea: JTextArea = _
  var lineNumberBar: JTextArea = _
  var cursorStatusBar: JTextField = _
  var savedStatus: Boolean = _
  var FileName: String = ""
  var fileDir: String = ""
  val undoManager: UndoManager = new UndoManager()
  val log = Logger.getLogger("MainWindow")

  def Editor() {
    textArea = new JTextArea()
    textArea.setFont(new Font("MONOSPACED", Font.PLAIN, 14))
    textArea.setLineWrap(false)
    textArea.setWrapStyleWord(true)
    lineNumberBar = new JTextArea("1")
    lineNumberBar.setFont(textArea.getFont)
    lineNumberBar.setBackground(Color.LIGHT_GRAY)
    lineNumberBar.setEditable(false)
    textArea.getDocument.addDocumentListener(new DocumentListener {
      def getText(): String = {
        val cursorLocation: Int = textArea.getDocument.getLength
        val rootElement: Element = textArea.getDocument.getDefaultRootElement
        var sideNumer: String = "1" + System.getProperty("line.separator")
        for (i <- 2 until rootElement.getElementIndex(cursorLocation) + 2) {
          sideNumer += i + System.getProperty("line.separator")
        }
        sideNumer
      }
      def insertUpdate(e: DocumentEvent) {
        lineNumberBar.setText(getText())
        savedStatus = false
      }

      def changedUpdate(e: DocumentEvent) {
        lineNumberBar.setText(getText())
        savedStatus = false
      }

      def removeUpdate(e: DocumentEvent) {
        lineNumberBar.setText(getText())
        savedStatus = false
      }
    })
    scrollPane = new JScrollPane()
    scrollPane.getViewport.add(textArea)
    scrollPane.setRowHeaderView(lineNumberBar)
//    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS)
    scrollPane.setBorder(
      BorderFactory.createCompoundBorder(
        BorderFactory.createCompoundBorder(
          BorderFactory.createLineBorder(new Color(111)),
          BorderFactory.createEmptyBorder(6, 6, 6, 6)
        ), scrollPane.getBorder
      )
    )
    textArea.addCaretListener(new CaretListener {
      def caretUpdate(e: CaretEvent) {
        val dArea: JTextArea = e.getSource.asInstanceOf[JTextArea]
        var rowNum = 1
        var colNum = 1
        try {
          val caretpos = dArea.getCaretPosition
          rowNum = dArea.getLineOfOffset(caretpos)
          colNum = caretpos - dArea.getLineStartOffset(rowNum)
          rowNum += 1
        } catch {
          case ex: Exception => log.warning(ex.getMessage)
        }
        updatesBar(rowNum,colNum)
      }
    })
    this.setLayout(new BorderLayout())
    this.add(scrollPane, BorderLayout.CENTER)
    cursorStatusBar = new JTextField()
    updatesBar(1, 0)
    textArea.getDocument.addUndoableEditListener(new UndoableEditListener {
      def undoableEditHappened(e: UndoableEditEvent) {
        undoManager.addEdit(e.getEdit)
      }
    })
    textArea.getActionMap.put("Undo", new AbstractAction("Undo") {
      def actionPerformed(e: ActionEvent) {
        undo()
      }
    })
    textArea.getActionMap.put("Redo", new AbstractAction() {
      def actionPerformed(e: ActionEvent) {
        redo()
      }
    })
  }

  def undo() {
    try {
      if(undoManager.canUndo)
        undoManager.undo()
    } catch {
      case ex: CannotUndoException => log.warning(ex.getMessage)
    }
  }

  def redo() {
    try {
      if(undoManager.canRedo)
        undoManager.redo()
    } catch {
      case ex: CannotRedoException => log.warning(ex.getMessage)
    }
  }

  def updatesBar(rowNum: Int, colNum: Int) {
    cursorStatusBar.setText("Line: " + rowNum +
                          "Column: " + colNum)
  }
}
