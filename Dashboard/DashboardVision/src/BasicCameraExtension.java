import edu.wpi.first.smartdashboard.camera.WPICameraExtension;
import edu.wpi.first.wpijavacv.WPIColorImage;
import edu.wpi.first.wpijavacv.WPIImage;

/**
 *
 * @author jacobgeorge
 */
public class BasicCameraExtension extends WPICameraExtension {
    @Override
    public WPIImage processImage(WPIColorImage rawImage) {
        return rawImage;
    }
}
 