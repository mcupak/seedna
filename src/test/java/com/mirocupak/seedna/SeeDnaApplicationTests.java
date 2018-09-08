package com.mirocupak.seedna;

import com.mirocupak.seedna.SeeDnaApplication;
import com.mirocupak.seedna.service.util.InputFormat;
import com.mirocupak.seedna.service.util.OutputFormat;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SeeDnaApplicationTests {

    private static final String EOL = System.getProperty("line.separator");
    private static final PrintStream SYSTEM_CONSOLE = System.out;

    private static ByteArrayOutputStream output;
    private static PrintStream testConsole;

    private void executeMain(List<String> args) {
        SeeDnaApplication.main(args.toArray(new String[0]));
    }

    private void assertApplicationStartedWithArgs(List<String> args) {
        assertThat(output.toString()).contains(SeeDnaApplication.APPLICATION_STARTED + args);
    }

    private void assertApplicationNotStartedWithArgs(List<String> args) {
        assertThat(output.toString()).doesNotContain(SeeDnaApplication.APPLICATION_STARTED + args);
    }

    @Before
    public void setUpConsole() {
        output = new ByteArrayOutputStream();
        testConsole = new PrintStream(output);
        System.setOut(testConsole);
    }

    @After
    public void resetConsole() {
        testConsole.close();
        System.setOut(SYSTEM_CONSOLE);
    }

    @Test
    public void testRunWithInputFormatParameterShort() {
        List<String> args = List.of("-if", InputFormat.SNP.toString());
        executeMain(args);

        assertApplicationStartedWithArgs(args);
    }

    @Test
    public void testRunWithInputFormatParameterLong() {
        List<String> args = List.of("--inputFormat", InputFormat.SNP.toString());
        executeMain(args);

        assertApplicationStartedWithArgs(args);
    }

    @Test
    public void testRunWithInvalidInputFormatParameter() {
        List<String> args = List.of("-if", InputFormat.SNP.toString());
        executeMain(args);

        assertApplicationNotStartedWithArgs(List.of("-if", "test_invalid"));
    }

    @Test
    public void testRunWithOutputFormatParameterShort() {
        List<String> args = List.of("-of", OutputFormat.PNG.toString());
        executeMain(args);

        assertApplicationStartedWithArgs(args);
    }

    @Test
    public void testRunWithOutputFormatParameterLong() {
        List<String> args = List.of("--outputFormat", OutputFormat.PNG.toString());
        executeMain(args);

        assertApplicationStartedWithArgs(args);
    }

    @Test
    public void testRunWithInvalidOutputFormatParameter() {
        List<String> args = List.of("-of", OutputFormat.PNG.toString());
        executeMain(args);

        assertApplicationNotStartedWithArgs(List.of("-of", "test_invalid"));
    }

    @Test
    public void testRunWithInputPathParameterShort() {
        List<String> args = List.of("-i", "test");
        executeMain(args);

        assertApplicationStartedWithArgs(args);
    }

    @Test
    public void testRunWithInputPathParameterLong() {
        List<String> args = List.of("--inputPath", "test");
        executeMain(args);

        assertApplicationStartedWithArgs(args);
    }

    @Test
    public void testRunWithInvalidInputPathParameter() {
        List<String> args = List.of("-i", "test");
        executeMain(args);

        assertApplicationNotStartedWithArgs(List.of("-i", "test_invalid"));
    }

    @Test
    public void testRunWithOutputPathParameterShort() {
        List<String> args = List.of("-o", "test");
        executeMain(args);

        assertApplicationStartedWithArgs(args);
    }

    @Test
    public void testRunWithOutputPathParameterLong() {
        List<String> args = List.of("--outputPath", "test");
        executeMain(args);

        assertApplicationStartedWithArgs(args);
    }

    @Test
    public void testRunWithInvalidOutputPathParameter() {
        List<String> args = List.of("-o", "test");
        executeMain(args);

        assertApplicationNotStartedWithArgs(List.of("-o", "test_invalid"));
    }

}
