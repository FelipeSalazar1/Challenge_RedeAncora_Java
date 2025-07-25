package br.com.redeAncora.System;

import br.com.redeAncora.System.utilities.SortUtils;
import br.com.redeAncora.System.utilities.AutocompleteUtils;
import br.com.redeAncora.System.validators.MontadoraResponse;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class ChallengeTests {

    @Test
    public void testQuickSortChallenge() {
        // Test Challenge 1: QuickSort for Montadora data
        SortUtils sortUtils = new SortUtils();
        
        // Create mock data for testing
        List<MontadoraResponse.Montadora> testMontadoras = Arrays.asList(
            new MontadoraResponse.Montadora(5, "Volkswagen"),
            new MontadoraResponse.Montadora(1, "Ford"),
            new MontadoraResponse.Montadora(3, "Chevrolet"),
            new MontadoraResponse.Montadora(2, "Fiat")
        );
        
        List<MontadoraResponse.Montadora> sortedList = sortUtils.sortById(testMontadoras);
        
        // Verify the list is sorted by ID
        assertEquals(1, sortedList.get(0).id());
        assertEquals(2, sortedList.get(1).id());
        assertEquals(3, sortedList.get(2).id());
        assertEquals(5, sortedList.get(3).id());
        
        System.out.println("✅ Challenge 1 (QuickSort) test passed!");
    }

    @Test
    public void testAutocompleteChallenge() {
        // Test Challenge 3: Autocomplete using BST
        AutocompleteUtils autocompleteUtils = new AutocompleteUtils();
        
        // Test autocomplete suggestions
        List<String> suggestions = autocompleteUtils.suggest("a");
        
        assertNotNull(suggestions);
        assertFalse(suggestions.isEmpty());
        assertTrue(suggestions.size() <= 4); // Limit is set to 4
        
        // All suggestions should start with 'A'
        for (String suggestion : suggestions) {
            assertTrue(suggestion.toUpperCase().startsWith("A"));
        }
        
        System.out.println("✅ Challenge 3 (Autocomplete BST) test passed!");
        System.out.println("Autocomplete suggestions for 'a': " + suggestions);
    }

    @Test
    public void testEmptyAndNullCases() {
        // Test edge cases
        SortUtils sortUtils = new SortUtils();
        
        // Test null list
        assertNull(sortUtils.sortById(null));
        
        // Test empty list
        List<MontadoraResponse.Montadora> emptyList = Arrays.asList();
        assertEquals(emptyList, sortUtils.sortById(emptyList));
        
        // Test single element
        List<MontadoraResponse.Montadora> singleElement = Arrays.asList(
            new MontadoraResponse.Montadora(1, "Ford")
        );
        assertEquals(singleElement, sortUtils.sortById(singleElement));
        
        System.out.println("✅ Edge cases test passed!");
    }
}