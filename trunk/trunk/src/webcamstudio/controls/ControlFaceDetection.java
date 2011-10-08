/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ControlActivity.java
 *
 * Created on 2010-01-12, 23:53:43
 */
package webcamstudio.controls;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import webcamstudio.Main;
import webcamstudio.components.Faces;
import webcamstudio.components.SourceListener;
import webcamstudio.sources.VideoSource;

/**
 *
 * @author pballeux
 */
public class ControlFaceDetection extends javax.swing.JPanel implements Controls {

    private String label = "";
    private VideoSource source = null;
    private Faces faces = new Faces();
    private SourceListener listener=null;
    /** Creates new form ControlActivity */
    public ControlFaceDetection(VideoSource source) {
        initComponents();
        this.source = source;
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("webcamstudio.Languages");
        label = bundle.getString("FACEDETECTION");
        javax.swing.DefaultComboBoxModel model = new javax.swing.DefaultComboBoxModel(faces.getKeys());
        javax.swing.DefaultListCellRenderer renderer = new javax.swing.DefaultListCellRenderer() {

            @Override
            public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean hasFocus) {
                Component comp = super.getListCellRendererComponent(list, value, index, isSelected, hasFocus);
                JLabel label = (JLabel) comp;
                Image face = faces.getImage(value.toString());
                if (face != null) {
                    BufferedImage img = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
                    if (face != null) {
                        img.createGraphics().drawImage(face, 0, 0, 16, 16, 0, 0, face.getWidth(null), face.getHeight(null), null);
                        label.setIcon(new ImageIcon(img));
                    }
                }

                return comp;
            }
        };
        cboFaces.setRenderer(renderer);
        cboFaces.setModel(model);
        cboFaces.setSelectedIndex(0);

    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        chkEnableFaceDetection = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        cboFaces = new javax.swing.JComboBox();

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("webcamstudio/Languages"); // NOI18N
        chkEnableFaceDetection.setText(bundle.getString("ENABLEFACEDETECTION")); // NOI18N
        chkEnableFaceDetection.setName("chkEnableFaceDetection"); // NOI18N
        chkEnableFaceDetection.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkEnableFaceDetectionActionPerformed(evt);
            }
        });

        jLabel1.setText(bundle.getString("FACES")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        cboFaces.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboFaces.setName("cboFaces"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chkEnableFaceDetection)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboFaces, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chkEnableFaceDetection)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cboFaces, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(237, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void chkEnableFaceDetectionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkEnableFaceDetectionActionPerformed
        if (chkEnableFaceDetection.isSelected()) {
            new Thread(new Runnable() {

                @Override
                public void run() {
                    System.out.println("Face Detection Started...");
                    int[] points = null;
                    String selectedFace = cboFaces.getSelectedItem().toString();
                    Image meface = faces.getImage(selectedFace);
                    BufferedImage face = new BufferedImage(320, 240, BufferedImage.TYPE_INT_ARGB);
                    Graphics2D buffer = face.createGraphics();
                    buffer.setBackground(new Color(0,0,0,0));
                    while (chkEnableFaceDetection.isSelected()) {
                        try {
                            if (source.getRawImage() != null) {
                                if (!selectedFace.equals(cboFaces.getSelectedItem())){
                                    selectedFace = cboFaces.getSelectedItem().toString();
                                    meface = faces.getImage(selectedFace);
                                }
                                points = Main.MainFaceDetector.detectAllFaces(source.getRawImage());
                                if (points != null) {
                                    int xl = points[0];
                                    int yl = points[1];
                                    int xr = points[2];
                                    int eyesDelta = xr - xl;
                                    int faceWidth = 400 * eyesDelta / 100;
                                    int newXl = xl - (150 * faceWidth / 400);
                                    int newYl = yl - (195 * faceWidth / 400);
                                    buffer.clearRect(0, 0, 320, 240);
                                    buffer.drawImage(meface, newXl, newYl, newXl + faceWidth, newYl + faceWidth, 0, 0, 400, 400, null);
                                    
                                }
                                source.setFaceDetection(face);
                            }
                            Thread.sleep(200);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(ControlFaceDetection.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    buffer.dispose();
                    source.setFaceDetection(null);
                    System.out.println("Face Detection Stopped...");
                }
            }).start();
        }
    }//GEN-LAST:event_chkEnableFaceDetectionActionPerformed

    @Override
    public String getLabel() {
        return label;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cboFaces;
    private javax.swing.JCheckBox chkEnableFaceDetection;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables

    @Override
    public void removeControl() {
        chkEnableFaceDetection.setSelected(false);
        source=null;
        faces=null;
        
    }

    @Override
    public void setListener(SourceListener l) {
        listener=l;
    }
}