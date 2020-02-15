package Launchpad;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;
import java.util.ArrayList;
import java.util.List;

public class LaunchpadRunner implements LaunchpadReceiver {

    private final List<PadRunner> pads;
    private final Launchpad lp;

    public LaunchpadRunner() throws MidiUnavailableException {
        this.lp = new Launchpad(this);
        pads = new ArrayList<>();
        pads.add(new PadRunner(Pad.CON1));
        pads.add(new PadRunner(Pad.CON2));
        pads.add(new PadRunner(Pad.CON3));
        pads.add(new PadRunner(Pad.CON4));
        pads.add(new PadRunner(Pad.CON5));
        pads.add(new PadRunner(Pad.CON6));
        pads.add(new PadRunner(Pad.CON7));
        pads.add(new PadRunner(Pad.CON8));
        pads.add(new PadRunner(null));

        pads.add(new PadRunner(Pad.A1));
        pads.add(new PadRunner(Pad.A2));
        pads.add(new PadRunner(Pad.A3));
        pads.add(new PadRunner(Pad.A4));
        pads.add(new PadRunner(Pad.A5));
        pads.add(new PadRunner(Pad.A6));
        pads.add(new PadRunner(Pad.A7));
        pads.add(new PadRunner(Pad.A8));
        pads.add(new PadRunner(Pad.A));

        pads.add(new PadRunner(Pad.B1));
        pads.add(new PadRunner(Pad.B2));
        pads.add(new PadRunner(Pad.B3));
        pads.add(new PadRunner(Pad.B4));
        pads.add(new PadRunner(Pad.B5));
        pads.add(new PadRunner(Pad.B6));
        pads.add(new PadRunner(Pad.B7));
        pads.add(new PadRunner(Pad.B8));
        pads.add(new PadRunner(Pad.B));

        pads.add(new PadRunner(Pad.C1));
        pads.add(new PadRunner(Pad.C2));
        pads.add(new PadRunner(Pad.C3));
        pads.add(new PadRunner(Pad.C4));
        pads.add(new PadRunner(Pad.C5));
        pads.add(new PadRunner(Pad.C6));
        pads.add(new PadRunner(Pad.C7));
        pads.add(new PadRunner(Pad.C8));
        pads.add(new PadRunner(Pad.C));

        pads.add(new PadRunner(Pad.D1));
        pads.add(new PadRunner(Pad.D2));
        pads.add(new PadRunner(Pad.D3));
        pads.add(new PadRunner(Pad.D4));
        pads.add(new PadRunner(Pad.D5));
        pads.add(new PadRunner(Pad.D6));
        pads.add(new PadRunner(Pad.D7));
        pads.add(new PadRunner(Pad.D8));
        pads.add(new PadRunner(Pad.D));

        pads.add(new PadRunner(Pad.E1));
        pads.add(new PadRunner(Pad.E2));
        pads.add(new PadRunner(Pad.E3));
        pads.add(new PadRunner(Pad.E4));
        pads.add(new PadRunner(Pad.E5));
        pads.add(new PadRunner(Pad.E6));
        pads.add(new PadRunner(Pad.E7));
        pads.add(new PadRunner(Pad.E8));
        pads.add(new PadRunner(Pad.E));

        pads.add(new PadRunner(Pad.F1));
        pads.add(new PadRunner(Pad.F2));
        pads.add(new PadRunner(Pad.F3));
        pads.add(new PadRunner(Pad.F4));
        pads.add(new PadRunner(Pad.F5));
        pads.add(new PadRunner(Pad.F6));
        pads.add(new PadRunner(Pad.F7));
        pads.add(new PadRunner(Pad.F8));
        pads.add(new PadRunner(Pad.F));

        pads.add(new PadRunner(Pad.G1));
        pads.add(new PadRunner(Pad.G2));
        pads.add(new PadRunner(Pad.G3));
        pads.add(new PadRunner(Pad.G4));
        pads.add(new PadRunner(Pad.G5));
        pads.add(new PadRunner(Pad.G6));
        pads.add(new PadRunner(Pad.G7));
        pads.add(new PadRunner(Pad.G8));
        pads.add(new PadRunner(Pad.G));

        pads.add(new PadRunner(Pad.H1));
        pads.add(new PadRunner(Pad.H2));
        pads.add(new PadRunner(Pad.H3));
        pads.add(new PadRunner(Pad.H4));
        pads.add(new PadRunner(Pad.H5));
        pads.add(new PadRunner(Pad.H6));
        pads.add(new PadRunner(Pad.H7));
        pads.add(new PadRunner(Pad.H8));
        pads.add(new PadRunner(Pad.H));
    }

    public void onLights() throws InvalidMidiDataException {
        for (PadRunner spot : pads) {
            spot.onLight(lp);
        }
    }

    @Override
    @SuppressWarnings("squid:S00112")
    public void receive(Pad arg0) {
        try {
            for (PadRunner pad : pads) {
                pad.setOn(lp, arg0);
            }
        } catch (InvalidMidiDataException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

}

