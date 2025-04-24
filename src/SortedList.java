import java.util.ArrayList;
import java.util.List;

public class SortedList {
    private List<String> list;

    public SortedList() {
        this.list = new ArrayList<>();
    }

    public void add(String element) {
        int insertionPoint = binarySearchForInsertion(element);
        this.list.add(insertionPoint, element);
    }

    public int binarySearchForInsertion(String target) {
        int low = 0;
        int high = this.list.size() - 1;
        int insertionPoint = this.list.size(); // Default to adding at the end

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int comparisonResult = this.list.get(mid).compareTo(target);

            if (comparisonResult == 0) {
                return mid; // Element found, insert here to maintain order (could also insert before or after)
            } else if (comparisonResult < 0) {
                low = mid + 1; // Target is in the right half
            } else {
                high = mid - 1; // Target is in the left half
                insertionPoint = mid; // Potential insertion point if not found
            }
        }
        return insertionPoint;
    }

    public String search(String target) {
        int low = 0;
        int high = this.list.size() - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int comparisonResult = this.list.get(mid).compareTo(target);

            if (comparisonResult == 0) {
                return "Found: " + this.list.get(mid) + " at index " + mid;
            } else if (comparisonResult < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        // Element not found, return the index where it would be inserted
        return "Not found. Would be inserted at index " + low;
    }

    public List<String> getList() {
        return this.list;
    }
}
