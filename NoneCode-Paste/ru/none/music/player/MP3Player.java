/*
 * Decompiled with CFR 0.150.
 */
package ru.none.music.player;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.BooleanControl;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;
import ru.none.music.Player;
import ru.none.music.player.MP3PlayerThemeDefault;
import ru.none.music.util.BitstreamException;
import ru.none.music.util.Decoder;
import ru.none.music.util.DecoderException;
import ru.none.music.util.Frame;
import ru.none.music.util.SampleBuffer;
import ru.none.music.util.SoundStream;

public class MP3Player {
    private static final long serialVersionUID = 1L;
    private static final Random RANDOM = new Random();
    private List<Object> playlist = new ArrayList<Object>();
    private transient boolean isPaused = false;
    private transient boolean isStopped = true;
    private volatile transient int volume = 50;
    private volatile transient boolean shuffle = false;
    private volatile transient boolean repeat = true;
    private volatile transient Thread playingThread = null;
    private volatile transient int playingIndex = 0;
    private volatile transient SourceDataLine playingSource = null;
    private volatile transient int playingSourceVolume = 0;
    public static URL songurl;
    public static String durationInSeconds;
    public InputStream inputStreamVar;

    public MP3Player() {
        this.init();
    }

    public MP3Player(File file) {
        this.add(file);
        this.init();
    }

    public MP3Player(File ... files) {
        for (File file : files) {
            this.add(file);
        }
        this.init();
    }

    public MP3Player(URL url) {
        this.add(url);
        songurl = url;
        this.init();
    }

    public MP3Player(URL ... urls) {
        for (URL url : urls) {
            this.add(url);
        }
        this.init();
    }

    public long getMaxLeight() {
        try {
            BufferedInputStream bis = new BufferedInputStream(this.inputStreamVar);
            AudioInputStream audioInputStream = null;
            audioInputStream = AudioSystem.getAudioInputStream(bis);
            AudioFormat format2 = audioInputStream.getFormat();
            long frames = audioInputStream.getFrameLength();
            return frames;
        }
        catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return 0L;
    }

    private void init() {
        new MP3PlayerThemeDefault().apply(this);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public MP3Player add(File file, boolean recursively) {
        if (file.isFile()) {
            if (file.getName().endsWith(".cla")) {
                MP3Player mP3Player = this;
                synchronized (mP3Player) {
                    this.playlist.add(file);
                }
            }
        } else if (file.isDirectory()) {
            File[] files;
            for (File file2 : files = file.listFiles()) {
                if (!file2.isFile() && !recursively) continue;
                this.add(file2, recursively);
            }
        } else {
            throw new IllegalArgumentException("WTF is this? ( " + file + " )");
        }
        return this;
    }

    public MP3Player add(File file) {
        this.add(file, true);
        return this;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public MP3Player add(URL url) {
        MP3Player mP3Player = this;
        synchronized (mP3Player) {
            this.playlist.add(url);
        }
        return this;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void play() {
        MP3Player mP3Player = this;
        synchronized (mP3Player) {
            if (this.isPaused) {
                this.isPaused = false;
                Player.playerPlaying = !this.isPaused && !this.isStopped;
                this.notifyAll();
                return;
            }
        }
        this.stop();
        if (this.playlist.size() == 0) {
            return;
        }
        mP3Player = this;
        synchronized (mP3Player) {
            this.isStopped = false;
            Player.playerStopped = false;
            Player.playerPlaying = !this.isPaused && !this.isStopped;
        }
        if (this.playingThread == null) {
            this.playingThread = new Thread(){

                /*
                 * WARNING - Removed try catching itself - possible behaviour change.
                 */
                @Override
                public void run() {
                    boolean skipForwardAllowed;
                    MP3Player mP3Player;
                    InputStream inputStream = null;
                    try {
                        MP3Player mP3Player2;
                        Object playlistObject;
                        mP3Player = MP3Player.this;
                        synchronized (mP3Player) {
                            playlistObject = MP3Player.this.playlist.get(MP3Player.this.playingIndex);
                        }
                        if (playlistObject instanceof File) {
                            inputStream = new FileInputStream((File)playlistObject);
                        } else if (playlistObject instanceof URL) {
                            inputStream = ((URL)playlistObject).openStream();
                            MP3Player.this.inputStreamVar = ((URL)playlistObject).openStream();
                        } else {
                            throw new IOException("this is impossible; how come the play list contains this kind of object? :: " + playlistObject.getClass());
                        }
                        SoundStream soundStream = new SoundStream(inputStream);
                        Decoder decoder = new Decoder();
                        while (true) {
                            mP3Player2 = MP3Player.this;
                            synchronized (mP3Player2) {
                                if (MP3Player.this.isStopped) {
                                    break;
                                }
                                if (MP3Player.this.isPaused) {
                                    if (MP3Player.this.playingSource != null) {
                                        MP3Player.this.playingSource.flush();
                                    }
                                    MP3Player.this.playingSourceVolume = MP3Player.this.volume;
                                    try {
                                        MP3Player.this.wait();
                                    }
                                    catch (InterruptedException interruptedException) {
                                        // empty catch block
                                    }
                                    continue;
                                }
                            }
                            try {
                                Frame frame = soundStream.readFrame();
                                if (frame == null) break;
                                if (MP3Player.this.playingSource == null) {
                                    int frequency = frame.frequency();
                                    int channels = frame.mode() == 3 ? 1 : 2;
                                    AudioFormat format = new AudioFormat(frequency, 16, channels, true, false);
                                    Line line = AudioSystem.getLine(new DataLine.Info(SourceDataLine.class, format));
                                    MP3Player.this.playingSource = (SourceDataLine)line;
                                    MP3Player.this.playingSource.open(format);
                                    MP3Player.this.playingSource.start();
                                    MP3Player.this.setVolume(MP3Player.this.playingSource, MP3Player.this.playingSourceVolume = 0);
                                }
                                SampleBuffer output = (SampleBuffer)decoder.decodeFrame(frame, soundStream);
                                short[] buffer = output.getBuffer();
                                int offs = 0;
                                int len = output.getBufferLength();
                                if (MP3Player.this.playingSourceVolume != MP3Player.this.volume) {
                                    if (MP3Player.this.playingSourceVolume > MP3Player.this.volume) {
                                        MP3Player.this.playingSourceVolume = MP3Player.this.playingSourceVolume - 10;
                                        if (MP3Player.this.playingSourceVolume < MP3Player.this.volume) {
                                            MP3Player.this.playingSourceVolume = MP3Player.this.volume;
                                        }
                                    } else {
                                        MP3Player.this.playingSourceVolume = MP3Player.this.playingSourceVolume + 10;
                                        if (MP3Player.this.playingSourceVolume > MP3Player.this.volume) {
                                            MP3Player.this.playingSourceVolume = MP3Player.this.volume;
                                        }
                                    }
                                    MP3Player.this.setVolume(MP3Player.this.playingSource, MP3Player.this.playingSourceVolume);
                                }
                                MP3Player.this.playingSource.write(MP3Player.this.toByteArray(buffer, offs, len), 0, len * 2);
                                soundStream.closeFrame();
                            }
                            catch (LineUnavailableException | BitstreamException | DecoderException e) {
                                e.printStackTrace();
                                break;
                            }
                        }
                        if (MP3Player.this.playingSource != null) {
                            mP3Player2 = MP3Player.this;
                            synchronized (mP3Player2) {
                                if (!MP3Player.this.isStopped) {
                                    MP3Player.this.playingSource.drain();
                                } else {
                                    MP3Player.this.playingSource.flush();
                                }
                            }
                            MP3Player.this.playingSource.stop();
                            MP3Player.this.playingSource.close();
                            MP3Player.this.playingSource = null;
                        }
                        try {
                            soundStream.close();
                        }
                        catch (Exception exception) {
                            // empty catch block
                        }
                    }
                    catch (IOException playlistObject) {
                    }
                    finally {
                        if (inputStream != null) {
                            try {
                                inputStream.close();
                            }
                            catch (Exception playlistObject) {}
                        }
                    }
                    mP3Player = MP3Player.this;
                    synchronized (mP3Player) {
                        skipForwardAllowed = !MP3Player.this.isStopped;
                        MP3Player.this.isPaused = false;
                        MP3Player.this.isStopped = true;
                        Player.playerPlaying = !MP3Player.this.isPaused && !MP3Player.this.isStopped;
                        Player.playerStopped = true;
                    }
                    MP3Player.this.playingThread = null;
                    if (skipForwardAllowed) {
                        MP3Player.this.skipForward();
                    }
                }
            };
            this.playingThread.start();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public boolean isPlaying() {
        MP3Player mP3Player = this;
        synchronized (mP3Player) {
            return !this.isPaused && !this.isStopped;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void pause() {
        if (!this.isPlaying()) {
            return;
        }
        MP3Player mP3Player = this;
        synchronized (mP3Player) {
            this.isPaused = true;
            Player.playerPlaying = !this.isPaused && !this.isStopped;
            this.notifyAll();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public boolean isPaused() {
        MP3Player mP3Player = this;
        synchronized (mP3Player) {
            return this.isPaused;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void stop() {
        MP3Player mP3Player = this;
        synchronized (mP3Player) {
            this.isPaused = false;
            this.isStopped = true;
            Player.playerStopped = true;
            Player.playerPlaying = !this.isPaused && !this.isStopped;
            this.notifyAll();
        }
        if (this.playingThread != null) {
            try {
                this.playingThread.join();
            }
            catch (InterruptedException interruptedException) {
                // empty catch block
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public boolean isStopped() {
        MP3Player mP3Player = this;
        synchronized (mP3Player) {
            return this.isStopped;
        }
    }

    public void skipForward() {
        this.skip(1);
    }

    public void skipBackward() {
        this.skip(-1);
    }

    private void skip(int items) {
        if (this.playlist.size() == 0) {
            return;
        }
        boolean playAllowed = this.isPlaying();
        if (this.shuffle) {
            this.playingIndex = RANDOM.nextInt(this.playlist.size());
        } else {
            this.playingIndex += items;
            if (this.playingIndex > this.playlist.size() - 1) {
                if (this.repeat) {
                    this.playingIndex = 0;
                } else {
                    this.playingIndex = this.playlist.size() - 1;
                    playAllowed = false;
                }
            } else if (this.playingIndex < 0) {
                if (this.repeat) {
                    this.playingIndex = this.playlist.size() - 1;
                } else {
                    this.playingIndex = 0;
                    playAllowed = false;
                }
            }
        }
        this.stop();
        if (playAllowed) {
            this.play();
        }
    }

    public MP3Player setVolume(int volume) {
        if (volume < 0 || volume > 100) {
            throw new IllegalArgumentException("Wrong value for volume, must be in interval [0..100].");
        }
        this.volume = volume;
        return this;
    }

    public int getVolume() {
        return this.volume;
    }

    public MP3Player setShuffle(boolean shuffle) {
        this.shuffle = shuffle;
        return this;
    }

    public boolean isShuffle() {
        return this.shuffle;
    }

    public MP3Player setRepeat(boolean repeat) {
        this.repeat = repeat;
        return this;
    }

    public boolean isRepeat() {
        return this.repeat;
    }

    private void setVolume(SourceDataLine source, int volume) {
        try {
            FloatControl gainControl = (FloatControl)source.getControl(FloatControl.Type.MASTER_GAIN);
            BooleanControl muteControl = (BooleanControl)source.getControl(BooleanControl.Type.MUTE);
            if (volume == 0) {
                muteControl.setValue(true);
            } else {
                muteControl.setValue(false);
                gainControl.setValue((float)(Math.log((double)volume / 100.0) / Math.log(10.0) * 20.0));
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public int getPosition() {
        int pos = 0;
        if (this.playingSource != null) {
            pos = (int)(this.playingSource.getMicrosecondPosition() / 1000L);
        }
        return pos;
    }

    public void getDuration() throws UnsupportedAudioFileException, IOException {
        if (this.playingSource.getFramePosition() > 0) {
            BufferedInputStream bis = new BufferedInputStream(this.inputStreamVar);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(bis);
            AudioFormat format2 = audioInputStream.getFormat();
            long frames = audioInputStream.getFrameLength();
            durationInSeconds = String.valueOf(((double)frames + 0.0) / (double)format2.getFrameRate());
        }
    }

    private byte[] toByteArray(short[] ss, int offs, int len) {
        byte[] bb = new byte[len * 2];
        int idx = 0;
        while (len-- > 0) {
            short s = ss[offs++];
            bb[idx++] = (byte)s;
            bb[idx++] = (byte)(s >>> 8);
        }
        return bb;
    }

    private void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        objectInputStream.defaultReadObject();
    }

    static {
        durationInSeconds = "0:00";
    }
}

