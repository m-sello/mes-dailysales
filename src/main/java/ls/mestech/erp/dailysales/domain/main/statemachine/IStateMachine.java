package ls.mestech.erp.dailysales.domain.main.statemachine;
public interface IStateMachine {
    void PressOnButton(TV context);
    void PressOffButton(TV context);
    void PressMuteButton(TV context);
}
