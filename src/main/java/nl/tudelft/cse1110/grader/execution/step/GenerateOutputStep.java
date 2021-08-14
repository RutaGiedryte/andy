package nl.tudelft.cse1110.grader.execution.step;

import nl.tudelft.cse1110.grader.config.Configuration;
import nl.tudelft.cse1110.grader.execution.ExecutionStep;
import nl.tudelft.cse1110.grader.result.ResultBuilder;
import nl.tudelft.cse1110.grader.util.FileUtils;

import java.io.FileWriter;
import java.io.IOException;

public class GenerateOutputStep implements ExecutionStep {

    @Override
    public void execute(Configuration cfg, ResultBuilder result){
        writeOutputFile(result);
        writeXLMFile(result);
    }

    private void writeOutputFile(ResultBuilder result) {
        try{
            FileWriter fw = new FileWriter(FileUtils.fileWithParentDirCreated(
                    "src/main/java/nl/tudelft/cse1110/grader/output", "stdout.txt"));

            fw.write(result.buildEndUserResult());
            fw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeXLMFile(ResultBuilder result) {
        try{
            FileWriter fw = new FileWriter(FileUtils.fileWithParentDirCreated(
                    "src/main/java/nl/tudelft/cse1110/grader/output", "results.xml"));

            fw.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<testsuites>\n\t<testsuite>\n");

            //At the moment, a compilation failure will result in a score of 0
            //but the file will be created normally as if "100 tests failed"

            int score = result.getFinalScore();
            String failed = "\t\t<testcase><failure></failure></testcase>\n";
            String passed = "\t\t<testcase/>\n";

            //score = -1 means compilation error
            if(score >= 0){
                fw.write(failed.repeat(100 - score));
                fw.write(passed.repeat(score));
            } else{
                fw.write(failed.repeat(100));
            }

            fw.write("\t</testsuite>\n</testsuites>\n");
            fw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
