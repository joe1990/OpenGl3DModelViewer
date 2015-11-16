package userInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by michael on 16.11.2015.
 */

public class Window {

    private static JFrame frame;
    private JTextField textField;
    private Canvas canvas;

    public static boolean closeRequested = false;


    public Window() {
        initialize();
        frame.setVisible(true);
    }


    private void initialize() {
        frame = new JFrame();
        frame.setTitle("");
        frame.setBounds(100, 100, 1200, 800);
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        canvas = new Canvas();
        frame.getContentPane().add(canvas);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e){
                closeRequested = true;
            }
        });

        JPanel panel = new JPanel();
        frame.getContentPane().add(panel, BorderLayout.NORTH);

        textField = new JTextField();
        panel.add(textField);
        textField.setColumns(50);

        JButton btnNewButton = new JButton("Load file");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            }
        });
        panel.add(btnNewButton);
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

}

