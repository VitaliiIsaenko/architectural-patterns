package models;

import models.BeatModelInterface;

import javax.sound.midi.MetaEventListener;
import javax.sound.midi.MetaMessage;
import javax.sound.midi.Sequencer;
import java.util.ArrayList;

public class BeatModel implements BeatModelInterface, MetaEventListener {
    Sequencer sequencer;
    List<BeatObserver> beatObservers = new ArrayList<>();
    List<BPMObserver> bpmobservers = new ArrayList<>();
    int bpm;

    @Override
    public void initialize() {
        setUpMidi();
        buildTrackAndStart();
    }

    @Override
    public void on() {
        sequencer.start();
        setBPM(90);
    }

    @Override
    public void off() {
        setBPM(0);
        sequencer.stop();
    }

    @Override
    public void setBPM(int bpm) {
        this.bpm = bpm;
        sequencer.setTempoInBPM(getBPM(););
        notifyBPMObservers();
    }

    @Override
    public void getBPM() {
        return this.bpm;
    }

    @Override
    public void registerObserver(BeatObserver beatObserver) {

    }

    @Override
    public void removeObserver(BeatObserver beatObserver) {

    }

    @Override
    public void registerObserver(BPMObserver bpmObserver) {

    }

    @Override
    public void removeObserver(BPMObserver bpmObserver) {

    }

    @Override
    public void meta(MetaMessage meta) {

    }
}
