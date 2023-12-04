import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Recursion {

    public static long arraySum(int[] data, int position){
        if (data.length == position || data.length == 0){
            return 0;
        }else {
            return data[position] + arraySum(data, position+1);
        }

    }
    public static int arrayMin(int[] data, int position){
        int placehold = 0;
        int[]newArray = Arrays.copyOf(data, data.length);
        if (data.length-1 == position){
            return newArray[newArray.length-1];
        } else if (newArray[position] < data[position+1]) {
            placehold = newArray[position];
            newArray[position] = data[position+1];
            newArray[position+1] = placehold;
            return arrayMin(newArray, position+1);
        } else {
            return arrayMin(newArray, position+1);
        }
    }

    public static boolean isWordSymmetric(String[] words, int start, int end){
        if (start == end || start > end){
            return true;
        }else if (words.length < 1){
            return false;
        } else {
            if (words[start].toLowerCase().equals(words[end].toLowerCase())){
                isWordSymmetric(words, start+1, end-1);
                return true;
            }else {
                return false;
            }
        }

    }
    public static double computePyramidWeights(double[][] weights, int row, int column) {
        if (row < 0 || column < 0){
            return 0;
        }
        if (weights[0].length < 1){
            return 0;
        }
        if (row > weights.length-1 || column > weights[row].length-1){
            return 0;
        }
        if (row == 0) {
            if (column == 0) {
                return weights[row][column];
            }else {
                return 0;
            }
        }else {
            return weights[row][column] + (computePyramidWeights(weights, row -1, column-1)/ 2) + (computePyramidWeights(weights, row-1, column)/ 2);
        }
    }


    public static class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }
        public int getY() {
            return y;
        }
    }


    public static int howManyOrganisms(char[][] image) {
        List<List<Point>> organisms = new ArrayList<>();
        char[] letters = {
                'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h',
                'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p',
                'q', 'r', 's', 't', 'u', 'v', 'w', 'x',
                'y', 'z',
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
                'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
                'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X',
                'Y', 'Z'
        };
        int count = 0;
        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[0].length; j++) {
                if (image[i][j] == '*') {
                    count++;
                    Point currentPoint = new Point(i, j);
                    ArrayList<Point> currentOrganism = findOrganism(image, currentPoint);
                    organisms.add(currentOrganism);
                    for (int spot = 0; spot < currentOrganism.toArray().length; spot++) {
                        int orgPointX = ((Point) currentOrganism.toArray()[spot]).getX();
                        int orgPointY = ((Point) currentOrganism.toArray()[spot]).getY();
                        image[orgPointX][orgPointY] = letters[count - 1];
                    }
                }
            }
        }
        return count;
    }
    public static ArrayList<Point> findOrganism(char[][] image, Point start){
        Point test = new Point(0,0);
        ArrayList<Point> organism = new ArrayList<>();
        organism.add(test);
        return organism;


    }


    public static void main(String[] args) {


        int[] sumMe = { 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89 };
        System.out.printf("Array Sum: %d\n", arraySum(sumMe, 0));

        int[] minMe = { 0, 1, 1, 2, 3, 5, 8, -42, 13, 21, 34, 55, 89 };
        System.out.printf("Array Min: %d\n", arrayMin(minMe, 0));

        String[] amISymmetric =  {
                "You can cage a swallow can't you but you can't swallow a cage can you",
                "I still say cS 1410 is my favorite class"
        };
        for (String test : amISymmetric) {
            String[] words = test.toLowerCase().split(" ");
            System.out.println();
            System.out.println(test);
            System.out.printf("Is word symmetric: %b\n", isWordSymmetric(words, 0, words.length - 1));
        }

        double weights[][] = {
                { 51.18 },
                { 55.90, 131.25 },
                { 69.05, 133.66, 132.82 },
                { 53.43, 139.61, 134.06, 121.63 }
        };
        System.out.println();
        System.out.println("--- Weight Pyramid ---");
        for (int row = 0; row < weights.length; row++) {
            for (int column = 0; column < weights[row].length; column++) {
                double weight = computePyramidWeights(weights, row, column);
                System.out.printf("%.2f ", weight);
            }
            System.out.println();
        }

        char image[][] = {
                {'*','*',' ',' ',' ',' ',' ',' ','*',' '},
                {' ','*',' ',' ',' ',' ',' ',' ','*',' '},
                {' ',' ',' ',' ',' ',' ','*','*',' ',' '},
                {' ','*',' ',' ','*','*','*',' ',' ',' '},
                {' ','*','*',' ','*',' ','*',' ','*',' '},
                {' ','*','*',' ','*','*','*','*','*','*'},
                {' ',' ',' ',' ',' ',' ',' ',' ','*',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ','*',' '},
                {' ',' ',' ','*','*','*',' ',' ','*',' '},
                {' ',' ',' ',' ',' ','*',' ',' ','*',' '}
        };
        int howMany = howManyOrganisms(image);
        System.out.println();
        System.out.println("--- Labeled Organism Image ---");
        for (char[] line : image) {
            for (char item : line) {
                System.out.print(item);
            }
            System.out.println();
        }
        System.out.printf("There are %d organisms in the image.\n", howMany);

    }
}
