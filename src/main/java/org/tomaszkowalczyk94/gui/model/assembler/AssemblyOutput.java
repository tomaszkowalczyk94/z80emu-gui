package org.tomaszkowalczyk94.gui.model.assembler;

import lombok.Data;
import org.tomaszkowalczyk94.gui.view.ValueFormatter;
import org.tomaszkowalczyk94.xbit.XBit8;

import java.util.ArrayList;
import java.util.List;

@Data
public class AssemblyOutput {

    @Data
    public static class AssemblerLine {

        private ValueFormatter valueFormatter;

        private String instruction;
        private int lineNumber;
        private List<XBit8> bytes = new ArrayList<>();
        private int address;

        AssemblerLine(ValueFormatter valueFormatter) {
            this.valueFormatter = valueFormatter;
        }

        public String getAddressInTable() {
            return valueFormatter.getUnsignedHex(address, 4);
        }

        public String getBytesInTable() {
            StringBuilder stringBuilder = new StringBuilder();

            bytes.forEach(oneByte -> {
                stringBuilder.append(valueFormatter.getUnsignedHex(oneByte.getUnsignedValue(), 2));
                stringBuilder.append(" ");
            });

            return stringBuilder.toString();
        }

        public String getInstructionInTable() {
            return instruction;
        }
    }



    private List<AssemblerLine> lines = new ArrayList<>();

}
