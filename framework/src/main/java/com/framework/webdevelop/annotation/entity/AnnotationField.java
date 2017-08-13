package com.framework.webdevelop.annotation.entity;

/**
 * Author Jun
 * Email
 * Date   7/2/17
 * Time   12:13 AM
 */
public class AnnotationField {
    private String variableClassName;
    private String variableName;
    private String belongClassName;

    public String getBelongClassName() {
        return belongClassName;
    }
    public void setBelongClassName(String belongClassName) {
        this.belongClassName = belongClassName;
    }

    public String getVariableClassName() {
        return variableClassName;
    }
    public void setVariableClassName(String variableClassName) {
        this.variableClassName = variableClassName;
    }

    public String getVariableName() {
        return variableName;
    }
    public void setVariableName(String variableName) {
        this.variableName = variableName;
    }

    public static BeanBuilder builder(){
        return new BeanBuilder();
    }

    public static class BeanBuilder{
        private String variableClassName;
        private String variableName;
        private String belongClassName;

        public BeanBuilder variableClassName(String variableClassName){
            this.variableClassName = variableClassName;
            return this;
        }
        public BeanBuilder variableName(String variableName){
            this.variableName = variableName;
            return this;
        }
        public BeanBuilder belongClassName(String belongClassName){
            this.belongClassName = belongClassName;
            return this;
        }

        public AnnotationField build(){
            AnnotationField annotationField = new AnnotationField();
            annotationField.setBelongClassName(this.belongClassName);
            annotationField.setVariableClassName(this.variableClassName);
            annotationField.setVariableName(this.variableName);
            return annotationField;
        }
    }
}
