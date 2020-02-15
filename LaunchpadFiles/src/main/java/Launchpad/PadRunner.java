package Launchpad;

import javax.sound.midi.InvalidMidiDataException;

public class PadRunner {

    private final Pad pad;
    private boolean on;
    private Color background;
    private Color currColor;

    public PadRunner(final Pad pad) {
        this.pad = pad;
        this.on = false;
        background = Color.BLANK;
        currColor = background;
    }

    public boolean isOn() {
        return this.on;
    }

    public Pad getPad(){
        return this.pad;
    }

    public void onLight(Launchpad lp) throws InvalidMidiDataException {
        if (this.pad != null) {
            if(this.on) {
                System.out.println(pad + " IS ON");
                currColor = Color.GREEN;
            }else{
                currColor =  this.background;
            }
            if(this.on) System.out.println(this.on + " " + Color.toString(currColor));
            lp.set(this.pad, currColor);
        }
    }

    public void setColor(Launchpad lp, Pad pad, Color color) throws InvalidMidiDataException {
        try {
            if (pad.equals(this.pad)) {
                this.background = color;
            }
        }catch(NullPointerException e) {
            System.out.println("NULL POINTER EXCEPTION!");
        }
    }

    public String getColorString(Color color){
        return Color.toString(color);
    }

    public void setOn(Launchpad lp, Pad pad) throws InvalidMidiDataException {
        try {
            if (pad.equals(this.pad)) {
                if(!on) {
                    this.on = true;
                }else if(on){
                    this.on = false;
                }
                if(this.on){
                    System.out.println(pad+" on? "+on);
                }
            }
        }catch(NullPointerException e) {
            System.out.println("NULL POINTER EXCEPTION!");
        }
    }
}