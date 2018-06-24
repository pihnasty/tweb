package magistral;

import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;
import java.util.function.ToDoubleFunction;

public class Section  {
    private Double position;
    private ToDoubleFunction speed;
    private Bunker bunker;
    private String name;

    private List<Section> childNodes = new ArrayList<>();
    private List<Section> parentNodes = new ArrayList<>();

    public Section(String name, Double position, ToDoubleFunction speed, Bunker bunker) {
        this.position = position;
        this.speed = speed;
        this.bunker = bunker;
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

    public Double getPosition() {
        return position;
    }

    public ToDoubleFunction getSpeed() {
        return speed;
    }
}
