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
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc78.FRC2013.commands.*;
/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);
    
    // Another type of button you can create is a DigitalIOButton, which is
    // a button or switch hooked up to the cypress module. These are useful if
    // you want to build a customized operator interface.
    // Button button = new DigitalIOButton(1);
    
    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.
    
    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:
    
    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());
    
    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());
    
    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());
    
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public JoystickButton tiltToBalanceBut;
    public JoystickButton tiltToPreBalance;
    public Joystick driverJoystick;
    public JoystickButton loadPositionBut;
    public JoystickButton tiltSlowSpeedBut;
    public JoystickButton spotLightOnBut;
    public JoystickButton spotLightOffBut;
    public JoystickButton shoot5ptOneBut;
    public JoystickButton tilt2PositionBut;
    public JoystickButton tilt3ptCornerBut;
    public JoystickButton tilt2ptWallBut;
    public JoystickButton tilt3ptWallBut;
    public JoystickButton shootOneBut;
    public JoystickButton shootFourBut;
    public JoystickButton tilt5ptAngleBut;
    public JoystickButton noServoShoot1But;
    public Joystick shooterJoystick;
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public OI() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        shooterJoystick = new Joystick(2);
        
        noServoShoot1But = new JoystickButton(shooterJoystick, 11);
        noServoShoot1But.whileHeld(new Shoot1());
        tilt5ptAngleBut = new JoystickButton(shooterJoystick, 12);
        tilt5ptAngleBut.whileHeld(new Tilt5ptMove());
        shootFourBut = new JoystickButton(shooterJoystick, 10);
        shootFourBut.whenPressed(new LoadAndShootFour());
        shootOneBut = new JoystickButton(shooterJoystick, 9);
        shootOneBut.whenPressed(new LoadAndShootOne());
        tilt3ptWallBut = new JoystickButton(shooterJoystick, 8);
        tilt3ptWallBut.whileHeld(new Tilt3ptWall());
        tilt2ptWallBut = new JoystickButton(shooterJoystick, 7);
        tilt2ptWallBut.whileHeld(new Tilt2ptWall());
        tilt3ptCornerBut = new JoystickButton(shooterJoystick, 6);
        tilt3ptCornerBut.whileHeld(new TiltFront3ptCorner());
        tilt2PositionBut = new JoystickButton(shooterJoystick, 5);
        tilt2PositionBut.whileHeld(new TiltFront2ptCorner());
        shoot5ptOneBut = new JoystickButton(shooterJoystick, 4);
        shoot5ptOneBut.whenPressed(new LoadAndShoot5ptOne());
        spotLightOffBut = new JoystickButton(shooterJoystick, 3);
        spotLightOffBut.whenReleased(new TargetSpotOff());
        spotLightOnBut = new JoystickButton(shooterJoystick, 3);
        spotLightOnBut.whenPressed(new TargetSpotOn());
        tiltSlowSpeedBut = new JoystickButton(shooterJoystick, 2);
        tiltSlowSpeedBut.whileHeld(new TiltFlipSpeed());
        loadPositionBut = new JoystickButton(shooterJoystick, 1);
        loadPositionBut.whileHeld(new TiltMoveToLoad());
        driverJoystick = new Joystick(1);
        
        tiltToPreBalance = new JoystickButton(driverJoystick, 5);
        tiltToPreBalance.whileHeld(new TiltMoveToPreBalance());
        tiltToBalanceBut = new JoystickButton(driverJoystick, 6);
        tiltToBalanceBut.whileHeld(new TiltMoveToBalance());
	    
        // SmartDashboard Buttons
        SmartDashboard.putData("AutoMoveFoward5", new AutoMoveFoward5());
        SmartDashboard.putData("AutoMoveBack5", new AutoMoveBack5());
        SmartDashboard.putData("AutoTurn90", new AutoTurn90());
        SmartDashboard.putData("AutoTurn270", new AutoTurn270());
        SmartDashboard.putData("LoadAndShootOne", new LoadAndShootOne());
        SmartDashboard.putData("LoadAndShootFour", new LoadAndShootFour());
        SmartDashboard.putData("LoadAndShoot5ptOne", new LoadAndShoot5ptOne());
        SmartDashboard.putData("StackInitServos", new StackInitServos());
        SmartDashboard.putData("StackChamberFrisbee", new StackChamberFrisbee());
        SmartDashboard.putData("StackOpenAllServos", new StackOpenAllServos());
        SmartDashboard.putData("TiltResetEncoder", new TiltResetEncoder());
        SmartDashboard.putData("Shoot1", new Shoot1());
        SmartDashboard.putData("DriveForwardFullSpeed", new DriveForwardFullSpeed());
        SmartDashboard.putData("DriveForwardHalfSpeed", new DriveForwardHalfSpeed());
        SmartDashboard.putData("ChassisAutoHang", new ChassisAutoHang());
        SmartDashboard.putData("AutoNewRear3", new AutoNewRear3());
        SmartDashboard.putData("AutoNewRear2", new AutoNewRear2());
        SmartDashboard.putData("Auto3ptWallMove", new Auto3ptWallMove());
        SmartDashboard.putData("AutoDrivetoCornerShoot", new AutoDrivetoCornerShoot());
        SmartDashboard.putData("AutoPyramidDriveBackShoot", new AutoPyramidDriveBackShoot());
        SmartDashboard.putData("Command 1", new Command1());
        SmartDashboard.putData("TiltMoveToPreBalance", new TiltMoveToPreBalance());
        SmartDashboard.putData("TiltWithJoystick", new TiltWithJoystick());
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
//        SmartDashboard.putData("ChassisMove", new ChassisMove(18.8));
//        SmartDashboard.putData("ChassisTurn", new ChassisTurn(90.0));
    }
    
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
    public Joystick getDriverJoystick() {
        return driverJoystick;
    }
    public Joystick getShooterJoystick() {
        return shooterJoystick;
    }
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
    public double getDriverLeftSpeed() {
        return -driverJoystick.getY();
    }
    public double getDriverRightSpeed() {
        return -driverJoystick.getThrottle();
    }
    public double getShooterTiltSpeed() {
        return -shooterJoystick.getY();
    }
    public double getShooterWheelSpeed() {
        return -shooterJoystick.getThrottle();
    }
}
