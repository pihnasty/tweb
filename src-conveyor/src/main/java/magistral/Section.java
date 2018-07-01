package magistral;

import java.util.ArrayList;
import java.util.List;
import java.util.function.ToDoubleFunction;

public class Section implements entity.Section {
    private Double position;
    private ToDoubleFunction speed;
    private Bunker bunker;
    private ToDoubleFunction boundaryСonditions;
    private String name;

    private List<Section> childNodes = new ArrayList<>();
    private List<Section> parentNodes = new ArrayList<>();

    public Section(String name, Double position, ToDoubleFunction speed, Bunker bunker) {
        this.position = position;
        this.speed = speed;
        this.bunker = bunker;
        this.name = name;
    }

    public Section(String name, Double position, ToDoubleFunction speed, ToDoubleFunction boundaryСonditions, Bunker bunker) {
        this.position = position;
        this.speed = speed;
        this.bunker = bunker;
        this.boundaryСonditions = boundaryСonditions;
        this.name = name;
    }
    public Section(String name, Double position, ToDoubleFunction speed, Bunker bunker,  List<Section> childNodes, List<Section> parentNodes) {
        this.position = position;
        this.speed = speed;
        this.bunker = bunker;
        this.name = name;
        this.childNodes = childNodes;
        this.parentNodes = parentNodes;
    }

    public void setPositionBunkerSpeed(String name, Double position, Bunker bunker, ToDoubleFunction speed) {
        this.position = position;
        this.bunker = bunker;
        this.speed = speed;
    }

    public List<Section> getChildNodes() {
        return childNodes;
    }

    public void setChildNodes(List<Section> childNodes) {
        this.childNodes = childNodes;
    }

    public List<Section> getParentNodes() {
        return parentNodes;
    }

    public void setParentNodes(List<Section> parentNodes) {
        this.parentNodes = parentNodes;
    }

    public String getName() {
        return name;
    }

    @Override
    public double getPosition() {
        return position;
    }

    public ToDoubleFunction getSpeed() {
        return speed;
    }

    public ToDoubleFunction getBoundaryСonditions() {
        return boundaryСonditions;
    }

    public void setBoundaryСonditions(ToDoubleFunction boundaryСonditions) {
        this.boundaryСonditions = boundaryСonditions;
    }

    public Bunker getBunker() {
        return bunker;
    }

      public void setBunker(Bunker bunker) {
        this.bunker = bunker;
    }
}
