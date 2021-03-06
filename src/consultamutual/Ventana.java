/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consultamutual;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

/**
 *
 * @author Sistemas
 */
public class Ventana extends javax.swing.JFrame {

    /**
     * Creates new form Ventana
     */
    FirefoxOptions options = new FirefoxOptions()
            .addPreference("network.proxy.type", 0)
            .addPreference("javascript.enabled", true)
            .addPreference("permissions.default.image", 3)
            .addPreference("permissions.default.stylesheet", 2).addArguments("--headless")
            ;

    WebDriver driver;

    public Ventana() {
        initComponents();
        setLocationRelativeTo(null);
        System.setProperty("webdriver.gecko.driver", "C:\\Program Files (x86)\\Mozilla Firefox\\geckodriver.exe");
        driver = new FirefoxDriver(options);
        driver.get("http://www.mutualser.com/app/consulta_afiliados_form.php");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        text_identidad = new javax.swing.JTextField();
        boton_consultar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Consulta Mutual Ser");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Numero Identificacion: ");

        text_identidad.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        text_identidad.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        text_identidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                text_identidadKeyPressed(evt);
            }
        });

        boton_consultar.setText("CONSULTAR");
        boton_consultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_consultarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(text_identidad, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(141, 141, 141)
                        .addComponent(boton_consultar)))
                .addContainerGap(69, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(text_identidad, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(boton_consultar)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void boton_consultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_consultarActionPerformed
        consulta("" + text_identidad.getText());
    }//GEN-LAST:event_boton_consultarActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
             driver.close();
            try {
                Runtime.getRuntime().exec("TASKKILL /F /IM geckodriver.exe /IM firefox.exe");
            } catch (IOException ex) {
                Logger.getLogger(ConsultaMutual.class.getName()).log(Level.SEVERE, null, ex);
            }
    }//GEN-LAST:event_formWindowClosing

    private void text_identidadKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_text_identidadKeyPressed
      if( evt.getKeyCode()==KeyEvent.VK_ENTER){
             consulta("" + text_identidad.getText());
      }
    }//GEN-LAST:event_text_identidadKeyPressed
    protected void consulta(String identidad) {

        try {
            text_identidad.setText("");
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            // String identidad = JOptionPane.showInputDialog(null, "Numero identidad");
            driver.findElement(By.name("CodEmp")).sendKeys(identidad);
            WebElement captcha = driver.findElement(By.name("g-recaptcha-response"));
            executor.executeScript("arguments[0].setAttribute('style','display:enable;');", captcha);
            Thread.sleep(700);
            driver.findElement(By.id("g-recaptcha-response")).sendKeys("332432432edwed");
            Thread.sleep(700);
            driver.findElement(By.name("Submit")).click();
            Thread.sleep(700);
            String resultados = driver.findElement(By.xpath("/html/body/table[1]/tbody/tr[2]")).getText();
            JOptionPane.showMessageDialog(null, resultados);
            Thread.sleep(1000);
            WebElement atras =driver.findElement(By.linkText("Volver a la Consulta de Afiliados"));
            executor.executeScript("arguments[0].scrollIntoView();", atras);
            Thread.sleep(1000);
           executor.executeScript("arguments[0].click()", atras);
           
        } catch (NoSuchElementException ex) {
            try {
                //System.err.println(ex.getMessage());
                JOptionPane.showMessageDialog(null, "No existe en la base de datos");
                Thread.sleep(1000);
                driver.findElement(By.linkText("Volver a la Consulta de Afiliados")).click();
                //text_identidad.setText("");
            } catch (InterruptedException ex1) {
                Logger.getLogger(Ventana.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(ConsultaMutual.class.getName()).log(Level.SEVERE, null, ex);
        }
//        finally {
//            driver.close();
//            try {
//                Runtime.getRuntime().exec("TASKKILL /F /IM geckodriver.exe /IM firefox.exe");
//            } catch (IOException ex) {
//                Logger.getLogger(ConsultaMutual.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ventana().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton boton_consultar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField text_identidad;
    // End of variables declaration//GEN-END:variables
}
