import java.util.ArrayList;

public class AirportSection {

    private String sectionName;
    private ArrayList<AirportStuff> sectionStuff;

    public AirportSection(String sectionName) {
        this.sectionName = sectionName;
        this.sectionStuff = new ArrayList<AirportStuff>();
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public String getSectionName() {
        return this.sectionName;
    }

    public ArrayList<AirportStuff> getSectionStuff() {
        return this.sectionStuff;
    }
}
