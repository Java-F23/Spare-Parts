package CarSparePartsSystem;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.io.*;

public class Inventory {
    private List<SparePart> spareParts;
    private static final String FILE_PATH = "inventory.txt";

    public Inventory() {
        this.spareParts = new ArrayList<>();
        loadInventory();
    }

    public List<String> getAllCategories() {
        return spareParts.stream()
                         .map(SparePart::getCategory)
                         .distinct()
                         .collect(Collectors.toList());
    }
    
    public void addSparePart(SparePart part) {
        spareParts.add(part);
        saveInventory();
    }

    public boolean removeSparePart(String partId) {
        boolean removed = spareParts.removeIf(part -> part.getId().equals(partId));
        if (removed) {
            saveInventory();
        }
        return removed;
    }

    public void updateSparePart(String partId, SparePart updatedPart) {
        int index = spareParts.indexOf(getSparePartById(partId));
        if (index != -1) {
            spareParts.set(index, updatedPart);
        }
    }

    public SparePart getSparePartById(String partId) {
        return spareParts.stream()
            .filter(part -> part.getId().equals(partId))
            .findFirst()
            .orElse(null);
    }

    public List<SparePart> searchSpareParts(String type) {
        return spareParts.stream()
            .filter(part -> part.getType().equalsIgnoreCase(type))
            .collect(Collectors.toList());
    }
    
    public List<SparePart> getSparePartsByCategory(String category) {
        return spareParts.stream()
        	.filter(part -> part.getCategory().equals(category))
            .collect(Collectors.toList());
    }

    public List<SparePart> getSpareParts() {
        return new ArrayList<>(spareParts);
    }
    
    void saveInventory() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (SparePart part : spareParts) {
                writer.write(part.toFileString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadInventory() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                SparePart part = SparePart.fromFileString(line);
                if (part != null) {
                    spareParts.add(part);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
