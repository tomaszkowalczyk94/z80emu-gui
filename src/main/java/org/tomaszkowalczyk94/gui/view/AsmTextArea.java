package org.tomaszkowalczyk94.gui.view;

import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.LineNumberFactory;
import org.fxmisc.richtext.model.StyleSpans;
import org.fxmisc.richtext.model.StyleSpansBuilder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings("squid:MaximumInheritanceDepth")
public class AsmTextArea extends CodeArea {

    private static final String DEFAULT_CODE = "MAX\t    LD HL, 30h\n" +
            "        LD B, (HL)\n" +
            "\t\tLD A, 0\n" +
            "\t\tINC HL\n" +
            "\t\tLD (50h), HL\n" +
            "LOOP    CP (HL)\n" +
            "\t\tJR NC, NOSWITCH\n" +
            "\t\tLD A, (HL)\n" +
            "\t\tLD (50h), HL\n" +
            "NOSWITCH INC HL\n" +
            "\t\tDEC B\n" +
            "\t\tJR NZ, LOOP\n" +
            "\t\tRET";

    private static final String[] INSTRUCTIONS = new String[] {
            "adc", "add", "and", "bit", "call", "ccf", "cp", "cpd", "cpdr", "cpi", "cpir", "cpl",
            "daa", "dec", "di", "djnz", "ei", "ex", "exx", "halt", "im", "in", "inc", "ind", "indr",
            "ini", "inir", "jp", "jr", "ld", "ldd", "lddr", "ldi", "ldir", "neg", "nop", "or", "otdr",
            "otir", "out", "outd", "outi", "pop", "push", "res", "ret", "reti", "retn", "rl", "rla", "rlc",
            "rlca", "rld", "rr", "rra", "rrc", "rrca", "rrd", "rst", "sbc", "scf", "set", "sla", "sra",
            "srl", "sub", "xor",
    };

    private static final String STRING_PATTERN = "(\".*\")";
    private static final String INSTRUCTIONS_PATTERN = "\\b(" + String.join("|", INSTRUCTIONS) + ")\\b";
    private static final String COMMENTS_PATTERN = "(;.*)";



    private static final Pattern PATTERN = Pattern.compile(
            "(?<string>" + STRING_PATTERN + ")" +
            "|(?<instruction>" + INSTRUCTIONS_PATTERN + ")"+
            "|(?<comment>" + COMMENTS_PATTERN + ")"
    );

    public AsmTextArea() {
        super();
        this.setParagraphGraphicFactory(LineNumberFactory.get(this));

        this.textProperty().addListener((obs, oldText, newText) -> this.setStyleSpans(0, computeHighlighting(newText)));
        this.replaceText(0, 0, DEFAULT_CODE);
        this.setId("asmTextArea");
    }


    private static StyleSpans<Collection<String>> computeHighlighting(String text) {

        Matcher matcher = PATTERN.matcher(text);
        int lastEnd = 0;
        StyleSpansBuilder<Collection<String>> spansBuilder = new StyleSpansBuilder<>();

        List<String> matcherGroups = new ArrayList<>();
        Collections.addAll(matcherGroups, "instruction", "comment", "string");

        while(matcher.find()) {

            spansBuilder.add(Collections.emptyList(), matcher.start() - lastEnd);

            for(String matcherGroup: matcherGroups) {
                if(matcher.group(matcherGroup) != null) {
                    spansBuilder.add(Collections.singleton(matcherGroup), matcher.end() - matcher.start());
                }
            }

            lastEnd = matcher.end();
        }
        spansBuilder.add(Collections.emptyList(), text.length() - lastEnd);
        return spansBuilder.create();
    }
}
