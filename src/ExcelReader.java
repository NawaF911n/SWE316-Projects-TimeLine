import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.apache.poi.ss.usermodel.*;

public class ExcelReader {

    public static void read(){
//        String baseDirectory = System.getProperty("user.dir");
        String ProjectExcel = "D:\\SWE316-PATH\\Projects.xls";
        String StageExcel = "D:\\SWE316-PATH\\Stages.xls";
        String StageDetExcel = "D:\\SWE316-PATH\\Stages_Detailed.xls";

//        System.out.println("Working Directory = " + baseDirectory);
//        System.out.println("excelFilePath " + excelFilePath);
        try {
            FileInputStream inputStreamPR = new FileInputStream(new File(ProjectExcel));
            FileInputStream inputStreamST = new FileInputStream(new File(StageExcel));
            FileInputStream inputStreamSTD = new FileInputStream(new File(StageDetExcel));

            Workbook wbProject = WorkbookFactory.create(inputStreamPR);
            Workbook wbStage = WorkbookFactory.create(inputStreamST);
            Workbook wbStageDet = WorkbookFactory.create(inputStreamSTD);

            Sheet Project_sheet = wbProject.getSheetAt(0);
            Sheet Stage_sheet = wbStage.getSheetAt(0);
            Sheet StageDet_sheet = wbStageDet.getSheetAt(0);
            //int rows = PR_sheet.getLastRowNum();

            ProjectsCollection projectsCollection = ProjectsCollection.getInstance();

            int stageRowRef = 1;
            // loop for projects
            for (Row row : Project_sheet) {
                if (row.getRowNum() == 0)
                    continue;
                String NodeID = row.getCell(0).getStringCellValue(),
                        projectID = row.getCell(1).getStringCellValue();
                int stageNum = (int) row.getCell(2).getNumericCellValue();

                Project project = new Project(projectID,stageNum);


                int rows = Stage_sheet.getLastRowNum();
                // loop for stages
                for (int i = stageRowRef; i < rows; i++){
                    Row stage_row = Stage_sheet.getRow(i);
                    Row stageDetailed_row = StageDet_sheet.getRow(i);

                    if(!stage_row.getCell(0).getStringCellValue().equals(NodeID)){
                        break;
                    }

                    Date date = stageDetailed_row.getCell(2).getDateCellValue();
                    ////LocalDate localDate = LocalDate.of(date.getYear(), date.getMonth(), date.getDay());
  
                    LocalDate localDate = Instant.ofEpochMilli(date.getTime())
                    	      .atZone(ZoneId.systemDefault())
                    	      .toLocalDate();

                    int docNum = (int) stageDetailed_row.getCell(1).getNumericCellValue();
                    int newVal = (int )stage_row.getCell(5).getNumericCellValue();
                    int oldVal ;
                    if (stage_row.getCell(6) == null ){
                        oldVal = 0;
                    }else{
                        oldVal = (int )stage_row.getCell(6).getNumericCellValue();
                    }
                    StageData stage = new StageData(docNum,newVal,oldVal,localDate);

                    project.getStagesCollection().add(stage);



                    stageRowRef++;
                }

                projectsCollection.add(project);

//                    CellReference cellRef = new CellReference(row.getRowNum(), cell.getColumnIndex());
//                    String text = formatter.formatCellValue(cell);
//                    System.out.println(cellRef.formatAsString() + " : <" + text + ">     " );

            }
        } catch (IOException e) {
            // TODO: handle exception
            System.out.println(e.toString());
        }
    }
}