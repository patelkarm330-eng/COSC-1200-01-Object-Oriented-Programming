/*
 Name: Karm Patel
 COSC 1200 - Class exercise 5
 Program Name: DataForm
 Description: These program creates user interface and store in our local computer.
 */
import javax.swing.*;
        import java.awt.*;
        import java.awt.event.*;
        import java.io.*;
        import java.util.ArrayList;

public class DataForm extends JFrame {

    // List to store all text fields for easy saving/loading
    private ArrayList<JTextField> fields = new ArrayList<>();

    public DataForm() {
        super("Class Exercise 5 - User Interface and Files");

        // Main panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 2, 10, 10));

        // Example fields
        String[] labels = {
                "Player 1 Name:", "Player 2 Name:",
                "Player 1 score:", "Player 2 score :"
        };

        for (String label : labels) {
            panel.add(new JLabel(label));
            JTextField tf = new JTextField();
            fields.add(tf);
            panel.add(tf);
        }

        // Buttons
        JButton saveButton = new JButton("Save to File");
        JButton openButton = new JButton("Open File");

        // Button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(saveButton);
        buttonPanel.add(openButton);

        // Add panels to frame
        add(panel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Save button action
        saveButton.addActionListener(e -> saveToFile());

        // Open button action
        openButton.addActionListener(e -> openFromFile());

        // Frame settings
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    // Save data to file
    private void saveToFile() {
        JFileChooser chooser = new JFileChooser();
        int result = chooser.showSaveDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();

            try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
                for (JTextField tf : fields) {
                    writer.println(tf.getText());
                }
                JOptionPane.showMessageDialog(this, "File saved successfully!");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error saving file.");
            }
        }
    }

    // Load data from file
    private void openFromFile() {
        JFileChooser chooser = new JFileChooser();
        int result = chooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();

            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                int index = 0;

                while ((line = reader.readLine()) != null && index < fields.size()) {
                    fields.get(index).setText(line);
                    index++;
                }

                JOptionPane.showMessageDialog(this, "File loaded successfully!");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error loading file.");
            }
        }
    }

    public static void main(String[] args) {
        new DataForm();
    }
}
