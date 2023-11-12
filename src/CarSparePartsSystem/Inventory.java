package CarSparePartsSystem;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Inventory {
    private List<SparePart> spareParts;

    public Inventory() {
        this.spareParts = new ArrayList<>();
    }

    public void addSparePart(SparePart part) {
        spareParts.add(part);
    }

    public boolean removeSparePart(String partId) {
        return spareParts.removeIf(part -> part.getId().equals(partId));
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
}
