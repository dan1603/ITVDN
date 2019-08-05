package io.realm;


import android.annotation.TargetApi;
import android.os.Build;
import android.util.JsonReader;
import android.util.JsonToken;
import io.realm.ProxyUtils;
import io.realm.exceptions.RealmMigrationNeededException;
import io.realm.internal.ColumnInfo;
import io.realm.internal.OsList;
import io.realm.internal.OsObject;
import io.realm.internal.OsObjectSchemaInfo;
import io.realm.internal.OsSchemaInfo;
import io.realm.internal.Property;
import io.realm.internal.RealmObjectProxy;
import io.realm.internal.Row;
import io.realm.internal.Table;
import io.realm.internal.android.JsonUtils;
import io.realm.log.RealmLog;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@SuppressWarnings("all")
public class com_kalashnyk_denys_simplemvpapp_data_database_entity_UserEntityRealmProxy extends com.kalashnyk.denys.simplemvpapp.data.database.entity.UserEntity
    implements RealmObjectProxy, com_kalashnyk_denys_simplemvpapp_data_database_entity_UserEntityRealmProxyInterface {

    static final class UserEntityColumnInfo extends ColumnInfo {
        long mIdIndex;
        long mNameIndex;
        long mSurnameIndex;
        long mFathernameIndex;

        UserEntityColumnInfo(OsSchemaInfo schemaInfo) {
            super(4);
            OsObjectSchemaInfo objectSchemaInfo = schemaInfo.getObjectSchemaInfo("UserEntity");
            this.mIdIndex = addColumnDetails("mId", "mId", objectSchemaInfo);
            this.mNameIndex = addColumnDetails("mName", "mName", objectSchemaInfo);
            this.mSurnameIndex = addColumnDetails("mSurname", "mSurname", objectSchemaInfo);
            this.mFathernameIndex = addColumnDetails("mFathername", "mFathername", objectSchemaInfo);
        }

        UserEntityColumnInfo(ColumnInfo src, boolean mutable) {
            super(src, mutable);
            copy(src, this);
        }

        @Override
        protected final ColumnInfo copy(boolean mutable) {
            return new UserEntityColumnInfo(this, mutable);
        }

        @Override
        protected final void copy(ColumnInfo rawSrc, ColumnInfo rawDst) {
            final UserEntityColumnInfo src = (UserEntityColumnInfo) rawSrc;
            final UserEntityColumnInfo dst = (UserEntityColumnInfo) rawDst;
            dst.mIdIndex = src.mIdIndex;
            dst.mNameIndex = src.mNameIndex;
            dst.mSurnameIndex = src.mSurnameIndex;
            dst.mFathernameIndex = src.mFathernameIndex;
        }
    }

    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();

    private UserEntityColumnInfo columnInfo;
    private ProxyState<com.kalashnyk.denys.simplemvpapp.data.database.entity.UserEntity> proxyState;

    com_kalashnyk_denys_simplemvpapp_data_database_entity_UserEntityRealmProxy() {
        proxyState.setConstructionFinished();
    }

    @Override
    public void realm$injectObjectContext() {
        if (this.proxyState != null) {
            return;
        }
        final BaseRealm.RealmObjectContext context = BaseRealm.objectContext.get();
        this.columnInfo = (UserEntityColumnInfo) context.getColumnInfo();
        this.proxyState = new ProxyState<com.kalashnyk.denys.simplemvpapp.data.database.entity.UserEntity>(this);
        proxyState.setRealm$realm(context.getRealm());
        proxyState.setRow$realm(context.getRow());
        proxyState.setAcceptDefaultValue$realm(context.getAcceptDefaultValue());
        proxyState.setExcludeFields$realm(context.getExcludeFields());
    }

    @Override
    @SuppressWarnings("cast")
    public Long realmGet$mId() {
        proxyState.getRealm$realm().checkIfValid();
        if (proxyState.getRow$realm().isNull(columnInfo.mIdIndex)) {
            return null;
        }
        return (long) proxyState.getRow$realm().getLong(columnInfo.mIdIndex);
    }

    @Override
    public void realmSet$mId(Long value) {
        if (proxyState.isUnderConstruction()) {
            // default value of the primary key is always ignored.
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        throw new io.realm.exceptions.RealmException("Primary key field 'mId' cannot be changed after object was created.");
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$mName() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.mNameIndex);
    }

    @Override
    public void realmSet$mName(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.mNameIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.mNameIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.mNameIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.mNameIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$mSurname() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.mSurnameIndex);
    }

    @Override
    public void realmSet$mSurname(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.mSurnameIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.mSurnameIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.mSurnameIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.mSurnameIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$mFathername() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.mFathernameIndex);
    }

    @Override
    public void realmSet$mFathername(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.mFathernameIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.mFathernameIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.mFathernameIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.mFathernameIndex, value);
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        OsObjectSchemaInfo.Builder builder = new OsObjectSchemaInfo.Builder("UserEntity", 4, 0);
        builder.addPersistedProperty("mId", RealmFieldType.INTEGER, Property.PRIMARY_KEY, Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("mName", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("mSurname", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("mFathername", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static UserEntityColumnInfo createColumnInfo(OsSchemaInfo schemaInfo) {
        return new UserEntityColumnInfo(schemaInfo);
    }

    public static String getSimpleClassName() {
        return "UserEntity";
    }

    public static final class ClassNameHelper {
        public static final String INTERNAL_CLASS_NAME = "UserEntity";
    }

    @SuppressWarnings("cast")
    public static com.kalashnyk.denys.simplemvpapp.data.database.entity.UserEntity createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        final List<String> excludeFields = Collections.<String> emptyList();
        com.kalashnyk.denys.simplemvpapp.data.database.entity.UserEntity obj = null;
        if (update) {
            Table table = realm.getTable(com.kalashnyk.denys.simplemvpapp.data.database.entity.UserEntity.class);
            UserEntityColumnInfo columnInfo = (UserEntityColumnInfo) realm.getSchema().getColumnInfo(com.kalashnyk.denys.simplemvpapp.data.database.entity.UserEntity.class);
            long pkColumnIndex = columnInfo.mIdIndex;
            long rowIndex = Table.NO_MATCH;
            if (json.isNull("mId")) {
                rowIndex = table.findFirstNull(pkColumnIndex);
            } else {
                rowIndex = table.findFirstLong(pkColumnIndex, json.getLong("mId"));
            }
            if (rowIndex != Table.NO_MATCH) {
                final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
                try {
                    objectContext.set(realm, table.getUncheckedRow(rowIndex), realm.getSchema().getColumnInfo(com.kalashnyk.denys.simplemvpapp.data.database.entity.UserEntity.class), false, Collections.<String> emptyList());
                    obj = new io.realm.com_kalashnyk_denys_simplemvpapp_data_database_entity_UserEntityRealmProxy();
                } finally {
                    objectContext.clear();
                }
            }
        }
        if (obj == null) {
            if (json.has("mId")) {
                if (json.isNull("mId")) {
                    obj = (io.realm.com_kalashnyk_denys_simplemvpapp_data_database_entity_UserEntityRealmProxy) realm.createObjectInternal(com.kalashnyk.denys.simplemvpapp.data.database.entity.UserEntity.class, null, true, excludeFields);
                } else {
                    obj = (io.realm.com_kalashnyk_denys_simplemvpapp_data_database_entity_UserEntityRealmProxy) realm.createObjectInternal(com.kalashnyk.denys.simplemvpapp.data.database.entity.UserEntity.class, json.getLong("mId"), true, excludeFields);
                }
            } else {
                throw new IllegalArgumentException("JSON object doesn't have the primary key field 'mId'.");
            }
        }

        final com_kalashnyk_denys_simplemvpapp_data_database_entity_UserEntityRealmProxyInterface objProxy = (com_kalashnyk_denys_simplemvpapp_data_database_entity_UserEntityRealmProxyInterface) obj;
        if (json.has("mName")) {
            if (json.isNull("mName")) {
                objProxy.realmSet$mName(null);
            } else {
                objProxy.realmSet$mName((String) json.getString("mName"));
            }
        }
        if (json.has("mSurname")) {
            if (json.isNull("mSurname")) {
                objProxy.realmSet$mSurname(null);
            } else {
                objProxy.realmSet$mSurname((String) json.getString("mSurname"));
            }
        }
        if (json.has("mFathername")) {
            if (json.isNull("mFathername")) {
                objProxy.realmSet$mFathername(null);
            } else {
                objProxy.realmSet$mFathername((String) json.getString("mFathername"));
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static com.kalashnyk.denys.simplemvpapp.data.database.entity.UserEntity createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        boolean jsonHasPrimaryKey = false;
        final com.kalashnyk.denys.simplemvpapp.data.database.entity.UserEntity obj = new com.kalashnyk.denys.simplemvpapp.data.database.entity.UserEntity();
        final com_kalashnyk_denys_simplemvpapp_data_database_entity_UserEntityRealmProxyInterface objProxy = (com_kalashnyk_denys_simplemvpapp_data_database_entity_UserEntityRealmProxyInterface) obj;
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (false) {
            } else if (name.equals("mId")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$mId((long) reader.nextLong());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$mId(null);
                }
                jsonHasPrimaryKey = true;
            } else if (name.equals("mName")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$mName((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$mName(null);
                }
            } else if (name.equals("mSurname")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$mSurname((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$mSurname(null);
                }
            } else if (name.equals("mFathername")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$mFathername((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$mFathername(null);
                }
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        if (!jsonHasPrimaryKey) {
            throw new IllegalArgumentException("JSON object doesn't have the primary key field 'mId'.");
        }
        return realm.copyToRealm(obj);
    }

    public static com.kalashnyk.denys.simplemvpapp.data.database.entity.UserEntity copyOrUpdate(Realm realm, com.kalashnyk.denys.simplemvpapp.data.database.entity.UserEntity object, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null) {
            final BaseRealm otherRealm = ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm();
            if (otherRealm.threadId != realm.threadId) {
                throw new IllegalArgumentException("Objects which belong to Realm instances in other threads cannot be copied into this Realm instance.");
            }
            if (otherRealm.getPath().equals(realm.getPath())) {
                return object;
            }
        }
        final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
        RealmObjectProxy cachedRealmObject = cache.get(object);
        if (cachedRealmObject != null) {
            return (com.kalashnyk.denys.simplemvpapp.data.database.entity.UserEntity) cachedRealmObject;
        }

        com.kalashnyk.denys.simplemvpapp.data.database.entity.UserEntity realmObject = null;
        boolean canUpdate = update;
        if (canUpdate) {
            Table table = realm.getTable(com.kalashnyk.denys.simplemvpapp.data.database.entity.UserEntity.class);
            UserEntityColumnInfo columnInfo = (UserEntityColumnInfo) realm.getSchema().getColumnInfo(com.kalashnyk.denys.simplemvpapp.data.database.entity.UserEntity.class);
            long pkColumnIndex = columnInfo.mIdIndex;
            Number value = ((com_kalashnyk_denys_simplemvpapp_data_database_entity_UserEntityRealmProxyInterface) object).realmGet$mId();
            long rowIndex = Table.NO_MATCH;
            if (value == null) {
                rowIndex = table.findFirstNull(pkColumnIndex);
            } else {
                rowIndex = table.findFirstLong(pkColumnIndex, value.longValue());
            }
            if (rowIndex == Table.NO_MATCH) {
                canUpdate = false;
            } else {
                try {
                    objectContext.set(realm, table.getUncheckedRow(rowIndex), realm.getSchema().getColumnInfo(com.kalashnyk.denys.simplemvpapp.data.database.entity.UserEntity.class), false, Collections.<String> emptyList());
                    realmObject = new io.realm.com_kalashnyk_denys_simplemvpapp_data_database_entity_UserEntityRealmProxy();
                    cache.put(object, (RealmObjectProxy) realmObject);
                } finally {
                    objectContext.clear();
                }
            }
        }

        return (canUpdate) ? update(realm, realmObject, object, cache) : copy(realm, object, update, cache);
    }

    public static com.kalashnyk.denys.simplemvpapp.data.database.entity.UserEntity copy(Realm realm, com.kalashnyk.denys.simplemvpapp.data.database.entity.UserEntity newObject, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        RealmObjectProxy cachedRealmObject = cache.get(newObject);
        if (cachedRealmObject != null) {
            return (com.kalashnyk.denys.simplemvpapp.data.database.entity.UserEntity) cachedRealmObject;
        }

        // rejecting default values to avoid creating unexpected objects from RealmModel/RealmList fields.
        com.kalashnyk.denys.simplemvpapp.data.database.entity.UserEntity realmObject = realm.createObjectInternal(com.kalashnyk.denys.simplemvpapp.data.database.entity.UserEntity.class, ((com_kalashnyk_denys_simplemvpapp_data_database_entity_UserEntityRealmProxyInterface) newObject).realmGet$mId(), false, Collections.<String>emptyList());
        cache.put(newObject, (RealmObjectProxy) realmObject);

        com_kalashnyk_denys_simplemvpapp_data_database_entity_UserEntityRealmProxyInterface realmObjectSource = (com_kalashnyk_denys_simplemvpapp_data_database_entity_UserEntityRealmProxyInterface) newObject;
        com_kalashnyk_denys_simplemvpapp_data_database_entity_UserEntityRealmProxyInterface realmObjectCopy = (com_kalashnyk_denys_simplemvpapp_data_database_entity_UserEntityRealmProxyInterface) realmObject;

        realmObjectCopy.realmSet$mName(realmObjectSource.realmGet$mName());
        realmObjectCopy.realmSet$mSurname(realmObjectSource.realmGet$mSurname());
        realmObjectCopy.realmSet$mFathername(realmObjectSource.realmGet$mFathername());
        return realmObject;
    }

    public static long insert(Realm realm, com.kalashnyk.denys.simplemvpapp.data.database.entity.UserEntity object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.kalashnyk.denys.simplemvpapp.data.database.entity.UserEntity.class);
        long tableNativePtr = table.getNativePtr();
        UserEntityColumnInfo columnInfo = (UserEntityColumnInfo) realm.getSchema().getColumnInfo(com.kalashnyk.denys.simplemvpapp.data.database.entity.UserEntity.class);
        long pkColumnIndex = columnInfo.mIdIndex;
        Object primaryKeyValue = ((com_kalashnyk_denys_simplemvpapp_data_database_entity_UserEntityRealmProxyInterface) object).realmGet$mId();
        long rowIndex = Table.NO_MATCH;
        if (primaryKeyValue == null) {
            rowIndex = Table.nativeFindFirstNull(tableNativePtr, pkColumnIndex);
        } else {
            rowIndex = Table.nativeFindFirstInt(tableNativePtr, pkColumnIndex, ((com_kalashnyk_denys_simplemvpapp_data_database_entity_UserEntityRealmProxyInterface) object).realmGet$mId());
        }
        if (rowIndex == Table.NO_MATCH) {
            rowIndex = OsObject.createRowWithPrimaryKey(table, pkColumnIndex, ((com_kalashnyk_denys_simplemvpapp_data_database_entity_UserEntityRealmProxyInterface) object).realmGet$mId());
        } else {
            Table.throwDuplicatePrimaryKeyException(primaryKeyValue);
        }
        cache.put(object, rowIndex);
        String realmGet$mName = ((com_kalashnyk_denys_simplemvpapp_data_database_entity_UserEntityRealmProxyInterface) object).realmGet$mName();
        if (realmGet$mName != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.mNameIndex, rowIndex, realmGet$mName, false);
        }
        String realmGet$mSurname = ((com_kalashnyk_denys_simplemvpapp_data_database_entity_UserEntityRealmProxyInterface) object).realmGet$mSurname();
        if (realmGet$mSurname != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.mSurnameIndex, rowIndex, realmGet$mSurname, false);
        }
        String realmGet$mFathername = ((com_kalashnyk_denys_simplemvpapp_data_database_entity_UserEntityRealmProxyInterface) object).realmGet$mFathername();
        if (realmGet$mFathername != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.mFathernameIndex, rowIndex, realmGet$mFathername, false);
        }
        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.kalashnyk.denys.simplemvpapp.data.database.entity.UserEntity.class);
        long tableNativePtr = table.getNativePtr();
        UserEntityColumnInfo columnInfo = (UserEntityColumnInfo) realm.getSchema().getColumnInfo(com.kalashnyk.denys.simplemvpapp.data.database.entity.UserEntity.class);
        long pkColumnIndex = columnInfo.mIdIndex;
        com.kalashnyk.denys.simplemvpapp.data.database.entity.UserEntity object = null;
        while (objects.hasNext()) {
            object = (com.kalashnyk.denys.simplemvpapp.data.database.entity.UserEntity) objects.next();
            if (cache.containsKey(object)) {
                continue;
            }
            if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                cache.put(object, ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex());
                continue;
            }
            Object primaryKeyValue = ((com_kalashnyk_denys_simplemvpapp_data_database_entity_UserEntityRealmProxyInterface) object).realmGet$mId();
            long rowIndex = Table.NO_MATCH;
            if (primaryKeyValue == null) {
                rowIndex = Table.nativeFindFirstNull(tableNativePtr, pkColumnIndex);
            } else {
                rowIndex = Table.nativeFindFirstInt(tableNativePtr, pkColumnIndex, ((com_kalashnyk_denys_simplemvpapp_data_database_entity_UserEntityRealmProxyInterface) object).realmGet$mId());
            }
            if (rowIndex == Table.NO_MATCH) {
                rowIndex = OsObject.createRowWithPrimaryKey(table, pkColumnIndex, ((com_kalashnyk_denys_simplemvpapp_data_database_entity_UserEntityRealmProxyInterface) object).realmGet$mId());
            } else {
                Table.throwDuplicatePrimaryKeyException(primaryKeyValue);
            }
            cache.put(object, rowIndex);
            String realmGet$mName = ((com_kalashnyk_denys_simplemvpapp_data_database_entity_UserEntityRealmProxyInterface) object).realmGet$mName();
            if (realmGet$mName != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.mNameIndex, rowIndex, realmGet$mName, false);
            }
            String realmGet$mSurname = ((com_kalashnyk_denys_simplemvpapp_data_database_entity_UserEntityRealmProxyInterface) object).realmGet$mSurname();
            if (realmGet$mSurname != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.mSurnameIndex, rowIndex, realmGet$mSurname, false);
            }
            String realmGet$mFathername = ((com_kalashnyk_denys_simplemvpapp_data_database_entity_UserEntityRealmProxyInterface) object).realmGet$mFathername();
            if (realmGet$mFathername != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.mFathernameIndex, rowIndex, realmGet$mFathername, false);
            }
        }
    }

    public static long insertOrUpdate(Realm realm, com.kalashnyk.denys.simplemvpapp.data.database.entity.UserEntity object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.kalashnyk.denys.simplemvpapp.data.database.entity.UserEntity.class);
        long tableNativePtr = table.getNativePtr();
        UserEntityColumnInfo columnInfo = (UserEntityColumnInfo) realm.getSchema().getColumnInfo(com.kalashnyk.denys.simplemvpapp.data.database.entity.UserEntity.class);
        long pkColumnIndex = columnInfo.mIdIndex;
        Object primaryKeyValue = ((com_kalashnyk_denys_simplemvpapp_data_database_entity_UserEntityRealmProxyInterface) object).realmGet$mId();
        long rowIndex = Table.NO_MATCH;
        if (primaryKeyValue == null) {
            rowIndex = Table.nativeFindFirstNull(tableNativePtr, pkColumnIndex);
        } else {
            rowIndex = Table.nativeFindFirstInt(tableNativePtr, pkColumnIndex, ((com_kalashnyk_denys_simplemvpapp_data_database_entity_UserEntityRealmProxyInterface) object).realmGet$mId());
        }
        if (rowIndex == Table.NO_MATCH) {
            rowIndex = OsObject.createRowWithPrimaryKey(table, pkColumnIndex, ((com_kalashnyk_denys_simplemvpapp_data_database_entity_UserEntityRealmProxyInterface) object).realmGet$mId());
        }
        cache.put(object, rowIndex);
        String realmGet$mName = ((com_kalashnyk_denys_simplemvpapp_data_database_entity_UserEntityRealmProxyInterface) object).realmGet$mName();
        if (realmGet$mName != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.mNameIndex, rowIndex, realmGet$mName, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.mNameIndex, rowIndex, false);
        }
        String realmGet$mSurname = ((com_kalashnyk_denys_simplemvpapp_data_database_entity_UserEntityRealmProxyInterface) object).realmGet$mSurname();
        if (realmGet$mSurname != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.mSurnameIndex, rowIndex, realmGet$mSurname, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.mSurnameIndex, rowIndex, false);
        }
        String realmGet$mFathername = ((com_kalashnyk_denys_simplemvpapp_data_database_entity_UserEntityRealmProxyInterface) object).realmGet$mFathername();
        if (realmGet$mFathername != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.mFathernameIndex, rowIndex, realmGet$mFathername, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.mFathernameIndex, rowIndex, false);
        }
        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.kalashnyk.denys.simplemvpapp.data.database.entity.UserEntity.class);
        long tableNativePtr = table.getNativePtr();
        UserEntityColumnInfo columnInfo = (UserEntityColumnInfo) realm.getSchema().getColumnInfo(com.kalashnyk.denys.simplemvpapp.data.database.entity.UserEntity.class);
        long pkColumnIndex = columnInfo.mIdIndex;
        com.kalashnyk.denys.simplemvpapp.data.database.entity.UserEntity object = null;
        while (objects.hasNext()) {
            object = (com.kalashnyk.denys.simplemvpapp.data.database.entity.UserEntity) objects.next();
            if (cache.containsKey(object)) {
                continue;
            }
            if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                cache.put(object, ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex());
                continue;
            }
            Object primaryKeyValue = ((com_kalashnyk_denys_simplemvpapp_data_database_entity_UserEntityRealmProxyInterface) object).realmGet$mId();
            long rowIndex = Table.NO_MATCH;
            if (primaryKeyValue == null) {
                rowIndex = Table.nativeFindFirstNull(tableNativePtr, pkColumnIndex);
            } else {
                rowIndex = Table.nativeFindFirstInt(tableNativePtr, pkColumnIndex, ((com_kalashnyk_denys_simplemvpapp_data_database_entity_UserEntityRealmProxyInterface) object).realmGet$mId());
            }
            if (rowIndex == Table.NO_MATCH) {
                rowIndex = OsObject.createRowWithPrimaryKey(table, pkColumnIndex, ((com_kalashnyk_denys_simplemvpapp_data_database_entity_UserEntityRealmProxyInterface) object).realmGet$mId());
            }
            cache.put(object, rowIndex);
            String realmGet$mName = ((com_kalashnyk_denys_simplemvpapp_data_database_entity_UserEntityRealmProxyInterface) object).realmGet$mName();
            if (realmGet$mName != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.mNameIndex, rowIndex, realmGet$mName, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.mNameIndex, rowIndex, false);
            }
            String realmGet$mSurname = ((com_kalashnyk_denys_simplemvpapp_data_database_entity_UserEntityRealmProxyInterface) object).realmGet$mSurname();
            if (realmGet$mSurname != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.mSurnameIndex, rowIndex, realmGet$mSurname, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.mSurnameIndex, rowIndex, false);
            }
            String realmGet$mFathername = ((com_kalashnyk_denys_simplemvpapp_data_database_entity_UserEntityRealmProxyInterface) object).realmGet$mFathername();
            if (realmGet$mFathername != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.mFathernameIndex, rowIndex, realmGet$mFathername, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.mFathernameIndex, rowIndex, false);
            }
        }
    }

    public static com.kalashnyk.denys.simplemvpapp.data.database.entity.UserEntity createDetachedCopy(com.kalashnyk.denys.simplemvpapp.data.database.entity.UserEntity realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmModel> cachedObject = cache.get(realmObject);
        com.kalashnyk.denys.simplemvpapp.data.database.entity.UserEntity unmanagedObject;
        if (cachedObject == null) {
            unmanagedObject = new com.kalashnyk.denys.simplemvpapp.data.database.entity.UserEntity();
            cache.put(realmObject, new RealmObjectProxy.CacheData<RealmModel>(currentDepth, unmanagedObject));
        } else {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (com.kalashnyk.denys.simplemvpapp.data.database.entity.UserEntity) cachedObject.object;
            }
            unmanagedObject = (com.kalashnyk.denys.simplemvpapp.data.database.entity.UserEntity) cachedObject.object;
            cachedObject.minDepth = currentDepth;
        }
        com_kalashnyk_denys_simplemvpapp_data_database_entity_UserEntityRealmProxyInterface unmanagedCopy = (com_kalashnyk_denys_simplemvpapp_data_database_entity_UserEntityRealmProxyInterface) unmanagedObject;
        com_kalashnyk_denys_simplemvpapp_data_database_entity_UserEntityRealmProxyInterface realmSource = (com_kalashnyk_denys_simplemvpapp_data_database_entity_UserEntityRealmProxyInterface) realmObject;
        unmanagedCopy.realmSet$mId(realmSource.realmGet$mId());
        unmanagedCopy.realmSet$mName(realmSource.realmGet$mName());
        unmanagedCopy.realmSet$mSurname(realmSource.realmGet$mSurname());
        unmanagedCopy.realmSet$mFathername(realmSource.realmGet$mFathername());

        return unmanagedObject;
    }

    static com.kalashnyk.denys.simplemvpapp.data.database.entity.UserEntity update(Realm realm, com.kalashnyk.denys.simplemvpapp.data.database.entity.UserEntity realmObject, com.kalashnyk.denys.simplemvpapp.data.database.entity.UserEntity newObject, Map<RealmModel, RealmObjectProxy> cache) {
        com_kalashnyk_denys_simplemvpapp_data_database_entity_UserEntityRealmProxyInterface realmObjectTarget = (com_kalashnyk_denys_simplemvpapp_data_database_entity_UserEntityRealmProxyInterface) realmObject;
        com_kalashnyk_denys_simplemvpapp_data_database_entity_UserEntityRealmProxyInterface realmObjectSource = (com_kalashnyk_denys_simplemvpapp_data_database_entity_UserEntityRealmProxyInterface) newObject;
        realmObjectTarget.realmSet$mName(realmObjectSource.realmGet$mName());
        realmObjectTarget.realmSet$mSurname(realmObjectSource.realmGet$mSurname());
        realmObjectTarget.realmSet$mFathername(realmObjectSource.realmGet$mFathername());
        return realmObject;
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
        com_kalashnyk_denys_simplemvpapp_data_database_entity_UserEntityRealmProxy aUserEntity = (com_kalashnyk_denys_simplemvpapp_data_database_entity_UserEntityRealmProxy)o;

        String path = proxyState.getRealm$realm().getPath();
        String otherPath = aUserEntity.proxyState.getRealm$realm().getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;

        String tableName = proxyState.getRow$realm().getTable().getName();
        String otherTableName = aUserEntity.proxyState.getRow$realm().getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (proxyState.getRow$realm().getIndex() != aUserEntity.proxyState.getRow$realm().getIndex()) return false;

        return true;
    }
}
