package core.utils;

import org.apache.pdfbox.text.PDFTextStripperByArea;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import static core.utils.DriverManageUtils.getDriver;

public class FileUtils {

    public static File downloadsDir =
            new File(System.getProperty("user.dir") + File.separatorChar + "target" + File.separatorChar + "downloads");

    public static void deleteAllFiles() {
        for (File f: Objects.requireNonNull(downloadsDir.listFiles())) {
            f.delete();
        }
        new WebDriverWait(getDriver(), 60).until(d -> downloadsDir.listFiles().length == 0);
    }

    public static List<String> readPdf() throws IOException {
        new WebDriverWait(getDriver(), 60).until(d ->
                downloadsDir.listFiles().length > 0 && downloadsDir.listFiles()[0].exists());
        PDDocument document = PDDocument.load(Objects.requireNonNull(downloadsDir.listFiles())[0]);
        PDFTextStripperByArea stripper = new PDFTextStripperByArea();
        stripper.setSortByPosition(true);

        PDFTextStripper tStripper = new PDFTextStripper();

        String pdfFileInText = tStripper.getText(document);
        return Arrays.asList(pdfFileInText.split("\\r?\\n"));
    }
}
