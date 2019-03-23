package frc.robot.auto.actions;
import frc.robot.Setup;

public class AutoLEDAction implements Action  
{
    Setup mSetup = Setup.getInstance();
    private boolean mAutoLEDControl;


    public AutoLEDAction(boolean LEDBool) {
        mAutoLEDControl = LEDBool;
    
    }

    @Override
    public void start()
    {
        
        mSetup.AutoLEDControl = mAutoLEDControl;

    }
    
    public void update()
    {

        
    }
    
    public boolean isFinished()
    {
        return true;
    }
    
    public void done()
    {

    }

}