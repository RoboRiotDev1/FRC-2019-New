package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.DigitalInput;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class Setup
{

    private static Setup mInstance = new Setup();

    public static Setup getInstance() {
        return mInstance;
    }
    
public Setup()
{
this.ControlBoard();

}

//----------------------------------------------------------------------------Controls-----------------------------------------------------------------------------------------//
 
/*------------------------------------------------- Controls -----
  |  Name: Various Methods for returning buttons 
  |
  |  Purpose:  This is the main control mapping for each one of the buttons. 
  |
  |  Returns: This is what returns the buttons from the controllers to a public area 
  |           for the Robot.java file to read for maunal control 
  |           (Note you can use an Axis as a button with a > .2 deadzone.)
  *-------------------------------------------------------------------*/

  /*------------------------------------------------- Xbox Controller Key -----
  |  
  |   A = 1  
  |   B = 2 
  |   X = 3
  |   Y = 4
  |      
  |   Left Joystick X = Axis 0 
  |   Left Joystick Y = Axis 1
  |   Left Joystick Button = 9
  |
  |   Right Joystick X = Axis 4 
  |   Right Joystick Y = Axis 5
  |   Left Joystick Button = 10
  |   
  |   Left Trigger = Axis 2
  |   Right Trigger = Axis 3
  |
  |   Left Button = 5
  |   Right Button = 6
  |
  |   Start = 7
  |   Select = 8
  |
  |   D-Pad = POV
  |
  *-------------------------------------------------------------------*/


    //Creates Joystick Objects
    Joystick mDriverStick;
    Joystick mSecondaryDriverStick;
    Joystick mSwitchboard;
 

    //Initialize Joystick Objects
    void ControlBoard() {
    	mDriverStick = new Joystick(0);
      mSecondaryDriverStick = new Joystick(1);
      mSwitchboard = new Joystick(2);
    }

     //DRIVER CONTROLLER
    
    //Drive Controls
    public boolean getDriverFastSpeed()
    {
      return mDriverStick.getRawButton(1);
    }
    
    public double getDriverLeftX(){
    	return mDriverStick.getRawAxis(0);
    }
    
    public double getDriverLeftY(){
    	return -mDriverStick.getRawAxis(5)*-1;
    }
    
    public double getDriverRightX(){
    	return mDriverStick.getRawAxis(4);
    }
    
    public double getDriverRightY(){
    	return -mDriverStick.getRawAxis(1)*-1;
    }
    
    public int getDriverPov(){
    	return mDriverStick.getPOV(0);
    }

    
    //Driver Gear Controls
    public boolean getDriverHighGearButton(){
    	return mDriverStick.getRawButton(6);
    }
    
    public boolean getDriverLowGearButton(){
        return mDriverStick.getRawButton(5);
    }

    //Climber 
    public boolean getDriverClimbButton()
    {
      return mDriverStick.getRawAxis(3) > .2;
    }

    public boolean getDriverFallButton()
    {
      return mDriverStick.getRawAxis(2) > .2;
    }

    public boolean getDriverClimberBackRetractButton()
    {
      return mDriverStick.getRawButton(7);
    }

    public boolean getDriverClimberFrontRetractButton()
    {
      return mDriverStick.getRawButton(8);
    }
    
    //SECONDARY CONTROLLER

    //Cargo Intake 
    public boolean getSecondaryCargoIntakeButton(){
    	return mSecondaryDriverStick.getRawAxis(2) > .2;
    }
    
    public boolean getSecondaryCargoOuttakeButton()
    {
    	return mSecondaryDriverStick.getRawAxis(3) > .2;
    }
    
    //Mr Huck
    public boolean getMrHuckSuckButton(){
    	return mSecondaryDriverStick.getRawButton(5);
    }
    
    public boolean getMrHuckStopButton(){
      
      return mSecondaryDriverStick.getPOV() == 90;
    }

    public boolean getVaccuumReleaseButton()
    {
      return mSecondaryDriverStick.getRawButton(6);
    }

    //Intake Rotary
    public boolean getSecondaryIntakeRotaryCargoButton(){
    	return  mSecondaryDriverStick.getPOV()>90 && mSecondaryDriverStick.getPOV()<270;
    }
    
    public boolean getSecondaryIntakeRotaryHatchButton(){
    	return mSecondaryDriverStick.getPOV()==0 || mSecondaryDriverStick.getPOV()==315 || mSecondaryDriverStick.getPOV()==45;
    }
    public double getSecondaryIntakeRotaryAnalog()
    {
      return mSecondaryDriverStick.getRawAxis(1) * -1;
    }

    //Elevator 
    public double getSecondaryElevatorAnalog(){
    	return mSecondaryDriverStick.getRawAxis(5)*-1;
	}

	public boolean getSecondaryElevatorHighButton(){
    	return mSecondaryDriverStick.getRawButton(4);
	}
	
	public boolean getSecondaryElevatorMiddleButton(){
    	return mSecondaryDriverStick.getRawButton(2);
	}
	
	public boolean getSecondaryElevatorLowButton(){
    	return mSecondaryDriverStick.getRawButton(1);
  }

    public boolean getSecondaryAutoStopButton(){
      return mSecondaryDriverStick.getRawButton(3);
  }

    //LED Controls 

    //Switch Board
   public boolean GetLEDClearButton(){
    return mSwitchboard.getRawButton(1);
  }
  public boolean GetLEDRainbowButton(){
    return mSwitchboard.getRawButton(2);
  }
  public boolean GetLEDCargoButton(){
    return mSwitchboard.getRawButton(3);
  }
  public boolean GetLEDRedFlashyThingButton(){
    return mSwitchboard.getRawButton(4);
  }
  public boolean GetLEDTeamBlueButton(){
    return mSwitchboard.getRawButton(5);
  }
  public boolean GetLEDTeamRedButton(){
    return mSwitchboard.getRawButton(6);
  }
  public boolean GetLEDHatchButton(){
    return mSwitchboard.getRawButton(7);
  }
  public boolean GetLEDElevatorMaxHeightyButton(){
    return mSwitchboard.getRawButton(8);
  }

  public boolean GetLEDNoButton(){
    if ( (GetLEDClearButton() == false) && (GetLEDRainbowButton() == false) && (GetLEDCargoButton() == false) && (GetLEDCargoButton() == false) && (GetLEDRedFlashyThingButton() == false) && (GetLEDTeamBlueButton() == false) && (GetLEDTeamRedButton() == false) && (GetLEDHatchButton() == false) && (GetLEDElevatorMaxHeightyButton() == false))
    {
      return true;
    }
    else
    {
      return false;
    }
  }


//----------------------------------------------------------------------------Hardware Map------------------------------------------------------------------------------------//

//Speed Controllers
public VictorSPX mIntakeHardware;
public VictorSPX mMrHuckHardware;

public TalonSRX mSpoolHardware;
public TalonSRX mIntakeRotaryHardware;

//Pneumatics
public Compressor mCompressorHardware;
public Solenoid mLeftShifterHardware;
public Solenoid mRightShifterHardware;

//Sensos
public ADXRS450_Gyro mGyro;	
public DigitalInput mElevatorTopProxHardware;
public DigitalInput mElevatorBottomProxHardware;

void HardwareMap() {
    //test
    try
    {
        //Speed Controllers
        mIntakeHardware = new VictorSPX(Setup.kIntakeId);
        mMrHuckHardware = new VictorSPX(Setup.kMrHuckId);
        
        mSpoolHardware = new TalonSRX(Setup.kSpoolId);
        mIntakeRotaryHardware = new TalonSRX(Setup.kIntakeRotaryId);

        //Pneumatics
        mLeftShifterHardware = new Solenoid(Setup.kShifterSolenoidId);
        mRightShifterHardware = new Solenoid(Setup.kShifterSolenoidId);
        mCompressorHardware = new Compressor(0);

        //Sensors
        mGyro = new ADXRS450_Gyro();
        mGyro.calibrate();

    }
    catch(Exception e)
    {
        
    }
}


//-----------------------------------------------------------------------------------Not a Constant----------------------------------------------------------------------------------//
public boolean AutoRunning = false;
public boolean AutoLEDControl = false;
//-----------------------------------------------------------------------------------Constants----------------------------------------------------------------------------------//

/*------------------------------------------------- Port Assignments -----
  |  Name: Port Assignments
  |
  |  Purpose: This is a public area for constant Port Assignments it is used 
  |            for declaring motors in different Subsystems.  
  |
  *-------------------------------------------------------------------*/


//Port Assignments

//CAN

//Drive Train )
public static int kLeftFrontMotorId = 15;
public static int kLeftRearMotorId = 1;
public static int kRightFrontMotorId = 2;
public static int kRightRearMotorId = 3;

//Intake
public static int kIntakeId = 5;
public static int kIntakeRotaryId = 6;
public static int kMrHuckId = 7;
public static int kMrHuckJrId = 8;

//Elevator
public static int kSpoolId = 9;
public static int kSpoolId1 = 10;

//Climber
public static int kClimberLeftId = 11;
public static int kClimberRightId = 12;
public static int kClimberBackId = 13;
//Note Make PDP Board 14

//SOLENOIDS (0-7)

//Shifter
public static int kShifterSolenoidId = 1;


//ANALOG (0-4) 

//Ardunio
public static int kArdunio = 1;


//DIO
public static int kElevatorBottomProx = 0;
public static int kElevatorTopProx = 1;
public static int kIntakeCargoLimit = 2;
public static int kIntakeHatchLimit = 3;
public static int kElevatorLaser = 4;
public static int kClimber1Prox = 5;
public static int kClimber2Prox = 6;
public static int kClimber3Prox = 7;
public static int kLED1 = 8;
public static int kLED2 = 9;
public static int kLED3 = 10;
}