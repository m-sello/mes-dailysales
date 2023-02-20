package ls.mestech.erp.dailysales.domain.main.statemachine;
public class TVOn  implements IStateMachine{
    TV tvContext;
    public TVOn(TV context)
    {
        this.tvContext = context;
    }
    //Users can press any of these buttons at this state-On, Off or Mute
//TV is On already, user is pressing On button again
    public void PressOnButton(TV context)
    {

    }
    //TV is On now, user is pressing Off button
    public void PressOffButton(TV context)
    {
        tvContext.CurrentState = new TVOff(context);
    }
    //TV is On now, user is pressing Mute button
    public void PressMuteButton(TV context)
    {
        tvContext.CurrentState = new TVMute(context);
    }
}
