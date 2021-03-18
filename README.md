# Meteor Challenge

## Definition 
This challenge consists of analyzing an input image and 
saying how many stars and how many meteors this image has. 
After this initial analysis, knowing that the meteors fall 
perpendicularly to the ground, it is necessary to check 
how many will fall into the water. There is also a fourth 
step which consists of finding the phrase that is hidden 
in the points of the sky.

1. Count the number of Stars.
2. Count the number of Meteors.
3. Count the number of Meteors that fell on the water.
4. Find the phrase that is hidden in the dots in the sky.
- - a. HINT 1: 177 Characters
- - b. HINT 2: Most of the last tasks' code can be reused for this one
    
The image used as input can be seen below: <br><br>
![Input Image](src/main/resources/image.png "Input image")<br>

Pixel Ref: <br>
A pure white pixel (255,255,255,255): Star <br>
A pure red pixel (255,0,0,255): Meteor <br>
A pure blue pixel (0,0,255,255): Water <br>
A pure black pixel (0,0,0,255): Ground <br>

## Answers:

<table>
   <tbody>
      <tr>
         <td>Number of Stars</td>
         <td>315</td>
      </tr>
      <tr>
         <td>Number of Meteors</td>
         <td>328</td>
      </tr>
      <tr>
         <td>Meteors falling on the Water</td>
         <td>105</td>
      </tr>
      <tr>
         <td>Hidden Phrase</td>
         <td></td>
      </tr>
   </tbody>
</table>

## About the project

This project is made using Java, Gradle and the IntelliJ IDE.

- Java: OpenJDK 15.0.2 <br>
- Gradle: 6.7<br>
- IntelliJ IDEA: IntelliJ IDEA Community Edition 2020.3.2 <br>

## About the program

The program consists of 5 classes. ``Matrix``, ``Meteor``, ``Meteor_Challenge``, ``Star`` and ``WaterDrop``.

- ``Meteor`` is used to denote a pixel that represents a meteor. <br>
- ``Star`` is used to denote a pixel that represents a star. <br>
- ``WaterDrop`` is used to denote a pixel that represents a water drop. <br>
- ``Meteor_Challenge`` is the class that contains the main method and reads the input image file. <br>
- ``Matrix`` is the class that holds all the pixels of the input file on a 2D Array. 

### The Matrix class

- <b>The constuctor</b> <br>
  It starts getting the height and the width of the image to use as column and row number, respectively.
  Then it initializes all of the ArrayLists. After that it populates the 2D Array called matrix using the
  method ``createUniverse``  passing the image as parameter. Then it calls the ``meteorStrike`` method. <br><br>

- <b>The createUniverse method</b> <br>
  It consists of two nested for loops. The outer loop iterates over the columns and the inner loop iterates 
  over the rows. In each iteration it gets the color from that pixel and stores on the matrix. But before going
  to the next iteration it checks if the actual pixel is star, meteor or water. This is achieved looking at the 
  color present on that pixel. If it is any of these specials pixels it gets stored on the dedicated array (if 
  its a star goes to constellation and so on). <br><br>
  With this is possible to discover the number of stars and meteors. Thus answering questions 1 and 2. The next
  question is answered on the next method ``meteorStrike``. <br><br>

- <b> The meteorStrike method</b> <br>
  Similar to the createUniverse method this one also is two nested loops. But here it won't iterate over the 
  entire image. It will iterate just over the meteors and ocean arrays. For every meteor on the array it will
  check if its column is the same as of any waterDrop on the ocean array. <br><br> 
  If it is this meteor is added on the meteorsOnWater array and the inner loops breaks and the outer one continues. 
  This is necessary because it prevents that the same meteor gets counted again for hiting another waterDrop that 
  is in the same column but deeper. <br><br>
  Then the size of the meteorsOnWater array is the answer for the third question. <br><br>
  
### The hidden phrase

To find the hidden phrase I tried three methods:

1. ``getHidenMessageUsingStars()`` <br>
On this method I tried looking how many stars there were in a row. Then I transformed the number of stars on a char
   using the ascii table. After looking up all the rows and count all the stars and transform these numbers in chars
   I couldn't think in a phrase. <br>
   
2. ``getHidenMessageUsingMeteors()`` <br>
On this method I tried something similar to the above one. But here I thought that the answer was on the meteors because
   they were more concentrated on the upper part of the image. I did the same thing as above. I've counted the number of 
   meteors per row then I transformed these numbers each to a char using the ascii table. No luck here either. <br>
   
The number of stars and meteors per line are almost all the same. So it isn't enough to form a phrase because the letters are
always A, B, C or D. And according to the first hint the phrase must contain 177 characters. But one of the methods returns 
220 chars and the other returns 90. <br>

3. ``getHidenMessageUsingAlpha()`` <br>
On my third attempt I thought that maybe there were some pixels hidden using transparency. The given image was a PNG and it 
   is possible on this kind of file. I tried the same approach as the above methods but there wasn’t even a pixel whose alpha
   value was less than 255. <br>