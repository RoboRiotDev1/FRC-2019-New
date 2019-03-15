package frc.robot.auto;
import frc.robot.auto.modes.DeliverHatchMiddlePosition;
public class AutoExecuter {

	private AutoModeBase mAutoMode;
    private Thread m_thread = null;


    public void setAutoRoutine(AutoModeBase newAutoMode) {

        mAutoMode = newAutoMode;

    }


    public void start() {

        if (m_thread == null) {

            m_thread = new Thread() {

                public void run() {

                    if (mAutoMode != null) {

                        mAutoMode.run();

                    }

                }

            };

            m_thread.start();

        }

    }

    

    public void stop() {

        if (mAutoMode != null) {

            mAutoMode.stop();

        }

        m_thread = null;

    }

}





//This is extra code to run auto in teleop

/*

public void auto(String automode)
 {

  if (mSetup.AutoRunning == false)
  {
    mSetup.AutoRunning = true;
    
    if (mAutoExecuter != null) {

      mAutoExecuter.stop();

    }

    mAutoExecuter = new AutoExecuter();

   switch(automode) {

     case "DeliverHatchHighPosition":
      mAutoExecuter.setAutoRoutine(new DeliverHatchHighPosition());
      mAutoExecuter.start();
 			break;
     case "DeliverHatchMiddlePosition":
      mAutoExecuter.setAutoRoutine(new DeliverHatchMiddlePosition());
      mAutoExecuter.start();
 		  break;
     case "DeliverHatchLowPosition":
     mAutoExecuter.setAutoRoutine(new DeliverHatchLowPosition());
     mAutoExecuter.start();
       break;
     default:
 			break;
     }
    }
}

This was in tele op perodic

    // if (mSetup.getSecondaryAutoStopButton())
    // {
    //   mAutoExecuter.stop();
    // }

    // if(mSetup.getSecondaryElevatorMiddleButton())
    // {
    //   auto("DeliverHatchMiddlePosition");
    // }
    // if (mSetup.AutoRunning = false)
    // {
    //   //manual();
    // }
    
    // else
    // {
    //   manual();
    // }

Here is some camera code

    //Camera Setup
    
    HttpCamera httpCamera = new HttpCamera("CoprocessorCamera", "frcvision.local:1181/stream.mjpg");
    CameraServer.getInstance().addCamera(httpCamera);
    Shuffleboard.getTab("Tab")
    .add(httpCamera);
    
*/




