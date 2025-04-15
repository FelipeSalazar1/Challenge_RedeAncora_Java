package com.redeAncoraUsers.fiap.utils;

import java.util.List;
import java.util.Collections;
import com.redeAncoraUsers.fiap.validators.MontadoraResponse;

public class SortUtils {
    public static List<MontadoraResponse.Montadora> sortById(List<MontadoraResponse.Montadora> list) {
        if (list == null || list.size() <= 1) {
            return list;
        }
        quickSort(list, 0, list.size() - 1);
        return list;
    }

    private static void quickSort(List<MontadoraResponse.Montadora> list, int low, int high) {
        if (low < high) {
            int pi = partition(list, low, high);
            quickSort(list, low, pi - 1);
            quickSort(list, pi + 1, high);
        }
    }

    private static int partition(List<MontadoraResponse.Montadora> list, int low, int high) {
        int pivot = list.get(high).id();
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (list.get(j).id() < pivot) {
                i++;
                Collections.swap(list, i, j);
            }
        }

        Collections.swap(list, i + 1, high);
        return i + 1;
    }
}
