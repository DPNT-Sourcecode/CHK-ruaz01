package befaster.solutions.CHK;

import befaster.runner.SolutionNotImplementedException;

import java.util.HashMap;
import java.util.Map;

public class CheckoutSolution {
    public Integer checkout(String skus) {
//        throw new SolutionNotImplementedException();
        Map<Character, Integer> price = new HashMap<>();
        Map<Character, int[]> specialPrice = new HashMap<>();

        price.put('A', 50);
        price.put('B', 30);
        price.put('C', 20);
        price.put('D', 15);

        specialPrice.put('A', new int[]{3, 130});
        specialPrice.put('B', new int[]{2, 45});

        for(Character ch: skus.toCharArray()) {
            if(!price.containsKey(ch) || !Character.isDigit(ch)) {
                return -1;
            }
        }


       return -1;
    }
}
