package frc.robot.auto.modes;
import frc.robot.auto.AutoModeBase;
import frc.robot.auto.AutoModeEndedException;
import frc.robot.auto.actions.AutoLEDAction;
import frc.robot.auto.actions.WaitAction;
import frc.robot.Setup;

public class MatchTime extends AutoModeBase {

	@Override
	protected void routine() throws AutoModeEndedException {

        runAction(new WaitAction(105));
	runAction(new AutoLEDAction(true));
	runAction(new WaitAction(5));
	runAction(new AutoLEDAction(true));
	
	}

}