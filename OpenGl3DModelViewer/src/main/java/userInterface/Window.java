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
 * Created by michael on 16.11.2015.
 */

public class Window {

    private static JFrame frame;
    private JTextField textField;
    private Canvas canvas;
    private static String wavefrontFile;

    public static boolean closeRequested = false;


    public Window() {
        initialize();
        frame.setVisible(true);
    }


    private void initialize() {
        frame = new JFrame();
        frame.setTitle("");
        frame.setBounds(100, 100, 1200, 800);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setResizable(false);

        //Frame icon
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

        JPanel panel = new JPanel();
        frame.getContentPane().add(panel, BorderLayout.NORTH);

        //Textfield
        textField = new JTextField();
        textField.setColumns(40);
        panel.add(textField);

        JButton btnSelect = new JButton("Select a file");
        JButton btnOpen = new JButton("Open");

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
                   // System.out.println("Selected file: " + selectedFile.getAbsolutePath())
                }
            }
        });

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

    public static void setTitle(String title){
        frame.setTitle(title);
    }

    public static String getTitle(){
        return frame.getTitle();
    }

    public static boolean isCloseRequested() {
        return closeRequested;
    }

    public Canvas getCanvas(){
        return canvas;
    }

    public void dispose(){
        frame.dispose();
    }

    public int getCanvasHeiht(){
        return canvas.getHeight();
    }

    public int getCanvasWidth(){
        return canvas.getWidth();
    }

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

