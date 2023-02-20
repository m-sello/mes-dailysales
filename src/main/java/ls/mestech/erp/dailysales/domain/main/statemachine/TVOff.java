package ls.mestech.erp.dailysales.domain.main.statemachine;
public class TVOff  implements IStateMachine{
    TV tvContext;
    //Initially we'll start from Off state
    public TVOff(TV context)
    {
        this.tvContext = context;
    }
    //Users can press any of these buttons at this state-On, Off or Mute
//TV is Off now, user is pressing On button
    public void PressOnButton(TV context)
    {
        tvContext.CurrentState = new TVOn(context);
    }
    //TV is Off already, user is pressing Off button again
    public void PressOffButton(TV context)
    {
    }
    //TV is Off now, user is pressing Mute button
    public void PressMuteButton(TV context)
    {

    }
}
