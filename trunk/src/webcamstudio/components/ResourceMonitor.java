/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ResourceMonitor.java
 *
 * Created on 15-Apr-2012, 1:04:46 AM
 */
package webcamstudio.components;

import java.util.Timer;
import java.util.TimerTask;
import webcamstudio.mixers.MasterFrameBuilder;

/**
 *
 * @author patrick
 */
public class ResourceMonitor extends javax.swing.JPanel {

    Timer timer = new Timer();
    /** Creates new form ResourceMonitor */
    public ResourceMonitor() {
        initComponents();
        timer.scheduleAtFixedRate(new ResourceMonitorThread(this), 0, 1000);
    }

    public void updateInfo(){
        long maxMem = Runtime.getRuntime().maxMemory() / 1024 / 1024;
        long usedMem = ((Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory()) / 1024 / 1024);
        memLevel.setMaximum((int)maxMem);
        memLevel.setValue((int)usedMem);
        memLevel.setString(usedMem + "MB/" + maxMem + "MB");
        lblFPS.setText(MasterFrameBuilder.getFPS() + " fps");
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        memLevel = new javax.swing.JProgressBar();
        lblFPS = new javax.swing.JLabel();

        memLevel.setName("memLevel"); // NOI18N
        memLevel.setStringPainted(true);

        lblFPS.setText("jLabel1");
        lblFPS.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        lblFPS.setName("lblFPS"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(memLevel, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblFPS)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(memLevel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblFPS))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblFPS;
    private javax.swing.JProgressBar memLevel;
    // End of variables declaration//GEN-END:variables
}
class ResourceMonitorThread extends TimerTask{
    ResourceMonitor monitor;
    public ResourceMonitorThread(ResourceMonitor m){
        monitor=m;
    }
    @Override
    public void run() {
        monitor.updateInfo();
    }
}