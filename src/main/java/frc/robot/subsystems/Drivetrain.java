package frc.robot.subsystems;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Solenoid;
import frc.robot.Setup;

/*------------------------------------------------- Drivetrain -----
  |  Name: Drivetrain.java
  |
  |  Purpose:  This subsystem controls the drive motors of the robot.
  |            The subsystem also controls the drive gear. 
  |
  |  Varibles:
  | 
  |  Drive Gear ENUM: This is used to output to the smart dashboard what state 
  |                   the drive gear is in for debugging.
  |
  |  Methods: 
  |
  |   setTankDriveSpeed: This sets the speed of the drive motors given the parameters
  |						 for left and Right speeds. 
  |   setLowGear: This sets the drive gear to high.
  |   setHighGear: This sets the drive gear to low.
  |   stop: This stops the Subsystem
  |
  |  Returns:
  |
  |  This returns the Drive Gear ENUM to the Smartdashboard.
  |  This also returns the speeds for each motor to the Smartdashboard.
  |  
  *-------------------------------------------------------------------*/


public class Drivetrain extends Subsystem {

    static Drivetrain mInstance = new Drivetrain();

    public static Drivetrain getInstance() {
        return mInstance;
	}
	
	
    //Shifter
    Solenoid mSolenoid;
   
    //Drive Motors
    CANSparkMax mRightFrontDrive;
    CANSparkMax mRightRearDrive;
    CANSparkMax mLeftFrontDrive;
    CANSparkMax mLeftRearDrive;

    public Drivetrain(){
	
		mSolenoid = new Solenoid(Setup.kShifterSolenoidId);
		
    	mLeftFrontDrive = new CANSparkMax(Setup.kLeftFrontMotorId, MotorType.kBrushless);
    	mLeftFrontDrive.set(0);
		mLeftFrontDrive.setInverted(false);
		mLeftFrontDrive.setOpenLoopRampRate(.2);
    	
        mRightFrontDrive = new CANSparkMax(Setup.kRightFrontMotorId, MotorType.kBrushless);
    	mRightFrontDrive.set(0);
		mRightFrontDrive.setInverted(true);
		mRightFrontDrive.setOpenLoopRampRate(.2);
    	
    	mLeftRearDrive = new CANSparkMax(Setup.kLeftRearMotorId, MotorType.kBrushless);
    	mLeftRearDrive.set(0);
    	mLeftRearDrive.setInverted(false);
		mLeftRearDrive.setOpenLoopRampRate(.2);
		
    	mRightRearDrive = new CANSparkMax(Setup.kRightRearMotorId, MotorType.kBrushless);
    	mRightRearDrive.set(0);
		mRightRearDrive.setInverted(true);
		mRightRearDrive.setOpenLoopRampRate(.2);
	
		System.out.println("Drivetrain Done Initializing.");

		}
    
    private DriveGear mDriveGear;
    private double mLeftSpeed;
    private double mRightSpeed;
    private double mMoveSpeed;
    private double mRotateSpeed;
    
    public enum DriveGear {
    	LOW, HIGH
    }
    
    public DriveGear getDriveGear(){
    	return mDriveGear;
    }
    
    public void setTankDriveSpeed(double left, double right){
    	//System.out.println("Left speed = " + left + " right speed = " + right);
    	mLeftSpeed = -left;
    	mRightSpeed = right;
    	mLeftFrontDrive.set(left);
    	mLeftRearDrive.set(left);
    	mRightFrontDrive.set(right);
    	mRightRearDrive.set(right);
    }
    

    //Stop
    @Override
    public void stop(){
    	mLeftFrontDrive.set(0);
    	mLeftRearDrive.set(0);
    	mRightFrontDrive.set(0);
    	mRightRearDrive.set(0);
    }
    

    //Gears
    private void setLowGear() {
    	
    	mSolenoid.set(false);
    	
    }
	
    private void setHighGear() {

    	mSolenoid.set(true);
    	
    }
    
    
    //Update
	@Override
	public void updateSubsystem() {
        
		switch(mDriveGear){
		case HIGH:
			setHighGear();
			break;
		case LOW:
			setLowGear();
			break;
		default:
			highGear();
			break;
		}
		
		outputToSmartDashboard();
		
	}
	
	public void highGear(){
		mDriveGear = DriveGear.HIGH;
	}
	
	public void lowGear(){
		mDriveGear = DriveGear.LOW;
	}

	@Override
	public void outputToSmartDashboard() {
		SmartDashboard.putNumber("DriveTrain_LeftMotorSpeeds", mLeftSpeed);
		SmartDashboard.putNumber("DriveTrain_RightMotorSpeeds", mRightSpeed);
		SmartDashboard.putNumber("DriveTrain_MoveValue", mMoveSpeed);
		SmartDashboard.putNumber("DriveTrain_RotateValue", mRotateSpeed);
		SmartDashboard.putString("Drive_Gear", mDriveGear.toString());
	}


}
