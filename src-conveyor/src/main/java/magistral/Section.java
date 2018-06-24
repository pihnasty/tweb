package magistral;

import org.w3c.dom.NodeList;

import java.util.function.ToDoubleFunction;

public class Section  {
    private Double position;
    private ToDoubleFunction speed;
    private Bunker bunker;
    private String name;

    private NodeList childNodes;
    private NodeList ParentNodes;

    public Section(String name, Double position, ToDoubleFunction speed, Bunker bunker,  NodeList childNodes, NodeList parentNodes) {
        this.position = position;
        this.speed = speed;
        this.bunker = bunker;
        this.name = name;
        this.childNodes = childNodes;
        ParentNodes = parentNodes;
    }

    public void setPositionBunkerSpeed(String name, Double position, Bunker bunker, ToDoubleFunction speed) {
        this.position = position;
        this.bunker = bunker;
        this.speed = speed;
    }

    public NodeList getChildNodes() {
        return childNodes;
    }

    public void setChildNodes(NodeList childNodes) {
        this.childNodes = childNodes;
    }

    public NodeList getParentNodes() {
        return ParentNodes;
    }

    public void setParentNodes(NodeList parentNodes) {
        ParentNodes = parentNodes;
    }
}
