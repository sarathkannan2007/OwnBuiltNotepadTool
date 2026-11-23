import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.UndoManager;
import javax.swing.JScrollPane;
import java.awt.event.*;
import java.awt.FileDialog;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.Format;
import java.awt.Font;
import java.awt.Color;



class GUI implements ActionListener
{
    JFrame Window;
    JTextArea textArea;
    JScrollPane scrollPane;
    boolean wordwrapOn = false;

    JMenuBar menuBar;
    JMenu fileMenu,EditMenu,FormatMenu,ColorMenu;
    JMenuItem New,Open,Save,SaveUs,Exit;
    JMenuItem Wrap,FontAriel,FontCSMS,FontTNR;
    JMenuItem Size8,Size12,Size16,Size20,Size24,Size28;
    JMenu Font,Size;
    JMenuItem color1,color2,color3;
    JMenuItem Undo,Redo;


    File_Function File = new File_Function(this);
    FormatFunction format = new FormatFunction(this);
    ColorFunction cf = new ColorFunction(this);
    UndoManager um = new UndoManager();
    EditFunction ef = new EditFunction(this);
    
    public static void main(String[] args)
    {
        GUI Gui = new GUI();
        
    }
    public GUI(){
        
        
        createWindow();
        createTextArea();
        createBar();
        createFileMenu();
        createFormat();
        createColor();
        createEdit();
        format.selectedFont = "Arial";
        format.createFont(16);
        cf.changeColor("White");
        Window.setVisible(true);
    }
    public void createWindow()
    {
        Window = new JFrame("NotePad");
        Window.setSize(600,400);
        Window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void createTextArea()
    {
        textArea = new JTextArea();
        textArea.getDocument().addUndoableEditListener(new UndoableEditListener() {
            public void undoableEditHappened(UndoableEditEvent e){
                um.addEdit(e.getEdit());
            }
        });
        scrollPane = new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        Window.add(scrollPane);
    }
    public void createBar()
    {
        menuBar = new JMenuBar();
        Window.setJMenuBar(menuBar);

        fileMenu = new JMenu("File");
        menuBar.add(fileMenu);

        EditMenu = new JMenu("Edit");
        menuBar.add(EditMenu);

        FormatMenu = new JMenu("Format");
        menuBar.add(FormatMenu);

        ColorMenu = new JMenu("Color");
        menuBar.add(ColorMenu);

    }
    public void createFileMenu()
    {
        New = new JMenuItem("New");
        fileMenu.add(New);
        New.addActionListener(this);
        New.setActionCommand("New");


        Open = new JMenuItem("Open");
        fileMenu.add(Open);
        Open.addActionListener(this);
        Open.setActionCommand("Open");

        Save = new JMenuItem("Save");
        fileMenu.add(Save);
        Save.addActionListener(this);
        Save.setActionCommand("Save");

        SaveUs = new JMenuItem("SaveAs");
        fileMenu.add(SaveUs);
        SaveUs.addActionListener(this);
        SaveUs.setActionCommand("SaveAs");

        Exit = new JMenuItem("Exit");
        fileMenu.add(Exit);
        Exit.addActionListener(this);
        Exit.setActionCommand("Exit");
    }

    public void createFormat()
    {
        Wrap = new JMenuItem("Word Wrap : Off");
        Wrap.addActionListener(this);
        Wrap.setActionCommand("Word Wrap");
        FormatMenu.add(Wrap);

        Font = new JMenu("Font");
        FormatMenu.add(Font);
        FontAriel = new JMenuItem("Arial");
        FontAriel.addActionListener(this);
        FontAriel.setActionCommand("Arial");
        Font.add(FontAriel);

        FontCSMS = new JMenuItem("CSMS");
        FontCSMS.addActionListener(this);
        FontCSMS.setActionCommand("CSMS");
        Font.add(FontCSMS);

        FontTNR = new JMenuItem("Times New Roman");
        FontTNR.addActionListener(this);
        FontTNR.setActionCommand("TNR");
        Font.add(FontTNR);

        Size = new JMenu("Size");
        FormatMenu.add(Size);

        Size8 = new JMenuItem("Size 8");
        Size8.addActionListener(this);
        Size8.setActionCommand("Size8");
        Size.add(Size8);

        Size12 = new JMenuItem("Size 12");
        Size12.addActionListener(this);
        Size12.setActionCommand("Size12");
        Size.add(Size12);

        Size16 = new JMenuItem("Size 16");
        Size16.addActionListener(this);
        Size16.setActionCommand("Size16");
        Size.add(Size16);

        Size20 = new JMenuItem("Size 20");
        Size20.addActionListener(this);
        Size20.setActionCommand("Size20");
        Size.add(Size20);

        Size24 = new JMenuItem("Size 24");
        Size24.addActionListener(this);
        Size24.setActionCommand("Size24");
        Size.add(Size24);

        Size28 = new JMenuItem("Size 28");
        Size28.addActionListener(this);
        Size28.setActionCommand("Size28");
        Size.add(Size28);

    }

    public void createColor()
    {
        color1 = new JMenuItem("White");
        color1.addActionListener(this);
        color1.setActionCommand("White");
        ColorMenu.add(color1);

        color2 = new JMenuItem("Blue");
        color2.addActionListener(this);
        color2.setActionCommand("Blue");
        ColorMenu.add(color2);

        color3 = new JMenuItem("Yellow");
        color3.addActionListener(this);
        color3.setActionCommand("Yellow");
        ColorMenu.add(color3);
    }

    public void createEdit()
    {
        Undo = new JMenuItem("Undo");
        Undo.addActionListener(this);
        Undo.setActionCommand("Undo");
        EditMenu.add(Undo);

        Redo = new JMenuItem("Redo");
        Redo.addActionListener(this);
        Redo.setActionCommand("Redo");
        EditMenu.add(Redo);

    }

    public void actionPerformed(ActionEvent e)
    {
        String string = e.getActionCommand();
         switch(string)
         {
            case "New":
                File.newFile();
                break;

            case "Open":
                File.OpenFile();
                break;

            case "SaveAs":
                File.SaveAs();
                break;

            case "Save":
                File.SaveFile();
                break;
                
            case "Exit":
                File.Exit();
                break;

            case "Word Wrap":
                format.wordWrap();
                break;

            case "Arial":
                format.setFont("Arial");
                break;
            
            case "CSMS":
                format.setFont("Comic Sans MS");
                break;

            case "TNR":
                format.setFont("Times New Roman");
                break;

            case "Size8":
                format.createFont(8);
                break;
            case "Size12":
                format.createFont(12);
                break;
            case "Size16":
                format.createFont(16);
                break;
            case "Size20":
                format.createFont(20);
                break;
            case "Size24":
                format.createFont(24);
                break;
            case "Size28":
                format.createFont(28);
                break;
            case "White":
                cf.changeColor("White");
                break;
            case "Blue":
                cf.changeColor("Blue");
                break;
            case "Yellow":
                cf.changeColor("Yellow");
                break;

            case "Undo":
                ef.undo();
                break;
            case "Redo":
                ef.redo();
                break;
         }
    }

}

class ColorFunction
{
    GUI Gui;
    ColorFunction(GUI gui)
    {
        this.Gui = gui;
    }
    public void changeColor(String color)
    {
        switch(color)
        {
            case "White":
                Gui.Window.getContentPane().setBackground(Color.white);
                Gui.textArea.setBackground(Color.white);
                Gui.textArea.setForeground(Color.black);
                break;

            case "Blue":
                Gui.Window.getContentPane().setBackground(Color.blue);
                Gui.textArea.setBackground(Color.blue);
                Gui.textArea.setForeground(Color.white);
                break;

            case "Yellow":
                Gui.Window.getContentPane().setBackground(Color.yellow);
                Gui.textArea.setBackground(Color.yellow);
                Gui.textArea.setForeground(Color.black);
                break;

        }
    }
}

class EditFunction
{
    GUI Gui;
    EditFunction(GUI gui)
    {
        this.Gui = gui;
    }
    public void undo()
    {
        Gui.um.undo();
    }
    public void redo()
    {
        Gui.um.redo();
    }
}

class File_Function
{
    GUI Gui;
    String FileName,FileAddress;
    File_Function(GUI gui)
    {
        this.Gui = gui;
    }
    public void newFile()
    {
        Gui.textArea.setText("");
        Gui.Window.setTitle("New File");
        FileName = null;
        FileAddress = null;
    }
    public void OpenFile()
    {
        FileDialog fd = new FileDialog(Gui.Window,"Open",FileDialog.LOAD);
        fd.setVisible(true);

        if(fd.getFile() != null){
            FileName = fd.getFile();
            FileAddress = fd.getDirectory();
            Gui.Window.setTitle(FileName);
        }

        try{
            BufferedReader br = new BufferedReader(new FileReader(FileAddress + FileName));
            Gui.textArea.setText("");
            String line = null;
            while((line = br.readLine()) != null)
            {
                Gui.textArea.append(line + "\n");    
            }
        }catch(Exception e)
        {
            System.out.println("Error : File Not Opened");
        }
    }

    public void SaveFile()
    {
        if(FileName == null)
        {
            SaveAs();
        }
        else
        {
            try
            {
                FileWriter fw = new FileWriter(FileAddress + FileName);
                fw.write(Gui.textArea.getText());
                Gui.Window.setTitle(FileName);
                fw.close();
            }
            catch(Exception e)
            {
                System.out.println("Something Wrong");
            }
        }
    }
    public void SaveAs()
    {
        FileDialog fd = new FileDialog(Gui.Window,"Save",FileDialog.SAVE);
        fd.setVisible(true);
        if(fd.getFile() != null)
        {
            FileName = fd.getFile();
            FileAddress = fd.getDirectory();
            Gui.Window.setTitle(FileName);
        }
        try{
            FileWriter fw = new FileWriter(FileAddress + FileName);
            fw.write(Gui.textArea.getText());
            Gui.Window.setTitle(FileName);
            fw.close();

        }catch(Exception e)
        {
            System.out.println("Something Wrong");
        }

    }
    public void Exit()
    {
        System.exit(0);
    }

}

class FormatFunction
{
    GUI Gui;
    Font arial,comicSansMS,timesnewRoman;
    String selectedFont;
    FormatFunction(GUI gui)
    {
        this.Gui = gui;
    }

    public void wordWrap()
    {
        if(Gui.wordwrapOn == false)
        {
            Gui.wordwrapOn = true;
            Gui.textArea.setLineWrap(true);
            Gui.textArea.setWrapStyleWord(true);
            Gui.Wrap.setText("Word Wrap : On");
        }
        else{
            Gui.wordwrapOn = false;
            Gui.textArea.setLineWrap(false);
            Gui.textArea.setWrapStyleWord(false);
            Gui.Wrap.setText("Word Wrap : Off");
        }
    }

    public void createFont(int fontSize)
    {
        arial = new Font("Arial",Font.PLAIN,fontSize);
        comicSansMS = new Font("Comic Sans MS",Font.PLAIN,fontSize);
        timesnewRoman = new  Font("Times New Roman",Font.PLAIN,fontSize);
        setFont(selectedFont);
    }
    public void setFont(String font){
        selectedFont = font;
        switch(selectedFont)
        {
            case "Arial":
                Gui.textArea.setFont(arial);
                break;
            case "Comic Sans MS":
                Gui.textArea.setFont(comicSansMS);
                break;
            case "Times New Roman":
                Gui.textArea.setFont(timesnewRoman);
                break;
        }
    }
}
