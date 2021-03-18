import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Meteor_Challenge {
    public static void main(String[] args) throws IOException {
        String path = Meteor_Challenge.class.getResource("image.png").getFile();
        File file = new File(path);
        BufferedImage image = ImageIO.read(file);

        Matrix matrix = new Matrix(image);

        matrix.printResults();

//        System.out.println(matrix.getHidenMessageUsingStars());
//        System.out.println(matrix.getHidenMessageUsingMeteors());
//        System.out.println(matrix.getHidenMessageUsingAlpha());

//        Used for debug purposes
//        matrix.newImage();
//        System.out.println(matrix);
    }

}
