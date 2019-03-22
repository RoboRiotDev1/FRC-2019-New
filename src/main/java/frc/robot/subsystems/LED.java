package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Setup;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Timer;


/*------------------------------------------------- LED -----
  |  Name: LED.java
  |
  |  Purpose:  This is the most important subsystem that sends values to an ardunio.
  |            The ardunio then changes the led lights based singals recived 
  |
  |  Varibles:
  |             DIO
  | 
  |  
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

  
public class LED extends Subsystem
{

    static LED mInstance = new LED();

    public static LED getInstance() {
        return mInstance;
    }

    DigitalOutput mLEDPort1;
    DigitalOutput mLEDPort2;
    DigitalOutput mLEDPort3;
    Setup mSetup = new Setup();

    public LED()
    {
        mLEDPort1 = new DigitalOutput(Setup.kLED1);
        mLEDPort2 = new DigitalOutput(Setup.kLED2);
        mLEDPort3 = new DigitalOutput(Setup.kLED3);
    }   

    @Override
    public void updateSubsystem()
    {

    }
    @Override
    public void outputToSmartDashboard()
    {
        
    }
    @Override
    public void stop()
    {

    }

    public void Clear()
    {
        //000
        mLEDPort1.set(true);
        mLEDPort2.set(true);
        mLEDPort3.set(true);
    }
    public void Rainbow()
    {
        //001
        mLEDPort1.set(true);
        mLEDPort2.set(true);
        mLEDPort3.set(false);
    }

    public void Cargo()
    {
        //010
        mLEDPort1.set(true);
        mLEDPort2.set(false);
        mLEDPort3.set(true);
    }

    public void RedFlashyThing()
    {
        //011
        mLEDPort1.set(true);
        mLEDPort2.set(false);
        mLEDPort3.set(false);
    }
    public void TeamBlue()
    {
        //100
        mLEDPort1.set(false);
        mLEDPort2.set(true);
        mLEDPort3.set(true);
    }
    public void TeamRed()
    {
        //101
        mLEDPort1.set(false);
        mLEDPort2.set(true);
        mLEDPort3.set(false);
    }
    public void Hatch()
    {
        //110
        mLEDPort1.set(false);
        mLEDPort2.set(false);
        mLEDPort3.set(true);
    }
    public void ElevatorMaxHeighty()
    {
        //111
        mLEDPort1.set(false);
        mLEDPort2.set(false);
        mLEDPort3.set(false);
    }


}