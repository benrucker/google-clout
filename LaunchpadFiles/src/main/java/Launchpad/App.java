package Launchpad;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;

public class App implements Runnable {
  private LaunchpadRunner launchpad;
  private PadRunner[][] pads;
  private boolean running = true;
  int TICK_LENGTH = 1000; //milliseconds

  public static void main(String[] args) throws MidiUnavailableException {
    new App().run();
  }

  public App() throws MidiUnavailableException {
    launchpad = new LaunchpadRunner();
  }

  public void run() {
    while (running) {
      try {
        launchpad.onLights();
        pads = launchpad.getButtons();
        if(pads[0][0].isOn()){
          launchpad.loadingAnimation();
        }
        Thread.sleep(TICK_LENGTH);
      } catch (InvalidMidiDataException | InterruptedException e) {
        System.err.println(e.getMessage());
        this.running = false;
      }
    }
  }

}
