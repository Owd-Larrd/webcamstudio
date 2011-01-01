/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ControlDesktop.java
 *
 * Created on 2010-01-12, 23:50:21
 */
package webcamstudio.controls;

import java.util.logging.Level;
import java.util.logging.Logger;
import webcamstudio.sources.VideoSource;
import webcamstudio.sources.VideoSourceMovie;

/**
 *
 * @author pballeux
 */
public class ControlPosition extends javax.swing.JPanel implements Controls {

    VideoSource source = null;
    String label = "Capture";

    /** Creates new form ControlDesktop */
    public ControlPosition(VideoSource src) {
        initComponents();
        this.source = src;
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("webcamstudio.Languages");
        label = bundle.getString("POSITION");
        spinWidth.setValue(source.getOutputWidth());
        spinHeight.setValue(source.getOutputHeight());
        spinX.setValue(source.getShowAtX());
        spinY.setValue(source.getShowAtY());
        if (source instanceof VideoSourceMovie) {
            lblSeek.setVisible(true);
            slideSeek.setVisible(true);
            slideSeek.setMaximum((int) ((VideoSourceMovie) source).getDuration());
            slideSeek.setMinimum(0);
            slideSeek.setValue((int) ((VideoSourceMovie) source).getSeekPosition());
            new Thread(new Runnable() {

                @Override
                public void run() {
                    while (source != null) {
                        if (slideSeek.getMaximum() == 0 && (source.isPlaying() || source.isPaused())) {
                            slideSeek.setMaximum((int) ((VideoSourceMovie) source).getDuration());
                            slideSeek.setMajorTickSpacing((slideSeek.getMaximum() / 5) / 60 * 60);
                            slideSeek.setMinorTickSpacing(0);
                        }
                        int pos = (int) ((VideoSourceMovie) source).getSeekPosition();
                        if (!slideSeek.getValueIsAdjusting()) {
                            slideSeek.setValue(pos);
                        }
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(ControlPosition.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }).start();
        } else {
            lblSeek.setVisible(false);
            slideSeek.setVisible(false);
        }


    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblWidth = new javax.swing.JLabel();
        spinWidth = new javax.swing.JSpinner();
        lblHeight = new javax.swing.JLabel();
        spinHeight = new javax.swing.JSpinner();
        lblX = new javax.swing.JLabel();
        spinX = new javax.swing.JSpinner();
        lblY = new javax.swing.JLabel();
        spinY = new javax.swing.JSpinner();
        lblSeek = new javax.swing.JLabel();
        slideSeek = new javax.swing.JSlider();

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("webcamstudio/Languages"); // NOI18N
        lblWidth.setText(bundle.getString("WIDTH")); // NOI18N
        lblWidth.setName("lblWidth"); // NOI18N

        spinWidth.setName("spinWidth"); // NOI18N
        spinWidth.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spinWidthStateChanged(evt);
            }
        });

        lblHeight.setText(bundle.getString("HEIGHT")); // NOI18N
        lblHeight.setName("lblHeight"); // NOI18N

        spinHeight.setName("spinHeight"); // NOI18N
        spinHeight.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spinHeightStateChanged(evt);
            }
        });

        lblX.setText(bundle.getString("X")); // NOI18N
        lblX.setName("lblX"); // NOI18N

        spinX.setName("spinX"); // NOI18N
        spinX.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spinXStateChanged(evt);
            }
        });

        lblY.setText(bundle.getString("Y")); // NOI18N
        lblY.setName("lblY"); // NOI18N

        spinY.setName("spinY"); // NOI18N
        spinY.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spinYStateChanged(evt);
            }
        });

        lblSeek.setText(bundle.getString("SEEK")); // NOI18N
        lblSeek.setName("lblSeek"); // NOI18N

        slideSeek.setPaintLabels(true);
        slideSeek.setName("slideSeek"); // NOI18N
        slideSeek.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                slideSeekMouseReleased(evt);
            }
        });
        slideSeek.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                slideSeekMouseDragged(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblWidth)
                                .addGap(18, 18, 18)
                                .addComponent(spinWidth, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblX)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(spinX, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblHeight)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(spinHeight, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblY)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(spinY, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblSeek)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(slideSeek, javax.swing.GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblWidth)
                    .addComponent(spinWidth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblHeight)
                    .addComponent(spinHeight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblX)
                    .addComponent(spinY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblY)
                    .addComponent(spinX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblSeek)
                    .addComponent(slideSeek, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(121, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void spinWidthStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spinWidthStateChanged
        source.setOutputWidth((Integer) spinWidth.getValue());

    }//GEN-LAST:event_spinWidthStateChanged

    private void spinHeightStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spinHeightStateChanged
        source.setOutputHeight((Integer) spinHeight.getValue());
    }//GEN-LAST:event_spinHeightStateChanged

    private void spinXStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spinXStateChanged
        source.setShowAtX((Integer) spinX.getValue());
    }//GEN-LAST:event_spinXStateChanged

    private void spinYStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spinYStateChanged
        source.setShowAtY((Integer) spinY.getValue());
    }//GEN-LAST:event_spinYStateChanged

    private void slideSeekMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_slideSeekMouseDragged
    }//GEN-LAST:event_slideSeekMouseDragged

    private void slideSeekMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_slideSeekMouseReleased
        if (source instanceof VideoSourceMovie) {
            ((VideoSourceMovie) source).seek(slideSeek.getValue());
        }
    }//GEN-LAST:event_slideSeekMouseReleased
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblHeight;
    private javax.swing.JLabel lblSeek;
    private javax.swing.JLabel lblWidth;
    private javax.swing.JLabel lblX;
    private javax.swing.JLabel lblY;
    private javax.swing.JSlider slideSeek;
    private javax.swing.JSpinner spinHeight;
    private javax.swing.JSpinner spinWidth;
    private javax.swing.JSpinner spinX;
    private javax.swing.JSpinner spinY;
    // End of variables declaration//GEN-END:variables

    @Override
    public String getLabel() {
        return label;
    }

    @Override
    public void removeControl() {
        source = null;
    }
}