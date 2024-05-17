import javax.swing.*;
import javax.swing.event.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.awt.font.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

class Editor implements ActionListener, ChangeListener
{
    public JFrame frame;
    public JTextArea textArea;
    public JButton bold, italics, copy, cut, paste, save, underline, colours, toggle, open;
    public JPanel panel, file, fontArea, clipboard, mode;
    public JSpinner spinner;
    public JScrollPane scrollPane;
    public JComboBox<String> box = new JComboBox<>();
    public JMenuBar menuBar;
    public JMenu menu;
    public JMenuItem mail, whatsapp;
    public Editor()
    {
        String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();

        frame = new JFrame("RetroPad");          //main frame
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        textArea = new JTextArea();                                           //textArea
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setFont(new Font("Arial", Font.PLAIN, 25));
        textArea.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));

        scrollPane = new JScrollPane(textArea);                                //scrollPane
        scrollPane.setVisible(true);
        scrollPane.setPreferredSize(new Dimension(500, 100));
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        file = new JPanel();               //File panel
        save = new JButton("Save");              //save btn
        save.setName("Save");  
        save.setBorderPainted(false);
        save.setFocusPainted(false);
        save.setContentAreaFilled(false);      
        save.addActionListener(this);

        open = new JButton("Open");
        open.setName("Open");
        open.setBorderPainted(false);
        open.setFocusPainted(false);
        open.setContentAreaFilled(false); 
        open.addActionListener(this);

        menuBar = new JMenuBar();
        menu = new JMenu("Share");
        menu.setName("Share");
        whatsapp = new JMenuItem("WhatsApp");
        whatsapp.setName("whatsapp");
        mail = new JMenuItem("E-mail");
        mail.setName("mail");
        menu.add(whatsapp);
        menu.add(mail);
        menuBar.add(menu);
        whatsapp.setActionCommand("whatsapp");
        mail.setActionCommand("mail");
        mail.addActionListener(this);
        whatsapp.addActionListener(this);

        file.setLayout(new FlowLayout());
        file.add(open);
        file.add(save);
        file.add(menuBar);
        file.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        fontArea = new JPanel();        //font panel
        box = new JComboBox<>();        //Font family
        for (String string : fonts) {
            box.addItem(string);
        }
        box.setSelectedItem("Arial");  
        box.setName("box");
        box.addActionListener(this);

        spinner = new JSpinner();           //Font size
        spinner.setName("spinner");
        spinner.setPreferredSize(new Dimension(50,25));   
        spinner.setValue(19);
        spinner.addChangeListener(this);

        colours = new JButton("Color");        //colours button
        colours.setName("color");
        colours.setFocusPainted(false);
        colours.setContentAreaFilled(false);
        colours.setBorderPainted(false);
        colours.setPreferredSize(new Dimension(50, 25));
        colours.addActionListener(this);

        bold = new JButton("𝗕");
        bold.setName("Bold");                  //bold button
        bold.setBorderPainted(false);
        bold.setFocusPainted(false);
        bold.setContentAreaFilled(false);
        bold.addActionListener(this);

        italics = new JButton("𝘐");
        italics.setName("italics");               //italics btn
        italics.setBorderPainted(false);
        italics.setContentAreaFilled(false);
        italics.setFocusPainted(false);
        italics.addActionListener(this);

        underline = new JButton("U̲");
        underline.setName("underline");
        underline.setBorderPainted(false);
        underline.setContentAreaFilled(false);
        underline.setFocusPainted(false);
        underline.addActionListener(this);

        fontArea.setLayout(new GridLayout(2, 3));
        fontArea.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        fontArea.add(box);
        fontArea.add(spinner);
        fontArea.add(colours);
        fontArea.add(bold);
        fontArea.add(italics);
        fontArea.add(underline);

        clipboard = new JPanel();                               //clipboard pannel
        clipboard.setLayout(new GridLayout(1,3));
        copy = new JButton();
        copy.setText("📄 \nCopy");                           //copy btn
        copy.setBorderPainted(false); 
        copy.setFocusPainted(false); 
        copy.setContentAreaFilled(false);
        copy.addActionListener(this);

        cut = new JButton();
        cut.setText("✂ Cut");                              //cut btn  
        cut.setBorderPainted(false); 
        cut.setFocusPainted(false); 
        cut.setContentAreaFilled(false);
        cut.addActionListener(this);

        paste = new JButton();
        paste.setText("📋 Paste");                         //paste btn  
        paste.setBorderPainted(false); 
        paste.setFocusPainted(false); 
        paste.setContentAreaFilled(false);
        paste.addActionListener(this);

        clipboard.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        clipboard.add(cut);
        clipboard.add(copy);
        clipboard.add(paste);

        toggle = new JButton("☀\nMode"); //toggle btn
        toggle.setName("toggle");
        toggle.setBorderPainted(false);
        toggle.setContentAreaFilled(false);
        toggle.setFocusPainted(false);
        toggle.addActionListener(this);
        toggle.setForeground(Color.BLACK);
        mode = new JPanel();
        mode.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        mode.add(toggle);
                                 
        panel = new JPanel();                                   //Menu panel
        panel.setLayout(new GridLayout(1, 4));
        panel.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        panel.add(file);
        panel.add(fontArea);
        panel.add(clipboard);
        panel.add(mode);

        //final process
        frame.setLayout(new BorderLayout());
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(panel, BorderLayout.NORTH);
        frame.setVisible(true);
    }
    public void actionPerformed(ActionEvent e)  /* ALL ACTIONS */
    {
        if (e.getSource() == save) 
        {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
            fileChooser.setFileFilter(new FileNameExtensionFilter("Text Files (*.txt)", "txt"));
            fileChooser.setFileFilter(new FileNameExtensionFilter("Pdfs....", "pdf"));
            fileChooser.setApproveButtonText("Save");
            int result = fileChooser.showSaveDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) 
            {
                File selectedFile = fileChooser.getSelectedFile();
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(selectedFile))) 
                {
                    writer.write(textArea.getText());
                } 
                catch (IOException d) 
                {
                    System.err.println("Error saving file: " + d.getMessage());
                }
            }   
        }
        if (e.getSource() == open) 
        {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
            fileChooser.setFileFilter(new FileNameExtensionFilter("Text Files (*.txt)", "txt"));
            fileChooser.setFileFilter(new FileNameExtensionFilter("Pdfs....", "pdf"));
            int result = fileChooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                try (BufferedReader reader = new BufferedReader(new FileReader(selectedFile))) {
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) 
                    {
                        stringBuilder.append(line).append("\n");
                    }
                    textArea.setText(stringBuilder.toString());
                }
                catch (IOException ex) 
                {
                    System.err.println("Error opening file: " + ex.getMessage());
                }
            }
        }
        if(e.getSource() == box)
        {
            textArea.setFont(new Font((String) box.getSelectedItem(), textArea.getFont().getStyle(), textArea.getFont().getSize()));
        }
        if(e.getSource() == colours) {
            Color f = JColorChooser.showDialog(null, "Choose a color", Color.BLACK); 
            textArea.setForeground(f);
        }
        if (e.getSource() == bold) 
        {   
            Font currentFont = textArea.getFont();
            Map<TextAttribute, Object> attributes = new HashMap<>(currentFont.getAttributes());
            int style = Font.BOLD;

            boolean isPlain = textArea.getFont().getStyle() == Font.PLAIN;
            boolean isItalic = textArea.getFont().getStyle() == Font.ITALIC;
            boolean isBold = textArea.getFont().getStyle() == Font.BOLD;
            boolean hasUnderline = attributes.get(TextAttribute.UNDERLINE) != null;
            if (isPlain) {
                style = Font.BOLD;
            }
            if (isItalic) {
                style |= Font.ITALIC;
            }
            if (hasUnderline) {
                style |= TextAttribute.UNDERLINE_ON;
            }
            if (isBold){
                style = Font.PLAIN;
            }
            textArea.setFont(textArea.getFont().deriveFont(style));
        }
        if (e.getSource() == italics) 
        {
            Font currentFont = textArea.getFont();
            Map<TextAttribute, Object> attributes = new HashMap<>(currentFont.getAttributes());
            int style = Font.ITALIC;

            boolean isPlain = textArea.getFont().getStyle() == Font.PLAIN;
            boolean isBold = textArea.getFont().getStyle() == Font.BOLD;
            boolean isItalic = textArea.getFont().getStyle() == Font.ITALIC;
            boolean hasUnderline = attributes.get(TextAttribute.UNDERLINE) != null;
            if (isPlain) {
                style = Font.ITALIC;
            }
            if (isBold) {
                style |= Font.BOLD;
            }
            if (isItalic) {
                style = Font.PLAIN;
            }
            if (hasUnderline) {
                style |= TextAttribute.UNDERLINE_ON;
            }
            textArea.setFont(textArea.getFont().deriveFont(style));    
        }
        if(e.getSource() == underline)
        {
            Font currentFont = textArea.getFont();
            Map<TextAttribute, Object> attributes = new HashMap<>(currentFont.getAttributes());
            boolean hasUnderline = attributes.get(TextAttribute.UNDERLINE) != null;
            if (hasUnderline) {
                attributes.remove(TextAttribute.UNDERLINE);
            }
            else{
                attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
            }
            textArea.setFont(textArea.getFont().deriveFont(attributes));
        }
        if (e.getSource() == copy){
            textArea.copy();
        }
        if (e.getSource() == cut) {
            textArea.cut();
        }
        if (e.getSource() == paste){
            textArea.paste();
        }
        if(e.getSource() == toggle)
        {
            if(toggle.getForeground() == Color.BLACK)
            {
                textArea.setBackground(Color.BLACK);  //textArea
                if(textArea.getForeground() == Color.BLACK)
                {
                    textArea.setForeground(Color.WHITE);
                }
                textArea.setCaretColor(Color.GREEN);
                fontArea.setBackground(Color.DARK_GRAY);
                fontArea.setForeground(Color.WHITE); //fontArea
                for (Component component : fontArea.getComponents()) 
                {
                    component.setForeground(Color.WHITE);
                    component.setBackground(Color.DARK_GRAY);
                }
                mode.setBackground(Color.DARK_GRAY);
                mode.setForeground(Color.WHITE);
                toggle.setBackground(Color.DARK_GRAY); //toggle
                toggle.setForeground(Color.WHITE);
                clipboard.setBackground(Color.DARK_GRAY);
                clipboard.setForeground(Color.WHITE);
                for (Component component : clipboard.getComponents())
                {
                    component.setForeground(Color.WHITE);
                    component.setBackground(Color.DARK_GRAY);
                }
                file.setBackground(Color.DARK_GRAY);
                file.setForeground(Color.WHITE);
                for(Component component : file.getComponents())
                {
                    component.setBackground(Color.DARK_GRAY);
                    component.setForeground(Color.WHITE);
                }
            }
            else
            {
                textArea.setBackground(Color.WHITE);  //textArea
                if(textArea.getForeground() == Color.WHITE)
                {
                    textArea.setForeground(Color.BLACK);
                }
                textArea.setCaretColor(Color.BLACK);

                fontArea.setBackground(Color.WHITE);
                fontArea.setForeground(Color.BLACK); //fontArea
                for (Component component : fontArea.getComponents()) 
                {
                    component.setForeground(Color.BLACK);
                    component.setBackground(Color.WHITE);
                }
                mode.setBackground(Color.WHITE);
                mode.setForeground(Color.BLACK);
                toggle.setBackground(Color.WHITE); //toggle
                toggle.setForeground(Color.BLACK);
                clipboard.setBackground(Color.WHITE);
                clipboard.setForeground(Color.BLACK);
                for (Component component : clipboard.getComponents())
                {
                    component.setForeground(Color.BLACK);
                    component.setBackground(Color.WHITE);
                }
                file.setBackground(Color.WHITE);
                file.setForeground(Color.BLACK);
                for(Component component : file.getComponents())
                {
                    component.setBackground(Color.WHITE);
                    component.setForeground(Color.BLACK);
                }
            }
        }
        if(e.getSource() == mail)
        {
            Share share = new Share(textArea);
            share.mail();
        }
        if(e.getSource() == whatsapp)
        {
            Share share = new Share(textArea);
            share.whatsapp();
        }
    }
    public void stateChanged(ChangeEvent e) 
    {
        if(e.getSource() == spinner){
            textArea.setFont(new Font(textArea.getFont().getFamily(), Font.BOLD, (int) spinner.getValue()));
        }
    }
}
