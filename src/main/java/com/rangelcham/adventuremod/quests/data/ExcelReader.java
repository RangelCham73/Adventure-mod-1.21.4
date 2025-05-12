package com.rangelcham.adventuremod.quests.data;

import com.rangelcham.adventuremod.quests.Quest;
import com.rangelcham.adventuremod.quests.QuestType;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class ExcelReader {

    private static final int NAME_COL = 0;  // Columna de nombre de la misión
    private static final int DESCRIPTION_COL = 1;  // Columna de descripción
    private static final int QUEST_TYPE_COL = 2;  // Columna de tipo de misión
    private static final int STEPS_COL = 3;  // Columna de pasos
    private static final int LEVEL_COL = 4;  // Columna de nivel
    private static final int MIN_REWARD_COL = 5;  // Columna de recompensa mínima
    private static final int MAX_REWARD_COL = 6;  // Columna de recompensa máxima
    private static final int DIFFICULTY_COL = 7;  // Columna de dificultad
    private static final int IS_COMPLETED_COL = 8;  // Columna de completado
    private static final int IS_ACTIVE_COL = 9;  // Columna de actividad

    public HashMap<Integer, Quest> readExcel() {
        String rutaArchivo = "Libro.xlsx"; // Ruta al archivo Excel
        HashMap<Integer, Quest> quests = new HashMap<>();

        try (FileInputStream fis = new FileInputStream(new File(rutaArchivo));
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0); // Primera hoja
            Iterator<Row> rowIterator = sheet.iterator();

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                // Salta la fila de encabezado si es necesario
                if (row.getRowNum() == 0) continue;

                quests.put(row.getRowNum(), new Quest(
                        getCellValue(row, NAME_COL),
                        getCellValue(row, DESCRIPTION_COL),
                        getQuestType((int) getCellNumericValue(row, QUEST_TYPE_COL)),
                        getQuestSteps(getCellValue(row, STEPS_COL)),
                        (int) getCellNumericValue(row, LEVEL_COL),
                        (int) getCellNumericValue(row, MIN_REWARD_COL),
                        (int) getCellNumericValue(row, MAX_REWARD_COL),
                        (int) getCellNumericValue(row, DIFFICULTY_COL),
                        getCellBooleanValue(row, IS_COMPLETED_COL),
                        getCellBooleanValue(row, IS_ACTIVE_COL)
                ));
            }
            return quests;

        } catch (IOException e) {
            System.err.println("Error leyendo el archivo: " + e.getMessage());
            return quests;
        }
    }

    private String getCellValue(Row row, int colIndex) {
        Cell cell = row.getCell(colIndex);
        return cell != null ? cell.toString() : "";
    }

    private double getCellNumericValue(Row row, int colIndex) {
        Cell cell = row.getCell(colIndex);
        return cell != null ? cell.getNumericCellValue() : 0;
    }

    private boolean getCellBooleanValue(Row row, int colIndex) {
        Cell cell = row.getCell(colIndex);
        return cell != null && cell.getBooleanCellValue();
    }

    public QuestType getQuestType(int id) {
        switch (id) {
            case 1: return QuestType.PRINCIPAL;
            case 2: return QuestType.SECUNDARY;
            case 3: return QuestType.HUNT;
            case 4: return QuestType.TRESURE;
            case 5: return QuestType.TASK;
            default: return QuestType.PRINCIPAL;
        }
    }

    public List<String> getQuestSteps(String steps) {
        if (steps == null || steps.isEmpty()) {
            return Collections.emptyList();
        }
        return Arrays.asList(steps.split(","));
    }
}
