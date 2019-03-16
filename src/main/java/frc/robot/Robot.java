/*                                                                                              
      @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@            @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@      
      @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@            @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@      
     @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@            @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@      
     @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@           @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@      
     @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@           @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@      
     @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@          @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@      
     @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@          @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@      
     @@@@@@@@@@@@@@@           @@@@@@@@@@@@@          @@@@@@@@@@@@@            @@@@@@@@@@@@@@@     
     @@@@@@@@@@@@@@@            @@@@@@@@@@@@          @@@@@@@@@@@@@            @@@@@@@@@@@@@@@     
    @@@@@@@@@@@@@@@@            @@@@@@@@@@@@@         @@@@@@@@@@@@@            @@@@@@@@@@@@@@@     
    @@@@@@@@@@@@@@@@            @@@@@@@@@@@@@         @@@@@@@@@@@@@            @@@@@@@@@@@@@@@     
    @@@@@@@@@@@@@@@@            @@@@@@@@@@@@@        @@@@@@@@@@@@@@            @@@@@@@@@@@@@@@     
    @@@@@@@@@@@@@@@@            @@@@@@@@@@@@@        @@@@@@@@@@@@@@            @@@@@@@@@@@@@@@     
    @@@@@@@@@@@@@@@@            @@@@@@@@@@@@@        @@@@@@@@@@@@@@            @@@@@@@@@@@@@@@     
    @@@@@@@@@@@@@@@@@@@@@@@@    @@@@@@@@@@@@@        @@@@@@@@@@@@@    @@@@@@@@@@@@@@@@@@@@@@@@@    
    @@@@@@@@@@@@@@@@@@@@@@@@    @@@@@@@@@@@@@@       @@@@@@@@@@@@@    @@@@@@@@@@@@@@@@@@@@@@@@@    
   @@@@@@@@@@@@@@@@@@@@@@@@@    @@@@@@@@@@@@@@       @@@@@@@@@@@@@    @@@@@@@@@@@@@@@@@@@@@@@@@    
   @@@@@@@@@@@@@@@@@@@@@@@@@    @@@@@@@@@@@@@@       @@@@@@@@@@@@@    @@@@@@@@@@@@@@@@@@@@@@@@@    
    @@@@@@@@@@@@@@@@@@@@@@@@    @@@@@@@@@@@@@@      @@@@@@@@@@@@@@    @@@@@@@@@@@@@@@@@@@@@@@@@    
         @@@@@@@@@@@@@@@@@@@@   @@@@@@@@@@@@@@      @@@@@@@@@@@@@@    @@@@@@@@@@@@@@@@@@@          
             @@@@@@@@@@@@@@@    @@@@@@@@@@@@@@      @@@@@@@@@@@@@@    @@@@@@@@@@@@@@@              
            @@@@@@@@@@@@@@@     @@@@@@@@@@@@@@      @@@@@@@@@@@@@@     @@@@@@@@@@@@@@@             
           @@@@@@@@@@@@@@@@     @@@@@@@@@@@@@@@     @@@@@@@@@@@@@@      @@@@@@@@@@@@@@@            
           @@@@@@@@@@@@@@@      @@@@@@@@@@@@@@@     @@@@@@@@@@@@@@       @@@@@@@@@@@@@@@           
          @@@@@@@@@@@@@@@       @@@@@@@@@@@@@@@     @@@@@@@@@@@@@@       @@@@@@@@@@@@@@@@          
         @@@@@@@@@@@@@@@        @@@@@@@@@@@@@@@    @@@@@@@@@@@@@@@        @@@@@@@@@@@@@@@          
        @@@@@@@@@@@@@@@@         @@@@@@@@@@@@@@    @@@@@@@@@@@@@@@         @@@@@@@@@@@@@@@         
       @@@@@@@@@@@@@@@@          @@@@@@@@@@@@@@    @@@@@@@@@@@@@@@          @@@@@@@@@@@@@@@        
      @@@@@@@@@@@@@@@@           @@@@@@@@@@@@@@    @@@@@@@@@@@@@@@          @@@@@@@@@@@@@@@@       
     @@@@@@@@@@@@@@@@            @@@@@@@@@@@@@@    @@@@@@@@@@@@@@@           @@@@@@@@@@@@@@@@      
     @@@@@@@@@@@@@@@             @@@@@@@@@@@@@@@   @@@@@@@@@@@@@@@            @@@@@@@@@@@@@@@@     
    @@@@@@@@@@@@@@@@             @@@@@@@@@@@@@@@   @@@@@@@@@@@@@@@             @@@@@@@@@@@@@@@@    
   @@@@@@@@@@@@@@@@              @@@@@@@@@@@@@@@  @@@@@@@@@@@@@@@              @@@@@@@@@@@@@@@@    
  @@@@@@@@@@@@@@@@               @@@@@@@@@@@@@@@  @@@@@@@@@@@@@@@               @@@@@@@@@@@@@@@@   
 @@@@@@@@@@@@@@@@                @@@@@@@@@@@@@@@  @@@@@@@@@@@@@@@                @@@@@@@@@@@@@@@@  
                                                                                                   
*/

 /*------------------------------------------------- Main Robot Class -----
  |  Name: Robot.java 
  |
  |  Purpose: This is what runs on the robot and calls the subsystems as needed. 
  |
  |  Returns: This class returns values to the smartdashboard in the update subsystem method.
  *-------------------------------------------------------------------*/


package frc.robot;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.LED;
import frc.robot.auto.AutoExecuter;

import edu.wpi.first.wpilibj.IterativeRobot;


public class Robot extends IterativeRobot  {

  //Initialize main parts of the robot
  Setup mSetup;
  Drivetrain mDrivetrain;
  Elevator mElevator;
  Intake mIntake;
  LED mLED;
  Climber mClimber;

  AutoExecuter mAutoExecuter = null;
  
  public void updateAllSubsystems(){
		
		mDrivetrain.updateSubsystem();
	  mIntake.updateSubsystem();
    mElevator.updateSubsystem();
    mLED.updateSubsystem();
  }
  
  public void stopAllSubsystems(){
    
		mDrivetrain.stop();
		mDrivetrain.lowGear();
		mIntake.stop();
    mElevator.stop();
    mLED.Clear();
  }
  
/*------------------------------------------------- Manual Controls -----
  |  Name: manual 
  |
  |  Purpose: These are the manual controls for the robot. Setup.java contains the control mappings.
  |
  *-------------------------------------------------------------------*/
 
public void manual()
{
    
    //Controls

    //Drive train Gears 
		if(mSetup.getDriverHighGearButton()) {
			mDrivetrain.highGear();
		}
		if(mSetup.getDriverLowGearButton()) {
			mDrivetrain.lowGear();
    }
    
    //Dpad and Tank Driver Controls
    double dpadSpeed = .25;

    if(mSetup.getDriverFastSpeed())
    {
      dpadSpeed = dpadSpeed * 2;
    }
    else
    {
      dpadSpeed = .25;
    }
    
    if(mSetup.getDriverPov() == 0 || mSetup.getDriverPov() == 45 || mSetup.getDriverPov() == 315){
      mDrivetrain.setTankDriveSpeed(-dpadSpeed, -dpadSpeed);
    }
    else if(mSetup.getDriverPov() == 180 || mSetup.getDriverPov() == 225 || mSetup.getDriverPov() == 135){
      mDrivetrain.setTankDriveSpeed(dpadSpeed, dpadSpeed);
    }
    else if(mSetup.getDriverPov() == 90 || mSetup.getDriverPov() == 45 || mSetup.getDriverPov() == 135){
      mDrivetrain.setTankDriveSpeed(dpadSpeed, -dpadSpeed);
    }
    else if(mSetup.getDriverPov() == 270 || mSetup.getDriverPov() == 225 || mSetup.getDriverPov() == 315){
      mDrivetrain.setTankDriveSpeed(-dpadSpeed, dpadSpeed);
    }
    else
    {
      mDrivetrain.setTankDriveSpeed(mSetup.getDriverLeftY(), mSetup.getDriverRightY());
    }


    //Climber
    if(mSetup.getDriverClimbButton()){
      mClimber.Climb();
    }
    else if (mSetup.getDriverFallButton()){
      mClimber.Fall();
    }
    else {
      mClimber.stop();
    }

    //Cargo Intake
		if(mSetup.getSecondaryCargoIntakeButton()) {
      mIntake.intakeCargo();
     } 
   else if(mSetup.getSecondaryCargoOuttakeButton())
    { 
			mIntake.outtakeCargo();
		}
		else {
			mIntake.stopCargoIntakeMotor();
    }
    
     //Hatch Intake
		if(mSetup.getMrHuckSuckButton()) {
      mIntake.IntakeHatch();
     } 
    else if(mSetup.getMrHuckStopButton())
    { 
			mIntake.stopSucking();
		}
	
    //Intake Rotary
    if(mSetup.getSecondaryIntakeRotaryCargoButton()) {
      mIntake.SetIntakeRotaryCargoState();
     } 

     if(mSetup.getSecondaryIntakeRotaryHatchButton()) {
      mIntake.SetIntakeRotaryHatchState();
     } 

		//Elevator Analog
		mElevator.setElevatorSpeedAnalog(mSetup.getSecondaryElevatorAnalog());

    //Elevator Positions
    if(mSetup.getSecondaryElevatorHighButton()) {
      mElevator.setElevatorPosition("HATCH", "HIGH");
     } 

     if(mSetup.getSecondaryElevatorMiddleButton()) {
       mElevator.setElevatorPosition("HATCH","MIDDLE");
      } 

    if(mSetup.getSecondaryElevatorLowButton()) {
      mElevator.setElevatorPosition("HATCH","LOW");
     } 

     //LED Lights
     if(mSetup.GetLEDClearButton()){
      mLED.Clear();
    }
     if(mSetup.GetLEDRainbowButton()){
       mLED.Rainbow();
     }
     if(mSetup.GetLEDCargoButton()){
      mLED.Cargo();
    }
    if(mSetup.GetLEDRedFlashyThingButton()){
      mLED.RedFlashyThing();
    }
    if(mSetup.GetLEDTeamBlueButton()){
      mLED.TeamBlue();
    }
    if(mSetup.GetLEDTeamRedButton()){
      mLED.TeamRed();
    }
    if(mSetup.GetLEDHatchButton()){
      mLED.Hatch();
    }
    if(mSetup.GetLEDElevatorMaxHeightyButton()){
      mLED.ElevatorMaxHeighty();
    }
    if(mSetup.GetLEDNoButton()){
      mLED.Clear();
    }

    updateAllSubsystems();

}

/*------------------------------------------------- Robot Methods -----
  |  Name: robotInit, autonomousInit, 
          autonomousPeriodic, disabledInit, 
          disabledPeriodic, teleopInit, 
          teleopPeriodic
  |
  |  Purpose: These are the main robot methods that are ran by either the DriverStation or FMS System.
              The Init means initialized. This runs when either Tele-Op or Automous is started. 
              The periodic method for both Tele-Op and Auto runs continuously in the background.
  |
  |  Returns: The Methods wil print when the Robot initializes differnt states. 
  *-------------------------------------------------------------------*/

  @Override
  public void robotInit() {
    System.out.println("Robot Init");
    mSetup = Setup.getInstance();
    mDrivetrain = Drivetrain.getInstance();
    mIntake = Intake.getInstance();
    mLED = LED.getInstance();
    mElevator = Elevator.getInstance();
    stopAllSubsystems();
  }

  @Override
	public void autonomousInit() {
    System.out.println("Auto Init");
		stopAllSubsystems();
		updateAllSubsystems();
	}
	
	@Override
	public void autonomousPeriodic() {
    manual();
    updateAllSubsystems();
	}

  
  @Override
	public void disabledInit(){
      System.out.println("Disabled Init");
      mDrivetrain.highGear();
		  stopAllSubsystems();
		  updateAllSubsystems();
  }
  
  @Override
	public void disabledPeriodic() {

	 //LED Lights Controls
   if(mSetup.GetLEDClearButton()){
    mLED.Clear();
  }
   if(mSetup.GetLEDRainbowButton()){
     mLED.Rainbow();
   }
   if(mSetup.GetLEDCargoButton()){
    mLED.Cargo();
  }
  if(mSetup.GetLEDRedFlashyThingButton()){
    mLED.RedFlashyThing();
  }
  if(mSetup.GetLEDTeamBlueButton()){
    mLED.TeamBlue();
  }
  if(mSetup.GetLEDTeamRedButton()){
    mLED.TeamRed();
  }
  if(mSetup.GetLEDHatchButton()){
    mLED.Hatch();
  }
  if(mSetup.GetLEDElevatorMaxHeightyButton()){
    mLED.ElevatorMaxHeighty();
  }
  if(mSetup.GetLEDNoButton()){
    mLED.Clear();
  }
  }

  @Override
	public void teleopInit(){
    System.out.println("Tele Init");
		stopAllSubsystems();
    mDrivetrain.lowGear();
    updateAllSubsystems();
  }
  
  @Override
  public void teleopPeriodic() {
    manual();
    updateAllSubsystems();
  }
 
}
