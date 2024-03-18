import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class MenuExample extends JFrame {
    private static final long serialVersionUID = 1L;
	private JTextArea textArea;

    public MenuExample() {
        setTitle("Menu Example");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        createMenuBar();

        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
    }

    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem showDateTimeItem = new JMenuItem("Show Date/Time");
        JMenuItem saveLogItem = new JMenuItem("Save Log");
        JMenuItem changeColorItem = new JMenuItem("Change Color");
        JMenuItem exitItem = new JMenuItem("Exit");

        showDateTimeItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showDateTime();
            }
        });

        saveLogItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveLog();
            }
        });

        changeColorItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                changeColor();
            }
        });

        exitItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        fileMenu.add(showDateTimeItem);
        fileMenu.add(saveLogItem);
        fileMenu.add(changeColorItem);
        fileMenu.add(exitItem);
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);
    }

    private void showDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateTime = dateFormat.format(new Date());
        textArea.append(dateTime + "\n");
    }

    private void saveLog() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("log.txt", true));
            writer.write(textArea.getText());
            writer.close();
            JOptionPane.showMessageDialog(this, "Log saved successfully!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error while saving log: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void changeColor() {
        Random rand = new Random();
        float hue = 0.25f + rand.nextFloat() * 0.1667f; // Adjust range to potential shades of green
        Color color = Color.getHSBColor(hue, 0.8f, 0.8f);

        // Set background color for the frame
        getContentPane().setBackground(color);

        // Set background color for the menu
        UIManager.put("Menu.background", color);
        UIManager.put("MenuBar.background", color);
        SwingUtilities.updateComponentTreeUI(this);
    }

    public static void main(String[] args) {
        try {
            // Set cross-platform look and feel
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                MenuExample menuExample = new MenuExample();
                menuExample.setVisible(true);
            }
        });
    }}