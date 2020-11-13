/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.AdminMenu4;

import model.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class AddVoucherMainScreen implements ActionListener{
    JFrame voucherMainFrame = new JFrame();
    
    
    public AddVoucherMainScreen(){
        
    }
    @Override
    public void actionPerformed(ActionEvent e){
        String command = e.getActionCommand();
        switch(command){
            case "Add":
                break;
        }
    }
}
