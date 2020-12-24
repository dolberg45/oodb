package graph;

public class Edge { //Ребро
    private EntityNode nodeSource;
    private EntityNode nodeTarget;
    private RelationType relationType;

    public Edge(EntityNode nodeSource, EntityNode nodeTarget, RelationType relationType) {
        this.nodeSource = nodeSource; //Узел источник
        this.nodeTarget = nodeTarget; //Узел целевой
        this.relationType = relationType; //Тип отношения
    }

    public EntityNode getNodeSource() {
        return nodeSource;
    }

    public void setNodeSource(EntityNode nodeSource) {
        this.nodeSource = nodeSource;
    }

    public EntityNode getNodeTarget() {
        return nodeTarget;
    }

    public void setNodeTarget(EntityNode nodeTarget) {
        this.nodeTarget = nodeTarget;
    }

    public RelationType getRelationType() {
        return relationType;
    }

    public void setRelationType(RelationType relationType) {
        this.relationType = relationType;
    }

    @Override
    public String toString() {
        return "<edge id=\"" + nodeSource.getEntityName() + nodeTarget.getEntityName() + "\" directed=\"true\" source=\"" + nodeSource.getEntityName() + "\" target=\"" + nodeTarget.getEntityName() + "\"/>";
    }
}
