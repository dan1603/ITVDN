package io.realm;


import android.annotation.TargetApi;
import android.os.Build;
import android.util.JsonReader;
import android.util.JsonToken;

import com.kalasnyk.denys.realmexample.models.TaskEntity;

import io.realm.exceptions.RealmMigrationNeededException;
import io.realm.internal.ColumnInfo;
import io.realm.internal.RealmObjectProxy;
import io.realm.internal.Row;
import io.realm.internal.SharedRealm;
import io.realm.internal.Table;
import io.realm.log.RealmLog;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

public class TaskRealmProxy extends TaskEntity
    implements RealmObjectProxy, TaskRealmProxyInterface {

    static final class TaskColumnInfo extends ColumnInfo
        implements Cloneable {

        public long idIndex;
        public long nameIndex;
        public long doneIndex;
        public long timestampIndex;

        TaskColumnInfo(String path, Table table) {
            final Map<String, Long> indicesMap = new HashMap<String, Long>(4);
            this.idIndex = getValidColumnIndex(path, table, "TaskEntity", "id");
            indicesMap.put("id", this.idIndex);
            this.nameIndex = getValidColumnIndex(path, table, "TaskEntity", "name");
            indicesMap.put("name", this.nameIndex);
            this.doneIndex = getValidColumnIndex(path, table, "TaskEntity", "done");
            indicesMap.put("done", this.doneIndex);
            this.timestampIndex = getValidColumnIndex(path, table, "TaskEntity", "timestamp");
            indicesMap.put("timestamp", this.timestampIndex);

            setIndicesMap(indicesMap);
        }

        @Override
        public final void copyColumnInfoFrom(ColumnInfo other) {
            final TaskColumnInfo otherInfo = (TaskColumnInfo) other;
            this.idIndex = otherInfo.idIndex;
            this.nameIndex = otherInfo.nameIndex;
            this.doneIndex = otherInfo.doneIndex;
            this.timestampIndex = otherInfo.timestampIndex;

            setIndicesMap(otherInfo.getIndicesMap());
        }

        @Override
        public final TaskColumnInfo clone() {
            return (TaskColumnInfo) super.clone();
        }

    }
    private TaskColumnInfo columnInfo;
    private ProxyState<TaskEntity> proxyState;
    private static final List<String> FIELD_NAMES;
    static {
        List<String> fieldNames = new ArrayList<String>();
        fieldNames.add("id");
        fieldNames.add("name");
        fieldNames.add("done");
        fieldNames.add("timestamp");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    TaskRealmProxy() {
        proxyState.setConstructionFinished();
    }

    @Override
    public void realm$injectObjectContext() {
        if (this.proxyState != null) {
            return;
        }
        final BaseRealm.RealmObjectContext context = BaseRealm.objectContext.get();
        this.columnInfo = (TaskColumnInfo) context.getColumnInfo();
        this.proxyState = new ProxyState<TaskEntity>(this);
        proxyState.setRealm$realm(context.getRealm());
        proxyState.setRow$realm(context.getRow());
        proxyState.setAcceptDefaultValue$realm(context.getAcceptDefaultValue());
        proxyState.setExcludeFields$realm(context.getExcludeFields());
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$id() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.idIndex);
    }

    @Override
    public void realmSet$id(String value) {
        if (proxyState.isUnderConstruction()) {
            // default value of the primary key is always ignored.
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        throw new io.realm.exceptions.RealmException("Primary key field 'id' cannot be changed after object was created.");
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$name() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.nameIndex);
    }

    @Override
    public void realmSet$name(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'name' to null.");
            }
            row.getTable().setString(columnInfo.nameIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            throw new IllegalArgumentException("Trying to set non-nullable field 'name' to null.");
        }
        proxyState.getRow$realm().setString(columnInfo.nameIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public boolean realmGet$done() {
        proxyState.getRealm$realm().checkIfValid();
        return (boolean) proxyState.getRow$realm().getBoolean(columnInfo.doneIndex);
    }

    @Override
    public void realmSet$done(boolean value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setBoolean(columnInfo.doneIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setBoolean(columnInfo.doneIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public long realmGet$timestamp() {
        proxyState.getRealm$realm().checkIfValid();
        return (long) proxyState.getRow$realm().getLong(columnInfo.timestampIndex);
    }

    @Override
    public void realmSet$timestamp(long value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setLong(columnInfo.timestampIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setLong(columnInfo.timestampIndex, value);
    }

    public static RealmObjectSchema createRealmObjectSchema(RealmSchema realmSchema) {
        if (!realmSchema.contains("TaskEntity")) {
            RealmObjectSchema realmObjectSchema = realmSchema.create("TaskEntity");
            realmObjectSchema.add("id", RealmFieldType.STRING, Property.PRIMARY_KEY, Property.INDEXED, Property.REQUIRED);
            realmObjectSchema.add("name", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
            realmObjectSchema.add("done", RealmFieldType.BOOLEAN, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
            realmObjectSchema.add("timestamp", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
            return realmObjectSchema;
        }
        return realmSchema.get("TaskEntity");
    }

    public static TaskColumnInfo validateTable(SharedRealm sharedRealm, boolean allowExtraColumns) {
        if (!sharedRealm.hasTable("class_Task")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "The 'TaskEntity' class is missing from the schema for this Realm.");
        }
        Table table = sharedRealm.getTable("class_Task");
        final long columnCount = table.getColumnCount();
        if (columnCount != 4) {
            if (columnCount < 4) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field count is less than expected - expected 4 but was " + columnCount);
            }
            if (allowExtraColumns) {
                RealmLog.debug("Field count is more than expected - expected 4 but was %1$d", columnCount);
            } else {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field count is more than expected - expected 4 but was " + columnCount);
            }
        }
        Map<String, RealmFieldType> columnTypes = new HashMap<String, RealmFieldType>();
        for (long i = 0; i < columnCount; i++) {
            columnTypes.put(table.getColumnName(i), table.getColumnType(i));
        }

        final TaskColumnInfo columnInfo = new TaskColumnInfo(sharedRealm.getPath(), table);

        if (!table.hasPrimaryKey()) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Primary key not defined for field 'id' in existing Realm file. @PrimaryKey was added.");
        } else {
            if (table.getPrimaryKey() != columnInfo.idIndex) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Primary Key annotation definition was changed, from field " + table.getColumnName(table.getPrimaryKey()) + " to field id");
            }
        }

        if (!columnTypes.containsKey("id")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'id' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
        }
        if (columnTypes.get("id") != RealmFieldType.STRING) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'String' for field 'id' in existing Realm file.");
        }
        if (table.isColumnNullable(columnInfo.idIndex) && table.findFirstNull(columnInfo.idIndex) != Table.NO_MATCH) {
            throw new IllegalStateException("Cannot migrate an object with null value in field 'id'. Either maintain the same type for primary key field 'id', or remove the object with null value before migration.");
        }
        if (!table.hasSearchIndex(table.getColumnIndex("id"))) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Index not defined for field 'id' in existing Realm file. Either set @Index or migrate using io.realm.internal.Table.removeSearchIndex().");
        }
        if (!columnTypes.containsKey("name")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'name' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
        }
        if (columnTypes.get("name") != RealmFieldType.STRING) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'String' for field 'name' in existing Realm file.");
        }
        if (table.isColumnNullable(columnInfo.nameIndex)) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'name' does support null values in the existing Realm file. Remove @Required or @PrimaryKey from field 'name' or migrate using RealmObjectSchema.setNullable().");
        }
        if (!columnTypes.containsKey("done")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'done' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
        }
        if (columnTypes.get("done") != RealmFieldType.BOOLEAN) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'boolean' for field 'done' in existing Realm file.");
        }
        if (table.isColumnNullable(columnInfo.doneIndex)) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'done' does support null values in the existing Realm file. Use corresponding boxed type for field 'done' or migrate using RealmObjectSchema.setNullable().");
        }
        if (!columnTypes.containsKey("timestamp")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'timestamp' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
        }
        if (columnTypes.get("timestamp") != RealmFieldType.INTEGER) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'long' for field 'timestamp' in existing Realm file.");
        }
        if (table.isColumnNullable(columnInfo.timestampIndex)) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'timestamp' does support null values in the existing Realm file. Use corresponding boxed type for field 'timestamp' or migrate using RealmObjectSchema.setNullable().");
        }

        return columnInfo;
    }

    public static String getTableName() {
        return "class_Task";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    @SuppressWarnings("cast")
    public static TaskEntity createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        final List<String> excludeFields = Collections.<String> emptyList();
        TaskEntity obj = null;
        if (update) {
            Table table = realm.getTable(TaskEntity.class);
            long pkColumnIndex = table.getPrimaryKey();
            long rowIndex = Table.NO_MATCH;
            if (!json.isNull("id")) {
                rowIndex = table.findFirstString(pkColumnIndex, json.getString("id"));
            }
            if (rowIndex != Table.NO_MATCH) {
                final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
                try {
                    objectContext.set(realm, table.getUncheckedRow(rowIndex), realm.schema.getColumnInfo(TaskEntity.class), false, Collections.<String> emptyList());
                    obj = new io.realm.TaskRealmProxy();
                } finally {
                    objectContext.clear();
                }
            }
        }
        if (obj == null) {
            if (json.has("id")) {
                if (json.isNull("id")) {
                    obj = (io.realm.TaskRealmProxy) realm.createObjectInternal(TaskEntity.class, null, true, excludeFields);
                } else {
                    obj = (io.realm.TaskRealmProxy) realm.createObjectInternal(TaskEntity.class, json.getString("id"), true, excludeFields);
                }
            } else {
                throw new IllegalArgumentException("JSON object doesn't have the primary key field 'id'.");
            }
        }
        if (json.has("name")) {
            if (json.isNull("name")) {
                ((TaskRealmProxyInterface) obj).realmSet$name(null);
            } else {
                ((TaskRealmProxyInterface) obj).realmSet$name((String) json.getString("name"));
            }
        }
        if (json.has("done")) {
            if (json.isNull("done")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'done' to null.");
            } else {
                ((TaskRealmProxyInterface) obj).realmSet$done((boolean) json.getBoolean("done"));
            }
        }
        if (json.has("timestamp")) {
            if (json.isNull("timestamp")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'timestamp' to null.");
            } else {
                ((TaskRealmProxyInterface) obj).realmSet$timestamp((long) json.getLong("timestamp"));
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static TaskEntity createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        boolean jsonHasPrimaryKey = false;
        TaskEntity obj = new TaskEntity();
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (false) {
            } else if (name.equals("id")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((TaskRealmProxyInterface) obj).realmSet$id(null);
                } else {
                    ((TaskRealmProxyInterface) obj).realmSet$id((String) reader.nextString());
                }
                jsonHasPrimaryKey = true;
            } else if (name.equals("name")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((TaskRealmProxyInterface) obj).realmSet$name(null);
                } else {
                    ((TaskRealmProxyInterface) obj).realmSet$name((String) reader.nextString());
                }
            } else if (name.equals("done")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'done' to null.");
                } else {
                    ((TaskRealmProxyInterface) obj).realmSet$done((boolean) reader.nextBoolean());
                }
            } else if (name.equals("timestamp")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'timestamp' to null.");
                } else {
                    ((TaskRealmProxyInterface) obj).realmSet$timestamp((long) reader.nextLong());
                }
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        if (!jsonHasPrimaryKey) {
            throw new IllegalArgumentException("JSON object doesn't have the primary key field 'id'.");
        }
        obj = realm.copyToRealm(obj);
        return obj;
    }

    public static TaskEntity copyOrUpdate(Realm realm, TaskEntity object, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().threadId != realm.threadId) {
            throw new IllegalArgumentException("Objects which belong to Realm instances in other threads cannot be copied into this Realm instance.");
        }
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return object;
        }
        final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
        RealmObjectProxy cachedRealmObject = cache.get(object);
        if (cachedRealmObject != null) {
            return (TaskEntity) cachedRealmObject;
        } else {
            TaskEntity realmObject = null;
            boolean canUpdate = update;
            if (canUpdate) {
                Table table = realm.getTable(TaskEntity.class);
                long pkColumnIndex = table.getPrimaryKey();
                long rowIndex = table.findFirstString(pkColumnIndex, ((TaskRealmProxyInterface) object).realmGet$id());
                if (rowIndex != Table.NO_MATCH) {
                    try {
                        objectContext.set(realm, table.getUncheckedRow(rowIndex), realm.schema.getColumnInfo(TaskEntity.class), false, Collections.<String> emptyList());
                        realmObject = new io.realm.TaskRealmProxy();
                        cache.put(object, (RealmObjectProxy) realmObject);
                    } finally {
                        objectContext.clear();
                    }
                } else {
                    canUpdate = false;
                }
            }

            if (canUpdate) {
                return update(realm, realmObject, object, cache);
            } else {
                return copy(realm, object, update, cache);
            }
        }
    }

    public static TaskEntity copy(Realm realm, TaskEntity newObject, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        RealmObjectProxy cachedRealmObject = cache.get(newObject);
        if (cachedRealmObject != null) {
            return (TaskEntity) cachedRealmObject;
        } else {
            // rejecting default values to avoid creating unexpected objects from RealmModel/RealmList fields.
            TaskEntity realmObject = realm.createObjectInternal(TaskEntity.class, ((TaskRealmProxyInterface) newObject).realmGet$id(), false, Collections.<String>emptyList());
            cache.put(newObject, (RealmObjectProxy) realmObject);
            ((TaskRealmProxyInterface) realmObject).realmSet$name(((TaskRealmProxyInterface) newObject).realmGet$name());
            ((TaskRealmProxyInterface) realmObject).realmSet$done(((TaskRealmProxyInterface) newObject).realmGet$done());
            ((TaskRealmProxyInterface) realmObject).realmSet$timestamp(((TaskRealmProxyInterface) newObject).realmGet$timestamp());
            return realmObject;
        }
    }

    public static long insert(Realm realm, TaskEntity object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(TaskEntity.class);
        long tableNativePtr = table.getNativeTablePointer();
        TaskColumnInfo columnInfo = (TaskColumnInfo) realm.schema.getColumnInfo(TaskEntity.class);
        long pkColumnIndex = table.getPrimaryKey();
        long rowIndex = Table.NO_MATCH;
        Object primaryKeyValue = ((TaskRealmProxyInterface) object).realmGet$id();
        if (primaryKeyValue != null) {
            rowIndex = Table.nativeFindFirstString(tableNativePtr, pkColumnIndex, (String)primaryKeyValue);
        }
        if (rowIndex == Table.NO_MATCH) {
            rowIndex = table.addEmptyRowWithPrimaryKey(primaryKeyValue, false);
        } else {
            Table.throwDuplicatePrimaryKeyException(primaryKeyValue);
        }
        cache.put(object, rowIndex);
        String realmGet$name = ((TaskRealmProxyInterface)object).realmGet$name();
        if (realmGet$name != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.nameIndex, rowIndex, realmGet$name, false);
        }
        Table.nativeSetBoolean(tableNativePtr, columnInfo.doneIndex, rowIndex, ((TaskRealmProxyInterface)object).realmGet$done(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.timestampIndex, rowIndex, ((TaskRealmProxyInterface)object).realmGet$timestamp(), false);
        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(TaskEntity.class);
        long tableNativePtr = table.getNativeTablePointer();
        TaskColumnInfo columnInfo = (TaskColumnInfo) realm.schema.getColumnInfo(TaskEntity.class);
        long pkColumnIndex = table.getPrimaryKey();
        TaskEntity object = null;
        while (objects.hasNext()) {
            object = (TaskEntity) objects.next();
            if(!cache.containsKey(object)) {
                if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex());
                    continue;
                }
                long rowIndex = Table.NO_MATCH;
                Object primaryKeyValue = ((TaskRealmProxyInterface) object).realmGet$id();
                if (primaryKeyValue != null) {
                    rowIndex = Table.nativeFindFirstString(tableNativePtr, pkColumnIndex, (String)primaryKeyValue);
                }
                if (rowIndex == Table.NO_MATCH) {
                    rowIndex = table.addEmptyRowWithPrimaryKey(primaryKeyValue, false);
                } else {
                    Table.throwDuplicatePrimaryKeyException(primaryKeyValue);
                }
                cache.put(object, rowIndex);
                String realmGet$name = ((TaskRealmProxyInterface)object).realmGet$name();
                if (realmGet$name != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.nameIndex, rowIndex, realmGet$name, false);
                }
                Table.nativeSetBoolean(tableNativePtr, columnInfo.doneIndex, rowIndex, ((TaskRealmProxyInterface)object).realmGet$done(), false);
                Table.nativeSetLong(tableNativePtr, columnInfo.timestampIndex, rowIndex, ((TaskRealmProxyInterface)object).realmGet$timestamp(), false);
            }
        }
    }

    public static long insertOrUpdate(Realm realm, TaskEntity object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(TaskEntity.class);
        long tableNativePtr = table.getNativeTablePointer();
        TaskColumnInfo columnInfo = (TaskColumnInfo) realm.schema.getColumnInfo(TaskEntity.class);
        long pkColumnIndex = table.getPrimaryKey();
        long rowIndex = Table.NO_MATCH;
        Object primaryKeyValue = ((TaskRealmProxyInterface) object).realmGet$id();
        if (primaryKeyValue != null) {
            rowIndex = Table.nativeFindFirstString(tableNativePtr, pkColumnIndex, (String)primaryKeyValue);
        }
        if (rowIndex == Table.NO_MATCH) {
            rowIndex = table.addEmptyRowWithPrimaryKey(primaryKeyValue, false);
        }
        cache.put(object, rowIndex);
        String realmGet$name = ((TaskRealmProxyInterface)object).realmGet$name();
        if (realmGet$name != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.nameIndex, rowIndex, realmGet$name, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.nameIndex, rowIndex, false);
        }
        Table.nativeSetBoolean(tableNativePtr, columnInfo.doneIndex, rowIndex, ((TaskRealmProxyInterface)object).realmGet$done(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.timestampIndex, rowIndex, ((TaskRealmProxyInterface)object).realmGet$timestamp(), false);
        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(TaskEntity.class);
        long tableNativePtr = table.getNativeTablePointer();
        TaskColumnInfo columnInfo = (TaskColumnInfo) realm.schema.getColumnInfo(TaskEntity.class);
        long pkColumnIndex = table.getPrimaryKey();
        TaskEntity object = null;
        while (objects.hasNext()) {
            object = (TaskEntity) objects.next();
            if(!cache.containsKey(object)) {
                if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex());
                    continue;
                }
                long rowIndex = Table.NO_MATCH;
                Object primaryKeyValue = ((TaskRealmProxyInterface) object).realmGet$id();
                if (primaryKeyValue != null) {
                    rowIndex = Table.nativeFindFirstString(tableNativePtr, pkColumnIndex, (String)primaryKeyValue);
                }
                if (rowIndex == Table.NO_MATCH) {
                    rowIndex = table.addEmptyRowWithPrimaryKey(primaryKeyValue, false);
                }
                cache.put(object, rowIndex);
                String realmGet$name = ((TaskRealmProxyInterface)object).realmGet$name();
                if (realmGet$name != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.nameIndex, rowIndex, realmGet$name, false);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.nameIndex, rowIndex, false);
                }
                Table.nativeSetBoolean(tableNativePtr, columnInfo.doneIndex, rowIndex, ((TaskRealmProxyInterface)object).realmGet$done(), false);
                Table.nativeSetLong(tableNativePtr, columnInfo.timestampIndex, rowIndex, ((TaskRealmProxyInterface)object).realmGet$timestamp(), false);
            }
        }
    }

    public static TaskEntity createDetachedCopy(TaskEntity realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmModel> cachedObject = cache.get(realmObject);
        TaskEntity unmanagedObject;
        if (cachedObject != null) {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (TaskEntity)cachedObject.object;
            } else {
                unmanagedObject = (TaskEntity)cachedObject.object;
                cachedObject.minDepth = currentDepth;
            }
        } else {
            unmanagedObject = new TaskEntity();
            cache.put(realmObject, new RealmObjectProxy.CacheData<RealmModel>(currentDepth, unmanagedObject));
        }
        ((TaskRealmProxyInterface) unmanagedObject).realmSet$id(((TaskRealmProxyInterface) realmObject).realmGet$id());
        ((TaskRealmProxyInterface) unmanagedObject).realmSet$name(((TaskRealmProxyInterface) realmObject).realmGet$name());
        ((TaskRealmProxyInterface) unmanagedObject).realmSet$done(((TaskRealmProxyInterface) realmObject).realmGet$done());
        ((TaskRealmProxyInterface) unmanagedObject).realmSet$timestamp(((TaskRealmProxyInterface) realmObject).realmGet$timestamp());
        return unmanagedObject;
    }

    static TaskEntity update(Realm realm, TaskEntity realmObject, TaskEntity newObject, Map<RealmModel, RealmObjectProxy> cache) {
        ((TaskRealmProxyInterface) realmObject).realmSet$name(((TaskRealmProxyInterface) newObject).realmGet$name());
        ((TaskRealmProxyInterface) realmObject).realmSet$done(((TaskRealmProxyInterface) newObject).realmGet$done());
        ((TaskRealmProxyInterface) realmObject).realmSet$timestamp(((TaskRealmProxyInterface) newObject).realmGet$timestamp());
        return realmObject;
    }

    @Override
    @SuppressWarnings("ArrayToString")
    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("TaskEntity = [");
        stringBuilder.append("{id:");
        stringBuilder.append(realmGet$id());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{name:");
        stringBuilder.append(realmGet$name());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{done:");
        stringBuilder.append(realmGet$done());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{timestamp:");
        stringBuilder.append(realmGet$timestamp());
        stringBuilder.append("}");
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    @Override
    public ProxyState<?> realmGet$proxyState() {
        return proxyState;
    }

    @Override
    public int hashCode() {
        String realmName = proxyState.getRealm$realm().getPath();
        String tableName = proxyState.getRow$realm().getTable().getName();
        long rowIndex = proxyState.getRow$realm().getIndex();

        int result = 17;
        result = 31 * result + ((realmName != null) ? realmName.hashCode() : 0);
        result = 31 * result + ((tableName != null) ? tableName.hashCode() : 0);
        result = 31 * result + (int) (rowIndex ^ (rowIndex >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskRealmProxy aTask = (TaskRealmProxy)o;

        String path = proxyState.getRealm$realm().getPath();
        String otherPath = aTask.proxyState.getRealm$realm().getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;

        String tableName = proxyState.getRow$realm().getTable().getName();
        String otherTableName = aTask.proxyState.getRow$realm().getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (proxyState.getRow$realm().getIndex() != aTask.proxyState.getRow$realm().getIndex()) return false;

        return true;
    }

}
