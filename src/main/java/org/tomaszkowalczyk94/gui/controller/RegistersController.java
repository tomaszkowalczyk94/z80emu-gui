package org.tomaszkowalczyk94.gui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.tomaszkowalczyk94.gui.model.Context;
import org.tomaszkowalczyk94.gui.model.ValueFormatter;
import org.tomaszkowalczyk94.z80emu.core.register.RegisterBank;

public class RegistersController {


    private Context context;

    @FXML public TextField regA;
    @FXML public TextField regB;
    @FXML public TextField regC;
    @FXML public TextField regD;
    @FXML public TextField regE;
    @FXML public TextField regH;
    @FXML public TextField regL;
    @FXML public TextField regAPrim;
    @FXML public TextField regBPrim;
    @FXML public TextField regCPrim;
    @FXML public TextField regDPrim;
    @FXML public TextField regEPrim;
    @FXML public TextField regHPrim;
    @FXML public TextField regLPrim;
    @FXML public TextField regPc;
    @FXML public TextField regSp;
    @FXML public TextField regIx;
    @FXML public TextField regIy;

    public void setContext(Context context) {
        this.context = context;
        refreshRegs();
    }

    public void refreshRegs() {

        ValueFormatter valueFormatter = context.getValueFormatter();
        RegisterBank regs = context.getZ80().getRegs();

        regA.setText(valueFormatter.getUnsignedHex( regs.getA().getUnsignedValue(), 2));
        regB.setText(valueFormatter.getUnsignedHex( regs.getB().getUnsignedValue(), 2));
        regC.setText(valueFormatter.getUnsignedHex( regs.getC().getUnsignedValue(), 2));
        regD.setText(valueFormatter.getUnsignedHex( regs.getD().getUnsignedValue(), 2));
        regE.setText(valueFormatter.getUnsignedHex( regs.getE().getUnsignedValue(), 2));
        regH.setText(valueFormatter.getUnsignedHex( regs.getH().getUnsignedValue(), 2));
        regL.setText(valueFormatter.getUnsignedHex( regs.getL().getUnsignedValue(), 2));

        regAPrim.setText(valueFormatter.getUnsignedHex( regs.getAlternativeRegisterSet().getA().getUnsignedValue(), 2));
        regBPrim.setText(valueFormatter.getUnsignedHex( regs.getAlternativeRegisterSet().getB().getUnsignedValue(), 2));
        regCPrim.setText(valueFormatter.getUnsignedHex( regs.getAlternativeRegisterSet().getC().getUnsignedValue(), 2));
        regDPrim.setText(valueFormatter.getUnsignedHex( regs.getAlternativeRegisterSet().getD().getUnsignedValue(), 2));
        regEPrim.setText(valueFormatter.getUnsignedHex( regs.getAlternativeRegisterSet().getE().getUnsignedValue(), 2));
        regHPrim.setText(valueFormatter.getUnsignedHex( regs.getAlternativeRegisterSet().getH().getUnsignedValue(), 2));
        regLPrim.setText(valueFormatter.getUnsignedHex( regs.getAlternativeRegisterSet().getL().getUnsignedValue(), 2));

        regPc.setText(valueFormatter.getUnsignedHex( regs.getPc().getUnsignedValue(), 4));
        regSp.setText(valueFormatter.getUnsignedHex( regs.getSp().getUnsignedValue(), 4));
        regIx.setText(valueFormatter.getUnsignedHex( regs.getIx().getUnsignedValue(), 4));
        regIy.setText(valueFormatter.getUnsignedHex( regs.getIy().getUnsignedValue(), 4));
    }

}
