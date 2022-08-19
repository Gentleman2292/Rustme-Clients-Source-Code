/*
 * Decompiled with CFR 0.150.
 */
package ru.none.music.player;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import ru.none.music.player.MP3Player;
import ru.none.music.player.MP3PlayerTheme;

final class MP3PlayerThemeDefault
implements MP3PlayerTheme {
    MP3PlayerThemeDefault() {
    }

    @Override
    public void apply(final MP3Player player) {
        JButton playButton = new JButton();
        playButton.setText(">");
        playButton.setToolTipText("Play");
        playButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                player.play();
            }
        });
        JButton pauseButton = new JButton();
        pauseButton.setText("||");
        pauseButton.setToolTipText("Pause");
        pauseButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                player.pause();
            }
        });
        JButton stopButton = new JButton();
        stopButton.setText("#");
        stopButton.setToolTipText("Stop");
        stopButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                player.stop();
            }
        });
        JButton skipBackwardButton = new JButton();
        skipBackwardButton.setText("|<");
        skipBackwardButton.setToolTipText("Skip Backward");
        skipBackwardButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                player.skipBackward();
            }
        });
        JButton skipForwardButton = new JButton();
        skipForwardButton.setText(">|");
        skipForwardButton.setToolTipText("Skip Forward");
        skipForwardButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                player.skipForward();
            }
        });
        final JSlider volumeSlider = new JSlider();
        volumeSlider.setToolTipText("Volume");
        volumeSlider.addChangeListener(new ChangeListener(){

            @Override
            public void stateChanged(ChangeEvent e) {
                player.setVolume(volumeSlider.getValue());
            }
        });
        volumeSlider.setMinimum(0);
        volumeSlider.setMaximum(100);
        volumeSlider.setMajorTickSpacing(50);
        volumeSlider.setMinorTickSpacing(10);
        volumeSlider.setPaintTicks(true);
        volumeSlider.setPaintTrack(true);
        final JCheckBox repeatCheckBox = new JCheckBox();
        repeatCheckBox.setText("Repeat");
        repeatCheckBox.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                player.setRepeat(repeatCheckBox.isSelected());
            }
        });
        final JCheckBox shuffleCheckBox = new JCheckBox();
        shuffleCheckBox.setText("Shuffle");
        shuffleCheckBox.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                player.setShuffle(shuffleCheckBox.isSelected());
            }
        });
    }
}

