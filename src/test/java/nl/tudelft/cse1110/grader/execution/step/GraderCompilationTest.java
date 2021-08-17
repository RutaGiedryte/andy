package nl.tudelft.cse1110.grader.execution.step;

import org.junit.jupiter.api.Test;

import java.io.File;

import static nl.tudelft.cse1110.grader.execution.step.GraderIntegrationTestHelper.justCompilation;
import static nl.tudelft.cse1110.grader.util.FileUtils.concatenateDirectories;
import static nl.tudelft.cse1110.grader.execution.step.GraderIntegrationTestAssertions.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class GraderCompilationTest extends GraderIntegrationTestBase {

    @Test
    void compilationFails() {
        String result = run(justCompilation(), "ArrayUtilsIsSortedLibrary", "ArrayUtilsIsSortedWithCompilationError");
        assertThat(result)
                .has(compilationFailure())
                .has(compilationErrorOnLine(29))
                .has(compilationErrorType("not a statement"))
                .has(compilationErrorType("';' expected"))
                .doesNotHave(compilationErrorMoreTimes("cannot find symbol", 2));;
    }


    @Test
    void compilationOk() {
        String result = run(justCompilation(),  "ListUtilsLibrary", "ListUtilsCompilationSuccess");
        assertThat(result)
                .has(compilationSuccess());
    }


    @Test
    void compilationDifferentFailures() {
        String result = run(justCompilation(), "MathArraysLibrary","MathArraysDifferentCompilationErrors");
        assertThat(result)
                .has(compilationFailure())
                .has(compilationErrorOnLine(21))
                .has(compilationErrorOnLine(25))
                .has(compilationErrorOnLine(33))
                .has(GraderIntegrationTestAssertions.compilationErrorMoreTimes("cannot find symbol", 3));
    }

    @Test
    void highlightsGeneratedWhenCompilationFails(){
        run(justCompilation(), "ArrayUtilsIndexOfLibrary", "ArrayUtilsIndexOfImportListCommented");

        File highlights = new File(concatenateDirectories(workDir.toString(), "highlights.json"));
        assertThat(highlights).exists().isFile();

        String expected = "{\"Error List\":[{\"Line\":40,\"Message\":\"cannot find symbol\\n  symbol:   class List\\n  location: class delft.ArrayUtilsTests\",\"Color\":\"red\"},{\"Line\":69,\"Message\":\"cannot find symbol\\n  symbol:   class List\\n  location: class delft.ArrayUtilsTests\",\"Color\":\"red\"}]}";
        assertThat(highlights).hasContent(expected);
    }
}