package simple.project.giis.model.entity;

import javax.persistence.Entity;

@Entity
public class PushSetting extends BaseEntity {


    public PushSetting() {
    }

    public PushSetting( boolean var1, boolean var2, boolean var3, boolean var4) {
        setPushSwitch(var1);
        setVoice(var2);
        setVibrate(var3);
        setFloatWindow(var4);
    }


    private boolean pushSwitch;

    private boolean voice;

    private boolean vibrate;

    private boolean floatWindow;


    public boolean isPushSwitch() {
        return pushSwitch;
    }

    public void setPushSwitch(boolean pushSwitch) {
        this.pushSwitch = pushSwitch;
    }

    public boolean isVoice() {
        return voice;
    }

    public void setVoice(boolean voice) {
        this.voice = voice;
    }

    public boolean isVibrate() {
        return vibrate;
    }

    public void setVibrate(boolean vibrate) {
        this.vibrate = vibrate;
    }

    public boolean isFloatWindow() {
        return floatWindow;
    }

    public void setFloatWindow(boolean floatWindow) {
        this.floatWindow = floatWindow;
    }


}
