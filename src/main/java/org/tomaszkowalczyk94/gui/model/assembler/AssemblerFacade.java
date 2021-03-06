package org.tomaszkowalczyk94.gui.model.assembler;

import com.google.inject.Inject;
import nl.grauw.glass.*;
import org.tomaszkowalczyk94.gui.view.ValueFormatter;
import org.tomaszkowalczyk94.xbit.XBit8;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
public class AssemblerFacade {

    @Inject private ValueFormatter valueFormatter;

    public AssemblyOutput assembly(String asmCode) throws AssemblerException{
        try {
            SourceBuilder sourceBuilder = new SourceBuilder(new ArrayList<>());
            Source source = sourceBuilder.parse(new StringReader(asmCode), null);

            ByteArrayOutputStream output = new ByteArrayOutputStream();

            source.assemble(output);
            return transform(source.getLines());
        } catch (IOException | AssemblyException e) {
            throw new AssemblerException(e);
        }
    }

    private AssemblyOutput transform(List<Line> lines) {
        AssemblyOutput assemblyOutput = new AssemblyOutput();

        lines.stream()
            .filter(line -> (line.getBytes().length != 0)) //ignore comments, labels, etc
            .forEach(lineFromGlassAssembler -> {
                AssemblyOutput.AssemblerLine assemblerLine = new AssemblyOutput.AssemblerLine(valueFormatter);
                assemblerLine.setAddress(lineFromGlassAssembler.getScope().getAddress());
                assemblerLine.setInstruction(generateInstructionString(lineFromGlassAssembler));
                assemblerLine.setLineNumber(lineFromGlassAssembler.getLineNumber());

                for(byte oneByte: lineFromGlassAssembler.getBytes()) {
                    assemblerLine.getBytes().add(XBit8.valueOfSigned(oneByte));
                }

                assemblyOutput.getLines().add(assemblerLine);
            });

        return assemblyOutput;
    }

    private String generateInstructionString(Line line) {
            if(line.getMnemonic() != null) {
                return line.getMnemonic() + (line.getArguments() != null ? " " + line.getArguments() : "");
            } else {
                return "";
            }
    }

}
