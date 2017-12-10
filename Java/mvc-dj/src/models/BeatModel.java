package models;

import models.BeatModelInterface;
import views.BPMObserver;
import views.BeatObserver;

import javax.sound.midi.MetaEventListener;
import javax.sound.midi.MetaMessage;
import javax.sound.midi.Sequencer;
import javax.sound.midi.Track;
import java.util.ArrayList;
import java.util.List;

public class BeatModel implements BeatModelInterface, MetaEventListener {
    Sequencer sequencer;
    List<BeatObserver> beatObservers = new ArrayList<>();
    List<BPMObserver> bpmobservers = new ArrayList<>();
    int bpm;
    Track track;

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
    public int getBPM() {
        return this.bpm;
    }

    public void registerObserver(BeatObserver beatObserver) {
        beatObservers.add(beatObserver);
    }

    public void removeObserver(BeatObserver beatObserver) {
        beatObservers.remove(beatObserver);
    }

    public void notifyBeatObservers() {
        beatObservers.forEach(bo -> bo.updateBeat());
    }

    public void registerObserver(BPMObserver bpmObserver) {
        bpmobservers.add(bpmObserver);
    }

    public void removeObserver(BPMObserver bpmObserver) {
        bpmobservers.remove(bpmObserver);
    }

    public void notifyBPMObservers() {
        bpmobservers.forEach(bpmo -> bpmo.updateBPM());
    }

    @Override
    public void meta(MetaMessage meta) {
        if (message.getType() == 47) {
            beatEvent();
            sequencer.start();
            setBPM(getBPM());
        }
    }
}
