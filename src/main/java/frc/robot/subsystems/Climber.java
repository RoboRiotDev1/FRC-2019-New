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
    VictorSPX mClimber1;
    VictorSPX mClimber2;
    VictorSPX mClimber3;

    //Prox Sensors
    DigitalInput mClimber1Prox;
    DigitalInput mClimber2Prox;
    DigitalInput mClimber3Prox;

    public Climber(){
	
    	mClimber1 = new VictorSPX(Setup.kClimber1Id);
        mClimber1.setInverted(false);
        
        mClimber2 = new VictorSPX(Setup.kClimber2Id);
        mClimber2.setInverted(false);
        
        mClimber3 = new VictorSPX(Setup.kClimber3Id);
        mClimber3.setInverted(false);
        
        mClimber1Prox = new DigitalInput(Setup.kClimber1Prox);
        mClimber2Prox = new DigitalInput(Setup.kClimber2Prox);
        mClimber3Prox = new DigitalInput(Setup.kClimber3Prox);
        
        System.out.println("Climber Done Initializing.");

		}
    
    private ClimberState mClimberState;
    
    public enum ClimberState {
    	Nothing, Climbing, Falling
    }
    
    public ClimberState getClimberStates(){
    	return mClimberState;
    }

    public void Climb(){

        mClimberState = ClimberState.Climbing;
        
        if ((mClimber1Prox.get() == false) && (mClimber2Prox.get() == false) && (mClimber3Prox.get() == false))
        {
            mClimber1.set(ControlMode.PercentOutput, 1);
            mClimber2.set(ControlMode.PercentOutput, 1);
            mClimber3.set(ControlMode.PercentOutput, 1);
        }
        else
        {
            mClimber1.set(ControlMode.PercentOutput, 0);
            mClimber2.set(ControlMode.PercentOutput, 0);
            mClimber3.set(ControlMode.PercentOutput, 0);
        }
        
    	
    }

    public void Fall(){
        
        mClimberState = ClimberState.Falling;
        
        mClimber1.set(ControlMode.PercentOutput, -1);
        mClimber2.set(ControlMode.PercentOutput, -1);
        mClimber3.set(ControlMode.PercentOutput, -1);
    }
    

    //Stop
    @Override
    public void stop(){

    	mClimberState = ClimberState.Nothing;
        
        mClimber1.set(ControlMode.PercentOutput, 0);
        mClimber2.set(ControlMode.PercentOutput, 0);
        mClimber3.set(ControlMode.PercentOutput, 0);
    }
    
    //Update
	@Override
	public void updateSubsystem() {
        
		outputToSmartDashboard();
		
	}
	
	@Override
	public void outputToSmartDashboard() {
		SmartDashboard.putString("Climber State", mClimberState.toString());
	}


}
