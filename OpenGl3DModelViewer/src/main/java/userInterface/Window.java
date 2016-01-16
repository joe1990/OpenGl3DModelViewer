package userInterface;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

/**
 * Klasse erstellt das Fenster des User-Interfaces.
 */
public class Window {

    private static JFrame frame;
    private JTextField textField;
    private Canvas canvas;
    private static String wavefrontFile;

    public static boolean closeRequested = false;

    /**
     * Konstruktor. Initialisiert das Fenster und blendet das initialisierte Fenster ein.
     */
    public Window() {
        initialize();
        frame.setVisible(true);
    }

    /**
     * Initialisiert das Fenster
     */
    private void initialize() {

        frame = new JFrame();
        frame.setTitle("OpenGL Wavefront Viewer");
        frame.setBounds(100, 100, 1200, 800);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setResizable(false);

        //Fenster-Icon
        ImageIcon img = new ImageIcon("ressources\\logo.png");
        frame.setIconImage(img.getImage());

        //Canvas
        canvas = new Canvas();
        frame.getContentPane().add(canvas);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                closeRequested = true;
            }
        });

        //Panel
        JPanel panel = new JPanel();
        frame.getContentPane().add(panel, BorderLayout.NORTH);

        //Textfeld zur Angabe des Filepfads des Wavefront OBJ-File
        textField = new JTextField();
        textField.setColumns(69);
        Font font = new Font("Arial", Font.BOLD, 16);
        textField.setFont(font);
        panel.add(textField);

        //Buttons zur Auswahl des Wavefront OBJ-Files
        JButton btnSelect = new JButton("Select a file");
        JButton btnOpen = new JButton("Open");

        btnSelect.setPreferredSize(new Dimension(100,22));
        btnOpen.setPreferredSize(new Dimension(100,22));

        //ActionListener für den "Select a File"-Button-Klick -> FileOpenDialog einblenden, welche die Auswahl eines Wavefront OBJ-Files
        //ermöglicht.
        btnSelect.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {

                FileFilter filter = new FileNameExtensionFilter("Wavefront OBJ", "obj");
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Open Wavefront");

                // Filter wird dem JFileChooser hinzugefügt
                fileChooser.setFileFilter(filter);
                fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
                int result = fileChooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    wavefrontFile = selectedFile.getAbsolutePath();
                    textField.setText(wavefrontFile);
                }
            }
        });

        //ActionListener für den "Open"-Button-Klick. Öffnet das im Eingabefeld angegebene Wavefront OBJ-File.
        btnOpen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {

                File file = new File(textField.getText());
                if(file.exists() && !file.isDirectory()) {
                    wavefrontFile = textField.getText();
                }else{
                    System.out.println("File " + textField.getText() + " not found");
                }
            }
        });

        panel.add(btnSelect);
        panel.add(btnOpen);
    }

    /**
     * Setzt den Titel des Fensters. Dem Titel des Fenster wird der übergebene Titel angehängt.
     * @param title Titel, welcher zum statischen Titel "OpenGL Wavefront Viewer - " angehängt werden soll.
     */
    public static void setTitle(String title){
        frame.setTitle("OpenGL Wavefront Viewer - " + title);
    }

    /**
     * Gibt das Canvas des Fenster zurück.
     * @return canvas
     */
    public Canvas getCanvas(){
        return canvas;
    }

    /**
     * dispose
     */
    public void dispose(){
        frame.dispose();
    }

    /**
     * Gibt die Höhe des Canvas zurück.
     *
     * @return canvas Höhe
     */
    public int getCanvasHeight(){
        return canvas.getHeight();
    }

    /**
     * Gibt die Breite des Canvas zurück.
     *
     * @return canvas Breite.
     */
    public int getCanvasWidth(){
        return canvas.getWidth();
    }

    /**
     * Gibt das geöffnete Wavefront OBJ-File zurück.
     * @return Geöffnetes wavefront file
     */
    public static File getWavefrontFile(){

        if(wavefrontFile != null && !wavefrontFile.isEmpty()) {
            File file =  new File(wavefrontFile);
            wavefrontFile = new String();
            return file;
        }else{
            return null;
        }
    }
}

