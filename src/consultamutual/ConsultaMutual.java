/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consultamutual;

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


public class ConsultaMutual {

    /**
     * @param args the command line arguments
     */
    

    public static void main(String[] args) {
      try {
       
   System.setProperty("webdriver.gecko.driver", "C:\\Program Files (x86)\\Mozilla Firefox\\geckodriver.exe");
     FirefoxOptions options = new FirefoxOptions()
            .addPreference("network.proxy.type", 0)
            .addPreference("javascript.enabled", true)
            .addPreference("permissions.default.image", 3)
            .addPreference("permissions.default.stylesheet", 2)//.addArguments("--headless")
            ;
      WebDriver driver = new FirefoxDriver(options);
    JavascriptExecutor executor = (JavascriptExecutor) driver;
        driver.get("http://www.mutualser.com/app/consulta_afiliados_form.php");
             String identidad = JOptionPane.showInputDialog(null, "Numero identidad");
            driver.findElement(By.name("CodEmp")).sendKeys(identidad);
            WebElement captcha = driver.findElement(By.name("g-recaptcha-response"));
            executor.executeScript("arguments[0].setAttribute('style','display:enable;');", captcha);
            Thread.sleep(1000);
            driver.findElement(By.id("g-recaptcha-response")).sendKeys("332432432edwed");
            Thread.sleep(1000);
            driver.findElement(By.name("Submit")).click();
            Thread.sleep(1000);
            String resultados = driver.findElement(By.xpath("/html/body/table[1]/tbody/tr[2]")).getText();
            JOptionPane.showMessageDialog(null, resultados);

        } catch (NoSuchElementException ex) {
            //System.err.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "No existe en la base de datos");
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

}
