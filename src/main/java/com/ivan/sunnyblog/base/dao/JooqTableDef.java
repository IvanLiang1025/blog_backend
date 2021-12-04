//package com.ivan.sunnyblog.base.dao;
//
////import com.rtc.trustiics.base.SysException;
////import com.rtc.trustiics.base.minero.log.MLog;
//import org.jooq.*;
//import org.springframework.util.Assert;
//
//import java.lang.reflect.Field;
//import java.util.*;
//
///**
// * author wiley
// */
//public class JooqTableDef {
////    private static MLog log = MLog.getLog(JooqTableDef.class);
//    Table<?> table = null;
//    List<TableField> tableFieldList = new ArrayList<TableField>();
//    Map<String,TableField> tableFieldMap = new HashMap<>();
//    TableField pkField = null;
//    public JooqTableDef(Table<?> table) {
//        this.table = table;
//        findTableFields();
//    }
//    private void findTableFields() {
//        //此处认为 所有业务表都有且只有一个PK，不然出错
//        Assert.isTrue(table.getPrimaryKey().getFields().size()==1);
//        pkField = table.getPrimaryKey().getFields().get(0);
//
//        Field[] fields = table.getClass().getDeclaredFields();
//
//        Arrays.stream(fields).forEach(field -> {
//            try {
//                if(field.getType().isAssignableFrom(TableField.class)) {
//                    field.setAccessible(true);
//                    TableField tableField = (TableField)field.get(table);
//                    if(!tableField.equals(pkField)){
//                        tableFieldList.add(tableField);
//                        tableFieldMap.put(tableField.getName(),tableField);
//                    }else{
//                        tableFieldMap.put(tableField.getName(),tableField);
//                    }
//
//                }
//            } catch (IllegalAccessException e) {
//                log.error(e);
//            }
//        });
//        //把主键加上
//
//    }
//    protected InsertSetMoreStep getSaveSetMoreStep(DSLContext dslContext,Object instance){
//        Field[] fields = instance.getClass().getDeclaredFields();
//        InsertSetStep setStep = dslContext.insertInto(table);
//        InsertSetMoreStep setMoreStep = null;
//        for(Field field:fields){
//            try {
//                if(!field.getName().equals("serialVersionUID")){
//                    field.setAccessible(true);
//                    Object fieldValue = field.get(instance) ;
//                    if(fieldValue!=null){
//                        TableField tableField = tableFieldMap.get(field.getName());
//                        if(setMoreStep == null) {
//                            setMoreStep = setStep.set(tableField, fieldValue);
//                        }else{
//                            setMoreStep = setMoreStep.set(tableField, fieldValue);
//                        }
//                    }
//
//                }
//
//            } catch (IllegalAccessException e) {
//                log.error(e);
//            }
//        }
//        return setMoreStep;
//    }
//    protected void save(DSLContext dslContext,Object instance){
//        InsertSetMoreStep setMoreStep = getSaveSetMoreStep(dslContext,instance);
//        if(setMoreStep != null){
//            setMoreStep.execute();
//        }else{
//            SysException.throwException("Insert DB Error! No Value can be inserted.");
//        }
//
//    }
//    protected <R> R saveAndReturn(DSLContext dslContext,Object instance){
//        InsertSetMoreStep setMoreStep = getSaveSetMoreStep(dslContext,instance);
//        if(setMoreStep != null){
//            Record record = setMoreStep.returning(pkField).fetchOne();
//            return (R)record.get(pkField);
//        }else{
//            SysException.throwException("Insert DB Error! No Value can be inserted.");
//            return null;
//        }
//    }
//    protected void update(DSLContext dslContext,Object instance){
//        //对象的pk值
//        Object pkValue = null;
//        Field[] fields = instance.getClass().getDeclaredFields();
//        UpdateSetFirstStep setFirstStep = dslContext.update(table);
//        UpdateSetMoreStep setMoreStep = null;
//        for(Field field:fields){
//            try {
//                if(!field.getName().equals("serialVersionUID")){
//                    field.setAccessible(true);
//                    Object fieldValue = field.get(instance) ;
//                    if(field.getName().equals(pkField.getName())){
//                        //pk value
//                        if(fieldValue == null){
//                            SysException.throwException("Update DB Error! Empty PKey Value.");
//                        }
//                        pkValue = fieldValue;
//                    }else {
//                        if (fieldValue != null) {
//                            TableField tableField = tableFieldMap.get(field.getName());
//                            if (setMoreStep == null) {
//                                setMoreStep = setFirstStep.set(tableField, fieldValue);
//                            }
//                            else {
//                                setMoreStep = setMoreStep.set(tableField, fieldValue);
//                            }
//                        }
//                    }
//
//                }
//
//            } catch (IllegalAccessException e) {
//                log.error(e);
//            }
//        }
//        if(setMoreStep != null){
//            setMoreStep.where(pkField.eq(pkValue)).execute();
//        }else{
////            SysException.throwException("Update DB Error! No Value can be updated.");
//            log.warn("Update DB Error! No Value can be updated. {}",table);
//        }
//
//    }
//    protected void delete(DSLContext dslContext,Object instance){
//        //对象的pk值
//        Object pkValue = null;
//        Field[] fields = instance.getClass().getDeclaredFields();
//        for(Field field:fields){
//            try {
//                if(!field.getName().equals("serialVersionUID")){
//                    field.setAccessible(true);
//                    Object fieldValue = field.get(instance) ;
//                    if(field.getName().equals(pkField.getName())){
//                        //pk value
//                        if(fieldValue == null){
//                            SysException.throwException("Delete DB Error! Empty PKey Value.");
//                        }
//                        pkValue = fieldValue;
//                        break;
//                    }else {
//
//                    }
//
//                }
//
//            } catch (IllegalAccessException e) {
//                log.error(e);
//            }
//        }
//        dslContext.delete(table).where(pkField.eq(pkValue)).execute();
//
//    }
//    protected void deleteByKey(DSLContext dslContext,Object instance,TableField keyField){
//        //对象的pk值
//        Object pkValue = null;
//        Field[] fields = instance.getClass().getDeclaredFields();
//        for(Field field:fields){
//            try {
//                if(!field.getName().equals("serialVersionUID")){
//                    field.setAccessible(true);
//                    Object fieldValue = field.get(instance) ;
//                    if(field.getName().equals(keyField.getName())){
//                        //pk value
//                        if(fieldValue == null){
//                            SysException.throwException("Delete DB Error! Empty TargetKey Value.");
//                        }
//                        pkValue = fieldValue;
//                        break;
//                    }else {
//
//                    }
//
//                }
//
//            } catch (IllegalAccessException e) {
//                log.error(e);
//            }
//        }
//        dslContext.delete(table).where(keyField.eq(pkValue)).execute();
//
//    }
//    protected void deleteByKey(DSLContext dslContext,Object instance,TableField keyField1,TableField keyField2){
//        //对象的pk值
//        Object keyValue1 = null;
//        Object keyValue2 = null;
//        Field[] fields = instance.getClass().getDeclaredFields();
//        for(Field field:fields){
//            try {
//                if(!field.getName().equals("serialVersionUID")){
//                    field.setAccessible(true);
//                    Object fieldValue = field.get(instance) ;
//                    if(field.getName().equals(keyField1.getName())){
//                        if(fieldValue == null){
//                            SysException.throwException("Delete DB Error! Empty TargetKey Value.");
//                        }
//                        keyValue1 = fieldValue;
//                    }else if(field.getName().equals(keyField2.getName())){
//                        if(fieldValue == null){
//                            SysException.throwException("Delete DB Error! Empty TargetKey Value.");
//                        }
//                        keyValue2 = fieldValue;
//                    }
//
//                }
//
//            } catch (IllegalAccessException e) {
//                log.error(e);
//            }
//        }
//        dslContext.delete(table).where(keyField1.eq(keyValue1)).and(keyField2.eq(keyValue2)).execute();
//
//    }
//    //根据指定的条件查询后更新
//    protected void updateByKey(DSLContext dslContext,Object instance,TableField keyField){
//        //对象的pk值
//        Object keyValue = null;
//        Field[] fields = instance.getClass().getDeclaredFields();
//        UpdateSetFirstStep setFirstStep = dslContext.update(table);
//        UpdateSetMoreStep setMoreStep = null;
//        for(Field field:fields){
//            try {
//                if(!field.getName().equals("serialVersionUID")){
//                    field.setAccessible(true);
//                    Object fieldValue = field.get(instance) ;
//                    if(field.getName().equals(keyField.getName())){
//                        //pk value
//                        if(fieldValue == null){
//                            SysException.throwException("Update DB Error! Empty TargetKey Value.");
//                        }
//                        keyValue = fieldValue;
//
//                    }
//                    else if(field.getName().equals(pkField.getName())){
//                        //ignore
//                    }else {
//                        if (fieldValue != null) {
//                            TableField tableField = tableFieldMap.get(field.getName());
//                            if (setMoreStep == null) {
//                                setMoreStep = setFirstStep.set(tableField, fieldValue);
//                            }
//                            else {
//                                setMoreStep = setMoreStep.set(tableField, fieldValue);
//                            }
//                        }
//                    }
//
//                }
//
//            } catch (IllegalAccessException e) {
//                log.error(e);
//            }
//        }
//        if(setMoreStep != null){
//            setMoreStep.where(keyField.eq(keyValue)).execute();
//        }else{
//            SysException.throwException("Update DB Error! No Value can be updated.");
////            log.warn("Update DB Error! No Value can be updated. {}",table);
//        }
//
//    }
//    //根据指定的条件查询后更新
//    protected void updateByKey(DSLContext dslContext,Object instance,TableField keyField1,TableField keyField2){
//        //对象的pk值
//        Object keyValue1 = null;
//        Object keyValue2 = null;
//        Field[] fields = instance.getClass().getDeclaredFields();
//        UpdateSetFirstStep setFirstStep = dslContext.update(table);
//        UpdateSetMoreStep setMoreStep = null;
//        for(Field field:fields){
//            try {
//                if(!field.getName().equals("serialVersionUID")){
//                    field.setAccessible(true);
//                    Object fieldValue = field.get(instance) ;
//                    if(field.getName().equals(keyField1.getName())){
//                        if(fieldValue == null){
//                            SysException.throwException("Delete DB Error! Empty TargetKey Value.");
//                        }
//                        keyValue1 = fieldValue;
//                    }else if(field.getName().equals(keyField2.getName())){
//                        if(fieldValue == null){
//                            SysException.throwException("Delete DB Error! Empty TargetKey Value.");
//                        }
//                        keyValue2 = fieldValue;
//                    }
//                    else if(field.getName().equals(pkField.getName())){
//                        //ignore
//                    }else {
//                        if (fieldValue != null) {
//                            TableField tableField = tableFieldMap.get(field.getName());
//                            if (setMoreStep == null) {
//                                setMoreStep = setFirstStep.set(tableField, fieldValue);
//                            }
//                            else {
//                                setMoreStep = setMoreStep.set(tableField, fieldValue);
//                            }
//                        }
//                    }
//
//                }
//
//            } catch (IllegalAccessException e) {
//                log.error(e);
//            }
//        }
//        if(setMoreStep != null){
//            setMoreStep.where(keyField1.eq(keyValue1)).and(keyField2.eq(keyValue2)).execute();
//        }else{
////            SysException.throwException("Update DB Error! No Value can be updated.");
//            log.warn("Update DB Error! No Value can be updated. {}",table);
//        }
//
//    }
//    //单表根据条件查询某表，传入的对象中，不为空的字段自动为条件, 不算PK的查询
//    protected <E> List<E> queryByKey(DSLContext dslContext,E instance){
//        SelectJoinStep joinStep = dslContext.select().from(table);
//
//        SelectConditionStep conditionStep = null;
//
//        Field[] fields = instance.getClass().getDeclaredFields();
//        for(Field field:fields){
//            try {
//                if(!field.getName().equals("serialVersionUID")){
//                    field.setAccessible(true);
//                    Object fieldValue = field.get(instance) ;
//                    if(fieldValue != null){//不为空即为条件
//                        //获得tableField
//                        TableField tableField =tableFieldMap.get(field.getName());
//                        if(tableField != null){
//                            if(conditionStep==null){
//                                conditionStep=joinStep.where(tableField.eq(fieldValue));
//                            }else{
//                                conditionStep.and(tableField.eq(fieldValue));
//                            }
//                        }
//
//                    }else {
//
//                    }
//
//                }
//
//            } catch (IllegalAccessException e) {
//                log.error(e);
//            }
//        }
//        if(conditionStep == null){
//            SysException.throwException("Query DB Error! Empty TargetKey Value.");
//        }
//        return conditionStep.fetchInto(instance.getClass());
//
//
//    }
//    public static void main(String[] args){
//
//    }
//}
