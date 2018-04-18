package com.example.olijefavour.med_manager;

/**
 * Created by Olije favour on 4/12/2018.
 */

public class MedicationDetails {

    private String mMedicationName;
    private String startTime;
    private String mDosage;

    public  MedicationDetails(String medicationName, String startTime){

        mMedicationName=medicationName;
        this.startTime = startTime;
    }

    public String getmMedicationName() {
        return mMedicationName;
    }

    public void setmMedicationName(String mMedicationName) {
        this.mMedicationName = mMedicationName;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getmDosage() {
        return mDosage;
    }

    public void setmDosage(String mDosage) {
        this.mDosage = mDosage;
    }

}
