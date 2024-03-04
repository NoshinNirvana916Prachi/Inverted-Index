package client;

import java.util.Map;
import java.util.Map.Entry;
import java.util.List;
import java.rmi.Naming;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

import service.InvertedIndexService;

public class InvertedIndexClient {
    public static void main(String[] args) {
        try {
            // Lookup the RMI service
            InvertedIndexService service = (InvertedIndexService) Naming.lookup("//168.138.93.236/opc");
            
            // Retrieve the inverted index from the service
            Map<String, List<Integer>> invertedIndex = service.getInvertedIndex("sample.txt");
            
            // Display top 5 tokens with most frequent appearance and locations
            displayTopTokens(invertedIndex);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
 // Method to display top 5 tokens with most frequent appearance and locations
    private static void displayTopTokens(Map<String, List<Integer>> invertedIndex) {
        // Sort tokens by frequency
        List<Map.Entry<String, List<Integer>>> sortedEntries = invertedIndex.entrySet().stream()
                .sorted(Comparator.comparingInt(entry -> ((Entry<String, List<Integer>>) entry).getValue().size()).reversed())
                .collect(Collectors.toList());
        
        // Display top 5 tokens
        int count = 0;
        for (Map.Entry<String, List<Integer>> entry : sortedEntries) {
            if (count >= 5) break; // Display only top 5 tokens
            System.out.println("Token: " + entry.getKey() + ", Frequency: " + entry.getValue().size() + ", Locations: " + entry.getValue());
            count++;
        }
    }
}
