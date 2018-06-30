package comparator;

import entity.Section;

import java.util.Comparator;

public class SectionComparator implements Comparator<Section> {

    @Override
    public int compare(Section section1, Section section2) {
        double delta = section1.getPosition() - section2.getPosition();
        if (delta == 0) return 0;
        return (delta > 0) ? 1 : -1;
    }
}
