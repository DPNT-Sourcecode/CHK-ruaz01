package befaster.solutions.CHK;

import befaster.runner.SolutionNotImplementedException;

import java.util.*;

public class CheckoutSolution {

    public Map<Character, Integer> reduceItem(Map<Character, Integer> items, Map<Character, String> buyGetOffer) {
        for(Character ch1: buyGetOffer.keySet()) {
            String[] parts = buyGetOffer.get(ch1).split(" ");
            int offerCount = Integer.parseInt(parts[0]);
            Character ch2 = parts[1].charAt(0);

            int count1 = items.getOrDefault(ch1, 0);
            int count2 = items.getOrDefault(ch2, 0);

            if(ch1 == ch2) {
                offerCount++;
                count1 = count1 - (count1/offerCount);
                items.put(ch1, count1);
            } else {
                count2 = count2 - (count1/offerCount);
                count2 = Math.max(count2, 0);
                items.put(ch2, count2);
            }
        }

        return items;
    }

    public Map<Character, Integer> combo(Map<Character, Integer> items) {
        int total  = 0;



        return items;
    }

    public Integer checkout(String skus) {
        if(skus.trim().isEmpty()) {
            return 0;
        }
//        throw new SolutionNotImplementedException();
        Map<Character, Integer> price = new HashMap<>();
        Map<Character, Map<Integer, Integer>> specialPrice = new HashMap<>();
        Map<Character, Integer> items = new HashMap<>();
        Map<Character, String> buyGetOffer = new HashMap<>();

        price.put('A', 50);
        price.put('B', 30);
        price.put('C', 20);
        price.put('D', 15);
        price.put('E', 40);
        price.put('F', 10);
        price.put('G', 20);
        price.put('H', 10);
        price.put('I', 35);
        price.put('J', 60);
        price.put('K', 70);
        price.put('L', 90);
        price.put('M', 15);
        price.put('N', 40);
        price.put('O', 10);
        price.put('P', 50);
        price.put('Q', 30);
        price.put('R', 50);
        price.put('S', 20);
        price.put('T', 20);
        price.put('U', 40);
        price.put('V', 50);
        price.put('W', 20);
        price.put('X', 17);
        price.put('Y', 20);
        price.put('Z', 21);

        specialPrice.put('A', Map.of(3,130,5,200));
        specialPrice.put('B', Map.of(2,45));
        specialPrice.put('H', Map.of(5,45, 10, 80));
        specialPrice.put('K', Map.of(2,120));
        specialPrice.put('P', Map.of(5,200));
        specialPrice.put('Q', Map.of(3,80));
        specialPrice.put('V', Map.of(2,90, 3, 130));

        buyGetOffer.put('E', "2 B");
        buyGetOffer.put('F', "2 F");
        buyGetOffer.put('N', "3 M");
        buyGetOffer.put('R', "3 Q");
        buyGetOffer.put('U', "3 U");


        for(Character ch: skus.toCharArray()) {
            if(!price.containsKey(ch)) {
                return -1;
            }
            int n = items.getOrDefault(ch, 0);
            n++;
            items.put(ch, n);
        }

//        int countE = items.getOrDefault('E', 0);
//        if(countE > 1) {
//            int countB = items.getOrDefault('B', 0);
//            countB = countB - (countE/2);
//            countB = Math.max(countB, 0);
//            items.put('B', countB);
//        }
//
//        int countF = items.getOrDefault('F', 0);
//        countF = countF - (countF/3);
//        items.put('F', countF);

        int total = 0;

        reduceItem(items, buyGetOffer);
        List<Character> characters = Arrays.asList('X', 'S', 'T', 'Y', 'Z');

        int min3 = 0;
        for(Character ch: characters) {
            min3 += items.getOrDefault(ch, 0);
        }
        int comboCount = min3/3;
        int gap = min3%3;

        total += comboCount * 45;
        for(Character ch: characters) {
            int currentcount = items.getOrDefault(ch, 0);
            if(currentcount >= gap) {
                total += gap * price.get(ch);
                break;
            } else {
                total += currentcount * price.get(ch);
                gap -= currentcount;
            }
            if(gap < 1) {
                break;
            }
        }
//        while (true) {
//            int min3 = 0;
//            for(Character ch: characters) {
//                if(items.getOrDefault(ch, 0) > 0) {
//                    min3++;
//                }
//            }
//            if(min3 < 3) {
//                break;
//            } else {
//                total += 45;
//                int counter = 0;
//                for(Character ch: characters) {
//                    int tmp = items.getOrDefault(ch, 0);
//                    if(tmp > 0) {
//                        counter++;
//                        tmp--;
//                        items.put(ch, tmp);
//                    }
//                    if(counter == 3) {
//                        break;
//                    }
//                }
//            }
//        }


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


