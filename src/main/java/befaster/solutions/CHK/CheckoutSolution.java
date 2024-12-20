package befaster.solutions.CHK;

import befaster.runner.SolutionNotImplementedException;

import java.util.HashMap;
import java.util.Map;

public class CheckoutSolution {
    public Integer checkout(String skus) {
        if(skus.trim().isEmpty()) {
            return 0;
        }
//        throw new SolutionNotImplementedException();
        Map<Character, Integer> price = new HashMap<>();
        Map<Character, int[]> specialPrice = new HashMap<>();
        Map<Character, Integer> items = new HashMap<>();

        price.put('A', 50);
        price.put('B', 30);
        price.put('C', 20);
        price.put('D', 15);

        specialPrice.put('A', new int[]{3, 130});
        specialPrice.put('B', new int[]{2, 45});

        for(Character ch: skus.toCharArray()) {
            if(!price.containsKey(ch)) {
                return -1;
            }
            int n = items.getOrDefault(ch, 0);
            n++;
            items.put(ch, n);
        }

        int total = 0;
        for(Character ch: items.keySet()) {
            int number = items.get(ch);
            if(specialPrice.containsKey(ch)) {
                int offerMin = specialPrice.get(ch)[0];
                int offerVal = specialPrice.get(ch)[1];

                int offerCount = number / offerMin;
                total += offerCount * offerVal;
                number = number % offerMin;
            }
            int regularPrice = price.get(ch);
            total += regularPrice * number;
        }

        return total;
    }
}
