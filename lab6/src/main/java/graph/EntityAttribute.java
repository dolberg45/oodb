package graph;

public class EntityAttribute { //Атрибут сущности
    private String attributeName; //Имя аттрибута
    private String attributeType; //Тип аттрибута

    public EntityAttribute(String attributeName, String attributeType) {
        this.attributeName = attributeName;
        this.attributeType = attributeType;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    public String getAttributeType() {
        return attributeType;
    }

    public void setAttributeType(String attributeType) {
        this.attributeType = attributeType;
    }
}
