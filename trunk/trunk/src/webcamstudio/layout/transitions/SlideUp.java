/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package webcamstudio.layout.transitions;

import java.util.logging.Level;
import java.util.logging.Logger;
import webcamstudio.components.Mixer;
import webcamstudio.layout.LayoutItem;
import webcamstudio.sources.VideoSource;

/**
 *
 * @author pballeux
 */
public class SlideUp extends Transition {

    @Override
    public void doTransition(final LayoutItem item) {
        VideoSource source = item.getSource();
        int frames = 30;
        int x = item.getX();
        int y = Mixer.getHeight();
        source.setOutputWidth(item.getWidth());
        source.setOutputHeight(item.getHeight());
        source.setVolume(item.getVolume());
        source.setOpacity(100);
        int yDelta = ((Mixer.getHeight()-item.getY()) / frames);
        source.setShowAtX(x);
        source.setShowAtY(y);
        if (!source.isPlaying()) {
            source.startSource();
        }
        for (int i = 0; i < frames; i++) {
            try {
                source.setShowAtY(y - (i*yDelta));
                Thread.sleep(2000/frames);
            } catch (InterruptedException ex) {
                Logger.getLogger(getName()).log(Level.SEVERE, null, ex);
            }
        }
        source.setShowAtX(item.getX());
        source.setShowAtY(item.getY());
        source.setOutputWidth(item.getWidth());
        source.setOutputHeight(item.getHeight());
        source.fireSourceUpdated();

    }
}