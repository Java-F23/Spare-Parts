package CarSparePartsSystem;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

public class User {
    private JFrame frame;
    private ShoppingCart shoppingCart;
    private Inventory inventory; // Assuming Inventory class is available
    private Set<SparePart> favoriteParts;

    public User(Inventory inventory) {
        this.shoppingCart = new ShoppingCart();
        this.inventory = inventory;
        this.favoriteParts = new HashSet<>();
    }

    public void userMode() {
    	// Initialize the GUI components
        frame = new JFrame("User Mode");
        frame.setLayout(new GridLayout(0, 1)); // Grid layout for buttons
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Add New Spare Part Button
        JButton browsePartButton = new JButton("Browse Spare Parts");
        browsePartButton.addActionListener(e -> browseSparePartsByCategory());
        frame.add(browsePartButton);

        // Edit Spare Part Button
        JButton searchPartButton = new JButton("Search Spare Parts");
        searchPartButton.addActionListener(e -> searchSpareParts());
        frame.add(searchPartButton);

        // Categorize Spare Parts Button
        JButton checkPartButton = new JButton("Check Availability and Stock");
        checkPartButton.addActionListener(e -> checkAvailabilityAndStock());
        frame.add(checkPartButton);

        // Track and Manage Orders Button
        JButton favButton = new JButton("Mark/View Favorites");
        favButton.addActionListener(e -> manageFavorites());
        frame.add(favButton);

        // Manage Shipment and Delivery Button
        JButton pricingButton = new JButton("Check Pricing Information");
        pricingButton.addActionListener(e -> checkPricingInformation());
        frame.add(pricingButton);
        
        JButton addToCartButton = new JButton("Add to Cart and Place Order");
        addToCartButton.addActionListener(e -> addToCartAndOrder());
        frame.add(addToCartButton);
        
        JButton warrantyButton = new JButton("View Warranty Information");
        warrantyButton.addActionListener(e -> viewWarrantyInformation());
        frame.add(warrantyButton);
        
        JButton maintenanceButton = new JButton("Check Maintenance History");
        maintenanceButton.addActionListener(e -> checkMaintenanceHistory());
        frame.add(maintenanceButton);
        
        JButton viewCartButton = new JButton("View Cart");
        viewCartButton.addActionListener(e -> viewCart());
        frame.add(viewCartButton);

        frame.setVisible(true);
    }
    
  

    private void browseSparePartsByCategory() {
        // Create a new frame or dialog
        JDialog dialog = new JDialog(frame, "Browse Spare Parts by Category", true);
        dialog.setLayout(new BorderLayout());

        // Category input
        JComboBox<String> categoryComboBox = new JComboBox<>();
        for (String category : inventory.getAllCategories()) {
            categoryComboBox.addItem(category);
        }
        dialog.add(categoryComboBox, BorderLayout.NORTH);

        // Results area
        DefaultListModel<SparePart> listModel = new DefaultListModel<>();
        JList<SparePart> partsList = new JList<>(listModel);
        dialog.add(new JScrollPane(partsList), BorderLayout.CENTER);

        // Confirm button to show parts of selected category
        JButton confirmButton = new JButton("Show Parts");
        confirmButton.addActionListener(e -> {
            String selectedCategory = (String) categoryComboBox.getSelectedItem();
            List<SparePart> parts = inventory.getSparePartsByCategory(selectedCategory);
            listModel.clear();
            parts.forEach(listModel::addElement);
        });
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(confirmButton);

        // Return button
        JButton returnButton = new JButton("Return");
        returnButton.addActionListener(e -> dialog.dispose());
        buttonPanel.add(returnButton);

        dialog.add(buttonPanel, BorderLayout.SOUTH);

        // Set dialog properties
        dialog.pack();
        dialog.setLocationRelativeTo(frame);
        dialog.setVisible(true);
    }







    private void searchSpareParts() {
        // Create a new frame or dialog
        JDialog dialog = new JDialog(frame, "Search Spare Parts", true);
        dialog.setLayout(new BorderLayout());

        // Panel for search input and button
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new FlowLayout());

        // Label for search field
        JLabel searchLabel = new JLabel("Enter by type:");
        searchPanel.add(searchLabel);

        // Search criteria input
        JTextField searchField = new JTextField(20);
        searchPanel.add(searchField);

        // Results area
        DefaultListModel<SparePart> listModel = new DefaultListModel<>();
        JList<SparePart> resultsList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(resultsList);
        dialog.add(scrollPane, BorderLayout.CENTER);

        // Search button
        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(e -> {
            String criteria = searchField.getText();
            List<SparePart> results = inventory.searchSpareParts(criteria);
            listModel.clear();

            if (results.isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "No results found for: " + criteria);
            } else {
                results.forEach(listModel::addElement);
            }
        });
        searchPanel.add(searchButton);

        // Add search panel to dialog
        dialog.add(searchPanel, BorderLayout.NORTH);

        // Return button
        JButton returnButton = new JButton("Return");
        returnButton.addActionListener(e -> dialog.dispose());
        dialog.add(returnButton, BorderLayout.SOUTH);

        // Set dialog properties
        dialog.pack();
        dialog.setLocationRelativeTo(frame);
        dialog.setVisible(true);
    }


    private void checkAvailabilityAndStock() {
        // Create a new frame or dialog
        JDialog dialog = new JDialog(frame, "Check Availability and Stock", true);
        dialog.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Part ID input label and field
        JLabel partIdLabel = new JLabel("Enter Part ID:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        dialog.add(partIdLabel, gbc);

        JTextField partIdField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 0;
        dialog.add(partIdField, gbc);

        // Stock level display
        JLabel stockLevelLabel = new JLabel("Stock level will be displayed here.");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        dialog.add(stockLevelLabel, gbc);

        // Check stock button
        JButton checkStockButton = new JButton("Check Stock");
        checkStockButton.addActionListener(e -> {
            String partId = partIdField.getText();
            SparePart part = inventory.getSparePartById(partId);
            if (part != null) {
                stockLevelLabel.setText("Stock level: " + part.getStockLevel());
            } else {
                stockLevelLabel.setText("Part not found.");
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        dialog.add(checkStockButton, gbc);

        // Return button
        JButton returnButton = new JButton("Return");
        returnButton.addActionListener(e -> dialog.dispose());
        gbc.gridx = 1;
        gbc.gridy = 2;
        dialog.add(returnButton, gbc);

        // Set dialog properties
        dialog.pack();
        dialog.setLocationRelativeTo(frame);
        dialog.setVisible(true);
    }



    private void manageFavorites() {
        // Create a new frame or dialog
        JDialog dialog = new JDialog(frame, "Manage Favorites", true);
        dialog.setLayout(new BorderLayout());

        // Buttons for Add and View actions
        JButton addButton = new JButton("Add to Favorites");
        JButton viewButton = new JButton("View Favorites");

        // Text field for part ID input
        JTextField partIdField = new JTextField(20);

        // Display area for favorites
        JTextArea favoritesDisplay = new JTextArea(10, 30);
        favoritesDisplay.setEditable(false);

        // Add to Favorites functionality
        addButton.addActionListener(e -> {
            String partId = partIdField.getText();
            SparePart part = inventory.getSparePartById(partId);
            if (part != null) {
                favoriteParts.add(part);
                JOptionPane.showMessageDialog(dialog, "Part added to favorites.");
            } else {
                JOptionPane.showMessageDialog(dialog, "Part not found.");
            }
        });

        // View Favorites functionality
        viewButton.addActionListener(e -> {
            StringBuilder favoritesList = new StringBuilder();
            for (SparePart part : favoriteParts) {
                favoritesList.append(part.toString()).append("\n");
            }
            favoritesDisplay.setText(favoritesList.toString());
        });

        // Layout
        JPanel inputPanel = new JPanel();
        inputPanel.add(partIdField);
        inputPanel.add(addButton);
        inputPanel.add(viewButton);

        dialog.add(inputPanel, BorderLayout.NORTH);
        dialog.add(new JScrollPane(favoritesDisplay), BorderLayout.CENTER);

        // Return button
        JButton returnButton = new JButton("Return");
        returnButton.addActionListener(e -> dialog.dispose());
        dialog.add(returnButton, BorderLayout.SOUTH);

        // Set dialog properties
        dialog.pack();
        dialog.setLocationRelativeTo(frame);
        dialog.setVisible(true);
    }


    private void checkPricingInformation() {
        // Create a new frame or dialog
        JDialog dialog = new JDialog(frame, "Check Pricing Information", true);
        dialog.setLayout(new BorderLayout());

        // Text field for part ID input
        JTextField partIdField = new JTextField(20);

        // Button for checking price
        JButton checkPriceButton = new JButton("Check Price");

        // Label to display price
        JLabel priceLabel = new JLabel("Price: ");

        // Check Price functionality
        checkPriceButton.addActionListener(e -> {
            String partId = partIdField.getText();
            SparePart part = inventory.getSparePartById(partId);
            if (part != null) {
                priceLabel.setText("Price: " + part.getPrice());
            } else {
                JOptionPane.showMessageDialog(dialog, "Part not found.");
            }
        });

        // Layout
        JPanel inputPanel = new JPanel();
        inputPanel.add(partIdField);
        inputPanel.add(checkPriceButton);

        dialog.add(inputPanel, BorderLayout.NORTH);
        dialog.add(priceLabel, BorderLayout.CENTER);

        // Return button
        JButton returnButton = new JButton("Return");
        returnButton.addActionListener(e -> dialog.dispose());
        dialog.add(returnButton, BorderLayout.SOUTH);

        // Set dialog properties
        dialog.pack();
        dialog.setLocationRelativeTo(frame);
        dialog.setVisible(true);
    }


    private void addToCartAndOrder() {
        // Create a new frame or dialog
        JDialog dialog = new JDialog(frame, "Add to Cart and Order", true);
        dialog.setLayout(new BorderLayout());

        // Text fields for part ID and quantity input
        JTextField partIdField = new JTextField(20);
        JTextField quantityField = new JTextField(5);

        // Button for adding to cart
        JButton addToCartButton = new JButton("Add to Cart");

        // Add to Cart functionality
        addToCartButton.addActionListener(e -> {
            String partId = partIdField.getText();
            int quantity;
            try {
                quantity = Integer.parseInt(quantityField.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog, "Invalid quantity.");
                return;
            }

            SparePart part = inventory.getSparePartById(partId);
            if (part != null) {
                shoppingCart.addItem(part, quantity);
                JOptionPane.showMessageDialog(dialog, "Added to cart.");
                viewCart(); // Assuming you have a GUI method to view the cart
            } else {
                JOptionPane.showMessageDialog(dialog, "Part not found.");
            }
        });

        // Layout
        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Part ID:"));
        inputPanel.add(partIdField);
        inputPanel.add(new JLabel("Quantity:"));
        inputPanel.add(quantityField);
        inputPanel.add(addToCartButton);

        dialog.add(inputPanel, BorderLayout.CENTER);

        // Return button
        JButton returnButton = new JButton("Return");
        returnButton.addActionListener(e -> dialog.dispose());
        dialog.add(returnButton, BorderLayout.SOUTH);

        // Set dialog properties
        dialog.pack();
        dialog.setLocationRelativeTo(frame);
        dialog.setVisible(true);
    }


    private void viewWarrantyInformation() {
        // Create a new frame or dialog
        JDialog dialog = new JDialog(frame, "View Warranty Information", true);
        dialog.setLayout(new BorderLayout());

        // Text field for part ID input
        JTextField partIdField = new JTextField(20);

        // Button for viewing warranty information
        JButton viewWarrantyButton = new JButton("View Warranty");

        // Label for displaying warranty information
        JLabel warrantyInfoLabel = new JLabel("Warranty information will appear here.");

        // View Warranty functionality
        viewWarrantyButton.addActionListener(e -> {
            String partId = partIdField.getText();
            SparePart part = inventory.getSparePartById(partId);
            if (part != null) {
                warrantyInfoLabel.setText("Warranty: " + part.getWarrantyInfo());
            } else {
                warrantyInfoLabel.setText("Part not found.");
            }
        });

        // Layout
        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Part ID:"));
        inputPanel.add(partIdField);
        inputPanel.add(viewWarrantyButton);

        dialog.add(inputPanel, BorderLayout.NORTH);
        dialog.add(warrantyInfoLabel, BorderLayout.CENTER);

        // Return button
        JButton returnButton = new JButton("Return");
        returnButton.addActionListener(e -> dialog.dispose());
        dialog.add(returnButton, BorderLayout.SOUTH);

        // Set dialog properties
        dialog.pack();
        dialog.setLocationRelativeTo(frame);
        dialog.setVisible(true);
    }


    private void checkMaintenanceHistory() {
        // Create a new frame or dialog
        JDialog dialog = new JDialog(frame, "Check Maintenance History", true);
        dialog.setLayout(new BorderLayout());

        // Text field for part ID input
        JTextField partIdField = new JTextField(20);

        // Button for checking maintenance history
        JButton checkHistoryButton = new JButton("Check History");

        // Label for displaying maintenance history
        JLabel historyLabel = new JLabel("Maintenance history will appear here.");

        // Check History functionality
        checkHistoryButton.addActionListener(e -> {
            String partId = partIdField.getText();
            SparePart part = inventory.getSparePartById(partId);
            if (part != null) {
                historyLabel.setText("Maintenance History: " + part.getMaintenanceHistory());
            } else {
                historyLabel.setText("Part not found.");
            }
        });

        // Layout
        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Part ID:"));
        inputPanel.add(partIdField);
        inputPanel.add(checkHistoryButton);

        dialog.add(inputPanel, BorderLayout.NORTH);
        dialog.add(historyLabel, BorderLayout.CENTER);

        // Return button
        JButton returnButton = new JButton("Return");
        returnButton.addActionListener(e -> dialog.dispose());
        dialog.add(returnButton, BorderLayout.SOUTH);

        // Set dialog properties
        dialog.pack();
        dialog.setLocationRelativeTo(frame);
        dialog.setVisible(true);
    }


    private void viewCart() {
        // Create a new frame or dialog
        JDialog dialog = new JDialog(frame, "View Cart", true);
        dialog.setLayout(new BorderLayout());

        // Text area for displaying cart items
        JTextArea cartItemsArea = new JTextArea(10, 30);
        cartItemsArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(cartItemsArea);

        // Populate the text area with cart items
        shoppingCart.getItems().forEach(item -> 
            cartItemsArea.append(item + ", Quantity: " + shoppingCart.getQuantity(item) + "\n"));

        // Checkout button
        JButton checkoutButton = new JButton("Checkout");
        checkoutButton.addActionListener(e -> {
            checkout(); // Assuming checkout() is a method that handles the checkout process
            dialog.dispose(); // Close the dialog after checkout
        });

        // Return button
        JButton returnButton = new JButton("Return");
        returnButton.addActionListener(e -> dialog.dispose());

        // Layout
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(checkoutButton);
        buttonPanel.add(returnButton);

        dialog.add(scrollPane, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);

        // Set dialog properties
        dialog.pack();
        dialog.setLocationRelativeTo(frame);
        dialog.setVisible(true);
    }


    private void checkout() {
        // Create a new frame or dialog
        JDialog dialog = new JDialog(frame, "Checkout", true);
        dialog.setLayout(new BorderLayout());

        // Label for displaying checkout message
        JLabel messageLabel = new JLabel("Finalizing order...");
        dialog.add(messageLabel, BorderLayout.NORTH);

        // Text area for displaying order details
        JTextArea orderDetailsArea = new JTextArea(10, 30);
        orderDetailsArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(orderDetailsArea);
        dialog.add(scrollPane, BorderLayout.CENTER);

        // Finalize Order button
        JButton finalizeOrderButton = new JButton("Finalize Order");
        finalizeOrderButton.addActionListener(e -> {
            Order order = shoppingCart.checkout(); // Assuming checkout() returns an Order object
            orderDetailsArea.setText("Order placed: " + order);
            // Further implementation for order processing
        });

        // Return button
        JButton returnButton = new JButton("Close");
        returnButton.addActionListener(e -> dialog.dispose());

        // Layout
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(finalizeOrderButton);
        buttonPanel.add(returnButton);

        dialog.add(buttonPanel, BorderLayout.SOUTH);

        // Set dialog properties
        dialog.pack();
        dialog.setLocationRelativeTo(frame);
        dialog.setVisible(true);
    }


    // Additional methods for user functionalities can be added here
}

    