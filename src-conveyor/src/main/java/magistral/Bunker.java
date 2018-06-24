package magistral;

import java.util.function.ToDoubleFunction;

public class Bunker {

    public Bunker(ToDoubleFunction gamma, Boolean isRegulable) {
        this.gamma = gamma;
        this.isRegulable = isRegulable;
    }

    public ToDoubleFunction getGamma() {
        return gamma;
    }

    public void setGamma(ToDoubleFunction gamma) {
        this.gamma = gamma;
    }

    public Boolean getRegulable() {
        return isRegulable;
    }

    public void setRegulable(Boolean regulable) {
        isRegulable = regulable;
    }

    private ToDoubleFunction gamma;
    Boolean isRegulable;


}
