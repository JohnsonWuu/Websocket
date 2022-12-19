package com.controller;

import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public enum ExceptionRecordNumberGenerator {


    RETURN_TO_TOTE_RECORD {
        public List<String> getRecordNumbers(int countOfRecordNumber) {

            // System.out.println("Autowired TesterService: " + testerService);

            //TODO find the newest record number in corresponding table
            //ExceptionRecordService  exceptionRecordService = SpringBeanProvider.getBean(ExceptionRecordService.class);
            //System.out.println("exceptionRecordService,queryAllTester: " + exceptionRecordService.queryAllTester());

            //current record number from DB
            //Situation 1
            //String newestFormat1 = null;

            //current record number from DB
            //Situation 2
            //String newestFormat1 = "RTT2211000011";

            //Situation 3
            //String newestFormat1 = "RTT2211000011";

            List<String> recordNumbers;
//            if(newestFormat1 == null)
//            {
            recordNumbers = generateFirstTimeRecordNumbers(RETURN_TO_TOTE_RECORD_PREFIX,countOfRecordNumber);
//            }else
//            {
//                recordNumbers =  generateRecordNumbers(RETURN_TO_TOTE_RECORD_PREFIX,newestFormat1,countOfRecordNumber);
//            }


            System.out.println("recordNumbers: " + recordNumbers);

            return recordNumbers;
        }
    },
    LOST_RECORD {
        public List<String> getRecordNumbers(int countOfRecordNumber) {

            //current record number from DB
            //Situation 1
            String newestFormat2 = null;

            //Situation 2
            //current record number from DB
            //String newestFormat2 = "LT2201000109";

            //String recordNumber = LOST_RECORD_PREFIX + generatePostfixNumber(newestFormat2);

            List<String> recordNumbers;
            if(newestFormat2 == null)
            {
                recordNumbers = generateFirstTimeRecordNumbers(LOST_RECORD_PREFIX,countOfRecordNumber);
            }else
            {
                recordNumbers =  generateRecordNumbers(LOST_RECORD_PREFIX,newestFormat2,countOfRecordNumber);
            }

            return recordNumbers;
        }
    };
//    FOUND_RECORD {
//        public String getRecordNumbers(int countOfRecordNumber) {
//
//            //current record number from DB
//            String newestFormat1 = "FD2211000101";
//
//            String recordNumber = FOUND_RECORD_PREFIX + generatePostfixNumber(newestFormat1);
//
//            return recordNumber;
//        }
//    },
//    INVENTORY_TOTE_DAMAGE_RECORD {
//        public String getRecordNumbers(int countOfRecordNumber) {
//
//            String newestFormat1 = "ITD2211000001";
//            String recordNumber = INVENTORY_TOTE_DAMAGE_RECORD_PREFIX + generatePostfixNumber(newestFormat1);
//            return recordNumber;
//        }
//    },
//    DIRTY_TOTE_HANDLING {
//        public String getRecordNumbers(int countOfRecordNumber) {
//
//            String newestFormat1 = "DTH2211000001";
//            String recordNumber = DIRTY_TOTE_HANDLING_RECORD_PREFIX + generatePostfixNumber(newestFormat1);
//            return recordNumber;
//        }
//    },
//    SKU_DAMAGE_RECORD {
//        public String getRecordNumbers(int countOfRecordNumber) {
//            String newestFormat1 = "SD2211000001";
//            String recordNumber = SKU_DAMAGE_RECORD_PREFIX + generatePostfixNumber(newestFormat1);
//            return recordNumber;
//        }
//    },
//    INCIDENT_RECORD {
//        public String getRecordNumbers(int countOfRecordNumber) {
//
//            String newestFormat1 = "IC2211000001";
//            String recordNumber = INCIDENT_RECORD_PREFIX + generatePostfixNumber(newestFormat1);
//            return recordNumber;
//        }
//    },
//    DISCREPANCY_RECORD {
//        public String getRecordNumbers(int countOfRecordNumber) {
//
//            String newestFormat1 = "DS2211000001";
//            String recordNumber = DISCREPANCY_RECORD_PREFIX + generatePostfixNumber(newestFormat1);
//            return recordNumber;
//        }
//    };

    private static final String RETURN_TO_TOTE_RECORD_PREFIX = "RTT";
    private static final String LOST_RECORD_PREFIX = "LT";
    private static final String FOUND_RECORD_PREFIX = "FD";
    private static final String INVENTORY_TOTE_DAMAGE_RECORD_PREFIX = "ITD";
    private static final String DIRTY_TOTE_HANDLING_RECORD_PREFIX = "DTH";
    private static final String SKU_DAMAGE_RECORD_PREFIX = "SD";
    private static final String INCIDENT_RECORD_PREFIX = "IC";
    private static final String DISCREPANCY_RECORD_PREFIX = "DS";

    private static final int RESET_RECORD_NUMBER = 1;


    private static List<String> generateFirstTimeRecordNumbers(String preFixStr,int countOfRecordNumber) {
        List<String> recordNumbersResult = new ArrayList<String>();

        LocalDate localDate = new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int currentYear = localDate.getYear() % 100;
        int currentMonth = localDate.getMonthValue();

        for(int i = 0 ; i < countOfRecordNumber ; i++)
        {
            StringBuilder stringBuilder = new StringBuilder();

            String newRecordNumber = String.format("%06d", (RESET_RECORD_NUMBER + i));
            String formatCurrentMonth = String.format("%02d", currentMonth);

            stringBuilder.append(preFixStr).append(currentYear).append(formatCurrentMonth).append(newRecordNumber);

            recordNumbersResult.add(stringBuilder.toString());
        }

        return recordNumbersResult;
    }

    private static List<String> generateRecordNumbers(String preFixStr , String currentRecordNumber, int countOfRecordNumber) {
        List<String> recordNumbersResult = new ArrayList<String>();

        LocalDate localDate = new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int currentYear = localDate.getYear() % 100;
        int currentMonth = localDate.getMonthValue();

        System.out.println("currentYear: " + currentYear + " ,currentMonth: " + currentMonth);

        //Remove all non-numeric characters
        String postProcessCurrentRecordNumber = currentRecordNumber.replaceAll("[^\\d]", "");
        System.out.println("postProcessCurrentRecordNumber: " + postProcessCurrentRecordNumber);

        int recordNumberFromDB = Integer.valueOf(postProcessCurrentRecordNumber.substring(4));
        System.out.println("recordNumberFromDB: " + recordNumberFromDB);

        int yearFromDB = Integer.valueOf(postProcessCurrentRecordNumber.substring(0,2));
        int monthFromDB = Integer.valueOf(postProcessCurrentRecordNumber.substring(2,4));
        //System.out.println("yearFromDB: " + yearFromDB + " ,monthFromDB: " + monthFromDB);


        for(int i = 0 ; i < countOfRecordNumber ; i++)
        {
            StringBuilder stringBuilder = new StringBuilder();
            //String formatRecordNumberFromDB = "";
            //String formatCurrentMonth = "";
            if(currentYear == yearFromDB && currentMonth == monthFromDB )
            {
                //Use the current record number and add one for current record number
                String formatRecordNumberFromDB = String.format("%06d", (recordNumberFromDB + (i+1)));
                stringBuilder.append(preFixStr).append(yearFromDB).append(monthFromDB).append(formatRecordNumberFromDB);
            }else
            {
                String formatCurrentMonth = String.format("%02d", currentMonth);
                String newRecordNumber = String.format("%06d", (RESET_RECORD_NUMBER + i));
                stringBuilder.append(preFixStr).append(currentYear).append(formatCurrentMonth).append(newRecordNumber);
            }


            recordNumbersResult.add(stringBuilder.toString());
            System.out.println( "postfix result: " + stringBuilder);
        }


        return recordNumbersResult;
    }

    public abstract List<String> getRecordNumbers(int countOfRecordNumber);

}
