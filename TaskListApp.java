
   /*
     * TaskListApp.java
     * Author: Karm Patel
     * Date: April 24, 2026
     * Description: A Swing application that loads tasks from a file, displays them, allows navigation, editing
     */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

    public class TaskListApp extends JFrame {

        private static final String FILE_NAME = "tasks.txt";

        private ArrayList<task> tasks = new ArrayList<>();
        private int currentIndex = 0;
        private int nextId = 1;

        // Top section
        private JTextField txtTaskId;
        private JButton btnBack;
        private JButton btnForward;

        // Bottom section
        private JTextField txtName;
        private JCheckBox chkCompleted;
        private JTextField txtDeadline;
        private JButton btnSaveTask;
        private JButton btnNewTask;
        private JButton btnSaveList;

        public TaskListApp() {
            super("Task List Manager");

            loadTasksFromFile();
            initComponents();
            updateTaskDisplay();
        }

       // Loads tasks from tasks.txt into the ArrayList.

        private void loadTasksFromFile() {
            File file = new File(FILE_NAME);

            if (!file.exists()) {
                tasks = new ArrayList<>();
                nextId = 1;
                return;
            }

            int maxId = 0;

            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;

                while ((line = reader.readLine()) != null) {
                    task t = task.fromFileString(line);
                    if (t != null) {
                        tasks.add(t);
                        if (t.getId() > maxId) maxId = t.getId();
                    }
                }

            } catch (IOException e) {
                JOptionPane.showMessageDialog(this,
                        "Error loading tasks: " + e.getMessage(),
                        "File Error",
                        JOptionPane.ERROR_MESSAGE);
            }

            nextId = maxId + 1;
        }

       // Saves all tasks back to tasks

        private void saveTasksToFile() {
            try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
                for (task t : tasks) {
                    writer.println(t.toFileString());
                }

                JOptionPane.showMessageDialog(this,
                        "Task list saved successfully.",
                        "Save Complete",
                        JOptionPane.INFORMATION_MESSAGE);

            } catch (IOException e) {
                JOptionPane.showMessageDialog(this,
                        "Error saving tasks: " + e.getMessage(),
                        "File Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }

       // Builds the Swing UI.
        private void initComponents() {
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLayout(new BorderLayout());

            // Top panel
            JPanel topPanel = new JPanel(new FlowLayout());

            JLabel lblTaskId = new JLabel("Task ID:");
            txtTaskId = new JTextField(5);
            txtTaskId.setEditable(false);

            btnBack = new JButton("Back");
            btnForward = new JButton("Forward");

            btnBack.addActionListener(e -> {
                if (currentIndex > 0) {
                    saveCurrentTaskFromFields();
                    currentIndex--;
                    updateTaskDisplay();
                }
            });

            btnForward.addActionListener(e -> {
                if (currentIndex < tasks.size() - 1) {
                    saveCurrentTaskFromFields();
                    currentIndex++;
                    updateTaskDisplay();
                }
            });

            topPanel.add(lblTaskId);
            topPanel.add(txtTaskId);
            topPanel.add(btnBack);
            topPanel.add(btnForward);

            // Bottom panel 
            JPanel bottomPanel = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();

            JLabel lblName = new JLabel("Name:");
            JLabel lblCompleted = new JLabel("Completed:");
            JLabel lblDeadline = new JLabel("Deadline:");

            txtName = new JTextField(20);
            chkCompleted = new JCheckBox();
            txtDeadline = new JTextField(12);

            btnSaveTask = new JButton("Save Task");
            btnNewTask = new JButton("New Task");
            btnSaveList = new JButton("Save List");

            // Row 0 - Name
            gbc.gridx = 0; gbc.gridy = 0; gbc.anchor = GridBagConstraints.LINE_END;
            bottomPanel.add(lblName, gbc);

            gbc.gridx = 1; gbc.anchor = GridBagConstraints.LINE_START;
            bottomPanel.add(txtName, gbc);

            // Row 1 - Completed
            gbc.gridx = 0; gbc.gridy = 1; gbc.anchor = GridBagConstraints.LINE_END;
            bottomPanel.add(lblCompleted, gbc);

            gbc.gridx = 1; gbc.anchor = GridBagConstraints.LINE_START;
            bottomPanel.add(chkCompleted, gbc);

            // Row 2 - Deadline
            gbc.gridx = 0; gbc.gridy = 2; gbc.anchor = GridBagConstraints.LINE_END;
            bottomPanel.add(lblDeadline, gbc);

            gbc.gridx = 1; gbc.anchor = GridBagConstraints.LINE_START;
            bottomPanel.add(txtDeadline, gbc);

            // Row 3 - Buttons
            JPanel buttonPanel = new JPanel(new FlowLayout());
            buttonPanel.add(btnSaveTask);
            buttonPanel.add(btnNewTask);
            buttonPanel.add(btnSaveList);

            gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
            bottomPanel.add(buttonPanel, gbc);

            // Button Actions
            btnSaveTask.addActionListener(e -> {
                saveCurrentTaskFromFields();
                JOptionPane.showMessageDialog(this,
                        "Task updated.",
                        "Saved",
                        JOptionPane.INFORMATION_MESSAGE);
            });

            btnNewTask.addActionListener(e -> createNewTask());

            btnSaveList.addActionListener(e -> {
                saveCurrentTaskFromFields();
                saveTasksToFile();
            });

            add(topPanel, BorderLayout.NORTH);
            add(bottomPanel, BorderLayout.CENTER);

            pack();
            setLocationRelativeTo(null);
        }

        // Updates the UI fields with the current task.

        private void updateTaskDisplay() {
            if (tasks.isEmpty()) {
                txtTaskId.setText("N/A");
                txtName.setText("");
                chkCompleted.setSelected(false);
                txtDeadline.setText("");

                btnBack.setEnabled(false);
                btnForward.setEnabled(false);
                return;
            }

            task t = tasks.get(currentIndex);

            txtTaskId.setText(String.valueOf(t.getId()));
            txtName.setText(t.getName());
            chkCompleted.setSelected(t.isCompleted());
            txtDeadline.setText(t.getDeadline());

            btnBack.setEnabled(currentIndex > 0);
            btnForward.setEnabled(currentIndex < tasks.size() - 1);
        }

        // Saves the UI field values back into the current Task object.

        private void saveCurrentTaskFromFields() {
            if (tasks.isEmpty()) return;

            task t = tasks.get(currentIndex);
            t.setName(txtName.getText().trim());
            t.setCompleted(chkCompleted.isSelected());
            t.setDeadline(txtDeadline.getText().trim());
        }

       // Creates a new task and navigates to it.

        private void createNewTask() {
            saveCurrentTaskFromFields();

            task newTask = new task(nextId, "New Task", false, "");
            nextId++;

            tasks.add(newTask);
            currentIndex = tasks.size() - 1;

            updateTaskDisplay();
        }

        public static void main(String[] args) {
            SwingUtilities.invokeLater(() -> new TaskListApp().setVisible(true));
        }
    }


