package Launchpad;

import javax.sound.midi.InvalidMidiDataException;

public class PadRunner {

    private final Pad pad;
    private boolean on;

    public PadRunner(final Pad pad) {
        this.pad = pad;
        this.on = false;
    }

    public boolean isOn() {
        return on;
    }

    public void onLight(Launchpad lp) throws InvalidMidiDataException {
        if (pad != null) {
            lp.set(pad, this.on ? Color.GREEN : Color.BLANK);
        }
        on = false;
    }

    public void setOn(Launchpad lp, Pad pad) throws InvalidMidiDataException {
        try {
            if (pad.equals(this.pad)) {
                this.on = true;
                System.out.println(pad);
            }
        }catch(NullPointerException e) {
            System.out.println("NULL POINTER EXCEPTION!");
        }
    }
}