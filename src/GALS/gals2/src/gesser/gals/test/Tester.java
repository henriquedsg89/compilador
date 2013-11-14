/*
 * Copyright 2004 Carlos Eduardo Gesser
 * 
 * This program is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation; either version 2 of the License, or (at your option)
 * any later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for
 * more details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program; if not, you can get it at http://www.gnu.org/licenses/gpl.txt
 */

package gesser.gals.test;

import gesser.gals.grammar.core.Grammar;
import gesser.gals.grammar.core.Symbol;
import gesser.gals.grammar.dom.GrammarDecl;
import gesser.gals.grammar.dom.ItemListDecl;
import gesser.gals.grammar.dom.ProductionDecl;
import gesser.gals.grammar.dom.tokens.NonTerminalToken;
import gesser.gals.grammar.parser.AnalysisError;
import gesser.gals.grammar.parser.Generator;
import gesser.gals.grammar.parser.Parser;
import gesser.gals.grammar.parser.Scanner;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class Tester extends JFrame implements ActionListener
{
    DefaultMutableTreeNode root = new DefaultMutableTreeNode();
    DefaultTreeModel model = new DefaultTreeModel(root);
    JTree tree = new JTree(model);
    
    DefaultListModel symbols = new DefaultListModel();   
    
	JTextArea textArea = new JTextArea();	
	
	public Tester()
	{
		super("GALS 2");
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		textArea.setText(
			
			"<C> ::= if <E> then <C> ( else <C> )?\n" +
			"| begin <C>+ end;\n" +
			"<C> ::= id <E> ( \",\" <E> )*;"
			
			
			/**
			"<G> ::= <P>+ ;\n"+
			"<P> ::= NT \"::=\" <PLIST> \";\" ;\n"+
			"<PLIST> ::= #5 <ITEM_LIST> ( \"|\" #5 <ITEM_LIST> )* ;\n"+
			"<ITEM_LIST> ::= <ITEM>+ | EPSILON ;\n"+
			"<ITEM> ::= ( <S> | \"(\" <PLIST> \")\" ) <CARD>? ;\n"+
			"<CARD> ::= \"*\" | \"+\" | \"?\" ;\n"+
			"<S> ::= T | NT | SA ;"
			
			/**/
		);
		
		
		JButton btn = new JButton("Testar");
		btn.addActionListener(this);
		
		add(new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
		        new JSplitPane(JSplitPane.VERTICAL_SPLIT,
		                new JScrollPane(tree),
		                new JScrollPane(new JList(symbols))
		        ),	        
		        new JScrollPane(textArea)) );
		
		add(btn, BorderLayout.SOUTH);
		
		setBounds(100, 100, 300, 300);
		
		tree.setRootVisible(false);
		
		tree.getSelectionModel().addTreeSelectionListener(new TreeSelectionListener(){
            public void valueChanged(TreeSelectionEvent e)
            {
                DefaultMutableTreeNode n = (DefaultMutableTreeNode) e.getNewLeadSelectionPath().getLastPathComponent();
                if (n.getUserObject() == null)
                    return;
                if (n.getUserObject() instanceof ItemListDecl)
                {
                	ItemListDecl item = (ItemListDecl) n.getUserObject();
                    textArea.requestFocus();
                    textArea.getCaret().setDot(item.getItems().get(0).getStart());
		            textArea.getCaret().moveDot(item.getItems().get(item.getItems().size()-1).getEnd());
                }
                else if (n.getUserObject() instanceof NonTerminalToken)
                {
                    NonTerminalToken item = (NonTerminalToken) n.getUserObject();
                    textArea.requestFocus();
                    textArea.getCaret().setDot(item.getPosition());
		            textArea.getCaret().moveDot(item.getPosition()+item.getLexeme().length());
                }
                else
                    System.out.println(n.getUserObject() + " - " + n.getUserObject().getClass());
            }
		});
	}
	
	public void actionPerformed(ActionEvent e)
	{
		try
		{
			Scanner s = new Scanner(textArea.getText());
			Generator g = new Generator();
			Parser p = new Parser();
			
			Symbol.clearSymbols();
			
			p.parse(s, g);
			
			GrammarDecl gd = g.getGrammar();
			
			populateTree(gd);
			
			System.out.println(gd);
			System.out.println("-----------");
			Grammar grm = Grammar.process(gd);
			System.out.println(grm);
			for (Symbol symb : Symbol.allSymbols())
			{
				System.out.println(symb + " - " + symb.getFirst());
			}
		}
		catch (AnalysisError ex)
		{
			ex.printStackTrace();
		}
	}
	
	private void populateTree(GrammarDecl gd)
	{
	    root.removeAllChildren();
	    tree.collapseRow(0);
	    	    
	    for (ProductionDecl p : gd.getProductions())
	    {
	        DefaultMutableTreeNode lhs = new DefaultMutableTreeNode(p.getLhs());
	        
	        root.add(lhs);
	        
	        populateList(lhs, p.getItems());	       
	    }
	    
	    model.reload();
	    symbols.clear();
	    for (Symbol s : Symbol.allSymbols())
	    {
	        //if (!s.isArtificial() && !(s instanceof SemanticAction))
	                symbols.addElement(s);
	    }
	}
	
	private void populateList(DefaultMutableTreeNode root, Collection<ItemListDecl> itemLists)
	{
	    for (ItemListDecl itl : itemLists)
        {
            DefaultMutableTreeNode list = new DefaultMutableTreeNode(itl);
            
            root.add(list);
        }
	}

	
	public static void main(String[] args)
	{
		new Tester().setVisible(true);
	}
}
