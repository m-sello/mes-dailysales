package ls.mestech.erp.dailysales.domain.main.statemachine;
public class TV {
    public IStateMachine CurrentState;
    public TV()
    {
        this.CurrentState = new TVOff(this);
    }
    public void PressOffButton()
    {
        CurrentState.PressOffButton(this);//Delegating the state
    }
    public void PressOnButton()
    {
        CurrentState.PressOnButton(this);//Delegating the state
    }
    public void PressMuteButton()
    {
        CurrentState.PressMuteButton(this);//Delegating the state
    }
}
