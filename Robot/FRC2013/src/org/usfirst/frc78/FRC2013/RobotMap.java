// RobotBuilder Version: 0.0.2
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in th future.
package org.usfirst.frc78.FRC2013;
    
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.CounterBase.EncodingType; import edu.wpi.first.wpilibj.Encoder.PIDSourceParameter;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import java.util.Vector;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public static Encoder chassisLeftEncoder;
    public static Encoder chassisRightEncoder;
    public static Gyro chassisHeadingGyro;
    public static SpeedController chassisLFmotor;
    public static SpeedController chassisLRmotor;
    public static SpeedController chassisRFmotor;
    public static SpeedController chassisRRmotor;
    public static RobotDrive chassisDrive;
    public static SpeedController shooterFrontMotor;
    public static Encoder shooterFrontEncoder;
    public static PIDController shooterFrontPID;
    public static SpeedController shooterRearMotor;
    public static Encoder shooterRearEncoder;
    public static PIDController shooterRearPID;
    public static SpeedController shooterTiltMotor;
    public static AnalogChannel shooterTiltPot;
    public static PIDController shooterTiltPID;
    public static SpeedController shooterTriggerMotor;
    public static DigitalInput shooterTriggerStop;
    public static Servo stackerLowerGate1;
    public static Servo stackerLowerGate2;
    public static Servo stackerLowerGate3;
    public static Servo stackerUpperGate1;
    public static Servo stackerUpperGate2;
    public static Servo stackerUpperGate3;
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public static void init() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        chassisLeftEncoder = new Encoder(1, 11, 1, 12, false, EncodingType.k4X);
	LiveWindow.addSensor("Chassis", "LeftEncoder", chassisLeftEncoder);
        chassisLeftEncoder.setDistancePerPulse(0.077);
        chassisLeftEncoder.setPIDSourceParameter(PIDSourceParameter.kDistance);
        chassisLeftEncoder.start();
        chassisRightEncoder = new Encoder(1, 13, 1, 14, false, EncodingType.k4X);
	LiveWindow.addSensor("Chassis", "RightEncoder", chassisRightEncoder);
        chassisRightEncoder.setDistancePerPulse(0.077);
        chassisRightEncoder.setPIDSourceParameter(PIDSourceParameter.kDistance);
        chassisRightEncoder.start();
        chassisHeadingGyro = new Gyro(1, 1);
	LiveWindow.addSensor("Chassis", "HeadingGyro", chassisHeadingGyro);
        chassisHeadingGyro.setSensitivity(0.007);
        chassisLFmotor = new Victor(1, 1);
	LiveWindow.addActuator("Chassis", "LFmotor", (Victor) chassisLFmotor);
        
        chassisLRmotor = new Victor(1, 2);
	LiveWindow.addActuator("Chassis", "LRmotor", (Victor) chassisLRmotor);
        
        chassisRFmotor = new Victor(1, 3);
	LiveWindow.addActuator("Chassis", "RFmotor", (Victor) chassisRFmotor);
        
        chassisRRmotor = new Victor(1, 4);
	LiveWindow.addActuator("Chassis", "RRmotor", (Victor) chassisRRmotor);
        
        chassisDrive = new RobotDrive(chassisLFmotor, chassisLRmotor,
              chassisRFmotor, chassisRRmotor);
	
        chassisDrive.setSafetyEnabled(false);
        chassisDrive.setExpiration(0.5);
        chassisDrive.setSensitivity(0.5);
        chassisDrive.setMaxOutput(1.0);
        shooterFrontMotor = new Victor(1, 7);
	LiveWindow.addActuator("Shooter", "FrontMotor", (Victor) shooterFrontMotor);
        
        shooterFrontEncoder = new Encoder(1, 1, 1, 2, false, EncodingType.k1X);
	LiveWindow.addSensor("Shooter", "FrontEncoder", shooterFrontEncoder);
        shooterFrontEncoder.setDistancePerPulse(0.01);
        shooterFrontEncoder.setPIDSourceParameter(PIDSourceParameter.kRate);
        shooterFrontEncoder.start();
        shooterFrontPID = new PIDController(1.0E-5, 0.0, 0.0, 6.67E-5, shooterFrontEncoder, shooterFrontMotor, 0.02);
	LiveWindow.addActuator("Shooter", "FrontPID", shooterFrontPID);
        shooterFrontPID.setContinuous(false); shooterFrontPID.setAbsoluteTolerance(3000.0); 
        shooterFrontPID.setInputRange(-15000.0, 15000.0);
        shooterFrontPID.setOutputRange(-1.0, 1.0);        
        shooterRearMotor = new Victor(1, 8);
	LiveWindow.addActuator("Shooter", "RearMotor", (Victor) shooterRearMotor);
        
        shooterRearEncoder = new Encoder(1, 3, 1, 4, false, EncodingType.k1X);
	LiveWindow.addSensor("Shooter", "RearEncoder", shooterRearEncoder);
        shooterRearEncoder.setDistancePerPulse(0.01);
        shooterRearEncoder.setPIDSourceParameter(PIDSourceParameter.kRate);
        shooterRearEncoder.start();
        shooterRearPID = new PIDController(1.0E-5, 0.0, 0.0, 6.67E-5, shooterRearEncoder, shooterRearMotor, 0.02);
	LiveWindow.addActuator("Shooter", "RearPID", shooterRearPID);
        shooterRearPID.setContinuous(false); shooterRearPID.setAbsoluteTolerance(1000.0); 
        shooterRearPID.setInputRange(-10000.0, 10000.0);
        shooterRearPID.setOutputRange(-1.0, 1.0);        
        shooterTiltMotor = new Victor(1, 5);
	LiveWindow.addActuator("Shooter", "TiltMotor", (Victor) shooterTiltMotor);
        
        shooterTiltPot = new AnalogChannel(1, 2);
	LiveWindow.addSensor("Shooter", "TiltPot", shooterTiltPot);
        
        shooterTiltPID = new PIDController(0.4, 0.0, 0.0, 0.0, shooterTiltPot, shooterTiltMotor, 0.02);
	LiveWindow.addActuator("Shooter", "TiltPID", shooterTiltPID);
        shooterTiltPID.setContinuous(false); shooterTiltPID.setAbsoluteTolerance(2.5); 
        shooterTiltPID.setInputRange(0.0, 90.0);
        shooterTiltPID.setOutputRange(-1.0, 1.0);        
        shooterTriggerMotor = new Victor(1, 6);
	LiveWindow.addActuator("Shooter", "TriggerMotor", (Victor) shooterTriggerMotor);
        
        shooterTriggerStop = new DigitalInput(1, 5);
	LiveWindow.addSensor("Shooter", "TriggerStop", shooterTriggerStop);
        
        stackerLowerGate1 = new Servo(2, 1);
	LiveWindow.addActuator("Stacker", "LowerGate1", stackerLowerGate1);
        
        stackerLowerGate2 = new Servo(2, 2);
	LiveWindow.addActuator("Stacker", "LowerGate2", stackerLowerGate2);
        
        stackerLowerGate3 = new Servo(2, 3);
	LiveWindow.addActuator("Stacker", "LowerGate3", stackerLowerGate3);
        
        stackerUpperGate1 = new Servo(2, 4);
	LiveWindow.addActuator("Stacker", "UpperGate1", stackerUpperGate1);
        
        stackerUpperGate2 = new Servo(2, 5);
	LiveWindow.addActuator("Stacker", "UpperGate2", stackerUpperGate2);
        
        stackerUpperGate3 = new Servo(2, 6);
	LiveWindow.addActuator("Stacker", "UpperGate3", stackerUpperGate3);
        
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
    }
}