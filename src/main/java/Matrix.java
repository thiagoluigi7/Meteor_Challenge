import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Matrix {
    private int column;
    private int row;
    private Color[][] matrix;
    private ArrayList<Star> constallation;
    private ArrayList<Meteor> meteors;
    private ArrayList<WaterDrop> ocean;
    private ArrayList<Meteor> meteorsOnWater;

    public Matrix(BufferedImage image) {
        column = image.getHeight();
        row = image.getWidth();

        constallation = new ArrayList<>();
        meteors = new ArrayList<>();
        ocean = new ArrayList<>();
        meteorsOnWater = new ArrayList<>();

        matrix = createUniverse(image);
        meteorStrike();
    }

    private Color[][] createUniverse(BufferedImage image) {
        matrix = new Color[column][row];
        for (int x = 0; x < column; x++) {
            for (int y = 0; y < row; y++) {
                matrix[x][y] = new Color(image.getRGB(x, y));
                if (isStar(matrix[x][y])) {
//                    System.out.println("Star found!");
                    Star star = new Star(x, y);
                    constallation.add(star);
                }
                if (isMeteor(matrix[x][y])) {
//                    System.out.println("Meteor found!");
                    Meteor meteor = new Meteor(x, y);
                    meteors.add(meteor);
                }
                if (isWater(matrix[x][y])) {
//                    System.out.println("Water drop found!");
                    WaterDrop waterDrop = new WaterDrop(x, y);
                    ocean.add(waterDrop);
                }
            }
        }
        return matrix;
    }

    private void meteorStrike() {
//        System.out.println("Meteor striking down on water!");
        for(Meteor meteor : meteors) {
            for(WaterDrop waterDrop : ocean) {
                if (waterDrop.getColumn() == meteor.getColumn()) {
                    meteorsOnWater.add(meteor);
                    break;
                }
            }
        }
    }

    private Boolean isStar(Color color) {
        if ((color.getRed() == 255) && (color.getGreen() == 255) && (color.getBlue() == 255)) {
            return true;
        } else {
            return false;
        }
    }

    private Boolean isMeteor(Color color) {
        if ((color.getRed() == 255) && (color.getGreen() == 0) && (color.getBlue() == 0)) {
            return true;
        } else {
            return false;
        }
    }

    private Boolean isWater(Color color) {
        if ((color.getRed() == 0) && (color.getGreen() == 0) && (color.getBlue() == 255)) {
            return true;
        } else {
            return false;
        }
    }

    private Boolean isGround(Color color) {
        if ((color.getRed() == 0) && (color.getGreen() == 0) && (color.getBlue() == 0)) {
            return true;
        } else {
            return false;
        }
    }

    private int getNumberOfStars() {
        return constallation.size();
    }

    private int getNumberOfMeteors() {
        return meteors.size();
    }

    private int getNumberOfMeteorsOnWater() {
        return meteorsOnWater.size();
    }

    public String getHidenMessageUsingStars() {
        ArrayList<Integer> numbersPerLine = new ArrayList<>();
        int quantity = 0;
        for (int y = 0; y < row; y++) {
            for (int x = 0; x < column; x++) {
                if (isStar(matrix[x][y])) {
                    quantity++;
                }
            }
            numbersPerLine.add(quantity);
            quantity = 0;
        }
        String phrase = "";
        for (Integer integer : numbersPerLine) {
            if ((integer < 1) || (integer > 26)) {
                //System.out.println("Error: " + integer);
            } else {
                phrase = phrase + (char) (integer + 64);
            }
        }
        return "The phrase using the stars is: " + phrase;
    }

    public String getHidenMessageUsingMeteors() {
        ArrayList<Integer> numbersPerLine = new ArrayList<>();
        int quantity = 0;
        for (int y = 0; y < row; y++) {
            for (int x = 0; x < column; x++) {
                if (isMeteor(matrix[x][y])) {
                    quantity++;
                }
            }
            numbersPerLine.add(quantity);
            quantity = 0;
        }
        String phrase = "";
        for (Integer integer : numbersPerLine) {
            if ((integer < 1) || (integer > 26)) {
                //System.out.println("Error: " + integer);
            } else {
                phrase = phrase + (char) (integer + 64);
            }
        }
        return "The phrase using the meteors is: " + phrase;
    }

    public String getHidenMessageUsingAlpha() {
        ArrayList<Integer> numbersPerLine = new ArrayList<>();
        int quantity = 0;
        for (int y = 0; y < row; y++) {
            for (int x = 0; x < column; x++) {
//                System.out.print(matrix[x][y].getAlpha());
                if (matrix[x][y].getAlpha() != 255) {
                    System.out.println(matrix[x][y].getAlpha());
                    quantity++;
                }
            }
            numbersPerLine.add(quantity);
            quantity = 0;
        }
        String phrase = "";
        for (Integer integer : numbersPerLine) {
            if ((integer < 1) || (integer > 26)) {
                //System.out.println("Error: " + integer);
            } else {
                phrase = phrase + (char) (integer + 64);
            }
        }
        return "The phrase using the alpha value is: " + phrase;
    }

    public void printResults() {
        printNumberOfStars();
        printNumberOfMeteors();
        printNumberOfMeteorsOnWater();
    }

    private void printNumberOfStars() {
        System.out.println("The number of stars is: " + getNumberOfStars());
    }
    private void printNumberOfMeteors() {
        System.out.println("The number of meteors is: " + getNumberOfMeteors());
    }
    private void printNumberOfMeteorsOnWater() {
        System.out.println("The number of meteors that hit the water is: " + getNumberOfMeteorsOnWater());
    }

    // Used for debug purpose
    public void newImage() throws IOException {
        BufferedImage bufferedImage = new BufferedImage(matrix.length, matrix[0].length, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < column; x++) {
            for (int y = 0; y < row; y++) {
                bufferedImage.setRGB(x, y, matrix[x][y].getRGB());
            }
        }
        File outputfile = new File(Meteor_Challenge.class.getResource("outputImage.png").getFile());
        if (outputfile.exists()) {
            System.out.println("Escrevendo arquivo de imagem...");
            ImageIO.write(bufferedImage, "png", outputfile);
        }
    }

    @Override
    public String toString() {
        for (int x = 0; x < column; x++) { // Coluna
            for (int y = 0; y < row; y++) { // Linha
                System.out.print(matrix[x][y] + " ");
            }
            System.out.println("");
        }
        return "";
    }
}
