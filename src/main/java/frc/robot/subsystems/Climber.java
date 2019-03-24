package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Setup;

/*------------------------------------------------- Climber -----
  |  Name: Climber.java
  |
  |  Purpose:  This subsystem pushes stilts down using the three climber motors.
  |            The wheels on the botton of the stilts use the drive train to run. 
  |
  |  Varibles:
  | 
  |  Climber State ENUM: This is used to output to the smart dashboard what state 
  |                      the climber is in for debugging.
  |
  |  Methods: 
  |
  |   Climb: This pushes the stilts down to climb the HAB and Sets the Climber
  |          State ENUM to Climb
  |   Fall: This retracts the stilts to descend and Sets the Climber
  |         State ENUM to Fall
  |   Stop: This stops the climber with brakes and Sets the Climber
  |         State ENUM to Nothing
  |
  |  Returns:
  |
  |  This returns the Climber State ENUM to the Smartdashboard.
  |  
  *-------------------------------------------------------------------*/

public class Climber extends Subsystem {

    static Climber mInstance = new Climber();

    public static Climber getInstance() {
        return mInstance;
	}
	
    //Drive Motors
    VictorSPX mClimberLeft;
    VictorSPX mClimberRight;
    VictorSPX mClimberBack;

    //Prox Sensors
    DigitalInput mClimberLeftLimitSwitch;
    DigitalInput mClimberRightLimitSwitch;
    DigitalInput mClimberBackLimitSwitch;

    //Constants
    public double ClimberSpeed = Setup.ClimberSpeed;
    Setup mSetup = new Setup();
    public Climber(){
	
    	mClimberLeft = new VictorSPX(Setup.kClimberLeftId);
        mClimberLeft.setInverted(false);
        
        mClimberRight = new VictorSPX(Setup.kClimberRightId);
        mClimberRight.setInverted(false);
        
        mClimberBack = new VictorSPX(Setup.kClimberBackId);
        mClimberBack.setInverted(false);
        
        mClimberLeftLimitSwitch = new DigitalInput(Setup.kLeftClimberLimitSwitch);
        mClimberRightLimitSwitch = new DigitalInput(Setup.kRightClimberLimitSwitch);
        mClimberBackLimitSwitch = new DigitalInput(Setup.kBackClimberLimitSwitch);
        
        System.out.println("Climber Done Initializing.");

		}
    
    public enum ClimberState {
        Nothing, 
        Climbing, 
        Falling, 
        RetractBack, 
        RetrackFront
    }

    private ClimberState  mClimberState = ClimberState.Nothing;
    
    public ClimberState getClimberStates(){
    	return mClimberState;
    }

    public void Climb(){

        mClimberState = ClimberState.Climbing;
        
        if (mClimberLeftLimitSwitch.get() == true)
        {
            mClimberLeft.set(ControlMode.PercentOutput, -ClimberSpeed);
        }
        else if(mClimberLeftLimitSwitch.get() == false)
        {
            mClimberLeft.set(ControlMode.PercentOutput, 0);
        }

         if (mClimberRightLimitSwitch.get() == true)
         {
             mClimberRight.set(ControlMode.PercentOutput, ClimberSpeed);
         }
         else if(mClimberRightLimitSwitch.get() == false)
         {
             mClimberRight.set(ControlMode.PercentOutput, 0);
         }

         if (mClimberBackLimitSwitch.get() == true)
         {
             mClimberBack.set(ControlMode.PercentOutput, -1);
         }
         else if(mClimberBackLimitSwitch.get() == false)
         {
             mClimberBack.set(ControlMode.PercentOutput, 0);
         }
        
    }

    public void Fall(){
        
        mClimberState = ClimberState.Falling;
        
        mClimberLeft.set(ControlMode.PercentOutput, ClimberSpeed);
        mClimberRight.set(ControlMode.PercentOutput, -ClimberSpeed);
        mClimberBack.set(ControlMode.PercentOutput, -ClimberSpeed);
    }

    public void RetractBack()
    {
        mClimberState = ClimberState.RetractBack;
        mClimberBack.set(ControlMode.PercentOutput, .85);
    }

    public void RetractFront()
    {
        mClimberState = ClimberState.RetrackFront;
        mClimberLeft.set(ControlMode.PercentOutput, ClimberSpeed);
        mClimberRight.set(ControlMode.PercentOutput, -ClimberSpeed);
    }
    

    //Stop
    @Override
    public void stop(){

    	mClimberState = ClimberState.Nothing;
        
        mClimberLeft.set(ControlMode.PercentOutput, 0);
        mClimberRight.set(ControlMode.PercentOutput, 0);
        mClimberBack.set(ControlMode.PercentOutput, 0);
    }
    
    //Update
	@Override
	public void updateSubsystem() {
        System.out.println("Right Limit " +mClimberLeftLimitSwitch.get());
        System.out.println("Left Limit " + mClimberLeftLimitSwitch.get());
        System.out.println("Back Limit " + mClimberBackLimitSwitch.get());
		outputToSmartDashboard();
		
	}
	
	@Override
	public void outputToSmartDashboard() {
		SmartDashboard.putString("Climber State", mClimberState.toString());
	}


}
