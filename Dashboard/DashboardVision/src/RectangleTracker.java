/**
 *
 * @author jacobgeorge
 */
//package team78.smartdashboard.extension.camera.tracking;

//import static com.googlecode.javacv.cpp.opencv_highgui.cvSaveImage;
import edu.wpi.first.smartdashboard.camera.WPICameraExtension;
import edu.wpi.first.smartdashboard.properties.BooleanProperty;
import edu.wpi.first.smartdashboard.properties.NumberProperty;
import edu.wpi.first.smartdashboard.robot.Robot;
import edu.wpi.first.wpijavacv.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JButton;

public class RectangleTracker extends WPICameraExtension {
 
 //DashboardFrame frame = new DashboardFrame(false);

    public static final String NAME = "Axis Rectangle Tracker";
    public final NumberProperty minRed = new NumberProperty(this, "Red Min", 0);    //0,23
    public final NumberProperty maxRed = new NumberProperty(this, "Red Max", 23);  
    public final NumberProperty minGrn = new NumberProperty(this, "Green Min", 119); // 119, 255
    public final NumberProperty maxGrn = new NumberProperty(this, "Green Max", 255);
    public final NumberProperty minBlu = new NumberProperty(this, "Blue Min", 17);   // 17,47
    public final NumberProperty maxBlu = new NumberProperty(this, "Blue Max", 47);
    public final NumberProperty minArea = new NumberProperty(this, "Minimum Area", 1000);
    public final NumberProperty maxArea = new NumberProperty(this, "Maximum Area", 10000);
    public final BooleanProperty bDilateFirst = new  BooleanProperty(this, "Dilate then Erode", true); 
    public final NumberProperty dilations = new NumberProperty(this, "Dilations", 2);
    public final NumberProperty erosions = new NumberProperty(this, "Erosions", 0);
    public final NumberProperty polygonAccuracy = new NumberProperty(this, "Polygon Accuracy", 4);
    public final BooleanProperty displayVertices = new BooleanProperty(this, "Display Vertices", true);
    public final BooleanProperty displayMatchCenter = new BooleanProperty(this, "Display Match Center", true);
    public final BooleanProperty displayImageCenter = new BooleanProperty(this, "Display Image Center", true);
    public final BooleanProperty displayMatchCoords = new BooleanProperty(this, "Display Match Coordinates", true);
    public final BooleanProperty displayContours = new BooleanProperty(this, "Display Contours", true);
    public final NumberProperty viewingAngle = new NumberProperty(this, "Viewing Angle", 55);
    public final NumberProperty heightTopTarget = new NumberProperty(this, "Height Top Target", 109);
    public final NumberProperty heightMidTarget = new NumberProperty(this, "Height Mid Target", 72);
    public final NumberProperty heightOfCamera = new NumberProperty(this, "Height of Camera", 52);
    public final NumberProperty targetSpeed = new NumberProperty(this, "Target Speed Factor", 0.5);
    public final BooleanProperty sendDataToRobot = new BooleanProperty(this, "Send Data to Robot", false);
    public final NumberProperty hgtPercent = new NumberProperty(this, "Height Percent filter", 10.0);
    public final NumberProperty centerdiff = new NumberProperty(this, "Center Diff", 0.05);
    public final NumberProperty xGain = new NumberProperty(this, "Xgain", 1.0);
    public final NumberProperty xOffset = new NumberProperty(this, "Xoffset", 0.0);
    public final NumberProperty Angle = new NumberProperty(this, "Angle answer", 0.0);
    public final NumberProperty Range = new NumberProperty(this, "Range answer", 0.0);
    
    WPIColor contourColour = new WPIColor(255, 255, 0);
    WPIColor centerColour = new WPIColor(0, 0, 255);
    WPIColor verticeColour = new WPIColor(255, 0, 0);
    WPIColor matchColour = new WPIColor(50, 100, 255);
    ArrayList<Match> matches = new ArrayList<Match>();

    JButton takePicture;
    String pictureDate;
    int frameNumber = 0;
    boolean takePictureNextFrame = false;

    long startTime = 0;
    long numFrames = 0;
    
    double range = 0.0;
    double xanswer = 0.0;
    int found = 0;
//    double midTargetSpace = 54.75;

    long lastSendTime = 0;

    @Override
    public void init() {
        super.init();

        takePicture = new JButton("Take Picture");
        takePicture.setActionCommand("takePicture");
        takePicture.setBounds(200, 0, 80, 30);
        add(takePicture);

        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ("takePicture".equals(e.getActionCommand())) {
                    takePictureNextFrame = true;
                }
            }
        };
        takePicture.addActionListener(actionListener);

        try {
            frameNumber = Integer.parseInt(readFile("./iter"));
            System.out.println("" + frameNumber);
        } catch (Exception e) {}
    }

    @Override
    public WPIImage processImage(WPIColorImage rawImage) {
        if (startTime == 0) startTime = System.currentTimeMillis();
        if (takePictureNextFrame) {
            frameNumber++;
            SimpleDateFormat imageDate = new SimpleDateFormat("HH-mm-ss");
            pictureDate = imageDate.format(new Date());
//            cvSaveImage("./images/" + frameNumber + "_rawImage_" + pictureDate + ".jpg", rawImage.image);

            try {
                writeFile("./iter", Integer.toString(frameNumber));
            } catch (Exception e) {}
        }

        // start image processing by applying rRGB min and max thresholds to the image
        WPIBinaryImage redMinImage = rawImage.getRedChannel().getThreshold(minRed.getValue().intValue());
        WPIBinaryImage redMaxImage = rawImage.getRedChannel().getThresholdInverted(maxRed.getValue().intValue());
        WPIBinaryImage greenMinImage = rawImage.getGreenChannel().getThreshold(minGrn.getValue().intValue());
        WPIBinaryImage greenMaxImage = rawImage.getGreenChannel().getThresholdInverted(maxGrn.getValue().intValue());
        WPIBinaryImage blueMinImage = rawImage.getBlueChannel().getThreshold(minBlu.getValue().intValue());
        WPIBinaryImage blueMaxImage = rawImage.getBlueChannel().getThresholdInverted(maxBlu.getValue().intValue());
        WPIBinaryImage binImage = redMinImage.getAnd(redMaxImage).getAnd(greenMinImage).getAnd(greenMaxImage).getAnd(blueMinImage).getAnd(blueMaxImage);
//        WPIBinaryImage binImage = greenMinImage.getAnd(greenMaxImage);
        redMinImage.dispose();
        redMaxImage.dispose();
        greenMinImage.dispose();
        greenMaxImage.dispose();
        blueMinImage.dispose();
        blueMaxImage.dispose();
		
        // next apply dialtion and erosion filters to get rid of speckles and strengthen edges
        if(bDilateFirst.getValue().booleanValue()) {
            binImage.dilate(dilations.getValue().intValue());
            binImage.erode(erosions.getValue().intValue());
        } else {
            binImage.erode(erosions.getValue().intValue());
            binImage.dilate(dilations.getValue().intValue());
        }

        // now create a list of contours for analysis
        WPIContour[] contours = binImage.findContours();

        if (displayContours.getValue().booleanValue())
            rawImage.drawContours(contours, contourColour, 2);
        
        // now the funs starts, get a list of candidates that are the targets we are seeking
        int index = 0;
        matches.clear();
        for (WPIContour contour : contours) {
            // first apply a gross min and max area filter
            if (contour.getHeight() * contour.getWidth() < minArea.getValue().intValue())
                continue;
            else if (contour.getHeight() * contour.getWidth() > maxArea.getValue().intValue())
                continue;
            // next classify as a polygon of certain accuracy
            WPIPolygon temp = contour.approxPolygon(polygonAccuracy.getValue().intValue());
            // make sure it is 4 cornered polygon and is convex
            if (temp.isConvex() && temp.getNumVertices() == 4) {
                Match tempMatch = new Match(temp, rawImage.getWidth(), rawImage.getHeight());
                if (tempMatch.points[0] != null && tempMatch.points[1] != null && 
                        tempMatch.points[2] != null && tempMatch.points[3] != null) {
                    matches.add(tempMatch);
                }
            }
        }
        // remove from the list rectangles that are fully enclosed inside others
        for (int i = 0; i < matches.size(); i++) {
            for (int j = 0; j < matches.size(); j++) {
                if (matches.get(i).isSubMatchOf(matches.get(j))) {
                    matches.remove(i);
                    i--;
                    break;
                }
            }
        }
        // calculate the average height and filter +/- greater than 10%
        double avgHgt = 0.0;
        for (int i = 0; i < matches.size(); i++) {
            avgHgt += matches.get(i).polygon.getHeight();
        }
        if (0 < matches.size())
            avgHgt /= matches.size();
        else
            avgHgt = 0.0;

            for (int i = 0; i < matches.size() && avgHgt != 0.0; i++) {
            if (Math.abs((matches.get(i).polygon.getHeight()/avgHgt)-1.0) > (hgtPercent.getValue().doubleValue()/100))
                matches.remove(i);
        }
       
        // identify targets so we can orient ourselves
        // first sort by y from highest to lowest
        for (int i = 0; i < matches.size(); i++) {
            for (int j = 0; j < matches.size(); j++) {
                if (matches.get(i).dY < matches.get(j).dY) {
                    Match temp = matches.set(i, matches.get(j));
                    matches.set(j, temp);
                }
            }
        }
        double vA = viewingAngle.getValue().doubleValue();
        double hTopT = heightTopTarget.getValue().doubleValue();
        double hOC = heightOfCamera.getValue().doubleValue();
        int fX = 0, fY = 0;

        found = matches.size();
        if (1 == found) {
            matches.get(0).angle = matches.get(0).angleFromTarget(vA);
            matches.get(0).range = matches.get(0).distanceInInches(vA, hTopT, hOC);
            xanswer = matches.get(0).dX;        // +/-1.0 answer
            range = matches.get(0).range;
            fX = matches.get(0).cX;
            fY = matches.get(0).cY;
        } else if (2 <= found) {
            // pick the highest one unless 2 side by side, in which case, 
            // pick closest to center
            double cdiff = centerdiff.getValue().doubleValue();
            // look at dY's of top 2 candidates and see if they are on same horizon
            double y1 = matches.get(0).dY;
            double y2 = matches.get(1).dY;
            if (cdiff > Math.abs(y1-y2)) {
                // they are even, take closest to middle
                double x1 = matches.get(0).dX;
                double x2 = matches.get(1).dX;
                if (Math.abs(x2) < Math.abs(x1))
                    index = 1;
            }
            matches.get(index).range = matches.get(index).distanceInInches(vA, hTopT, hOC);
            matches.get(index).angle = matches.get(index).angleFromTarget(vA);
            xanswer = matches.get(index).dX;
            range = matches.get(index).range;
            fX = matches.get(index).cX;
            fY = matches.get(index).cY;
        } else {
            found = 0;
            xanswer = 0.0;
            range = 0.0;
        }
//        Angle.setValue(xanswer);
//        Range.setValue(range);

        if (sendDataToRobot.getValue().booleanValue() &&
                System.currentTimeMillis() >= (lastSendTime + 9)) {
            lastSendTime = System.currentTimeMillis();
            Robot.getTable().putInt("targetp", found);
            Robot.getTable().putDouble("targetx", xanswer);
            Robot.getTable().putDouble("targetr", range);
        }

        for (int i = 0; i < matches.size(); i++) {
            if (0 < found) {
                if (index == i)
                    rawImage.drawPolygon(matches.get(i).polygon, verticeColour, 2);
                else
                    rawImage.drawPolygon(matches.get(i).polygon, matchColour, 2);
            }

            if (displayMatchCenter.getValue().booleanValue())
                rawImage.drawPoint(new WPIPoint(matches.get(i).cX, matches.get(i).cY), 
                        centerColour, 1);

             if (displayVertices.getValue().booleanValue())
                for (WPIPoint vertice : matches.get(i).points)
                    rawImage.drawPoint(vertice, verticeColour, 2);
        }

        if (displayImageCenter.getValue().booleanValue()) {
            int cX = rawImage.getWidth() / 2;
            int cY = rawImage.getHeight() / 2;
            rawImage.drawLine(new WPIPoint(cX, cY - 50), new WPIPoint(cX, cY + 50), centerColour, 2);
            rawImage.drawLine(new WPIPoint(cX - 50, cY), new WPIPoint(cX + 50, cY), centerColour, 2);
            if (0 < found) {
                rawImage.drawLine(new WPIPoint(fX, fY - 25), new WPIPoint(fX, fY + 25), verticeColour, 2);
                rawImage.drawLine(new WPIPoint(fX - 25, fY), new WPIPoint(fX + 25, fY), verticeColour, 2);
            }
        }
        
        if (takePictureNextFrame) {
            takePictureNextFrame = false;
//            cvSaveImage("./images/" + frameNumber + "_processedImage_" + pictureDate + ".jpg", rawImage.image);
//            cvSaveImage("./images/" + frameNumber + "_binaryImage_" + pictureDate + ".jpg", binImage.image);
        }

        numFrames++;
        binImage.dispose();
        return rawImage;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.YELLOW);

        String affix = (matches.size() == 1) ? "" : "es";
        String temp = matches.size() + " match" + affix + " found.";
        g.drawString(temp, 5, 40);

        long timeDifference = System.currentTimeMillis() - startTime;
        float secondsElapsed = timeDifference / 1000;
        float fps = Math.round(numFrames / secondsElapsed);
        g.drawString(Float.toString(fps) + " FPS", 5, 20);

        DecimalFormat f = new DecimalFormat("####.###");

        int i = 1;
        for (Match match : matches) {
            String values = "[" + i + "] X: " + f.format(match.dX) + " Y: " + f.format(match.dY + 
                    " R: " + f.format(match.range) + " A: " + f.format(match.angle));
//            g.drawString(values, match.cX + 4, match.cY + 12);
            g.drawString(values, 5, 40 + i * 20);

//            String distance = f.format(match.distanceInInches(viewingAngle.getValue().doubleValue(), heightTopTarget.getValue().doubleValue(), heightOfCamera.getValue().doubleValue())) + "\"";
//            g.drawString(distance, match.cX + 4, match.cY + 32);

//            String angle = f.format(match.angleFromTarget(viewingAngle.getValue().doubleValue()));
//            g.drawString(angle, match.cX + 4, match.cY + 52);

//            if (!displayMatchCoords.getValue().booleanValue()) continue;
//            int quadrant = 1;
//            for (WPIPoint point : match.points) {
//                String coordinates = Integer.toString(point.getX()) + ", " + Integer.toString(point.getY());
//                int dX = (quadrant == 2 || quadrant == 3) ? 5 : -60;
//                int dY = (quadrant == 1 || quadrant == 2) ? -5 : 5;
//                g.drawString(coordinates, point.getX() + dX, point.getY() + dY);
//                quadrant++;
//            }
        }
    }

    private String readFile(String path) throws IOException {
        FileInputStream stream = new FileInputStream(new File(path));
        try {
            FileChannel fc = stream.getChannel();
            MappedByteBuffer bb = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size());
            return Charset.defaultCharset().decode(bb).toString();
        } finally {
            stream.close();
        }
    }

    private void writeFile(String path, String data) throws IOException {
        FileOutputStream stream = new FileOutputStream(new File(path));
        try {
            stream.write(data.getBytes());
            stream.flush();
        } finally {
            stream.close();
        }
    }
}
