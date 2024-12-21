package befaster.solutions.CHK;

import befaster.runner.SolutionNotImplementedException;

import java.util.*;

public class CheckoutSolution {

//    public Map<Character, Integer> reduceItem(Map)

    public Integer checkout(String skus) {
        if(skus.trim().isEmpty()) {
            return 0;
        }
//        throw new SolutionNotImplementedException();
        Map<Character, Integer> price = new HashMap<>();
        Map<Character, Map<Integer, Integer>> specialPrice = new HashMap<>();
        Map<Character, Integer> items = new HashMap<>();

        price.put('A', 50);
        price.put('B', 30);
        price.put('C', 20);
        price.put('D', 15);
        price.put('E', 40);
        price.put('F', 10);

        specialPrice.put('A', Map.of(3,130,5,200));
        specialPrice.put('B', Map.of(2,45));

        for(Character ch: skus.toCharArray()) {
            if(!price.containsKey(ch)) {
                return -1;
            }
            int n = items.getOrDefault(ch, 0);
            n++;
            items.put(ch, n);
        }

        int countE = items.getOrDefault('E', 0);
        if(countE > 1) {
            int countB = items.getOrDefault('B', 0);
            countB = countB - (countE/2);
            countB = Math.max(countB, 0);
            items.put('B', countB);
        }

        int countF = items.getOrDefault('F', 0);
        countF = countF - (countF/3);
        items.put('F', countF);

        int total = 0;
        for(Character ch: items.keySet()) {
            int number = items.get(ch);
            if(specialPrice.containsKey(ch)) {
                Map<Integer, Integer> offer = specialPrice.get(ch);
                List<Integer> specialCounts = new java.util.ArrayList<>(offer.keySet().stream().toList());
                specialCounts.sort(Comparator.reverseOrder());
                for(int offerMin: specialCounts) {
                    int offerCount = number / offerMin;
                    total += offerCount * offer.get(offerMin);
                    number = number % offerMin;
                }

            }
            int regularPrice = price.get(ch);
            total += regularPrice * number;
        }

        return total;
    }
}
