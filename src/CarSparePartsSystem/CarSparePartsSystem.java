package CarSparePartsSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CarSparePartsSystem {

    private static Inventory inventory = new Inventory();
    private static OrderManager orderManager = new OrderManager(inventory);
    private static User user = new User(inventory);
    private static Admin admin = new Admin(inventory, orderManager);

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CarSparePartsSystem::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        // Create the main frame
        JFrame frame = new JFrame("Car Spare Parts System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());

        // Temporary welcome message
        final JDialog welcomeDialog = new JDialog(frame, "Welcome", true);
        JLabel welcomeLabel = new JLabel("Welcome to the Car Spare Parts System", SwingConstants.CENTER);
        welcomeDialog.add(welcomeLabel);
        welcomeDialog.setSize(300, 200);
        welcomeDialog.setLocationRelativeTo(frame);
        new Timer(2000, e -> welcomeDialog.dispose()).start();
        welcomeDialog.setVisible(true);

        JPanel centerPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10); // Insets for spacing around components

        JButton userModeButton = new JButton("User Mode");
        userModeButton.setPreferredSize(new Dimension(160, 40)); // Width to height ratio of 8:2
        userModeButton.addActionListener(e -> user.userMode());
        centerPanel.add(userModeButton, gbc);

        // Adjusting vertical gap between buttons
        gbc.insets = new Insets(20, 10, 10, 10); // Increase top inset to add space above the second button

        JButton adminModeButton = new JButton("Admin Mode");
        adminModeButton.setPreferredSize(new Dimension(160, 40)); // Width to height ratio of 8:2
        adminModeButton.addActionListener(e -> admin.adminMode());
        centerPanel.add(adminModeButton, gbc);

        frame.add(centerPanel, BorderLayout.CENTER);

        // Display the window
        frame.setVisible(true);
    }
}