package CarSparePartsSystem;

import java.util.Map;
import javax.swing.*; 
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;
import java.util.List;
import java.util.Vector;
import java.util.Optional;

public class Admin {
    private JFrame frame;
    private Inventory inventory;
    private OrderManager orderManager;
    private static final String ADMIN_PASSWORD = "admin123"; 

    public Admin(Inventory inventory, OrderManager orderManager) {
        this.inventory = inventory;
        this.orderManager = orderManager;
    }

    public void adminMode() {
        if (showLoginDialog()) {
            // Initialize the GUI components only if login is successful
            frame = new JFrame("Admin Mode");
            frame.setLayout(new GridLayout(0, 1)); // Grid layout for buttons
            frame.setSize(600, 400);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	        // Add New Spare Part Button
	        JButton addPartButton = new JButton("Add New Spare Part");
	        addPartButton.addActionListener(e -> showAddPartDialog());
	        frame.add(addPartButton);
	
	        // Edit Spare Part Button
	        JButton editPartButton = new JButton("Edit Spare Part");
	        editPartButton.addActionListener(e -> showEditPartDialog());
	        frame.add(editPartButton);
	
	        // Manage Inventory Button
	        JButton manageInventoryButton = new JButton("Manage Inventory");
	        manageInventoryButton.addActionListener(e -> manageInventory());
	        frame.add(manageInventoryButton);
	
	        // Categorize Spare Parts Button
	        JButton categorizePartsButton = new JButton("Categorize Spare Parts");
	        categorizePartsButton.addActionListener(e -> showCategorizePartDialog());
	        frame.add(categorizePartsButton);
	
	        // Track and Manage Orders Button
	        JButton manageOrdersButton = new JButton("Track and Manage Orders");
	        manageOrdersButton.addActionListener(e -> trackAndManageOrders());
	        frame.add(manageOrdersButton);
	
	        // Manage Shipment and Delivery Button
	        JButton manageShipmentButton = new JButton("Manage Shipment and Delivery");
	        manageShipmentButton.addActionListener(e -> manageShipmentAndDelivery());
	        frame.add(manageShipmentButton);
	        
	     // Track and Release Orders Button
	        JButton releaseOrdersButton = new JButton("Release Order");
	        releaseOrdersButton.addActionListener(e -> showReleaseOrderDialog());
	        frame.add(releaseOrdersButton);

	
	        frame.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Incorrect password. Access denied.", "Login Failed", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private boolean showLoginDialog() {
        JPasswordField passwordField = new JPasswordField(10);
        int action = JOptionPane.showConfirmDialog(null, passwordField, "Enter Password", JOptionPane.OK_CANCEL_OPTION);
        if (action == JOptionPane.OK_OPTION) {
            return new String(passwordField.getPassword()).equals(ADMIN_PASSWORD);
        }
        return false;
    }
    
    private void showAddPartDialog() {
        JDialog dialog = new JDialog(frame, "Add New Spare Part", true);
        dialog.setLayout(new GridLayout(0, 2));
        dialog.setSize(400, 300);

        // Add input fields and labels
        dialog.add(new JLabel("ID:"));
        JTextField idField = new JTextField(10);
        dialog.add(idField);

        dialog.add(new JLabel("Name:"));
        JTextField nameField = new JTextField(10);
        dialog.add(nameField);

        dialog.add(new JLabel("Type:"));
        JTextField typeField = new JTextField(10);
        dialog.add(typeField);

        dialog.add(new JLabel("Category:"));
        JTextField categoryField = new JTextField(10);
        dialog.add(categoryField);

        dialog.add(new JLabel("Specifications:"));
        JTextField specificationsField = new JTextField(10);
        dialog.add(specificationsField);

        dialog.add(new JLabel("Stock Level:"));
        JTextField stockLevelField = new JTextField(10);
        dialog.add(stockLevelField);

        dialog.add(new JLabel("Price:"));
        JTextField priceField = new JTextField(10);
        dialog.add(priceField);

        dialog.add(new JLabel("Warranty Information:"));
        JTextField warrantyInfoField = new JTextField(10);
        dialog.add(warrantyInfoField);

        dialog.add(new JLabel("Maintenance History:"));
        JTextField maintenanceHistoryField = new JTextField(10);
        dialog.add(maintenanceHistoryField);

        // Add Submit and Cancel buttons
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Retrieve data from fields and add new spare part
                String id = idField.getText();
                String name = nameField.getText();
                String type = typeField.getText();
                String category = categoryField.getText();
                String specifications = specificationsField.getText();
                int stockLevel = Integer.parseInt(stockLevelField.getText());
                double price = Double.parseDouble(priceField.getText());
                String warrantyInfo = warrantyInfoField.getText();
                String maintenanceHistory = maintenanceHistoryField.getText();

                addNewSparePart(id, name, type, category, specifications, stockLevel, price, warrantyInfo, maintenanceHistory);
                dialog.dispose();
            }
        });
        dialog.add(submitButton);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> dialog.dispose());
        dialog.add(cancelButton);

        dialog.setVisible(true);
    }
    
    private void showEditPartDialog() {
        JDialog dialog = new JDialog(frame, "Edit Spare Part", true);
        dialog.setLayout(new GridLayout(0, 2));
        dialog.setSize(400, 300);

        // Add input fields and labels
        dialog.add(new JLabel("ID:"));
        JTextField idField = new JTextField(10);
        dialog.add(idField);

        dialog.add(new JLabel("New Name:"));
        JTextField nameField = new JTextField(10);
        dialog.add(nameField);

        dialog.add(new JLabel("New Type:"));
        JTextField typeField = new JTextField(10);
        dialog.add(typeField);

        dialog.add(new JLabel("New Specifications:"));
        JTextField specificationsField = new JTextField(10);
        dialog.add(specificationsField);
        
        dialog.add(new JLabel("New StockLevel:"));
        JTextField stockLevelField = new JTextField(10);
        dialog.add(stockLevelField);

        // Add Submit and Cancel buttons
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Retrieve data from fields and edit the spare part
                String partId = idField.getText();
                String newName = nameField.getText();
                String newType = typeField.getText();
                String newSpecifications = specificationsField.getText();
                int newStockLevel = Integer.parseInt(stockLevelField.getText());

                editSparePart(partId, newName, newType, newSpecifications, newStockLevel);
                dialog.dispose();
            }
        });
        dialog.add(submitButton);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> dialog.dispose());
        dialog.add(cancelButton);

        dialog.setVisible(true);
    }
    
    private void showCategorizePartDialog() {
        JDialog dialog = new JDialog(frame, "Categorize Spare Part", true);
        dialog.setLayout(new GridLayout(0, 2));
        dialog.setSize(400, 200);

        // Add input fields and labels
        dialog.add(new JLabel("Part ID:"));
        JTextField idField = new JTextField(10);
        dialog.add(idField);

        dialog.add(new JLabel("New Category:"));
        JTextField categoryField = new JTextField(10);
        dialog.add(categoryField);

        // Add Submit and Cancel buttons
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Retrieve data from fields and categorize the spare part
                String partId = idField.getText();
                String newCategory = categoryField.getText();

                categorizeSpareParts(partId, newCategory);
                dialog.dispose();
            }
        });
        dialog.add(submitButton);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> dialog.dispose());
        dialog.add(cancelButton);

        dialog.setVisible(true);
    }
    
    private void showRemovePartDialog() {
        JDialog dialog = new JDialog(frame, "Remove Spare Part", true);
        dialog.setLayout(new GridLayout(0, 2));
        dialog.setSize(400, 200);

        // Input field for part ID
        dialog.add(new JLabel("Part ID:"));
        JTextField idField = new JTextField(10);
        dialog.add(idField);

        // Submit and Cancel buttons
        JButton submitButton = new JButton("Remove");
        submitButton.addActionListener(e -> {
            String id = idField.getText();
            removePartFromInventory(id);
            dialog.dispose();
        });
        dialog.add(submitButton);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> dialog.dispose());
        dialog.add(cancelButton);

        dialog.setVisible(true);
    }

    // 1. Add New Spare Parts
    public void addNewSparePart(String id, String name, String type, String category, String specifications, int stockLevel, double price, String warrantyInfo, String maintenanceHistory) {
        SparePart newPart = new SparePart(id, name, type, category, specifications, stockLevel, price, warrantyInfo, maintenanceHistory);
        inventory.addSparePart(newPart);
        final JDialog addedDialog = new JDialog(frame, "Update", true);
        JLabel addedLabel = new JLabel("New Spare Part Added!", SwingConstants.CENTER);
        addedDialog.add(addedLabel);
        addedDialog.setSize(300, 200);
        addedDialog.setLocationRelativeTo(frame);
        new Timer(1000, e -> addedDialog.dispose()).start();
        addedDialog.setVisible(true);
    }

    // 2. Edit and Update Spare Parts
    public void editSparePart(String partId, String newName, String newType, String newSpecifications, int newStockLevel) {
        SparePart part = inventory.getSparePartById(partId);
        if (part != null) {
            part.setName(newName);
            part.setType(newType);
            part.setSpecifications(newSpecifications);
            part.setStockLevel(newStockLevel);
            
            final JDialog updatedDialog = new JDialog(frame, "Update", true);
            JLabel updatedLabel = new JLabel("Spare Part Updated " + partId + "!" , SwingConstants.CENTER);
            updatedDialog.add(updatedLabel);
            updatedDialog.setSize(300, 200);
            updatedDialog.setLocationRelativeTo(frame);
            new Timer(1000, e -> updatedDialog.dispose()).start();
            updatedDialog.setVisible(true);
            inventory.saveInventory();
        } else {
        	final JDialog notfoundDialog = new JDialog(frame, "Update", true);
            JLabel notfoundLabel = new JLabel("Spare Part not found!", SwingConstants.CENTER);
            notfoundDialog.add(notfoundLabel);
            notfoundDialog.setSize(300, 200);
            notfoundDialog.setLocationRelativeTo(frame);
            new Timer(1000, e -> notfoundDialog.dispose()).start();
            notfoundDialog.setVisible(true);
        }
    }

    private void manageInventory() {
        JFrame inventoryFrame = new JFrame("Inventory Management");
        inventoryFrame.setLayout(new GridLayout(0, 1));
        inventoryFrame.setSize(400, 300);

        // Button to view inventory
        JButton viewInventoryButton = new JButton("View Inventory");
        viewInventoryButton.addActionListener(e -> displayInventory());
        inventoryFrame.add(viewInventoryButton);

        // Button to add a spare part
        JButton addPartButton = new JButton("Add Spare Part");
        addPartButton.addActionListener(e -> showAddPartDialog());
        inventoryFrame.add(addPartButton);

        // Button to remove a spare part
        JButton removePartButton = new JButton("Remove Spare Part");
        removePartButton.addActionListener(e -> showRemovePartDialog());
        inventoryFrame.add(removePartButton);

        // Button to return to admin mode
        JButton returnButton = new JButton("Return");
        returnButton.addActionListener(e -> inventoryFrame.dispose());
        inventoryFrame.add(returnButton);

        inventoryFrame.setVisible(true);
    }

    private void displayInventory() {
        JDialog inventoryDialog = new JDialog(frame, "Inventory", true);
        inventoryDialog.setLayout(new BorderLayout());
        inventoryDialog.setSize(500, 400);

        // Assuming you have a method to get a list or array of SparePart objects
        List<SparePart> parts = inventory.getSpareParts();

        // Display parts in a scrollable list or table
        JList<SparePart> partsList = new JList<>(new Vector<>(parts));
        JScrollPane scrollPane = new JScrollPane(partsList);
        inventoryDialog.add(scrollPane, BorderLayout.CENTER);

        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> inventoryDialog.dispose());
        inventoryDialog.add(closeButton, BorderLayout.SOUTH);

        inventoryDialog.setVisible(true);
    }


    private void removePartFromInventory(String id) {
        boolean removed = inventory.removeSparePart(id);
        if (removed) {
        	final JDialog removeDialog = new JDialog(frame, "Update", true);
            JLabel removeLabel = new JLabel("Spare part has been removed!", SwingConstants.CENTER);
            removeDialog.add(removeLabel);
            removeDialog.setSize(300, 200);
            removeDialog.setLocationRelativeTo(frame);
            new Timer(1000, e -> removeDialog.dispose()).start();
            removeDialog.setVisible(true);
            inventory.saveInventory();
        } else {
        	final JDialog notfoundDialog = new JDialog(frame, "Update", true);
            JLabel notfoundLabel = new JLabel("Spare Part not found!", SwingConstants.CENTER);
            notfoundDialog.add(notfoundLabel);
            notfoundDialog.setSize(300, 200);
            notfoundDialog.setLocationRelativeTo(frame);
            new Timer(1000, e -> notfoundDialog.dispose()).start();
            notfoundDialog.setVisible(true);
        }
    }


    // 4. Categorize Spare Parts
    public void categorizeSpareParts(String partId, String category) {
        SparePart part = inventory.getSparePartById(partId);
        if (part != null) {
            part.setCategory(category);
            final JDialog catDialog = new JDialog(frame, "Update", true);
            JLabel catLabel = new JLabel("Spare Part Categorized " + partId + "!" , SwingConstants.CENTER);
            catDialog.add(catLabel);
            catDialog.setSize(300, 200);
            catDialog.setLocationRelativeTo(frame);
            new Timer(1000, e -> catDialog.dispose()).start();
            catDialog.setVisible(true);
            inventory.saveInventory();
        } else {
        	final JDialog notfoundDialog = new JDialog(frame, "Update", true);
            JLabel notfoundLabel = new JLabel("Spare Part not found!", SwingConstants.CENTER);
            notfoundDialog.add(notfoundLabel);
            notfoundDialog.setSize(300, 200);
            notfoundDialog.setLocationRelativeTo(frame);
            new Timer(1000, e -> notfoundDialog.dispose()).start();
            notfoundDialog.setVisible(true);
        }
    }

    private void trackAndManageOrders() {
        JFrame orderFrame = new JFrame("Order Management");
        orderFrame.setLayout(new GridLayout(0, 1));
        orderFrame.setSize(400, 300);

        JButton viewOrdersButton = new JButton("View All Orders");
        viewOrdersButton.addActionListener(e -> displayAllOrders());
        orderFrame.add(viewOrdersButton);

        JButton updateOrderButton = new JButton("Update Order Status");
        updateOrderButton.addActionListener(e -> showUpdateOrderStatusDialog());
        orderFrame.add(updateOrderButton);

        JButton returnButton = new JButton("Return");
        returnButton.addActionListener(e -> orderFrame.dispose());
        orderFrame.add(returnButton);

        orderFrame.setVisible(true);
    }
    
    private void displayAllOrders() {
        JDialog ordersDialog = new JDialog(frame, "All Orders", true);
        ordersDialog.setLayout(new BorderLayout());
        ordersDialog.setSize(500, 400);

        List<Order> orders = orderManager.getAllOrders();
        JList<Order> ordersList = new JList<>(new DefaultListModel<>());
        DefaultListModel<Order> model = (DefaultListModel<Order>) ordersList.getModel();
        orders.forEach(model::addElement);

        JScrollPane scrollPane = new JScrollPane(ordersList);
        ordersDialog.add(scrollPane, BorderLayout.CENTER);

        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> ordersDialog.dispose());
        ordersDialog.add(closeButton, BorderLayout.SOUTH);

        ordersDialog.setVisible(true);
    }

    private void showUpdateOrderStatusDialog() {
        JDialog updateDialog = new JDialog(frame, "Update Order Status", true);
        updateDialog.setLayout(new GridLayout(0, 2));
        updateDialog.setSize(400, 200);

        updateDialog.add(new JLabel("Order ID:"));
        JTextField orderIdField = new JTextField(10);
        updateDialog.add(orderIdField);

        updateDialog.add(new JLabel("New Status:"));
        JTextField statusField = new JTextField(10);
        updateDialog.add(statusField);

        JButton updateButton = new JButton("Update");
        updateButton.addActionListener(e -> {
            String orderId = orderIdField.getText();
            String newStatus = statusField.getText();
            orderManager.updateOrderStatus(orderId, newStatus);
            updateDialog.dispose();
        });
        updateDialog.add(updateButton);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> updateDialog.dispose());
        updateDialog.add(cancelButton);

        updateDialog.setVisible(true);
    }

    private void manageShipmentAndDelivery() {
        JFrame shipmentFrame = new JFrame("Shipment and Delivery Management");
        shipmentFrame.setLayout(new GridLayout(0, 1));
        shipmentFrame.setSize(400, 300);

        JButton viewShipmentButton = new JButton("View Shipment Details");
        viewShipmentButton.addActionListener(e -> viewShipmentDetails());
        shipmentFrame.add(viewShipmentButton);

        JButton updateShipmentButton = new JButton("Update Shipment Details");
        updateShipmentButton.addActionListener(e -> showUpdateShipmentDetailsDialog());
        shipmentFrame.add(updateShipmentButton);

        JButton returnButton = new JButton("Return to Admin Mode");
        returnButton.addActionListener(e -> shipmentFrame.dispose());
        shipmentFrame.add(returnButton);

        shipmentFrame.setVisible(true);
    }

    private void viewShipmentDetails() {
        JDialog detailsDialog = new JDialog(frame, "View Shipment Details", true);
        detailsDialog.setLayout(new GridLayout(0, 2));
        detailsDialog.setSize(400, 200);

        detailsDialog.add(new JLabel("Order ID:"));
        JTextField orderIdField = new JTextField(10);
        detailsDialog.add(orderIdField);

        JButton viewButton = new JButton("View");
        viewButton.addActionListener(e -> {
            String orderId = orderIdField.getText();
            Optional<Order> order = orderManager.getOrderById(orderId);
            if (order.isPresent()) {
                // Create a string to display order details
                StringBuilder orderDetails = new StringBuilder();
                orderDetails.append("Order ID: ").append(order.get().getId()).append("\n");
                orderDetails.append("Order Status: ").append(order.get().getStatus()).append("\n");
                orderDetails.append("Spare Parts in the Order:\n").append(order.get().getOrderedParts());

                JOptionPane.showMessageDialog(detailsDialog, orderDetails.toString());
            } else {
                JOptionPane.showMessageDialog(detailsDialog, "Order not found.");
            }
            detailsDialog.dispose();
        });
        detailsDialog.add(viewButton);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> detailsDialog.dispose());
        detailsDialog.add(cancelButton);

        detailsDialog.setVisible(true);
    }


    private void showUpdateShipmentDetailsDialog() {
        JDialog updateDialog = new JDialog(frame, "Update Shipment Details", true);
        updateDialog.setLayout(new GridLayout(0, 2));
        updateDialog.setSize(400, 200);

        updateDialog.add(new JLabel("Order ID:"));
        JTextField orderIdField = new JTextField(10);
        updateDialog.add(orderIdField);

        updateDialog.add(new JLabel("New Shipment Details:"));
        JTextField shipmentDetailsField = new JTextField(10);
        updateDialog.add(shipmentDetailsField);

        JButton updateButton = new JButton("Update");
        updateButton.addActionListener(e -> {
            String orderId = orderIdField.getText();
            String newShipmentDetails = shipmentDetailsField.getText();
            Optional<Order> order = orderManager.getOrderById(orderId);
            if (order.isPresent()) {
                // Assuming Order class has a method to set shipment details
                order.get().setStatus(newShipmentDetails);
                JOptionPane.showMessageDialog(updateDialog, "Shipment details updated for Order ID: " + orderId);
            } else {
                JOptionPane.showMessageDialog(updateDialog, "Order not found.");
            }
            updateDialog.dispose();
        });
        updateDialog.add(updateButton);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> updateDialog.dispose());
        updateDialog.add(cancelButton);

        updateDialog.setVisible(true);
    }
    
    private void showReleaseOrderDialog() {
        JDialog releaseDialog = new JDialog(frame, "Release Order", true);
        releaseDialog.setLayout(new GridLayout(0, 2));
        releaseDialog.setSize(400, 200);

        releaseDialog.add(new JLabel("Order ID:"));
        JTextField orderIdField = new JTextField(10);
        releaseDialog.add(orderIdField);

        JButton releaseButton = new JButton("Release");
        releaseButton.addActionListener(e -> {
            String orderId = orderIdField.getText();
            releaseOrder(orderId);
            releaseDialog.dispose();
        });
        releaseDialog.add(releaseButton);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> releaseDialog.dispose());
        releaseDialog.add(cancelButton);

        releaseDialog.setVisible(true);
    }
    
    private void releaseOrder(String orderId) {
        Optional<Order> orderOpt = orderManager.getOrderById(orderId);
        if (orderOpt.isPresent()) {
            Order order = orderOpt.get();
            order.setStatus("Delivered");
            updateInventoryStock(order);
            JOptionPane.showMessageDialog(frame, "Order " + orderId + " released and marked as Delivered.");
        } else {
            JOptionPane.showMessageDialog(frame, "Order not found.");
        }
    }

    private void updateInventoryStock(Order order) {
        Map<SparePart, Integer> orderedParts = order.getOrderedParts();
        for (Map.Entry<SparePart, Integer> entry : orderedParts.entrySet()) {
            SparePart part = entry.getKey();
            int quantity = entry.getValue();
            SparePart inventoryPart = inventory.getSparePartById(part.getId());
            if (inventoryPart != null) {
                inventoryPart.decreaseStockLevel(quantity); // Assuming SparePart has a method to decrease stock level
            }
        }
        inventory.saveInventory(); // Save the updated inventory to the file
    }



    // Additional methods for admin functionalities can be added here
}
