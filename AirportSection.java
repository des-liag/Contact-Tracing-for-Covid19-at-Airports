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

    public void addSectionStuff(AirportStuff sectionStuff) {
        if(!this.sectionStuff.contains(sectionStuff)) {
            this.sectionStuff.add(sectionStuff);
        } else {
            System.out.println("This employee already works in this airport");
        }
    }

    public ArrayList<AirportStuff> getSectionStuff() {
        return this.sectionStuff;
    }
}
