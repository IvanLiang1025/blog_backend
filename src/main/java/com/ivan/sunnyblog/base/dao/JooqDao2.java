//package com.ivan.sunnyblog.base.dao;
//
//import com.rtc.trustiics.base.SysException;
//import com.rtc.trustiics.base.model.Tables;
//import org.jooq.*;
//import org.jooq.impl.DSL;
//import org.jooq.impl.DataSourceConnectionProvider;
//import org.jooq.impl.DefaultConfiguration;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.jooq.SpringTransactionProvider;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;
//import org.springframework.stereotype.Component;
//import org.springframework.util.Assert;
//
//import javax.annotation.PostConstruct;
//import javax.annotation.PreDestroy;
//import javax.sql.DataSource;
//import java.lang.reflect.Field;
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//
//@Component
//public class JooqDao2 {
////    static MLog log = MLog.getLog(JooqDao.class);
//    public Logger logger = LoggerFactory.getLogger(JooqDao2.class);
//    final DSLContext create ;
//
//    @Autowired
//    public JooqDao2(DSLContext dslContext) {
//        this.create = dslContext;
//    }
////    @Autowired
//    private DataSource dataSource;
//
//
//    private Map<String,JooqTableDef> tableDefMap = new HashMap<>();
//
//    @PostConstruct
//    private void init(){
//        logger.info("initializing dbContext ...");
////        create = getDSLContext(dataSource);
//        initTableDefMap();
////        get(AdminUser.class,1);
////        get_r(AdminUser.class,1);
//        logger.info("dbContext is initialized.");
//    }
//    @PreDestroy
//    private void onDestroy(){
//        logger.info("destroying dbContext ...");
//        create.close();
////        DataSourceTypeManager.clear();
//
//        logger.info("dbContext is destroyed.");
//    }
//    private void initTableDefMap(){
//        Field[] fields = Tables.class.getDeclaredFields();
//        Arrays.stream(fields).forEach(field -> {
//            try {
//                field.setAccessible(true);
//                Table<?> table = (Table<?>)field.get(Tables.class) ;
//                JooqTableDef tableDef = new JooqTableDef(table);
//                tableDefMap.put(field.getType().getSimpleName(),tableDef);
//            } catch (IllegalAccessException e) {
//                logger.error("initTableDefMap", e);
//            }
//        });
//    }
//    public void transaction(TransactionalRunnable transactionalRunnable){
////        DataSourceTypeManager.set(DataSources.MASTER);
//        create.transaction(transactionalRunnable);
//    }
//    public <T> void save(T t){
////        DataSourceTypeManager.set(DataSources.MASTER);
//        JooqTableDef tableDef = tableDefMap.get(t.getClass().getSimpleName());
//        if(tableDef == null){
//            SysException.throwException("Save DB Error! No JooqTableDef found.");
//        }
//
//        tableDef.save(create, t);
//    }
//    public <T,R>  R saveAndReturn(T t){
////        DataSourceTypeManager.set(DataSources.MASTER);
//        JooqTableDef tableDef = tableDefMap.get(t.getClass().getSimpleName());
//        if(tableDef == null){
//            SysException.throwException("Save DB Error! No JooqTableDef found.");
//        }
//
//        return (R)tableDef.saveAndReturn(create, t);
//    }
//    public <T> void update(T t){
////        DataSourceTypeManager.set(DataSources.MASTER);
//        JooqTableDef tableDef = tableDefMap.get(t.getClass().getSimpleName());
//        if(tableDef == null){
//            SysException.throwException("Update DB Error! No JooqTableDef found.");
//        }
//
//        tableDef.update(create, t);
//    }
//    public <T> void delete(T t){
////        DataSourceTypeManager.set(DataSources.MASTER);
//        JooqTableDef tableDef = tableDefMap.get(t.getClass().getSimpleName());
//        if(tableDef == null){
//            SysException.throwException("Delete DB Error! No JooqTableDef found.");
//        }
//
//        tableDef.delete(create, t);
//    }
//    public <T> void deleteBy(T t,TableField tableField){
////        DataSourceTypeManager.set(DataSources.MASTER);
//        JooqTableDef tableDef = tableDefMap.get(t.getClass().getSimpleName());
//        if(tableDef == null){
//            SysException.throwException("Delete DB Error! No JooqTableDef found.");
//        }
//
//        tableDef.deleteByKey(create, t, tableField);
//    }
//    public <T> void deleteBy(T t,TableField tableField1,TableField tableField2){
////        DataSourceTypeManager.set(DataSources.MASTER);
//        JooqTableDef tableDef = tableDefMap.get(t.getClass().getSimpleName());
//        if(tableDef == null){
//            SysException.throwException("Delete DB Error! No JooqTableDef found.");
//        }
//
//        tableDef.deleteByKey(create, t, tableField1, tableField2);
//    }
//    public <T> void updateBy(T t,TableField tableField){
////        DataSourceTypeManager.set(DataSources.MASTER);
//        JooqTableDef tableDef = tableDefMap.get(t.getClass().getSimpleName());
//        if(tableDef == null){
//            SysException.throwException("updateBy DB Error! No JooqTableDef found.");
//        }
//
//        tableDef.updateByKey(create, t, tableField);
//    }
//    public <T> void updateBy(T t,TableField tableField1,TableField tableField2){
////        DataSourceTypeManager.set(DataSources.MASTER);
//        JooqTableDef tableDef = tableDefMap.get(t.getClass().getSimpleName());
//        if(tableDef == null){
//            SysException.throwException("updateBy DB Error! No JooqTableDef found.");
//        }
//
//        tableDef.updateByKey(create, t, tableField1, tableField2);
//    }
//    public DSLContext writeContext(){
////        DataSourceTypeManager.set(DataSources.MASTER);
//        return create;
//    }
//    public DSLContext readContext(){
////        DataSourceTypeManager.set(DataSources.SLAVE);
//        return create;
//    }
//
//    //--------query------------
//    public <R> R get(Class clazz, Object id){
////        DataSourceTypeManager.set(DataSources.MASTER);
//        JooqTableDef tableDef = tableDefMap.get(clazz.getSimpleName());
//        if(tableDef == null){
//            return null;
//        }
//        Record record = create.select().from(tableDef.table).where(tableDef.pkField.eq(id)).fetchOne();
//        if(record==null)return null;
//        return (R)record.into(clazz);
//    }
//    public <R> R get(Class clazz, Object id,boolean assertNotNull){
////        DataSourceTypeManager.set(DataSources.MASTER);
//        JooqTableDef tableDef = tableDefMap.get(clazz.getSimpleName());
//        if(tableDef == null){
//            return null;
//        }
//        Record record = create.select().from(tableDef.table).where(tableDef.pkField.eq(id)).fetchOne();
//        if(record==null)return null;
//        R o =  (R)record.into(clazz);
//        if(assertNotNull) {
//            Assert.isTrue(o != null, "get " + clazz.getSimpleName() + " error. id=" + id);
//        }
//        return o;
//    }
//    public <R> R get_r(Class clazz, Object id){
//        JooqTableDef tableDef = tableDefMap.get(clazz.getSimpleName());
//        if(tableDef == null){
//            return null;
//        }
////        DataSourceTypeManager.set(DataSources.SLAVE);
//        Record record = create.select().from(tableDef.table).where(tableDef.pkField.eq(id)).fetchOne();
//        if(record==null)return null;
//        return (R)record.into(clazz);
//    }
//    public <E> List<E> findBy(E instance){
//        JooqTableDef tableDef = tableDefMap.get(instance.getClass().getSimpleName());
//        if(tableDef == null){
//            return null;
//        }
//        return tableDef.queryByKey(create,instance);
//    }
//    private DSLContext getDSLContext(DataSource dataSource) {
//        TransactionAwareDataSourceProxy proxy = new TransactionAwareDataSourceProxy(dataSource);
//        DataSourceTransactionManager txMgr =  new DataSourceTransactionManager(dataSource);
//        Configuration configuration = new DefaultConfiguration()
//            .set(new DataSourceConnectionProvider(proxy))
//            .set(new SpringTransactionProvider(txMgr))
//            .set(SQLDialect.POSTGRES_10);
//        return DSL.using(configuration);
//    }
//}
