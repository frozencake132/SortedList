import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SortedListGUI extends JFrame implements ActionListener {
    private SortedList sortedList;
    private JTextField addTextField;
    private JButton addButton;
    private JTextField searchTextField;
    private JButton searchButton;
    private JTextArea displayTextArea;

    public SortedListGUI() {
        this.sortedList = new SortedList();
        setTitle("Sorted List Operations");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new FlowLayout());
        addTextField = new JTextField(15);
        addButton = new JButton("Add");
        addButton.addActionListener(this);
        inputPanel.add(new JLabel("Add String:"));
        inputPanel.add(addTextField);
        inputPanel.add(addButton);

        JPanel searchPanel = new JPanel(new FlowLayout());
        searchTextField = new JTextField(15);
        searchButton = new JButton("Search");
        searchButton.addActionListener(this);
        searchPanel.add(new JLabel("Search String:"));
        searchPanel.add(searchTextField);
        searchPanel.add(searchButton);

        displayTextArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(displayTextArea);

        add(inputPanel, BorderLayout.NORTH);
        add(searchPanel, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);

        setVisible(true);
        updateDisplay();
    }

    private void updateDisplay() {
        displayTextArea.setText("Current List:\n");
        for (String s : sortedList.getList()) {
            displayTextArea.append(s + "\n");
        }
        displayTextArea.setCaretPosition(displayTextArea.getDocument().getLength()); // Auto-scroll to bottom
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            String textToAdd = addTextField.getText().trim();
            if (!textToAdd.isEmpty()) {
                sortedList.add(textToAdd);
                updateDisplay();
                addTextField.setText("");
            }
        } else if (e.getSource() == searchButton) {
            String textToSearch = searchTextField.getText().trim();
            if (!textToSearch.isEmpty()) {
                String searchResult = sortedList.search(textToSearch);
                displayTextArea.append("\nSearch for '" + textToSearch + "': " + searchResult + "\n");
                searchTextField.setText("");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SortedListGUI::new);
    }
}