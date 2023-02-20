package ls.mestech.erp.dailysales.domain.main.statemachine;
public class TVMute implements IStateMachine{
    TV tvContext;
    public TVMute(TV context)
    {
        this.tvContext = context;
    }
    //Users can press any of these buttons at this state-On, Off or Mute
    //TV is in mute, user is pressing On button
    public void PressOnButton(TV context)
    {
        tvContext.CurrentState = new TVOn(context);
    }
    //TV is in mute, user is pressing Off button
    public void PressOffButton(TV context)
    {
        tvContext.CurrentState = new TVOff(context);
    }
    //TV is in mute already, user is pressing mute button again
    public void PressMuteButton(TV context)
    {

    }
}
