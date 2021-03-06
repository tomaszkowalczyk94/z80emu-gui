package org.tomaszkowalczyk94.gui.controller;

import com.google.inject.Inject;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.controlsfx.control.PopOver;
import org.tomaszkowalczyk94.gui.model.help.HelpPopOverService;
import org.tomaszkowalczyk94.gui.view.ValueFormatter;
import org.tomaszkowalczyk94.z80emu.core.Z80;
import org.tomaszkowalczyk94.z80emu.core.register.FlagRegManager;
import org.tomaszkowalczyk94.z80emu.core.register.RegisterBank;

import java.net.URL;
import java.util.ResourceBundle;

public class RegistersController implements Initializable{



    @Inject private ValueFormatter valueFormatter;
    @Inject private Z80 z80;
    @Inject private HelpPopOverService helpPopOverService;

    @FXML public TextField regA;
    @FXML public TextField regB;
    @FXML public TextField regC;
    @FXML public TextField regD;
    @FXML public TextField regE;
    @FXML public TextField regH;
    @FXML public TextField regL;
    @FXML public TextField regF;

    @FXML public TextField regAPrim;
    @FXML public TextField regBPrim;
    @FXML public TextField regCPrim;
    @FXML public TextField regDPrim;
    @FXML public TextField regEPrim;
    @FXML public TextField regHPrim;
    @FXML public TextField regLPrim;
    @FXML public TextField regFPrim;

    @FXML public TextField regPc;
    @FXML public TextField regSp;
    @FXML public TextField regIx;
    @FXML public TextField regIy;
    @FXML public TextField regI;
    @FXML public TextField regR;

    @FXML public CheckBox flagS;
    @FXML public CheckBox flagZ;
    @FXML public CheckBox flagX5;
    @FXML public CheckBox flagH;
    @FXML public CheckBox flagX3;
    @FXML public CheckBox flagP;
    @FXML public CheckBox flagN;
    @FXML public CheckBox flagC;

    @FXML public Label regILabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        popOverForIReg = helpPopOverService.createPopOverForIReg();
    }

    public void refreshRegs() {
        RegisterBank regs = z80.getRegs();

        regA.setText(valueFormatter.getUnsignedHex( regs.getA().getUnsignedValue(), 2));
        regB.setText(valueFormatter.getUnsignedHex( regs.getB().getUnsignedValue(), 2));
        regC.setText(valueFormatter.getUnsignedHex( regs.getC().getUnsignedValue(), 2));
        regD.setText(valueFormatter.getUnsignedHex( regs.getD().getUnsignedValue(), 2));
        regE.setText(valueFormatter.getUnsignedHex( regs.getE().getUnsignedValue(), 2));
        regH.setText(valueFormatter.getUnsignedHex( regs.getH().getUnsignedValue(), 2));
        regL.setText(valueFormatter.getUnsignedHex( regs.getL().getUnsignedValue(), 2));
        regF.setText(valueFormatter.getUnsignedHex( regs.getF().getUnsignedValue(), 2));

        regAPrim.setText(valueFormatter.getUnsignedHex( regs.getAlternativeRegisterSet().getA().getUnsignedValue(), 2));
        regBPrim.setText(valueFormatter.getUnsignedHex( regs.getAlternativeRegisterSet().getB().getUnsignedValue(), 2));
        regCPrim.setText(valueFormatter.getUnsignedHex( regs.getAlternativeRegisterSet().getC().getUnsignedValue(), 2));
        regDPrim.setText(valueFormatter.getUnsignedHex( regs.getAlternativeRegisterSet().getD().getUnsignedValue(), 2));
        regEPrim.setText(valueFormatter.getUnsignedHex( regs.getAlternativeRegisterSet().getE().getUnsignedValue(), 2));
        regHPrim.setText(valueFormatter.getUnsignedHex( regs.getAlternativeRegisterSet().getH().getUnsignedValue(), 2));
        regLPrim.setText(valueFormatter.getUnsignedHex( regs.getAlternativeRegisterSet().getL().getUnsignedValue(), 2));
        regFPrim.setText(valueFormatter.getUnsignedHex( regs.getAlternativeRegisterSet().getF().getUnsignedValue(), 2));

        regPc.setText(valueFormatter.getUnsignedHex( regs.getPc().getUnsignedValue(), 4));
        regSp.setText(valueFormatter.getUnsignedHex( regs.getSp().getUnsignedValue(), 4));
        regIx.setText(valueFormatter.getUnsignedHex( regs.getIx().getUnsignedValue(), 4));
        regIy.setText(valueFormatter.getUnsignedHex( regs.getIy().getUnsignedValue(), 4));
    }

    public void refreshFlags() {
        flagS.setSelected(z80.getRegs().getFlag(FlagRegManager.Flag.S));
        flagZ.setSelected(z80.getRegs().getFlag(FlagRegManager.Flag.Z));
        flagH.setSelected(z80.getRegs().getFlag(FlagRegManager.Flag.H));
        flagP.setSelected(z80.getRegs().getFlag(FlagRegManager.Flag.PV));
        flagN.setSelected(z80.getRegs().getFlag(FlagRegManager.Flag.N));
        flagC.setSelected(z80.getRegs().getFlag(FlagRegManager.Flag.C));
    }

    PopOver popOverForIReg;


    public void onMouseEntered(MouseEvent mouseEvent) {
        System.out.println("mouse entered");
        popOverForIReg.show(regILabel);
    }

    public void onMouseExited(MouseEvent mouseEvent) {
        System.out.println("mouse exited");
        popOverForIReg.hide();
    }


}
